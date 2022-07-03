package com.tave_app_1.senapool.plant_diary.repository;

import com.tave_app_1.senapool.entity.MyPlant;
import com.tave_app_1.senapool.entity.PlantDiary;
import com.tave_app_1.senapool.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PlantDiaryRepository extends JpaRepository<PlantDiary,Long> {

    PlantDiary findByPlantDiaryPK(Long plantDiaryPK);
    Optional <PlantDiary> deleteByUser(User userPK);
}