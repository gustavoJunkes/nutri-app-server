package com.nutriapp.dto;

import com.nutriapp.auth.User;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class WeeklyMenuDto {

    private UUID id;

    private User user;

    private LocalDate beginDate;

    private LocalDate endDate;

}
