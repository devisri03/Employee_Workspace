package com.ls.EmployeeDetailsApplication.services;

import com.ls.EmployeeDetailsApplication.payloads.EmployeeDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    List<EmployeeDto> getAllEmployeeDetails();
    EmployeeDto getEmployeeById(Long id) throws RuntimeException;
    EmployeeDto updateEmployeeById(Long id,EmployeeDto employeeDto) throws RuntimeException;
    void deleteEmployeeById(Long id) throws RuntimeException;
}
