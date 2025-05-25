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
@Table(name = "credit_staff")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditStaff {
    @Id
    private String staffID;
    
    private String name;
    private String position;
    private String email;
    
    @OneToMany(mappedBy = "staff")
    private List<Loan> loans;
}