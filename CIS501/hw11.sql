--
-- Name: Victor Sun
-- Homework 11
--

SPOOL
SET ECHO ON

--x Write a query to select the all of the album names in your collection.
--x Write a query to select the all the track titles with a rating greater than or equal to 4.
-- Write a query to return the number of artists in your music collection.
-- Write a query to return the album_id and the average rating for the tracks in that album. Hint: You only need the tracks table for this. Output should be two columns: ALBUM_ID and the average rating.
-- Write a query to return the album name and the artist name for that album.
-- Write a query to return the album name and the average rating for the tracks in that album.

SELECT title
FROM albums;

SELECT title
FROM tracks
WHERE rating >= 4;

SELECT 
FROM

SET ECHO OFF
SPOOL OFF