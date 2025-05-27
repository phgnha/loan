package com.loan_management.service;

import com.loan_management.model.LoanPortfolio;

import java.util.List;
import java.util.Optional;

public interface LoanPortfolioService {
    // Find portfolio by ID
    Optional<LoanPortfolio> findById(String portfolioId);
    
    // Get all portfolios
    List<LoanPortfolio> findAllPortfolios();
    
    // Find portfolios by loan type
    List<LoanPortfolio> findPortfoliosByLoanType(String loanType);
    // Check if portfolio exists by ID
    boolean existsById(String portfolioId);
    
}