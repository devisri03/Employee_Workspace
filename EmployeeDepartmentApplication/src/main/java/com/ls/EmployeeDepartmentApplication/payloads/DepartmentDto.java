package com.ls.EmployeeDepartmentApplication.payloads;

import com.ls.EmployeeAddressApplication.payloads.AddressDto;
import com.ls.EmployeeDepartmentApplication.entities.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {
    private Long id;
    private String name;
    private String description;

    /*public DepartmentDto(Department department) {
        this.id = department.getId();
        this.name = department.getName();
        this.description = department.getDescription();
    }*/
}
