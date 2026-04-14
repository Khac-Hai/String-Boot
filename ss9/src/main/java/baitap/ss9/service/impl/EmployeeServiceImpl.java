package baitap.ss9.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import baitap.ss9.dto.request.EmployeeCreateDTO;
import baitap.ss9.dto.response.EmployeeResponse;
import baitap.ss9.exception.DuplicateResourceException;
import baitap.ss9.exception.ResourceNotFoundException;
import baitap.ss9.mapper.EmployeeMapper;
import baitap.ss9.model.Department;
import baitap.ss9.model.Employee;
import baitap.ss9.repositories.IDepartmentRepository;
import baitap.ss9.repositories.IEmployeeRepository;
import baitap.ss9.service.IEmployeeService;
import baitap.ss9.validation.FileValidator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements IEmployeeService {

    private final IEmployeeRepository employeeRepository;
    private final IDepartmentRepository departmentRepository;
    @Override
    public EmployeeResponse createEmployee(EmployeeCreateDTO dto) {
        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Phòng ban không tồn tại"));

        Employee employee = EmployeeMapper.mapToEntity(dto, department);

        if(employeeRepository.existsByPhone(dto.getPhone())) {
            throw new DuplicateResourceException("Số điện thoai đã tồn tại");
        }
        if (employeeRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateResourceException("Email đã tồn tại");
        }
        employee =  employeeRepository.save(employee);
        return EmployeeMapper.mapToResponse(employee);
    }
    @Override
    public EmployeeResponse updateAvatar(Long id, MultipartFile file) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nhân viên không tồn tại"));

        FileValidator.validateImage(file);

        try {
            String uploadDir = "uploads/";
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            String originalName = file.getOriginalFilename();
            String newFileName = System.currentTimeMillis() + "_" + originalName;

            Path path = Paths.get(uploadDir + newFileName);
            Files.write(path, file.getBytes());

            String fileUrl = "/uploads/" + newFileName;

            employee.setAvatarUrl(fileUrl);
            employeeRepository.save(employee);

            return EmployeeMapper.mapToResponse(employee);

        } catch (IOException e) {
            throw new RuntimeException("Upload file thất bại");
        }
    }
}