# Spring Boot Employee CRUD REST API & Testing

**Created by Siva Praneesh Velpula**

A hands-on Java backend project demonstrating Employee CRUD REST APIs using Spring Boot, Spring Data JPA, and MySQL, along with automated testing using **JUnit 5, Mockito, and MockMvc**.

This repository contains the application source code, test classes, database setup, and instructions to run and test the project locally.

---

## 📌 About the Project

This project implements REST APIs to manage employee records stored in a MySQL database.

The application follows a layered architecture, separating the controller, service, repository, and entity responsibilities. In addition to implementing CRUD operations, this project focuses on understanding how automated testing works in a Spring Boot application.

The testing part covers service-layer unit tests, controller unit tests, REST endpoint testing with MockMvc, and a Spring Boot application context-loading test.

## ✨ Features

- Create employee records.
- Retrieve all employees.
- Retrieve an employee by ID.
- Search for an employee by name.
- Update employee details.
- Delete employee records.
- Connect to MySQL using Spring Data JPA.
- Test service methods using JUnit 5 and Mockito.
- Test controller behaviour using Mockito.
- Simulate HTTP requests using MockMvc.
- Run automated tests through Maven.

## 🧪 Spring Boot Testing

**Testing is one of the main learning areas of this project.**

### 1. Service Layer Unit Testing

**File:** `ServiceMockitoTests.java`

Uses JUnit 5 and Mockito to test service-layer behaviour while mocking the repository dependency.

This allows the service methods to be tested independently without requiring actual database operations for every unit test.

### 2. Controller Unit Testing

**File:** `ControllerMockitoTests.java`

Tests controller behaviour using mocked service dependencies.

This helps verify how the controller handles service results and produces responses without calling the real service implementation.

### 3. REST API Testing with MockMvc

**File:** `ControllerMockMvcTesting.java`

Uses MockMvc to simulate HTTP requests to Spring MVC controllers.

It allows us to test REST endpoints and verify responses without starting a real HTTP server for each test.

This is useful for automating checks that might otherwise be performed manually through Postman.

### 4. Spring Boot Context Testing

**File:** `CountryServiceMySqldbApplicationTests.java`

Contains the standard Spring Boot context-loading test, which checks whether the application context can start under the configured test environment.

### Testing Approaches at a Glance

| Testing approach | Purpose |
|---|---|
| JUnit 5 + Mockito | Execute tests and mock dependencies |
| Service unit tests | Test service behaviour independently |
| Controller unit tests | Test controller behaviour with mocked services |
| MockMvc | Simulate HTTP requests and check MVC responses |
| Context-loading test | Check application context initialisation |

### Run the Tests

On Windows, open a terminal in the project root and execute:

```bash
mvnw.cmd test
```

Review the Maven output to check which tests pass or fail. Full application-context tests may require the appropriate database or test-profile configuration.

---

## 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Java | Programming language |
| Spring Boot | Application framework |
| Spring Web | REST API development |
| Spring Data JPA | Database operations |
| Hibernate | JPA implementation |
| MySQL | Relational database |
| Maven | Dependency management and build |
| JUnit 5 | Automated testing |
| Mockito | Mocking dependencies |
| MockMvc | Controller and REST endpoint testing |
| Postman | Manual API testing |

## 🏗️ Project Architecture

```text
                 Client / Postman
                        |
                        v
              EmployeeController
                        |
                        v
                 EmployeeService
                        |
                        v
                  EmployeeRepo
                        |
                        v
                  MySQL Database
```

**How the application works:**

1. The controller receives an HTTP request.
2. The controller calls the appropriate service method.
3. The service uses the repository to perform database operations.
4. Spring Data JPA communicates with MySQL.
5. The controller returns the HTTP response.

The test classes verify different parts of the application using mocked dependencies or simulated HTTP requests.

## 📂 Project Structure

```text
spring-boot-employee-crud-api-testing/
│
├── src/
│   ├── main/
│   │   ├── java/com/sp/www/
│   │   │   ├── Beans/
│   │   │   │   ├── Employee.java
│   │   │   │   └── Message.java
│   │   │   ├── Controllers/
│   │   │   │   └── EmployeeController.java
│   │   │   ├── Repositories/
│   │   │   │   └── EmployeeRepo.java
│   │   │   ├── Services/
│   │   │   │   └── EmployeeService.java
│   │   │   └── CountryServiceMySqldbApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── application-h2.properties
│   │       └── data.sql
│   │
│   └── test/
│       └── java/com/sp/www/
│           ├── ControllerMockitoTests.java
│           ├── ControllerMockMvcTesting.java
│           ├── ServiceMockitoTests.java
│           └── CountryServiceMySqldbApplicationTests.java
│
├── pom.xml
├── mvnw
└── mvnw.cmd
```

## 🔗 REST API Endpoints

The following endpoints are implemented in `EmployeeController.java`.

| HTTP Method | Endpoint | Description |
|---|---|---|
| GET | `/getAllEmployees` | Retrieve all employees |
| GET | `/getEmployee/{id}` | Retrieve an employee by ID |
| GET | `/getEmployeeByName/{name}` | Search for an employee by name |
| POST | `/addNewEmployee` | Add a new employee |
| PUT | `/updateEmployee/{id}` | Update employee details |
| DELETE | `/deleteEmployee/{id}` | Delete an employee |

### Example: Add an Employee

**Request**

```http
POST /addNewEmployee
Content-Type: application/json
```

**Request body**

```json
{
  "empId": 1,
  "empName": "Rahul",
  "empDept": "IT",
  "salary": 50000.0
}
```

Send this request to your running application using Postman. The actual response depends on the controller implementation.

### Example: Retrieve All Employees

```http
GET /getAllEmployees
```

This endpoint retrieves the employee records available through the API.

## 🗄️ Database Setup

The project uses MySQL with the database name `stsdb` and the table name `Employee`.

### Step 1: Create the Database and Table

Run the following SQL in MySQL Workbench or the MySQL command-line client:

```sql
CREATE DATABASE stsdb;

USE stsdb;

CREATE TABLE Employee (
    empId INT PRIMARY KEY,
    empName VARCHAR(50),
    empDept VARCHAR(20),
    salary DOUBLE
);
```

### Step 2: Insert Sample Data (Optional)

```sql
INSERT INTO Employee
    (empId, empName, empDept, salary)
VALUES
    (1, 'Rahul', 'IT', 50000.0),
    (2, 'Priya', 'HR', 45000.0);

SELECT * FROM Employee;
```

These are fictional sample records for local practice.

### Step 3: Configure the Database Connection

Update `src/main/resources/application.properties`:

```properties
spring.application.name=CountryService_MySQLDB

spring.datasource.url=jdbc:mysql://localhost:3306/stsdb
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=none
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
```

Set `DB_USERNAME` and `DB_PASSWORD` as environment variables on your computer.

**Important:** Never publish real database credentials to a public repository. Since `spring.jpa.hibernate.ddl-auto=none` is configured, the table must already exist before the application starts.

## 🚀 How to Run the Project

### Prerequisites

- Java version supported by the project
- MySQL Server
- Maven Wrapper included in the repository
- Postman or another REST API client

### Step 1: Clone the Repository

```bash
git clone https://github.com/sivaatmbu/spring-boot-employee-crud-api-testing.git
```

### Step 2: Open the Project

Import the project into Spring Tool Suite or Eclipse as an existing Maven project.

### Step 3: Set Up MySQL

Create the `stsdb` database and `Employee` table using the SQL script above.

### Step 4: Configure Database Credentials

Set your local database environment variables and verify the connection settings.

### Step 5: Run the Application

Run `CountryServiceMySqldbApplication.java` from your IDE.

### Step 6: Test the API

Use Postman to send GET, POST, PUT, and DELETE requests to the endpoints listed above.

### Step 7: Execute the Tests

Run the following command from the project root:

```bash
mvnw.cmd test
```

Check the output to verify the test results.

## 📚 What I Learned

Through this project, I practised:

- Developing REST APIs using Spring Boot.
- Separating controller, service, and repository responsibilities.
- Performing database operations using Spring Data JPA.
- Connecting a Spring Boot application to MySQL.
- Handling HTTP requests and responses using `ResponseEntity`.
- Writing unit tests with JUnit 5.
- Mocking dependencies using Mockito.
- Testing service and controller layers independently.
- Testing REST endpoints using MockMvc.
- Understanding the difference between unit testing and web-layer testing.
- Running automated tests using Maven.

## 🔮 Possible Future Enhancements

- Input validation using Bean Validation.
- Centralised exception handling.
- Pagination and sorting.
- Additional integration tests.
- Swagger/OpenAPI documentation.
- Authentication and authorisation using Spring Security.

These are potential future improvements, not features claimed as already implemented.

---

## 👨‍💻 About the Author

**Siva Praneesh Velpula**

Aspiring Java Backend Developer interested in building REST APIs using Java and Spring Boot and improving application reliability through automated testing.

- **GitHub:** [@sivaatmbu](https://github.com/sivaatmbu)
- **Project Repository:** [Spring Boot Employee CRUD API & Testing](https://github.com/sivaatmbu/spring-boot-employee-crud-api-testing)

---

⭐ If you are learning Spring Boot, feel free to explore the code, run the application, and practise the testing approaches demonstrated in this project.

**Thank you for visiting!**
