package com.nutriapp.repository;

import com.nutriapp.auth.User;
import com.nutriapp.domain.Authority;
import com.nutriapp.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, UUID> {

    Optional<Authority> findFirstByRole(String role);

    @Query(value = "SELECT a.* FROM authority a INNER JOIN tb_user_authorities tua ON tua.authorities_id = a.id WHERE tua.tb_user_id = ?1", nativeQuery = true)
    List<Authority> findAuthoritiesByUser(UUID userId);

}
