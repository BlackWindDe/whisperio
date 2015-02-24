/*-------------------------------------------------------------*/
/* Script to initialize test environment used by Whisper.io    */
/*-------------------------------------------------------------*/

/* --- Clean the existing values --- */
DELETE FROM BacklogItems;
DELETE FROM Sprints;
DELETE FROM Releases;
DELETE FROM Projects;
DELETE FROM Users;

/* ------------------------------------- */
/* ------------------------------------- */
/* General Users                         */
/* ------------------------------------- */
/* ------------------------------------- */

INSERT INTO Users(ID, Mail, Forename, LastName) 
VALUES (2, "a", "a", "a");

/* ------------------------------------- */
/* ------------------------------------- */
/*  Test Dashboard                       */
/* ------------------------------------- */
/* ------------------------------------- */

/* Test working Dashboard */
INSERT INTO Projects(ID, Name, KeyName, Description, CreationDate)
VALUES (1, "Test Dashboard", "TDB", "Test of Dashboard.", "2015-02-16");
INSERT INTO Releases(ID, Name, ReleaseNumber, StartDate, EndDate, NumberOfSprint, ProjectID, IsActive)
VALUES (1, "Test Dashboard Inactive", 1, "2014-08-02", "2014-12-01", 3, 1, false);
INSERT INTO Releases(ID, Name, ReleaseNumber, StartDate, EndDate, NumberOfSprint, ProjectID, IsActive)
VALUES (2, "Test Dashboard Active", 2, "2014-12-02", "2015-01-31",5, 1, true);
INSERT INTO Sprints(ID, Name, SprintNumber, StartDate, EndDate, ReleaseID, IsActive, IsClosed, Velocity, ReleaseRemainingPointEndOfSprint)
VALUES (1, "Sprint1", 1, "2014-07-02", "2014-07-30", 1, false, true, 33.5, 81);
INSERT INTO Sprints(ID, Name, SprintNumber, StartDate, EndDate, ReleaseID, IsActive, IsClosed, Velocity, ReleaseRemainingPointEndOfSprint)
VALUES (2, "Sprint2", 2, "2014-08-02", "2014-08-30", 1, false, true, 32.5, 48.5);
INSERT INTO Sprints(ID, Name, SprintNumber, StartDate, EndDate, ReleaseID, IsActive, IsClosed, Velocity, ReleaseRemainingPointEndOfSprint)
VALUES (3, "Sprint3", 3, "2014-09-02", "2014-09-30", 1, false, true, 38.5, 10);
INSERT INTO Sprints(ID, Name, SprintNumber, StartDate, EndDate, ReleaseID, IsActive, IsClosed, Velocity, ReleaseRemainingPointEndOfSprint)
VALUES (4, "Sprint4", 1, "2014-10-02", "2014-10-30", 2, false, true, 13, 84.5);
INSERT INTO Sprints(ID, Name, SprintNumber, StartDate, EndDate, ReleaseID, IsActive, IsClosed, Velocity, ReleaseRemainingPointEndOfSprint)
VALUES (5, "Sprint5", 2, "2014-11-02", "2014-11-30", 2, false, true, 13, 95.5);
INSERT INTO Sprints(ID, Name, SprintNumber, StartDate, EndDate, ReleaseID, IsActive, IsClosed, Velocity, ReleaseRemainingPointEndOfSprint)
VALUES (6, "Sprint6", 3, "2014-12-02", "2014-12-30", 2, false, true, 14, 81.5);
INSERT INTO Sprints(ID, Name, SprintNumber, StartDate, EndDate, ReleaseID, IsActive, IsClosed)
VALUES (9, "Sprint7", 4, "2015-01-01", "2015-01-30", 2, true, false);

/* Test No Active Release Dashboard */
INSERT INTO Projects(ID, Name, KeyName, Description, CreationDate)
VALUES (2, "DB : No Active Release", "DB1", "Test of Dashboard.", "2015-02-16");
INSERT INTO Releases(ID, Name, ReleaseNumber, StartDate, EndDate, NumberOfSprint, ProjectID, IsActive)
VALUES (3, "DB : No Active Release", 1, "2014-12-02", "2015-01-31",5, 2, false);

/* Test No Closed Sprint Dashboard */
INSERT INTO Projects(ID, Name, KeyName, Description, CreationDate)
VALUES (3, "DB : No Closed Sprint", "DB2", "Test of Dashboard.", "2015-02-16");
INSERT INTO Releases(ID, Name, ReleaseNumber, StartDate, EndDate, NumberOfSprint, ProjectID, IsActive)
VALUES (4, "DB : No Closed Sprint", 1, "2014-12-02", "2015-01-31",5, 3, true);
INSERT INTO Sprints(ID, Name, SprintNumber, StartDate, EndDate, ReleaseID, IsActive, IsClosed)
VALUES (7, "Sprint1", 1, "2014-07-02", "2014-07-30", 4, true, false);
INSERT INTO Sprints(ID, Name, SprintNumber, StartDate, EndDate, ReleaseID, IsActive, IsClosed)
VALUES (8, "Sprint2", 2, "2014-08-02", "2014-08-30", 4, false, false);
