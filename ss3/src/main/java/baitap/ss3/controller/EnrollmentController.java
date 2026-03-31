package baitap.ss3.controller;

import baitap.ss3.model.Enrollment;
import baitap.ss3.payload.ApiResponse;
import baitap.ss3.service.EnrollmentService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Enrollment>>> getAllEnrollments() {
        return ResponseEntity.ok(new ApiResponse<>(true, "Fetched enrollments successfully", enrollmentService.getAllEnrollments()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Enrollment>> getEnrollmentById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new ApiResponse<>(true, "Fetched successfully", enrollmentService.findEnrollmentById(id)));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Enrollment>> createEnrollment(@RequestBody Enrollment enrollment) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Enrollment created", enrollmentService.createEnrollment(enrollment)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Enrollment>> updateEnrollment(@PathVariable Long id, @RequestBody Enrollment enrollment) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Enrollment updated", enrollmentService.updateEnrollment(id, enrollment))
            );
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Enrollment>> deleteEnrollment(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Enrollment deleted", enrollmentService.deleteEnrollmentById(id))
            );
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }
}