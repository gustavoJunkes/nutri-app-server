package com.nutriapp.repository;

import com.nutriapp.domain.WeeklyMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WeeklyMenuRepository extends JpaRepository<WeeklyMenu, UUID> {

    @Query(value = "SELECT * FROM weekly_menu wm WHERE wm.begin_date <= ?1 and wm.end_date >= ?1 limit 1", nativeQuery = true)
    Optional<WeeklyMenu> buscaPorDataEntre(LocalDate date);

    Optional<WeeklyMenu> findFirstByBeginDateLessThanEqualAndEndDateGreaterThanEqual(LocalDate date, LocalDate otherDate);

}
