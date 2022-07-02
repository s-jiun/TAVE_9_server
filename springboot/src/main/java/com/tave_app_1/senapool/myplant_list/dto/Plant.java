package com.tave_app_1.senapool.myplant_list.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
@AllArgsConstructor
public class Plant {
    private Long plantPK;
    private String plantName;
    private byte[] plantImage; // 자료형 변환해줘야함
}
