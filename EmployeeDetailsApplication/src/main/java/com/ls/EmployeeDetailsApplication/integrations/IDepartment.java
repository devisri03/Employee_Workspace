package com.ls.EmployeeDetailsApplication.integrations;

import com.ls.EmployeeDepartmentApplication.payloads.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "EMPLOYEE-DEPARTMENT-APP")
public interface IDepartment {
    @RequestMapping(value = "/departments/{id}",method = RequestMethod.GET)
    DepartmentDto fetchDepartmentById(@PathVariable Long id) throws RuntimeException;
}
