package com.tave_app_1.senapool.myplant_list.dto.plant_list_response;

import com.tave_app_1.senapool.entity.MyPlant;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PlantListDto {

    private List<PlantDto> plantDtoList;

    public PlantListDto(List<MyPlant> plantList){
        plantDtoList = new ArrayList<>(plantList.size());

        for(MyPlant m : plantList){
            plantDtoList.add(new PlantDto(m.getPlantPK(), m.getPlantName(), m.getPlantImage()));
        }
    }
}
