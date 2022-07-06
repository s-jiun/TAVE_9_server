package com.tave_app_1.senapool.plant_diary.dto;


import com.tave_app_1.senapool.entity.PlantDiary;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.PrePersist;
import java.time.LocalDateTime;


@Slf4j
@Data
public class PlantDiaryDto {

    private long plantDiaryPK;

    private String title;

    private String content;

    private String diaryImage;

    private Boolean publish;

    private LocalDateTime createDate;


    public PlantDiaryDto(PlantDiary plantDiary) {
        this.plantDiaryPK = plantDiary.getPlantDiaryPK();
        this.title = plantDiary.getTitle();
        this.content = plantDiary.getContent();
        this.publish = plantDiary.getPublish();
        this.createDate = plantDiary.getCreateDate();

        if(plantDiary.getDiaryImage().isBlank()) this.diaryImage = "Default.png";
        else this.diaryImage = plantDiary.getDiaryImage();
    }

}
