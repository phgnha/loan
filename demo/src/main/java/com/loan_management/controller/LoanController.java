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
@CrossOrigin(origins = "*") // Thêm dòng này
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
            return new ResponseEntity<>("Không tìm thấy khách hàng", HttpStatus.NOT_FOUND);
        }

        if (loanService.hasPendingLoan(customerId)) {
            return new ResponseEntity<>("Khách Hàng có khoản vay đang xử lý, không thể tạo thêm khoản vay mới", 
                                      HttpStatus.CONFLICT);
        }
        
        // Check if customer is eligible for the loan

        
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
    
    // Delete a loan
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLoan(@PathVariable("id") Long id) {
        if (!loanService.findById(id).isPresent()) {
            return new ResponseEntity<>("Loan not found", HttpStatus.NOT_FOUND);
        }
        
        loanService.deleteLoan(id);
        return new ResponseEntity<>("Loan deleted successfully", HttpStatus.OK);
    }
}



// curl -X POST http://localhost:8080/api/loans ^
//   -H "Content-Type: application/json" ^
//   -d "{ \"amount\": 1000000, \"term\": 16, \"interest_rate\": 5.5, \"staffID\": \"S003\", \"created_date\": \"2025-05-01\", \"customer\": {\"customerID\": \"C001\"}}"

//   curl -X DELETE http://localhost:8080/api/loans/{id}

//   curl -X GET http://localhost:8080/api/loans