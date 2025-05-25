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
        if (loan.getCreatedDate() == null) {
            loan.setCreatedDate(LocalDate.now());
        }
        
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
    public List<Loan> findLoansByCustomer(Customer customer) {
        return loanRepository.findByCustomer(customer);
    }
    
    @Override
    public List<Loan> findLoansByCustomerId(String customerId) {
        return loanRepository.findByCustomer_CustomerID(customerId);
    }
    
    @Override
    public List<Loan> findLoansByStaff(CreditStaff staff) {
        return loanRepository.findByStaff(staff);
    }
    
    @Override
    public List<Loan> findLoansByStaffId(String staffId) {
        return loanRepository.findByStaff_StaffID(staffId);
    }
    
    @Override
    public List<Loan> findLoansByDateRange(LocalDate startDate, LocalDate endDate) {
        return loanRepository.findByCreatedDateBetween(startDate, endDate);
    }
    
    @Override
    public List<Loan> findLoansByAmountRange(float minAmount, float maxAmount) {
        return loanRepository.findByAmountBetween(minAmount, maxAmount);
    }
    
    @Override
    public Float calculateTotalLoanAmount(String customerId) {
        Float total = loanRepository.sumLoanAmountByCustomerID(customerId);
        return total != null ? total : 0.0f;
    }
    
    @Override
    public long countLoansByCustomer(String customerId) {
        return loanRepository.countByCustomer_CustomerID(customerId);
    }
    
    @Override
    public boolean isCustomerEligibleForLoan(String customerId, float requestedAmount) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        
        if (!customerOpt.isPresent()) {
            return false;
        }
        
        Customer customer = customerOpt.get();
        int creditScore = customer.getCreditScore();
        
        // Business rule: Check credit score and loan amount
        if (creditScore < 300) {
            return false;  // Very poor credit score
        } else if (creditScore < 500) {
            return requestedAmount <= 10000000; // Up to 10 million VND
        } else if (creditScore < 700) {
            return requestedAmount <= 50000000; // Up to 50 million VND
        } else {
            return true; // Good credit score, eligible for any amount
        }
    }
    
    @Override
    public Loan updateLoan(Loan loan) {
        return loanRepository.save(loan);
    }
    
    @Override
    public void deleteLoan(String loanId) {
        loanRepository.deleteById(loanId);
    }
}