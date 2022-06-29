package com.tave_app_1.senapool.myplant_list.dto;

import com.tave_app_1.senapool.entity.MyPlant;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PlantListDto {

    private List<PlantDto> plantDtos;

    public PlantListDto(List<MyPlant> plantList){
        plantDtos = new ArrayList<>(plantList.size());

        for(MyPlant m : plantList){
            plantDtos.add(new PlantDto(m.getPlantPK(), m.getPlantName(), m.getPlantImage()));
        }
    }
}
