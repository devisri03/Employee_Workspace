package com.ls.EmployeeDetailsApplication.services;

import com.ls.EmployeeDetailsApplication.entities.Employee;
import com.ls.EmployeeDetailsApplication.enums.EMessage;
import com.ls.EmployeeDetailsApplication.integrations.IAddress;
import com.ls.EmployeeDetailsApplication.integrations.IDepartment;
import com.ls.EmployeeDetailsApplication.payloads.EmployeeDto;
import com.ls.EmployeeDetailsApplication.repos.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository repository;
    private ModelMapper mapper;

    @Autowired
    private IDepartment department;

    @Autowired
    private IAddress address;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = mapToEntity(employeeDto);
        Employee savedEmp = repository.save(employee);
        EmployeeDto employeeDtos = new EmployeeDto(savedEmp);
        employeeDtos.setDepartment(department.fetchDepartmentById(savedEmp.getId()));
        employeeDtos.setAddress(address.fetchAddressById(savedEmp.getId()));
        return employeeDtos;
    }

    @Override
    public List<EmployeeDto> getAllEmployeeDetails() {
        List<Employee> employees = repository.findAll();
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        for(Employee employee:employees){
            EmployeeDto employeeDto = new EmployeeDto(employee);
            employeeDto.setDepartment(department.fetchDepartmentById(employee.getId()));
            employeeDto.setAddress(address.fetchAddressById(employee.getId()));
            employeeDtos.add(employeeDto);
        }
        return employeeDtos;
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) throws RuntimeException {
        Employee employee = repository.findById(id).orElseThrow(() -> new RuntimeException(String.valueOf(EMessage.ID_NOT_FOUND)));
        EmployeeDto employeeDto = new EmployeeDto(employee);
        employeeDto.setDepartment(department.fetchDepartmentById(employee.getId()));
        employeeDto.setAddress(address.fetchAddressById(employee.getId()));
        return employeeDto;
    }

    @Override
    public EmployeeDto updateEmployeeById(Long id, EmployeeDto employeeDto) throws RuntimeException {
        Employee employee = repository.findById(id).orElseThrow(() -> new RuntimeException(String.valueOf(EMessage.ID_NOT_FOUND)));
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setAge(employeeDto.getAge());
        employee.setPhone(employeeDto.getPhone());
        employee.setEmail(employeeDto.getEmail());
        Employee savedEmp = repository.save(employee);
        return mapToDto(savedEmp);
    }

    @Override
    public void deleteEmployeeById(Long id) throws RuntimeException {
        Employee employee = repository.findById(id).orElseThrow(() -> new RuntimeException(String.valueOf(EMessage.ID_NOT_FOUND)));
        repository.delete(employee);
    }

    private EmployeeDto mapToDto(Employee employee){
        return mapper.map(employee,EmployeeDto.class);
    }

    private Employee mapToEntity(EmployeeDto employeeDto){
        return mapper.map(employeeDto,Employee.class);
    }
}
