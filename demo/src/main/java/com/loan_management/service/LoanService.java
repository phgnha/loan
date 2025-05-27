package com.loan_management.service;

import com.loan_management.model.Loan;
import com.loan_management.model.Customer;
import com.loan_management.model.CreditStaff;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LoanService {
    // Create and save a new loan
    Loan createLoan(Loan loan);
    
    // Find loan by ID
    Optional<Loan> findById(Long loanId);
    
    // Get all loans
    List<Loan> findAllLoans();
    
    
    // Delete a loan
    void deleteLoan(Long loanId);
    
    boolean hasPendingLoan(String customerId);

}