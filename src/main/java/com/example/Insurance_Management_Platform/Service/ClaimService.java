package com.example.Insurance_Management_Platform.Service;

import com.example.Insurance_Management_Platform.Data.Claim;
import com.example.Insurance_Management_Platform.Data.InsurancePolicy;
import com.example.Insurance_Management_Platform.Dto.ClaimDto;
import com.example.Insurance_Management_Platform.Dto.ClaimUpdateDto;
import com.example.Insurance_Management_Platform.Repository.ClaimRepository;
import com.example.Insurance_Management_Platform.Repository.ClientRepository;
import com.example.Insurance_Management_Platform.Repository.InsurancePolicyRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClaimService {

    private final ClaimRepository claimRepository;
    private final ClientRepository clientRepository;
    private final InsurancePolicyRepository insurancePolicyRepository;

    public ClaimService(ClaimRepository claimRepository, ClientRepository clientRepository, InsurancePolicyRepository insurancePolicyRepository) {
        this.claimRepository = claimRepository;
        this.clientRepository = clientRepository;
        this.insurancePolicyRepository = insurancePolicyRepository;
    }

    public Optional<Claim> findById(Integer id) {
        return Optional.of(claimRepository.findById(id).orElseThrow());
    }

    public Claim createClaim(ClaimDto claimDto) {
        InsurancePolicy insurancePolicy;

        if(insurancePolicyRepository.findById(claimDto.getPolicyId()).isPresent())
        {
            insurancePolicy = insurancePolicyRepository.findById(claimDto.getPolicyId()).get();
        }
        else throw new RuntimeException("Policy does not exist with provided policyId: " + claimDto.getPolicyId());

        Claim claim = Claim.builder()
                .claimNumber(claimDto.getClaimNumber())
                .claimDate(claimDto.getClaimDate())
                .claimStatus(claimDto.getClaimStatus())
                .description(claimDto.getDescription())
                .build();

        List<Claim> claimList =insurancePolicy.getClaimList();
        claimList.add(claim);

        insurancePolicy.setClaimList(claimList);
        insurancePolicyRepository.save(insurancePolicy);

        return  claim;
    }

    public void updateClaim(Integer id, ClaimUpdateDto updatedClaimDto) {

        Claim claim = claimRepository.findById(id).get();

        claim.setClaimDate(updatedClaimDto.getClaimDate());
        claim.setClaimStatus(updatedClaimDto.getClaimStatus());
        claim.setClaimNumber(updatedClaimDto.getClaimNumber());
        claim.setDescription(updatedClaimDto.getDescription());

        claimRepository.save(claim);
    }

    public ResponseEntity<?> deleteClaim(Integer id) {

        Optional<Claim> optionalClaim = claimRepository.findById(id);
        return optionalClaim.map(claim -> {
            claimRepository.delete(claim);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
