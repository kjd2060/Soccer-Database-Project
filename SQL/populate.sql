LOAD DATA LOCAL INFILE 'C:/Users/Kevin/Documents/_Data_Management/src/SQL/P1 Sample Data - Location.csv'
INTO TABLE location  
    FIELDS TERMINATED BY ',' 
           OPTIONALLY ENCLOSED BY '"'
    LINES  TERMINATED BY '\n' -- or \r\n
    IGNORE 1 ROWS
(loc_id,Country,City);

INSERT INTO league VALUES ('db league',3,NULL,NULL);

set foreign_key_checks = 0;
LOAD DATA LOCAL INFILE 'C:/Users/Kevin/Documents/_Data_Management/src/SQL/P1 Sample Data - Team.csv'
INTO TABLE team
    FIELDS TERMINATED BY ',' 
           OPTIONALLY ENCLOSED BY '"'
    LINES  TERMINATED BY '\n' -- or \r\n
    IGNORE 1 ROWS
(name,location_loc_id,Wins,Ties,Losses,league_name);

UPDATE team set league_name = 'db league';
UPDATE league SET 1st_place='Germany' WHERE name='db league';

LOAD DATA LOCAL INFILE 'C:/Users/Kevin/Documents/_Data_Management/src/SQL/P1 Sample Data - Staff.csv'
INTO TABLE staff
    FIELDS TERMINATED BY ',' 
           OPTIONALLY ENCLOSED BY '"'
    LINES  TERMINATED BY '\n' -- or \r\n
    IGNORE 1 ROWS
(fname,minit,lname,role,team_Name);

LOAD DATA LOCAL INFILE 'C:/Users/Kevin/Documents/_Data_Management/src/SQL/P1 Sample Data - Player.csv'
INTO TABLE player
    FIELDS TERMINATED BY ',' 
           OPTIONALLY ENCLOSED BY '"'
    LINES  TERMINATED BY '\n' -- or \r\n
    IGNORE 1 ROWS
(fname,minit,lname,player_num,position,team_Name,caps,goals);

LOAD DATA LOCAL INFILE 'C:/Users/Kevin/Documents/_Data_Management/src/SQL/P1 Sample Data - Match.csv'
INTO TABLE mydb.match
    FIELDS TERMINATED BY ',' 
           OPTIONALLY ENCLOSED BY '"'
    LINES  TERMINATED BY '\n' -- or \r\n
    IGNORE 1 ROWS
(idmatch,date,hscore,ascore);

LOAD DATA LOCAL INFILE 'C:/Users/Kevin/Documents/_Data_Management/src/SQL/P1 Sample Data - Plays Home.csv'
INTO TABLE plays_home
    FIELDS TERMINATED BY ',' 
           OPTIONALLY ENCLOSED BY '"'
    LINES  TERMINATED BY '\n' -- or \r\n
    IGNORE 1 ROWS
(match_idmatch,team_Name);

LOAD DATA LOCAL INFILE 'C:/Users/Kevin/Documents/_Data_Management/src/SQL/P1 Sample Data - Plays Away.csv'
INTO TABLE plays_away
    FIELDS TERMINATED BY ',' 
           OPTIONALLY ENCLOSED BY '"'
    LINES  TERMINATED BY '\n' -- or \r\n
    IGNORE 1 ROWS
(match_idmatch,team_Name);
set foreign_key_checks = 1;