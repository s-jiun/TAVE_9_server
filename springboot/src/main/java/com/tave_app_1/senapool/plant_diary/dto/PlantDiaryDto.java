package com.tave_app_1.senapool.plant_diary.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PlantDiaryDto {

    private Long id;

    private String title;

    private String content;

    private String picture;

    private Boolean publish;

    private LocalDateTime createDate;

    private  LocalDateTime modifiedDate;

}
