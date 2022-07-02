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

import java.util.Collections;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final FileUtil fileUtil;

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final PlantDiaryService plantDiaryService;
    private final MyPlantService myPlantService;


    public ResponseEntity<?> join(UserDto userDto) {
        if (userRepository.findOneWithAuthoritiesByEmail(userDto.getEmail()).orElse(null) != null) {
            return new ResponseEntity<>("이미 가입되어 있는 회원입니다.",HttpStatus.BAD_REQUEST);
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        /*
        image 설정 안했을 경우 처리.
         */
        String userImage;
        if(userDto.getUserImage().isEmpty()) userImage = "";
        else userImage = fileUtil.saveUserImage(userDto.getUserImage());

        User user = User.builder()
                .userId(userDto.getUserId())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .email(userDto.getEmail())
                .userImageName(userImage)
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
    }

    public ResponseEntity<?> login(UserLoginDto userLoginDto) {
        Optional<User> loginUser = userRepository.findByEmail(userLoginDto.getEmail());
        if ((loginUser.orElse(null) == null) || !passwordEncoder.matches(userLoginDto.getPassword(), loginUser.get().getPassword())) {
            return new ResponseEntity<>("아이디가 없거나 비밀번호가 일치하지 않음",HttpStatus.NOT_FOUND);
        }
        else
            return getTokenDtoResponseEntity(userLoginDto);
    }


    public User userInfoUpdate(Long userPk,UserDto userDto) {
        User user = userRepository.findByUserPK(userPk);

        fileUtil.deleteUserImage(user.getUserImageName());



        user.setUserId(userDto.getUserId());
        user.setEmail(userDto.getEmail());
        user.setUserImageName(fileUtil.saveUserImage(userDto.getUserImage()));

        log.info("업데이트된 유저 정보={}", user);

        return userRepository.save(user);
    }

    
    private ResponseEntity<TokenDto> getTokenDtoResponseEntity(UserLoginDto userLoginDTO) {
        User user = userRepository.findByEmail(userLoginDTO.getEmail()).get();
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userLoginDTO.getEmail(), userLoginDTO.getPassword());
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