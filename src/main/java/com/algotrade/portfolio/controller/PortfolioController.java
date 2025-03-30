package com.algotrade.portfolio.controller;

import com.algotrade.portfolio.model.Portfolio;
import com.algotrade.portfolio.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/portfolio")
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<Portfolio> getPortfolioByUserId(@PathVariable Long userId) {
        try {
            // Fetch the portfolio associated with the user
            Optional<Portfolio> portfolioOptional = portfolioService.findPortfolioByUserId(userId);

            // Return the portfolio if found
            return portfolioOptional.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());

        } catch (Exception e) {
            // Log the exception (in a real app, use a logging framework like SLF4J)
            System.err.println("Error fetching portfolio for user ID " + userId + ": " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}