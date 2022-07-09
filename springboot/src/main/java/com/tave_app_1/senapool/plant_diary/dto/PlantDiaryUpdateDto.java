package com.tave_app_1.senapool.plant_diary.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PlantDiaryUpdateDto {

    private MultipartFile file;

    private String title;

    private String content;

    private String diaryImage;

    private Boolean publish;

    private LocalDate createDate;

}
