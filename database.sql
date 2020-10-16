#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: User
#------------------------------------------------------------

CREATE TABLE User(
        id_user  Int  Auto_increment  NOT NULL ,
        mail     Varchar (50) NOT NULL ,
        password Varchar (50) NOT NULL
	,CONSTRAINT User_PK PRIMARY KEY (id_user)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: State
#------------------------------------------------------------

CREATE TABLE State(
        id_state    Int  Auto_increment  NOT NULL ,
        label_state Varchar (50) NOT NULL
	,CONSTRAINT State_PK PRIMARY KEY (id_state)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Location
#------------------------------------------------------------

CREATE TABLE Location(
        id_location Int  Auto_increment  NOT NULL ,
        longitute   Int NOT NULL ,
        latitude    Int NOT NULL
	,CONSTRAINT Location_PK PRIMARY KEY (id_location)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Notification
#------------------------------------------------------------

CREATE TABLE Notification(
        id_notification    Int  Auto_increment  NOT NULL ,
        date_notification  TimeStamp NOT NULL ,
        label_notification Varchar (50) NOT NULL ,
        id_location        Int NOT NULL ,
        id_user            Int NOT NULL ,
        id_state           Int NOT NULL
	,CONSTRAINT Notification_PK PRIMARY KEY (id_notification)

	,CONSTRAINT Notification_Location_FK FOREIGN KEY (id_location) REFERENCES Location(id_location)
	,CONSTRAINT Notification_User0_FK FOREIGN KEY (id_user) REFERENCES User(id_user)
	,CONSTRAINT Notification_State1_FK FOREIGN KEY (id_state) REFERENCES State(id_state)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: user_state
#------------------------------------------------------------

CREATE TABLE user_state(
        id_state Int NOT NULL ,
        id_user  Int NOT NULL ,
        date     TimeStamp NOT NULL
	,CONSTRAINT user_state_PK PRIMARY KEY (id_state,id_user)

	,CONSTRAINT user_state_State_FK FOREIGN KEY (id_state) REFERENCES State(id_state)
	,CONSTRAINT user_state_User0_FK FOREIGN KEY (id_user) REFERENCES User(id_user)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: user_localized
#------------------------------------------------------------

CREATE TABLE user_localized(
        id_location Int NOT NULL ,
        id_user     Int NOT NULL ,
        date        TimeStamp NOT NULL
	,CONSTRAINT user_localized_PK PRIMARY KEY (id_location,id_user)

	,CONSTRAINT user_localized_Location_FK FOREIGN KEY (id_location) REFERENCES Location(id_location)
	,CONSTRAINT user_localized_User0_FK FOREIGN KEY (id_user) REFERENCES User(id_user)
)ENGINE=InnoDB;

