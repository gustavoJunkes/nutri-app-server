package com.nutriapp.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FoodDto {

    private String id;
    private String description;

}
