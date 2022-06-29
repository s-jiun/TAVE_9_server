package com.tave_app_1.senapool.myplant_list.service;

import com.tave_app_1.senapool.entity.MyPlant;
import com.tave_app_1.senapool.entity.User;
import com.tave_app_1.senapool.myplant_list.dto.PlantRegisterRequestDto;
import com.tave_app_1.senapool.myplant_list.dto.PlantListResponseDto;
import com.tave_app_1.senapool.myplant_list.repository.MyPlantRepository;
import com.tave_app_1.senapool.user.repository.UserRepository;
import com.tave_app_1.senapool.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyPlantService {

    private final MyPlantRepository myPlantRepository;
    private final UserRepository userRepository;
    private final FileUtil fileUtil;

    @Transactional(readOnly = true)
    public PlantListResponseDto makeList(int userPK) {
        // userPK로 해당 user 정보 가져오기
        User user = userRepository.findByUserPK(userPK);
        // Entity -> Dto 변환
        PlantListResponseDto plantListResponseDto = new PlantListResponseDto(user);
        return plantListResponseDto;
    }

    /*
    세션정보 넘어온 뒤 user 정보 넣어줘야함
     */
    @Transactional
    public void joinPlant(PlantRegisterRequestDto plantRegisterRequestDto){

        // 식물 이미지 저장
        fileUtil.savePlantImage(plantRegisterRequestDto.getFile());

        /*
        dummy user
         */
        User user = User.builder()
                .userId("test")
                .password("1234")
                .email("test@naver.com")
                .userImage("test")
                .build();

        /*
            추후 빌더 형태로 변환
         */
        String uniqueImageName = fileUtil.getUniqueImageName(plantRegisterRequestDto.getFile());
        MyPlant myPlant = plantRegisterRequestDto.toEntity(uniqueImageName, user);
        myPlantRepository.save(myPlant);
    }

    @Transactional
    public void updatePlant(int userPK, int plantPK) {
    }

    @Transactional
    public void deletePlant(int userPK, int plantPK) {
    }
}
