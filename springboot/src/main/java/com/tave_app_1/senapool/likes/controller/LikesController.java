package com.tave_app_1.senapool.likes.controller;

import com.tave_app_1.senapool.entity.User;
import com.tave_app_1.senapool.likes.service.LikesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LikesController {
    private final LikesService likesService;

    @PutMapping("/plant-diary/{diaryPK}/like")
    public void likes(@PathVariable("diaryPK") long diaryPK, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        likesService.likes(diaryPK, user.getUserPK());
    }

    @DeleteMapping("/plant-diary/{diaryPK}/unlike")
    public void unLikes(@PathVariable("diaryPK") long diaryPK, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        likesService.unLikes(diaryPK, user.getUserPK());
    }
}
