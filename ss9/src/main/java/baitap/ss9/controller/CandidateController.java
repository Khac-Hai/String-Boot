package baitap.ss9.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import baitap.ss9.dto.request.CandidateApplyDTO;
import baitap.ss9.dto.response.ApiResponse;
import baitap.ss9.dto.response.CandidateResponse;
import baitap.ss9.service.ICandidateService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/candidates/apply")
public class CandidateController {
    private final ICandidateService service;
    @PostMapping
    public ResponseEntity<ApiResponse<CandidateResponse>> apply(@ModelAttribute CandidateApplyDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<CandidateResponse>builder()
                        .status("SUCCESS")
                        .message("Tạo ứng viên thành công")
                        .data(service.apply(dto))
                        .build()
        );
    }

}
