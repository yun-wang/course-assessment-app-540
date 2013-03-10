CREATE TABLE COURSES (C_ID    CHAR(32),
                     C_TOKEN CHAR(32),
                     C_NAME  CHAR(32),
                     C_START DATE,
                     C_END   DATE,
                     PRIMARY KEY(C_TOKEN));

CREATE TABLE COURSETOPICS (T_NAME  CHAR(64),
                        C_ID  CHAR(32),
                        PRIMARY KEY(T_NAME, C_ID));

CREATE TABLE STUDENTS (S_ID   CHAR(32),
                      S_PASS CHAR(32),
                      PRIMARY KEY (S_ID));

CREATE TABLE TAKES (S_ID  CHAR(32),
                    C_TOKEN  CHAR(32),
                    PRIMARY KEY (S_ID, C_TOKEN),
                    FOREIGN KEY (S_ID) REFERENCES STUDENTS ON DELETE CASCADE,
                    FOREIGN KEY (C_TOKEN) REFERENCES COURSES ON DELETE CASCADE);

CREATE TABLE PROFESSORS (P_ID   CHAR(32),
                        P_PASS CHAR(32),
                        PRIMARY KEY (P_ID));

CREATE TABLE TEACHES (P_ID  CHAR(32),
                      C_TOKEN  CHAR(32),
                      PRIMARY KEY (P_ID, C_TOKEN),
                      FOREIGN KEY (P_ID) REFERENCES PROFESSORS
                      ON DELETE CASCADE,
                      FOREIGN KEY (C_TOKEN) REFERENCES COURSES 
                      ON DELETE CASCADE);

CREATE TABLE ASSISTS (CONTRACT_ID  INTEGER,
                      S_ID    CHAR(32),
                      C_TOKEN    CHAR(32),
                      PRIMARY KEY (S_ID, C_TOKEN), 
                      FOREIGN KEY (S_ID) REFERENCES STUDENTS
                      ON DELETE CASCADE,
                      FOREIGN KEY (C_TOKEN) REFERENCES COURSES 
                      ON DELETE CASCADE);

CREATE TABLE QUESTIONS (Q_ID INTEGER, 
                        QUESTION_TEXT CHAR(128),
                        DIFFICULTY INTEGER,
                        C_TOKEN CHAR(32),
                        FOREIGN KEY (C_TOKEN) REFERENCES COURSES ON DELETE CASCADE,
                        PRIMARY KEY (Q_ID));

CREATE TABLE ANSWERS (A_ID INTEGER,
    		      ANSWER_TEXT CHAR(128), 
                      EXPLANATION CHAR(128),
                      HINT CHAR(128),
                      IS_CORRECT NUMBER(1) CHECK(IS_CORRECT IN (0, 1)),
                      Q_ID INTEGER,
                      PRIMARY KEY (A_ID),
                      FOREIGN KEY (Q_ID) REFERENCES QUESTIONS ON DELETE CASCADE);

CREATE TABLE ASSESSMENTS (RETRIES INTEGER,
                          AS_ID INTEGER, 
                          AS_NAME CHAR(32),
                          DESCRIPTION CHAR(64),
                          METHOD CHAR(32),
                          FINAL_GRADE DOUBLE PRECISION,
                          AS_START DATE,
                          AS_END DATE, 
                          PTS_CORRECT DOUBLE PRECISION,
                          PTS_INCORRECT DOUBLE PRECISION,
                          C_TOKEN CHAR(32),
                          FOREIGN KEY (C_TOKEN) REFERENCES COURSES, 
                          PRIMARY KEY (AS_ID));

CREATE TABLE ATTEMPTS (AT_ID INTEGER, 
                       TOTAL_PTS DOUBLE PRECISION, 
                       S_ID CHAR(32), 
                       SUBMISSION_TIME TIMESTAMP, 
                       AS_ID INTEGER, 
                       PRIMARY KEY (AT_ID, AS_ID, S_ID),
                       FOREIGN KEY (AS_ID) REFERENCES ASSESSMENTS ON DELETE CASCADE,
                       FOREIGN KEY (S_ID) REFERENCES STUDENTS ON DELETE CASCADE);

CREATE TABLE ATTEMPTQUESTIONS (AT_ID INTEGER,
                               Q_ID INTEGER,
                               A_ID INTEGER,
							   AS_ID INTEGER,
							   S_ID CHAR(32), 
                               JUSTIFICATION CHAR(128),
                               PRIMARY KEY (AT_ID, Q_ID, A_ID),
                               FOREIGN KEY (AT_ID, AS_ID, S_ID) REFERENCES ATTEMPTS ON DELETE CASCADE,
                               FOREIGN KEY (A_ID) REFERENCES ANSWERS ON DELETE CASCADE,
                               FOREIGN KEY (Q_ID) REFERENCES QUESTIONS ON DELETE CASCADE)