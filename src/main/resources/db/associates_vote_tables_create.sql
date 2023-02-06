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
CREATE TABLE IF NOT EXISTS  session (
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
  session_id INT(11) NOT NULL,
  associate_id BIGINT NOT NULL,
  vote_choice TINYINT(1) NOT NULL DEFAULT 0,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (session_id) REFERENCES session(id),
  FOREIGN KEY (associate_id) REFERENCES associate(id)
);