--Table Creation
CREATE TABLE Customers (
    CustomerID INT PRIMARY KEY,
    Name VARCHAR(100),
    DOB DATE,
    Balance DECIMAL(10,2),
    LastModified DATE
);

CREATE TABLE Transactions (
    TransactionID INT PRIMARY KEY AUTO_INCREMENT,
    AccountID INT,
    Amount DECIMAL(10,2),
    TransactionType ENUM('Deposit', 'Withdrawal'),
    TransactionDate DATE
);

CREATE TABLE AuditLog (
    AuditID INT PRIMARY KEY AUTO_INCREMENT,
    TransactionID INT,
    AccountID INT,
    Amount DECIMAL(10,2),
    TransactionType ENUM('Deposit', 'Withdrawal'),
    TransactionDate DATE,
    LogDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

--Insert Values into table
INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
VALUES
(1, 'Alice Johnson', '1980-12-15', 1500.00, CURDATE()),
(2, 'Bob Smith', '1975-05-20', 2000.00, CURDATE());

INSERT INTO Transactions (AccountID, Amount, TransactionType, TransactionDate)
VALUES
(1, 500.00, 'Deposit', CURDATE()),
(1, 200.00, 'Withdrawal', CURDATE()),
(2, 1000.00, 'Deposit', CURDATE());

--Trigger for Scenario 1

DELIMITER $$

CREATE TRIGGER UpdateCustomerLastModified
BEFORE UPDATE ON Customers
FOR EACH ROW
BEGIN
    SET NEW.LastModified = CURDATE();
END $$

DELIMITER ;

--Output for Scenario 1
UPDATE Customers
SET Balance = Balance + 100
WHERE CustomerID = 1;

SELECT * FROM Customers;

--Trigger for Scenario 2
DELIMITER $$

CREATE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (TransactionID, AccountID, Amount, TransactionType, TransactionDate)
    VALUES (NEW.TransactionID, NEW.AccountID, NEW.Amount, NEW.TransactionType, NEW.TransactionDate);
END $$

DELIMITER ;

--Output for Scenario 2
INSERT INTO Transactions (AccountID, Amount, TransactionType, TransactionDate)
VALUES (101, 500.00, 'Deposit', CURDATE());

SELECT * FROM AuditLog;

--Trigger for Scenario 3
DELIMITER $$

CREATE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
BEGIN
    DECLARE v_balance DECIMAL(10,2);

    SELECT Balance INTO v_balance
    FROM Customers
    WHERE CustomerID = NEW.AccountID;

    IF NEW.TransactionType = 'Withdrawal' AND v_balance < NEW.Amount THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: Insufficient funds for withdrawal';
    END IF;

    IF NEW.TransactionType = 'Deposit' AND NEW.Amount <= 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: Deposit amount must be positive';
    END IF;
END $$

DELIMITER ;

--Output for Scenario 3
INSERT INTO Transactions (AccountID, Amount, TransactionType, TransactionDate)
VALUES (101, 200.00, 'Deposit', CURDATE());

INSERT INTO Transactions (AccountID, Amount, TransactionType, TransactionDate)
VALUES (101, -50.00, 'Deposit', CURDATE());

INSERT INTO Transactions (AccountID, Amount, TransactionType, TransactionDate)
VALUES (102, 600.00, 'Withdrawal', CURDATE());

SELECT * FROM Transactions;
