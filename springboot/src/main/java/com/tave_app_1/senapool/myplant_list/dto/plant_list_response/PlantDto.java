package com.tave_app_1.senapool.myplant_list.dto.plant_list_response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@AllArgsConstructor
public class PlantDto {

    private Long plantPK;

    private String plantName;

    private String plantImage;

}
