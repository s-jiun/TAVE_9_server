package com.tave_app_1.senapool.user.controller;


import com.tave_app_1.senapool.entity.User;
import com.tave_app_1.senapool.jwt.TokenProvider;
import com.tave_app_1.senapool.user.dto.UserDto;
import com.tave_app_1.senapool.user.dto.UserLoginDto;
import com.tave_app_1.senapool.user.dto.UserPasswordDto;
import com.tave_app_1.senapool.user.service.EmailServiceImpl;
import com.tave_app_1.senapool.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final EmailServiceImpl emailServiceImpl;

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;


    @PostMapping("/user/signup") // 회원 가입
    public ResponseEntity<?> userSignUp(UserDto userDto) {
        log.info("user={}", userDto);
        return userService.join(userDto);
    }

    @PostMapping("/mailConfirm")
    public void emailConfirm(@RequestBody String email) throws Exception {
        log.info("인증 요청 이메일={}",email);
        emailServiceImpl.sendSimpleMessage(email);
    }
    @PostMapping("/verifyCode")
    public int verifyCode(@RequestBody  String code) {
        if(emailServiceImpl.ePw.equals(code)) {
            return 1;
        }
        else
            return 0;
    }
    @PostMapping("/user/login") //로그인
    public ResponseEntity<?> login(@RequestBody UserLoginDto userLoginDTO) throws NoSuchElementException {
        return userService.login(userLoginDTO);
    }

    @PatchMapping("/user/update") // 회원 정보 수정
    public User userUpdate(Authentication authentication, UserDto userDto) {
        User user = (User) authentication.getPrincipal();
        return userService.userInfoUpdate(user.getUserPK(), userDto);
    }

    @PostMapping("/user/temPassword")
    public ResponseEntity<?> setTemPW(@RequestBody String email) throws Exception {
        String temPW = emailServiceImpl.sendSimpleMessage(email);
        return userService.setTemPassword(email, temPW);
    }

    //jwt 토큰 테스트
    @GetMapping("/jwt")
    public void jwtResponse(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        log.info("토큰 유저 정보={}",user.getUserId());
    }

    @DeleteMapping("/user/delete")
    public void deleteUser(Authentication authentication, @RequestBody UserPasswordDto passwordDto) throws Exception{
        User user = (User) authentication.getPrincipal();
        userService.deleteUser(user, passwordDto);
    }
}
