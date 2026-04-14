package baitap.ss9.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import baitap.ss9.dto.request.DepartmentDTO;
import baitap.ss9.dto.response.ApiResponse;
import baitap.ss9.dto.response.DepartmentResponse;
import baitap.ss9.service.IDepartmentService;

@RestController
@RequestMapping("/api/v1/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final IDepartmentService service;
    @PostMapping
    public ResponseEntity<ApiResponse<DepartmentResponse>> createDepartment(@Valid @RequestBody DepartmentDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<DepartmentResponse>builder()
                        .status("SUCCESS")
                        .message("Tạo phòng ban thành công")
                        .data(service.createDepartment(dto))
                        .build()
        );
    }
}
