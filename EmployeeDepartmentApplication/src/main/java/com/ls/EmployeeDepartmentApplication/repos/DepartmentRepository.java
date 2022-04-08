package com.ls.EmployeeDepartmentApplication.repos;

import com.ls.EmployeeDepartmentApplication.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
}
