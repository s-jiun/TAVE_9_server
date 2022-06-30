package com.tave_app_1.senapool.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class PlantDiaryModule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;
}

