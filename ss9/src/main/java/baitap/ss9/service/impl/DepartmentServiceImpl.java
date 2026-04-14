package baitap.ss9.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import baitap.ss9.dto.request.DepartmentDTO;
import baitap.ss9.dto.response.DepartmentResponse;
import baitap.ss9.mapper.DepartmentMapper;
import baitap.ss9.model.Department;
import baitap.ss9.repositories.IDepartmentRepository;
import baitap.ss9.service.IDepartmentService;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements IDepartmentService {
    private final IDepartmentRepository departmentRepository;


    @Override
    public DepartmentResponse createDepartment(DepartmentDTO dto) {
        Department entity = DepartmentMapper.mapToEntity(dto);

        if (departmentRepository.existsByName(dto.getName())) {
            throw new RuntimeException("Department tên " + dto.getName() + " đã tồn tại");
        }

        entity = departmentRepository.save(entity);
        return DepartmentMapper.mapToResponse(entity);
    }
}
