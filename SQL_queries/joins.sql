
SELECT c.full_name, a.account_id, a.balance
FROM Customer c
JOIN Account a ON c.customer_id = a.customer_id;


SELECT b.branch_name, SUM(a.balance) AS total_balance
FROM Branch b
JOIN Account a ON b.branch_id = a.branch_id
GROUP BY b.branch_name;


SELECT c.full_name, l.loan_type, l.loan_amount
FROM Customer c
JOIN Loan l ON c.customer_id = l.customer_id;


SELECT *
FROM Account
ORDER BY balance DESC
LIMIT 1;


SELECT account_id, COUNT(*) AS total_transactions
FROM Transaction
GROUP BY account_id;