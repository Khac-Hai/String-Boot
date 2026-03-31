package baitap.ss3.repository;

import baitap.ss3.model.Course;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class CourseRepository {
    private List<Course> courses = new ArrayList<>();

    public CourseRepository() {
        courses.add(new Course(1L, "Intro Java", "ACTIVE", 1L));
        courses.add(new Course(2L, "Spring Boot", "INACTIVE", 1L));
    }

    public List<Course> findAll() { return courses; }

    public Optional<Course> findById(Long id) {
        return courses.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    public Course create(Course course) {
        courses.add(course);
        return course;
    }

    public Course update(Long id, Course course) {
        Course existing = findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
        existing.setTitle(course.getTitle());
        existing.setStatus(course.getStatus());
        return existing;
    }

    public Course deleteById(Long id) {
        Course existing = findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
        courses.remove(existing);
        return existing;
    }
}
