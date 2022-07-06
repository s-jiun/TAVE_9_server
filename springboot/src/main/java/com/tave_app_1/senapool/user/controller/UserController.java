package com.tave_app_1.senapool.user.controller;

import com.tave_app_1.senapool.entity.User;
import com.tave_app_1.senapool.user.dto.*;
import com.tave_app_1.senapool.user.service.EmailServiceImpl;
import com.tave_app_1.senapool.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final EmailServiceImpl emailServiceImpl;

    @ApiOperation(
            value = "회원가입",
            notes = "이메일 인증 후 회원가입이 되어야함")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "String 반환: 회원가입 성공"),
            @ApiResponse(code = 400, message = "String 반환: 이미 사용중인 아이디입니다.")
    }
    )
    @PostMapping("/user/signup") // 회원 가입
    public ResponseEntity<?> userSignUp(UserDto userDto) throws IOException {
        log.info("user={}", userDto);
        return userService.join(userDto);
    }

    @ApiOperation(value = "본인 인증 메일 전송", notes = "코드 전송")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "String 반환: 인증코드가 전송 되었습니다"),
            @ApiResponse(code = 400, message = "String 반환: 이미 가입된 아이디가 존재합니다.")
    }
    )
    @PostMapping("/mailConfirm")
    public ResponseEntity<String> emailConfirm(@RequestBody EmailDto emailDto) throws Exception {

        Optional<User> user = userService.findUserId(emailDto.getEmail());
        if (user.isEmpty()) {
            emailServiceImpl.sendSimpleMessage(emailDto.getEmail());
            return new ResponseEntity<>("인증코드가 전송 되었습니다.",HttpStatus.OK);
        } else {
            return new ResponseEntity<>("이미 가입된 아이디가 존재합니다.",HttpStatus.BAD_REQUEST);
        }
    }


    @ApiOperation(
            value = "본인 인증 코드 일치 여부 확인")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Int 반환: 1 -> 일치, 회원가입 진행", response = Integer.class),
            @ApiResponse(code = 400 , message = "Int 반환: 0 -> 불일치, 일치할때까지 회원가입 안되게 해야함", response = Integer.class)
    }
    )
    @PostMapping("/verifyCode")
    public ResponseEntity<Integer> verifyCode(@RequestBody VerifyCodeDto verifyCodeDto) {
        if(emailServiceImpl.ePw.equals(verifyCodeDto.getCode())) {
            return new ResponseEntity<>(1,HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(0,HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "로그인", notes = "유저 id로 로그인")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "JSON 반환: token: token 값"),
            @ApiResponse(code = 404, message = "String 반환: 아이디가 없거나 비밀번호가 일치하지 않음")
    }
    )
    @PostMapping("/user/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto userLoginDTO) throws NoSuchElementException {
        return userService.login(userLoginDTO);
    }

    @ApiOperation(value = "회원 정보 수정", notes = "토큰 헤더에 넣어서 요청")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User 정보 반환"),
    }
    )
    @PatchMapping("/user/update")
    public ResponseEntity<?> userUpdate(Authentication authentication, UserDto userDto) {
        User user = (User) authentication.getPrincipal();
        return userService.userInfoUpdate(user.getUserPK(), userDto);
    }

    @ApiOperation(value = "임시 비밀번호 발급", notes = "이메일에 전송")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "String 반환: 임시 비밀번호가 발급되었습니다."),
            @ApiResponse(code = 404, message = "String 반화: 이메일로 가입된 아이디가 존재하지 않습니다.")
    }
    )
    @PatchMapping("/user/temPassword")
    public ResponseEntity<?> setTemPW(@RequestBody EmailDto emailDto) throws Exception {
        Optional<User> userId = userService.findUserId(emailDto.getEmail());
        log.info("이메일로 찾은 유저={}",userId.get().getUserId());
        if (userId.orElse(null) == null) {
            return new ResponseEntity<>("이메일로 가입된 아이디가 존재하지 않습니다.", HttpStatus.NOT_FOUND);
        }
        emailServiceImpl.sendTempPwMessage(emailDto.getEmail());
        return userService.setTemPassword(emailDto.getEmail(), emailServiceImpl.tempPw);
    }

    @ApiOperation(value = "비밀번호 변경", notes = "토큰과 비밀번호 담아서 요청")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "String 반환: 비밀번호가 변경되었습니다."),
    }
    )
    @PatchMapping("/user/changePw")
    public ResponseEntity<?> changePw(Authentication authentication,@RequestBody PasswordDto passwordDto) {
        User user = (User) authentication.getPrincipal();
        return userService.changePassword(user, passwordDto.getPassword());
    }

    @ApiOperation(value = "아이디 찾기", notes = "이메일로 가입되어 있는 userId 전송")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "String 반환: 아이디가 전송되었습니다"),
            @ApiResponse(code = 404, message = "String 반환: 이메일로 가입된 아이디가 존재하지 않습니다.")
    }
    )
    @GetMapping("/user/findId")
    public ResponseEntity<?> findUserId(@RequestBody EmailDto emailDto) throws Exception {

        Optional<User> user = userService.findUserId(emailDto.getEmail());
        if (user.isEmpty()) {
            return new ResponseEntity<>("이메일로 가입된 아이디가 존재하지 않습니다.", HttpStatus.NOT_FOUND);
        } else {
            emailServiceImpl.sendFindUserMessage(user.get().getUserId(),emailDto.getEmail());
            return new ResponseEntity<>("아이디가 전송되었습니다.",HttpStatus.OK);
        }
    }
    @ApiOperation(value = "회원 탈퇴", notes = "'설정 페이지'에서 회원 탈퇴 기능")
    @DeleteMapping("/user/delete")
    public void deleteUser(Authentication authentication, @RequestBody UserPasswordDto passwordDto) throws Exception{
        User user = (User) authentication.getPrincipal();
        userService.deleteUser(user, passwordDto);
    }
}