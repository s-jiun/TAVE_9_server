package com.tave_app_1.senapool.plant_diary.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class PlantDiaryDto {

    private Long id;

    private String title;

    private String content;

    private String picture;

    private Boolean publish;

    private LocalDateTime createDate;

    private  LocalDateTime modifiedDate;

}
