package com.sp.www.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.www.Beans.Employee;
import com.sp.www.Beans.Message;
import com.sp.www.Repositories.EmployeeRepo;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepo repo;
    public List<Employee> getAllEmployees() {
        return repo.findAll();
    }

    public Employee getEmployeeById(int id) {
        return repo.findById(id).orElse(null);
    }

    public Employee getEmployeeByName(String name) {
        return repo.getByEmpName(name);
    }

    public Employee addNewEmployee(Employee emp) {
        repo.save(emp);
        return emp;
    }

    public Employee updateEmployee(int id, Employee emp) {
        Employee existingEmp = repo.findById(id).orElse(null);
        if (existingEmp != null) {
            emp.setEmpId(id);
            return repo.save(emp);
        }
        return null;
    }
    public Message deleteEmployee(int id) {
        Employee existingEmp = repo.findById(id).orElse(null);

        if (existingEmp != null) {
            repo.delete(existingEmp);

            Message m = new Message();
            m.setMsg("Employee with Id: " + id + " removed or deleted successfully");

            return m;
        }

        return null;
    }
}