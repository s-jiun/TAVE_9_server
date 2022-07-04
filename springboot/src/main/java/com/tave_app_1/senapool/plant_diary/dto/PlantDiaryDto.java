package com.tave_app_1.senapool.plant_diary.dto;


import com.tave_app_1.senapool.entity.PlantDiary;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;


@Slf4j
@Data
public class PlantDiaryDto {

    private long plantDiaryPK;

    private String title;

    private String content;

    private String diaryImage;

    private Boolean publish;

    //하나만 필요한가?
    private LocalDateTime createDate;
    private  LocalDateTime modifiedDate;

    public PlantDiaryDto(PlantDiary plantDiary) {
        this.plantDiaryPK = plantDiary.getPlantDiaryPK();
        this.title = plantDiary.getTitle();
        this.content = plantDiary.getContent();
        this.publish = plantDiary.getPublish();

        if(plantDiary.getDiaryImage().isBlank()) this.diaryImage = "Default.png";
        else this.diaryImage = plantDiary.getDiaryImage();
    }

}
