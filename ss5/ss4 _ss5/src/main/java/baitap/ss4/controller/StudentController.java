package baitap.ss4.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import baitap.ss4.service.StudentService;
import baitap.ss4.entity.Student;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<String> createStudent(@RequestBody Student student) {
        studentService.createStudent(student);
        return ResponseEntity.ok("Student Created Successfully");
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.findAllStudents();
    }
}
