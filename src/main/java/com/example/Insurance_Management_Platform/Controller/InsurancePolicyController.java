package com.example.Insurance_Management_Platform.Controller;

import com.example.Insurance_Management_Platform.Data.InsurancePolicy;
import com.example.Insurance_Management_Platform.Dto.PolicyRequestDto;
import com.example.Insurance_Management_Platform.Dto.PolicyUpdateDto;
import com.example.Insurance_Management_Platform.Service.InsurancePolicyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/policies")
public class InsurancePolicyController {

    private final InsurancePolicyService insuranceService;

    public InsurancePolicyController(InsurancePolicyService insuranceService) {
        this.insuranceService = insuranceService;
    }

    // 1. GET /api/policies: Fetch all insurance policies.
    @GetMapping
    public List<InsurancePolicy> getAllPolicies() {
        return insuranceService.getAllPolicies();
    }

    // 2. GET /api/policies/{id}: Fetch a specific insurance policy by ID.
    @GetMapping("/{id}")
    public Optional<InsurancePolicy> getPolicyById(@PathVariable Integer id) {
        return insuranceService.getPolicyById(id);
    }

    // 3. POST /api/policies: Create a new insurance policy.
    @PostMapping
    public InsurancePolicy createPolicy(@RequestBody PolicyRequestDto policyDto) {
        return insuranceService.createPolicy(policyDto);
    }

    // 4. PUT /api/policies/{id}: Update an insurance policy.
    @PutMapping("/{id}")
    public InsurancePolicy updatePolicy(@PathVariable Integer id, @RequestBody PolicyUpdateDto policy) {
        return insuranceService.updatePolicy(id, policy);
    }

    // 5. DELETE /api/policies/{id}: Delete an insurance policy.
    @DeleteMapping("/{id}")
    public void deletePolicy(@PathVariable Integer id) {
        insuranceService.deletePolicy(id);
    }
}
