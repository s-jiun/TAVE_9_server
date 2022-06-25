package com.tave_app_1.senapool.myplant_list.dto;

import com.tave_app_1.senapool.entity.MyPlant;
import com.tave_app_1.senapool.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RespPlantListDto {

    private Integer userPK;
    private String userId;
    private String userImage; // 자료형 변환해줘야함

    private PlantList plantList;

    /*
    추후 builder로 변경
     */
    public RespPlantListDto(User user){
        userPK = user.getUserPK();
        userId = user.getUserId();
        userImage = user.getUserImage();

        plantList = new PlantList(user.getMyPlants());
    }
}
