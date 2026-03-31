package baitap.ss3.service;

import baitap.ss3.model.Course;
import baitap.ss3.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() { return courseRepository.findAll(); }

    public Course findCourseById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
    }

    public Course createCourse(Course course) { return courseRepository.create(course); }

    public Course updateCourse(Long id, Course course) { return courseRepository.update(id, course); }

    public Course deleteCourseById(Long id) { return courseRepository.deleteById(id); }
}
