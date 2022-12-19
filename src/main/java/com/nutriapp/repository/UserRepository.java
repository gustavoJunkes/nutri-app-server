package com.nutriapp.repository;

import com.nutriapp.domain.Food;
import com.nutriapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
