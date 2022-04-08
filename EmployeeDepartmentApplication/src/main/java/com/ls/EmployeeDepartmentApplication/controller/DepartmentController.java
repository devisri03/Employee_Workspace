package com.ls.EmployeeDepartmentApplication.controller;

import com.ls.EmployeeDepartmentApplication.enums.EMessage;
import com.ls.EmployeeDepartmentApplication.payloads.DepartmentDto;
import com.ls.EmployeeDepartmentApplication.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @RequestMapping(value = "/departments",method = RequestMethod.POST)
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto) {
        return new ResponseEntity<>(service.createDepartment(departmentDto), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/departments",method = RequestMethod.GET)
    public ResponseEntity<List<DepartmentDto>> fetchAllDepartments(){
        return new ResponseEntity<>(service.getAllDepartments(),HttpStatus.OK);
    }

    @RequestMapping(value = "/departments/{id}",method = RequestMethod.GET)
    public DepartmentDto fetchDepartmentById(@PathVariable Long id) throws RuntimeException{
        return service.getDepartmentById(id);
    }

    @RequestMapping(value = "/departments/{id}",method = RequestMethod.PUT)
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable Long id,@RequestBody DepartmentDto departmentDto) throws RuntimeException{
        return new ResponseEntity<>(service.updateDepartmentById(id, departmentDto),HttpStatus.CREATED);
    }

    @RequestMapping(value = "/departments/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id) throws RuntimeException{
        service.deleteDepartmentById(id);
        return new ResponseEntity<>(String.valueOf(EMessage.DEPARTMENT_DELETED_SUCCESSFULLY),HttpStatus.OK);
    }

}
