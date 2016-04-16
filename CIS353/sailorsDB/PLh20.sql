-- File PLh20.sql
-- Author: VICTOR SUN
-------------------------------------------------------------------
SET SERVEROUTPUT ON
SET VERIFY OFF
------------------------------------
ACCEPT rateDecrement NUMBER PROMPT 'Enter the rate decrement: '
ACCEPT allowedMinRate NUMBER PROMPT 'Enter the allowed min. rate: '
DECLARE
	br boats%ROWTYPE;
	CURSOR bCursor IS
	SELECT bid, bname, color, rate, length, logkeeper
	FROM boats;
BEGIN
	OPEN bCursor;
	LOOP
		-- Fetch rows
		FETCH bCursor INTO br;
		EXIT WHEN bCursor%NOTFOUND;
		
		-- Print old boat rating
		DBMS_OUTPUT.PUT_LINE ('+++++ Boat:'||br.bid||': old rate = '||br.rate);
		br.rate := br.rate - &rateDecrement;
		
		--
		DECLARE
			belowAllowedMin EXCEPTION;
		BEGIN
			IF br.rate < &allowedMinRate
			THEN RAISE belowAllowedMin;
			ELSE UPDATE boats
				SET rate = br.rate
				WHERE boats.bid = br.bid;
				-- Print new boat record
				DBMS_OUTPUT.PUT_LINE ('----- Boat:'||br.bid||': new rate = '||br.rate);
			END IF;

		EXCEPTION
			WHEN belowAllowedMin THEN
				DBMS_OUTPUT.PUT_LINE ('----- Boat:'||br.bid||': Update rejected. The new rate would have been '||br.rate);
			WHEN OTHERS THEN
				DBMS_OUTPUT.PUT_LINE ('----- Update rejected: '||SQLCODE||'...'||SQLERRM);
		END;
	END LOOP;

	COMMIT;
	CLOSE bCursor;
END;
/ 
--
UNDEFINE rateDecrement
UNDEFINE allowedMinRate