package com.loan_management.service;

import com.loan_management.model.Loan;
import com.loan_management.model.Customer;
import com.loan_management.model.CreditStaff;
import com.loan_management.repository.LoanRepository;
import com.loan_management.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Override
    public Loan createLoan(Loan loan) {
        // Generate a loan ID if not provided
        if (loan.getLoanID() == null || loan.getLoanID().isEmpty()) {
            loan.setLoanID("L" + System.currentTimeMillis());
        }
        
        // Set created date if not provided
        // if (loan.getCreatedDate() == null) {
        //     loan.setCreatedDate(LocalDate.now());
        // }
        
        return loanRepository.save(loan);
    }
    
    @Override
    public Optional<Loan> findById(String loanId) {
        return loanRepository.findById(loanId);
    }
    
    @Override
    public List<Loan> findAllLoans() {
        return loanRepository.findAll();
    }
    
    @Override
    public void deleteLoan(String loanId) {
        loanRepository.deleteById(loanId);
    }
}