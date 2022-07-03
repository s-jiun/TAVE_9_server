package com.tave_app_1.senapool.user.controller;


import com.tave_app_1.senapool.entity.User;
import com.tave_app_1.senapool.user.dto.UserDto;
import com.tave_app_1.senapool.user.dto.UserLoginDto;
import com.tave_app_1.senapool.user.dto.UserPasswordDto;
import com.tave_app_1.senapool.user.service.EmailServiceImpl;
import com.tave_app_1.senapool.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final EmailServiceImpl emailServiceImpl;

    @ApiOperation(
            value = "회원가입",
            notes = "이메일 인증 후 회원가입이 되어야함, response 200 ok: 성공, 400 bad_request: 실패(메세지: 이미 가입되어 있는 회원입니다.")
    @PostMapping("/user/signup") // 회원 가입
    public ResponseEntity<?> userSignUp(UserDto userDto) {
        log.info("user={}", userDto);
        return userService.join(userDto);
    }

    @ApiOperation(value = "본인 인증 메일 전송", notes = "코드 전송")
    @PostMapping("/mailConfirm")
    public void emailConfirm(@RequestBody String email) throws Exception {
        log.info("인증 요청 이메일={}",email);
        emailServiceImpl.sendSimpleMessage(email);
    }
    @ApiOperation(
            value = "본인 인증 코드 일치 여부 확인",
            notes = "response 1: 일치함 -> 회원가입 진행, 0: 불일치 -> 이메일 인증이 될 때까지(1이 올때까지) 회원가입 진행 하면 안됨")
    @PostMapping("/verifyCode")
    public int verifyCode(@RequestBody  String code) {
        if(emailServiceImpl.ePw.equals(code)) {
            return 1;
        }
        else
            return 0;
    }

    @ApiOperation(value = "로그인", notes = "유저 id로 로그인")
    @PostMapping("/user/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto userLoginDTO) throws NoSuchElementException {
        return userService.login(userLoginDTO);
    }

    @ApiOperation(value = "회원 정보 수정", notes = "토큰 헤더에 넣어서 요청")
    @PatchMapping("/user/update")
    public User userUpdate(Authentication authentication, UserDto userDto) {
        User user = (User) authentication.getPrincipal();
        return userService.userInfoUpdate(user.getUserPK(), userDto);
    }

    @ApiOperation(value = "임시 비밀번호 발급", notes = "이메일에 전송")
    @PatchMapping("/user/temPassword")
    public ResponseEntity<?> setTemPW(@RequestBody String email) throws Exception {
        emailServiceImpl.sendTempPwMessage(email);
        return userService.setTemPassword(email, emailServiceImpl.tempPw);
    }

    @ApiOperation(value = "회원 탈퇴", notes = "'설정 페이지'에서 회원 탈퇴 기능")
    @DeleteMapping("/user/delete")
    public void deleteUser(Authentication authentication, @RequestBody UserPasswordDto passwordDto) throws Exception{
        User user = (User) authentication.getPrincipal();
        userService.deleteUser(user, passwordDto);
    }
}
