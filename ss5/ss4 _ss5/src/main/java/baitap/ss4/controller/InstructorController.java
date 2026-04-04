package baitap.ss4.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import baitap.ss4.service.InstructorService;
import baitap.ss4.entity.Instructor;

import java.util.List;

@RestController
@RequestMapping("/instructors")
public class InstructorController {
    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @PostMapping
    public ResponseEntity<String> createInstructor(@RequestBody Instructor instructor) {
        instructorService.createInstructor(instructor);
        return ResponseEntity.ok("Instructor Created Successfully");
    }

    @GetMapping
    public List<Instructor> getAllInstructors() {
        return instructorService.findAllInstructors();
    }
}
