package baitap.ss3.service;

import baitap.ss3.model.Enrollment;
import baitap.ss3.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;

    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public List<Enrollment> getAllEnrollments() { return enrollmentRepository.findAll(); }

    public Enrollment findEnrollmentById(Long id) {
        return enrollmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Enrollment not found"));
    }

    public Enrollment createEnrollment(Enrollment enrollment) { return enrollmentRepository.create(enrollment); }

    public Enrollment updateEnrollment(Long id, Enrollment enrollment) { return enrollmentRepository.update(id, enrollment); }

    public Enrollment deleteEnrollmentById(Long id) { return enrollmentRepository.deleteById(id); }
}
