package com.tave_app_1.senapool.myplant_list.dto.plant_register_request;

import com.tave_app_1.senapool.entity.MyPlant;
import com.tave_app_1.senapool.entity.User;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Data
public class PlantRegisterRequestDto {

    @NotBlank
    private MultipartFile file;

    @NotBlank
    private String plantName;

    @NotBlank
    private String plantType;

    @NotBlank
    private Integer waterPeriod;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastWater;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDay;

    /*
     추가 정보 넣어야함
     */
    public MyPlant toEntity(String plantImageName, User user){
        return new MyPlant(user, plantName, plantType, waterPeriod, lastWater, startDay, plantImageName);
    }

}
