package com.tave_app_1.senapool.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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
    private String diaryImage;

    //공개 여부
    @Column
    private Boolean publish;

    //식물 정보 매핑
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "plant_pk")
    private MyPlant myPlant;

    //유저 정보 매핑
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_pk")
    private User user;

    //좋아요 개수 매핑
    @JsonIgnoreProperties({"diary"})
    @OneToMany(mappedBy = "diary")
    private List<Likes> likesList;

    @Transient
    private long likesCount;

    @Transient
    private boolean likesState;

    public void updateLikesCount(long likesCount) {
        this.likesCount = likesCount;
    }

    public void updateLikesState(boolean likesState) {
        this.likesState = likesState;
    }

}
