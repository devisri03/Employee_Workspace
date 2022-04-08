package com.ls.EmployeeDepartmentApplication.integrations;

import com.ls.EmployeeAddressApplication.payloads.AddressDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "EMPLOYEE-ADDRESS-APP")
public interface IAddress {
    @RequestMapping(value = "/address/{id}",method = RequestMethod.GET)
    AddressDto fetchAddressById(@PathVariable Long id) throws RuntimeException;
}
