package com.tave_app_1.senapool.feed.service;

import com.tave_app_1.senapool.entity.PlantDiary;
import com.tave_app_1.senapool.feed.repository.feedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class feedService {
    private final feedRepository feedRepository;

    @Transactional
    public Page<PlantDiary> getDiaries(long userPK, Pageable pageable){
        Page<PlantDiary> plantDiaryList = feedRepository.mainFeed(userPK, pageable);

        plantDiaryList.filter(diary ->
            diary.getPublish().equals(true)
        ).forEach(diary -> {
            diary.updateLikesCount(diary.getLikesList().size());
            diary.getLikesList().forEach(likes -> {
                if(likes.getUser().getUserPK() == userPK) diary.updateLikesState(true);
            });
        });

        return plantDiaryList;
    }
}
