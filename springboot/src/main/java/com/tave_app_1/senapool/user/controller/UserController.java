package com.tave_app_1.senapool.user.controller;


import com.tave_app_1.senapool.entity.Member;
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
    public Member userSignUp(@RequestBody Member member) {
        log.info("user={}", member);
        member.setPassword(userService.encryptPassword(member.getPassword()));

        return userService.join(member);
    }

    @PostMapping("/user/login") //로그인
    public Optional<Member> userLogin(@RequestBody UserLoginDTO userLoginDTO) throws NoSuchElementException {
        try {
            Optional<Member> loginMember = userService.login(userLoginDTO.getEmail(), userLoginDTO.getPassword());
            return loginMember;
        } catch (NoSuchElementException e) {
            return Optional.empty();
        }
    }

    @PutMapping("/user/update") // 회원 정보 수정
    public Member userUpdate(@RequestBody Member updateInfoMember ) {
        Member updatedMember = userService.userInfoUpdate(updateInfoMember);
        return updatedMember;
    }
}
