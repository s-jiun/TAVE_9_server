package com.tave_app_1.senapool.plant_diary.service;


import com.tave_app_1.senapool.entity.MyPlant;
import com.tave_app_1.senapool.entity.PlantDiary;
import com.tave_app_1.senapool.entity.User;
import com.tave_app_1.senapool.myplant_list.dto.plant_list_response.PlantListResponseDto;
import com.tave_app_1.senapool.myplant_list.repository.MyPlantRepository;
import com.tave_app_1.senapool.plant_diary.dto.PlantDiaryDto;
import com.tave_app_1.senapool.plant_diary.dto.PlantDiaryUploadDto;
import com.tave_app_1.senapool.plant_diary.repository.PlantDiaryRepository;
import com.tave_app_1.senapool.user.repository.UserRepository;
import com.tave_app_1.senapool.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class PlantDiaryService {


    private final UserRepository userRepository;
    private final PlantDiaryRepository plantDiaryRepository;
    private final MyPlantRepository myPlantRepository;

    private final FileUtil fileUtil;


    @Transactional
    public void save(PlantDiaryUploadDto plantDiaryUploadDto,long userPK,long plantPK, MultipartFile multipartFile){

        String plantDiaryImage;
        if (plantDiaryUploadDto.getDiaryImage().isEmpty()) plantDiaryImage="";
        else plantDiaryImage = fileUtil.saveDiaryImage(plantDiaryUploadDto.getDiaryImage());

        User user = userRepository.findByUserPK(userPK);
        MyPlant myPlant = myPlantRepository.findByPlantPK(plantPK);
        plantDiaryRepository.save(PlantDiary.builder()
                .diaryImage(plantDiaryImage)
                .title(plantDiaryUploadDto.getTitle())
                .content(plantDiaryUploadDto.getContent())
                .user(user).myPlant(myPlant)
                .build());
    }

    @Transactional
    public PlantDiaryDto getPlantDiaryDto(long plantDiaryId){
        PlantDiary plantDiary = plantDiaryRepository.findById(plantDiaryId).get();

        PlantDiaryDto plantDiaryDto = PlantDiaryDto.builder()
                .id(plantDiaryId)
                .title(plantDiary.getTitle())
                .content(plantDiary.getContent())
                .diaryImage(plantDiary.getDiaryImage())
                .publish(plantDiary.getPublish())
                .build();

        return plantDiaryDto;
    }

    @Transactional
    public void deleteUserDiaryAll(User user){
        plantDiaryRepository.deleteByUser(user);
    }

}
