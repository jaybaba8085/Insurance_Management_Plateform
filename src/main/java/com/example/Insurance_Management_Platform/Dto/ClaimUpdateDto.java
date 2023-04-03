package com.example.Insurance_Management_Platform.Dto;

import com.example.Insurance_Management_Platform.Helper.ClaimStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class ClaimUpdateDto {
    private String claimNumber;
    private String description;
    private LocalDate claimDate;
    private ClaimStatus claimStatus;
}
