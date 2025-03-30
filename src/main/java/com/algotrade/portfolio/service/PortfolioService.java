package com.algotrade.portfolio.service;

import com.algotrade.portfolio.model.AppUser;
import com.algotrade.portfolio.model.Portfolio;
import com.algotrade.portfolio.repository.PortfolioRepository;
import com.algotrade.portfolio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class PortfolioService {

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private UserRepository userRepository;

    public Portfolio createOrUpdatePortfolio(Long userId, BigDecimal currentValue) {
        AppUser appUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Portfolio portfolio = appUser.getPortfolio();
        if (portfolio == null) {
            portfolio = new Portfolio();
            appUser.setPortfolio(portfolio);
        } else {
            // Update existing portfolio
            portfolio.setCurrentValue(currentValue);
        }

        return portfolioRepository.save(portfolio);
    }

    public Optional<Portfolio> findPortfolioByUserId(Long userId) {
        // Find the user by ID
        Optional<AppUser> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            AppUser appUser = userOptional.get();
            // Since it's a one-to-one relationship, get the portfolio directly
            return Optional.ofNullable(appUser.getPortfolio());
        } else {
            return Optional.empty();
        }
    }
}
