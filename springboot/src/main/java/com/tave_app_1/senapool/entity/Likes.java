package com.tave_app_1.senapool.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "like_table")
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "likePK")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userPK")
    private User user;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diaryPK")
    private Diary diary;

    public static Likes setLike(User user, Diary diary){
        Likes like = new Likes();
        like.setUser(user);
        like.setDiary(diary);

        return like;
    }*/


}