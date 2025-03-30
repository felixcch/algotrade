package com.algotrade.portfolio.repository;

import com.algotrade.portfolio.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findById(Long id);
}