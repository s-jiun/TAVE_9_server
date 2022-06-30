package com.tave_app_1.senapool.image.controller;

import com.tave_app_1.senapool.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;

@RestController
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    /**
     * 이미지 다운로드
     * [GET] /images/{type}/{userPK}/{fileName}
     * 작성자 : 장동호
     * 작성일 :
     */
    // type = {plant, user}
    @GetMapping("/images/{type}/{fileName}")
    public Resource downloadImage(@PathVariable("type") String type,
                                  @PathVariable("userPK") int userPK,
                                  @PathVariable("fileName") String fileName) throws MalformedURLException {
        /*
        토큰으로 url 인가처리
         */

        String filePath = imageService.getFilePath(type);
        return new UrlResource("file:" + filePath + fileName);
    }
}