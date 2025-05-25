package com.loan_management.service;

import com.loan_management.model.Customer;
import com.loan_management.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    
    @Override
    public Customer createCustomer(Customer customer) {
        // Generate a customer ID if not provided
        if (customer.getCustomerID() == null || customer.getCustomerID().isEmpty()) {
            customer.setCustomerID("C" + System.currentTimeMillis());
        }
        
        return customerRepository.save(customer);
    }
    
    @Override
    public Optional<Customer> findById(String customerId) {
        return customerRepository.findById(customerId);
    }
    
    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }
    
    @Override
    public List<Customer> findCustomersByName(String name) {
        return customerRepository.findByNameContainingIgnoreCase(name);
    }
    
    @Override
    public List<Customer> findCustomersByMinCreditScore(int minScore) {
        return customerRepository.findByCreditScoreGreaterThanEqual(minScore);
    }
    
    @Override
    public Customer findCustomerByPhone(String phone) {
        return customerRepository.findByPhone(phone);
    }
    
    @Override
    public boolean existsById(String customerId) {
        return customerRepository.existsByCustomerID(customerId);
    }
    
    @Override
    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
    
    @Override
    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }
}