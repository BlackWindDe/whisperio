/*-------------------------------------------------------------*/
/* Script to initialize test environment used by Whisper.io    */
/*-------------------------------------------------------------*/

/* --- Clean the existing values --- */
DELETE FROM BacklogItems;
DELETE FROM Sprints;
DELETE FROM Releases;
DELETE FROM Projects;
DELETE FROM Users;
DELETE FROM StoryEstimation;
DELETE FROM StoryBusinessValue;

/* ------------------------------------- */
/* ------------------------------------- */
/* Data Referentials                         */
/* ------------------------------------- */
/* ------------------------------------- */

/* --- Story Estimation --- */
INSERT INTO StoryEstimation(Name, Value) 
VALUES ("0", 0);
INSERT INTO StoryEstimation(Name, Value) 
VALUES ("1/2", 0.5);
INSERT INTO StoryEstimation(Name, Value) 
VALUES ("1", 1);
INSERT INTO StoryEstimation(Name, Value) 
VALUES ("2", 2);
INSERT INTO StoryEstimation(Name, Value) 
VALUES ("3", 3);
INSERT INTO StoryEstimation(Name, Value) 
VALUES ("5", 5);
INSERT INTO StoryEstimation(Name, Value) 
VALUES ("8", 8);
INSERT INTO StoryEstimation(Name, Value) 
VALUES ("13", 13);
INSERT INTO StoryEstimation(Name, Value) 
VALUES ("20", 20);
INSERT INTO StoryEstimation(Name, Value) 
VALUES ("40", 40);
INSERT INTO StoryEstimation(Name, Value) 
VALUES ("100", 100);
INSERT INTO StoryEstimation(Name, Value) 
VALUES ("?", null);
INSERT INTO StoryEstimation(Name, Value) 
VALUES ("Inf", null);

/* --- Story Business Value --- */
INSERT INTO StoryBusinessValue(Name, Value) 
VALUES ("0", 0);
INSERT INTO StoryBusinessValue(Name, Value) 
VALUES ("1/2", 0.5);
INSERT INTO StoryBusinessValue(Name, Value) 
VALUES ("1", 1);
INSERT INTO StoryBusinessValue(Name, Value) 
VALUES ("2", 2);
INSERT INTO StoryBusinessValue(Name, Value) 
VALUES ("3", 3);
INSERT INTO StoryBusinessValue(Name, Value) 
VALUES ("5", 5);
INSERT INTO StoryBusinessValue(Name, Value) 
VALUES ("8", 8);
INSERT INTO StoryBusinessValue(Name, Value) 
VALUES ("13", 13);
INSERT INTO StoryBusinessValue(Name, Value) 
VALUES ("20", 20);
INSERT INTO StoryBusinessValue(Name, Value) 
VALUES ("40", 40);
INSERT INTO StoryBusinessValue(Name, Value) 
VALUES ("100", 100);
INSERT INTO StoryBusinessValue(Name, Value) 
VALUES ("?", null);
