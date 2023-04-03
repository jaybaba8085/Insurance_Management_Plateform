package com.example.Insurance_Management_Platform.Dto;

import com.example.Insurance_Management_Platform.Data.InsurancePolicy;
import com.example.Insurance_Management_Platform.Helper.ClaimStatus;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@Data
public class ClaimDto {

    private  Integer policyId;
    private String claimNumber;
    private String description;
    private LocalDate claimDate;
    private ClaimStatus claimStatus;

}
