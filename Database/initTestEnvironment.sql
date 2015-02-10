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
VALUES (2, "alex.cool@whisper.io", "Alex", "COOL");
INSERT INTO Users(ID, Mail, Forename, LastName) 
VALUES (3, "john.smith@whisper.io", "John", "SMITH");
INSERT INTO Users(ID, Mail, Forename, LastName) 
VALUES (4, "alain.rolland@whisper.io", "Alain", "ROLLAND");
INSERT INTO Users(ID, Mail, Forename, LastName) 
VALUES (5, "sebastien.loeb@whisper.io", "Sebastien", "LOEB");
INSERT INTO Users(ID, Mail, Forename, LastName) 
VALUES (6, "remi.serein@whisper.io", "Remi", "SEREIN");

/* --- Projects --- */
INSERT INTO Projects(ID, Name, KeyName, Description, CreationDate)
VALUES (1, "Whisper.io", "WIO", "Agile Planning tool developped with Java EE Technologie.", "2014-05-20");

/* --- Releases --- */
INSERT INTO Releases(ID, Name, StartDate, EndDate, ProjectID, IsActive)
VALUES (1, "MVP1", "2014-05-01", "2014-08-01", 1, true);
INSERT INTO Releases(ID, Name, StartDate, EndDate, ProjectID, IsActive)
VALUES (2, "MVP2", "2014-08-02", "2014-12-01", 1, false);

/* --- Sprint --- */
INSERT INTO Sprints(ID, Name, StartDate, EndDate, ReleaseID, IsActive)
VALUES (1, "Sprint1", "2014-05-01", "2014-06-01", 1, false);
INSERT INTO Sprints(ID, Name, StartDate, EndDate, ReleaseID, IsActive)
VALUES (2, "Sprint2", "2014-06-02", "2014-07-01", 1, true);
INSERT INTO Sprints(ID, Name, StartDate, EndDate, ReleaseID, IsActive)
VALUES (3, "Sprint3", "2014-07-02", "2014-08-01", 1, false);

/* --- Backlog Items --- */
INSERT INTO BacklogItems(ID, Title, Description, CreationDate, LastUpdateDate, CreatorID, ProjectID, ReleaseID, SprintID)
VALUES(1, "Backlog Item 1", "Backlog Item1 Description", "2014-07-02 15:00:00", "2014-07-04 13:00:00", 2, 1, null, null);
INSERT INTO BacklogItems(ID, Title, Description, CreationDate, LastUpdateDate, CreatorID, ProjectID, ReleaseID, SprintID)
VALUES(2, "Backlog Item 2", "Backlog Item2 Description", "2014-07-06 16:00:00", "2014-07-09 14:00:00", 1, 1, 1, null);
INSERT INTO BacklogItems(ID, Title, Description, CreationDate, LastUpdateDate, CreatorID, ProjectID, ReleaseID, SprintID)
VALUES(3, "Backlog Item 3", "Backlog Item3 Description", "2014-07-03 17:00:00", "2014-07-10 15:00:00", 3, 1, 1, 1);
INSERT INTO BacklogItems(ID, Title, Description, CreationDate, LastUpdateDate, CreatorID, ProjectID, ReleaseID, SprintID)
VALUES(4, "Backlog Item 4", "Backlog Item4 Description", "2014-07-08 18:00:00", "2014-07-12 16:00:00", 2, 1, 1, 2);
