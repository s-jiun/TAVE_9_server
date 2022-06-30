package com.tave_app_1.senapool.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "myPlant")
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MyPlant {

    @Id @GeneratedValue
    @Column(name = "plant_pk")
    private Long plantPK;

    @Column(nullable = false, name = "plant_name")
    private String plantName;

    @Column(nullable = false, name = "plant_type")
    private String plantType;

    @Column(nullable = false, name = "water_period")
    private Integer waterPeriod;

    @Column(nullable = false, name = "plant_Image")
    private String plantImage;

    /*
     nullable 해제
     */
    @Column(nullable = true, name = "start_day")
    private String startDay;

    /*
     nullable 해제
     */
    @Column(nullable = true, name = "last_water")
    private LocalDateTime lastWater;

    /*
     startDay, lastWater 추가
     */

    /*
    cascade 설정 변경 필요
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_pk")
    private User user;

    @OneToMany(mappedBy = "myPlant")
    private List<PlantDiary> plantDiaryList;

    public MyPlant(User user, String plantName, String plantType, Integer waterPeriod, String plantImage) {
        this.user = user;
        this.plantName = plantName;
        this.plantType = plantType;
        this.waterPeriod = waterPeriod;
        this.plantImage = plantImage;
    }

    public void updatePlant(String plantImage, String plantName, String plantType, Integer waterPeriod) {
        this.plantImage = plantImage;
        this.plantName = plantName;
        this.plantType = plantType;
        this.waterPeriod = waterPeriod;
    }
}
