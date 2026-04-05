
INSERT INTO Branch (branch_id, branch_name, city)
VALUES (101, 'Test Branch', 'Pune');

SELECT * FROM Branch;
SELECT * FROM Branch WHERE city = 'Mumbai';

UPDATE Branch SET city = 'Nagpur' WHERE branch_id = 101;

DELETE FROM Branch WHERE branch_id = 101;

SELECT city, COUNT(*) AS total_branches
FROM Branch
GROUP BY city;