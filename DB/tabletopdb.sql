DROP DATABASE IF EXISTS tabletopdb;
CREATE DATABASE IF NOT EXISTS tabletopdb;

USE tabletopdb;

CREATE TABLE game (
  id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(250) NOT NULL,
  version DECIMAL(5, 2),
  image_url VARCHAR(500)
);

CREATE TABLE player (
  id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  address VARCHAR(500)
);


CREATE TABLE meeting (
  id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  location VARCHAR(500) NOT NULL,
  start_time DATETIME,
  campaign TEXT,
  storyteller INTEGER NOT NULL,
  CONSTRAINT fk_meeting_player FOREIGN KEY (storyteller) REFERENCES player (id),
  game_id INTEGER NOT NULL,
  CONSTRAINT fk_meeting_game FOREIGN KEY (game_id) REFERENCES game (id)
);

CREATE TABLE meeting_player (
  meeting_id INTEGER NOT NULL,
  player_id INTEGER NOT NUll,
  PRIMARY KEY (meeting_id,player_id),
  KEY fk_meeting_player_player (player_id),
  CONSTRAINT fk_meeting_player_meeting FOREIGN KEY (meeting_id) REFERENCES meeting (id),
  CONSTRAINT fk_meeting_player_player FOREIGN KEY (player_id) REFERENCES player (id)
);

CREATE TABLE adventurer (
  id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(75) NOT NULL,
  class VARCHAR(100),
  level TINYINT(3),
  player_id INTEGER NOT NULL,
  CONSTRAINT fk_adventurer_player FOREIGN KEY (player_id) REFERENCES player (id),
  game_id INTEGER NOT NULL,
  CONSTRAINT fk_adventurer_game FOREIGN KEY (game_id) REFERENCES game (id)
);

DROP USER IF EXISTS gamer@localhost;
CREATE USER IF NOT EXISTS gamer@localhost IDENTIFIED BY 'gamer';
GRANT SELECT,INSERT,UPDATE,DELETE ON tabletopdb.* TO gamer@localhost;

INSERT INTO game (id, name, version, image_url) VALUES (1, "Dungeons & Dragons", 5.1, "https://i.pinimg.com/originals/39/01/27/390127bb4051d747c356740d888d2aed.png");
INSERT INTO game (id, name, version, image_url) VALUES (2, "Dungeons & Dragons", 4, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR71iTzwZHopqk_fiIbYLFlYizCdN4VWlGOkQ&usqp=CAU");
INSERT INTO game (id, name, version, image_url) VALUES (3, "Shadowrun", 4, "https://images-worker.bonanzastatic.com/afu/images/bc74/9bd4/b12e_11094945179/512gr82e9wl._sl1500_.jpg");
INSERT INTO game (id, name, version, image_url) VALUES (4, "Changeling", 5.1, "https://online.anyflip.com/dhfzu/cvwl/files/mobile/2.jpg?1645946768");
INSERT INTO game (id, name, version, image_url) VALUES (5, "Vampire", 5.1, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQDuJ0yLkx6RXeadIzLkjZs7codIZi-R3qYo-xVBUBj_jXuejwX8KS91EcQZX1Y8qszrCI&usqp=CAU");
INSERT INTO game (id, name, version, image_url) VALUES (6, "Shadowrun", 3, "https://m.media-amazon.com/images/W/MEDIAX_792452-T2/images/I/61QeFr8SHbL._AC_UF1000,1000_QL80_.jpg");


INSERT INTO player (id, first_name, last_name, address) VALUES (1, 'Jacob', 'Stuart', "623 4th Ave. Kingman AZ 86401");
INSERT INTO adventurer (id, name, class, level, player_id, game_id) VALUES (3, 'Twyll', 'Wizard', 5, 1, 1);
INSERT INTO adventurer (id, name, class, level, player_id, game_id) VALUES (1, "Uthjack", "Barbarian", 9, 1, 2);
INSERT INTO adventurer (id, name, class, player_id, game_id) VALUES (17, 'Seraphim', 'Nocker', 1, 4);

INSERT INTO player (id, first_name, last_name, address) VALUES (2, 'Sophia', 'Smith', '321 Maple Ln. Raleigh NC 27601');
INSERT INTO adventurer (id, name, class, level, player_id, game_id) VALUES (2, 'Aria', 'Rogue', 7, 2, 1);
INSERT INTO adventurer (id, name, class, level, player_id, game_id) VALUES (5, 'Erik', 'Fighter', 8, 2, 2);


INSERT INTO player (id, first_name, last_name, address) VALUES (3, 'Daniel', 'Miller', '987 Birch Dr. Austin TX 78701');
INSERT INTO adventurer (id, name, class, level, player_id, game_id) VALUES (4, 'Luna', 'Cleric', 6, 3, 1);
INSERT INTO adventurer (id, name, class, level, player_id, game_id) VALUES (6, 'Sylvia', 'Sorcerer', 4, 3, 2);

INSERT INTO player (id, first_name, last_name, address) VALUES (4, 'Olivia', 'Taylor', '654 Cedar Rd. Portland OR 97201');
INSERT INTO adventurer (id, name, class, level, player_id, game_id) VALUES (7, 'Darius', 'Paladin', 7, 4, 1);
INSERT INTO adventurer (id, name, class, level, player_id, game_id) VALUES (8, 'Fiona', 'Bard', 6, 4, 2);

INSERT INTO player (id, first_name, last_name, address) VALUES (5, 'Ethan', 'Wilson', '789 Elm Blvd. Nashville TN 37201');
INSERT INTO adventurer (id, name, class, player_id, game_id) VALUES (9, 'Razor', 'Street Samurai', 5, 3);
INSERT INTO adventurer (id, name, class, player_id, game_id) VALUES (10, 'Briar Rose', 'Sidhe', 5, 4);

INSERT INTO player (id, first_name, last_name, address) VALUES (6, 'Mason', 'Davis', '876 Pinecrest Rd. Atlanta GA 30301');
INSERT INTO adventurer (id, name, class, player_id, game_id) VALUES (11, 'Mirage', 'Decker', 6, 3);
INSERT INTO adventurer (id, name, class, player_id, game_id) VALUES (12, 'Hogan', 'Boggin', 6, 4);

INSERT INTO player (id, first_name, last_name, address) VALUES (7, 'Isabella', 'Clark', '543 Birchwood Dr. Phoenix AZ 85001');
INSERT INTO adventurer (id, name, class, player_id, game_id) VALUES (13, 'Nightshade', 'Mage', 7,3);
INSERT INTO adventurer (id, name, class, player_id, game_id) VALUES (14, 'Renefro', 'Ventrue', 7, 5);

INSERT INTO player (id, first_name, last_name, address) VALUES (8, 'Emma', 'White', '654 Maplewood Rd. Las Vegas NV 89101');
INSERT INTO adventurer (id, name, class, player_id, game_id) VALUES (15, 'Ghostrider', 'Urban Brawler', 8, 3);
INSERT INTO adventurer (id, name, class, player_id, game_id) VALUES (16, 'Isabella', 'Lasombra', 8, 5);

INSERT INTO meeting (id, location, campaign, storyteller, game_id) VALUES (1, 'DM House', 'Spelljammer session 0', 5, 1);
INSERT INTO meeting (id, location, campaign, storyteller, game_id) VALUES (2, 'DM House', 'Dragon Hunting session 0', 1, 2);
INSERT INTO meeting (id, location, campaign, storyteller, game_id) VALUES (3, 'Ethans House', 'Artifacts unbound session 0', 1, 3);
INSERT INTO meeting (id, location, campaign, storyteller, game_id) VALUES (4, 'Online', 'Autumn is coming', 7, 4);
INSERT INTO meeting (id, location, campaign, storyteller, game_id) VALUES (5, 'Online', 'Devils Night', 1, 5);
INSERT INTO meeting (id, location, campaign, storyteller, game_id) VALUES (6, 'Online', 'Spelljammer session 1', 5, 1);

INSERT INTO meeting_player (meeting_id, player_id) VALUES (1,1);
INSERT INTO meeting_player (meeting_id, player_id) VALUES (1,2);
INSERT INTO meeting_player (meeting_id, player_id) VALUES (1,3);
INSERT INTO meeting_player (meeting_id, player_id) VALUES (1,4);
INSERT INTO meeting_player (meeting_id, player_id) VALUES (2,1);
INSERT INTO meeting_player (meeting_id, player_id) VALUES (2,2);
INSERT INTO meeting_player (meeting_id, player_id) VALUES (2,3);
INSERT INTO meeting_player (meeting_id, player_id) VALUES (2,4);
INSERT INTO meeting_player (meeting_id, player_id) VALUES (3,5);
INSERT INTO meeting_player (meeting_id, player_id) VALUES (3,6);
INSERT INTO meeting_player (meeting_id, player_id) VALUES (3,7);
INSERT INTO meeting_player (meeting_id, player_id) VALUES (3,8);
INSERT INTO meeting_player (meeting_id, player_id) VALUES (4,5);
INSERT INTO meeting_player (meeting_id, player_id) VALUES (4,6);
INSERT INTO meeting_player (meeting_id, player_id) VALUES (5,7);
INSERT INTO meeting_player (meeting_id, player_id) VALUES (5,8);
INSERT INTO meeting_player (meeting_id, player_id) VALUES (6,1);
INSERT INTO meeting_player (meeting_id, player_id) VALUES (6,2);
INSERT INTO meeting_player (meeting_id, player_id) VALUES (6,3);
INSERT INTO meeting_player (meeting_id, player_id) VALUES (6,4);
