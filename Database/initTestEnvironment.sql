/*-------------------------------------------------------------*/
/* Script to initialize test environment used by Agile-Booster */
/*-------------------------------------------------------------*/

/* --- Clean the existing values --- */
DELETE FROM BacklogItems;
DELETE FROM Sprints;
DELETE FROM Releases;
DELETE FROM Projects;
DELETE FROM Users;

/* --- Users ---*/
INSERT INTO Users(ID, Mail, Forename, LastName) 
VALUES (1, "john.doe@agilebooster.com", "John", "DOE");
INSERT INTO Users(ID, Mail, Forename, LastName) 
VALUES (2, "alex.cool@agilebooster.com", "Alex", "COOL");
INSERT INTO Users(ID, Mail, Forename, LastName) 
VALUES (3, "john.smith@agilebooster.com", "John", "SMITH");
INSERT INTO Users(ID, Mail, Forename, LastName) 
VALUES (4, "alain.rolland@agilebooster.com", "Alain", "ROLLAND");
INSERT INTO Users(ID, Mail, Forename, LastName) 
VALUES (5, "sebastien.loeb@agilebooster.com", "Sebastien", "LOEB");
INSERT INTO Users(ID, Mail, Forename, LastName) 
VALUES (6, "remi.serein@agilebooster.com", "Remi", "SEREIN");

/* --- Projects --- */
INSERT INTO Projects(ID, Name, KeyName, Description, CreationDate)
VALUES (1, "AgileBooster", "ABR", "Agile Planning tool developped with Java EE Technologie.", "01/05/2014");

/* --- Releases --- */
INSERT INTO Releases(ID, Name, StartDate, EndDate, ProjectID, IsActive)
VALUES (1, "MVP1", "01/05/2014", "01/08/2014", 1, true);
INSERT INTO Releases(ID, Name, StartDate, EndDate, ProjectID, IsActive)
VALUES (2, "MVP2", "02/08/2014", "01/12/2014", 1, false);

/* --- Sprint --- */
INSERT INTO Sprints(ID, Name, StartDate, EndDate, ReleaseID, IsActive)
VALUES (1, "Sprint1", "01/05/2014", "01/06/2014", 1, false);
INSERT INTO Sprints(ID, Name, StartDate, EndDate, ReleaseID, IsActive)
VALUES (2, "Sprint2", "02/06/2014", "01/07/2014", 1, true);
INSERT INTO Sprints(ID, Name, StartDate, EndDate, ReleaseID, IsActive)
VALUES (3, "Sprint3", "02/07/2014", "01/08/2014", 1, false);

/* --- Backlog Items --- */
INSERT INTO BacklogItems(ID, Title, Description, CreationDate, LastUpdateDate, CreatorID, ProjectID, ReleaseID, SprintID)
VALUES(1, "Backlog Item 1", "Backlog Item1 Description", "02/07/2014", "04/07/2014", 2, 1, null, null);
INSERT INTO BacklogItems(ID, Title, StoryType, CreationDate, LastUpdateDate, CreatorID, ProjectID, ReleaseID, SprintID)
VALUES(2, "Backlog Item 2", "Backlog Item2 Description", "06/07/2014", "09/07/2014", 1, 1, 1, null);
INSERT INTO BacklogItems(ID, Title, StoryType, Status, Description, UserField1, UserField2, CreationDate, LastUpdateDate, CreatorID, ProjectID, ReleaseID, SprintID)
VALUES(3, "Backlog Item 3", "Backlog Item3 Description", "03/07/2014", "10/07/2014", 3, 1, 1, 1);
INSERT INTO BacklogItems(ID, Title, StoryType, Status, Description, UserField1, UserField2, CreationDate, LastUpdateDate, CreatorID, ProjectID, ReleaseID, SprintID)
VALUES(4, "Backlog Item 4", "Backlog Item4 Description", "08/07/2014", "12/07/2014", 2, 1, 1, 2);
