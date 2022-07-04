package com.tave_app_1.senapool.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(BindException.class)
    public ErrorResponse<?> processValidationError(BindException exception) {
        log.error("BindingException 발생", exception);
        return new ErrorResponse<>(ErrorCode.INVALID_INPUT_VALUE);
    }

    @ExceptionHandler(CustomException.class)
    public ErrorResponse<?> processNotFoundError(CustomException exception) {
        log.error(exception.getMessage(), exception);
        return new ErrorResponse<>(ErrorCode.INVALID_REQUEST);
    }
}
