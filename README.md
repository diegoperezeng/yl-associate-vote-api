# yl-associate-vote-api
An aplication to control of the Associates vote calculation on business agenda. (Uma aplicação para controlar o cálculo dos votos sobre pautas de negócios dos associados).


## INSTALLATION

Run ```java mvn clean install ```
then
Run ```java mvn clean package ```

## SWAGGER

http://localhost:8080/swagger-ui/index.html (if you run your application locally)


## The Data Base:

![MySQL Database Structure, with four tables: named topic, associate, section and vote. Table section linked with table topic and table vote. Table vote linked with table topic and table associate](/documentation/Tables_diagram.png "MySQL Database Structure")

The database structure for this project have the following tables:

    1. topic: This table would have the following columns:
        - id (primary key)
        - title (string)
        - description (string)
        - open_status (boolean - tinyint)
        - created_at (timestamp)
    2. associate: This table would have the following columns:
        - id (BIGINT - primary key)
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
    4. vote: This table would have the following columns:
        - id (primary key)
        - section_id (foreign key referencing section table)
        - associate_id (BIGINT - foreign key referencing associate table)
        - vote_choice (boolean - tinyint)
        - created_at (timestamp)



## Database Settings

To set your database, use env.bkp content to fill a .env new file and change the variables there with your mySQL repository info.

The MySQL statements for create tables would be in the file:
**associates_vote_tables_create.sql**

The MySQL triggers for create triggers would be in the follow file:
**associates_vote_statements_triggers.sql**

Both files are located in **/src/main/resources/bd**
