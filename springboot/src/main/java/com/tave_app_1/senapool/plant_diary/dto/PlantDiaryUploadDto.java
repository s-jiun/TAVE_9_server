package com.tave_app_1.senapool.plant_diary.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
@Data
public class PlantDiaryUploadDto {
    private String title;
    private String content;
}
