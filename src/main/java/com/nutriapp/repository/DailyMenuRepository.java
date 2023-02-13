package com.nutriapp.repository;

import com.nutriapp.domain.DailyMenu;
import com.nutriapp.domain.WeekDayEnum;
import com.nutriapp.domain.WeeklyMenu;
import com.nutriapp.dto.DailyMenuDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DailyMenuRepository extends JpaRepository<DailyMenu, UUID> {

    Optional<DailyMenu> findByDate(LocalDate date);

    Optional<DailyMenu> findFirstByWeekDay(WeekDayEnum weekDay);

}
