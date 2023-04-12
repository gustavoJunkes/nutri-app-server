package com.nutriapp.repository;

import com.nutriapp.auth.User;
import com.nutriapp.domain.DailyMenu;
import com.nutriapp.domain.WeekDayEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DailyMenuRepository extends JpaRepository<DailyMenu, UUID> {

    @Query(value = "select * from daily_menu dm " +
                    "inner join weekly_menu wm on wm.id = dm.weekly_menu_id " +
                    "where dm.date = :date and wm.user_id = :user.id", nativeQuery = true)
    Optional<DailyMenu> findByDateAndUser(LocalDate date, User user);

    @Query("select dm from daily_menu dm join dm.weeklyMenu wm where dm.weekDay = ?1 and wm.id = ?2 and wm.user.id = ?3")
    Optional<DailyMenu> getByWeekDayAndAndWeeklyMenuAndUser(WeekDayEnum weekDay, UUID weeklyMenuId, UUID userId);
}
