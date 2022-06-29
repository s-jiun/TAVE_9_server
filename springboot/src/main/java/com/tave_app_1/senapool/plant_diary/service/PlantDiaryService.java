package com.tave_app_1.senapool.plant_diary.service;


import com.tave_app_1.senapool.plant_diary.repository.PlantDiaryRepository;
import com.tave_app_1.senapool.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class PlantDiaryService {


    private final UserRepository userRepository;
    private final PlantDiaryRepository plantDiaryRepository;

//    @Value("${plantdiary.path}")
//    private String picture;

//    @Transactional
//    public void save(PlantDiaryUploadDto plantDiaryUploadDto,long userId, Mu)

}
