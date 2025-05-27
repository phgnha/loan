package com.loan_management.repository;

import com.loan_management.model.LoanPortfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanPortfolioRepository extends JpaRepository<LoanPortfolio, String> {
    // Find portfolios by loan type
    List<LoanPortfolio> findByLoanType(String loanType);
    // Check if portfolio exists by ID
    boolean existsById(String id);
}