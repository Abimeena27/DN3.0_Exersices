--Table Creation
CREATE TABLE customers (
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    age INT,
    loan_interest_rate DECIMAL(5,2),
    balance DECIMAL(10,2),
    IsVIP BOOLEAN DEFAULT FALSE
);

CREATE TABLE loans (
    loan_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    loan_due_date DATE,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);
-- Insert sample data into table

INSERT INTO customers (name, age, loan_interest_rate, balance)
VALUES ('Alice', 65, 5.0, 12000);
INSERT INTO customers (name, age, loan_interest_rate, balance)
VALUES ('Bob', 55, 4.5, 8000);
INSERT INTO customers (name, age, loan_interest_rate, balance)
VALUES ('Charlie', 70, 6.0, 15000);
INSERT INTO customers (name, age, loan_interest_rate, balance)
VALUES ('David', 45, 3.5, 5000);


INSERT INTO loans (customer_id, loan_due_date)
VALUES (1, CURDATE() + INTERVAL 10 DAY);
INSERT INTO loans (customer_id, loan_due_date)
VALUES (2, CURDATE() + INTERVAL 35 DAY);
INSERT INTO loans (customer_id, loan_due_date)
VALUES (3, CURDATE() + INTERVAL 20 DAY);
INSERT INTO loans (customer_id, loan_due_date)
VALUES (4, CURDATE() + INTERVAL 5 DAY);


--Procedure for SCENARIO 1

DELIMITER //

DROP PROCEDURE IF EXISTS ApplyDiscount;

CREATE PROCEDURE ApplyDiscount()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE cust_id INT;
    DECLARE cust_age INT;
    DECLARE cust_rate DECIMAL(5,2);

    DECLARE cur CURSOR FOR SELECT customer_id, age, loan_interest_rate FROM customers;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    OPEN cur;
    read_loop: LOOP
        FETCH cur INTO cust_id, cust_age, cust_rate;
        IF done THEN
            LEAVE read_loop;
        END IF;

        IF cust_age > 60 THEN
            UPDATE customers
            SET loan_interest_rate = cust_rate * 0.99
            WHERE customer_id = cust_id;
        END IF;
    END LOOP;
    CLOSE cur;
END //

DELIMITER ;

--Output for SCENARIO 1:
CALL ApplyDiscount();

SELECT * FROM cusromers 
--Procedure for SCENARIO 2

DELIMITER //

CREATE PROCEDURE PromoteVIP()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE cust_id INT;
    DECLARE cust_balance DECIMAL(10,2);

    DECLARE cur CURSOR FOR SELECT customer_id, balance FROM customers;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    OPEN cur;
    read_loop: LOOP
        FETCH cur INTO cust_id, cust_balance;
        IF done THEN
            LEAVE read_loop;
        END IF;

        IF cust_balance > 10000 THEN
            UPDATE customers
            SET IsVIP = TRUE
            WHERE customer_id = cust_id;
        END IF;
    END LOOP;
    CLOSE cur;
END //

DELIMITER ;

--Output for SCENARIO 2
CALL PromoteVIP();

SELECT * FROM customers;

--Procedure for SCENARIO 3

DELIMITER //

CREATE PROCEDURE SendReminders()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE cust_id INT;
    DECLARE due_date DATE;
    DECLARE cust_name VARCHAR(100);

    DECLARE cur CURSOR FOR SELECT customer_id, loan_due_date FROM loans WHERE loan_due_date BETWEEN CURDATE() AND CURDATE() + INTERVAL 30 DAY;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    OPEN cur;
    read_loop: LOOP
        FETCH cur INTO cust_id, due_date;
        IF done THEN
            LEAVE read_loop;
        END IF;

        SELECT name INTO cust_name FROM customers WHERE customer_id = cust_id;
        SELECT CONCAT('Reminder: Dear ', cust_name, ', your loan is due on ', due_date) AS ReminderMessage;
    END LOOP;
    CLOSE cur;
END //

DELIMITER ;

--Output for SCENARIO 3

CALL SendReminders();

SELECT * FROM loans;
SELECT * FROM customers;
