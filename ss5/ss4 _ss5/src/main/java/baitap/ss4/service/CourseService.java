package baitap.ss4.service;

import org.springframework.stereotype.Service;
import baitap.ss4.repository.CourseRepository;
import baitap.ss4.repository.InstructorRepository;
import baitap.ss4.dto.*;
import baitap.ss4.entity.Course;
import baitap.ss4.entity.CourseStatus;
import baitap.ss4.entity.Instructor;
import org.springframework.data.domain.*;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;

    public CourseService(CourseRepository courseRepository,
                         InstructorRepository instructorRepository) {
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
    }

    public void createCourse(CourseCreateRequest req) {

        Instructor instructor = instructorRepository.findById(req.getInstructorId())
                .orElseThrow(() -> new RuntimeException("Instructor not found"));

        Course course = new Course();
        course.setTitle(req.getTitle());
        course.setStatus(req.getStatus());
        course.setInstructor(instructor);

        courseRepository.save(course);
    }

    public void updateCourse(Long id, CourseUpdateRequest req) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Instructor instructor = instructorRepository.findById(req.getInstructorId())
                .orElseThrow(() -> new RuntimeException("Instructor not found"));

        course.setTitle(req.getTitle());
        course.setStatus(req.getStatus());
        course.setInstructor(instructor);

        courseRepository.save(course);
    }

    public List<CourseResponse> getAllCourses() {

        List<Course> courses = courseRepository.findAll();

        return courses.stream().map(course -> {

            CourseInstructorResponse instructorDTO =
                    new CourseInstructorResponse(
                            course.getInstructor().getId(),
                            course.getInstructor().getName()
                    );

            return new CourseResponse(
                    course.getId(),
                    course.getTitle(),
                    course.getStatus(),
                    instructorDTO
            );

        }).toList();
    }

    public PageResponse<CourseResponse> getPagedCourses(
            int page, int size, String sortBy, Sort.Direction direction) {

        if (page < 0) page = 0;

        if (sortBy == null || sortBy.isBlank()) {
            sortBy = "id";
        }

        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Course> coursePage = courseRepository.findAll(pageable);

        Page<CourseResponse> dtoPage = coursePage.map(course -> {
            CourseInstructorResponse instructorDTO =
                    new CourseInstructorResponse(
                            course.getInstructor().getId(),
                            course.getInstructor().getName()
                    );

            return new CourseResponse(
                    course.getId(),
                    course.getTitle(),
                    course.getStatus(),
                    instructorDTO
            );
        });

        return new PageResponse<>(
                dtoPage.getContent(),
                dtoPage.getNumber(),
                dtoPage.getSize(),
                dtoPage.getTotalElements(),
                dtoPage.getTotalPages(),
                dtoPage.isLast()
        );
    }

    public PageResponse<CourseResponse> getPagedCoursesByStatus(
            int page,
            int size,
            String sortBy,
            Sort.Direction direction,
            CourseStatus status) {

        if (page < 0) page = 0;

        if (sortBy == null || sortBy.isBlank()) {
            sortBy = "id";
        }

        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Course> coursePage =
                courseRepository.findAllByStatus(status, pageable);


        Page<CourseResponse> dtoPage = coursePage.map(course -> {
            CourseInstructorResponse instructorDTO =
                    new CourseInstructorResponse(
                            course.getInstructor().getId(),
                            course.getInstructor().getName()
                    );

            return new CourseResponse(
                    course.getId(),
                    course.getTitle(),
                    course.getStatus(),
                    instructorDTO
            );
        });

        return new PageResponse<>(
                dtoPage.getContent(),
                dtoPage.getNumber(),
                dtoPage.getSize(),
                dtoPage.getTotalElements(),
                dtoPage.getTotalPages(),
                dtoPage.isLast()
        );
    }

    public Page<CourseResponseV2> getPagedCoursesV2(
            int page, int size,
            String sortBy,
            Sort.Direction direction,
            CourseStatus status
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        return courseRepository.findAllByStatusV2(status, pageable);
    }
}