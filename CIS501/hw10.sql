--
-- File: hw10.sql
-- Name: Victor Sun
--
--x Use the CREATE command to create a schema (set of tables) for storing a music collection. You should have a table for Artists with fields for id, first name, and last name.  Artists last names cannot be null, but first names can (e.g., “Prince” and “Cher”).
--x Create a table for Albums with fields for id, title, release date, and a reference to an artist. Album titles and  release dates cannot be null.
--x Create a table for Tracks with fields for id, title, and a reference to an album. Track titles cannot be null.
--x Use the ALTER command to add a column to Tracks for “rating”. The rating must be an integer between 0 and 5 (inclusive).
--x Use the ALTER command to add an integrity constraint to Artists to ensure the lastname, firstname combination is unique.
--x Use INSERT commands to insert at least two albums, two artists, and at least two tracks per album.
--x Use an INSERT command (that will fail when run) to show that you cannot enter a track with without a title.
--x Use an INSERT command (that will fail when run) to show that you cannot add an album with an artist that doesn’t exist. 
-- Write a query to select the all the track titles with a rating greater than or equal to 4.

SPOOL hw10.out
SET ECHO ON

DROP TABLE tracks;
DROP TABLE albums;
DROP TABLE artist;

CREATE TABLE artist (
	artist_id INTEGER PRIMARY KEY,
	fname VARCHAR2(15),
	lname VARCHAR2(15) NOT NULL
);
--

CREATE TABLE albums (
	album_id INTEGER PRIMARY KEY,
	title VARCHAR2(50),
	release_date DATE,
	artist_id INTEGER,
	CONSTRAINT con_artist FOREIGN KEY (artist_id) REFERENCES artist(artist_id)
);
--

CREATE TABLE tracks (
	track_id INTEGER PRIMARY KEY,
	title VARCHAR2(50) NOT NULL,
	album_id INTEGER ,
	CONSTRAINT con_album FOREIGN KEY (album_id) REFERENCES albums(album_id)
);
--

ALTER TABLE tracks ADD rating INTEGER;
ALTER TABLE tracks ADD CONSTRAINT tRating CHECK(rating >= 0 AND rating < 6);
ALTER TABLE artist ADD CONSTRAINT aUnique UNIQUE (fname,lname);
--
/*
Accepted Inserts
*/

INSERT INTO artist 
VALUES (1, NULL, 'Chicago');

INSERT INTO artist 
VALUES (2, NULL, 'Magic!');
--

INSERT INTO albums
VALUES (1, 'Chicago 17', to_date('May-14-1984', 'MM-DD-YYYY'), 1);

INSERT INTO albums
VALUES (2, 'Dont kill the magic', to_date('June-30-2014', 'MM-DD-YYYY'), 1);
--

INSERT INTO tracks
VALUES (1, 'Your the Inspiration', 1, 5);

INSERT INTO tracks
VALUES (2, 'Rude', 2, 4);
--
/*
Failed Inserts
*/

INSERT INTO tracks
VALUES (1, NULL, 3);
--

INSERT INTO albums
VALUES (3, 'Name', to_date('June-30-2014', 3);
--
SELECT *
FROM tracks
WHERE rating >= 4;
--

SET ECHO OFF
SPOOL OFF
