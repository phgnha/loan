package com.loan_management.service;

import com.loan_management.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    // Create and save a new customer
    Customer createCustomer(Customer customer);
    
    // Find customer by ID
    Optional<Customer> findById(String customerId);
    
    // Get all customers
    List<Customer> findAllCustomers();
    
    // Find customers by name (partial match, case-insensitive)
    List<Customer> findCustomersByName(String name);
    
    // Find customers by minimum credit score
    List<Customer> findCustomersByMinCreditScore(int minScore);
    
    // Find customer by phone number
    Customer findCustomerByPhone(String phone);
    
    // Check if customer exists by ID
    boolean existsById(String customerId);
    
    // Update an existing customer
    Customer updateCustomer(Customer customer);
    
    // Delete a customer
    void deleteCustomer(String customerId);
}