package com.loan_management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "Loan")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loanID")
    private Long loanID;
    
    @Column(name = "amount", nullable = false)
    private float amount;
    
    @Column(name = "term", nullable = false)
    private int term;
    
    @Column(name = "interestRate", nullable = false)
    private float interestRate;
    
    @Column(name = "createdDate")
    private LocalDate createdDate;

    @ManyToOne
    @JoinColumn(name = "customerID")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "staffID")
    private CreditStaff staff;
}