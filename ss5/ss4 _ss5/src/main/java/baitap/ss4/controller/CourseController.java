package baitap.ss4.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import baitap.ss4.service.CourseService;
import baitap.ss4.dto.CourseCreateRequest;
import baitap.ss4.dto.CourseUpdateRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import baitap.ss4.dto.*;
import baitap.ss4.entity.*;

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
    public ResponseEntity<ApiResponse<PageResponse<CourseResponseV2>>> getCoursesV2(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(defaultValue = "DESC") Sort.Direction direction,
            @RequestParam(defaultValue = "Registered") CourseStatus status
    ) {

        Page<CourseResponseV2> dtoPage =
                courseService.getPagedCoursesV2(page, size, sortBy, direction, status);

        PageResponse<CourseResponseV2> response = new PageResponse<>(
                dtoPage.getContent(),
                dtoPage.getNumber(),
                dtoPage.getSize(),
                dtoPage.getTotalElements(),
                dtoPage.getTotalPages(),
                dtoPage.isLast()
        );

        return ResponseEntity.ok(
                new ApiResponse<>(true,"Success", response)
        );
    }

}
