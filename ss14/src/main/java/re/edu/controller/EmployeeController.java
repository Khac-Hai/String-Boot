package re.edu.controller;

import re.edu.model.dto.request.EmployeeCreateDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.service.EmployeeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/Employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<?> getAllEmployee() {
        return new ResponseEntity<>(employeeService.getAllEmployee(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createEmployee(@Valid @ModelAttribute EmployeeCreateDTO req) {
        return new ResponseEntity<>(employeeService.createEmployee(req), HttpStatus.CREATED);
    }
}
