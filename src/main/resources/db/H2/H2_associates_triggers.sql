--"YES" COUNT OF A PARTICULAR TOPIC ON THE REGISTRATION OF A VOTE
CREATE TRIGGER vote_update_yes_count_on_insert
AFTER INSERT ON vote
FOR EACH ROW
UPDATE session s SET vote_count_yes = (
  SELECT COUNT(*) as count
  FROM vote
  WHERE vote_choice = 1 AND session_id = NEW.session_id
) WHERE s.id = NEW.session_id;

CREATE TRIGGER vote_update_yes_count_on_update 
AFTER UPDATE ON vote
FOR EACH ROW 
MERGE INTO session s
USING ( 
    SELECT COUNT(*) count, session_id 
    FROM vote 
    WHERE vote_choice = 1 AND session_id = NEW.session_id
) v 
ON s.id = v.session_id 
WHEN MATCHED THEN UPDATE SET s.vote_count_yes = v.count;

--"NO" COUNT OF A PARTICULAR TOPIC ON THE REGISTRATION OF A VOTE
CREATE TRIGGER vote_update_no_count_on_insert 
AFTER INSERT ON vote
FOR EACH ROW 
BEGIN
    UPDATE session SET vote_count_no = (
        SELECT COUNT(*) 
        FROM (
            SELECT * FROM vote 
            WHERE vote_choice = 0 AND session_id = NEW.session_id
        ) v
    ) WHERE id = NEW.session_id;
END;

CREATE TRIGGER vote_update_no_count_on_update
AFTER UPDATE ON vote
FOR EACH ROW 
BEGIN
    UPDATE session SET vote_count_no = (
        SELECT COUNT(*) 
        FROM (
        SELECT * FROM vote 
        WHERE vote_choice = 0 AND session_id = NEW.session_id) v
    ) WHERE id = NEW.session_id;
END;

--CHECK IF THERE IS NO END TIME GIVEN AND THEN PUT 60 SECONDS IN END TIME
CREATE TRIGGER session_end_time_update_on_insert
BEFORE INSERT ON session
FOR EACH ROW
BEGIN
SET NEW.end_time = COALESCE(NEW.end_time, DATEADD('SECOND', 60, NEW.start_time));
END;

CREATE TRIGGER session_end_time_update_on_update 
BEFORE UPDATE ON session
FOR EACH ROW 
BEGIN
	IF NEW.end_time IS NULL THEN
		SET NEW.end_time = DATEADD('SECOND', 60, NEW.start_time);
    END IF;
END;

--CHECK IF THE DIFFERENCE BETWEEN END AND START TIME IS 60 SECONDS
CREATE TRIGGER check_end_time_duration_on_insert
BEFORE INSERT ON session
FOR EACH ROW
BEGIN
IF NEW.end_time IS NOT NULL THEN
IF NEW.end_time <= DATEADD('SECOND', 59, NEW.start_time) THEN
SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'End time must be at least 60 seconds after start time';
END IF;
END IF;
END;

CREATE TRIGGER check_end_time_duration_on_update
BEFORE UPDATE ON session
FOR EACH ROW
BEGIN
IF NEW.end_time IS NOT NULL THEN
IF NEW.end_time <= DATEADD('SECOND', 59, NEW.start_time) THEN
SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'End time must be at least 60 seconds after start time';
END IF;
END IF;
END;

-- CHECKING IF THE VOTE IS OCURRING BETWEEN THE START AND END TIMES OF THE SESSION
CREATE TRIGGER check_if_session_is_open_on_insert 
BEFORE INSERT ON vote
FOR EACH ROW
BEGIN
    DECLARE session_start_time TIMESTAMP;
    DECLARE session_end_time TIMESTAMP;
    DECLARE session_is_open INT;

    SELECT start_time, end_time, is_open 
    INTO session_start_time, session_end_time, session_is_open
    FROM session
    WHERE id = NEW.session_id;

    IF session_is_open = 0 OR NEW.created_at < session_start_time OR NEW.created_at > session_end_time THEN
        CALL EXCEPTION 'The vote time is outside of the session start and end times, or the session is closed';
    END IF;
END;

-- CHECKING IF THE ASSOCIATE ONLY VOTES ONE TIME IN A SESSION
CREATE TRIGGER vote_check_unique_associate_vote
BEFORE INSERT ON vote
FOR EACH ROW 
BEGIN
    DECLARE vote_exists INT DEFAULT 0;
    
    SELECT COUNT(*) INTO vote_exists
    FROM vote
    WHERE session_id = NEW.session_id AND associate_id = NEW.associate_id;
    
    IF vote_exists > 0 THEN
        CALL EXCEPTION 'Associate has already voted on this session topic';
    END IF;
END;

-- CHECKING IF THE TOPIC ALREADY EXISTS IN A SESSION
CREATE TRIGGER topic_check_on_insert
BEFORE INSERT ON session
FOR EACH ROW
BEGIN
  DECLARE count_rows INT;
  
  SET count_rows = (SELECT COUNT(*) FROM session WHERE topic_id = NEW.topic_id);

  IF count_rows > 0 THEN
    CALL EXCEPTION 'Topic already registered in the session';
  END IF;
END;