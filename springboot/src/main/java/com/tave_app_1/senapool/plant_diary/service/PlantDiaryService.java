package com.tave_app_1.senapool.plant_diary.service;

import com.tave_app_1.senapool.entity.MyPlant;
import com.tave_app_1.senapool.entity.PlantDiary;
import com.tave_app_1.senapool.entity.PlantDiaryModule;
import com.tave_app_1.senapool.myplant_list.dto.Plant;
import com.tave_app_1.senapool.myplant_list.repository.MyPlantRepository;
import com.tave_app_1.senapool.plant_diary.dto.PlantDiaryDto;
import com.tave_app_1.senapool.plant_diary.dto.PlantDiaryUploadDto;
import com.tave_app_1.senapool.plant_diary.repository.PlantDiaryModuleRepository;
import com.tave_app_1.senapool.plant_diary.repository.PlantDiaryRepository;
import com.tave_app_1.senapool.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PlantDiaryService {

//    private final PlantDiaryRepository plantDiaryRepository;
//    private final MyPlantRepository myPlantRepository;
//    private final PlantDiaryModuleRepository plantDiaryModuleRepository;
//    private final ModelMapper modelMapper;
//    //일기 정보 식물 id로 조회
//    public List<PlantDiary> getDiaryList(Plant plant){
//        List<PlantDiary> plantDiaryList = plantDiaryRepository.findAllByPlant_idOrderByIdAsc(plant.getPlantPK());
//        return plantDiaryList;
//    }

    private final UserRepository userRepository;
    private final PlantDiaryRepository plantDiaryRepository;

//    @Value("${plantdiary.path}")
//    private String picture;

//    @Transactional
//    public void save(PlantDiaryUploadDto plantDiaryUploadDto,long userId, Mu)

}
