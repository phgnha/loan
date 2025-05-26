package com.loan_management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CreditStaff")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditStaff {
    @Id
    @Column(name = "staffID", length = 10)
    private String staffID;
    
    @Column(name = "name", length = 100, nullable = false)
    private String name;
    
    @Column(name = "position", length = 50)
    private String position;
    
    @Column(name = "email", length = 100)
    private String email;
}