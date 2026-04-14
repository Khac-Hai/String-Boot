package baitap.ss9.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import baitap.ss9.dto.request.EmployeeCreateDTO;
import baitap.ss9.dto.response.ApiResponse;
import baitap.ss9.dto.response.EmployeeResponse;
import baitap.ss9.service.IEmployeeService;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final IEmployeeService service;

    @PostMapping
    public ResponseEntity<ApiResponse<EmployeeResponse>> createEmployee(
            @Valid @RequestBody EmployeeCreateDTO dto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<EmployeeResponse>builder()
                        .status("SUCCESS")
                        .message("Thêm nhân viên thành công")
                        .data(service.createEmployee(dto))
                        .build()
        );
    }
    @PutMapping("/{id}/avatar")
    public ResponseEntity<ApiResponse<EmployeeResponse>> updateAvatar(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file
    ) {
        EmployeeResponse response = service.updateAvatar(id, file);

        return ResponseEntity.ok(
                new ApiResponse<>("SUCCESS", "Upload avatar thành công", response)
        );
    }
}
