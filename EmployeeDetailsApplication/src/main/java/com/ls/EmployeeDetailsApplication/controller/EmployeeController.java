package com.ls.EmployeeDetailsApplication.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.ls.EmployeeDetailsApplication.enums.EMessage;
import com.ls.EmployeeDetailsApplication.payloads.EmployeeDto;
import com.ls.EmployeeDetailsApplication.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @RequestMapping(value = "/employees",method = RequestMethod.POST)
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
        return new ResponseEntity<>(service.createEmployee(employeeDto), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/employees",method = RequestMethod.GET)
    public ResponseEntity<List<EmployeeDto>> fetchAllEmployees(){
        return new ResponseEntity<>(service.getAllEmployeeDetails(),HttpStatus.OK);
    }

    @RequestMapping(value = "/employees/{id}",method = RequestMethod.GET)
    public EntityModel<EmployeeDto> fetchEmployeeById(@PathVariable Long id) throws RuntimeException{
        EmployeeDto employee = service.getEmployeeById(id);
        EntityModel<EmployeeDto> model = EntityModel.of(employee);
        WebMvcLinkBuilder builder = linkTo(methodOn(this.getClass()).fetchAllEmployees());
        model.add(builder.withRel("all-employees"));
        return model;
    }

    @RequestMapping(value = "/employees/{id}",method = RequestMethod.PUT)
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id,@RequestBody EmployeeDto employeeDto) throws RuntimeException{
        return new ResponseEntity<>(service.updateEmployeeById(id, employeeDto),HttpStatus.CREATED);
    }

    @RequestMapping(value = "/employees/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id){
        service.deleteEmployeeById(id);
        return new ResponseEntity<>(String.valueOf(EMessage.EMPLOYEE_DELETED_SUCCESSFULLY),HttpStatus.OK);
    }

    /*@RequestMapping(value = "/employee/{id}",method = RequestMethod.GET)
    public EmployeeDto fetchEmployeesById(@PathVariable Long id) throws RuntimeException{
        return service.getEmployeeById(id);
    }*/

}
