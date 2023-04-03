package com.example.Insurance_Management_Platform.Service;

import com.example.Insurance_Management_Platform.Data.Client;
import com.example.Insurance_Management_Platform.Data.InsurancePolicy;
import com.example.Insurance_Management_Platform.Dto.PolicyRequestDto;
import com.example.Insurance_Management_Platform.Dto.PolicyUpdateDto;
import com.example.Insurance_Management_Platform.Repository.ClaimRepository;
import com.example.Insurance_Management_Platform.Repository.ClientRepository;
import com.example.Insurance_Management_Platform.Repository.InsurancePolicyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InsurancePolicyService {

    private final ClaimRepository claimRepository;
    private final ClientRepository clientRepository;
    private final InsurancePolicyRepository insurancePolicyRepository;

    public InsurancePolicyService(ClaimRepository claimRepository, ClientRepository clientRepository, InsurancePolicyRepository insurancePolicyRepository) {
        this.claimRepository = claimRepository;
        this.clientRepository = clientRepository;
        this.insurancePolicyRepository = insurancePolicyRepository;
    }

    public List<InsurancePolicy> getAllPolicies() {
        return insurancePolicyRepository.findAll();
    }

    public Optional<InsurancePolicy> getPolicyById(Integer id) {
        return insurancePolicyRepository.findById(id);
    }

    public InsurancePolicy createPolicy(PolicyRequestDto policy) {
        Client client = clientRepository.findById(policy.getClientId()).get();

        InsurancePolicy insurancePolicy = InsurancePolicy.builder()
                .policyType(policy.getPolicyType())
                .policyNumber(policy.getPolicyNumber())
                .premium(policy.getPremium())
                .startDate(policy.getStartDate())
                .endDate(policy.getEndDate())
                .coverageAmount(policy.getCoverageAmount())
                .build();

        List<InsurancePolicy> insurancePolicies = client.getInsurancePolicies();
        insurancePolicies.add(insurancePolicy);
        client.setInsurancePolicies(insurancePolicies);
        clientRepository.save(client);

        insurancePolicyRepository.save(insurancePolicy);
        return  insurancePolicy;
    }

    public InsurancePolicy updatePolicy(Integer id, PolicyUpdateDto policyUpdateDto) {
        InsurancePolicy insurancePolicy = insurancePolicyRepository.findById(id).get();

        insurancePolicy.setPolicyNumber(policyUpdateDto.getPolicyNumber());
        insurancePolicy.setPolicyType(policyUpdateDto.getPolicyType());
        insurancePolicy.setCoverageAmount(policyUpdateDto.getCoverageAmount());
        insurancePolicy.setStartDate(policyUpdateDto.getStartDate());
        insurancePolicy.setEndDate(policyUpdateDto.getEndDate());
        insurancePolicy.setPremium(policyUpdateDto.getPremium());

        Client client = clientRepository.findById(insurancePolicy.getClient().getId()).get();
        List<InsurancePolicy> insurancePolicies =client.getInsurancePolicies();
        insurancePolicies.add(insurancePolicy);
        client.setInsurancePolicies(insurancePolicies);
        clientRepository.save(client);
        insurancePolicyRepository.save(insurancePolicy);
        return insurancePolicy;
    }

    public void deletePolicy(Integer id) {
        insurancePolicyRepository.deleteById(id);
    }
//
//    public String generatePolicyNumber() {
//        UUID uuid = UUID.randomUUID();
//        String policyNumber = uuid.toString().substring(0, 8).replaceAll("-", "").toUpperCase();
//        return policyNumber;
//    }
}
