-- File: create.sql
/*
This script creates the TABLES and adds the CONSTRAINTS for the SQL Project 
BY: Victor Sun
	_______
	_______
	_______
*/
create table Location (
	location_id INTEGER PRIMARY KEY,
	location_name VARCHAR(50) NOT NULL,
	colonized CHAR(1) NOT NULL,
	star_size VARCHAR(15) NOT NULL,
	CONSTRAINT IC_LOCATION1 CHECK(star_size in ('red dwarf', 'red giant', 'white dwarf', 'pulsar', 'black hole')),
	CONSTRAINT IC_LOCATION2 CHECK(colonized in ('Y' , 'N'))
);
--
create table Fleet (
	fleet_id INTEGER primary key,
	fleet_name VARCHAR(50) not null,
	fleet_rank VARCHAR(15) not null,
	fleet_location_id INTEGER not null,
	constraint IC_FLEET1 foreign key (fleet_location_id) references Location(location_id),
	constraint IC_FLEET2 check (fleet_rank in ('bronze', 'silver', 'gold'))
);
--
create table Ship_Design (
	design_id INTEGER PRIMARY KEY,
	design_name VARCHAR2(15) not null,
	design_color VARCHAR2(8) not null,
	design_class VARCHAR2(10) not null,
	CONSTRAINT IC_SHIP_DES1 CHECK (design_color in ('Red', 'Yellow', 'Blue', 'Green')),
	CONSTRAINT IC_SHIP_DES2 CHECK (design_class in ('assault', 'escort', 'cruiser', 'capital', 'carrier'))
);
--
create table Ship (
	ship_id INTEGER primary key,
	ship_name VARCHAR2(15) not null,
	parent_fleet_id INTEGER not null,
	ship_design_id INTEGER not null,
	joined_on date not null,
	constraint IC_SHIP1 foreign key (parent_fleet_id) references Fleet(fleet_id),
	constraint IC_SHIP2 foreign key (ship_design_id) references Ship_Design(design_id)
);
--
create table Captain (
	ship_id INTEGER,
	name VARCHAR(15),
	start_date DATE not null,
	PRIMARY KEY (ship_id, name),
	constraint IC_CAPTAIN1 foreign key (ship_id) references Ship(ship_id) on delete cascade
);
--
create table Weapons (
	ship_id INTEGER,
	weapon VARCHAR(15),
	design_name VARCHAR(15),
	PRIMARY KEY(ship_id, weapon),
	CONSTRAINT IC_WEAPONS1 FOREIGN KEY (ship_id) REFERENCES Ship(ship_id),
	CONSTRAINT IC_WEAPONS2 CHECK (weapon IN ('Laser', 'Rockets', 'Mines', 'Cannon', 'Flares')),
	CONSTRAINT IC_WEAPONS3 CHECK (NOT (weapon = 'Canon' OR weapon = 'Rocket' AND design_name = 'Carrier'))
);
--
create table Missions (
	mission_id INTEGER PRIMARY KEY,
	reward INTEGER,
	difficulty VARCHAR(15),
	description VARCHAR(100),
	CONSTRAINT IC_Missions1 CHECK (difficulty IN ('Easy', 'Medium', 'Hard')),
	CONSTRAINT IC_Missions2 CHECK (NOT (difficulty = 'Easy' AND reward >= 500)),
	CONSTRAINT IC_Missions3 CHECK (NOT (difficulty = 'Medium' AND (reward < 500 OR reward > 1000))),
	CONSTRAINT IC_Missions4 CHECK (NOT (difficulty = 'Hard' AND ( reward <= 1000))) 
);
--
create table Assigned_Missions(
	mission_id INTEGER,
	fleet_id INTEGER,
	CONSTRAINT IC_Assigned_Mission1 FOREIGN KEY (mission_id) REFERENCES Missions (mission_id),
	CONSTRAINT IC_Assigned_Mission2 FOREIGN KEY (fleet_id) REFERENCES Fleet (fleet_id)
);