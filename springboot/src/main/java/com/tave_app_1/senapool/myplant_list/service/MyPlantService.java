package com.tave_app_1.senapool.myplant_list.service;

import com.tave_app_1.senapool.entity.MyPlant;
import com.tave_app_1.senapool.entity.User;
import com.tave_app_1.senapool.myplant_list.dto.RespPlantListDto;
import com.tave_app_1.senapool.myplant_list.repository.MyPlantRepository;
import com.tave_app_1.senapool.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPlantService {

    private MyPlantRepository myPlantRepository;
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public RespPlantListDto makeList(int userPK) {
        User user = userRepository.findByUserPK(userPK);
        List<MyPlant> plantList = myPlantRepository.findPlantList(userPK);

        // Entity -> Dto 변환
        RespPlantListDto respPlantListDto = new RespPlantListDto(user, plantList);
        return respPlantListDto;
    }
}
