package com.tave_app_1.senapool.user.controller;


import com.tave_app_1.senapool.entity.User;
import com.tave_app_1.senapool.user.dto.UserLoginDTO;
import com.tave_app_1.senapool.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Controller
@ResponseBody
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/signup") // 회원 가입
    public User userSignUp(@RequestBody User User) {
        log.info("user={}", User);
        User.setPassword(userService.encryptPassword(User.getPassword()));

        return userService.join(User);
    }

    @PostMapping("/user/login") //로그인
    public Optional<User> userLogin(@RequestBody UserLoginDTO userLoginDTO) throws NoSuchElementException {
        try {
            Optional<User> loginUser = userService.login(userLoginDTO.getEmail(), userLoginDTO.getPassword());
            return loginUser;
        } catch (NoSuchElementException e) {
            return Optional.empty();
        }
    }

    @PutMapping("/user/update") // 회원 정보 수정
    public User userUpdate(@RequestBody User updateInfoUser ) {
        User updatedUser = userService.userInfoUpdate(updateInfoUser);
        return updatedUser;
    }
}
