package com.tave_app_1.senapool.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "member")
@AllArgsConstructor // @Builder 를 이용하기 위해서 항상 같이 처리해야 컴파일 에러가 발생하지 않는다
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userPK;

    @Column(name = "userId",nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(name = "userImage",nullable = true)
    private String userImage;

    @Builder
    public Member(String userId, String password, String email, String userImage) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.userImage = userImage;
    }


    public void setPassword(String enPw) {
        this.password = enPw;
    }
}
