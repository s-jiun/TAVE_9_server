package com.tave_app_1.senapool.plant_diary.service;


import com.tave_app_1.senapool.entity.MyPlant;
import com.tave_app_1.senapool.entity.PlantDiary;
import com.tave_app_1.senapool.entity.User;
import com.tave_app_1.senapool.likes.repository.LikesRepository;
import com.tave_app_1.senapool.myplant_list.dto.plant_list_response.PlantListResponseDto;
import com.tave_app_1.senapool.myplant_list.dto.plant_register_request.PlantRegisterRequestDto;
import com.tave_app_1.senapool.myplant_list.dto.plant_update_request.PlantUpdateRequestDto;
import com.tave_app_1.senapool.myplant_list.repository.MyPlantRepository;
import com.tave_app_1.senapool.plant_diary.dto.PlantDiaryDto;
import com.tave_app_1.senapool.plant_diary.dto.PlantDiaryUpdateDto;
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

@Slf4j
@RequiredArgsConstructor
@Service
public class PlantDiaryService {


    private final UserRepository userRepository;
    private final PlantDiaryRepository plantDiaryRepository;
    private final MyPlantRepository myPlantRepository;

    private final FileUtil fileUtil;


    @Transactional
    public void save(PlantDiaryUploadDto plantDiaryUploadDto,long userPK,long plantPK){

        String plantDiaryImage;
        if (plantDiaryUploadDto.getFile().isEmpty()) plantDiaryImage="";
        else plantDiaryImage = fileUtil.saveDiaryImage(plantDiaryUploadDto.getFile());

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
    public void update(long plantDiaryPK, PlantDiaryUpdateDto plantDiaryUpdateDto){
        PlantDiary plantDiary = plantDiaryRepository.findByPlantDiaryPK(plantDiaryPK);
        String uniqueImageName = fileUtil.imageChange(plantDiaryUpdateDto.getFile(),plantDiary.getDiaryImage());
        plantDiary.update(plantDiaryUpdateDto.getTitle(),plantDiaryUpdateDto.getContent(),uniqueImageName,plantDiaryUpdateDto.getPublish());
    }


    @Transactional
    public void delete(Long plantPK) {
        // 일기 삭제
        plantDiaryRepository.deleteById(plantPK);
    }


    @Transactional
    public void deleteUserDiaryAll(User user){
        plantDiaryRepository.deleteByUser(user);
    }

}
