package com.tave_app_1.senapool.myplant_list.dto.diary_list_response;

import com.tave_app_1.senapool.entity.PlantDiary;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DiaryPrevListDto {

    private List<DiaryPrevDto> diaryPrevDtoList;

    public DiaryPrevListDto(List<PlantDiary> plantDiaryList) {
        diaryPrevDtoList = new ArrayList<>(plantDiaryList.size());

        for (PlantDiary m : plantDiaryList) {
            String diaryImage;
            if(m.getDiaryImage().isBlank()) diaryImage = "Default.png";
            else diaryImage = m.getDiaryImage();

            diaryPrevDtoList.add(new DiaryPrevDto(m.getId(), m.getTitle(), diaryImage, m.getPublish(), m.getCreatedAt()));
        }
    }
}
