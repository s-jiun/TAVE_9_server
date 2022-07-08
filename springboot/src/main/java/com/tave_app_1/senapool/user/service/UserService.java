package com.tave_app_1.senapool.user.service;


import com.tave_app_1.senapool.entity.Authority;
import com.tave_app_1.senapool.entity.User;
import com.tave_app_1.senapool.jwt.JwtFilter;
import com.tave_app_1.senapool.jwt.TokenProvider;
import com.tave_app_1.senapool.myplant_list.service.MyPlantService;
import com.tave_app_1.senapool.plant_diary.service.PlantDiaryService;
import com.tave_app_1.senapool.user.dto.*;
import com.tave_app_1.senapool.user.repository.UserRepository;
import com.tave_app_1.senapool.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
@PropertySource("classpath:application.properties")
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final FileUtil fileUtil;

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final PlantDiaryService plantDiaryService;
    private final MyPlantService myPlantService;


    public void join(UserDto userDto) throws IOException, Exception {

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        /*
        image 설정 안했을 경우 처리.
         */
        String outputFileName;
        if(userDto.getUserImage().isEmpty()) {
           outputFileName = "Default.png";
        }
        else {
            outputFileName = fileUtil.saveUserImage(userDto.getUserImage());
        }


        User user = User.builder()
                .userId(userDto.getUserId())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .email(userDto.getEmail())
                .userImageName("http://ec2-3-39-104-218.ap-northeast-2.compute.amazonaws.com:8080/images/user/"+outputFileName)
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        userRepository.save(user);
    }

    public ResponseLoginUserDto login(UserLoginDto userLoginDto) throws Exception {
        Optional<User> loginUser = userRepository.findByUserId(userLoginDto.getUserId());
        if ((loginUser.orElse(null) == null) || !passwordEncoder.matches(userLoginDto.getPassword(), loginUser.get().getPassword())) {
            throw new Exception("아이디와 비밀번호가 일치하지 않음");
        }
        else {
            User user = loginUser.get();
            TokenDto tokenDtoResponseEntity = getTokenDtoResponseEntity(userLoginDto);
            return new ResponseLoginUserDto(tokenDtoResponseEntity, user);
        }
    }


    public void userInfoUpdate(User user, UserUpdateDto userUpdateDto) throws IOException {

        //Default.png가 아니면 삭제되게 수정해야함
        if (!user.getUserImageName().equals("Default.png")) {
            fileUtil.deleteUserImage(user.getUserImageName());
        }
        /*
        image 설정 안했을 경우 처리.
         */
        String outputFileName = "Default.png";
        if (!userUpdateDto.getUserImage().isEmpty()) {
            outputFileName = fileUtil.saveUserImage(userUpdateDto.getUserImage());
        }

        user.setUserId(userUpdateDto.getUserId());
        user.setUserImageName(outputFileName);

        userRepository.save(user);
    }

    public void setTemPassword(String email, String temPassword) {
        User user = userRepository.findByEmail(email).get();
        user.setPassword(passwordEncoder.encode(temPassword));
        userRepository.save(user);
    }

    public void changePassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    public Optional<User> findUserId(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findUserById(String userId) {
        return userRepository.findByUserId(userId);
    }


    
    private TokenDto getTokenDtoResponseEntity(UserLoginDto userLoginDTO) {
        User user = userRepository.findByUserId(userLoginDTO.getUserId()).get();
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getEmail(), userLoginDTO.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(user.getUserPK(),authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new TokenDto(jwt);
    }

    @Transactional
    public void deleteUser(User user, UserPasswordDto passwordDto) throws Exception{
        if(passwordEncoder.matches(passwordDto.getPassword(), user.getPassword())){
            plantDiaryService.deleteUserDiaryAll(user);
            myPlantService.deleteUserPlantAll(user);
            fileUtil.deleteUserImage(user.getUserImageName());
            userRepository.delete(user);
        }
    }

}