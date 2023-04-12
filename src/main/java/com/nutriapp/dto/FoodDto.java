package com.nutriapp.dto;

import com.nutriapp.domain.UnityEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FoodDto {

    private String id;
    private String description;
    private UnityEnum unity;

}
