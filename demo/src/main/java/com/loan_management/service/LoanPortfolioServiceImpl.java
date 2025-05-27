package com.loan_management.service;

import com.loan_management.model.LoanPortfolio;
import com.loan_management.repository.LoanPortfolioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanPortfolioServiceImpl implements LoanPortfolioService {

    @Autowired
    private LoanPortfolioRepository loanPortfolioRepository;
    
    @Override
    public Optional<LoanPortfolio> findById(String portfolioId) {
        return loanPortfolioRepository.findById(portfolioId);
    }
     @Override
    public List<LoanPortfolio> findPortfoliosByLoanType(String loanType) {
        return loanPortfolioRepository.findByLoanType(loanType);
    }
    
    @Override
    public List<LoanPortfolio> findAllPortfolios() {
        return loanPortfolioRepository.findAll();
    }
    
    @Override
    public boolean existsById(String portfolioId) {
        return loanPortfolioRepository.existsById(portfolioId);
    }
}