package com.tave_app_1.senapool.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "myplant")
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MyPlant {

    @Id @GeneratedValue
    @Column(name = "plant_pk")
    private Integer plantPK;

    @ManyToOne
    @JoinColumn(name = "user_pk")
    private User user;

    @Column(nullable = false, name = "plant_type")
    private String plantType;

    @Column(nullable = false, name = "last_water")
    private LocalDateTime lastWater;

    /*
    자료형 수정 필요
     */
    @Column(nullable = false, name = "plant_Image")
    private String plantImage;

    @Column(nullable = false, name = "plant_name")
    private String plantName;

    @Column(nullable = false, name = "start_day")
    private String startDay;
}
