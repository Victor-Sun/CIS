-- File: PLh10.sql
-- Author: Victor Sun
-- ----------------------------------
SET SERVEROUTPUT ON
SET VERIFY OFF
-- ----------------------------------
ACCEPT traineeID NUMBER PROMPT 'Enter a trainee ID: '
ACCEPT increment NUMBER PROMPT 'Enter an increment for his trainers: '
DECLARE
	sr sailors%ROWTYPE;
	CURSOR tCursor IS
		SELECT SID, SNAME, RATING, AGE, TRAINEE
		FROM SAILORS;
BEGIN
	OPEN tCursor;
	LOOP
		-- Fetch the qualifying rows one by one
		FETCH tCursor INTO SR;
		-- Print the sailor' old record
		DBMS_OUTPUT.PUT_LINE('+++ old row: '||SR.SID||'	'||SR.NAME||'	'||SR.RATING||'	'||SR.AGE||'	'||SR.TRAINEE);
		-- Increment the trainers' rating
		SR.RATING := SR.RATING + &increment;
		-- Print the sailor' new record
		DBMS_OUTPUT.PUT_LINE('+++++ new row: '||SR.SID||'	'||SR.NAME||'	'||SR.RATING||'	'||SR.AGE||'	'||SR.TRAINEE);
		END LOOP;
		-- test whether the sailor has no trainers (Hint: test tCursor%ROWCOUNT)
	IF tCursor%ROWCOUNT > 0 THEN
		COMMIT;
	END IF;
		CLOSE tCursor;
EXCEPTION
	WHEN NO_DATA_FOUND THEN
		DBMS_OUTPUT.PUT_LINE('+++++'||sr.sid|| 'is either not a sailor, or has no trainer');
	WHEN OTHERS THEN
		DBMS_OUTPUT.PUT_LINE('+++++'||SQLCODE||'...'||SQLERRM);
	END;
/
-- Let's see what happened to the database
SELECT *
FROM sailors S
WHERE S.trainee = '&traineeID';
UNDEFINE traineeID
UNDEFINE increment 