package com.tave_app_1.senapool.feed.service;

import com.tave_app_1.senapool.entity.PlantDiary;
import com.tave_app_1.senapool.feed.repository.feedRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public class feedService {
    private feedRepository feedRepository;

    @Transactional
    public Page<PlantDiary> getDiaries(long tokenId, Pageable pageable){
        Page<PlantDiary> plantDiaryList = feedRepository.mainFeed(tokenId, pageable);

        plantDiaryList.forEach(diary -> {
            diary.updateLikesCount(diary.getLikesList().size());
            diary.getLikesList().forEach(likes -> {
                if(likes.getUser().getUserPK() == tokenId) diary.updateLikesState(true);
            });
        });

        return plantDiaryList;
    }
}
