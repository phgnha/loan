package com.loan_management.controller;

import com.loan_management.model.Loan;
import com.loan_management.service.LoanService;
import com.loan_management.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;
    
    @Autowired
    private CustomerService customerService;
    
    // Create a new loan
    @PostMapping
    public ResponseEntity<?> createLoan(@RequestBody Loan loan) {
        // Check if customer exists
        if (loan.getCustomer() == null || loan.getCustomer().getCustomerID() == null) {
            return new ResponseEntity<>("Customer ID is required", HttpStatus.BAD_REQUEST);
        }
        
        String customerId = loan.getCustomer().getCustomerID();
        if (!customerService.existsById(customerId)) {
            return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
        }
        
        // Check if customer is eligible for the loan
        if (!loanService.isCustomerEligibleForLoan(customerId, loan.getAmount())) {
            return new ResponseEntity<>("Customer is not eligible for this loan amount", 
                                      HttpStatus.UNPROCESSABLE_ENTITY);
        }
        
        // Create the loan
        Loan createdLoan = loanService.createLoan(loan);
        return new ResponseEntity<>(createdLoan, HttpStatus.CREATED);
    }
    
    // Get a loan by ID
    // @GetMapping("/{id}")
    // public ResponseEntity<?> getLoanById(@PathVariable("id") String id) {
    //     return loanService.findById(id)
    //             .map(loan -> new ResponseEntity<>(loan, HttpStatus.OK))
    //             //.orElse(new ResponseEntity<>("Loan not found", HttpStatus.NOT_FOUND));
    // }
    
    // Get all loans
    @GetMapping
    public ResponseEntity<List<Loan>> getAllLoans() {
        List<Loan> loans = loanService.findAllLoans();
        return new ResponseEntity<>(loans, HttpStatus.OK);
    }
    
    // Get loans by customer ID
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Loan>> getLoansByCustomerId(@PathVariable("customerId") String customerId) {
        List<Loan> loans = loanService.findLoansByCustomerId(customerId);
        return new ResponseEntity<>(loans, HttpStatus.OK);
    }
    
    // Get loans by staff ID
    @GetMapping("/staff/{staffId}")
    public ResponseEntity<List<Loan>> getLoansByStaffId(@PathVariable("staffId") String staffId) {
        List<Loan> loans = loanService.findLoansByStaffId(staffId);
        return new ResponseEntity<>(loans, HttpStatus.OK);
    }
    
    // Get loans created between dates
    @GetMapping("/date-range")
    public ResponseEntity<List<Loan>> getLoansByDateRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<Loan> loans = loanService.findLoansByDateRange(startDate, endDate);
        return new ResponseEntity<>(loans, HttpStatus.OK);
    }
    
    // Get loans by amount range
    @GetMapping("/amount-range")
    public ResponseEntity<List<Loan>> getLoansByAmountRange(
            @RequestParam("min") float minAmount,
            @RequestParam("max") float maxAmount) {
        List<Loan> loans = loanService.findLoansByAmountRange(minAmount, maxAmount);
        return new ResponseEntity<>(loans, HttpStatus.OK);
    }
    
    // Get total loan amount for a customer
    @GetMapping("/total-amount/{customerId}")
    public ResponseEntity<?> getTotalLoanAmount(@PathVariable("customerId") String customerId) {
        if (!customerService.existsById(customerId)) {
            return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
        }
        
        Float totalAmount = loanService.calculateTotalLoanAmount(customerId);
        return new ResponseEntity<>(totalAmount, HttpStatus.OK);
    }
    
    // Update a loan
    @PutMapping("/{id}")
    public ResponseEntity<?> updateLoan(@PathVariable("id") String id, @RequestBody Loan loan) {
        if (!loanService.findById(id).isPresent()) {
            return new ResponseEntity<>("Loan not found", HttpStatus.NOT_FOUND);
        }
        
        loan.setLoanID(id); // Ensure the ID matches
        Loan updatedLoan = loanService.updateLoan(loan);
        return new ResponseEntity<>(updatedLoan, HttpStatus.OK);
    }
    
    // Delete a loan
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLoan(@PathVariable("id") String id) {
        if (!loanService.findById(id).isPresent()) {
            return new ResponseEntity<>("Loan not found", HttpStatus.NOT_FOUND);
        }
        
        loanService.deleteLoan(id);
        return new ResponseEntity<>("Loan deleted successfully", HttpStatus.OK);
    }
}