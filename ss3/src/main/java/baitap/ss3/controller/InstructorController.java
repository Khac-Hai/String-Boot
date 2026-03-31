package baitap.ss3.controller;

import baitap.ss3.model.Instructor;
import baitap.ss3.payload.ApiResponse;
import baitap.ss3.service.InstructorService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/instructors")
public class InstructorController {
    private final InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Instructor>>> getAllInstructors() {
        return ResponseEntity.ok(new ApiResponse<>(true, "Fetched instructors successfully", instructorService.getAllInstructors()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> getInstructorById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new ApiResponse<>(true, "Fetched successfully", instructorService.findInstructorById(id)));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Instructor>> createInstructor(@RequestBody Instructor instructor) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Instructor created", instructorService.createInstructor(instructor)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> updateInstructor(@PathVariable Long id, @RequestBody Instructor instructor) {
        try {
            return ResponseEntity.ok(new ApiResponse<>(true, "Instructor updated", instructorService.updateInstructor(id, instructor)));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> deleteInstructor(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new ApiResponse<>(true, "Instructor deleted", instructorService.deleteInstructorById(id)));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }
}
