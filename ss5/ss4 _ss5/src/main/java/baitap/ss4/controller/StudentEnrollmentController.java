package baitap.ss4.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import baitap.ss4.service.StudentEnrollmentService;
import baitap.ss4.entity.StudentEnrollment;

import java.util.List;

@RestController
@RequestMapping("/students-enrollments")
public class StudentEnrollmentController {
    private final StudentEnrollmentService enrollmentService;

    public StudentEnrollmentController(StudentEnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    public ResponseEntity<String> enrollStudent(@RequestParam Long studentId, @RequestParam Long courseId) {
        enrollmentService.enrollStudent(studentId, courseId);
        return ResponseEntity.ok("Student Enrolled Successfully");
    }

    @GetMapping
    public List<StudentEnrollment> getAllEnrollments() {
        return enrollmentService.findAllEnrollments();
    }
}
