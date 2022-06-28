package com.tave_app_1.senapool.myplant_list.dto;

import com.tave_app_1.senapool.entity.MyPlant;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Setter
@Getter
public class PlantList {
    private List<Plant> plants;

    public PlantList(List<MyPlant> plantList){
        plants = new ArrayList<>(plantList.size());

        for(MyPlant m : plantList){
            plants.add(new Plant(m.getPlantPK(), m.getPlantName(), m.getPlantImage()));
        }
    }
}
