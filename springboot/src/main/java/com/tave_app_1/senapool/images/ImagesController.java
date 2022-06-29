package com.tave_app_1.senapool.images;

import com.tave_app_1.senapool.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;

@RestController
@RequiredArgsConstructor
public class ImagesController {

    private final FileUtil fileUtil;

    /**
     * 이미지 다운로드
     * [GET] /images/{type}/{userPK}/{fileName}
     * 작성자 : 장동호
     * 작성일 :
     */
    // type = {plant, user}
    @GetMapping("/images/{type}/{userPK}/{fileName}")
    public Resource downloadImage(@PathVariable("type") String type,
                                  @PathVariable("userPK") int userPK,
                                  @PathVariable("fileName") String fileName) throws MalformedURLException {
        String filePath;
        if(type.equals("plant")) filePath = fileUtil.getPlantFolderPath();
        else filePath = fileUtil.getUserFolderPath();

        return new UrlResource("file:" + filePath + fileName);
    }
}
