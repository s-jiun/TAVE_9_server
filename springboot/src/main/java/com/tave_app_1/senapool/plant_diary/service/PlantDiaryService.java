package com.tave_app_1.senapool.plant_diary.service;


import com.tave_app_1.senapool.entity.PlantDiary;
import com.tave_app_1.senapool.entity.User;
import com.tave_app_1.senapool.plant_diary.dto.PlantDiaryDto;
import com.tave_app_1.senapool.plant_diary.dto.PlantDiaryUploadDto;
import com.tave_app_1.senapool.plant_diary.repository.PlantDiaryRepository;
import com.tave_app_1.senapool.user.dto.UserDto;
import com.tave_app_1.senapool.user.repository.UserRepository;
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

//    @Value("${post.path}")
//    private String uploadUrl;

//    @Transactional
//    public void save(PlantDiaryUploadDto plantDiaryUploadDto, MultipartFile multipartFile){
//        UUID uuid = UUID.randomUUID();
//        String imgFileName = uuid + "_" + multipartFile.getOriginalFilename();
//
//        Path imageFilePath = Paths.get(uploadUrl + imgFileName);
//        try{
//            Files.write(imageFilePath,multipartFile.getBytes());
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//
//        plantDiaryRepository.save(PlantDiary.builder()
//                .diaryImage(imgFileName)
//                .title(plantDiaryUploadDto.getTitle())
//                .content(plantDiaryUploadDto.getContent())
//                .build());
//    }

//    @Transactional
//    public PlantDiaryDto getPlantDiaryDto(long plantDiartId){
//        PlantDiary plantDiary = plantDiaryRepository.findById(plantDiartId).get();
//
//        PlantDiaryDto plantDiaryDto = PlantDiaryDto.builder()
//                .id(plantDiartId)
//                .title(plantDiary.getTitle())
//                .content(plantDiary.getContent())
//                .picture(plantDiary.getPicture())
//                .publish(plantDiary.getPublish())
//                .build();
//
//        return plantDiaryDto;
//    }

}
