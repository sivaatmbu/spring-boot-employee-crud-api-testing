
package com.sp.www;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sp.www.Beans.Employee;
import com.sp.www.Beans.Message;
import com.sp.www.Repositories.EmployeeRepo;
import com.sp.www.Services.EmployeeService;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
public class ServiceMockitoTests {

    @Mock
    EmployeeRepo empRepo;

    @InjectMocks
    EmployeeService empServices;

    @Test
    @Order(1)
    public void test_getAllEmployees() {
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(1, "Chalapathi", "Head", 85000));
        employees.add(new Employee(2, "Amaravathi", "CEO", 100000));

        when(empRepo.findAll()).thenReturn(employees);

        assertEquals(2, empServices.getAllEmployees().size());
    }

    @Test
    @Order(2)
    public void test_getEmployeeById() {
        int id = 2;

        Employee employee =
                new Employee(2, "Amaravathi", "CEO", 100000);

        when(empRepo.findById(id))
                .thenReturn(Optional.of(employee));

        assertEquals(id, empServices.getEmployeeById(id).getEmpId());
    }

    @Test
    @Order(3)
    public void test_getEmployeeByName() {
        String name = "Amaravathi";

        Employee emp =
                new Employee(2, "Amaravathi", "CEO", 100000);

        when(empRepo.getByEmpName(name)).thenReturn(emp);

        assertEquals(name,
                empServices.getEmployeeByName(name).getEmpName());
    }

    @Test
    @Order(4)
    public void test_addEmployee() {
        Employee emp =
                new Employee(3, "Siva Praneesh", "Java Developer", 90000);

        when(empRepo.save(emp)).thenReturn(emp);

        assertEquals(emp, empServices.addNewEmployee(emp));
    }

    @Test
    @Order(5)
    public void test_updateEmployee() {
        int id = 3;

        Employee emp =
                new Employee(3, "Siva Praneesh", "Java Developer", 90000);

        when(empRepo.findById(id))
                .thenReturn(Optional.of(emp));

        when(empRepo.save(emp)).thenReturn(emp);

        assertEquals(emp, empServices.updateEmployee(id, emp));
    }

    @Test
    @Order(6)
    public void test_deleteEmployee() {
        int id = 3;

        Employee emp =
                new Employee(3, "Siva Praneesh", "Java Developer", 90000);

        when(empRepo.findById(id))
                .thenReturn(Optional.of(emp));

        Message result = empServices.deleteEmployee(id);

        assertEquals(
                "Employee with Id: 3 removed or deleted successfully",
                result.getMsg()
        );
    }
}
