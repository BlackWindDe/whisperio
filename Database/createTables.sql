/*---------------------------------------------------*/
/* Script to create the tables used by whisperio.    */
/*---------------------------------------------------*/

/*--------------------------------------------*/
/* Association tables                         */
/*--------------------------------------------*/

/*--------------------------------------------*/
/* Main tables                                */
/*--------------------------------------------*/

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
Forename    VARCHAR(50) NOT NULL,
LastName    VARCHAR(50) NOT NULL,
CONSTRAINT Users_ID_PK PRIMARY KEY (ID),
CONSTRAINT Users_Mail_UQ UNIQUE (Mail)
);

/* ----- Project Management ----- */
Create Table Projects
(
ID               INT NOT NULL AUTO_INCREMENT,
Name             VARCHAR(50) NOT NULL,
KeyName          VARCHAR(3) NOT NULL,
Description      VARCHAR(300) NOT NULL,
CreationDate     DATE NOT NULL,
CONSTRAINT Projects_ID_PK PRIMARY KEY (ID),
CONSTRAINT Projects_KeyName_UQ UNIQUE (KeyName)
);

Create Table Releases
(
ID           INT NOT NULL AUTO_INCREMENT,
Name         VARCHAR(50) NOT NULL,
StartDate    DATE NOT NULL,
EndDate  DATE NOT NULL,
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
EndDate     DATE NOT NULL,
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
Description     TEXT NOT NULL,
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

/*--------------------------------------------*/
/* Association tables                         */
/*--------------------------------------------*/