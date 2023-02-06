INSERT INTO associate (name, email) VALUES ('John Doe', 'johndoe@example.com');

INSERT INTO associate (name, email, created_at)
VALUES 
("Associate 2", "associate1@example.com", NOW()),
("Associate 3", "associate2@example.com", NOW()),
("Associate 4", "associate3@example.com", NOW()),
("Associate 5", "associate4@example.com", NOW()),
("Associate 6", "associate5@example.com", NOW());

INSERT INTO topic (title, description, status) VALUES ('First Topic', 'This is the description of the first topic', 1);

INSERT INTO session (topic_id, start_time) VALUES (1, NOW());

INSERT INTO vote (session_id, associate_id, vote_choice) VALUES (1, 1, 'yes');


DROP TRIGGER session_end_time_update_oninsert;
DROP TRIGGER session_end_time_update_onupdate;
DROP TRIGGER check_end_time_duration_oninsert;
DROP TRIGGER check_end_time_duration_onupdate;

SELECT * FROM associate;

UPDATE associate SET name = 'Associate 1', email = 'associate1@example.com' WHERE id = 1;
UPDATE associate SET email = 'associate6@example.com' WHERE id = 6;
DELETE FROM associate WHERE id IN (7,8,9,10,11,12);