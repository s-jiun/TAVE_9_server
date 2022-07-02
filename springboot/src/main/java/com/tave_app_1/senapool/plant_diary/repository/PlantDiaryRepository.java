package com.tave_app_1.senapool.plant_diary.repository;

import com.tave_app_1.senapool.entity.PlantDiary;
import com.tave_app_1.senapool.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlantDiaryRepository extends JpaRepository<PlantDiary,Long> {
//    List<PlantDiary> findAllByPlant_idOrderByIdAsc(Long id);
//    PlantDiary findAllById(Long id);
//    Optional<PlantDiary> findTopBy_idOrderByCreatedDateDesc(Long id);

    Optional <PlantDiary> deleteByUser(User userPK);
}