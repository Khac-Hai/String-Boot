package baitap.ss4.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import baitap.ss4.service.CourseService;
import baitap.ss4.dto.CourseCreateRequest;
import baitap.ss4.dto.CourseUpdateRequest;
import baitap.ss4.entity.Course;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<String> createCourse(@RequestBody CourseCreateRequest req) {
        courseService.createCourse(req);
        return ResponseEntity.ok("Course Created Successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCourse(@PathVariable Long id, @RequestBody CourseUpdateRequest req) {
        courseService.updateCourse(id, req);
        return ResponseEntity.ok("Course Updated Successfully");
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.findAllCourses();
    }
}
