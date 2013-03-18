CREATE TABLE COURSES (C_ID    CHAR(32),
                     C_TOKEN CHAR(32),
                     C_NAME  CHAR(32),
                     C_START DATE,
                     C_END   DATE,
                     PRIMARY KEY(C_TOKEN));

CREATE TABLE TOPICS (T_ID INTEGER,
						T_NAME  CHAR(64),
                        PRIMARY KEY(T_ID));

CREATE TABLE COURSECONSISTS (C_TOKEN  CHAR(32),
                       T_ID   INTEGER,
					   PRIMARY KEY (C_TOKEN, T_ID),
					   FOREIGN KEY (C_TOKEN) REFERENCES COURSES ON DELETE CASCADE,
					   FOREIGN KEY (T_ID) REFERENCES TOPICS ON DELETE CASCADE);

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
                        T_ID INTEGER,
                        FOREIGN KEY (T_ID) REFERENCES TOPICS ON DELETE CASCADE,
                        PRIMARY KEY (Q_ID, T_ID));

CREATE TABLE ANSWERS (A_ID INTEGER,
    		          ANSWER_TEXT CHAR(128), 
                      EXPLANATION CHAR(128),
                      HINT CHAR(128),
                      IS_CORRECT NUMBER(1) CHECK(IS_CORRECT IN (0, 1)),
                      Q_ID INTEGER,
					  T_ID INTEGER,
                      PRIMARY KEY (A_ID, Q_ID, T_ID),
                      FOREIGN KEY (Q_ID, T_ID) REFERENCES QUESTIONS ON DELETE CASCADE);

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
                          FOREIGN KEY (C_TOKEN) REFERENCES COURSES ON DELETE CASCADE, 
                          PRIMARY KEY (AS_ID, C_TOKEN));

CREATE TABLE ASSESSMENTHAS (AS_ID INTEGER,
                            C_TOKEN CHAR(32),
				            Q_ID INTEGER,
							T_ID INTEGER,
							PRIMARY KEY (AS_ID, C_TOKEN, Q_ID, T_ID),
							FOREIGN KEY (AS_ID, C_TOKEN) REFERENCES ASSESSMENTS ON DELETE CASCADE,
							FOREIGN KEY (Q_ID, T_ID) REFERENCES QUESTIONS ON DELETE CASCADE);

CREATE TABLE ATTEMPTS (AT_ID INTEGER, 
                       TOTAL_PTS DOUBLE PRECISION, 
                       S_ID CHAR(32), 
                       SUBMISSION_TIME TIMESTAMP, 
                       AS_ID INTEGER, 
					   C_TOKEN CHAR(32),
                       PRIMARY KEY (AT_ID, AS_ID, S_ID, C_TOKEN),
                       FOREIGN KEY (AS_ID, C_TOKEN) REFERENCES ASSESSMENTS ON DELETE CASCADE,
                       FOREIGN KEY (S_ID) REFERENCES STUDENTS ON DELETE CASCADE);

CREATE TABLE ATTEMPTQUESTIONS (AT_ID INTEGER,
                               Q_ID INTEGER,
                               A_ID INTEGER,
							   AS_ID INTEGER,
							   S_ID CHAR(32),
							   C_TOKEN CHAR(32),
                               JUSTIFICATION CHAR(128),
                               T_ID INTEGER,
                               PRIMARY KEY (AT_ID, Q_ID, A_ID),
                               FOREIGN KEY (AT_ID, AS_ID, S_ID, C_TOKEN) REFERENCES ATTEMPTS ON DELETE CASCADE,
                               FOREIGN KEY (A_ID, Q_ID, T_ID) REFERENCES ANSWERS ON DELETE CASCADE)