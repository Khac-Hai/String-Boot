package baitap.ss7.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import baitap.ss7.dto.ApiResponse;
import baitap.ss7.dto.CandidateCreateDTO;
import baitap.ss7.dto.CandidateUpdateDTO;
import baitap.ss7.entity.Candidate;
import baitap.ss7.service.CandidateService;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {
    private final CandidateService service;
    private final CandidateService candidateService;

    public CandidateController(CandidateService service, CandidateService candidateService) {
        this.service = service;
        this.candidateService = candidateService;
    }
    @PostMapping
    public ResponseEntity<?> createCandidate(@Valid @RequestBody CandidateCreateDTO dto) {
        Candidate createdCandidate = service.createCandidate(dto);
        return ResponseEntity.status(201).body(createdCandidate);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Candidate>> updateCandidate
            (@PathVariable int id, @Valid @ModelAttribute CandidateUpdateDTO dto) {
        Candidate candidate = candidateService.updateCandidate(id, dto);
        ApiResponse<Candidate> response = new ApiResponse<>(
                "200",
                "Cập nhập thành công",
                candidate
        );
        return ResponseEntity.ok(response);
    }
}
