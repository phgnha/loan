package com.loan_management.repository;

import com.loan_management.model.Customer;
import com.loan_management.model.CreditStaff;
import com.loan_management.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    
    // Find loans by customer
    List<Loan> findByCustomer(Customer customer);
    
    // Find loans by customer ID
    List<Loan> findByCustomer_CustomerID(String customerID);
    
    // Find loans by staff
    List<Loan> findByStaff(CreditStaff staff);
    
    // Find loans by staff ID
    List<Loan> findByStaff_StaffID(String staffID);
    
    // Find loans created between dates
    List<Loan> findByCreatedDateBetween(LocalDate startDate, LocalDate endDate);
    
    // Find loans by amount range
    List<Loan> findByAmountBetween(float minAmount, float maxAmount);
    
    // Find loans by term
    List<Loan> findByTerm(int term);
    
    // Calculate sum of loan amount by customer ID
    @Query("SELECT SUM(l.amount) FROM Loan l WHERE l.customer.customerID = :customerID")
    Float sumLoanAmountByCustomerID(@Param("customerID") String customerID);
    
    // Count loans by customer ID
    long countByCustomer_CustomerID(String customerID);
}