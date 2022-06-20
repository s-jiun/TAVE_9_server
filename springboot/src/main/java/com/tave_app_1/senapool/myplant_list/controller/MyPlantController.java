package com.tave_app_1.senapool.myplant_list.controller;

import com.tave_app_1.senapool.myplant_list.dto.RespPlantListDto;
import com.tave_app_1.senapool.myplant_list.service.MyPlantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MyPlantController {

    private MyPlantService myPlantService;

    /**
     * 유저 식물 리스트
     * [GET] /myplant-list/{userPK}
     * 작성자 : 장동호
     * 작성일 : 2022-06-20
     */
    @GetMapping("/myplant-list/{userPK}")
    public RespPlantListDto plantList(@PathVariable("userPK") int userPK){
        RespPlantListDto respPlantListDto = myPlantService.makeList(userPK);
        return respPlantListDto;
    }

}
