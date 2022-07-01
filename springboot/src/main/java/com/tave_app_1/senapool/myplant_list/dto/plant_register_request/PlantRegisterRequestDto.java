package com.tave_app_1.senapool.myplant_list.dto.plant_register_request;

import com.tave_app_1.senapool.entity.MyPlant;
import com.tave_app_1.senapool.entity.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
public class PlantRegisterRequestDto {

    private MultipartFile file;

    private String plantName;

    private String plantType;

    private Integer waterPeriod;

    //private Date startDay;

    /*
     추가 정보 넣어야함
     */
    public MyPlant toEntity(String plantImageName, User user){
        return new MyPlant(user, plantName, plantType, waterPeriod, plantImageName);
    }

}
