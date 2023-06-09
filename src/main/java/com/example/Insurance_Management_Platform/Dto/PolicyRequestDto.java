package com.example.Insurance_Management_Platform.Dto;

import com.example.Insurance_Management_Platform.Helper.PolicyType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
public class PolicyRequestDto {

    private Integer clientId;
    private String policyNumber;
    private PolicyType policyType;
    private BigDecimal coverageAmount;
    private BigDecimal premium;
    private LocalDate startDate;
    private LocalDate endDate;
}
