package com.tave_app_1.senapool.myplant_list.dto.plant_list_response;

import com.tave_app_1.senapool.entity.User;
import lombok.Data;

@Data
public class PlantListResponseDto {

    private Long userPK;

    private String userId;

    private String userImage; // 자료형 변환해줘야함

    private PlantListDto plantListDto;

    /*
    추후 builder로 변경
     */
    public PlantListResponseDto(User user){
        userPK = user.getUserPK();
        userId = user.getUserId();
        userImage = user.getUserImage();

        plantListDto = new PlantListDto(user.getMyPlantList());
    }
}
