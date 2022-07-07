package com.tave_app_1.senapool.user.dto;

import com.tave_app_1.senapool.entity.User;
import lombok.Data;

@Data
public class ResponseLoginUserDto {

    private TokenDto tokenDto;
    private User user;

    public ResponseLoginUserDto(TokenDto tokenDto, User user) {
        this.tokenDto = tokenDto;
        this.user = user;
    }
}
