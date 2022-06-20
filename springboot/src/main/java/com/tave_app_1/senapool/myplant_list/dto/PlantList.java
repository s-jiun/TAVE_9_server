package com.tave_app_1.senapool.myplant_list.dto;

import com.tave_app_1.senapool.entity.MyPlant;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PlantList {
    private List<Plant> plants;

    PlantList(List<MyPlant> plantList){
        for(MyPlant m : plantList){
            plants.add(new Plant(m.getPlantPK(), m.getPlantName(), m.getPlantImage()));
        }
    }
}
