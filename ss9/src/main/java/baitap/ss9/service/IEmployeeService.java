package baitap.ss9.service;

import org.springframework.web.multipart.MultipartFile;
import baitap.ss9.dto.request.EmployeeCreateDTO;
import baitap.ss9.dto.response.EmployeeResponse;

public interface IEmployeeService {
    EmployeeResponse createEmployee(EmployeeCreateDTO dto);
    EmployeeResponse updateAvatar(Long id, MultipartFile file);
}
