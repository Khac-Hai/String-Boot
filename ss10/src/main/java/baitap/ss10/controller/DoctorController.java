package baitap.ss10.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import baitap.ss10.dto.request.DoctorRequest;
import baitap.ss10.dto.response.ApiResponse;
import baitap.ss10.dto.response.DoctorResponse;
import baitap.ss10.dto.response.Meta;
import baitap.ss10.service.IDoctorService;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/doctors")
public class DoctorController {

    private final IDoctorService doctorService;

    @Operation(summary = "Lấy danh sách toàn bộ bác sĩ (có phân trang)")
    @GetMapping
    public ResponseEntity<ApiResponse<List<DoctorResponse>>> getAllDoctors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<DoctorResponse> doctorPage = doctorService.getAllDoctors(page, size);

        Meta meta = new Meta(
                doctorPage.getTotalElements(),
                doctorPage.getNumber(),
                doctorPage.getTotalPages()
        );

        ApiResponse<List<DoctorResponse>> res = new ApiResponse<>(
                "success",
                200,
                doctorPage.getContent(),
                meta
        );

        return ResponseEntity.ok(res);
    }

    @Operation(summary = "Lấy chi tiết bác sĩ theo ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DoctorResponse>> getDoctorById(@PathVariable Long id) {

        ApiResponse<DoctorResponse> res = new ApiResponse<>(
                "success",
                200,
                doctorService.getDoctorById(id),
                null
        );

        return ResponseEntity.ok(res);
    }

    @Operation(summary = "Tạo mới bác sĩ")
    @PostMapping
    public ResponseEntity<ApiResponse<DoctorResponse>> createDoctor(
            @Valid @RequestBody DoctorRequest request
    ) {

        ApiResponse<DoctorResponse> res = new ApiResponse<>(
                "success",
                201,
                doctorService.createDoctor(request),
                null
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @Operation(summary = "Cập nhật bác sĩ")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DoctorResponse>> updateDoctor(
            @PathVariable Long id,
            @Valid @RequestBody DoctorRequest request
    ) {

        ApiResponse<DoctorResponse> res = new ApiResponse<>(
                "success",
                200,
                doctorService.updateDoctor(id, request),
                null
        );

        return ResponseEntity.ok(res);
    }

    @Operation(summary = "Xóa bác sĩ")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteDoctor(@PathVariable Long id) {

        doctorService.deleteDoctor(id);

        ApiResponse<Void> res = new ApiResponse<>(
                "success",
                200,
                null,
                null
        );

        return ResponseEntity.ok(res);
    }
}
