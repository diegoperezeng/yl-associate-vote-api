CREATE DATABASE IF NOT EXISTS associates_votes_db;

CREATE TABLE IF NOT EXISTS topic (
  id INT(11) NOT NULL AUTO_INCREMENT,
  title VARCHAR(255) NOT NULL,
  description TEXT NOT NULL,
  open_status TINYINT(1) NOT NULL DEFAULT 1,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS  associate (
  id BIGINT NOT NULL,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS  section (
  id INT(11) NOT NULL AUTO_INCREMENT,
  topic_id INT(11) NOT NULL,
  start_time TIMESTAMP NOT NULL,
  end_time TIMESTAMP,
  is_open TINYINT(1) NOT NULL DEFAULT 0,
  vote_count_yes INT(11) NOT NULL DEFAULT 0,
  vote_count_no  INT(11) NOT NULL DEFAULT 0,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (topic_id) REFERENCES topic(id)
);

CREATE TABLE IF NOT EXISTS  vote (
  id INT(11) NOT NULL AUTO_INCREMENT,
  section_id INT(11) NOT NULL,
  associate_id BIGINT NOT NULL,
  vote_choice TINYINT(1) NOT NULL DEFAULT 0,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (section_id) REFERENCES section(id),
  FOREIGN KEY (associate_id) REFERENCES associate(id)
);

##"YES" COUNT OF A PARTICULAR TOPIC ON THE REGISTRATION OF A VOTE
#DROP TRIGGER vote_update_yes_count_on_insert;
DELIMITER $$
CREATE TRIGGER vote_update_yes_count_on_insert
AFTER INSERT ON vote
FOR EACH ROW 
BEGIN
    UPDATE section SET vote_count_yes = (
        SELECT COUNT(*) 
        FROM (
        SELECT * FROM vote 
        WHERE vote_choice = 1 AND section_id = NEW.section_id) v
    ) WHERE id = NEW.section_id;
END $$
DELIMITER ;

#DROP TRIGGER vote_update_yes_count_on_update;
DELIMITER $$
CREATE TRIGGER vote_update_yes_count_on_update AFTER UPDATE ON vote
FOR EACH ROW 
BEGIN
    UPDATE section SET vote_count_yes = (
        SELECT COUNT(*) 
        FROM (
        SELECT * FROM vote 
        WHERE vote_choice = 1 AND section_id = NEW.section_id) v
    ) WHERE id = NEW.section_id;
END $$
DELIMITER ;

##"NO" COUNT OF A PARTICULAR TOPIC ON THE REGISTRATION OF A VOTE
#DROP TRIGGER vote_update_no_count_on_insert;
DELIMITER $$
CREATE TRIGGER vote_update_no_count_on_insert AFTER INSERT ON vote
FOR EACH ROW 
BEGIN
    UPDATE section SET vote_count_no = (
        SELECT COUNT(*) 
        FROM (
        SELECT * FROM vote 
        WHERE vote_choice = 0 AND section_id = NEW.section_id) v
    ) WHERE id = NEW.section_id;
END $$
DELIMITER ;

#DROP TRIGGER vote_update_no_count_on_update;
DELIMITER $$
CREATE TRIGGER vote_update_no_count_on_update AFTER UPDATE ON vote
FOR EACH ROW 
BEGIN
    UPDATE section SET vote_count_no = (
        SELECT COUNT(*) 
        FROM (
        SELECT * FROM vote 
        WHERE vote_choice = 0 AND section_id = NEW.section_id) v
    ) WHERE id = NEW.section_id;
END $$
DELIMITER ;

##CHECK IF THERE IS NO END TIME GIVEN AND THEN PUT 60 SECONDS IN END TIME
#DROP TRIGGER section_end_time_update_on_insert;
DELIMITER $$
CREATE TRIGGER section_end_time_update_on_insert
BEFORE INSERT ON section
FOR EACH ROW
BEGIN
SET NEW.end_time = COALESCE(NEW.end_time, DATE_ADD(NEW.start_time, INTERVAL 60 SECOND));
END$$
DELIMITER ;

#DROP TRIGGER section_end_time_update_on_update;
DELIMITER $$
CREATE TRIGGER section_end_time_update_on_update 
BEFORE UPDATE ON section
FOR EACH ROW 
BEGIN
	IF NEW.end_time IS NULL THEN
		SET NEW.end_time = DATE_ADD(NEW.start_time, INTERVAL 60 SECOND);
    END IF;
END $$
DELIMITER ;

##CHECK IF THE DIFERENCE BETWEEN END AND START TIME IS 60 SECONDS
#DROP TRIGGER check_end_time_duration_on_insert;
DELIMITER $$
CREATE TRIGGER check_end_time_duration_on_insert
BEFORE INSERT ON section
FOR EACH ROW
BEGIN
	IF NEW.end_time IS NOT NULL THEN
		IF NEW.end_time <= DATE_ADD(NEW.start_time, INTERVAL 59 SECOND) THEN
			SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'End time must be at least 60 seconds after start time';
		END IF;
	END IF;
END;
$$
DELIMITER ;

#DROP TRIGGER check_end_time_duration_on_update;
DELIMITER $$
CREATE TRIGGER check_end_time_duration_on_update
BEFORE UPDATE ON section
FOR EACH ROW
BEGIN
	IF NEW.end_time IS NOT NULL THEN
		IF NEW.end_time <= DATE_ADD(NEW.start_time, INTERVAL 59 SECOND) THEN
			SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'End time must be at least 60 seconds after start time';
		END IF;
	END IF;
END;
$$
DELIMITER ;

##CHECKING IF THE VOTE IS OCURRING BETWEEN THE START AND END TIMES OF THE SECTION
#DROP TRIGGER check_if_section_is_open_on_insert;
DELIMITER $$
CREATE TRIGGER check_if_section_is_open_on_insert 
BEFORE INSERT ON vote
FOR EACH ROW
BEGIN
    DECLARE section_start_time DATETIME;
    DECLARE section_end_time DATETIME;
    DECLARE section_is_open INT;

    SELECT start_time, end_time, is_open 
    INTO section_start_time, section_end_time, section_is_open
    FROM section
    WHERE id = NEW.section_id;

    IF section_is_open = 0 OR NEW.created_at < section_start_time OR NEW.created_at > section_end_time THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'The vote time is outside of the section start and end times, or the section is closed';
    END IF;
END $$
DELIMITER ;

##CHECKING IF THE ASSOCIATE ONLY VOTES ONE TIME IN A SECTION
#DROP TRIGGER vote_check_unique_associate_vote;
DELIMITER $$
CREATE TRIGGER vote_check_unique_associate_vote
BEFORE INSERT ON vote
FOR EACH ROW 
BEGIN
    DECLARE vote_exists INT DEFAULT 0;
    
    SELECT COUNT(*) INTO vote_exists
    FROM vote
    WHERE section_id = NEW.section_id AND associate_id = NEW.associate_id;
    
    IF vote_exists > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Associate has already voted on this section topic';
    END IF;
END $$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER topic_check_on_insert
BEFORE INSERT ON section
FOR EACH ROW
BEGIN
  DECLARE count_rows INT;
  
  SET count_rows = (SELECT COUNT(*) FROM section WHERE topic_id = NEW.topic_id);

  IF count_rows > 0 THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Topic already registered in the section';
  END IF;
END$$
DELIMITER ;
