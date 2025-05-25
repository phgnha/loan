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
    Optional<Loan> findById(String loanId);
    
    // Get all loans
    List<Loan> findAllLoans();
    
    // Find loans by customer
    List<Loan> findLoansByCustomer(Customer customer);
    
    // Find loans by customer ID
    List<Loan> findLoansByCustomerId(String customerId);
    
    // Find loans by staff
    List<Loan> findLoansByStaff(CreditStaff staff);
    
    // Find loans by staff ID
    List<Loan> findLoansByStaffId(String staffId);
    
    // Find loans created between dates
    List<Loan> findLoansByDateRange(LocalDate startDate, LocalDate endDate);
    
    // Find loans by amount range
    List<Loan> findLoansByAmountRange(float minAmount, float maxAmount);
    
    // Calculate total loan amount for a customer
    Float calculateTotalLoanAmount(String customerId);
    
    // Count loans by customer
    long countLoansByCustomer(String customerId);
    
    // Business logic: Check if customer is eligible for a loan
    boolean isCustomerEligibleForLoan(String customerId, float requestedAmount);
    
    // Update an existing loan
    Loan updateLoan(Loan loan);
    
    // Delete a loan
    void deleteLoan(String loanId);
}