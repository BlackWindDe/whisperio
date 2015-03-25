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
/* General Users                         */
/* ------------------------------------- */
/* ------------------------------------- */

INSERT INTO Users(ID, Mail, Username, Forename, LastName) 
VALUES (1, "a", "a", "a", "a");
INSERT INTO Users(ID, Mail, Username, Forename, LastName) 
VALUES (2, "mescourbiac@humanbooster.com", "mescourbiac", "Maxime", "Escourbiac");

/* ------------------------------------- */
/* ------------------------------------- */
/*  Test Home page.                       */
/* ------------------------------------- */
/* ------------------------------------- */

INSERT INTO Projects(ID, Name, Description, CreationDate)
VALUES (1, "Docker", "application container platform.", "2015-02-16");
INSERT INTO Projects(ID, Name, Description, CreationDate)
VALUES (2, "Kubernetes", "orchestration system for containers.", "2015-02-16");
INSERT INTO Projects(ID, Name, Description, CreationDate)
VALUES (3, "Taiga", "project management platform.", "2015-02-16");
INSERT INTO Projects(ID, Name, Description, CreationDate)
VALUES (4, "Apache Mesos", "cluster manager.", "2015-02-16");
INSERT INTO Projects(ID, Name, Description, CreationDate)
VALUES (5, "OpenStack", "cloud computing platform.", "2015-02-16");
INSERT INTO Projects(ID, Name, Description, CreationDate)
VALUES (6, "Ansible", "IT automation tool.", "2015-02-16");
INSERT INTO Projects(ID, Name, Description, CreationDate)
VALUES (7, "ownCloud", "cloud storage tool.", "2015-02-16");
