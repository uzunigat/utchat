CREATE DATABASE IF NOT EXISTS UTChat;
USE UTChat;

CREATE TABLE IF NOT EXISTS User (
	
	email VARCHAR(50),
	nickname VARCHAR(20) NOT NULL UNIQUE,
	password VARCHAR(30) NOT NULL,
	role enum ('Admin','Other'),
	PRIMARY KEY (email)

);

CREATE TABLE IF NOT EXISTS Chat (

	id int AUTO_INCREMENT,
	titre VARCHAR(50) NOT NULL UNIQUE,
	description VARCHAR(300),
	debut DATETIME NOT NULL,
	duree DATETIME NOT NULL,
	PRIMARY KEY (id)

);

CREATE TABLE IF NOT EXISTS Owner(
	
	email VARCHAR(50) REFERENCES User(email),
	id int REFERENCES Chat(id),
	owner tinyint(1) NOT NULL DEFAULT 0,
	PRIMARY KEY (email, id)

);

INSERT INTO user(email, nickname, password, role) VALUES ('admin@hotmail.com', 'admin', 'pass', 1);
INSERT INTO user(email, nickname, password, role) VALUES ('other@hotmail.com', 'other', 'pass', 2);