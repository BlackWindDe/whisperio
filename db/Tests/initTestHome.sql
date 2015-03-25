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

INSERT INTO Projects(ID, Name, KeyName, Description, CreationDate)
VALUES (1, "Docker", "TH1", "application container platform.", "2015-02-16");
INSERT INTO Projects(ID, Name, KeyName, Description, CreationDate)
VALUES (2, "Kubernetes", "TH2", "orchestration system for containers.", "2015-02-16");
INSERT INTO Projects(ID, Name, KeyName, Description, CreationDate)
VALUES (3, "Taiga", "TH3", "project management platform.", "2015-02-16");
INSERT INTO Projects(ID, Name, KeyName, Description, CreationDate)
VALUES (4, "Apache Mesos", "TH4", "cluster manager.", "2015-02-16");
INSERT INTO Projects(ID, Name, KeyName, Description, CreationDate)
VALUES (5, "OpenStack", "TH5", "cloud computing platform.", "2015-02-16");
INSERT INTO Projects(ID, Name, KeyName, Description, CreationDate)
VALUES (6, "Ansible", "TH6", "IT automation tool.", "2015-02-16");
INSERT INTO Projects(ID, Name, KeyName, Description, CreationDate)
VALUES (7, "ownCloud", "TH7", "cloud storage tool.", "2015-02-16");
