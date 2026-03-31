package baitap.ss3.repository;

import baitap.ss3.model.Instructor;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class InstructorRepository {
    private List<Instructor> instructors = new ArrayList<>();

    public InstructorRepository() {
        instructors.add(new Instructor(1L, "Nguyen Van A", "a@example.com"));
        instructors.add(new Instructor(2L, "Tran Thi B", "b@example.com"));
    }

    public List<Instructor> findAll() { return instructors; }

    public Optional<Instructor> findById(Long id) {
        return instructors.stream().filter(i -> i.getId().equals(id)).findFirst();
    }

    public Instructor create(Instructor instructor) {
        instructors.add(instructor);
        return instructor;
    }

    public Instructor update(Long id, Instructor instructor) {
        Instructor existing = findById(id).orElseThrow(() -> new RuntimeException("Instructor not found"));
        existing.setName(instructor.getName());
        existing.setEmail(instructor.getEmail());
        return existing;
    }

    public Instructor deleteById(Long id) {
        Instructor existing = findById(id).orElseThrow(() -> new RuntimeException("Instructor not found"));
        instructors.remove(existing);
        return existing;
    }
}
