package com.internspark.employee_api;

import com.internspark.employee_api.dto.EmployeeDTO;
import com.internspark.employee_api.model.Employee;
import com.internspark.employee_api.repository.EmployeeRepository;
import com.internspark.employee_api.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee;
    private EmployeeDTO employeeDTO;

    @BeforeEach
    void setUp() {
        employee = new Employee(1L, "Arjun Sharma",
                "arjun.sharma@example.com", "Engineering", 85000.00);
        employeeDTO = new EmployeeDTO(1L, "Arjun Sharma",
                "arjun.sharma@example.com", "Engineering", 85000.00);
    }

    @Test
    void testGetAllEmployees() {
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee));
        List<EmployeeDTO> result = employeeService.getAllEmployees();
        assertEquals(1, result.size());
        assertEquals("Arjun Sharma", result.get(0).getName());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void testGetEmployeeById_Success() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        EmployeeDTO result = employeeService.getEmployeeById(1L);
        assertNotNull(result);
        assertEquals("Arjun Sharma", result.getName());
        assertEquals("Engineering", result.getDepartment());
    }

    @Test
    void testGetEmployeeById_NotFound() {
        when(employeeRepository.findById(99L)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> employeeService.getEmployeeById(99L));
        assertEquals("Employee not found with id: 99", exception.getMessage());
    }

    @Test
    void testCreateEmployee_Success() {
        when(employeeRepository.existsByEmail(employeeDTO.getEmail())).thenReturn(false);
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        EmployeeDTO result = employeeService.createEmployee(employeeDTO);
        assertNotNull(result);
        assertEquals("Arjun Sharma", result.getName());
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void testCreateEmployee_EmailAlreadyExists() {
        when(employeeRepository.existsByEmail(employeeDTO.getEmail())).thenReturn(true);
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> employeeService.createEmployee(employeeDTO));
        assertTrue(exception.getMessage().contains("Email already exists"));
    }

    @Test
    void testUpdateEmployee_Success() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        EmployeeDTO result = employeeService.updateEmployee(1L, employeeDTO);
        assertNotNull(result);
        assertEquals("Arjun Sharma", result.getName());
    }

    @Test
    void testDeleteEmployee_Success() {
        when(employeeRepository.existsById(1L)).thenReturn(true);
        doNothing().when(employeeRepository).deleteById(1L);
        assertDoesNotThrow(() -> employeeService.deleteEmployee(1L));
        verify(employeeRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteEmployee_NotFound() {
        when(employeeRepository.existsById(99L)).thenReturn(false);
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> employeeService.deleteEmployee(99L));
        assertEquals("Employee not found with id: 99", exception.getMessage());
    }
}