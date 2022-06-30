package com.tave_app_1.senapool.feed.controller;

import com.tave_app_1.senapool.entity.PlantDiary;
import com.tave_app_1.senapool.entity.User;
import com.tave_app_1.senapool.feed.service.feedService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;

public class feedController {
    private feedService feedService;

    @GetMapping("/plant-diary")
    public Page<PlantDiary> mainFeed(Authentication authentication, @PageableDefault(size=3)Pageable pageable){
        User user  = (User) authentication.getPrincipal();
        return feedService.getDiaries(user.getUserPK(), pageable);
    }
}
