package com.sp.www.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sp.www.Beans.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
    Employee getByEmpName(String empName);

}