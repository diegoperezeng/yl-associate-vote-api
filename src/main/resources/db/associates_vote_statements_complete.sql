CREATE DATABASE IF NOT EXISTS associates_votes_db;

CREATE TABLE IF NOT EXISTS topic (
  id BIGINT NOT NULL AUTO_INCREMENT,
  title VARCHAR(255) NOT NULL,
  description TEXT NOT NULL,
  open_status TINYINT DEFAULT 1,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS  associate (
  id BIGINT NOT NULL AUTO_INCREMENT,
  cpf  VARCHAR(255) NOT NULL UNIQUE,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS  session (
  id BIGINT NOT NULL AUTO_INCREMENT,
  topic_id BIGINT NOT NULL,
  start_time TIMESTAMP NOT NULL,
  end_time TIMESTAMP,
  is_open TINYINT DEFAULT 0,
  vote_count_yes BIGINT DEFAULT 0,
  vote_count_no  BIGINT DEFAULT 0,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (topic_id) REFERENCES topic(id)
);

CREATE TABLE IF NOT EXISTS  vote (
  id BIGINT NOT NULL AUTO_INCREMENT,
  session_id BIGINT NOT NULL,
  associate_id BIGINT NOT NULL,
  vote_choice TINYINT NOT NULL DEFAULT 0,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (session_id) REFERENCES session(id),
  FOREIGN KEY (associate_id) REFERENCES associate(id)
);

##"YES" COUNT OF A PARTICULAR TOPIC ON THE REGISTRATION OF A VOTE
#DROP TRIGGER vote_update_yes_count_on_insert;
DELIMITER $$
CREATE TRIGGER vote_update_yes_count_on_insert
AFTER INSERT ON vote
FOR EACH ROW 
BEGIN
    UPDATE session SET vote_count_yes = (
        SELECT COUNT(*) 
        FROM (
        SELECT * FROM vote 
        WHERE vote_choice = 1 AND session_id = NEW.session_id) v
    ) WHERE id = NEW.session_id;
END $$
DELIMITER ;

#DROP TRIGGER vote_update_yes_count_on_update;
DELIMITER $$
CREATE TRIGGER vote_update_yes_count_on_update AFTER UPDATE ON vote
FOR EACH ROW 
BEGIN
    UPDATE session SET vote_count_yes = (
        SELECT COUNT(*) 
        FROM (
        SELECT * FROM vote 
        WHERE vote_choice = 1 AND session_id = NEW.session_id) v
    ) WHERE id = NEW.session_id;
END $$
DELIMITER ;

##"NO" COUNT OF A PARTICULAR TOPIC ON THE REGISTRATION OF A VOTE
#DROP TRIGGER vote_update_no_count_on_insert;
DELIMITER $$
CREATE TRIGGER vote_update_no_count_on_insert AFTER INSERT ON vote
FOR EACH ROW 
BEGIN
    UPDATE session SET vote_count_no = (
        SELECT COUNT(*) 
        FROM (
        SELECT * FROM vote 
        WHERE vote_choice = 0 AND session_id = NEW.session_id) v
    ) WHERE id = NEW.session_id;
END $$
DELIMITER ;

#DROP TRIGGER vote_update_no_count_on_update;
DELIMITER $$
CREATE TRIGGER vote_update_no_count_on_update AFTER UPDATE ON vote
FOR EACH ROW 
BEGIN
    UPDATE session SET vote_count_no = (
        SELECT COUNT(*) 
        FROM (
        SELECT * FROM vote 
        WHERE vote_choice = 0 AND session_id = NEW.session_id) v
    ) WHERE id = NEW.session_id;
END $$
DELIMITER ;

##CHECK IF THERE IS NO END TIME GIVEN AND THEN PUT 60 SECONDS IN END TIME
#DROP TRIGGER session_end_time_update_on_insert;
DELIMITER $$
CREATE TRIGGER session_end_time_update_on_insert
BEFORE INSERT ON session
FOR EACH ROW
BEGIN
SET NEW.end_time = COALESCE(NEW.end_time, DATE_ADD(NEW.start_time, INTERVAL 60 SECOND));
END$$
DELIMITER ;

#DROP TRIGGER session_end_time_update_on_update;
DELIMITER $$
CREATE TRIGGER session_end_time_update_on_update 
BEFORE UPDATE ON session
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
BEFORE INSERT ON session
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
BEFORE UPDATE ON session
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
#DROP TRIGGER check_if_session_is_open_on_insert;
DELIMITER $$
CREATE TRIGGER check_if_session_is_open_on_insert 
BEFORE INSERT ON vote
FOR EACH ROW
BEGIN
    DECLARE session_start_time DATETIME;
    DECLARE session_end_time DATETIME;
    DECLARE session_is_open INT;

    SELECT start_time, end_time, is_open 
    INTO session_start_time, session_end_time, session_is_open
    FROM session
    WHERE id = NEW.session_id;

    IF session_is_open = 0 OR NEW.created_at < session_start_time OR NEW.created_at > session_end_time THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'The vote time is outside of the session start and end times, or the session is closed';
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
    WHERE session_id = NEW.session_id AND associate_id = NEW.associate_id;
    
    IF vote_exists > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Associate has already voted on this session topic';
    END IF;
END $$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER topic_check_on_insert
BEFORE INSERT ON session
FOR EACH ROW
BEGIN
  DECLARE count_rows INT;
  
  SET count_rows = (SELECT COUNT(*) FROM session WHERE topic_id = NEW.topic_id);

  IF count_rows > 0 THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Topic already registered in the session';
  END IF;
END$$
DELIMITER ;
