package re.edu.service;

import re.edu.model.dto.request.EmployeeCreateDTO;
import re.edu.model.entity.Employee;
import re.edu.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    public Employee createEmployee(EmployeeCreateDTO req) {
        try {
            MultipartFile file = req.getAvatar();
            String fileName = null;
            if (file != null && !file.isEmpty()) {
                String uploadDir = "D:\\data\\Code\\SpringBoot\\FileDatabase";

                fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

                Path path = Paths.get(uploadDir, fileName);
                Files.write(path, file.getBytes());
            }

            Employee emp = new Employee();
            emp.setFullName(req.getFullName());
            emp.setSalary(req.getSalary());
            emp.setUrlAvatar(fileName);

            return employeeRepository.save(emp);

        } catch (IOException e) {
            throw new RuntimeException("Upload file thất bại");
        }
    }
}
