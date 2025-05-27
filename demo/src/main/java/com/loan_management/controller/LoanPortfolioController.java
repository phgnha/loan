package com.loan_management.controller;

import com.loan_management.model.LoanPortfolio;
import com.loan_management.service.LoanPortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/portfolios")
@CrossOrigin(origins = "*")
public class LoanPortfolioController {

    @Autowired
    private LoanPortfolioService loanPortfolioService;
    
    // Get all portfolios
    @GetMapping
    public ResponseEntity<List<LoanPortfolio>> getAllPortfolios() {
        List<LoanPortfolio> portfolios = loanPortfolioService.findAllPortfolios();
        return new ResponseEntity<>(portfolios, HttpStatus.OK);
    }
    
    // Get portfolio by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getPortfolioById(@PathVariable("id") String id) {
        return loanPortfolioService.findById(id)
                .<ResponseEntity<?>>map(portfolio -> new ResponseEntity<>(portfolio, HttpStatus.OK))
                .orElse(new ResponseEntity<>("Portfolio not found", HttpStatus.NOT_FOUND));
    }
    
    
    // Get portfolios by loan type
    @GetMapping("/type/{loanType}")
    public ResponseEntity<List<LoanPortfolio>> getPortfoliosByType(@PathVariable("loanType") String loanType) {
        List<LoanPortfolio> portfolios = loanPortfolioService.findPortfoliosByLoanType(loanType);
        return new ResponseEntity<>(portfolios, HttpStatus.OK);
    }
    
    
    // Inner class for portfolio statistics
    public static class PortfolioStats {
        public long loanCount;
        public Float totalAmount;
        
        public PortfolioStats(long loanCount, Float totalAmount) {
            this.loanCount = loanCount;
            this.totalAmount = totalAmount != null ? totalAmount : 0.0f;
        }
    }
}