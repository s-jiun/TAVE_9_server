package com.tave_app_1.senapool.myplant_list.controller;

import com.tave_app_1.senapool.myplant_list.dto.ReqPlantRegisterDto;
import com.tave_app_1.senapool.myplant_list.dto.RespPlantListDto;
import com.tave_app_1.senapool.myplant_list.service.MyPlantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.net.MalformedURLException;

// http://localhost:8080/swagger-ui/index.html#/

@Slf4j
@RestController
@RequiredArgsConstructor
public class MyPlantController {

    private final MyPlantService myPlantService;

    /**
     * 유저 식물 리스트
     * [GET] /myplant-list/{userPK}
     * 작성자 : 장동호
     * 작성일 : 2022-06-20
     */
    @ApiOperation(value = "유저 식물 리스트", notes = "main page로 이동할 때 유저 및 식물 정보 반환")
    @GetMapping("/myplant-list/{userPK}")
    public ResponseEntity<?> plantList(@PathVariable("userPK") int userPK){
        RespPlantListDto respPlantListDto = myPlantService.makeList(userPK);
        return new ResponseEntity<RespPlantListDto>(respPlantListDto, HttpStatus.OK);
    }

    /**
     * 내 식물 등록
     * [POST] /myplant-list/{userPK}
     * 작성자 : 장동호
     * 작성일 :
     */
    // 세션정보로 인증필요
    @PostMapping("/myplant-list/{userPK}")
    public ResponseEntity<?> plantRegister(@PathVariable("userPK") int userPK,
                                ReqPlantRegisterDto reqPlantRegisterDto){

        /*
        인증 내용 추가
         */
        myPlantService.joinPlant(reqPlantRegisterDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 내 식물 수정
     * [PUT] /myplant-list/{userPK}/{plantPK}
     * 작성자 : 장동호
     * 작성일 :
     */
    @PutMapping("myplant-list/{userPK}/{plantPK}")
    public ResponseEntity<?> plantUpdate(@PathVariable("userPK") int userPK,
                              @PathVariable("plantPK") int plantPK){

        /*
        인증 내용 추가
         */
        myPlantService.updatePlant(userPK, plantPK);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 내 식물 삭제
     * [DELETE] /myplant-list/{userPK}/{plantPK}
     * 작성자 : 장동호
     * 작성일 :
     */
    @DeleteMapping("myplant-list/{userPK}/{plantPK}")
    public ResponseEntity<?> plantDelete(@PathVariable("userPK") int userPK,
                              @PathVariable("plantPK") int plantPK){

        /*
        인증 내용 추가
         */
        myPlantService.deletePlant(userPK, plantPK);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 선택한 식물의 식물일기 리스트
     * [GET] /myplant-list/{userPK}/{plantPK}
     * 작성자 : 장동호
     * 작성일 :
     */
    @GetMapping("myplant-list/{userPK}/{plantPK}")
    public String diaryList(@PathVariable("userPK") int userPK,
                              @PathVariable("plantPK") int plantPK){



        return "ok";
    }
}
