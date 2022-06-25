package com.tave_app_1.senapool.myplant_list.service;

import com.tave_app_1.senapool.entity.MyPlant;
import com.tave_app_1.senapool.entity.User;
import com.tave_app_1.senapool.myplant_list.dto.ReqPlantRegisterDto;
import com.tave_app_1.senapool.myplant_list.dto.RespPlantListDto;
import com.tave_app_1.senapool.myplant_list.repository.MyPlantRepository;
import com.tave_app_1.senapool.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyPlantService {

    private final MyPlantRepository myPlantRepository;
    private final UserRepository userRepository;

    @Value("${file.path}")
    private String uploadFolder;

    @Transactional(readOnly = true)
    public RespPlantListDto makeList(int userPK) {
        User user = userRepository.findByUserPK(userPK);

        // Entity -> Dto 변환
        RespPlantListDto respPlantListDto = new RespPlantListDto(user);
        return respPlantListDto;
    }

    /*
    세션정보 넘어온 뒤 user 정보 넣어줘야함
     */
    @Transactional
    public void plantSaveToDB(ReqPlantRegisterDto reqPlantRegisterDto){
        String absolutePath = new File("").getAbsolutePath() + '\\';
        String path = absolutePath + "/src/main/resources/images/plant/";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        UUID uuid = UUID.randomUUID();
        String plantImage = uuid + "_" + reqPlantRegisterDto.getFile().getOriginalFilename();

        Path imageFilePath = Paths.get(path + plantImage);

        try{
            Files.write(imageFilePath, reqPlantRegisterDto.getFile().getBytes());

        }catch (Exception e){
            e.printStackTrace();
        }

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
        MyPlant myPlant = reqPlantRegisterDto.toEntity(plantImage, user);
        myPlantRepository.save(myPlant);
    }
}
