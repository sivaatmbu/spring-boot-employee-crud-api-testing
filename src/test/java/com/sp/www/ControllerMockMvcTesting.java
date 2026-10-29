
package com.sp.www;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.sp.www.Beans.Employee;
import com.sp.www.Beans.Message;
import com.sp.www.Controllers.EmployeeController;
import com.sp.www.Services.EmployeeService;

import tools.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ControllerMockMvcTesting {

    private MockMvc mockMvc;

    @Mock
    EmployeeService service;

    @InjectMocks
    EmployeeController controller;

    List<Employee> employees;
    Employee emp;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    @Order(1)
    public void test_getAllEmployees() throws Exception {

        employees = new ArrayList<>();

        employees.add(new Employee(129, "Siva Praneesh", "Java Developer", 250000));
        employees.add(new Employee(13, "Ajay", "Tester", 50000));
        employees.add(new Employee(1, "Chalapathi", "Manager", 100000));

        when(service.getAllEmployees()).thenReturn(employees);

        mockMvc.perform(get("/getAllEmployees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].empId").value(129))
                .andExpect(jsonPath("$[0].empName").value("Siva Praneesh"))
                .andDo(print());
    }

    @Test
    @Order(2)
    public void test_getEmployeeById() throws Exception {

        emp = new Employee(129, "Siva Praneesh", "Java Developer", 250000);
        int id = 129;

        when(service.getEmployeeById(id)).thenReturn(emp);

        mockMvc.perform(get("/getEmployee/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath(".empId").value(129))
                .andExpect(jsonPath(".empName").value("Siva Praneesh"))
                .andDo(print());
    }

    @Test
    @Order(3)
    public void test_getEmployeeByName() throws Exception {

        emp = new Employee(129, "Siva Praneesh", "Java Developer", 250000);
        String name = "Siva Praneesh";

        when(service.getEmployeeByName(name)).thenReturn(emp);

        mockMvc.perform(get("/getEmployeeByName/{name}", name))
                .andExpect(status().isOk())
                .andExpect(jsonPath(".empId").value(129))
                .andExpect(jsonPath(".empName").value("Siva Praneesh"))
                .andDo(print());
    }

    @Test
    @Order(4)
    public void test_addNewEmployee() throws Exception {

        emp = new Employee(129, "Siva Praneesh", "Java Developer", 250000);

        ObjectMapper mapper = new ObjectMapper();
        String jsonbody = mapper.writeValueAsString(emp);

        when(service.addNewEmployee(any(Employee.class))).thenReturn(emp);

        mockMvc.perform(post("/addNewEmployee")
                .content(jsonbody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath(".empId").value(129))
                .andExpect(jsonPath(".empName").value("Siva Praneesh"))
                .andDo(print());
    }

    @Test
    @Order(5)
    public void test_updateEmployee() throws Exception {

        emp = new Employee(129, "Siva Praneesh", "Java Developer", 250000);
        int id = 129;

        ObjectMapper mapper = new ObjectMapper();
        String jsonbody = mapper.writeValueAsString(emp);

        when(service.updateEmployee(eq(id), any(Employee.class))).thenReturn(emp);

        mockMvc.perform(put("/updateEmployee/{id}", id)
                .content(jsonbody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(".empId").value(129))
                .andExpect(jsonPath(".empName").value("Siva Praneesh"))
                .andExpect(jsonPath(".empDept").value("Java Developer"))
                .andDo(print());
    }

    @Test
    @Order(6)
    public void test_deleteEmployee() throws Exception {

        int id = 129;
        Message message = new Message();

        when(service.deleteEmployee(id)).thenReturn(message);

        mockMvc.perform(delete("/deleteEmployee/{id}", id))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
