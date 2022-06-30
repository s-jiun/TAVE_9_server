package com.tave_app_1.senapool.weather.controller.user.dto;

import lombok.Getter;

import javax.persistence.Id;

@Getter
public class UserUpdateDto {
    @Id
    private Long userPk;

    private String userId;

    private String email;
    private String userImage;


}
