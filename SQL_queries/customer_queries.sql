INSERT INTO Customer (customer_id, full_name, email, phone, address)
VALUES (201, 'Leo', 'leo@gmail.com', '9999999999', 'Pune');

SELECT * FROM Customer;
SELECT full_name, email FROM Customer WHERE address = 'Pune';

UPDATE Customer SET address = 'Mumbai' WHERE customer_id = 201;

DELETE FROM Customer WHERE customer_id = 201;

SELECT address, COUNT(*) AS total_customers
FROM Customer
GROUP BY address;