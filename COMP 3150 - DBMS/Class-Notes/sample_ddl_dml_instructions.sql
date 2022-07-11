
-- Hello Comp 3150 students.  Here are some sample SQL DDL and DML instructions 
-- for creating a table called emplyoee, inserting data into employee, 
-- querying employee, deleting data from employee, and dropping employee.


-- to create the table employee that does not already exist, use:
CREATE TABLE EMPLOYEE
(Fname VARCHAR2(15) NOT NULL,
 Minit CHAR,
 Lname VARCHAR2(15) NOT NULL,
 Ssn CHAR(9) NOT NULL,
Bdate DATE,
Address VARCHAR2(25),
 Sex CHAR,
Salary NUMBER(10, 2),
Super_ssn CHAR(9),
Dno INT NOT NULL,
 PRIMARY KEY(Ssn));


-- to insert a tuple into employee that does not already exist, use:
INSERT INTO EMPLOYEE
VALUES ('Franklin', 'T', 'Wong', '333445555', '12-aug-55', '638 Voss, Houston, TX', 'M', 40000, 888665555, 5);

commit;

-- to pose a query on the table employee such as:
-- Query 0: Get the birthdate and address of employee 'John B. Smith'.

SELECT Bdate, Address
FROM  EMPLOYEE
WHERE  Fname ='John' AND Minit='B' AND Lname = 'Smith';


-- To delete tuples from Employee table, do:
delete from employee;
commit;

-- To drop table employee, use 
drop table employee cascade;

-- or on our system if you are trying to drop a table being referenced 
-- (such as department) by other tables (such as Employee) not yet deleted.  
-- It is better to delete the tables referencing other tables (e.g., Employee) 
-- first before deleting the tables they are referencing (e.g., Department) 
--  
drop table employee cascade CONSTRAINTS;


-- Note that these are all discussed in detail in Chapters 6 and 7 which we have 
-- started in class.  However, for assignment 2, you were given the instructions 
-- you need to do what you need to do to get started with Sqlplus.  If you cannot 
-- delete the tables you have created now.  Just re-create new tables with a 
-- slightly different name such as Member1 and carry on until you can use the 
-- instructions well.  Hope this helps with a question by one student in the 
-- class through chat that I could not get to.


-- Two Sqlplus instructions we use a lot for checking the contents of the sytem 
-- catalogue, and describing the schema of any table in the database are:

-- the following shows all the tables in the database catalogue.
select * from cat;

-- the following gives the schema of the employee table.
desc employee;

