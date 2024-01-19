-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema tabletopdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `tabletopdb` ;

-- -----------------------------------------------------
-- Schema tabletopdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `tabletopdb` DEFAULT CHARACTER SET utf8 ;
USE `tabletopdb` ;

-- -----------------------------------------------------
-- Table `game`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `game` ;

CREATE TABLE IF NOT EXISTS `game` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(250) NOT NULL,
  `description` TEXT NULL DEFAULT NULL,
  `image_url` VARCHAR(500) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `player`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `player` ;

CREATE TABLE IF NOT EXISTS `player` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `address` VARCHAR(500) NULL DEFAULT NULL,
  `username` VARCHAR(25) NOT NULL,
  `password` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `campaign`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `campaign` ;

CREATE TABLE IF NOT EXISTS `campaign` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(400) NOT NULL,
  `storyteller` INT(11) NOT NULL,
  `game_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_campaign_player` (`storyteller` ASC),
  INDEX `fk_campaign_game` (`game_id` ASC),
  CONSTRAINT `fk_campaign_game`
    FOREIGN KEY (`game_id`)
    REFERENCES `game` (`id`),
  CONSTRAINT `fk_campaign_player`
    FOREIGN KEY (`storyteller`)
    REFERENCES `player` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `adventurer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `adventurer` ;

CREATE TABLE IF NOT EXISTS `adventurer` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(75) NOT NULL,
  `class` VARCHAR(100) NULL DEFAULT NULL,
  `level` TINYINT(3) NULL DEFAULT NULL,
  `player_id` INT(11) NOT NULL,
  `campaign_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_adventurer_player` (`player_id` ASC),
  INDEX `fk_adventurer_campaign` (`campaign_id` ASC),
  CONSTRAINT `fk_adventurer_campaign`
    FOREIGN KEY (`campaign_id`)
    REFERENCES `campaign` (`id`),
  CONSTRAINT `fk_adventurer_player`
    FOREIGN KEY (`player_id`)
    REFERENCES `player` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 18
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `campaign_player`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `campaign_player` ;

CREATE TABLE IF NOT EXISTS `campaign_player` (
  `campaign_id` INT(11) NOT NULL,
  `player_id` INT(11) NOT NULL,
  PRIMARY KEY (`campaign_id`, `player_id`),
  INDEX `fk_campaign_player_player` (`player_id` ASC),
  CONSTRAINT `fk_campaign_player_campaign`
    FOREIGN KEY (`campaign_id`)
    REFERENCES `campaign` (`id`),
  CONSTRAINT `fk_campaign_player_player`
    FOREIGN KEY (`player_id`)
    REFERENCES `player` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `meeting`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `meeting` ;

CREATE TABLE IF NOT EXISTS `meeting` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `location` VARCHAR(500) NOT NULL,
  `start_time` DATETIME NOT NULL,
  `campaign_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_meeting_campaign` (`campaign_id` ASC),
  CONSTRAINT `fk_meeting_campaign`
    FOREIGN KEY (`campaign_id`)
    REFERENCES `campaign` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
