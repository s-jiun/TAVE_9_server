package com.tave_app_1.senapool.likes.repository;

import com.tave_app_1.senapool.entity.Likes;
import com.tave_app_1.senapool.entity.PlantDiary;
import com.tave_app_1.senapool.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    Likes findLikesByDiaryAndUser(PlantDiary diary, User user);

    @Modifying
    @Query(value = "INSERT INTO likes(diary_pk, user_pk) VALUES(:diaryPK, :userPK)", nativeQuery = true)
    void likes(long diaryPK, long userPK);

    @Modifying
    @Query(value = "DELETE FROM likes WHERE diary_pk = :diaryPK AND user_pk = :userPK", nativeQuery = true)
    void unLikes(long diaryPK, long userPK);
}
