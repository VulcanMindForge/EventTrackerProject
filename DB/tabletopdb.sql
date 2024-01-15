DROP DATABASE IF EXISTS tabletopdb;
CREATE DATABASE IF NOT EXISTS tabletopdb;

USE tabletopdb;

CREATE TABLE game (
  id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(250) NOT NULL,
  description TEXT,
  image_url VARCHAR(500)
);

CREATE TABLE player (
  id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  address VARCHAR(500),
  username VARCHAR(25) NOT NULL,
  password VARCHAR(25) NOT NULL
);

CREATE TABLE campaign (
  id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(400) NOT NULL,
  storyteller INTEGER NOT NUll,
  CONSTRAINT fk_campaign_player FOREIGN KEY (storyteller) REFERENCES player (id),
  game_id INTEGER NOT NULL,
  CONSTRAINT fk_campaign_game FOREIGN KEY (game_id) REFERENCES game (id)
);

CREATE TABLE adventurer (
  id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(75) NOT NULL,
  class VARCHAR(100),
  level TINYINT(3),
  player_id INTEGER NOT NULL,
  CONSTRAINT fk_adventurer_player FOREIGN KEY (player_id) REFERENCES player (id),
  campaign_id INTEGER NOT NULL,
  CONSTRAINT fk_adventurer_campaign FOREIGN KEY (campaign_id) REFERENCES campaign (id)
);

CREATE TABLE campaign_player (
  campaign_id INTEGER NOT NULL,
  player_id INTEGER NOT NUll,
  PRIMARY KEY (campaign_id,player_id),
  KEY fk_campaign_player_player (player_id),
  CONSTRAINT fk_campaign_player_campaign FOREIGN KEY (campaign_id) REFERENCES campaign (id),
  CONSTRAINT fk_campaign_player_player FOREIGN KEY (player_id) REFERENCES player (id)
);

CREATE TABLE meeting (
  id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  location VARCHAR(500) NOT NULL,
  start_time DATETIME NOT NULL,
  campaign_id INTEGER NOT NULL,
  CONSTRAINT fk_meeting_campaign FOREIGN KEY (campaign_id) REFERENCES campaign (id)
);

DROP USER IF EXISTS gamer@localhost;
CREATE USER IF NOT EXISTS gamer@localhost IDENTIFIED BY 'gamer';
GRANT SELECT,INSERT,UPDATE,DELETE ON tabletopdb.* TO gamer@localhost;

INSERT INTO game (id, name, description, image_url) VALUES (1, "Dungeons & Dragons", '
Dungeons & Dragons 5th Edition (D&D 5E) is a popular tabletop role-playing game that serves as the latest iteration of the iconic fantasy RPG franchise. Developed by Wizards of the Coast, D&D 5E emphasizes streamlined rules, accessibility, and a balance between storytelling and mechanics. Players create characters with unique races and classes, embark on adventures guided by a Dungeon Master, and engage in imaginative storytelling, combat, and problem-solving within a fantastical world. The system incorporates advantage/disadvantage mechanics, simplified character creation, and flexible dungeon mastering tools, making it an inviting entry point for newcomers while retaining the depth and complexity that veteran players appreciate.', "https://i.pinimg.com/originals/39/01/27/390127bb4051d747c356740d888d2aed.png");
INSERT INTO game (id, name, description, image_url) VALUES (2, "Dungeons & Dragons", 'Dungeons & Dragons 4th Edition (D&D 4E) is a previous edition of the renowned tabletop role-playing game series created by Wizards of the Coast. Released in 2008, D&D 4E introduced significant changes to the games mechanics compared to its predecessor. It featured a more tactical combat system with powers structured around character classes, providing a wide array of abilities at different levels. The edition aimed to offer balanced gameplay and simplify certain aspects, making it accessible to new players. However, D&D 4E received mixed reactions for its departure from traditional role-playing elements and a strong focus on combat mechanics, leading to a diverse range of opinions within the D&D community.', "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR71iTzwZHopqk_fiIbYLFlYizCdN4VWlGOkQ&usqp=CAU");
INSERT INTO game (id, name, description, image_url) VALUES (3, "Shadowrun", '
Shadowrun 3rd Edition is a cyberpunk-themed tabletop role-playing game (RPG) published by FASA Corporation. Released in 1998, it is the third edition of the Shadowrun series. Set in a dystopian future blending elements of cyberpunk and fantasy, Shadowrun 3rd Edition allows players to assume the roles of shadowrunners - skilled mercenaries operating in a world where magic and advanced technology coexist. The game features a unique setting where megacorporations, magic, hacking, and traditional firearms play integral roles. Character creation involves selecting races, such as humans, elves, orks, and trolls, each with distinct traits. The system incorporates a combination of dice rolls and character attributes for resolving actions. Shadowrun 3rd Edition is celebrated for its immersive world-building and engaging mix of cyberpunk and fantasy elements.', "https://images-worker.bonanzastatic.com/afu/images/bc74/9bd4/b12e_11094945179/512gr82e9wl._sl1500_.jpg");
INSERT INTO game (id, name, description, image_url) VALUES (4, "Changeling", 'Changeling: The Dreaming is a tabletop role-playing game published by White Wolf Publishing. Released in 1995, it is part of the World of Darkness series. The game is set in a magical and fantastical world where the fae beings of folklore, known as changelings, coexist with the modern world. Changelings are individuals who have undergone a transformation after being kidnapped by the fae and taken to the mystical realm of Arcadia. Returning to the mortal world, they find themselves caught between their mortal lives and the magical fae existence. Players take on the roles of these changelings, navigating the complexities of dual existence, dealing with courtly politics, and facing the challenges of reclaiming their humanity. Changeling: The Dreaming explores themes of magic, dreams, and the struggle to balance the enchanting fae realm with the mundane reality.', "https://online.anyflip.com/dhfzu/cvwl/files/mobile/2.jpg?1645946768");
INSERT INTO game (id, name, description, image_url) VALUES (5, "Vampire", 'Vampire: The Masquerade is a dark and immersive tabletop role-playing game (RPG) set in the Gothic-Punk World of Darkness universe. First published in 1991 by White Wolf Publishing, the game has undergone multiple editions. Players take on the roles of vampires, navigating a complex and secretive society known as the Camarilla, where political intrigue, supernatural powers, and the constant struggle for survival define unlife. The game emphasizes storytelling, character development, and moral dilemmas, with players managing their characters hunger for blood (the "masquerade") and juggling the intricacies of vampire politics. Themes of personal horror, morality, and the eternal struggle between different vampire clans are central to Vampire: The Masquerade, making it a seminal work in the horror RPG genre.', "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQDuJ0yLkx6RXeadIzLkjZs7codIZi-R3qYo-xVBUBj_jXuejwX8KS91EcQZX1Y8qszrCI&usqp=CAU");
INSERT INTO game (id, name, description, image_url) VALUES (6, "Shadowrun", 'Shadowrun 4th Edition is a tabletop role-playing game set in a unique cyberpunk and fantasy hybrid universe. Released in 2005 by Catalyst Game Labs, it builds upon the Shadowrun series established themes. In this edition, players assume the roles of shadowrunners, skilled professionals undertaking covert and often illegal operations for various clients in a dystopian future. The game world incorporates magic, advanced technology, hacking, and diverse races, creating a rich and immersive environment. Character creation involves choosing a race, such as humans, elves, dwarves, orks, and trolls, each with its own distinct attributes. The system uses a combination of dice rolls and character skills to resolve actions. Shadowrun 4th Edition is known for its intricate storytelling, versatile character customization, and the integration of technology and magic in a seamless and engaging manner.', "https://m.media-amazon.com/images/W/MEDIAX_792452-T2/images/I/61QeFr8SHbL._AC_UF1000,1000_QL80_.jpg");

INSERT INTO player (id, first_name, last_name, address, username, password) VALUES (1, 'Jacob', 'Stuart', "623 4th Ave. Kingman AZ 86401", "JStuart", "JStuart");
INSERT INTO player (id, first_name, last_name, address, username, password) VALUES (2, 'Sophia', 'Smith', '321 Maple Ln. Raleigh NC 27601', "SSmith", "SSmith");
INSERT INTO player (id, first_name, last_name, address, username, password) VALUES (3, 'Daniel', 'Miller', '987 Birch Dr. Austin TX 78701', "DMiller", "DMiller");
INSERT INTO player (id, first_name, last_name, address, username, password) VALUES (4, 'Olivia', 'Taylor', '654 Cedar Rd. Portland OR 97201', "OTaylor", "OTaylor");
INSERT INTO player (id, first_name, last_name, address, username, password) VALUES (5, 'Ethan', 'Wilson', '789 Elm Blvd. Nashville TN 37201', "EWilson", "EWilson");
INSERT INTO player (id, first_name, last_name, address, username, password) VALUES (6, 'Mason', 'Davis', '876 Pinecrest Rd. Atlanta GA 30301', "MDavis", "MDavis");
INSERT INTO player (id, first_name, last_name, address, username, password) VALUES (7, 'Isabella', 'Clark', '543 Birchwood Dr. Phoenix AZ 85001', "IClark", "IClark");
INSERT INTO player (id, first_name, last_name, address, username, password) VALUES (8, 'Emma', 'White', '654 Maplewood Rd. Las Vegas NV 89101', "EWhite", "EWhite");

INSERT INTO campaign (id, name, storyteller, game_id) VALUES (1, 'Spelljammer Bebop', 5, 1);
INSERT INTO campaign (id, name, storyteller, game_id) VALUES (2, 'Artifacts Unbound', 1, 2);
INSERT INTO campaign (id, name, storyteller, game_id) VALUES (3, 'Corporate espionage', 1, 3);
INSERT INTO campaign (id, name, storyteller, game_id) VALUES (4, 'Autumn is coming', 7, 4);
INSERT INTO campaign (id, name, storyteller, game_id) VALUES (5, 'Land grab', 1, 5);

INSERT INTO adventurer (id, name, class, level, player_id, campaign_id) VALUES (3, 'Twyll', 'Wizard', 5, 1, 1);
INSERT INTO adventurer (id, name, class, level, player_id, campaign_id) VALUES (1, "Uthjack", "Barbarian", 9, 1, 2);
INSERT INTO adventurer (id, name, class, player_id, campaign_id) VALUES (17, 'Seraphim', 'Nocker', 1, 4);
INSERT INTO adventurer (id, name, class, level, player_id, campaign_id) VALUES (2, 'Aria', 'Rogue', 7, 2, 1);
INSERT INTO adventurer (id, name, class, level, player_id, campaign_id) VALUES (5, 'Erik', 'Fighter', 8, 2, 2);
INSERT INTO adventurer (id, name, class, level, player_id, campaign_id) VALUES (4, 'Luna', 'Cleric', 6, 3, 1);
INSERT INTO adventurer (id, name, class, level, player_id, campaign_id) VALUES (6, 'Sylvia', 'Sorcerer', 4, 3, 2);
INSERT INTO adventurer (id, name, class, level, player_id, campaign_id) VALUES (7, 'Darius', 'Paladin', 7, 4, 1);
INSERT INTO adventurer (id, name, class, level, player_id, campaign_id) VALUES (8, 'Fiona', 'Bard', 6, 4, 2);
INSERT INTO adventurer (id, name, class, player_id, campaign_id) VALUES (9, 'Razor', 'Street Samurai', 5, 3);
INSERT INTO adventurer (id, name, class, player_id, campaign_id) VALUES (10, 'Briar Rose', 'Sidhe', 5, 4);
INSERT INTO adventurer (id, name, class, player_id, campaign_id) VALUES (11, 'Mirage', 'Decker', 6, 3);
INSERT INTO adventurer (id, name, class, player_id, campaign_id) VALUES (12, 'Hogan', 'Boggin', 6, 4);
INSERT INTO adventurer (id, name, class, player_id, campaign_id) VALUES (13, 'Nightshade', 'Mage', 7,3);
INSERT INTO adventurer (id, name, class, player_id, campaign_id) VALUES (14, 'Renefro', 'Ventrue', 7, 5);
INSERT INTO adventurer (id, name, class, player_id, campaign_id) VALUES (15, 'Ghostrider', 'Urban Brawler', 8, 3);
INSERT INTO adventurer (id, name, class, player_id, campaign_id) VALUES (16, 'Isabella', 'Lasombra', 8, 5);

INSERT INTO meeting (id, location, start_time, campaign_id) VALUES (1, 'DM House', '2022-07-15 08:30:00', 1);
INSERT INTO meeting (id, location, start_time, campaign_id) VALUES (2, 'DM House', '2023-03-25 14:45:00', 2);
INSERT INTO meeting (id, location, start_time, campaign_id) VALUES (3, 'Ethans House', '2023-11-05 18:20:00', 3);
INSERT INTO meeting (id, location, start_time, campaign_id) VALUES (4, 'Online', '2024-05-10 10:00:00', 4);
INSERT INTO meeting (id, location, start_time, campaign_id) VALUES (5, 'Online', '2024-09-20 20:15:00', 5);
INSERT INTO meeting (id, location, start_time, campaign_id) VALUES (6, 'Online', '2025-02-03 12:30:00', 1);

INSERT INTO campaign_player (campaign_id, player_id) VALUES (1,1);
INSERT INTO campaign_player (campaign_id, player_id) VALUES (1,2);
INSERT INTO campaign_player (campaign_id, player_id) VALUES (1,3);
INSERT INTO campaign_player (campaign_id, player_id) VALUES (1,4);
INSERT INTO campaign_player (campaign_id, player_id) VALUES (2,1);
INSERT INTO campaign_player (campaign_id, player_id) VALUES (2,2);
INSERT INTO campaign_player (campaign_id, player_id) VALUES (2,3);
INSERT INTO campaign_player (campaign_id, player_id) VALUES (2,4);
INSERT INTO campaign_player (campaign_id, player_id) VALUES (3,5);
INSERT INTO campaign_player (campaign_id, player_id) VALUES (3,6);
INSERT INTO campaign_player (campaign_id, player_id) VALUES (3,7);
INSERT INTO campaign_player (campaign_id, player_id) VALUES (3,8);
INSERT INTO campaign_player (campaign_id, player_id) VALUES (4,5);
INSERT INTO campaign_player (campaign_id, player_id) VALUES (4,6);
INSERT INTO campaign_player (campaign_id, player_id) VALUES (5,7);
INSERT INTO campaign_player (campaign_id, player_id) VALUES (5,8);