package baitap.ss9.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import baitap.ss9.model.Employee;

public interface IEmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByPhone(String phone);
    boolean existsByEmail(String email);
}
