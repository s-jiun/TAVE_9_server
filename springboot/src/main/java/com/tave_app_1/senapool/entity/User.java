package com.tave_app_1.senapool.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "user")
@AllArgsConstructor // @Builder 를 이용하기 위해서 항상 같이 처리해야 컴파일 에러가 발생하지 않는다
@NoArgsConstructor
public class User {

    @Id @Column(name = "user_pk")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userPK;

    @Column(name = "user_id",nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(name = "user_image",nullable = true)
    private String userImage;

    //@OneToMany(mappedBy = "user")
    //private List<MyPlant> myPlants;


    @Builder
    public User(String userId, String password, String email, String userImage) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.userImage = userImage;
    }


    public void setPassword(String enPw) {
        this.password = enPw;
    }
}