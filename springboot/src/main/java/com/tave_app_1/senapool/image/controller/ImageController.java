package com.tave_app_1.senapool.image.controller;

import com.tave_app_1.senapool.image.service.ImageService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.net.MalformedURLException;

@RestController
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    /**
     * 이미지 다운로드
     * [GET] /images/{type}/{fileName}
     * 작성자 : 장동호
     * 수정일 : 2022-07-01
     */
    // type = {plant, user, diary}
    @ApiOperation(value = "이미지 다운로드", notes = "선택한 이미지 다운로드 - type = {plant, user, diary}", response = UrlResource.class)
    @GetMapping("/images/{type}/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable("type") @NotBlank String type,
                                           @PathVariable("fileName") @NotBlank String fileName) throws MalformedURLException {

        String filePath = imageService.getFilePath(type);
        return new ResponseEntity<Resource>(new UrlResource("file:" + filePath + fileName), HttpStatus.OK);
    }
}