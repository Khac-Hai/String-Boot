package baitap.ss9.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import baitap.ss9.model.Department;

public interface IDepartmentRepository extends JpaRepository<Department, Long> {
    boolean existsByName(String name);
}
