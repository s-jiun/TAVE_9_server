package com.tave_app_1.senapool.plant_diary.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Builder
@AllArgsConstructor
@Getter
@Data
public class PlantDiaryUploadDto {
    private Long userPK;
    private Long plantPK;
    private String title;
    private String content;
    private MultipartFile diaryImage;
}
