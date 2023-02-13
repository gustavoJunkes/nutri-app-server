package com.nutriapp.domain;

import com.nutriapp.auth.User;
import jakarta.persistence.*;
import lombok.*;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Entity(name = "weekly_menu")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeeklyMenu {


    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

//    @OneToMany
//    private List<DailyMenu> dailyMenus;

    @ManyToOne
    private User user;

    @Column(name = "begin_date")
    private LocalDate beginDate;

    @Column(name = "end_date")
    private LocalDate endDate;

}
// TODO: 27/01/2023 criar endopoint para weekly menu 
// TODO: 27/01/2023 criar endpoint para daily menu