package com.tave_app_1.senapool.plant_diary.controller;

import com.tave_app_1.senapool.entity.User;
import com.tave_app_1.senapool.myplant_list.dto.plant_register_request.PlantRegisterRequestDto;
import com.tave_app_1.senapool.myplant_list.dto.plant_update_request.PlantUpdateRequestDto;
import com.tave_app_1.senapool.plant_diary.dto.PlantDiaryInfoDto;
import com.tave_app_1.senapool.plant_diary.dto.PlantDiaryUpdateDto;
import com.tave_app_1.senapool.plant_diary.dto.PlantDiaryUploadDto;
import com.tave_app_1.senapool.plant_diary.handler.ex.CustomValidationException;
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
@RequiredArgsConstructor
@Controller
public class PlantDiaryController {

    private final PlantDiaryService plantDiaryService;


    //일기 등록
    @ApiOperation(value = "식물 일기 등록", response = ResponseEntity.class)
    @PostMapping("/myplant-diary/{userPK}/{plantPK}")
    public ResponseEntity<?> plantDiaryUpload(@PathVariable("userPK") Long userPK, @PathVariable("plantPK") Long plantPK,
                                           @Valid PlantDiaryUploadDto plantDiaryUploadDto,
                                           @ApiIgnore Authentication authentication){

        com.tave_app_1.senapool.entity.User user = (com.tave_app_1.senapool.entity.User) authentication.getPrincipal();

        // 인증 성공
        if (user.getUserPK() == userPK) {
            plantDiaryService.save(plantDiaryUploadDto, userPK,plantPK);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        // 인증 실패
        else{
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    //일기 수정
    @ApiOperation(value = "식물 일기 수정", response = ResponseEntity.class)
    @PutMapping("/myplant-diary/{userPK}/{diaryPK}")
    public ResponseEntity<?> plantDiaryUpdate(@PathVariable("userPK") Long userPK,@PathVariable("diaryPK") Long diaryPK,
                                              PlantDiaryUpdateDto plantDiaryUpdateDto,
                                         @ApiIgnore Authentication authentication){
        User user = (User) authentication.getPrincipal();

        // 인증 성공
        if (user.getUserPK() == userPK) {
            plantDiaryService.update(diaryPK,plantDiaryUpdateDto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        // 인증 실패
        else{
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }


    // 식물 일기 삭제
    @ApiOperation(value = "식물 일기 삭제", response = ResponseEntity.class)
    @DeleteMapping("/myplant-diary/{userPK}/{diaryPK}")
    public ResponseEntity<?> plantDiaryDelete(@PathVariable("userPK") Long userPK,@PathVariable("diaryPK") Long diaryPK,
                                         @ApiIgnore Authentication authentication){

        com.tave_app_1.senapool.entity.User user = (User) authentication.getPrincipal();

        // 인증 성공
        if (user.getUserPK() == userPK) {
            plantDiaryService.delete(diaryPK);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        // 인증 실패
        else{
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

}
