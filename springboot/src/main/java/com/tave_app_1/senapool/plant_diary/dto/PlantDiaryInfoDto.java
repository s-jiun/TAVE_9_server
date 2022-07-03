package com.tave_app_1.senapool.plant_diary.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class PlantDiaryInfoDto {

//    private long plantDiaryPK;
    private String title;
    private String content;
//    private LocalDateTime createdate;
    private MultipartFile diaryImage;
    private Boolean publish;

}
