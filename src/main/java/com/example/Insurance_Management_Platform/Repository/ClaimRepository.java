package com.example.Insurance_Management_Platform.Repository;

import com.example.Insurance_Management_Platform.Data.Claim;
import com.example.Insurance_Management_Platform.Data.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimRepository extends JpaRepository<Claim,Integer> {
}
