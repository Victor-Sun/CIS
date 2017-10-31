
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
-- Name: Victor Sun
--
-- -------------------------------------------------------------
--
-- NULL AND SUBSTRINGS -------------------------------
--
/*(10B)
Find the ssn and last name of every employee whose ssn contains two consecutive 8's, and has a supervisor. Sort the results by ssn.
*/
-- <<< Your SQL code goes here >>>
SELECT SSN, LNAME
FROM EMPLOYEE
WHERE SUPER_SSN IS NOT NULL AND SSN LIKE '%88%'
ORDER BY SSN;
-- JOINING 3 TABLES ------------------------------
-- 
/*(11B)
For every employee who works for more than 20 hours on any project that is controlled by the research department: Find the ssn, project number,  and number of hours. Sort the results by ssn.
*/
-- <<< Your SQL code goes here >>>
SELECT T1.ESSN, T1.PNO, T1.HOURS
FROM WORKS_ON T1, DEPARTMENT T2, PROJECT T3
WHERE T1.PNO = T3.PNUMBER AND T2.DNUMBER = T3.DNUM AND T1.HOURS > 20 AND T2.DNAME = 'Research'
ORDER BY T1.ESSN;
-- JOINING 3 TABLES ---------------------------
--
/*(12B)
Write a query that consists of one block only.
For every employee who works less than 10 hours on any project that is controlled by the department he works for: Find the employee's lname, his department number, project number, the number of the department controlling it, and the number of hours he works on that project. Sort the results by lname.
*/
-- <<< Your SQL code goes here >>>
SELECT T1.LNAME, T1.DNO, T2.PNUMBER, T2.DNUM, T3.Hours
FROM EMPLOYEE T1, PROJECT T2, WORKS_ON T3
WHERE T1.DNO = T2.DNUM AND T2.PNUMBER = T3.PNO AND T1.SSN = T3.ESSN AND T3.HOURS < 10
ORDER BY T1.LNAME;
-- JOINING 4 TABLES -------------------------
--
/*(13B)
For every employee who works on any project that is located in Houston: Find the employees ssn and lname, and the names of his/her dependent(s) and their relationship(s) to the employee. Notice that there will be one row per qualyfing dependent. Sort the results by employee lname.
*/
-- <<< Your SQL code goes here >>>
SELECT DISTINCT T1.LNAME, T2.DEPENDENT_NAME, T2.RELATIONSHIP
FROM EMPLOYEE T1, DEPENDENT T2, PROJECT T3, WORKS_ON T4
WHERE T1.SSN = T2.ESSN AND T1.SSN = T4.ESSN AND T4.PNO = T3.PNUMBER AND T3.PLOCATION = 'Houston'
ORDER BY T1.LNAME;
-- SELF JOIN -------------------------------------------
-- 
/*(14B)
Write a query that consists of one block only.
For every employee who works for a department that is different from his supervisor's department: Find his ssn, lname, department number; and his supervisor's ssn, lname, and department number. Sort the results by ssn.  
*/
-- <<< Your SQL code goes here >>>
SELECT T1.SSN, T1.LNAME, T1.DNO, T2.SSN, T2.LNAME, T2.DNO
FROM EMPLOYEE T1, EMPLOYEE T2
WHERE T1.SUPER_SSN = T2.SSN AND T1.DNO <> T2.DNO
ORDER BY T1.SSN;
-- USING MORE THAN ONE RANGE VARIABLE ON ONE TABLE -------------------
--
/*(15B)
Find pairs of employee lname's such that the two employees in the pair work on the same project for the same number of hours. List every pair once only. Sort the result by the lname in the left column in the result. 
*/
-- <<< Your SQL code goes here >>>
SELECT T1.LNAME, T2.LNAME
FROM EMPLOYEE T1, EMPLOYEE T2, WORKS_ON T3, WORKS_ON T4
WHERE T1.SSN = T3.ESSN AND T2.SSN = T4.ESSN AND T3.PNO = T4.PNO AND T3.HOURS = T4.HOURS AND T1.SSN < T2.SSN
ORDER BY T1.LNAME;
/*(16B)
For every employee who has more than one dependent: Find the ssn, lname, and number of dependents. Sort the result by lname
*/
-- <<< Your SQL code goes here >>>
SELECT T1.SSN, T1.LNAME, COUNT(*) AS DEPENDENT_COUNT
FROM EMPLOYEE T1, DEPENDENT T2
WHERE T1.SSN = T2.ESSN
GROUP BY T1.SSN, T1.LNAME
HAVING COUNT(*) > 1
ORDER BY T1.LNAME;
/*(17B)
For every project that has more than 2 employees working on it and the total hours worked on it is less than 40: Find the project number, project name, number of employees working on it, and the total number of hours worked by all employees on that project. Sort the results by project number.
*/
-- <<< Your SQL code goes here >>>
SELECT T1.PNO, T3.PNAME,COUNT(T2.SSN) AS EMPLOYEE_COUNT, SUM(T1.HOURS) AS TOTAL_HOURS
FROM WORKS_ON T1, EMPLOYEE T2, PROJECT T3
WHERE T1.ESSN = T2.SSN AND T1.PNO = T3.PNUMBER
GROUP BY T1.PNO, T3.PNAME
HAVING COUNT(T2.SSN) > 2 AND SUM(T1.HOURS) < 40
ORDER BY T1.PNO;
-- CORRELATED SUBQUERY --------------------------------
--
/*(18B)
For every employee whose salary is above the average salary in his department: Find the dno, ssn, lname, and salary. Sort the results by department number.
*/
-- <<< Your SQL code goes here >>> 
SELECT T1.DNO, T1.SSN, T1.LNAME
FROM EMPLOYEE T1
WHERE T1.SALARY > (SELECT AVG(SALARY) FROM EMPLOYEE T2 GROUP BY DNO HAVING T1.DNO = T2.DNO);
-- CORRELATED SUBQUERY -------------------------------
--
/*(19B)
For every employee who works for the research department but does not work on any one project for more than 20 hours: Find the ssn and lname. Sort the results by lname
*/
-- <<< Your SQL code goes here >>>
SELECT T1.SSN, T1.LNAME
FROM EMPLOYEE T1
WHERE EXISTS (SELECT * FROM DEPARTMENT T2, WORKS_ON T3 WHERE T1.SSN = T3.ESSN AND T1.DNO = T2.DNUMBER AND T2.DNAME = 'Research' AND  T3.HOURS <= 20)
ORDER BY T1.LNAME;
-- DIVISION ---------------------------------------------
--
/*(20B) Hint: This is a DIVISION query
For every employee who works on every project that is controlled by department 4: Find the ssn and lname. Sort the results by lname
*/
-- <<< Your SQL code goes here >>>
SELECT T1.SSN, T1.LNAME
FROM EMPLOYEE T1
WHERE NOT EXISTS(
	(SELECT T2.DNUMBER
	FROM DEPARTMENT T2
	WHERE T2.DNUMBER = 4)
	MINUS
	(SELECT T2.DNUMBER
	FROM DEPARTMENT T2, PROJECT T3
	WHERE T2.DNUMBER = T3.DNUM AND
	T1.DNO = T2.DNUMBER AND
	T2.DNUMBER = 4))
ORDER BY T1.LNAME;
--
SET ECHO OFF
SPOOL OFF

