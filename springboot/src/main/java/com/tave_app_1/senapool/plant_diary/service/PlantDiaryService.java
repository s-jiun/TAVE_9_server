package com.tave_app_1.senapool.plant_diary.service;

import com.tave_app_1.senapool.entity.MyPlant;
import com.tave_app_1.senapool.entity.PlantDiary;
import com.tave_app_1.senapool.entity.PlantDiaryModule;
import com.tave_app_1.senapool.myplant_list.dto.Plant;
import com.tave_app_1.senapool.myplant_list.repository.MyPlantRepository;
import com.tave_app_1.senapool.plant_diary.dto.PlantDiaryDto;
import com.tave_app_1.senapool.plant_diary.repository.PlantDiaryModuleRepository;
import com.tave_app_1.senapool.plant_diary.repository.PlantDiaryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PlantDiaryService {

    private final PlantDiaryRepository plantDiaryRepository;
    private final MyPlantRepository myPlantRepository;
    private final PlantDiaryModuleRepository plantDiaryModuleRepository;
    private final ModelMapper modelMapper;
    //일기 정보 식물 id로 조회
    public List<PlantDiary> getDiaryList(Plant plant){
        List<PlantDiary> plantDiaryList = plantDiaryRepository.findAllByPlant_idOrderByIdAsc(plant.getPlantPK());
        return plantDiaryList;
    }
//
//    //일기 권한 여부 체크 (미완성 : 자신 일기가 맞으면 권한 부여)
//    public boolean checkAccount(PlantDiary plantDiary){
//        PlantDiary myplant = plantDiaryRepository.findAllById(plantDiary.getId());
//
//
//        return true;
//    }
//
//    //일기 정보 insert
//    public String insertDiary(MyPlant myPlant, PlantDiaryModule plantDiaryModule){
//        try{
//            PlantDiary plantDiary = new PlantDiary();
//            plantDiary.addPlant(myPlant);
//            plantDiary.setTitle(plantDiaryModule.getTitle());
//            plantDiary.setContent(plantDiaryModule.getContent());
//            myPlant.add
//        }
//    }
//
//    public void updateDiary(PlantDiary plantDiary, PlantDiaryDto plantDiaryDto){
//        try{
//
//        }
//    }

}
