/*
This trigger implements part of the inclusion dependency bIC5 (see our
Sailors schema).It verifies, upon DELETE, or UPDATE on the Sailors table,
that boats.logKeeper is in Sailors.trainee
*/
-- Notice the use of PRAGMA AUTONOMOUS_TRANSACTION
-- -------------------------------------------------------
-- File: PLh110.sql
-- Author: Victor Sun
--
CREATE OR REPLACE TRIGGER bIC5_TB
BEFORE DELETE OR UPDATE OF trainee ON Sailors
FOR EACH ROW
DECLARE
	PRAGMA AUTONOMOUS_TRANSACTION; -- You’ll need this directive
--
-- <<< YOUR CODE GOES HERE>
--
BEGIN
	SELECT COUNT(*)
	FROM SAILORS S, BOATS B
	WHERE S.TRAINEE = B.LOGKEEPER AND S.TRAINEE = :OLD.trainee;
	IF numFOUND > 0	THEN
		RAISE_APPLICATION_ERROR (-20001||'+++++ DELETE or UPDATE rejected. Trainee ..'||S.TRAINEE||'..is a LogKeeper')
	END IF;
END;
/
SHOW ERROR
-- -------------------------------------------------------
