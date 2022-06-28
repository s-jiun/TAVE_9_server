package com.tave_app_1.senapool.user.controller;


import com.tave_app_1.senapool.entity.User;
import com.tave_app_1.senapool.jwt.JwtFilter;
import com.tave_app_1.senapool.jwt.TokenProvider;
import com.tave_app_1.senapool.user.dto.TokenDto;
import com.tave_app_1.senapool.user.dto.UserDto;
import com.tave_app_1.senapool.user.dto.UserLoginDto;
import com.tave_app_1.senapool.user.dto.UserUpdateDto;
import com.tave_app_1.senapool.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@RestController
public class UserController {

    private final UserService userService;

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public UserController(UserService userService, TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.userService = userService;
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    @PostMapping("/user/signup") // 회원 가입
    public ResponseEntity<?> userSignUp(
            @Valid @RequestBody UserDto userDto) {
        log.info("user={}", userDto);
        return userService.join(userDto);
    }

    @PostMapping("/user/login") //로그인
    public ResponseEntity<?> login(@RequestBody UserLoginDto userLoginDTO) throws NoSuchElementException {
        return userService.login(userLoginDTO);
    }

    @PatchMapping("/user/update") // 회원 정보 수정
    public User userUpdate(@RequestBody UserUpdateDto userUpdateDto) {
        return userService.userInfoUpdate(userUpdateDto);
    }
}
