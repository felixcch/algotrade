package com.algotrade.portfolio.repository;

import com.algotrade.portfolio.model.AppUser;
import com.algotrade.portfolio.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

    Optional<Portfolio> findByAppUser(AppUser appUser);
}