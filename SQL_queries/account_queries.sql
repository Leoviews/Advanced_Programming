INSERT INTO Account (account_id, customer_id, branch_id, account_type, balance)
VALUES (301, 1, 1, 'Savings', 20000);

SELECT * FROM Account;
SELECT account_id, balance FROM Account WHERE balance > 50000;

UPDATE Account
SET balance = balance + 5000
WHERE account_id = 301;

DELETE FROM Account WHERE account_id = 301;

SELECT branch_id, SUM(balance) AS total_balance
FROM Account
GROUP BY branch_id;