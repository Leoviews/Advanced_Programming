CREATE TABLE numbers (n INT PRIMARY KEY);
INSERT INTO numbers (n)
SELECT a.n + b.n * 10 + 1
FROM
(SELECT 0 n UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4
 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) a,
(SELECT 0 n UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4
 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) b
LIMIT 100;

INSERT INTO Branch (branch_id, branch_name, city)
SELECT n,
       CONCAT('Branch_', n),
       CASE
           WHEN n % 5 = 0 THEN 'Mumbai'
           WHEN n % 5 = 1 THEN 'Pune'
           WHEN n % 5 = 2 THEN 'Delhi'
           WHEN n % 5 = 3 THEN 'Bangalore'
           ELSE 'Chennai'
       END
FROM numbers;

select * from branch;

INSERT INTO Customer (customer_id, full_name, email, phone, address)
SELECT n,
       CONCAT('Customer_', n),
       CONCAT('customer', n, '@gmail.com'),
       CONCAT('900000', LPAD(n, 4, '0')),
       CASE
           WHEN n % 4 = 0 THEN 'Pune'
           WHEN n % 4 = 1 THEN 'Mumbai'
           WHEN n % 4 = 2 THEN 'Delhi'
           ELSE 'Nagpur'
       END
FROM numbers;

select * from customer;

INSERT INTO Account (account_id, customer_id, branch_id, account_type, balance)
SELECT n,
       n,
       n,
       CASE WHEN n % 2 = 0 THEN 'Savings' ELSE 'Current' END,
       10000 + (n * 500)
FROM numbers;



INSERT INTO Transaction (transaction_id, account_id, transaction_type, amount, transaction_date)
SELECT n,
       n,
       CASE
           WHEN n % 3 = 0 THEN 'Deposit'
           WHEN n % 3 = 1 THEN 'Withdrawal'
           ELSE 'Transfer'
       END,
       1000 + (n * 100),
       NOW()
FROM numbers;

INSERT INTO Loan (loan_id, customer_id, loan_type, loan_amount, interest_rate)
SELECT n,
       n,
       CASE
           WHEN n % 3 = 0 THEN 'Home Loan'
           WHEN n % 3 = 1 THEN 'Car Loan'
           ELSE 'Education Loan'
       END,
       500000 + (n * 10000),
       7.5 + (n % 5)
FROM numbers;

INSERT INTO Employee (employee_id, branch_id, employee_name, role)
SELECT n,
       n,
       CONCAT('Employee_', n),
       CASE
           WHEN n % 4 = 0 THEN 'Manager'
           WHEN n % 4 = 1 THEN 'Clerk'
           WHEN n % 4 = 2 THEN 'Cashier'
           ELSE 'Officer'
       END
FROM numbers;

SELECT COUNT(*) FROM Branch;
SELECT COUNT(*) FROM Customer;
SELECT COUNT(*) FROM Account;
SELECT COUNT(*) FROM Transaction;
SELECT COUNT(*) FROM Loan;
SELECT COUNT(*) FROM Employee;

