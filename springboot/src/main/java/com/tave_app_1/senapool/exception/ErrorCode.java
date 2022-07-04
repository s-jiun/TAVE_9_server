package com.tave_app_1.senapool.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // Success
    SUCCESS(200, "S001", "요청에 성공하였습니다."),

    // Common
    // 입력값이 없거나 잘못된 값을 입력했을 때.
    INVALID_INPUT_VALUE(400, "C001", "입력값을 확인해주세요"),
    // 토큰이 없거나 유효하지 않은 상태에서 정보를 요청할 때.
    INVALID_JWT(401, "C003", "토큰이 없거나, 유효하지 않습니다. 로그인을 해주세요."),
    // 특정 정보를 권한이 없는 유저가 요청할 때.
    NO_AUTHORITY(403, "C004", "권한이 없는 유저의 접근입니다."),
    // 존재하지 않는 정보를 요청할 때.
    INVALID_REQUEST(404, "C005", "잘못된 요청입니다.");


    private int code;
    private final String status;
    private final String message;

    ErrorCode(int code, String status, String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}
