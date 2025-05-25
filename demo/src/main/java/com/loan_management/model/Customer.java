package com.loan_management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    private String customerID;
    
    private String name;
    private int creditScore;
    private String phone;
    private String address;
    
    @OneToMany(mappedBy = "customer")
    private List<Loan> loans;
}