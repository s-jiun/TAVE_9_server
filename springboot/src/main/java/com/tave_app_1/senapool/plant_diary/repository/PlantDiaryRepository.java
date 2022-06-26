package com.tave_app_1.senapool.plant_diary.repository;

import com.tave_app_1.senapool.entity.PlantDiary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantDiaryRepository extends JpaRepository<PlantDiary,Long> {
    PlantDiary findAllById(Long id);
}