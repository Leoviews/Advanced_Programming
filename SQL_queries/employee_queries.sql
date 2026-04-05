
INSERT INTO Employee (employee_id, branch_id, employee_name, role)
VALUES (701, 1, 'Rahul Sharma', 'Manager');


SELECT * FROM Employee;


UPDATE Employee
SET role = 'Senior Manager'
WHERE employee_id = 701;


DELETE FROM Employee
WHERE employee_id = 701;


SELECT role, COUNT(*) AS total_employees
FROM Employee
GROUP BY role;