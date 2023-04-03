package com.example.Insurance_Management_Platform.Data;

import com.example.Insurance_Management_Platform.Helper.ClaimStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name ="claim")
@Builder
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
  public class Claim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String claimNumber;

    private String description;
    private LocalDate claimDate;

    @Enumerated(EnumType.STRING)
    private ClaimStatus claimStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "policy_id")
    private InsurancePolicy insurancePolicy;
}
