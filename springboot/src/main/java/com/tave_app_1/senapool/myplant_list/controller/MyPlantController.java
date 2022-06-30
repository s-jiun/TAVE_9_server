package com.tave_app_1.senapool.myplant_list.controller;

import com.tave_app_1.senapool.myplant_list.dto.diary_list_response.DiaryListResponseDto;
import com.tave_app_1.senapool.myplant_list.dto.plant_register_request.PlantRegisterRequestDto;
import com.tave_app_1.senapool.myplant_list.dto.plant_list_response.PlantListResponseDto;
import com.tave_app_1.senapool.myplant_list.dto.plant_update_request.PlantUpdateRequestDto;
import com.tave_app_1.senapool.myplant_list.service.MyPlantService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @ApiOperation(value = "선택한 유저의 나의 식물 리스트로 이동", notes = "'임의의 page' -> '나의 식물 리스트'로 이동할 때 유저 및 식물 정보 받아오기")
    @GetMapping("/myplant-list/{userPK}")
    public ResponseEntity<?> plantList(@PathVariable("userPK") Long userPK){
        PlantListResponseDto plantListResponseDto = myPlantService.makePlantList(userPK);
        return new ResponseEntity<PlantListResponseDto>(plantListResponseDto, HttpStatus.OK);
    }

    /**
     * 내 식물 등록
     * [POST] /myplant-list/{userPK}
     * 작성자 : 장동호
     * 작성일 :
     */
    // 세션정보로 인증필요
    @ApiOperation(value = "내 식물 등록", notes = "'나의 식물 리스트'에서 식물 등록")
    @PostMapping("/myplant-list/{userPK}")
    public ResponseEntity<?> plantRegister(@PathVariable("userPK") Long userPK,
                                PlantRegisterRequestDto plantRegisterRequestDto){

        /*
        인증 내용 추가
         */
        myPlantService.joinPlant(plantRegisterRequestDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 내 식물 수정
     * [PUT] /myplant-list/{userPK}/{plantPK}
     * 작성자 : 장동호
     * 작성일 :
     */
    @ApiOperation(value = "내 식물 수정", notes = "'나의 식물일기 리스트'에서 식물 수정")
    @PutMapping("myplant-list/{userPK}/{plantPK}")
    public ResponseEntity<?> plantUpdate(@PathVariable("userPK") Long userPK,
                                         @PathVariable("plantPK") Long plantPK,
                                         PlantUpdateRequestDto plantUpdateRequestDto){

        /*
        인증 내용 추가
         */
        myPlantService.updatePlant(plantPK, plantUpdateRequestDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 내 식물 삭제
     * [DELETE] /myplant-list/{userPK}/{plantPK}
     * 작성자 : 장동호
     * 작성일 :
     */
    @ApiOperation(value = "내 식물 삭제", notes = "'나의 식물일기 리스트'에서 식물 삭제")
    @DeleteMapping("myplant-list/{userPK}/{plantPK}")
    public ResponseEntity<?> plantDelete(@PathVariable("userPK") Long userPK,
                                         @PathVariable("plantPK") Long plantPK){

        /*
        인증 내용 추가
         */
        myPlantService.deletePlant(plantPK);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 선택한 식물의 식물일기 리스트로 이동
     * [GET] /myplant-list/{userPK}/{plantPK}
     * 작성자 : 장동호
     * 작성일 :
     */
    @ApiOperation(value = "선택한 식물의 식물일기 리스트로 이동", notes = "'나의 식물 리스트' -> '나의 식물일기 리스트'로 이동할 때 유저 및 식물 정보 받아오기")
    @GetMapping("myplant-list/{userPK}/{plantPK}")
    public ResponseEntity<?> diaryList(@PathVariable("userPK") Long userPK,
                              @PathVariable("plantPK") Long plantPK){

        DiaryListResponseDto diaryListResponseDto = myPlantService.makeDiaryList(plantPK, true);

        return new ResponseEntity<DiaryListResponseDto>(diaryListResponseDto, HttpStatus.OK);
    }
}
