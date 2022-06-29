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

    public FileUtil() {
        this.absolutePath = new File("").getAbsolutePath() + File.separator;
        this.plantFolderPath = absolutePath + "src/main/resources/static/images/plant/";
        this.userFolderPath = absolutePath + "src/main/resources/static/images/user/";
    }

    // uuid 추가한 이미지 이름 반환
    public String getUniqueImageName(MultipartFile file) {
        return UUID.randomUUID() + "_" + file.getOriginalFilename();
    }

    // 식물 이미지를 저장할 경로 반환
    public Path getPlantImagePath(String imageName) {
        return Paths.get(plantFolderPath + imageName);
    }

    // 유저 이미지를 저장할 경로 반환
    public Path getUserImagePath(String imageName) {
        return Paths.get(userFolderPath + imageName);
    }

    public void savePlantImage(MultipartFile file) {
        String uniqueImageName = getUniqueImageName(file);

        Path filePath = getPlantImagePath(uniqueImageName);

        try {
            file.transferTo(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveUserImage(MultipartFile file) {
        String uniqueImageName = getUniqueImageName(file);

        Path filePath = getUserImagePath(uniqueImageName);

        try {
            file.transferTo(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
