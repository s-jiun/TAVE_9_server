package com.tave_app_1.senapool.image.service;

import com.tave_app_1.senapool.user.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final FileUtil fileUtil;

    public String getFilePath(String type) {
        if(type.equals("plant")) return fileUtil.getPlantFolderPath();
        else return fileUtil.getUserFolderPath();
    }
}
