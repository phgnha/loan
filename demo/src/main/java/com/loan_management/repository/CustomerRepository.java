package com.loan_management.repository;

import com.loan_management.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    
    // Find customers by name containing the given string (case-insensitive)
    List<Customer> findByNameContainingIgnoreCase(String name);
    
    // Find customers with credit score greater than or equal to given value
    List<Customer> findByCreditScoreGreaterThanEqual(int minimumScore);
    
    // Find customer by phone number
    Customer findByPhone(String phone);
    
    // Check if customer exists by ID
    boolean existsByCustomerID(String customerID);
}