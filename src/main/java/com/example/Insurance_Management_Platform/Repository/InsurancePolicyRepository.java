package com.example.Insurance_Management_Platform.Repository;

import com.example.Insurance_Management_Platform.Data.InsurancePolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsurancePolicyRepository extends JpaRepository<InsurancePolicy,Integer> {
}
