package com.internspark.employee_api.service;

import com.internspark.employee_api.dto.EmployeeDTO;
import com.internspark.employee_api.model.Employee;
import com.internspark.employee_api.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Convert Entity to DTO
    private EmployeeDTO toDTO(Employee employee) {
        return new EmployeeDTO(
            employee.getId(),
            employee.getName(),
            employee.getEmail(),
            employee.getDepartment(),
            employee.getSalary()
        );
    }

    // Convert DTO to Entity
    private Employee toEntity(EmployeeDTO dto) {
        return new Employee(
            dto.getId(),
            dto.getName(),
            dto.getEmail(),
            dto.getDepartment(),
            dto.getSalary()
        );
    }

    // GET ALL
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll()
            .stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    // GET BY ID
    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        return toDTO(employee);
    }

    // CREATE
    public EmployeeDTO createEmployee(EmployeeDTO dto) {
        if (employeeRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists: " + dto.getEmail());
        }
        Employee saved = employeeRepository.save(toEntity(dto));
        return toDTO(saved);
    }

    // UPDATE
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO dto) {
        Employee existing = employeeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        existing.setDepartment(dto.getDepartment());
        existing.setSalary(dto.getSalary());

        return toDTO(employeeRepository.save(existing));
    }

    // DELETE
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Employee not found with id: " + id);
        }
        employeeRepository.deleteById(id);
    }
}