DROP DATABASE IF EXISTS tabletopdb;
CREATE DATABASE IF NOT EXISTS tabletopdb;

USE tabletopdb;

CREATE TABLE session (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  location VARCHAR(500) NOT NULL,
  start_time DATETIME,
  campaign TEXT
);

DROP USER IF EXISTS gamer@localhost;
CREATE USER IF NOT EXISTS gamer@localhost IDENTIFIED BY 'gamer';
GRANT SELECT,INSERT,UPDATE,DELETE ON tabletopdb.* TO gamer@localhost;

INSERT INTO session (id, location, campaign) VALUES (1, 'DM House', 'Spelljammer');
INSERT INTO session (id, location, campaign) VALUES (2, 'Online', 'Dragon Hunters');
INSERT INTO session (id, location, campaign) VALUES (3, 'DM House', 'Tape that kills everyone');