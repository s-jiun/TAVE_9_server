package com.tave_app_1.senapool.plant_diary.repository;

import com.tave_app_1.senapool.entity.PlantDiaryModule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantDiaryModuleRepository extends JpaRepository<PlantDiaryModule,Long> {
    PlantDiaryModule findAllById(Long id);
}
