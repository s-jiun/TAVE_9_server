package com.tave_app_1.senapool.myplant_list.controller;

import com.tave_app_1.senapool.myplant_list.dto.ReqPlantRegisterDto;
import com.tave_app_1.senapool.myplant_list.dto.RespPlantListDto;
import com.tave_app_1.senapool.myplant_list.service.MyPlantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
//@RequestMapping("/myplant-list")
public class MyPlantController {

    private final MyPlantService myPlantService;

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

    /**
     * 내 식물 등록
     * [POST] /myplant-list/{userPK}
     * 작성자 : 장동호
     * 작성일 :
     */
    // 세션정보로 인증필요
    @PostMapping("/myplant-list/{userPK}")
    public String plantRegister(@PathVariable("userPK") int userPK,
                                ReqPlantRegisterDto reqPlantRegisterDto){

        myPlantService.plantSaveToDB(reqPlantRegisterDto);

        //공통 응답 처리 필요
        return "ok";
    }

    @PutMapping("myplant-list/{userPK}/{plantPK}")
    public String plantUpdate(@PathVariable("userPK") int userPK,
                              @PathVariable("plantPK") int plantPK){



        return "ok";
    }


}
