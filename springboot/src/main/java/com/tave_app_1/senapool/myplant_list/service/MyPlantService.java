package com.tave_app_1.senapool.myplant_list.service;

import com.tave_app_1.senapool.entity.MyPlant;
import com.tave_app_1.senapool.entity.User;
import com.tave_app_1.senapool.myplant_list.dto.ReqPlantRegisterDto;
import com.tave_app_1.senapool.myplant_list.dto.RespPlantListDto;
import com.tave_app_1.senapool.myplant_list.repository.MyPlantRepository;
import com.tave_app_1.senapool.user.repository.UserRepository;
import com.tave_app_1.senapool.utils.FileProcessor;
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
    private final FileProcessor fileProcessor;

    @Transactional(readOnly = true)
    public RespPlantListDto makeList(int userPK) {
        // userPK로 해당 user 정보 가져오기
        User user = userRepository.findByUserPK(userPK);
        // Entity -> Dto 변환
        RespPlantListDto respPlantListDto = new RespPlantListDto(user);
        return respPlantListDto;
    }

    /*
    세션정보 넘어온 뒤 user 정보 넣어줘야함
     */
    @Transactional
    public void joinPlant(ReqPlantRegisterDto reqPlantRegisterDto){

        // 식물 이미지 저장
        fileProcessor.savePlantImage(reqPlantRegisterDto.getFile());

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
        String uniqueImageName = fileProcessor.getUniqueImageName(reqPlantRegisterDto.getFile());
        MyPlant myPlant = reqPlantRegisterDto.toEntity(uniqueImageName, user);
        myPlantRepository.save(myPlant);
    }

    @Transactional
    public void updatePlant(int userPK, int plantPK) {
    }

    @Transactional
    public void deletePlant(int userPK, int plantPK) {
    }
}
