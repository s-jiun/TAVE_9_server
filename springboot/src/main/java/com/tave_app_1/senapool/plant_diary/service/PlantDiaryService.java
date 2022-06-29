package com.tave_app_1.senapool.plant_diary.service;

import com.tave_app_1.senapool.myplant_list.repository.MyPlantRepository;
import com.tave_app_1.senapool.plant_diary.repository.PlantDiaryModuleRepository;
import com.tave_app_1.senapool.plant_diary.repository.PlantDiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PlantDiaryService {

    private final PlantDiaryRepository plantDiaryRepository;
    private final MyPlantRepository myPlantRepository;
    private final PlantDiaryModuleRepository plantDiaryModuleRepository;

//    //일기 정보 식물 id로 조회
//    public List<PlantDiary> getDiaryList(Plant plant){
//        List<PlantDiary> plantDiaryList = plantDiaryRepository.findAllByPlant_idOrderByIdAsc(plant.getPlantPK());
//        return plantDiaryList;
//    }
//    //일기 정보 insert
//    public String insertDiary(){
//        try{
//            PlantDiary plantDiary = new PlantDiary();
//            plantDiary.setNum();
//        }
//    }

}
