package com.tave_app_1.senapool.image.service;

import com.tave_app_1.senapool.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final FileUtil fileUtil;

    public String getFilePath(String type) {
        if(type.equals("plant")) return fileUtil.getPlantFolderPath();
        else if(type.equals("user")) return fileUtil.getUserFolderPath();
        else if(type.equals("diary")) return fileUtil.getDiaryFolderPath();
        else return "bad request";
    }
}
