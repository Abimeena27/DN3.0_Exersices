--table Creation


CREATE TABLE Customers (
    CustomerID INT PRIMARY KEY,
    Name VARCHAR(100),
    DOB DATE,
    Balance DECIMAL(10, 2),
    LastModified DATE
);


CREATE TABLE Accounts (
    AccountID INT PRIMARY KEY,
    CustomerID INT,
    AccountType VARCHAR(20),
    Balance DECIMAL(10, 2),
    LastModified DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);


CREATE TABLE Employees (
    EmployeeID INT PRIMARY KEY,
    Name VARCHAR(100),
    Position VARCHAR(50),
    Salary DECIMAL(10, 2),
    Department VARCHAR(50),
    HireDate DATE
);


--Insert the Values

INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified) VALUES
(1, 'Alice Johnson', '1980-12-15', 1500.00, NOW()),
(2, 'Bob Smith', '1990-07-22', 2000.00, NOW());

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified) VALUES
(101, 1, 'Savings', 1000.00, NOW()),
(102, 1, 'Checking', 500.00, NOW()),
(201, 2, 'Savings', 1500.00, NOW());


INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate) VALUES
(1, 'John Doe', 'Manager', 50000.00, 'Sales', '2020-01-15'),
(2, 'Jane Smith', 'Developer', 60000.00, 'IT', '2019-03-22');


--Procedure for Scenario 1

DELIMITER $$

CREATE PROCEDURE ProcessMonthlyInterest()
BEGIN

    UPDATE Accounts
    SET Balance = Balance * 1.01
    WHERE AccountType = 'Savings';
END $$

DELIMITER ;

--Output for Scenario 1
CALL ProcessMonthlyInterest();
SELECT * FROM Accounts;


--Procedure for Scenario 2

DELIMITER $$

CREATE PROCEDURE UpdateEmployeeBonus(
    IN p_department VARCHAR(50),
    IN p_bonus_percentage DECIMAL(5,2)
)
BEGIN

    UPDATE Employees
    SET Salary = Salary + (Salary * p_bonus_percentage / 100)
    WHERE Department = p_department;
END $$

DELIMITER ;

--Output For Scenario 2
CALL UpdateEmployeeBonus();
SELECT * FROM Employees;

-- Procedure For Scenario 3

DELIMITER $$

CREATE PROCEDURE TransferFunds(
    IN p_from_account_id INT,
    IN p_to_account_id INT,
    IN p_amount DECIMAL(10,2)
)
BEGIN
    DECLARE v_from_balance DECIMAL(10,2);
    DECLARE v_to_balance DECIMAL(10,2);
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN

        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error occurred while transferring funds';
    END;


    START TRANSACTION;

    SELECT Balance INTO v_from_balance
    FROM Accounts
    WHERE AccountID = p_from_account_id
    FOR UPDATE;

    SELECT Balance INTO v_to_balance
    FROM Accounts
    WHERE AccountID = p_to_account_id
    FOR UPDATE;

    IF v_from_balance IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: Source account does not exist.';
    ELSEIF v_from_balance < p_amount THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: Insufficient funds in the source account.';
    ELSE

        UPDATE Accounts
        SET Balance = Balance - p_amount
        WHERE AccountID = p_from_account_id;


        UPDATE Accounts
        SET Balance = Balance + p_amount
        WHERE AccountID = p_to_account_id;

        COMMIT;
    END IF;
END $$

DELIMITER ;


--Output for Scenario 3

CALL TransferFunds(101, 201, 500.00);
SELECT * FROM Accounts;
