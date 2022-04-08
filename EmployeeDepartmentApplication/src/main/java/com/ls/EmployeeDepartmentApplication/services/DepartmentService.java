package com.ls.EmployeeDepartmentApplication.services;

import com.ls.EmployeeDepartmentApplication.payloads.DepartmentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {
    DepartmentDto createDepartment(DepartmentDto departmentDto);
    List<DepartmentDto> getAllDepartments();
    DepartmentDto getDepartmentById(Long id) throws RuntimeException;
    DepartmentDto updateDepartmentById(Long id,DepartmentDto departmentDto) throws RuntimeException;
    void deleteDepartmentById(Long id) throws RuntimeException;
}
