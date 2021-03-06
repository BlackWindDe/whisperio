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
/*  Test Obeya                       */
/* ------------------------------------- */
/* ------------------------------------- */

/* Test working Obeya */
INSERT INTO Projects(ID, Name, Description, CreationDate)
VALUES (1, "Test Obeya", "Test of Obeya.", "2015-02-16");
INSERT INTO Releases(ID, Name, ReleaseNumber, StartDate, EndDate, NumberOfSprint, ProjectID, IsActive)
VALUES (1, "Onboarding Release", 1, "2014-08-02", "2014-11-01", 3, 1, false);
INSERT INTO Releases(ID, Name, ReleaseNumber, StartDate, EndDate, NumberOfSprint, ProjectID, IsActive)
VALUES (2, "Integration Release", 2, "2014-11-02", "2014-12-01", 3, 1, false);
INSERT INTO Releases(ID, Name, ReleaseNumber, StartDate, EndDate, NumberOfSprint, ProjectID, IsActive)
VALUES (3, "Export Release", 3, "2014-12-02", "2015-01-31",5, 1, true);
INSERT INTO Sprints(ID, Name, SprintNumber, StartDate, EndDate, ReleaseID, IsActive, IsClosed, Velocity, ReleaseRemainingPointEndOfSprint, BusinessValueDone)
VALUES (1, "Sprint1", 1, "2014-07-02", "2014-07-30", 1, false, true, 33.5, 81, 20.5);
INSERT INTO Sprints(ID, Name, SprintNumber, StartDate, EndDate, ReleaseID, IsActive, IsClosed, Velocity, ReleaseRemainingPointEndOfSprint, BusinessValueDone)
VALUES (2, "Sprint2", 2, "2014-08-02", "2014-08-30", 1, false, true, 32.5, 48.5, 26);
INSERT INTO Sprints(ID, Name, SprintNumber, StartDate, EndDate, ReleaseID, IsActive, IsClosed, Velocity, ReleaseRemainingPointEndOfSprint, BusinessValueDone)
VALUES (3, "Sprint3", 3, "2014-09-02", "2014-09-30", 1, false, true, 38.5, 10, 30);
INSERT INTO Sprints(ID, Name, SprintNumber, StartDate, EndDate, ReleaseID, IsActive, IsClosed, Velocity, ReleaseRemainingPointEndOfSprint, BusinessValueDone)
VALUES (4, "Sprint4", 1, "2014-07-02", "2014-07-30", 2, false, true, 33.5, 81, 34);
INSERT INTO Sprints(ID, Name, SprintNumber, StartDate, EndDate, ReleaseID, IsActive, IsClosed, Velocity, ReleaseRemainingPointEndOfSprint, BusinessValueDone)
VALUES (5, "Sprint5", 2, "2014-08-02", "2014-08-30", 2, false, true, 32.5, 48.5, 30);
INSERT INTO Sprints(ID, Name, SprintNumber, StartDate, EndDate, ReleaseID, IsActive, IsClosed, Velocity, ReleaseRemainingPointEndOfSprint, BusinessValueDone)
VALUES (6, "Sprint6", 3, "2014-09-02", "2014-09-30", 2, false, true, 38.5, 10, 26);
INSERT INTO Sprints(ID, Name, SprintNumber, StartDate, EndDate, ReleaseID, IsActive, IsClosed, Velocity, ReleaseRemainingPointEndOfSprint, BusinessValueDone)
VALUES (7, "Sprint7", 1, "2014-10-02", "2014-10-30", 3, false, true, 13, 84.5, 22);
INSERT INTO Sprints(ID, Name, SprintNumber, StartDate, EndDate, ReleaseID, IsActive, IsClosed, Velocity, ReleaseRemainingPointEndOfSprint, BusinessValueDone)
VALUES (8, "Sprint8", 2, "2014-11-02", "2014-11-30", 3, false, true, 13, 95.5, 16);
INSERT INTO Sprints(ID, Name, SprintNumber, StartDate, EndDate, ReleaseID, IsActive, IsClosed, Velocity, ReleaseRemainingPointEndOfSprint, BusinessValueDone)
VALUES (9, "Sprint9", 3, "2014-12-02", "2014-12-30", 3, false, true, 14, 81.5, 10);
INSERT INTO Sprints(ID, Name, SprintNumber, StartDate, EndDate, ReleaseID, IsActive, IsClosed)
VALUES (10, "Sprint10", 4, "2015-01-01", "2015-01-30", 3, true, false);

/* Test No Active Release Obeya */
INSERT INTO Projects(ID, Name, Description, CreationDate)
VALUES (10, "DB : No Active Release", "Test of Obeya.", "2015-02-16");
INSERT INTO Releases(ID, Name, ReleaseNumber, StartDate, EndDate, NumberOfSprint, ProjectID, IsActive)
VALUES (10, "DB : No Active Release", 1, "2014-12-02", "2015-01-31",5, 10, false);

/* Test No Closed Sprint Obeya */
INSERT INTO Projects(ID, Name, Description, CreationDate)
VALUES (20, "DB : No Closed Sprint", "Test of Obeya.", "2015-02-16");
INSERT INTO Releases(ID, Name, ReleaseNumber, StartDate, EndDate, NumberOfSprint, ProjectID, IsActive)
VALUES (20, "DB : No Closed Sprint", 1, "2014-12-02", "2015-01-31",5, 20, true);
INSERT INTO Sprints(ID, Name, SprintNumber, StartDate, EndDate, ReleaseID, IsActive, IsClosed)
VALUES (20, "Sprint1", 1, "2014-07-02", "2014-07-30", 20, true, false);
INSERT INTO Sprints(ID, Name, SprintNumber, StartDate, EndDate, ReleaseID, IsActive, IsClosed)
VALUES (21, "Sprint2", 2, "2014-08-02", "2014-08-30", 20, false, false);
