INSERT INTO associate (cpf, name, email) VALUES
('54295175700', 'Obiwan Kenobi', 'General.kenobi@jedi.com'),
('75688850805', 'Anakin Skywalker', 'darth.vader@sith.com'),
('75828174811', 'Luke Skywalker', 'luke@jedi.com'),
('66110155308', 'Leia Organa', 'leia@rebel.com'),
('05981736050', 'Han Solo', 'han@smuggler.com'),
('69435223654', 'Yoda', 'masteryoda@jedi.com'),
('71905323430', 'Darth Sidious', 'emperor@sith.com');

INSERT INTO topic (title, description, open_status)
VALUES
('The Force', 'The power that surrounds and penetrates all life', 1),
('Jedi Code', 'The principles that guide the Jedi Order', 1),
('Sith Philosophy', 'The code of the Dark Side of the Force', 1),
('Lightsaber Combat', 'The art of fighting with the iconic weapon of the Jedi', 1),
('The Force and Destiny', 'The intertwined fates of the Jedi and the Sith', 1);

INSERT INTO session (topic_id, start_time, end_time,is_open) VALUES (1, NOW(), 	DATEADD(DAY, 20, NOW()), 1);
INSERT INTO session (topic_id, start_time, end_time,is_open) VALUES (2, NOW(), 	DATEADD(DAY, 20, NOW()), 0);
INSERT INTO session (topic_id, start_time, is_open) VALUES (3, NOW(), 0);