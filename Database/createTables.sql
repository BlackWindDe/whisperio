/*---------------------------------------------------*/
/* Script to create the tables used by whisperio.    */
/*---------------------------------------------------*/

/*--------------------------------------------*/
/* Association tables                         */
/*--------------------------------------------*/
DROP TABLE IF EXISTS EventsGuests;
DROP TABLE IF EXISTS ProjectsAssignement;
DROP TABLE IF EXISTS BacklogItemsAssignees;

/*--------------------------------------------*/
/* Main tables                                */
/*--------------------------------------------*/
/* ----- Events ----- */
DROP TABLE IF EXISTS Events;

/* ----- Product Management ----- */
DROP TABLE IF EXISTS BacklogItems;

/* ----- Project Management ----- */
DROP TABLE IF EXISTS Sprints;
DROP TABLE IF EXISTS Releases;
DROP TABLE IF EXISTS Projects;

/* ----- Users ----- */
DROP TABLE IF EXISTS Users;

/*--------------------------------------------*/
/* Main tables                                */
/*--------------------------------------------*/

/* ----- Users ----- */
Create Table Users
(
ID          INT NOT NULL AUTO_INCREMENT,
Mail        VARCHAR(50) NOT NULL,
Forename    VARCHAR(15) NOT NULL,
LastName    VARCHAR(15) NOT NULL,
Password    VARCHAR(150) NOT NULL,
IsAdmin     BOOL NOT NULL Default False,
CONSTRAINT Users_ID_PK PRIMARY KEY (ID),
CONSTRAINT Users_Mail_UQ UNIQUE (Mail)
);

/* ----- Project Management ----- */
Create Table Projects
(
ID               INT NOT NULL AUTO_INCREMENT,
Name             VARCHAR(50) NOT NULL,
KeyName          VARCHAR(3) NOT NULL,
ScrumMasterID    INT NOT NULL,
ProductOwnerID   INT NOT NULL,
Description      VARCHAR(300) NOT NULL,
CreationDate     DATE NOT NULL,
URLWebsite       VARCHAR(100) NOT NULL,
URLRepository    VARCHAR(100) NOT NULL,
IsActive         BOOL NOT NULL Default True,
CONSTRAINT Projects_ID_PK PRIMARY KEY (ID),
CONSTRAINT Projects_KeyName_UQ UNIQUE (KeyName),
CONSTRAINT Users_SM_Projects_FK FOREIGN KEY( ScrumMasterID ) REFERENCES Users ( ID ),
CONSTRAINT Users_PO_Projects_FK FOREIGN KEY( ProductOwnerID ) REFERENCES Users ( ID )
);

Create Table Releases
(
ID           INT NOT NULL AUTO_INCREMENT,
Name         VARCHAR(50) NOT NULL,
StartDate    DATE NOT NULL,
ReleaseDate  DATE NOT NULL,
ProjectID    INT NOT NULL,
IsActive     BOOL NOT NULL Default False,
CONSTRAINT Releases_ID_PK PRIMARY KEY (ID),
CONSTRAINT Projects_Releases_FK FOREIGN KEY( ProjectID ) REFERENCES Projects ( ID )
);

Create Table Sprints
(
ID           INT NOT NULL AUTO_INCREMENT,
Name         VARCHAR(50) NOT NULL,
StartDate    DATE NOT NULL,
StopDate     DATE NOT NULL,
ReleaseID    INT NOT NULL,
IsActive     BOOL NOT NULL Default False,
CONSTRAINT Sprints_ID_PK PRIMARY KEY (ID),
CONSTRAINT Releases_Sprints_FK FOREIGN KEY( ReleaseID ) REFERENCES Releases ( ID )
);

/* ----- Product Management ----- */
Create Table BacklogItems
(
ID              INT NOT NULL AUTO_INCREMENT,
Title           VARCHAR(100) NOT NULL,
BacklogItemType INT NOT NULL,
Status          INT NOT NULL,
Description     TEXT NOT NULL,
UserField1      VARCHAR(50) NOT NULL,
UserField2      VARCHAR(50) NOT NULL,
CreationDate    DATETIME NOT NULL,
LastUpdateDate  DATETIME NOT NULL,
CreatorID       INT NOT NULL,
ProjectID       INT NOT NULL,
ReleaseID       INT,
SprintID        INT,
CONSTRAINT BacklogItems_ID_PK PRIMARY KEY (ID),
CONSTRAINT Users_BacklogItems_FK FOREIGN KEY( CreatorID ) REFERENCES Users ( ID ),
CONSTRAINT Projects_BacklogItems_FK FOREIGN KEY( ProjectID ) REFERENCES Projects ( ID ),
CONSTRAINT Releases_BacklogItems_FK FOREIGN KEY( ReleaseID ) REFERENCES Releases ( ID ),
CONSTRAINT Sprints_BacklogItems_FK FOREIGN KEY( SprintID ) REFERENCES Sprints ( ID )
);

/* ----- Events ----- */
Create Table Events
(
ID             INT NOT NULL AUTO_INCREMENT,
Title          VARCHAR(50) NOT NULL,
EventType      INT NOT NULL,
Place          VARCHAR(50) NOT NULL,
EventDate      DATETIME NOT NULL,
Duration       INT NOT NULL,
Description    VARCHAR(300) NOT NULL,
OrganizerID    INT NOT NULL,
ProjectID      INT NOT NULL,
CONSTRAINT Events_ID_PK PRIMARY KEY (ID),
CONSTRAINT Users_Events_FK FOREIGN KEY( OrganizerID ) REFERENCES Users ( ID ),
CONSTRAINT Projects_Events_FK FOREIGN KEY( ProjectID ) REFERENCES Projects ( ID )
);

/*--------------------------------------------*/
/* Association tables                         */
/*--------------------------------------------*/
Create Table EventsGuests
(
UserID      INT NOT NULL,
EventID     INT NOT NULL,
CONSTRAINT Users_EventsGuests_FK FOREIGN KEY( UserID ) REFERENCES Users ( ID ),
CONSTRAINT Events_EventsGuests_FK FOREIGN KEY( EventID ) REFERENCES Events ( ID )
);

Create Table BacklogItemsAssignees
(
BacklogItemID      INT NOT NULL,
AssigneeID         INT NOT NULL,
CONSTRAINT BacklogItems_BacklogItemsAssignees_FK FOREIGN KEY( BacklogItemID ) REFERENCES BacklogItems ( ID ),
CONSTRAINT Labels_BacklogItemsAssignees_FK FOREIGN KEY( AssigneeID ) REFERENCES Users ( ID )
);

Create Table ProjectsAssignement
(
UserID      INT NOT NULL,
ProjectID   INT NOT NULL,
CONSTRAINT Users_ProjectsAssignement_FK FOREIGN KEY( UserID ) REFERENCES Users ( ID ),
CONSTRAINT Projects_ProjectsAssignement_FK FOREIGN KEY( ProjectID ) REFERENCES Projects ( ID )
);