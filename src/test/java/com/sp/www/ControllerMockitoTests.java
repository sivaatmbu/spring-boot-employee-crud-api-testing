package com.sp.www;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sp.www.Beans.Employee;
import com.sp.www.Beans.Message;
import com.sp.www.Controllers.EmployeeController;
import com.sp.www.Services.EmployeeService;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
public class ControllerMockitoTests {

    @Mock
    EmployeeService empService;

    @InjectMocks
    EmployeeController empController;

    Employee emp;
    List<Employee> employees;

    @Test
    @Order(1)
    public void test_getAllEmployees() {
        employees = new ArrayList<Employee>();
        employees.add(new Employee(129, "Siva Praneesh", "Developer", 97000));
        employees.add(new Employee(1, "Ajay", "Tester", 72000));
        employees.add(new Employee(4, "Amrutha", "HR", 80000));
        employees.add(new Employee(3, "Narasimha", "Developer", 98000));

        when(empService.getAllEmployees()).thenReturn(employees);

        ResponseEntity<List<Employee>> res = empController.getAllEmployees();

        assertEquals(HttpStatus.OK, res.getStatusCode());
        assertEquals(4, res.getBody().size());
    }

    @Test
    @Order(2)
    public void test_getEmployeeById() {

        Employee emp = new Employee(129, "Siva Praneesh", "Developer", 97000);
        int id = 129;
        
        when(empService.getEmployeeById(id)).thenReturn(emp);

        ResponseEntity<Employee> findEmp = empController.getEmployeeById(id);

        assertEquals(HttpStatus.OK, findEmp.getStatusCode());
        assertEquals(id, findEmp.getBody().getEmpId());
    }

    @Test
    @Order(3)
    public void test_getEmployeeByName() {

        String name = "Siva Praneesh";
        Employee emp = new Employee(129, "Siva Praneesh", "Developer", 97000);

        when(empService.getEmployeeByName(name)).thenReturn(emp);

        ResponseEntity<Employee> find = empController.getEmployeeByName(name);

        assertEquals(HttpStatus.OK, find.getStatusCode());
        assertEquals(name, find.getBody().getEmpName());
    }

    @Test
    @Order(4)
    public void test_addNewEmployee() {

        Employee emp = new Employee(129, "Siva Praneesh", "Developer", 97000);

        when(empService.addNewEmployee(emp)).thenReturn(emp);

        ResponseEntity<Employee> newEmp = empController.addNewEmployee(emp);

        assertEquals(HttpStatus.CREATED, newEmp.getStatusCode());
        assertEquals(emp, newEmp.getBody());
    }

    @Test
    @Order(5)
    public void test_updateEmployee() {

        Employee emp = new Employee(129, "Siva Praneesh", "Developer", 97000);
        int id = 129;

        when(empService.updateEmployee(id, emp)).thenReturn(emp);

        ResponseEntity<Employee> res = empController.updateEmployee(id, emp);

        assertEquals(HttpStatus.OK, res.getStatusCode());
        assertEquals(emp, res.getBody());
    }

    @Test
    @Order(6)
    public void test_deleteEmployee() {

        int id = 129;
        Message m = new Message("Employee deleted successfully");

        when(empService.deleteEmployee(id)).thenReturn(m);

        ResponseEntity<Message> res = empController.deleteEmployee(id);

        assertEquals(HttpStatus.OK, res.getStatusCode());
        assertEquals(m, res.getBody());
    }
}