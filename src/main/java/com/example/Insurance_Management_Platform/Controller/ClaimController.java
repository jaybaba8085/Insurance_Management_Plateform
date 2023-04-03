package com.example.Insurance_Management_Platform.Controller;

import com.example.Insurance_Management_Platform.Data.Claim;
import com.example.Insurance_Management_Platform.Dto.ClaimDto;
import com.example.Insurance_Management_Platform.Dto.ClaimUpdateDto;
import com.example.Insurance_Management_Platform.Repository.ClaimRepository;
import com.example.Insurance_Management_Platform.Service.ClaimService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/claims")
public class ClaimController {

    private final ClaimService claimService;
    private final ClaimRepository claimRepository;

    public ClaimController(ClaimService claimService, ClaimRepository claimRepository) {
        this.claimService = claimService;
        this.claimRepository = claimRepository;
    }

    // 1. GET /api/claims: Fetch all claims.
    @GetMapping
    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }

    // 2. GET /api/claims/{id}: Fetch a specific claim by ID.
    @GetMapping("/{id}")
    public Optional<Claim> getClaimById(@PathVariable Integer id) {
        return claimService.findById(id);
    }

    // 3. POST /api/claims: Create a new claim.
    @PostMapping
    public Claim createClaim(@RequestBody ClaimDto claimDto) {
        return claimService.createClaim(claimDto);
    }

    // 4. PUT /api/claims/{id}: Update a claim's information.
    @PutMapping("/{id}")
    public String updateClaim(@PathVariable Integer id, @RequestBody ClaimUpdateDto updatedClaim) {
       claimService.updateClaim(id, updatedClaim);
       return "Claim Upadated";
    }

    // 5. DELETE /api/claims/{id}: Delete a claim.
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClaim(@PathVariable Integer id) {
        return claimService.deleteClaim(id);
    }
}
