package baitap.ss4.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import baitap.ss4.repository.StudentRepository;
import baitap.ss4.repository.CourseRepository;
import baitap.ss4.repository.StudentEnrollmentRepository;
import baitap.ss4.entity.Student;
import baitap.ss4.entity.Course;
import baitap.ss4.entity.StudentEnrollment;

import java.util.List;

@Service
public class StudentEnrollmentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentEnrollmentRepository enrollmentRepository;

    public void enrollStudent(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        StudentEnrollment enrollment = new StudentEnrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollmentRepository.save(enrollment);
    }

    public List<StudentEnrollment> findAllEnrollments() {
        return enrollmentRepository.findAll();
    }
}
