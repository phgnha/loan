package com.loan_management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Loan_Portfolio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanPortfolio {
    @Id
    @Column(name = "id", length = 20)
    private String id; // e.g., 'VAY01', 'VAY02'
    
    @Column(name = "loan_Type", nullable = false)
    private String loanType;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "interest_Rate", nullable = false)
    private float interestRate; // Base interest rate for this portfolio
    
    @Column(name = "status", length = 50)
    private String status = "Active"; // Default value: 'Active', 'Inactive'
    
}