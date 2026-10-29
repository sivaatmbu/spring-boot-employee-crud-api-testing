CREATE TABLE IF NOT EXISTS Employee (
    emp_id INT PRIMARY KEY,
    emp_name VARCHAR(100),
    emp_dept VARCHAR(100),
    salary DOUBLE
);

INSERT INTO Employee (emp_id, emp_name, emp_dept, salary)
VALUES (129, 'Siva Praneesh', 'Java Developer', 250000);

INSERT INTO Employee (emp_id, emp_name, emp_dept, salary)
VALUES (13, 'Ajay', 'Tester', 50000);

INSERT INTO Employee (emp_id, emp_name, emp_dept, salary)
VALUES (1, 'Chalapathi', 'Manager', 100000);

INSERT INTO Employee (emp_id, emp_name, emp_dept, salary)
VALUES (102, 'Rahul', 'Spring Boot Developer', 75000);

INSERT INTO Employee (emp_id, emp_name, emp_dept, salary)
VALUES (103, 'Priya', 'Frontend Developer', 65000);

INSERT INTO Employee (emp_id, emp_name, emp_dept, salary)
VALUES (104, 'Kiran', 'Database Administrator', 80000);

INSERT INTO Employee (emp_id, emp_name, emp_dept, salary)
VALUES (105, 'Sneha', 'Full Stack Developer', 90000);

INSERT INTO Employee (emp_id, emp_name, emp_dept, salary)
VALUES (106, 'Arjun', 'Software Tester', 55000);