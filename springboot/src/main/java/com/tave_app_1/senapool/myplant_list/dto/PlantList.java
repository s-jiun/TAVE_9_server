package com.tave_app_1.senapool.myplant_list.dto;

import com.tave_app_1.senapool.entity.MyPlant;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Setter
@Getter
public class PlantList {
    private List<Plant> plants;

    //@Value("${file.path}")
    private String uploadFolder;

    public PlantList(List<MyPlant> plantList){
        plants = new ArrayList<>(plantList.size());


        for(MyPlant m : plantList){

            try {
                // uploadFolder 사용하는법
                String imagePath = "C:/Users/vndtj/Desktop/senapool/upload/" + m.getPlantImage();
                // plantImage 바이트 변환
                Path imageFilePath = Paths.get(imagePath);

                byte[] imageByteArray = Files.readAllBytes(imageFilePath);

                plants.add(new Plant(m.getPlantPK(), m.getPlantName(), imageByteArray));
            }
            catch (IOException e) {
                throw new RuntimeException("Could not read file: " + m.getPlantImage(), e);
            }

        }
    }
}
