package com.tave_app_1.senapool.plant_diary.controller;

import com.tave_app_1.senapool.entity.MyPlant;
import com.tave_app_1.senapool.entity.User;
import com.tave_app_1.senapool.exception.ErrorCode;
import com.tave_app_1.senapool.exception.ErrorResponse;
import com.tave_app_1.senapool.myplant_list.dto.diary_list_response.DiaryListResponseDto;
import com.tave_app_1.senapool.myplant_list.repository.MyPlantRepository;
import com.tave_app_1.senapool.plant_diary.dto.PlantDiaryDetailDto;
import com.tave_app_1.senapool.plant_diary.dto.PlantDiaryUpdateDto;
import com.tave_app_1.senapool.plant_diary.dto.PlantDiaryUploadDto;
import com.tave_app_1.senapool.plant_diary.service.PlantDiaryService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.beanvalidation.CustomValidatorBean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PlantDiaryController {

    private final PlantDiaryService plantDiaryService;
    private final MyPlantRepository myPlantRepository;



    //일기 등록
    @ApiOperation(value = "식물 일기 등록")
    @PostMapping("/myplant-diary/{userPK}/{plantPK}")
    public ErrorResponse<?> plantDiaryUpload(@PathVariable("userPK") Long userPK, @PathVariable("plantPK") Long plantPK,
                                             @Valid PlantDiaryUploadDto plantDiaryUploadDto,
                                             @ApiIgnore Authentication authentication){

        try {
            User user = (User) authentication.getPrincipal();
            // 인증 성공
            if (user.getUserPK() == userPK) {
                MyPlant myPlant = myPlantRepository.findByPlantPK(plantPK);
                plantDiaryService.uploadDiary(plantDiaryUploadDto, user, myPlant);
                return new ErrorResponse<>(ErrorCode.SUCCESS);
            }
            // 인증 실패
            else {
                return new ErrorResponse<>(ErrorCode.INVALID_REQUEST);
            }
        } catch (Exception e) {
            return new ErrorResponse<>(ErrorCode.INVALID_JWT);
        }
    }

    //일기 수정
    @ApiOperation(value = "식물 일기 수정")
    @PutMapping("/myplant-diary/{userPK}/{diaryPK}")
    public ErrorResponse<?> plantDiaryUpdate(@PathVariable("userPK") Long userPK,@PathVariable("diaryPK") Long diaryPK,
                                             @Valid PlantDiaryUpdateDto plantDiaryUpdateDto,
                                         @ApiIgnore Authentication authentication){

        try {

            User user = (User) authentication.getPrincipal();

            // 인증 성공
            if (user.getUserPK() == userPK) {
                plantDiaryService.updateDiary(diaryPK, plantDiaryUpdateDto);
                return new ErrorResponse<>(ErrorCode.SUCCESS);
            }
            // 인증 실패
            else {
                return new ErrorResponse<>(ErrorCode.INVALID_REQUEST);
            }
        } catch (Exception e) {
            return new ErrorResponse<>(ErrorCode.INVALID_JWT);
        }

    }


    // 식물 일기 삭제
    @ApiOperation(value = "식물 일기 삭제")
    @DeleteMapping("/myplant-diary/{userPK}/{diaryPK}")
    public ErrorResponse<?> plantDiaryDelete(@PathVariable("userPK") Long userPK,@PathVariable("diaryPK") Long diaryPK,
                                         @ApiIgnore Authentication authentication){

        try {
            User user = (User) authentication.getPrincipal();
            // 인증 성공
            if (user.getUserPK() == userPK) {
                plantDiaryService.delete(diaryPK);
                return new ErrorResponse<>(ErrorCode.SUCCESS);
            }
            // 인증 실패
            else {
                return new ErrorResponse<>(ErrorCode.INVALID_REQUEST);
            }
        }  catch (Exception e) {
            return new ErrorResponse<>(ErrorCode.INVALID_JWT);
        }
    }


    @ApiOperation(value = "식물일기 자세히 보기")
    @GetMapping("/myplant-diary/{diaryPK}")
    public ErrorResponse<PlantDiaryDetailDto> diaryDetail(@PathVariable("diaryPK") Long diaryPK){

        PlantDiaryDetailDto plantDiaryDetailDto = plantDiaryService.makeDiaryDetail(diaryPK);

        return new ErrorResponse<>(plantDiaryDetailDto);
    }


}
