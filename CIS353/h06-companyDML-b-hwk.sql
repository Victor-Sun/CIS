
-- File: companyDML-b-solution 
-- SQL/DML HOMEWORK (on the COMPANY database)
/*
Every query is worth 2 point. There is no partial credit for a
partially working query - think of this hwk as a large program and each query is a small part of the program.
--
IMPORTANT SPECIFICATIONS
--
(A)
-- Download the script file company.sql and use it to create your COMPANY database.
-- Dowlnoad the file companyDBinstance.pdf; it is provided for your convenience when checking the results of your queries.
(B)
Implement the queries below by ***editing this file*** to include
your name and your SQL code in the indicated places.   
--
(C)
IMPORTANT:
-- Don't use views
-- Don't use inline queries in the FROM clause - see our class notes.
--
(D)
After you have written the SQL code in the appropriate places:
** Run this file (from the command line in sqlplus).
** Print the resulting spooled file (companyDML-b.out) and submit the printout in class on the due date.
--
**** Note: you can use Apex to develop the individual queries. However, you ***MUST*** run this file from the command line as just explained above and then submit a printout of the spooled file. Submitting a printout of the webpage resulting from Apex will *NOT* be accepted.
--
*/
-- Please don't remove the SET ECHO command below.
SPOOL companyDML-b.out
SET ECHO ON
-- ------------------------------------------------------------
-- 
-- Name: < ***** PLEASE ENTER YOUR NAME HERE ***** >
--
-- -------------------------------------------------------------
--
-- NULL AND SUBSTRINGS -------------------------------
--
/*(10B)
Find the ssn and last name of every employee whose ssn contains two consecutive 8's, and has a supervisor. Sort the results by ssn.
*/
SELECT Ssn, Lname
FROM EMPLOYEE
WHERE Ssn LIKE '%88%' AND
	Super_ssn IS NOT NULL
ORDER BY Ssn;
--
-- JOINING 3 TABLES ------------------------------
-- 
/*(11B)
For every employee who works for more than 20 hours on any project that is controlled by the research department: Find the ssn, project number, and number of hours. Sort the results by ssn.
*/
SELECT DISTINCT E.Ssn, P.Pnumber, W.Hours
FROM Employee E, Project P, WORKS_ON W
WHERE W.Hours > 20 AND
	P.Dnum = 5 AND
	W.Essn = E.Ssn AND
	W.Pno = P.Pnumber
ORDER BY E.Ssn;
--
-- JOINING 3 TABLES ---------------------------
--
/*(12B)
Write a query that consists of one block only.
For every employee who works less than 10 hours on any project that is controlled by the department he works for: 
Find the employee's lname, his department number, project number, the number of the department controlling it, and the number of hours he works on that project. Sort the results by lname.
*/
SELECT DISTINCT E.Lname, D.Dnumber, P.Pnumber, P.Dnum, W.Hours
FROM Employee E, Project P, Department D, WORKS_ON W
WHERE W.Hours < 10 AND
	E.Dno = P.Dnum AND
	E.Dno = D.Dnumber AND
	E.Ssn = W.Essn AND
	P.Pnumber = W.Pno
ORDER BY E.Lname;
--
-- JOINING 4 TABLES -------------------------
--
/*(13B)
For every employee who works on any project that is located in Houston: Find the employees ssn and lname, and the names of his/her dependent(s) and their relationship(s) to the employee. 
Notice that there will be one row per qualyfing dependent. Sort the results by employee lname.
*/
SELECT DISTINCT E.Ssn, E.Lname, D.Dependent_name, D.Relationship
FROM Employee E, Dependent D, WORKS_ON W, Project P
WHERE P.Plocation ='Houston' AND
	E.Ssn = D.Essn AND
	E.Ssn = W.Essn AND
	E.Dno = P.Dnum AND
	W.Pno = P.PNumber AND
	W.Essn = D.Essn
ORDER BY E.Lname;
--
-- SELF JOIN -------------------------------------------
-- 
/*(14B)
Write a query that consists of one block only.
For every employee who works for a department that is different from his supervisor's department: Find his ssn, lname, department number; and his supervisor's ssn, lname, and department number. 
Sort the results by ssn.  
*/
SELECT E1.Ssn, E1.Lname, E1.Dno, E2.Ssn AS Supervisor_Ssn, E2.Lname AS Supervisor_Lname, E2.Dno AS Supervisor_Dno
FROM Employee E1, Employee E2
WHERE E1.Super_ssn = E2.Ssn AND
	E1.Dno <> E2.Dno
ORDER BY E1.Ssn;
--
-- USING MORE THAN ONE RANGE VARIABLE ON ONE TABLE -------------------
--
/*(15B)
Find pairs of employee lname's such that the two employees in the pair work on the same project for the same number of hours. 
List every pair once only. Sort the result by the lname in the left column in the result. 
*/
SELECT DISTINCT E1.Lname AS FirstLname, E2.Lname AS SecondLname
FROM Employee E1, Employee E2, WORKS_ON W1, WORKS_ON W2
WHERE W1.Pno = W2.Pno AND
	W1.Hours = W2.Hours AND
	E1.Ssn <> E2.Ssn AND
	E1.Ssn = W1.Essn AND
	E2.Ssn = W2.Essn AND
	E1.Ssn < E2.Ssn
ORDER BY E1.Lname;
--
/*(16B)
For every employee who has more than one dependent: Find the ssn, lname, and number of dependents. Sort the result by lname
*/
SELECT E.Ssn, E.Lname
FROM Employee E, Dependent D
WHERE E.Ssn = D.Essn 
GROUP BY E.Ssn, E.Lname
HAVING COUNT(*) > 1
ORDER BY E.Lname;
-- 
/*(17B)
For every project that has more than 2 employees working on and the total hours worked on it is less than 40: Find the project number, project name, number of employees working on it,
and the total number of hours worked by all employees on that project. Sort the results by project number.
*/
SELECT P.Pnumber, P.Pname, COUNT (*)
FROM Employee E, Project P, WORKS_ON W
WHERE E.Dno = P.Dnum AND
	E.Ssn = W.Essn AND
	P.Pnumber = W.Pno
GROUP BY P.Pnumber, P.Pname
ORDER BY P.Pnumber;
--
-- CORRELATED SUBQUERY --------------------------------
--
/*(18B)
For every employee whose salary is above the average salary in his department: Find the dno, ssn, lname, and salary. Sort the results by department number.
*/
SELECT E.Dno, E.Ssn, E.Lname, E.Salary
FROM Employee E
WHERE E.Salary > 
	(SELECT AVG(E.Salary) 
	FROM Employee E)
ORDER BY E.Dno;
--
-- CORRELATED SUBQUERY -------------------------------
--
/*(19B)
For every employee who works for the research department but does not work on any one project for more than 20 hours: Find the ssn and lname. Sort the results by lname
*/
SELECT E.Ssn, E.Lname
FROM Employee E
WHERE
    (SELECT D.Dname FROM Department D WHERE D.Dnumber = E.Dno) = 'Research' AND
    (SELECT MAX(W.Hours) FROM WORKS_ON W WHERE W.Essn = E.Ssn) < 20
ORDER BY E.Lname;
--
-- DIVISION ---------------------------------------------
--
/*(20B) Hint: This is a DIVISION query
For every employee who works on every project that is controlled by department 4: Find the ssn and lname. Sort the results by lname
*/
SELECT E.Ssn, E.Lname
FROM Employee E
WHERE
    (SELECT COUNT(*) FROM Works_On W WHERE
        W.Essn = E.Ssn AND
        (SELECT P.Dnum FROM Project P WHERE P.Pnumber = W.Pno) = 4) > 0
ORDER BY E.Lname;
--
SET ECHO OFF
SPOOL OFF
