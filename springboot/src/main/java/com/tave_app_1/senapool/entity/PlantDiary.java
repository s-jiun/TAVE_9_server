package com.tave_app_1.senapool.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlantDiary{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //일기 순번
    @Column(nullable = false)
    private Long num;

    //일기 제목
    @Column(nullable = false)
    private String title;

    //일기 내용
    @Column
    private String content;

    //업로드 사진
    @Column
    private String picture;

    //공개 여부
    @Column
    private Boolean open;


}
