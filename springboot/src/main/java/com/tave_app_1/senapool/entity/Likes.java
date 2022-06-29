package com.tave_app_1.senapool.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "likes")
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "likePK")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userPK")
    private User User;

<<<<<<< HEAD
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diaryPK")
    private PlantDiary PlantDiary;

    public static Likes setLike(User user, PlantDiary diary){
        Likes like = new Likes();
        like.setUser(user);
        like.setPlantDiary(diary);

        return like;
    }
=======
>>>>>>> develop
}