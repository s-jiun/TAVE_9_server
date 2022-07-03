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
public class PlantDiaryDto {

    private Long plantDiaryPK;

    private String title;

    private String content;

    private String diaryImage;

    private Boolean publish;

    private LocalDateTime createDate;

    private  LocalDateTime modifiedDate;

}
