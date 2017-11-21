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
		FROM SAILORS
		WHERE TRAINEE = '&traineeID';
BEGIN
	OPEN tCursor;
	LOOP
		-- Fetch the qualifying rows one by one
		FETCH tCursor INTO SR;
		EXIT WHEN tCursor%NOTFOUND;
		-- Print the sailor' old record
		DBMS_OUTPUT.PUT_LINE('+++ old row: '||SR.SID||'	'||SR.SNAME||'	'||SR.RATING||'	'||SR.AGE||'	'||SR.TRAINEE);
		-- Increment the trainers' rating
		SR.RATING := SR.RATING + &increment;
		UPDATE sailors
		SET rating = SR.RATING
		WHERE Sailors.SID = SR.SID;
		-- Print the sailor' new record
		DBMS_OUTPUT.PUT_LINE('+++++ new row: '||SR.SID||'	'||SR.SNAME||'	'||SR.RATING||'	'||SR.AGE||'	'||SR.TRAINEE);
	END LOOP;
	-- test whether the sailor has no trainers (Hint: test tCursor%ROWCOUNT)
	IF tCursor%ROWCOUNT = 0 THEN
		raise NO_DATA_FOUND;
	ELSE
		COMMIT;
		DBMS_OUTPUT.PUT_LINE('+++++ DB has been updated');
	END IF;
	CLOSE tCursor;
EXCEPTION
	WHEN NO_DATA_FOUND THEN
		DBMS_OUTPUT.PUT_LINE('+++++ &traineeID  is either not a sailor, or has no trainer');
	WHEN OTHERS THEN
		DBMS_OUTPUT.PUT_LINE('+++++ '||SQLCODE||' ...'||SQLERRM);
	END;
/
-- Let's see what happened to the database
SELECT *
FROM sailors S
WHERE S.trainee = '&traineeID';
UNDEFINE traineeID
UNDEFINE increment 
