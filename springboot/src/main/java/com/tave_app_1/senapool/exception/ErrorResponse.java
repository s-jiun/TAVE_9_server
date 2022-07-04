package com.tave_app_1.senapool.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
public class ErrorResponse<T> {

    private int code;
    private String message;
    private String status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;

    public ErrorResponse(T result) {
        this.code = ErrorCode.SUCCESS.getCode();
        this.message = ErrorCode.SUCCESS.getMessage();
        this.status = ErrorCode.SUCCESS.getStatus();
        this.result = result;
    }

    public ErrorResponse(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.status = errorCode.getStatus();
    }
}
