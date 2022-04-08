package com.ls.EmployeeDetailsApplication.payloads;

import com.ls.EmployeeAddressApplication.payloads.AddressDto;
import com.ls.EmployeeDepartmentApplication.payloads.DepartmentDto;
import com.ls.EmployeeDetailsApplication.entities.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String phone;
    private String email;

    public EmployeeDto(Employee employee) {
        this.id = employee.getId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.age = employee.getAge();
        this.phone = employee.getPhone();
        this.email = employee.getEmail();
    }

    private DepartmentDto department;
    private AddressDto address;
}
