package baitap.ss3.service;

import baitap.ss3.model.Instructor;
import baitap.ss3.repository.InstructorRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class InstructorService {
    private final InstructorRepository instructorRepository;

    @Autowired
    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    public List<Instructor> getAllInstructors() { return instructorRepository.findAll(); }

    public Instructor findInstructorById(Long id) {
        return instructorRepository.findById(id).orElseThrow(() -> new RuntimeException("Instructor not found"));
    }

    public Instructor createInstructor(Instructor instructor) { return instructorRepository.create(instructor); }

    public Instructor updateInstructor(Long id, Instructor instructor) { return instructorRepository.update(id, instructor); }

    public Instructor deleteInstructorById(Long id) { return instructorRepository.deleteById(id); }
}
