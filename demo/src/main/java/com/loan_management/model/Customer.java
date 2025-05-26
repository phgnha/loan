package com.loan_management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @Column(name = "customerID", length = 10)
    private String customerID;
    
    @Column(name = "name", length = 100, nullable = false)
    private String name;
    
    @Column(name = "creditScore", nullable = false)
    private int creditScore;
    
    @Column(name = "phone", length = 15)
    private String phone;
    
    @Column(name = "address", length = 200)
    private String address;
}