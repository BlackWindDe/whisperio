/*-------------------------------------------------------------*/
/* Script to initialize test environment used by Whisper.io    */
/*-------------------------------------------------------------*/

/* --- Clean the existing values --- */
DELETE FROM BacklogItems;
DELETE FROM Sprints;
DELETE FROM Releases;
DELETE FROM Projects;
DELETE FROM Users;

/* --- Users ---*/
INSERT INTO Users(ID, Mail, Forename, LastName) 
VALUES (1, "john.doe@whisper.io", "John", "DOE");
INSERT INTO Users(ID, Mail, Forename, LastName) 
VALUES (2, "a", "a", "a");
INSERT INTO Users(ID, Mail, Forename, LastName) 
VALUES (3, "john.smith@whisper.io", "John", "SMITH");
INSERT INTO Users(ID, Mail, Forename, LastName) 
VALUES (4, "alain.rolland@whisper.io", "Alain", "ROLLAND");


/* --- Projects --- */
INSERT INTO Projects(ID, Name, KeyName, Description, CreationDate)
VALUES (1, "Whisper.io", "WIO", "Agile Planning tool developped with Java EE Technologie.", "2014-05-20");
INSERT INTO Projects(ID, Name, KeyName, Description, CreationDate)
VALUES (2, "Test Dashboard", "TDB", "Test of Dashboard.", "2015-02-16");
INSERT INTO Projects(ID, Name, KeyName, Description, CreationDate)
VALUES (3, "Ubuntu", "UBU", "Open Source OS", "2014-05-22");
INSERT INTO Projects(ID, Name, KeyName, Description, CreationDate)
VALUES (4, "Mac OS", "MAC", "OS developped by Apple.", "2014-05-23");

/* --- Releases --- */
INSERT INTO Releases(ID, Name, ReleaseNumber, StartDate, EndDate, NumberOfSprint, ProjectID, IsActive)
VALUES (1, "Test Dashboard Old1", 1, "2014-08-02", "2014-12-01", 3, 2, false);
INSERT INTO Releases(ID, Name, ReleaseNumber, StartDate, EndDate, NumberOfSprint, ProjectID, IsActive)
VALUES (2, "Test Dashboard Active", 2, "2014-12-02", "2015-01-31",5, 2, true);


/* --- Sprint --- */
INSERT INTO Sprints(ID, Name, SprintNumber, StartDate, EndDate, ReleaseID, IsActive, Velocity, ReleaseRemainingPointEndOfSprint)
VALUES (1, "Sprint1", 1, "2014-07-02", "2014-07-30", 1, false, 33.5, 81);
INSERT INTO Sprints(ID, Name, SprintNumber, StartDate, EndDate, ReleaseID, IsActive, Velocity, ReleaseRemainingPointEndOfSprint)
VALUES (2, "Sprint2", 2, "2014-08-02", "2014-08-30", 1, false, 32.5, 48.5);
INSERT INTO Sprints(ID, Name, SprintNumber, StartDate, EndDate, ReleaseID, IsActive, Velocity, ReleaseRemainingPointEndOfSprint)
VALUES (3, "Sprint3", 3, "2014-09-02", "2014-09-30", 1, false, 38.5, 10);
INSERT INTO Sprints(ID, Name, SprintNumber, StartDate, EndDate, ReleaseID, IsActive, Velocity, ReleaseRemainingPointEndOfSprint)
VALUES (4, "Sprint4", 1, "2014-10-02", "2014-10-30", 2, false, 13, 84.5);
INSERT INTO Sprints(ID, Name, SprintNumber, StartDate, EndDate, ReleaseID, IsActive, Velocity, ReleaseRemainingPointEndOfSprint)
VALUES (5, "Sprint5", 2, "2014-11-02", "2014-11-30", 2, false, 13, 71.5);
INSERT INTO Sprints(ID, Name, SprintNumber, StartDate, EndDate, ReleaseID, IsActive, Velocity, ReleaseRemainingPointEndOfSprint)
VALUES (6, "Sprint6", 3, "2014-12-02", "2014-12-30", 2, true, 14, 58.5);

/* --- Backlog Items --- */

