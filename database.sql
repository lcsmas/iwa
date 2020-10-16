#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------

CREATE DATABASE iwa;

------------------------------------------------------------
--        Script Postgre 
------------------------------------------------------------



------------------------------------------------------------
-- Table: User
------------------------------------------------------------
CREATE TABLE iwa.User(
	id_user    SERIAL NOT NULL ,
	mail       VARCHAR (50) NOT NULL ,
	password   VARCHAR (50) NOT NULL  ,
	CONSTRAINT User_PK PRIMARY KEY (id_user)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: State
------------------------------------------------------------
CREATE TABLE iwa.State(
	id_state      SERIAL NOT NULL ,
	label_state   VARCHAR (50) NOT NULL  ,
	CONSTRAINT State_PK PRIMARY KEY (id_state)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: Location
------------------------------------------------------------
CREATE TABLE iwa.Location(
	id_location   SERIAL NOT NULL ,
	longitute     INT  NOT NULL ,
	latitude      INT  NOT NULL  ,
	CONSTRAINT Location_PK PRIMARY KEY (id_location)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: Notification
------------------------------------------------------------
CREATE TABLE iwa.Notification(
	id_notification      SERIAL NOT NULL ,
	date_notification    TIMESTAMP  NOT NULL ,
	label_notification   VARCHAR (50) NOT NULL ,
	id_location          INT  NOT NULL ,
	id_user              INT  NOT NULL ,
	id_state             INT  NOT NULL  ,
	CONSTRAINT Notification_PK PRIMARY KEY (id_notification)

	,CONSTRAINT Notification_Location_FK FOREIGN KEY (id_location) REFERENCES iwa.Location(id_location)
	,CONSTRAINT Notification_User0_FK FOREIGN KEY (id_user) REFERENCES iwa.User(id_user)
	,CONSTRAINT Notification_State1_FK FOREIGN KEY (id_state) REFERENCES iwa.State(id_state)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: user_state
------------------------------------------------------------
CREATE TABLE iwa.user_state(
	id_state   INT  NOT NULL ,
	id_user    INT  NOT NULL ,
	date       TIMESTAMP  NOT NULL  ,
	CONSTRAINT user_state_PK PRIMARY KEY (id_state,id_user)

	,CONSTRAINT user_state_State_FK FOREIGN KEY (id_state) REFERENCES iwa.State(id_state)
	,CONSTRAINT user_state_User0_FK FOREIGN KEY (id_user) REFERENCES iwa.User(id_user)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: user_localized
------------------------------------------------------------
CREATE TABLE iwa.user_localized(
	id_location   INT  NOT NULL ,
	id_user       INT  NOT NULL ,
	date          TIMESTAMP  NOT NULL  ,
	CONSTRAINT user_localized_PK PRIMARY KEY (id_location,id_user)

	,CONSTRAINT user_localized_Location_FK FOREIGN KEY (id_location) REFERENCES iwa.Location(id_location)
	,CONSTRAINT user_localized_User0_FK FOREIGN KEY (id_user) REFERENCES iwa.User(id_user)
)WITHOUT OIDS;





