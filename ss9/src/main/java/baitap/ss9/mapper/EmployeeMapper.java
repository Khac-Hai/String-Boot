package baitap.ss9.mapper;
import org.springframework.stereotype.Component;
import baitap.ss9.dto.request.EmployeeCreateDTO;
import baitap.ss9.dto.response.EmployeeResponse;
import baitap.ss9.model.Department;
import baitap.ss9.model.Employee;

@Component
public class EmployeeMapper {
    public static EmployeeResponse mapToResponse(Employee employee) {
        EmployeeResponse response = new EmployeeResponse();
        response.setId(employee.getId());
        response.setFullName(employee.getFullName());
        response.setEmail(employee.getEmail());
        response.setPhone(employee.getPhone());
        response.setSalary(employee.getSalary());
        response.setDepartmentId(employee.getDepartment().getId());
        return response;

    }

    public static Employee mapToEntity(EmployeeCreateDTO dto, Department department){
        Employee employee = new Employee();
        employee.setFullName(dto.getFullName());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setSalary(dto.getSalary());
        employee.setDepartment(department);
        return employee;
    }
}
