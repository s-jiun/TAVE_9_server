package com.tave_app_1.senapool.myplant_list.dto.diary_list_response;

import com.tave_app_1.senapool.entity.PlantDiary;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DiaryPrevListDto {

    private List<DiaryPrevDto> diaryPrevDtoList;

    public DiaryPrevListDto(List<PlantDiary> plantDiaryList, Boolean publish) {
        diaryPrevDtoList = new ArrayList<>(plantDiaryList.size());

        for (PlantDiary m : plantDiaryList) {
            // 다른 사람의 일기 목록을 볼 때
            if(publish == false) {
                // 일기장의 publish 속성이 true 되어 있으면 가져온다.
                if (m.getPublish() == true) {
                    String diaryImage;
                    if (m.getDiaryImage().isBlank()) diaryImage = "Default.png";
                    else diaryImage = m.getDiaryImage();

                    diaryPrevDtoList.add(new DiaryPrevDto(m.getPlantDiaryPK(), m.getTitle(), diaryImage, m.getPublish(), m.getCreatedAt()));
                }
            }
            // 내 일기 목록을 볼 때는 목록 전부를 가져온다.
            else {
                String diaryImage;
                if (m.getDiaryImage().isBlank()) diaryImage = "Default.png";
                else diaryImage = m.getDiaryImage();

                diaryPrevDtoList.add(new DiaryPrevDto(m.getPlantDiaryPK(), m.getTitle(), diaryImage, m.getPublish(), m.getCreatedAt()));
            }
        }
    }
}
