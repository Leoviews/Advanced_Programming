
INSERT INTO Transaction (transaction_id, account_id, transaction_type, amount)
VALUES (1001, 1, 'Deposit', 5000);

SELECT * FROM Transaction;

UPDATE Transaction
SET amount = 6000
WHERE transaction_id = 1001;

DELETE FROM Transaction
WHERE transaction_id = 1001;

SELECT account_id, SUM(amount) AS total_transactions
FROM Transaction
GROUP BY account_id;