package com.tave_app_1.senapool.image.controller;

import com.tave_app_1.senapool.exception.ErrorCode;
import com.tave_app_1.senapool.exception.ErrorResponse;
import com.tave_app_1.senapool.image.service.ImageService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

@Slf4j
@RestController
@RequiredArgsConstructor
@Slf4j
public class ImageController {

    private final ImageService imageService;

    /**
     * 이미지 다운로드
     * [GET] /images/{type}/{fileName}
     * 작성자 : 장동호
     * 수정일 : 2022-07-01
     */
    // type = {plant, user, diary}
    @ApiOperation(value = "이미지 다운로드", notes = "선택한 이미지 다운로드 - type = {plant, user, diary}")
    @GetMapping("/images/{type}/{fileName}")
    public ResponseEntity<Resource> downloadImage(@PathVariable("type") @NotBlank String type,
                                                  @PathVariable("fileName") @NotBlank String fileName, HttpServletRequest request) throws MalformedURLException {

        log.info("이미지 호출");
        String filePath = imageService.getFilePath(type);
        Resource resource = new UrlResource("file:" + filePath + fileName);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            log.info("Could not determine file type.");
        }

        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}