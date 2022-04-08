package com.ls.EmployeeDepartmentApplication.services;

import com.ls.EmployeeDepartmentApplication.entities.Department;
import com.ls.EmployeeDepartmentApplication.enums.EMessage;
import com.ls.EmployeeDepartmentApplication.payloads.DepartmentDto;
import com.ls.EmployeeDepartmentApplication.repos.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository repository;
    private ModelMapper mapper;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Department department = mapToEntity(departmentDto);
        Department savedDep = repository.save(department);
        return mapToDto(savedDep);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments = repository.findAll();
        return departments.stream().map((department -> mapToDto(department))).collect(Collectors.toList());
    }

    @Override
    public DepartmentDto getDepartmentById(Long id) throws RuntimeException {
        Department department = repository.findById(id).orElseThrow(() -> new RuntimeException(String.valueOf(EMessage.ID_NOT_FOUND)));
        return mapToDto(department);
    }

    @Override
    public DepartmentDto updateDepartmentById(Long id, DepartmentDto departmentDto) throws RuntimeException {
        Department department = repository.findById(id).orElseThrow(() -> new RuntimeException(String.valueOf(EMessage.ID_NOT_FOUND)));
        department.setName(departmentDto.getName());
        department.setDescription(departmentDto.getDescription());
        Department savedDep = repository.save(department);
        return mapToDto(savedDep);
    }

    @Override
    public void deleteDepartmentById(Long id) throws RuntimeException {
        Department department = repository.findById(id).orElseThrow(() -> new RuntimeException(String.valueOf(EMessage.ID_NOT_FOUND)));
        repository.delete(department);
    }

    private DepartmentDto mapToDto(Department department){
        return mapper.map(department,DepartmentDto.class);
    }

    private Department mapToEntity(DepartmentDto departmentDto){
        return mapper.map(departmentDto,Department.class);
    }
}
