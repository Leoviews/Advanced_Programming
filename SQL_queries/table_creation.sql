create database Bank_Management;
use Bank_Management;

CREATE TABLE Branch (
branch_id INT PRIMARY KEY,
branch_name VARCHAR(100) NOT NULL,
city VARCHAR(50) NOT NULL
);

CREATE TABLE Customer (
customer_id INT PRIMARY KEY,
full_name VARCHAR(100) NOT NULL,
email VARCHAR(100) UNIQUE NOT NULL,
phone VARCHAR(15) UNIQUE NOT NULL,
address VARCHAR(255)
);

CREATE TABLE Account (
account_id INT PRIMARY KEY,
customer_id INT NOT NULL,
branch_id INT NOT NULL,
account_type VARCHAR(20) CHECK (account_type IN ('Savings', 'Current')),
balance DECIMAL(12,2) CHECK (balance >= 0),
created_at DATE DEFAULT (CURDATE()),
FOREIGN KEY (customer_id) REFERENCES Customer(customer_id),
FOREIGN KEY (branch_id) REFERENCES Branch(branch_id)
);

CREATE TABLE Transaction (
transaction_id INT PRIMARY KEY,
account_id INT NOT NULL,
transaction_type VARCHAR(20) CHECK (transaction_type IN ('Deposit', 'Withdrawal', 'Transfer')),
amount DECIMAL(10,2) CHECK (amount > 0),
transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
FOREIGN KEY (account_id) REFERENCES Account(account_id)
);

CREATE TABLE Loan (
loan_id INT PRIMARY KEY,
customer_id INT NOT NULL,
loan_type VARCHAR(50),
loan_amount DECIMAL(12,2) CHECK (loan_amount > 0),
interest_rate DECIMAL(5,2),
FOREIGN KEY (customer_id) REFERENCES Customer(customer_id)
);

CREATE TABLE Employee (
employee_id INT PRIMARY KEY,
branch_id INT NOT NULL,
employee_name VARCHAR(100) NOT NULL,
role VARCHAR(50),
FOREIGN KEY (branch_id) REFERENCES Branch(branch_id)
);
