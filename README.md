# yl-associate-vote-api
An aplication to control of the Associates vote calculation on business agenda. (Uma aplicação para controlar o cálculo dos votos sobre pautas de negócios dos associados).



## TECHNOLOGY
- Java v17
- Springboot and SpringData v2
- Swagger v3
- MySQL v8

![Java logo with number seventeen, Spring environment logo above the springboot and sprindata on sides with a plus sign](/documentation/techs-on-associates-votes.png "Techs on project")



## INSTALLATION

Run ``` mvn clean install ```

then

Run ``` mvn clean package ```


## RUN

After the installation process, the .jar project file will be in the folder "target" with the name "associatesvotes-X.X.X.jar".

Run ```java -jar  {put here the entire path to your jar file}\associatesvotes-X.X.X.jar```

Done. The Application should be running in your terminal.


## SWAGGER

http://localhost:8080/swagger-ui/index.html (if you run your application locally)

Click [here](documentation/AsVotAPI_v.1.1.0-EndPoints_Documentation.pdf) to open the the list of endpoints documentation.

## The Data Base:

![MySQL Database Structure, with four tables: named topic, associate, session and vote. Table session linked with table topic and table vote. Table vote linked with table topic and table associate](/documentation/Tables_diagram.png "MySQL Database Structure")

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
    3. session: This table would have the following columns:
        - id (primary key)
        - topic_id (foreign key referencing Topics table)
        - start_time (timestamp)
        - end_time (timestamp)
        - is_open (boolean - tinyint)
        - vote_count_yes INT(11)
        - vote_count_no  INT(11)
    4. vote: This table would have the following columns:
        - id (primary key)
        - session_id (foreign key referencing session table)
        - associate_id (BIGINT - foreign key referencing associate table)
        - vote_choice (boolean - tinyint)
        - created_at (timestamp)



## Database Settings

To set your database, use env.bkp content to fill a .env new file and change the variables there with your mySQL repository info.

The MySQL statements for create tables would be in the file:
**associates_vote_tables_create.sql**

The MySQL triggers for create triggers would be in the follow file:
**associates_vote_statements_triggers.sql**

If you prefer, the entire creation and triggering are in the following file:
**associates_vote_statements_complete.sql**

For filling with some init data:
**associates_vote_statements_filling_firsts.sql**

All BD Script files are located in **/src/main/resources/bd**
