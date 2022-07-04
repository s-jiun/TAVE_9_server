package com.tave_app_1.senapool.plant_diary.dto;

import com.tave_app_1.senapool.entity.MyPlant;
import com.tave_app_1.senapool.entity.PlantDiary;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Slf4j
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class PlantDiaryInfoDto {

    private long diaryPK;

    private String title;

    private String content;

    private String diaryImage;

    private Boolean publish;


    public PlantDiaryInfoDto(PlantDiary plantDiary) {
        this.diaryPK = plantDiary.getPlantDiaryPK();
        this.title = plantDiary.getTitle();
        this.content = plantDiary.getContent();
        this.publish = plantDiary.getPublish();

        if(plantDiary.getDiaryImage().isBlank()) this.diaryImage = "Default.png";
        else this.diaryImage = plantDiary.getDiaryImage();
    }



}
