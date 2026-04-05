
INSERT INTO Loan (loan_id, customer_id, loan_type, loan_amount, interest_rate)
VALUES (501, 1, 'Home Loan', 500000, 7.5);


SELECT * FROM Loan;


UPDATE Loan
SET interest_rate = 8.0
WHERE loan_id = 501;


DELETE FROM Loan
WHERE loan_id = 501;


SELECT loan_type, COUNT(*) AS total_loans
FROM Loan
GROUP BY loan_type;