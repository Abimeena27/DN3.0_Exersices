--Table Creation

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


CREATE TABLE Transactions (
    TransactionID INT PRIMARY KEY,
    AccountID INT,
    TransactionDate DATE,
    Amount DECIMAL(10, 2),
    TransactionType VARCHAR(10),
    FOREIGN KEY (AccountID) REFERENCES Accounts(AccountID)
);


CREATE TABLE Loans (
    LoanID INT PRIMARY KEY,
    CustomerID INT,
    LoanAmount DECIMAL(10, 2),
    InterestRate DECIMAL(5, 2),
    StartDate DATE,
    EndDate DATE,
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


--Add Values to Table

INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
VALUES
    (1, 'John Doe', '1955-05-15', 5000.00, CURDATE()),
    (2, 'Jane Smith', '1985-07-20', 12000.00, CURDATE()),
    (3, 'Alice Johnson', '1990-10-10', 2500.00, CURDATE());

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
VALUES
    (1, 1, 'Savings', 3000.00, CURDATE()),
    (2, 1, 'Checking', 2000.00, CURDATE()),
    (3, 2, 'Savings', 10000.00, CURDATE()),
    (4, 3, 'Checking', 1500.00, CURDATE());


INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate)
VALUES
    (1, 1, 2000.00, 5.0, '2024-01-01', '2027-01-01'),
    (2, 2, 5000.00, 4.5, '2024-06-01', '2029-06-01'),
    (3, 3, 1500.00, 6.0, '2024-03-01', '2026-03-01');


INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
VALUES
    (1, 'Michael Scott', 'Regional Manager', 60000.00, 'Sales', '2010-04-01'),
    (2, 'Pam Beesly', 'Receptionist', 35000.00, 'Administration', '2011-05-01'),
    (3, 'Jim Halpert', 'Sales Representative', 50000.00, 'Sales', '2012-06-01');


--Procedure for Scenario 1

DELIMITER //

CREATE PROCEDURE SafeTransferFunds (
    IN p_from_account_id INT,
    IN p_to_account_id INT,
    IN p_amount DECIMAL(10, 2)
)
BEGIN
    DECLARE v_from_balance DECIMAL(10, 2);
    DECLARE v_to_balance DECIMAL(10, 2);

    START TRANSACTION;

    SELECT Balance INTO v_from_balance
    FROM Accounts
    WHERE AccountID = p_from_account_id;

    IF v_from_balance < p_amount THEN

        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Insufficient funds in the source account.';
    ELSE

        SELECT Balance INTO v_to_balance
        FROM Accounts
        WHERE AccountID = p_to_account_id;

        UPDATE Accounts
        SET Balance = Balance - p_amount
        WHERE AccountID = p_from_account_id;

        UPDATE Accounts
        SET Balance = Balance + p_amount
        WHERE AccountID = p_to_account_id;

        COMMIT;
    END IF;
END //

DELIMITER ;

--Output For Scenario 1

CALL SafeTransferFunds(1,2,500.00);
SELECT * FROM Accounts;


--Procedure for Scenario 2

DELIMITER $$

CREATE PROCEDURE UpdateSalary (
    IN p_employee_id INT,
    IN p_percentage DECIMAL(5,2)
)
BEGIN
    DECLARE v_current_salary DECIMAL(10,2);

    SELECT Salary INTO v_current_salary
    FROM Employees
    WHERE EmployeeID = p_employee_id;

    IF v_current_salary IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: Employee ID does not exist.';
    ELSE
        UPDATE Employees
        SET Salary = Salary + (Salary * p_percentage / 100)
        WHERE EmployeeID = p_employee_id;

        COMMIT;
    END IF;
END $$

DELIMITER ;


--Output for Scenario 2

CALL UpdateSalary(1, 10.00);
SELECT * FROM Employees;


--Procedure for Scenario 3


DELIMITER $$

CREATE PROCEDURE AddNewCustomer (
    IN p_customer_id INT,
    IN p_name VARCHAR(100),
    IN p_dob DATE,
    IN p_balance DECIMAL(10,2)
)
BEGIN
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
    BEGIN

        ROLLBACK;

        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error occurred while inserting the customer: ' || MESSAGE_TEXT;
    END;

    START TRANSACTION;

    INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
    VALUES (p_customer_id, p_name, p_dob, p_balance, NOW());

    COMMIT;
END $$

DELIMITER ;


--Output for Scenario 3

CALL AddNewCustomer(4, 'Amelia Johnson', '1980-12-15', 1500.00);
SELECT * FROM Customers;

