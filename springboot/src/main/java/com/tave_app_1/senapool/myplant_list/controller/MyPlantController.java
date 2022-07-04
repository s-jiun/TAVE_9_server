package com.tave_app_1.senapool.myplant_list.controller;

import com.tave_app_1.senapool.entity.User;
import com.tave_app_1.senapool.exception.ErrorCode;
import com.tave_app_1.senapool.exception.ErrorResponse;
import com.tave_app_1.senapool.myplant_list.dto.diary_list_response.DiaryListResponseDto;
import com.tave_app_1.senapool.myplant_list.dto.plant_register_request.PlantRegisterRequestDto;
import com.tave_app_1.senapool.myplant_list.dto.plant_list_response.PlantListResponseDto;
import com.tave_app_1.senapool.myplant_list.dto.plant_update_request.PlantUpdateRequestDto;
import com.tave_app_1.senapool.myplant_list.service.MyPlantService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

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
     * 수정일 : 2022-07-01
     */
    @ApiOperation(value = "선택한 유저의 '나의 식물 리스트'로 이동", notes = "'임의의 page' -> '나의 식물 리스트'로 이동할 때 유저 및 식물 정보 받아오기")
    @ApiResponse(code = 200, message = "요청에 성공하였습니다.")
    @GetMapping(value = "/myplant-list/{userPK}")
    public ErrorResponse<?> plantList(@PathVariable("userPK") Long userPK){
        PlantListResponseDto plantListResponseDto = myPlantService.makePlantList(userPK);
        return new ErrorResponse<>(plantListResponseDto);
    }

    /**
     * 내 식물 등록
     * [POST] /myplant-list/{userPK}
     * 작성자 : 장동호
     * 수정일 : 2022-07-01
     */
    @ApiOperation(value = "내 식물 등록", notes = "'나의 식물 리스트'에서 식물 등록(multipart/form-data)", response = ResponseEntity.class)
    @PostMapping("/myplant-list/{userPK}")
    public ErrorResponse<?> plantRegister(@PathVariable("userPK") Long userPK,
                                           @Valid PlantRegisterRequestDto plantRegisterRequestDto,
                                           @ApiIgnore Authentication authentication) {

        /*
        Controller 에서 Entity 다루고 있음.
         */
        try {
            User user = (User) authentication.getPrincipal();

            // 인증 성공
            if (user.getUserPK() == userPK) {
                myPlantService.joinPlant(plantRegisterRequestDto, user);
                return new ErrorResponse<>(ErrorCode.SUCCESS);
            }
            // 인증 실패
            else {
                return new ErrorResponse<>(ErrorCode.NO_AUTHORITY);
            }
        } catch (Exception e) {
            return new ErrorResponse<>(ErrorCode.INVALID_JWT);
        }
    }

    /**
     * 내 식물 수정
     * [PUT] /myplant-list/{userPK}/{plantPK}
     * 작성자 : 장동호
     * 수정일 : 2022-07-01
     */
    @ApiOperation(value = "내 식물 수정", notes = "'나의 식물일기 리스트'에서 식물 수정(multipart/form-data)", response = ResponseEntity.class)
    @PutMapping("myplant-list/{userPK}/{plantPK}")
    public ErrorResponse<?> plantUpdate(@PathVariable("userPK") Long userPK,
                                         @PathVariable("plantPK") Long plantPK,
                                         @Valid PlantUpdateRequestDto plantUpdateRequestDto,
                                         @ApiIgnore Authentication authentication){


        try {
            User user = (User) authentication.getPrincipal();

            // 인증 성공
            if (user.getUserPK() == userPK) {
                myPlantService.updatePlant(plantPK, plantUpdateRequestDto);
                return new ErrorResponse<>(ErrorCode.SUCCESS);
            }
            // 인증 실패
            else {
                return new ErrorResponse<>(ErrorCode.NO_AUTHORITY);
            }
        }catch (Exception e) {
            return new ErrorResponse<>(ErrorCode.INVALID_JWT);
        }
    }

    /**
     * 내 식물 삭제
     * [DELETE] /myplant-list/{userPK}/{plantPK}
     * 작성자 : 장동호
     * 수정일 : 2022-07-01
     */
    @ApiOperation(value = "내 식물 삭제", notes = "'나의 식물일기 리스트'에서 식물 삭제", response = ResponseEntity.class)
    @DeleteMapping("myplant-list/{userPK}/{plantPK}")
    public ErrorResponse<?> plantDelete(@PathVariable("userPK") Long userPK,
                                         @PathVariable("plantPK") Long plantPK,
                                         @ApiIgnore Authentication authentication){

        try {
            User user = (User) authentication.getPrincipal();

            // 인증 성공
            if (user.getUserPK() == userPK) {
                myPlantService.deletePlant(plantPK);
                return new ErrorResponse<>(ErrorCode.SUCCESS);
            }
            // 인증 실패
            else {
                return new ErrorResponse<>(ErrorCode.NO_AUTHORITY);
            }
        }catch (Exception e) {
            return new ErrorResponse<>(ErrorCode.INVALID_JWT);
        }
    }

    /**
     * 선택한 식물의 식물일기 리스트로 이동
     * [GET] /myplant-list/{userPK}/{plantPK}
     * 작성자 : 장동호
     * 수정일 : 2022-07-01
     */
    @ApiOperation(value = "선택한 식물의 '식물일기 리스트'로 이동", notes = "'나의 식물 리스트' -> '나의 식물일기 리스트'로 이동할 때 유저 및 식물 정보 (multipart/form-data)", response = DiaryListResponseDto.class)
    @GetMapping("myplant-list/{userPK}/{plantPK}")
    public ErrorResponse<?> diaryList(@PathVariable("userPK") Long userPK,
                                       @PathVariable("plantPK") Long plantPK,
                                      @ApiIgnore Authentication authentication){

        try {
            User user = (User) authentication.getPrincipal();

            DiaryListResponseDto diaryListResponseDto;
            // 자신의 식물일기 리스트로 이동
            if(user.getUserPK() == userPK) diaryListResponseDto = myPlantService.makeDiaryList(plantPK, true);
            // 다른 사람의 식물일기 리스트로 이동
            else diaryListResponseDto = myPlantService.makeDiaryList(plantPK, false);

            return new ErrorResponse<>(diaryListResponseDto);
        } catch (Exception e) {
            return new ErrorResponse<>(ErrorCode.INVALID_JWT);
        }
    }
}