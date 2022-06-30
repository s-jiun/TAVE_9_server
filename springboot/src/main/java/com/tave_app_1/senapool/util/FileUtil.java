package com.tave_app_1.senapool.util;

import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Getter
@Component
public class FileUtil {

    private final String absolutePath;
    private final String plantFolderPath;
    private final String userFolderPath;
    private final String diaryFolderPath;

    public FileUtil() {
        this.absolutePath = new File("").getAbsolutePath() + File.separator;
        this.plantFolderPath = absolutePath + "src/main/resources/static/images/plant/";
        this.userFolderPath = absolutePath + "src/main/resources/static/images/user/";
        this.diaryFolderPath = absolutePath + "src/main/resources/static/images/diary/";
    }

    // uuid 추가한 이미지 이름 반환
    private String getUniqueImageName(MultipartFile file) {
        return UUID.randomUUID() + "_" + file.getOriginalFilename();
    }

    /*
    향후 중복 리팩토링
     */

    // 식물 이미지를 저장할 경로 반환
    private Path getPlantImagePath(String imageName) {
        return Paths.get(plantFolderPath + imageName);
    }

    // 유저 이미지를 저장할 경로 반환
    private Path getUserImagePath(String imageName) {
        return Paths.get(userFolderPath + imageName);
    }

    // 일기 이미지를 저장할 경로 반환
    private Path getDiaryImagePath(String imageName) {
        return Paths.get(diaryFolderPath + imageName);
    }

    public String savePlantImage(MultipartFile file) {
        String uniqueImageName = getUniqueImageName(file);

        Path filePath = getPlantImagePath(uniqueImageName);

        try {
            file.transferTo(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return uniqueImageName;
    }

    public String saveUserImage(MultipartFile file) {
        String uniqueImageName = getUniqueImageName(file);

        Path filePath = getUserImagePath(uniqueImageName);

        try {
            file.transferTo(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return uniqueImageName;
    }

    public String saveDiaryImage(MultipartFile file) {
        String uniqueImageName = getUniqueImageName(file);

        Path filePath = getDiaryImagePath(uniqueImageName);

        try {
            file.transferTo(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return uniqueImageName;
    }


    public void deletePlantImage(String imageName) {
        File file = new File(plantFolderPath + imageName);
        if (file.exists()) {
            file.delete();
        }
    }

    public void deleteUserImage(String imageName) {
        File file = new File(userFolderPath + imageName);
        if (file.exists()) {
            file.delete();
        }
    }

    public void deleteDiaryImage(String imageName) {
        File file = new File(diaryFolderPath + imageName);
        if (file.exists()) {
            file.delete();
        }
    }
}
