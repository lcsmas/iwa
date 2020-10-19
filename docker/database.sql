
------------------------------------------------------------
--        Script Postgre 
------------------------------------------------------------



------------------------------------------------------------
-- Table: user
------------------------------------------------------------
CREATE TABLE "user"(
	id_user    SERIAL NOT NULL ,
	mail       VARCHAR (50) NOT NULL ,
	password   VARCHAR (50) NOT NULL  ,
	CONSTRAINT user_PK PRIMARY KEY (id_user)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: state
------------------------------------------------------------
CREATE TABLE state
(
	id_state      SERIAL NOT NULL ,
	label_state   VARCHAR (50) NOT NULL  ,
	CONSTRAINT State_PK PRIMARY KEY (id_state)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: location
------------------------------------------------------------
CREATE TABLE location(
	id_location   SERIAL NOT NULL ,
	longitute     INT  NOT NULL ,
	latitude      INT  NOT NULL  ,
	CONSTRAINT location_PK PRIMARY KEY (id_location)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: notification
------------------------------------------------------------
CREATE TABLE notification
(
	id_notification      SERIAL NOT NULL ,
	date_notification    TIMESTAMP  NOT NULL ,
	label_notification   VARCHAR (50) NOT NULL ,
	id_location          INT  NOT NULL ,
	id_user              INT  NOT NULL ,
	id_state             INT  NOT NULL  ,
	CONSTRAINT notification_PK PRIMARY KEY (id_notification)

	,CONSTRAINT notification_location_FK FOREIGN KEY (id_location) REFERENCES location(id_location)
	,CONSTRAINT notification_user0_FK FOREIGN KEY (id_user) REFERENCES "user"(id_user)
	,CONSTRAINT notification_state1_FK FOREIGN KEY (id_state) REFERENCES state (id_state)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: user_state
------------------------------------------------------------
CREATE TABLE "user_state"(
	id_state   INT  NOT NULL ,
	id_user    INT  NOT NULL ,
	date       TIMESTAMP  NOT NULL  ,
	CONSTRAINT user_state_PK PRIMARY KEY (id_state,id_user)

	,CONSTRAINT user_state_state_FK FOREIGN KEY (id_state) REFERENCES state (id_state)
	,CONSTRAINT user_state_user0_FK FOREIGN KEY (id_user) REFERENCES "user"(id_user)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: user_localized
------------------------------------------------------------
CREATE TABLE "user_localized"(
	id_location   INT  NOT NULL ,
	id_user       INT  NOT NULL ,
	date          TIMESTAMP  NOT NULL  ,
	CONSTRAINT user_localized_PK PRIMARY KEY (id_location,id_user)

	,CONSTRAINT user_localized_location_FK FOREIGN KEY (id_location) REFERENCES location(id_location)
	,CONSTRAINT user_localized_user0_FK FOREIGN KEY (id_user) REFERENCES "user"(id_user)
)WITHOUT OIDS;





