package com.loan_management.repository;

import com.loan_management.model.CreditStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditStaffRepository extends JpaRepository<CreditStaff, String> {
    
    // Find staff members by position
    List<CreditStaff> findByPosition(String position);
    
    // Find staff members by name containing the given string (case-insensitive)
    List<CreditStaff> findByNameContainingIgnoreCase(String name);
    
    // Find staff member by email
    CreditStaff findByEmail(String email);
    
    // Check if staff exists by ID
    boolean existsByStaffID(String staffID);
}