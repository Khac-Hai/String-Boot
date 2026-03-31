package baitap.ss3.repository;

import baitap.ss3.model.Enrollment;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class EnrollmentRepository {
    private List<Enrollment> enrollments = new ArrayList<>();

    public EnrollmentRepository() {
        enrollments.add(new Enrollment(1L, "Student A", 1L));
        enrollments.add(new Enrollment(2L, "Student B", 2L));
    }

    public List<Enrollment> findAll() { return enrollments; }

    public Optional<Enrollment> findById(Long id) {
        return enrollments.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    public Enrollment create(Enrollment enrollment) {
        enrollments.add(enrollment);
        return enrollment;
    }

    public Enrollment update(Long id, Enrollment enrollment) {
        Enrollment existing = findById(id).orElseThrow(() -> new RuntimeException("Enrollment not found"));
        existing.setStudentName(enrollment.getStudentName());
        existing.setCourseId(enrollment.getCourseId());
        return existing;
    }

    public Enrollment deleteById(Long id) {
        Enrollment existing = findById(id).orElseThrow(() -> new RuntimeException("Enrollment not found"));
        enrollments.remove(existing);
        return existing;
    }
}
