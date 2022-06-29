package com.tave_app_1.senapool.likes.service;

import com.tave_app_1.senapool.entity.User;
import com.tave_app_1.senapool.likes.repository.LikesRepository;
import com.tave_app_1.senapool.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LikesService {
    private final LikesRepository likesRepository;
    private final UserRepository userRepository;

    @Transactional
    public void likes(long diaryPK, String loginEmail){
        User user = userRepository.findByEmail(loginEmail);
        likesRepository.likes(diaryPK, user.getUserPK());
    }

    @Transactional
    public void unLikes(long diaryPK, String loginEmail){
        User user = userRepository.findByEmail(loginEmail);
        likesRepository.unLikes(diaryPK, user.getUserPK());
    }
}
