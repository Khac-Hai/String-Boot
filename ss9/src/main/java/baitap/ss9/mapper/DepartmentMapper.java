package baitap.ss9.mapper;

import org.springframework.stereotype.Component;
import baitap.ss9.dto.request.DepartmentDTO;
import baitap.ss9.dto.response.DepartmentResponse;
import baitap.ss9.model.Department;
@Component
public class DepartmentMapper {
    public static DepartmentResponse mapToResponse(Department entity){
        DepartmentResponse response = new DepartmentResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setDescription(entity.getDescription());
        return response;
    }
    public static Department mapToEntity(DepartmentDTO dto){
        Department department = new Department();
        department.setName(dto.getName());
        department.setDescription(dto.getDescription());
        return department;
    }

}
