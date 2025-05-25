package com.loan_management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "loan")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Loan {
    @Id
    private String loanID;
    
    private float amount;
    private int term;
    private float interestRate;
    private LocalDate createdDate;

    @ManyToOne
    @JoinColumn(name = "customerID")
    private Customer customer;

    
    @ManyToOne
    @JoinColumn(name = "staffID")
    private CreditStaff staff;

}