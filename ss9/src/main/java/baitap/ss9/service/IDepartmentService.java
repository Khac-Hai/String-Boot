package baitap.ss9.service;

import baitap.ss9.dto.request.DepartmentDTO;
import baitap.ss9.dto.response.DepartmentResponse;

public interface IDepartmentService {
    DepartmentResponse createDepartment(DepartmentDTO dto);

}
