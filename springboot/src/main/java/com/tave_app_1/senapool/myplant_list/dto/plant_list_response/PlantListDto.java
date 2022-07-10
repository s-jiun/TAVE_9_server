package com.tave_app_1.senapool.myplant_list.dto.plant_list_response;

import com.tave_app_1.senapool.entity.MyPlant;
import com.tave_app_1.senapool.util.FileUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PlantListDto {

    private List<PlantDto> plantDtoList;

    public PlantListDto(List<MyPlant> plantList){
        plantDtoList = new ArrayList<>(plantList.size());

        for(MyPlant m : plantList){
            String plantImage;
            if(m.getPlantImage().isBlank()) plantImage = "Default.png";
            else plantImage = FileUtil.plantFolderPath + m.getPlantImage();

            plantDtoList.add(new PlantDto(m.getPlantPK(), m.getPlantName(), plantImage));
        }
    }
}
