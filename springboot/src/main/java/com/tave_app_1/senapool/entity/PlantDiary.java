package com.tave_app_1.senapool.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
//@Setter
//@EqualsAndHashCode(of = "id")
//@Builder
//@AllArgsConstructor
@NoArgsConstructor
public class PlantDiary extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diary_pk")
    private Long id;

    //일기 제목
    @Column(nullable = false)
    private String title;

    //일기 내용 (-text)
    @Column
    private String content;

    //업로드 사진 (-postImgUrl)
    @Column
    private String picture;

    //공개 여부
    @Column
    private Boolean publish;

//    //글 올린 날
//    @Column
//    private LocalDateTime createDate;

    //식물 정보 매핑
    @ManyToOne
    @JoinColumn(name = "plant_pk")
    private MyPlant myPlant;

    //유저 정보 매핑
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_pk")
    private User user;

//    public void addPlant(MyPlant myPlant){
//        PlantDiary plantDiary = new PlantDiary();
//        plantDiary.setMyPlant(myPlant);
//    }

    @Builder
    public PlantDiary(String picture, String title, String content,User user){
        this.picture = picture;
        this.title = title;
        this.content = content;
        this.user=user;
    }

    public void update(String picture, String title, String content){
        this.picture = picture;
        this.title = title;
        this.content = content;
    }

}
