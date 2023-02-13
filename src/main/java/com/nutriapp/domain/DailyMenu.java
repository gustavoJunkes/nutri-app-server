package com.nutriapp.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity(name = "daily_menu")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DailyMenu {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private WeekDayEnum weekDay;

    @ManyToOne
    private WeeklyMenu weeklyMenu;
}
