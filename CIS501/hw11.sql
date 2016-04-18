--
-- Name: Victor Sun
-- Homework 11
--
SPOOL hw11.out
SET ECHO ON
--
-- Write a query to select the all of the album names in your collection.
--
SELECT title
FROM albums;
--
-- Write a query to select the all the track titles with a rating greater than or equal to 4.
--
SELECT title
FROM tracks
WHERE rating >= 4;
--
-- Write a query to return the number of artists in your music collection.
--
SELECT COUNT(*) AS ArtistCount
FROM artist;
--
-- Write a query to return the album_id and the average rating for the tracks in that album. 
-- Hint: You only need the tracks table for this. 
-- Output should be two columns: ALBUM_ID and the average rating.
--
SELECT avg(rating), album_id
FROM tracks
GROUP BY album_id;
--
-- Write a query to return the album name and the artist name for that album.
--
SELECT A1.title, A2.fname, A2.lname
FROM albums A1, artist A2
WHERE A1.artist_id = A2.artist_id;
--
-- Write a query to return the album name and the average rating for the tracks in that album.
--
SELECT A.title, avg(T.rating)
FROM albums A, tracks T 
WHERE A.album_id = T.album_id
GROUP BY A.title;
--
SET ECHO OFF
SPOOL OFF