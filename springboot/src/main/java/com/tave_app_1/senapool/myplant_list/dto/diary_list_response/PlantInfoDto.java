package com.tave_app_1.senapool.myplant_list.dto.diary_list_response;

import com.tave_app_1.senapool.entity.MyPlant;
import lombok.Data;

@Data
public class PlantInfoDto {

    private Long plantPK;

    private String plantImage;

    private String plantName;

    private String plantType;

    private Integer waterPeriod;

    //private String startDay;

    /*
    추후 빌더로 변환
     */
    public PlantInfoDto(MyPlant myPlant) {
        this.plantPK = myPlant.getPlantPK();
        this.plantImage = myPlant.getPlantImage();
        this.plantName = myPlant.getPlantName();
        this.plantType = myPlant.getPlantType();
        this.waterPeriod = myPlant.getWaterPeriod();
    }
}
