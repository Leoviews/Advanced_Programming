CREATE INDEX idx_customer_email
ON Customer(email);

CREATE INDEX idx_account_customer
ON Account(customer_id);

CREATE INDEX idx_transaction_account
ON Transaction(account_id);

CREATE INDEX idx_branch_city
ON Branch(city);