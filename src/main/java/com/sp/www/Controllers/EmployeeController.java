package com.sp.www.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sp.www.Beans.Employee;
import com.sp.www.Beans.Message;
import com.sp.www.Services.EmployeeService;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService empService;

    @GetMapping("/getAllEmployees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        try {
            List<Employee> employees = empService.getAllEmployees();
            return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<List<Employee>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getEmployee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
        try {
            Employee emp = empService.getEmployeeById(id);
            return new ResponseEntity<Employee>(emp, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getEmployeeByName/{name}")
    public ResponseEntity<Employee> getEmployeeByName(@PathVariable String name) {
        try {
            Employee emp = empService.getEmployeeByName(name);
            return new ResponseEntity<Employee>(emp, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/addNewEmployee")
    public ResponseEntity<Employee> addNewEmployee(@RequestBody Employee emp) {
        try {
            Employee newEmp = empService.addNewEmployee(emp);
            return new ResponseEntity<Employee>(newEmp, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<Employee>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int id,@RequestBody Employee emp) {
        try {
            Employee updatedEmp = empService.updateEmployee(id, emp);
            return new ResponseEntity<Employee>(updatedEmp, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public ResponseEntity<Message> deleteEmployee(@PathVariable int id) {
        try {
            Message m = empService.deleteEmployee(id);
            return new ResponseEntity<Message>(m, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<Message>(HttpStatus.NOT_FOUND);
        }
    }
}
