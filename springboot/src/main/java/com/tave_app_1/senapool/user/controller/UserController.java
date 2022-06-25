package com.tave_app_1.senapool.user.controller;


import com.tave_app_1.senapool.entity.User;
import com.tave_app_1.senapool.user.dto.UserDto;
import com.tave_app_1.senapool.user.dto.UserLoginDto;
import com.tave_app_1.senapool.user.dto.UserUpdateDto;
import com.tave_app_1.senapool.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/signup") // 회원 가입
    public ResponseEntity<?> userSignUp(@RequestBody UserDto userDto) {
        log.info("user={}", userDto);
        User user = User.builder()
                .userId(userDto.getUserId())
                .password(userService.encryptPassword(userDto.getPassword()))
                .email(userDto.getEmail())
                .userImage(userDto.getUserImage())
                .build();
        Optional<User> signupUser = userService.join(user);
        if (signupUser.isEmpty())
            return ResponseEntity.ok("이미 가입된 회원");
        else
            return ResponseEntity.ok(signupUser);
    }

    @PostMapping("/user/login") //로그인
    public Optional<User> login(@RequestBody UserLoginDto userLoginDTO) throws NoSuchElementException {
        try {
            Optional<User> loginMember = userService.login(userLoginDTO.getEmail(), userLoginDTO.getPassword());
            return loginMember;
        } catch (NoSuchElementException e) {
            return Optional.empty();
        }
    }

    @PatchMapping("/user/update") // 회원 정보 수정
    public User userUpdate(@RequestBody UserUpdateDto userUpdateDto) {
        return userService.userInfoUpdate(userUpdateDto);
    }
}
