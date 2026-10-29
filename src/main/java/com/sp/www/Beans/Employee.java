package com.sp.www.Beans;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    @Column(name = "empId")
    private Integer empId;

    @Column(name = "empName")
    private String empName;

    @Column(name = "empDept")
    private String empDept;

    @Column(name = "salary")
    private double salary;

    public Employee() {
    }
    public Employee(Integer empId, String empName, String empDept, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.empDept = empDept;
        this.salary = salary;
    }
    public Integer getEmpId() { 
    	return empId; 
    }
    public void setEmpId(Integer empId) { 
    	this.empId = empId;
    }
    public String getEmpName() {
    	return empName; 
    }
    public void setEmpName(String empName) { 
    	this.empName = empName; 
    }
    public String getEmpDept() { 
    	return empDept; 
    }
    public void setEmpDept(String empDept) { 
    	this.empDept = empDept; 
    }
    public double getSalary() { 
    	return salary; 
    }
    public void setSalary(double salary) { 
    	this.salary = salary; 
    }
}