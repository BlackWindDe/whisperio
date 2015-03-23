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
/*  Test Product Backlog.                */
/* ------------------------------------- */
/* ------------------------------------- */

/* Project */
INSERT INTO Projects(ID, Name, KeyName, Description, CreationDate)
VALUES (1, "Test Product Backlog", "TPB", "Test of Product Backlog.", "2015-03-20");

/* Releases */
INSERT INTO Releases(ID, Name, ReleaseNumber, StartDate, EndDate, NumberOfSprint, ProjectID, IsActive)
VALUES (1, "Release 1", 1, "2014-08-02", "2014-11-01", 1, 1, false);
INSERT INTO Releases(ID, Name, ReleaseNumber, StartDate, EndDate, NumberOfSprint, ProjectID, IsActive)
VALUES (2, "Release 2", 2, "2014-11-02", "2014-12-01", 1, 1, true);

/* Sprints */
INSERT INTO Sprints(ID, Name, SprintNumber, StartDate, EndDate, ReleaseID, IsActive, IsClosed, Velocity, ReleaseRemainingPointEndOfSprint, BusinessValueDone)
VALUES (1, "Sprint 1.1", 1, "2014-07-02", "2014-07-30", 1, false, true, 33.5, 81, 20.5);
INSERT INTO Sprints(ID, Name, SprintNumber, StartDate, EndDate, ReleaseID, IsActive, IsClosed, Velocity, ReleaseRemainingPointEndOfSprint, BusinessValueDone)
VALUES (2, "Sprint 2.1", 2, "2014-08-02", "2014-08-30", 2, true, false, 32.5, 48.5, 26);

/* Estimation */
INSERT INTO StoryEstimation(ID, Name, Value) 
VALUES (1, "0", 0);
INSERT INTO StoryEstimation(ID, Name, Value) 
VALUES (2, "1/2", 0.5);
INSERT INTO StoryEstimation(ID, Name, Value) 
VALUES (3, "1", 1);
INSERT INTO StoryEstimation(ID, Name, Value) 
VALUES (4, "?", null);

/* Business Value */
INSERT INTO StoryBusinessValue(ID, Name, Value) 
VALUES (1, "0", 0);
INSERT INTO StoryBusinessValue(ID, Name, Value) 
VALUES (2, "1/2", 0.5);
INSERT INTO StoryBusinessValue(ID, Name, Value) 
VALUES (3, "1", 1);
INSERT INTO StoryBusinessValue(ID, Name, Value) 
VALUES (4, "?", null);

/* SandBox */
INSERT INTO BacklogItems(ID,Title,Description,BacklogItemType,ProductBacklogBox,EstimationID,BusinessValueID,
CreationDate,LastUpdateDate,CreatorID,ProjectID,ReleaseID,SprintID)
VALUES(1,"IDEA 1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et malesuada massa. Pellentesque ut eros nunc. Integer dapibus ipsum non urna imperdiet euismod. Sed sed turpis posuere, sodales purus eget, hendrerit diam. Aenean vel odio eget nibh elementum convallis. Fusce gravida aliquet nullam.",
0,0,4,4,"2014-07-02", "2014-07-30",1,1,null,null);
INSERT INTO BacklogItems(ID,Title,Description,BacklogItemType,ProductBacklogBox,EstimationID,BusinessValueID,
CreationDate,LastUpdateDate,CreatorID,ProjectID,ReleaseID,SprintID)
VALUES(2,"IDEA 2", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et malesuada massa. Pellentesque ut eros nunc. Integer dapibus ipsum non urna imperdiet euismod. Sed sed turpis posuere, sodales purus eget, hendrerit diam. Aenean vel odio eget nibh elementum convallis. Fusce gravida aliquet nullam.",
0,0,4,4,"2014-07-02", "2014-07-30",1,1,null,null);
INSERT INTO BacklogItems(ID,Title,Description,BacklogItemType,ProductBacklogBox,EstimationID,BusinessValueID,
CreationDate,LastUpdateDate,CreatorID,ProjectID,ReleaseID,SprintID)
VALUES(3,"IDEA 3", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et malesuada massa. Pellentesque ut eros nunc. Integer dapibus ipsum non urna imperdiet euismod. Sed sed turpis posuere, sodales purus eget, hendrerit diam. Aenean vel odio eget nibh elementum convallis. Fusce gravida aliquet nullam.",
0,0,4,4,"2014-07-02", "2014-07-30",1,1,null,null);
INSERT INTO BacklogItems(ID,Title,Description,BacklogItemType,ProductBacklogBox,EstimationID,BusinessValueID,
CreationDate,LastUpdateDate,CreatorID,ProjectID,ReleaseID,SprintID)
VALUES(4,"IDEA 4", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et malesuada massa. Pellentesque ut eros nunc. Integer dapibus ipsum non urna imperdiet euismod. Sed sed turpis posuere, sodales purus eget, hendrerit diam. Aenean vel odio eget nibh elementum convallis. Fusce gravida aliquet nullam.",
0,0,4,4,"2014-07-02", "2014-07-30",1,1,null,null);

/* IceBox */
INSERT INTO BacklogItems(ID,Title,Description,BacklogItemType,ProductBacklogBox,EstimationID,BusinessValueID,
CreationDate,LastUpdateDate,CreatorID,ProjectID,ReleaseID,SprintID)
VALUES(5,"ICEBOX 1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et malesuada massa. Pellentesque ut eros nunc. Integer dapibus ipsum non urna imperdiet euismod. Sed sed turpis posuere, sodales purus eget, hendrerit diam. Aenean vel odio eget nibh elementum convallis. Fusce gravida aliquet nullam.",
0,1,4,4,"2014-07-02", "2014-07-30",1,1,null,null);
INSERT INTO BacklogItems(ID,Title,Description,BacklogItemType,ProductBacklogBox,EstimationID,BusinessValueID,
CreationDate,LastUpdateDate,CreatorID,ProjectID,ReleaseID,SprintID)
VALUES(6,"ICEBOX 2", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et malesuada massa. Pellentesque ut eros nunc. Integer dapibus ipsum non urna imperdiet euismod. Sed sed turpis posuere, sodales purus eget, hendrerit diam. Aenean vel odio eget nibh elementum convallis. Fusce gravida aliquet nullam.",
1,1,3,2,"2014-07-02", "2014-07-30",1,1,null,null);
INSERT INTO BacklogItems(ID,Title,Description,BacklogItemType,ProductBacklogBox,EstimationID,BusinessValueID,
CreationDate,LastUpdateDate,CreatorID,ProjectID,ReleaseID,SprintID)
VALUES(7,"ICEBOX 3", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et malesuada massa. Pellentesque ut eros nunc. Integer dapibus ipsum non urna imperdiet euismod. Sed sed turpis posuere, sodales purus eget, hendrerit diam. Aenean vel odio eget nibh elementum convallis. Fusce gravida aliquet nullam.",
2,1,3,2,"2014-07-02", "2014-07-30",1,1,null,null);
INSERT INTO BacklogItems(ID,Title,Description,BacklogItemType,ProductBacklogBox,EstimationID,BusinessValueID,
CreationDate,LastUpdateDate,CreatorID,ProjectID,ReleaseID,SprintID)
VALUES(8,"ICEBOX 4", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et malesuada massa. Pellentesque ut eros nunc. Integer dapibus ipsum non urna imperdiet euismod. Sed sed turpis posuere, sodales purus eget, hendrerit diam. Aenean vel odio eget nibh elementum convallis. Fusce gravida aliquet nullam.",
3,1,2,1,"2014-07-02", "2014-07-30",1,1,null,null);
INSERT INTO BacklogItems(ID,Title,Description,BacklogItemType,ProductBacklogBox,EstimationID,BusinessValueID,
CreationDate,LastUpdateDate,CreatorID,ProjectID,ReleaseID,SprintID)
VALUES(9,"ICEBOX 5", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et malesuada massa. Pellentesque ut eros nunc. Integer dapibus ipsum non urna imperdiet euismod. Sed sed turpis posuere, sodales purus eget, hendrerit diam. Aenean vel odio eget nibh elementum convallis. Fusce gravida aliquet nullam.",
4,1,2,3,"2014-07-02", "2014-07-30",1,1,null,null);
INSERT INTO BacklogItems(ID,Title,Description,BacklogItemType,ProductBacklogBox,EstimationID,BusinessValueID,
CreationDate,LastUpdateDate,CreatorID,ProjectID,ReleaseID,SprintID)
VALUES(10,"ICEBOX 6", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et malesuada massa. Pellentesque ut eros nunc. Integer dapibus ipsum non urna imperdiet euismod. Sed sed turpis posuere, sodales purus eget, hendrerit diam. Aenean vel odio eget nibh elementum convallis. Fusce gravida aliquet nullam.",
5,1,2,1,"2014-07-02", "2014-07-30",1,1,null,null);
INSERT INTO BacklogItems(ID,Title,Description,BacklogItemType,ProductBacklogBox,EstimationID,BusinessValueID,
CreationDate,LastUpdateDate,CreatorID,ProjectID,ReleaseID,SprintID)
VALUES(11,"ICEBOX 7", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et malesuada massa. Pellentesque ut eros nunc. Integer dapibus ipsum non urna imperdiet euismod. Sed sed turpis posuere, sodales purus eget, hendrerit diam. Aenean vel odio eget nibh elementum convallis. Fusce gravida aliquet nullam.",
6,1,1,1,"2014-07-02", "2014-07-30",1,1,null,null);
INSERT INTO BacklogItems(ID,Title,Description,BacklogItemType,ProductBacklogBox,EstimationID,BusinessValueID,
CreationDate,LastUpdateDate,CreatorID,ProjectID,ReleaseID,SprintID)
VALUES(12,"ICEBOX 8", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et malesuada massa. Pellentesque ut eros nunc. Integer dapibus ipsum non urna imperdiet euismod. Sed sed turpis posuere, sodales purus eget, hendrerit diam. Aenean vel odio eget nibh elementum convallis. Fusce gravida aliquet nullam.",
7,1,4,4,"2014-07-02", "2014-07-30",1,1,null,null);

/*Culture Box */
INSERT INTO BacklogItems(ID,Title,Description,BacklogItemType,ProductBacklogBox,EstimationID,BusinessValueID,
CreationDate,LastUpdateDate,CreatorID,ProjectID,ReleaseID,SprintID)
VALUES(13,"CULTURE BOX 1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et malesuada massa. Pellentesque ut eros nunc. Integer dapibus ipsum non urna imperdiet euismod. Sed sed turpis posuere, sodales purus eget, hendrerit diam. Aenean vel odio eget nibh elementum convallis. Fusce gravida aliquet nullam.",
1,2,4,4,"2014-07-02", "2014-07-30",1,1,1,null);
INSERT INTO BacklogItems(ID,Title,Description,BacklogItemType,ProductBacklogBox,EstimationID,BusinessValueID,
CreationDate,LastUpdateDate,CreatorID,ProjectID,ReleaseID,SprintID)
VALUES(14,"CULTURE BOX 2", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et malesuada massa. Pellentesque ut eros nunc. Integer dapibus ipsum non urna imperdiet euismod. Sed sed turpis posuere, sodales purus eget, hendrerit diam. Aenean vel odio eget nibh elementum convallis. Fusce gravida aliquet nullam.",
2,2,2,3,"2014-07-02", "2014-07-30",1,1,1,null);
INSERT INTO BacklogItems(ID,Title,Description,BacklogItemType,ProductBacklogBox,EstimationID,BusinessValueID,
CreationDate,LastUpdateDate,CreatorID,ProjectID,ReleaseID,SprintID)
VALUES(15,"CULTURE BOX 3", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et malesuada massa. Pellentesque ut eros nunc. Integer dapibus ipsum non urna imperdiet euismod. Sed sed turpis posuere, sodales purus eget, hendrerit diam. Aenean vel odio eget nibh elementum convallis. Fusce gravida aliquet nullam.",
3,2,2,3,"2014-07-02", "2014-07-30",1,1,1,null);
INSERT INTO BacklogItems(ID,Title,Description,BacklogItemType,ProductBacklogBox,EstimationID,BusinessValueID,
CreationDate,LastUpdateDate,CreatorID,ProjectID,ReleaseID,SprintID)
VALUES(16,"CULTURE BOX 4", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et malesuada massa. Pellentesque ut eros nunc. Integer dapibus ipsum non urna imperdiet euismod. Sed sed turpis posuere, sodales purus eget, hendrerit diam. Aenean vel odio eget nibh elementum convallis. Fusce gravida aliquet nullam.",
4,2,2,3,"2014-07-02", "2014-07-30",1,1,1,null);
INSERT INTO BacklogItems(ID,Title,Description,BacklogItemType,ProductBacklogBox,EstimationID,BusinessValueID,
CreationDate,LastUpdateDate,CreatorID,ProjectID,ReleaseID,SprintID)
VALUES(17,"CULTURE BOX 5", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et malesuada massa. Pellentesque ut eros nunc. Integer dapibus ipsum non urna imperdiet euismod. Sed sed turpis posuere, sodales purus eget, hendrerit diam. Aenean vel odio eget nibh elementum convallis. Fusce gravida aliquet nullam.",
5,2,2,3,"2014-07-02", "2014-07-30",1,1,1,null);
INSERT INTO BacklogItems(ID,Title,Description,BacklogItemType,ProductBacklogBox,EstimationID,BusinessValueID,
CreationDate,LastUpdateDate,CreatorID,ProjectID,ReleaseID,SprintID)
VALUES(18,"CULTURE BOX 6", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et malesuada massa. Pellentesque ut eros nunc. Integer dapibus ipsum non urna imperdiet euismod. Sed sed turpis posuere, sodales purus eget, hendrerit diam. Aenean vel odio eget nibh elementum convallis. Fusce gravida aliquet nullam.",
6,2,2,3,"2014-07-02", "2014-07-30",1,1,1,null);
INSERT INTO BacklogItems(ID,Title,Description,BacklogItemType,ProductBacklogBox,EstimationID,BusinessValueID,
CreationDate,LastUpdateDate,CreatorID,ProjectID,ReleaseID,SprintID)
VALUES(19,"CULTURE BOX 7", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et malesuada massa. Pellentesque ut eros nunc. Integer dapibus ipsum non urna imperdiet euismod. Sed sed turpis posuere, sodales purus eget, hendrerit diam. Aenean vel odio eget nibh elementum convallis. Fusce gravida aliquet nullam.",
7,2,2,3,"2014-07-02", "2014-07-30",1,1,1,null);

/* Start Box */
INSERT INTO BacklogItems(ID,Title,Description,BacklogItemType,ProductBacklogBox,EstimationID,BusinessValueID,
CreationDate,LastUpdateDate,CreatorID,ProjectID,ReleaseID,SprintID)
VALUES(20,"START BOX 1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et malesuada massa. Pellentesque ut eros nunc. Integer dapibus ipsum non urna imperdiet euismod. Sed sed turpis posuere, sodales purus eget, hendrerit diam. Aenean vel odio eget nibh elementum convallis. Fusce gravida aliquet nullam.",
3,3,2,3,"2014-07-02", "2014-07-30",1,1,1,null);
INSERT INTO BacklogItems(ID,Title,Description,BacklogItemType,ProductBacklogBox,EstimationID,BusinessValueID,
CreationDate,LastUpdateDate,CreatorID,ProjectID,ReleaseID,SprintID)
VALUES(21,"START BOX 2", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et malesuada massa. Pellentesque ut eros nunc. Integer dapibus ipsum non urna imperdiet euismod. Sed sed turpis posuere, sodales purus eget, hendrerit diam. Aenean vel odio eget nibh elementum convallis. Fusce gravida aliquet nullam.",
4,3,2,3,"2014-07-02", "2014-07-30",1,1,1,null);
INSERT INTO BacklogItems(ID,Title,Description,BacklogItemType,ProductBacklogBox,EstimationID,BusinessValueID,
CreationDate,LastUpdateDate,CreatorID,ProjectID,ReleaseID,SprintID)
VALUES(22,"START BOX 3", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et malesuada massa. Pellentesque ut eros nunc. Integer dapibus ipsum non urna imperdiet euismod. Sed sed turpis posuere, sodales purus eget, hendrerit diam. Aenean vel odio eget nibh elementum convallis. Fusce gravida aliquet nullam.",
5,3,1,3,"2014-07-02", "2014-07-30",1,1,1,null);
INSERT INTO BacklogItems(ID,Title,Description,BacklogItemType,ProductBacklogBox,EstimationID,BusinessValueID,
CreationDate,LastUpdateDate,CreatorID,ProjectID,ReleaseID,SprintID)
VALUES(23,"START BOX 4", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et malesuada massa. Pellentesque ut eros nunc. Integer dapibus ipsum non urna imperdiet euismod. Sed sed turpis posuere, sodales purus eget, hendrerit diam. Aenean vel odio eget nibh elementum convallis. Fusce gravida aliquet nullam.",
6,3,1,3,"2014-07-02", "2014-07-30",1,1,1,null);
INSERT INTO BacklogItems(ID,Title,Description,BacklogItemType,ProductBacklogBox,EstimationID,BusinessValueID,
CreationDate,LastUpdateDate,CreatorID,ProjectID,ReleaseID,SprintID)
VALUES(24,"START BOX 5", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et malesuada massa. Pellentesque ut eros nunc. Integer dapibus ipsum non urna imperdiet euismod. Sed sed turpis posuere, sodales purus eget, hendrerit diam. Aenean vel odio eget nibh elementum convallis. Fusce gravida aliquet nullam.",
7,3,2,3,"2014-07-02", "2014-07-30",1,1,1,null);

/* Sprint Box */
INSERT INTO BacklogItems(ID,Title,Description,BacklogItemType,ProductBacklogBox,EstimationID,BusinessValueID,
CreationDate,LastUpdateDate,CreatorID,ProjectID,ReleaseID,SprintID)
VALUES(25,"SPRINT BOX 1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et malesuada massa. Pellentesque ut eros nunc. Integer dapibus ipsum non urna imperdiet euismod. Sed sed turpis posuere, sodales purus eget, hendrerit diam. Aenean vel odio eget nibh elementum convallis. Fusce gravida aliquet nullam.",
3,4,2,3,"2014-07-02", "2014-07-30",1,1,1,2);
INSERT INTO BacklogItems(ID,Title,Description,BacklogItemType,ProductBacklogBox,EstimationID,BusinessValueID,
CreationDate,LastUpdateDate,CreatorID,ProjectID,ReleaseID,SprintID)
VALUES(26,"SPRINT BOX 2", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et malesuada massa. Pellentesque ut eros nunc. Integer dapibus ipsum non urna imperdiet euismod. Sed sed turpis posuere, sodales purus eget, hendrerit diam. Aenean vel odio eget nibh elementum convallis. Fusce gravida aliquet nullam.",
4,4,2,3,"2014-07-02", "2014-07-30",1,1,1,2);
INSERT INTO BacklogItems(ID,Title,Description,BacklogItemType,ProductBacklogBox,EstimationID,BusinessValueID,
CreationDate,LastUpdateDate,CreatorID,ProjectID,ReleaseID,SprintID)
VALUES(27,"SPRINT BOX 3", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et malesuada massa. Pellentesque ut eros nunc. Integer dapibus ipsum non urna imperdiet euismod. Sed sed turpis posuere, sodales purus eget, hendrerit diam. Aenean vel odio eget nibh elementum convallis. Fusce gravida aliquet nullam.",
5,4,1,3,"2014-07-02", "2014-07-30",1,1,1,2);
INSERT INTO BacklogItems(ID,Title,Description,BacklogItemType,ProductBacklogBox,EstimationID,BusinessValueID,
CreationDate,LastUpdateDate,CreatorID,ProjectID,ReleaseID,SprintID)
VALUES(28,"SPRINT BOX 4", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et malesuada massa. Pellentesque ut eros nunc. Integer dapibus ipsum non urna imperdiet euismod. Sed sed turpis posuere, sodales purus eget, hendrerit diam. Aenean vel odio eget nibh elementum convallis. Fusce gravida aliquet nullam.",
6,4,1,3,"2014-07-02", "2014-07-30",1,1,1,2);
INSERT INTO BacklogItems(ID,Title,Description,BacklogItemType,ProductBacklogBox,EstimationID,BusinessValueID,
CreationDate,LastUpdateDate,CreatorID,ProjectID,ReleaseID,SprintID)
VALUES(29,"SPRINT BOX 5", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et malesuada massa. Pellentesque ut eros nunc. Integer dapibus ipsum non urna imperdiet euismod. Sed sed turpis posuere, sodales purus eget, hendrerit diam. Aenean vel odio eget nibh elementum convallis. Fusce gravida aliquet nullam.",
7,4,2,3,"2014-07-02", "2014-07-30",1,1,1,2);

/* Harvest Box */
INSERT INTO BacklogItems(ID,Title,Description,BacklogItemType,ProductBacklogBox,EstimationID,BusinessValueID,
CreationDate,LastUpdateDate,CreatorID,ProjectID,ReleaseID,SprintID)
VALUES(30,"HARVEST BOX 1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et malesuada massa. Pellentesque ut eros nunc. Integer dapibus ipsum non urna imperdiet euismod. Sed sed turpis posuere, sodales purus eget, hendrerit diam. Aenean vel odio eget nibh elementum convallis. Fusce gravida aliquet nullam.",
3,5,2,3,"2014-07-02", "2014-07-30",1,1,1,1);
INSERT INTO BacklogItems(ID,Title,Description,BacklogItemType,ProductBacklogBox,EstimationID,BusinessValueID,
CreationDate,LastUpdateDate,CreatorID,ProjectID,ReleaseID,SprintID)
VALUES(31,"HARVEST BOX 2", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et malesuada massa. Pellentesque ut eros nunc. Integer dapibus ipsum non urna imperdiet euismod. Sed sed turpis posuere, sodales purus eget, hendrerit diam. Aenean vel odio eget nibh elementum convallis. Fusce gravida aliquet nullam.",
4,5,2,3,"2014-07-02", "2014-07-30",1,1,1,1);
INSERT INTO BacklogItems(ID,Title,Description,BacklogItemType,ProductBacklogBox,EstimationID,BusinessValueID,
CreationDate,LastUpdateDate,CreatorID,ProjectID,ReleaseID,SprintID)
VALUES(32,"HARVEST BOX 3", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et malesuada massa. Pellentesque ut eros nunc. Integer dapibus ipsum non urna imperdiet euismod. Sed sed turpis posuere, sodales purus eget, hendrerit diam. Aenean vel odio eget nibh elementum convallis. Fusce gravida aliquet nullam.",
5,5,1,3,"2014-07-02", "2014-07-30",1,1,1,1);