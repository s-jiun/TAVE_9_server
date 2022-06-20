package com.tave_app_1.senapool.myplant_list.repository;

import com.tave_app_1.senapool.entity.MyPlant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MyPlantRepository extends JpaRepository<MyPlant, Integer> {

    @Query(value = "SELECT * FROM myplant WHERE user_pk = :userPK", nativeQuery = true)
    List<MyPlant> findPlantList(int userPK);

}
