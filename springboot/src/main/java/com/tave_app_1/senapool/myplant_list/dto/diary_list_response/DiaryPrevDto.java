package com.tave_app_1.senapool.myplant_list.dto.diary_list_response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DiaryPrevDto {

    private Long diaryPK;

    private String title;

    private String image;

    private Boolean publish;

    //private String createdAt;
}
