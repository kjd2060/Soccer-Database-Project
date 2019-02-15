-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`location` ;

CREATE TABLE IF NOT EXISTS `mydb`.`location` (
  `Country` VARCHAR(45) NOT NULL,
  `City` VARCHAR(45) NOT NULL,
  `loc_id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`loc_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`match`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`match` ;

CREATE TABLE IF NOT EXISTS `mydb`.`match` (
  `idmatch` INT NOT NULL AUTO_INCREMENT,
  `hscore` INT NOT NULL,
  `ascore` INT NOT NULL,
  `date` DATE NOT NULL,
  PRIMARY KEY (`idmatch`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`league`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`league` ;

CREATE TABLE IF NOT EXISTS `mydb`.`league` (
  `name` VARCHAR(45) NOT NULL,
  `NumTeams` INT NOT NULL,
  `1st_place` VARCHAR(45) NULL,
  `match_idmatch` INT NULL,
  PRIMARY KEY (`name`),
  INDEX `fk_league_team1_idx` (`1st_place` ASC),
  INDEX `fk_league_match1_idx` (`match_idmatch` ASC),
  CONSTRAINT `fk_league_team1`
    FOREIGN KEY (`1st_place`)
    REFERENCES `mydb`.`team` (`Name`)
    ON DELETE CASCADE
    ON UPDATE CASCADE ,
  CONSTRAINT `fk_league_match1`
    FOREIGN KEY (`match_idmatch`)
    REFERENCES `mydb`.`match` (`idmatch`)
    ON DELETE CASCADE
    ON UPDATE CASCADE )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`team`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`team` ;

CREATE TABLE IF NOT EXISTS `mydb`.`team` (
  `Name` VARCHAR(45) NOT NULL,
  `Wins` INT NOT NULL,
  `Losses` INT NOT NULL,
  `Ties` INT NOT NULL,
  `location_loc_id` INT NOT NULL,
  `league_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Name`, `location_loc_id`, `league_name`),
  INDEX `fk_team_location_idx` (`location_loc_id` ASC),
  INDEX `fk_team_league1_idx` (`league_name` ASC),
  CONSTRAINT `fk_team_location`
    FOREIGN KEY (`location_loc_id`)
    REFERENCES `mydb`.`location` (`loc_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE ,
  CONSTRAINT `fk_team_league1`
    FOREIGN KEY (`league_name`)
    REFERENCES `mydb`.`league` (`name`)
    ON DELETE CASCADE
    ON UPDATE CASCADE )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`player`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`player` ;

CREATE TABLE IF NOT EXISTS `mydb`.`player` (
  `fname` VARCHAR(45) NOT NULL,
  `minit` CHAR(1) NULL,
  `lname` VARCHAR(45) NOT NULL,
  `goals` INT NOT NULL,
  `player_num` INT NOT NULL,
  `position` VARCHAR(5) NOT NULL,
  `caps` INT NOT NULL,
  `team_Name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`fname`, `lname`, `team_Name`),
  INDEX `fk_player_team1_idx` (`team_Name` ASC),
  CONSTRAINT `fk_player_team1`
    FOREIGN KEY (`team_Name`)
    REFERENCES `mydb`.`team` (`Name`)
    ON DELETE CASCADE
    ON UPDATE CASCADE )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`staff`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`staff` ;

CREATE TABLE IF NOT EXISTS `mydb`.`staff` (
  `fname` VARCHAR(45) NOT NULL,
  `minit` CHAR(1) NULL,
  `lname` VARCHAR(45) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  `team_Name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`fname`, `lname`, `team_Name`),
  INDEX `fk_staff_team1_idx` (`team_Name` ASC),
  CONSTRAINT `fk_staff_team1`
    FOREIGN KEY (`team_Name`)
    REFERENCES `mydb`.`team` (`Name`)
    ON DELETE CASCADE
    ON UPDATE CASCADE )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`plays_home`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`plays_home` ;

CREATE TABLE IF NOT EXISTS `mydb`.`plays_home` (
  `match_idmatch` INT NOT NULL,
  `team_Name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`match_idmatch`, `team_Name`),
  INDEX `fk_match_has_team_team1_idx` (`team_Name` ASC),
  INDEX `fk_match_has_team_match1_idx` (`match_idmatch` ASC),
  CONSTRAINT `fk_match_has_team_match1`
    FOREIGN KEY (`match_idmatch`)
    REFERENCES `mydb`.`match` (`idmatch`)
    ON DELETE CASCADE
    ON UPDATE CASCADE ,
  CONSTRAINT `fk_match_has_team_team1`
    FOREIGN KEY (`team_Name`)
    REFERENCES `mydb`.`team` (`Name`)
    ON DELETE CASCADE
    ON UPDATE CASCADE )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`plays_away`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`plays_away` ;

CREATE TABLE IF NOT EXISTS `mydb`.`plays_away` (
  `team_Name` VARCHAR(45) NOT NULL,
  `match_idmatch` INT NOT NULL,
  PRIMARY KEY (`team_Name`, `match_idmatch`),
  INDEX `fk_team_has_match_match1_idx` (`match_idmatch` ASC),
  INDEX `fk_team_has_match_team1_idx` (`team_Name` ASC),
  CONSTRAINT `fk_team_has_match_team1`
    FOREIGN KEY (`team_Name`)
    REFERENCES `mydb`.`team` (`Name`)
    ON DELETE CASCADE
    ON UPDATE CASCADE ,
  CONSTRAINT `fk_team_has_match_match1`
    FOREIGN KEY (`match_idmatch`)
    REFERENCES `mydb`.`match` (`idmatch`)
    ON DELETE CASCADE
    ON UPDATE CASCADE )
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=1;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;