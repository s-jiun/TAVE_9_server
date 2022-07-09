package com.tave_app_1.senapool.plant_diary.dto;


import com.tave_app_1.senapool.entity.BaseTime;
import com.tave_app_1.senapool.entity.PlantDiary;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class PlantDiaryInfoDto extends BaseTime {

    private long diaryPK;

    private String title;

    private String content;

    private String diaryImage;

    private Boolean publish;

    private long likesCount;


    public PlantDiaryInfoDto(PlantDiary plantDiary) {
        this.diaryPK = plantDiary.getPlantDiaryPK();
        this.title = plantDiary.getTitle();
        this.content = plantDiary.getContent();
        this.publish = plantDiary.getPublish();
        this.likesCount = plantDiary.getLikesCount();

        if(plantDiary.getDiaryImage().isBlank()) this.diaryImage = "Default.png";
        else this.diaryImage = "http://ec2-3-39-104-218.ap-northeast-2.compute.amazonaws.com:8080/images/diary/" + plantDiary.getDiaryImage();
    }
}
