package com.internspark.employee_api.service;

import com.internspark.employee_api.dto.EmployeeDTO;
import com.internspark.employee_api.model.Employee;
import com.internspark.employee_api.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    private EmployeeDTO toDTO(Employee employee) {
        return new EmployeeDTO(
            employee.getId(),
            employee.getName(),
            employee.getEmail(),
            employee.getDepartment(),
            employee.getSalary()
        );
    }

    private Employee toEntity(EmployeeDTO dto) {
        return new Employee(
            dto.getId(),
            dto.getName(),
            dto.getEmail(),
            dto.getDepartment(),
            dto.getSalary()
        );
    }

    public List<EmployeeDTO> getAllEmployees() {
        logger.info("Fetching all employees");
        List<EmployeeDTO> employees = employeeRepository.findAll()
            .stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
        logger.info("Total employees found: {}", employees.size());
        return employees;
    }

    public EmployeeDTO getEmployeeById(Long id) {
        logger.info("Fetching employee with id: {}", id);
        Employee employee = employeeRepository.findById(id)
            .orElseThrow(() -> {
                logger.error("Employee not found with id: {}", id);
                return new RuntimeException("Employee not found with id: " + id);
            });
        return toDTO(employee);
    }

    public EmployeeDTO createEmployee(EmployeeDTO dto) {
        logger.info("Creating new employee with email: {}", dto.getEmail());
        if (employeeRepository.existsByEmail(dto.getEmail())) {
            logger.warn("Email already exists: {}", dto.getEmail());
            throw new RuntimeException("Email already exists: " + dto.getEmail());
        }
        Employee saved = employeeRepository.save(toEntity(dto));
        logger.info("Employee created successfully with id: {}", saved.getId());
        return toDTO(saved);
    }

    public EmployeeDTO updateEmployee(Long id, EmployeeDTO dto) {
        logger.info("Updating employee with id: {}", id);
        Employee existing = employeeRepository.findById(id)
            .orElseThrow(() -> {
                logger.error("Employee not found with id: {}", id);
                return new RuntimeException("Employee not found with id: " + id);
            });
        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        existing.setDepartment(dto.getDepartment());
        existing.setSalary(dto.getSalary());
        logger.info("Employee updated successfully with id: {}", id);
        return toDTO(employeeRepository.save(existing));
    }

    public void deleteEmployee(Long id) {
        logger.info("Deleting employee with id: {}", id);
        if (!employeeRepository.existsById(id)) {
            logger.error("Employee not found with id: {}", id);
            throw new RuntimeException("Employee not found with id: " + id);
        }
        employeeRepository.deleteById(id);
        logger.info("Employee deleted successfully with id: {}", id);
    }
}