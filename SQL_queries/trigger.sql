
CREATE VIEW customer_account_summary AS
SELECT 
    c.customer_id,
    c.full_name,
    a.account_id,
    a.account_type,
    a.balance
FROM Customer c
JOIN Account a ON c.customer_id = a.customer_id;


DELIMITER $$

CREATE TRIGGER before_account_update
BEFORE UPDATE ON Account
FOR EACH ROW
BEGIN
    IF NEW.balance < 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Balance cannot be negative';
    END IF;
END $$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE deposit_money(
    IN p_account_id INT,
    IN p_amount DECIMAL(10,2)
)
BEGIN
    UPDATE Account
    SET balance = balance + p_amount
    WHERE account_id = p_account_id;
END $$

DELIMITER ;

DELIMITER $$

CREATE FUNCTION get_transaction_count(p_account_id INT)
RETURNS INT
DETERMINISTIC
BEGIN
    DECLARE total_count INT;

    SELECT COUNT(*) INTO total_count
    FROM Transaction
    WHERE account_id = p_account_id;

    RETURN total_count;
END $$

DELIMITER ;