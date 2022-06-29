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
public class PlantDiary extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diary_pk")
    private Long id;

//    @Column(nullable = false)
//    private Long num;

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
    private Boolean publish;

    //식물 정보 매핑
    @ManyToOne
    @JoinColumn(name = "plant_pk")
    private MyPlant myPlant;

    //유저 정보 매핑
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_pk")
    private User user;

    public void addPlant(MyPlant myPlant){
        PlantDiary plantDiary = new PlantDiary();
        plantDiary.setMyPlant(myPlant);
    }

}
