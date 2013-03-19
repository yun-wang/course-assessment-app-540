INSERT INTO TOPICS (T_ID, T_NAME)
VALUES (1, 'Database Fundamentals');

INSERT INTO TOPICS (T_ID, T_NAME)
VALUES (2, 'Security and Authorization');

INSERT INTO TOPICS (T_ID, T_NAME)
VALUES (3, 'ER Design and other topics');

INSERT INTO TOPICS (T_ID, T_NAME)
VALUES (4, 'Binary search trees and Btrees');

INSERT INTO TOPICS (T_ID, T_NAME)
VALUES (5, 'Hashing');

INSERT INTO TOPICS (T_ID, T_NAME)
VALUES (6, 'Files and indexing and other topics');

INSERT INTO TOPICS (T_ID, T_NAME)
VALUES (7, 'Processes and Threads');

INSERT INTO TOPICS (T_ID, T_NAME)
VALUES (8, 'Memory Organization');

-- Enter questions then answers
INSERT INTO QUESTIONS (Q_ID, QUESTION_TEXT, DIFFICULTY, T_ID)
VALUES (1, 'Question 1', 1, 3);

INSERT INTO ANSWERS (A_ID, ANSWER_TEXT, EXPLANATION, HINT, IS_CORRECT, Q_ID, T_ID)
VALUES (1, 'Correct 1.1', 'Explanation 1.1', 'Hint 1.1', 1, 1, 3);

INSERT INTO ANSWERS (A_ID, ANSWER_TEXT, EXPLANATION, HINT, IS_CORRECT, Q_ID, T_ID)
VALUES (2, 'Correct 1.2', 'Explanation 1.2', 'Hint 1.2', 1, 1, 3);

INSERT INTO ANSWERS (A_ID, ANSWER_TEXT, EXPLANATION, HINT, IS_CORRECT, Q_ID, T_ID)
VALUES (3, 'Correct 1.3', 'Explanation 1.2', 'Hint 1.3', 1, 1, 3 );

INSERT INTO ANSWERS (A_ID, ANSWER_TEXT, IS_CORRECT, Q_ID, T_ID)
VALUES (4, 'Incorrect 1.1', 0, 1, 3);

INSERT INTO ANSWERS (A_ID, ANSWER_TEXT, IS_CORRECT, Q_ID, T_ID)
VALUES (5, 'Incorrect 1.2', 0, 1, 3);

INSERT INTO ANSWERS (A_ID, ANSWER_TEXT, IS_CORRECT, Q_ID, T_ID)
VALUES (6, 'Incorrect 1.3', 0, 1, 3);

INSERT INTO ANSWERS (A_ID, ANSWER_TEXT, IS_CORRECT, Q_ID, T_ID)
VALUES (7, 'Incorrect 1.4', 0, 1, 3);

INSERT INTO ANSWERS (A_ID, ANSWER_TEXT, IS_CORRECT, Q_ID, T_ID)
VALUES (8, 'Incorrect 1.5', 0, 1, 3);

INSERT INTO ANSWERS (A_ID, ANSWER_TEXT, IS_CORRECT, Q_ID, T_ID)
VALUES (9, 'Incorrect 1.6', 0, 1, 3);

INSERT INTO ANSWERS (A_ID, ANSWER_TEXT, IS_CORRECT, Q_ID, T_ID)
VALUES (10, 'Incorrect 1.7', 0, 1, 3);

INSERT INTO QUESTIONS (Q_ID, QUESTION_TEXT, DIFFICULTY, T_ID)
VALUES (2, 'Question 2', 1, 3);

INSERT INTO ANSWERS (A_ID, ANSWER_TEXT, EXPLANATION, HINT, IS_CORRECT, Q_ID, T_ID)
VALUES (11, 'Correct 2.1', 'Explanation 2.1', 'Hint 2.1', 1, 2, 3);

INSERT INTO ANSWERS (A_ID, ANSWER_TEXT, EXPLANATION, HINT, IS_CORRECT, Q_ID, T_ID)
VALUES (12, 'Correct 2.2', 'Explanation 2.2', 'Hint 2.2', 1, 2, 3);

INSERT INTO ANSWERS (A_ID, ANSWER_TEXT, EXPLANATION, HINT, IS_CORRECT, Q_ID, T_ID)
VALUES (13, 'Correct 2.3', 'Explanation 2.2', 'Hint 2.3', 1, 2, 3);

INSERT INTO ANSWERS (A_ID, ANSWER_TEXT, IS_CORRECT, Q_ID, T_ID)
VALUES (14, 'Incorrect 2.1', 0, 2, 3);

INSERT INTO ANSWERS (A_ID, ANSWER_TEXT, IS_CORRECT, Q_ID, T_ID)
VALUES (15, 'Incorrect 2.2', 0, 2, 3);

INSERT INTO ANSWERS (A_ID, ANSWER_TEXT, IS_CORRECT, Q_ID, T_ID)
VALUES (16, 'Incorrect 2.3', 0, 2, 3);

INSERT INTO ANSWERS (A_ID, ANSWER_TEXT, IS_CORRECT, Q_ID, T_ID)
VALUES (17, 'Incorrect 2.4', 0, 2, 3);

INSERT INTO ANSWERS (A_ID, ANSWER_TEXT, IS_CORRECT, Q_ID, T_ID)
VALUES (18, 'Incorrect 2.5', 0, 2, 3);

INSERT INTO ANSWERS (A_ID, ANSWER_TEXT, IS_CORRECT, Q_ID, T_ID)
VALUES (19, 'Incorrect 2.6', 0, 2, 3);

INSERT INTO ANSWERS (A_ID, ANSWER_TEXT, IS_CORRECT, Q_ID, T_ID)
VALUES (20, 'Incorrect 2.7', 0, 2, 3);

INSERT INTO QUESTIONS (Q_ID, QUESTION_TEXT, DIFFICULTY, T_ID)
VALUES (3, 'Question 3', 1, 1);

INSERT INTO ANSWERS (A_ID, ANSWER_TEXT, EXPLANATION, HINT, IS_CORRECT, Q_ID, T_ID)
VALUES (21, 'Correct 3.1', 'Explanation 3.1', 'Hint 3.1', 1, 3, 1);

INSERT INTO ANSWERS (A_ID, ANSWER_TEXT, EXPLANATION, HINT, IS_CORRECT, Q_ID, T_ID)
VALUES (22, 'Correct 3.2', 'Explanation 3.2', 'Hint 3.2', 1, 3, 1);

INSERT INTO ANSWERS (A_ID, ANSWER_TEXT, EXPLANATION, HINT, IS_CORRECT, Q_ID, T_ID)
VALUES (23, 'Correct 3.3', 'Explanation 3.2', 'Hint 3.3', 1, 3, 1);

INSERT INTO ANSWERS (A_ID, ANSWER_TEXT, IS_CORRECT, Q_ID, T_ID)
VALUES (24, 'Incorrect 3.1', 0, 3, 1);

INSERT INTO ANSWERS (A_ID, ANSWER_TEXT, IS_CORRECT, Q_ID, T_ID)
VALUES (25, 'Incorrect 3.2', 0, 3, 1);

INSERT INTO ANSWERS (A_ID, ANSWER_TEXT, IS_CORRECT, Q_ID, T_ID)
VALUES (26, 'Incorrect 3.3', 0, 3, 1);

INSERT INTO ANSWERS (A_ID, ANSWER_TEXT, IS_CORRECT, Q_ID, T_ID)
VALUES (27, 'Incorrect 3.4', 0, 3, 1);

INSERT INTO ANSWERS (A_ID, ANSWER_TEXT, IS_CORRECT, Q_ID, T_ID)
VALUES (28, 'Incorrect 3.5', 0, 3, 1);

INSERT INTO ANSWERS (A_ID, ANSWER_TEXT, IS_CORRECT, Q_ID, T_ID)
VALUES (29, 'Incorrect 3.6', 0, 3, 1);

INSERT INTO ANSWERS (A_ID, ANSWER_TEXT, IS_CORRECT, Q_ID, T_ID)
VALUES (30, 'Incorrect 3.7', 0, 3, 1);

INSERT INTO STUDENTS (S_ID, S_PASS)
VALUES ('ssbudha', '123bud');

INSERT INTO STUDENTS (S_ID, S_PASS)
VALUES ('sskanit', '123kan');

INSERT INTO STUDENTS (S_ID, S_PASS)
VALUES ('sgholak', '123hol');

INSERT INTO STUDENTS (S_ID, S_PASS)
VALUES ('sjoseph', '123jos');

INSERT INTO STUDENTS (S_ID, S_PASS)
VALUES ('tbirajd', '123bir');

-- Missing student data
INSERT INTO STUDENTS (S_ID, S_PASS)
VALUES ('aneela', '123nee');

INSERT INTO STUDENTS (S_ID, S_PASS)
VALUES ('jharla', '123har');

INSERT INTO STUDENTS (S_ID, S_PASS)
VALUES ('phart', '123har');

INSERT INTO PROFESSORS (P_ID, P_PASS)
VALUES ('kogan', '123kogan');

-- Missing professors
INSERT INTO PROFESSORS (P_ID, P_PASS)
VALUES ('rchirk', '123chi');

INSERT INTO PROFESSORS (P_ID, P_PASS)
VALUES ('rmuell', '123mue');

-- courses with all relations
INSERT INTO COURSES (C_ID, C_T, C_NAME, C_START, C_END)
VALUES ('CSC440', 'CSC440SPR13', 'Database Systems', TO_DATE('2013-01-01','YYYY-MM-DD'), TO_DATE('2013-05-10','YYYY-MM-DD'));

INSERT INTO TEACHES (P_ID, C_T)
VALUES ('kogan', 'CSC440SPR13');

INSERT INTO ASSISTS (S_ID, C_T, CONTRACT_ID)
VALUES ('aneela', 'CSC440SPR13', 1);

INSERT INTO COURSECONSISTS (C_T, T_ID)
VALUES ('CSC440SPR13', 1);

INSERT INTO COURSECONSISTS (C_T, T_ID)
VALUES ('CSC440SPR13', 2);

INSERT INTO COURSECONSISTS (C_T, T_ID)
VALUES ('CSC440SPR13', 3);

INSERT INTO TAKES (S_ID, C_T) 
VALUES ('ssbudha', 'CSC440SPR13');

INSERT INTO TAKES (S_ID, C_T) 
VALUES ('sskanit', 'CSC440SPR13');

INSERT INTO TAKES (S_ID, C_T) 
VALUES ('sgholak', 'CSC440SPR13');

INSERT INTO TAKES (S_ID, C_T) 
VALUES ('sjoseph', 'CSC440SPR13');

INSERT INTO COURSES (C_ID, C_T, C_NAME, C_START, C_END)
VALUES ('CSC541', 'CSC541FLL11', 'Advanced Data Structures', TO_DATE('2011-08-1','YYYY-MM-DD'), TO_DATE('2011-12-15','YYYY-MM-DD'));

INSERT INTO TEACHES (P_ID, C_T)
VALUES ('rchirk', 'CSC541FLL11');

INSERT INTO ASSISTS (S_ID, C_T, CONTRACT_ID)
VALUES ('jharla', 'CSC541FLL11', 2);

INSERT INTO COURSECONSISTS (C_T, T_ID)
VALUES ('CSC541FLL11', 4);

INSERT INTO COURSECONSISTS (C_T, T_ID)
VALUES ('CSC541FLL11', 5);

INSERT INTO COURSECONSISTS (C_T, T_ID)
VALUES ('CSC541FLL11', 6);

INSERT INTO COURSES (C_ID, C_T, C_NAME, C_START, C_END)
VALUES ('CSC501', 'CSC501SPR12', 'Operating Systems', TO_DATE('2012-01-1','YYYY-MM-DD'), TO_DATE('2012-05-10','YYYY-MM-DD'));

INSERT INTO TEACHES (P_ID, C_T)
VALUES ('rmuell', 'CSC501SPR12');

INSERT INTO ASSISTS (S_ID, C_T, CONTRACT_ID)
VALUES ('phart', 'CSC501SPR12', 1);

INSERT INTO COURSECONSISTS (C_T, T_ID)
VALUES ('CSC501SPR12', 7);

INSERT INTO COURSECONSISTS (C_T, T_ID)
VALUES ('CSC501SPR12', 8);

INSERT INTO COURSECONSISTS (C_T, T_ID)
VALUES ('CSC501SPR12', 9);

INSERT INTO TAKES (S_ID, C_T) 
VALUES ('sgholak', 'CSC501SPR12');

-- Assessments
INSERT INTO ASSESSMENTS (AS_ID, AS_NAME, DESCRIPTION, RETRIES, AS_START, AS_END, PTS_CORRECT, PTS_INCORRECT, METHOD, C_T)
VALUES (1, 'Homework 1', 'Database Fundamentals', 2, TO_DATE('2013-02-12','YYYY-MM-DD'), TO_DATE('2013-05-01','YYYY-MM-DD'), 3, 1, 'first attempt', 'CSC440SPR13');

INSERT INTO ASSESSMENTHAS(AS_ID, C_T, Q_ID, T_ID) 
VALUES (1, 'CSC440SPR13', 3, 1);

INSERT INTO ATTEMPTS (AT_ID, S_ID, AS_ID, C_T, SEED)
VALUES (1, 'sskanit', 1, 'CSC440SPR13', 74);

INSERT INTO ATTEMPTQUESTIONS (AT_ID, Q_ID, A_ID, AS_ID, S_ID, C_T, JUSTIFICATION)
VALUES (1, 3, 24, 1, 'sskanit', 'CSC440SPR13', 'Because 1');

INSERT INTO ATTEMPTS (AT_ID, S_ID, AS_ID, C_T, SEED)
VALUES (2, 'sskanit', 1, 'CSC440SPR13', 43);

INSERT INTO ATTEMPTQUESTIONS (AT_ID, Q_ID, A_ID, AS_ID, S_ID, C_T, JUSTIFICATION)
VALUES (2, 3, 21, 1, 'sskanit', 'CSC440SPR13', 'Because 2');

INSERT INTO ATTEMPTS (AT_ID, S_ID, AS_ID, C_T, SEED)
VALUES (3, 'sgholak', 1, 'CSC440SPR13', 7);

INSERT INTO ATTEMPTQUESTIONS (AT_ID, Q_ID, A_ID, AS_ID, S_ID, C_T, JUSTIFICATION)
VALUES (3, 3, 26, 1, 'sgholak', 'CSC440SPR13', 'Because 3');

INSERT INTO ASSESSMENTS (AS_ID, AS_NAME, DESCRIPTION, RETRIES, AS_START, AS_END, PTS_CORRECT, PTS_INCORRECT, METHOD, C_T)
VALUES (2, 'Homework 2', 'ER design', 1, TO_DATE('2013-03-12','YYYY-MM-DD'), TO_DATE('2013-04-01','YYYY-MM-DD'), 5, 2, 'average', 'CSC440SPR13');

INSERT INTO ASSESSMENTHAS(AS_ID, C_T, Q_ID, T_ID) 
VALUES (2, 'CSC440SPR13', 1, 3);

INSERT INTO ASSESSMENTHAS(AS_ID, C_T, Q_ID, T_ID) 
VALUES (2, 'CSC440SPR13', 2, 3);

INSERT INTO ATTEMPTS (AT_ID, S_ID, AS_ID, C_T, SEED)
VALUES (4, 'ssbudha', 2, 'CSC440SPR13', 52);

INSERT INTO ATTEMPTQUESTIONS (AT_ID, Q_ID, A_ID, AS_ID, S_ID, C_T, JUSTIFICATION)
VALUES (4, 1, 1, 2, 'ssbudha', 'CSC440SPR13', 'Because 4');

INSERT INTO ATTEMPTQUESTIONS (AT_ID, Q_ID, A_ID, AS_ID, S_ID, C_T, JUSTIFICATION)
VALUES (4, 2, 15, 2, 'ssbudha', 'CSC440SPR13', 'Because 5');



