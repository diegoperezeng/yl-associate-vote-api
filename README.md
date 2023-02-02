# yl-associate-vote-api
An aplication to control of the Associates vote calculation on business agenda. (Uma aplicação para controlar o cálculo dos votos sobre pautas de negócios dos associados.


## The Data Base:

The database structure for this project have the following tables:

    1. topic: This table would have the following columns:
        - id (primary key)
        - title (string)
        - description (string)
        - Status (boolean - tinyint)
        - created_at (timestamp)
    2. associate: This table would have the following columns:
        - id (primary key)
        - name (string)
        - email (string)
        - created_at (timestamp)
    3. section: This table would have the following columns:
        - id (primary key)
        - topic_id (foreign key referencing Topics table)
        - start_time (timestamp)
        - end_time (timestamp)
        - is_open (boolean - tinyint)
        - vote_count_yes INT(11)
        - vote_count_no  INT(11)
    4. votes: This table would have the following columns:
        - id (primary key)
        - section_id (foreign key referencing section table)
        - associate_id (foreign key referencing associate table)
        - vote_choice (enum of 'yes' or 'no')
        - created_at (timestamp)

The MySQL statements for create tables would be in the file **associates_vote_tables_create.sql**
The MySQL triggers for create triggers would be in the follow file:
**associates_vote_statements_triggers.sql**

To set your database, use env.bkp content to fill a .env new file and change the variables there with your mySQL repository info.