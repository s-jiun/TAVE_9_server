package com.tave_app_1.senapool.user.service;


import com.tave_app_1.senapool.entity.Authority;
import com.tave_app_1.senapool.entity.User;
import com.tave_app_1.senapool.jwt.JwtFilter;
import com.tave_app_1.senapool.jwt.TokenProvider;
import com.tave_app_1.senapool.myplant_list.service.MyPlantService;
import com.tave_app_1.senapool.plant_diary.service.PlantDiaryService;
import com.tave_app_1.senapool.user.dto.TokenDto;
import com.tave_app_1.senapool.user.dto.UserDto;
import com.tave_app_1.senapool.user.dto.UserLoginDto;
import com.tave_app_1.senapool.user.dto.UserPasswordDto;
import com.tave_app_1.senapool.user.repository.UserRepository;
import com.tave_app_1.senapool.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    public ResponseEntity<?> join(UserDto userDto) throws IOException {
        Optional<User> findUser = userRepository.findByUserId(userDto.getUserId());
        if (findUser.orElse(null) != null) {
            return new ResponseEntity<>("이미 사용중인 아이디입니다.",HttpStatus.BAD_REQUEST);
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        /*
        image 설정 안했을 경우 처리.
         */
        String outputFileName;
        if(userDto.getUserImage().isEmpty()) outputFileName = "Default.png";
        else outputFileName = String.valueOf(UUID.randomUUID());

        User user = User.builder()
                .userId(userDto.getUserId())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .email(userDto.getEmail())
                .userImageName( "user/" + outputFileName)
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        //base64 디코더
        byte[] decodedBytes = Base64.getDecoder().decode(outputFileName);
        //이미지 저장
        FileUtils.writeByteArrayToFile(new File(fileUtil.getUserFolderPath() + outputFileName + ".png"), decodedBytes);

        userRepository.save(user);
        return new ResponseEntity<>("회원가입 성공", HttpStatus.OK);
    }

    public ResponseEntity<?> login(UserLoginDto userLoginDto) {
        Optional<User> loginUser = userRepository.findByUserId(userLoginDto.getUserId());
        if ((loginUser.orElse(null) == null) || !passwordEncoder.matches(userLoginDto.getPassword(), loginUser.get().getPassword())) {
            return new ResponseEntity<>("아이디가 없거나 비밀번호가 일치하지 않음",HttpStatus.NOT_FOUND);
        }
        else
            return getTokenDtoResponseEntity(userLoginDto);
    }


    public ResponseEntity<?> userInfoUpdate(Long userPk,UserDto userDto) throws IOException {
        User user = userRepository.findByUserPK(userPk);

        //Default.png가 아니면 삭제되게 수정해야함
        fileUtil.deleteUserImage(user.getUserImageName());

        /*
        image 설정 안했을 경우 처리.
         */
        String outputFileName;
        if(userDto.getUserImage().isEmpty()) outputFileName = "Default.png";
        else outputFileName = String.valueOf(UUID.randomUUID());

        user.setUserId(userDto.getUserId());
        user.setEmail(userDto.getEmail());
        user.setUserImageName(outputFileName);

        //base64 디코더
        byte[] decodedBytes = Base64.getDecoder().decode(outputFileName);
        //이미지 저장
        FileUtils.writeByteArrayToFile(new File(fileUtil.getUserFolderPath() + outputFileName + ".png"), decodedBytes);log.info("업데이트된 유저 정보={}", user);

        return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
    }

    public ResponseEntity<?> setTemPassword(String email, String temPassword) {
        User user = userRepository.findByEmail(email).get();
        user.setPassword(passwordEncoder.encode(temPassword));
        userRepository.save(user);
        return new ResponseEntity<>("임시 비밀번호가 발급되었습니다.", HttpStatus.OK);
    }

    public ResponseEntity<?> changePassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return new ResponseEntity<>("비밀번호가 변경되었습니다.", HttpStatus.OK);
    }

    public Optional<User> findUserId(String email) {
        return userRepository.findByEmail(email);
    }

    
    private ResponseEntity<TokenDto> getTokenDtoResponseEntity(UserLoginDto userLoginDTO) {
        User user = userRepository.findByUserId(userLoginDTO.getUserId()).get();
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getEmail(), userLoginDTO.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(user.getUserPK(),authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
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