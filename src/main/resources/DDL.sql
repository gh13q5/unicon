
CREATE TABLE Company
(
	company_id           INT NOT NULL ,
	id                   VARCHAR(20) NOT NULL ,
	password             VARCHAR(20) NOT NULL ,
	email                VARCHAR(30) NOT NULL ,
	name                 VARCHAR(20) NOT NULL ,
	phone_number         VARCHAR(20) NOT NULL 
);

CREATE UNIQUE INDEX XPKCompany ON Company
(company_id   ASC);

ALTER TABLE Company
	ADD CONSTRAINT  XPKCompany PRIMARY KEY (company_id);

CREATE TABLE Game
(
	game_id              INT NOT NULL ,
	title                VARCHAR(50) NOT NULL ,
	start_date           DATE NOT NULL ,
	end_date             DATE NOT NULL ,
	image_address        VARCHAR(100) NULL ,
	description          VARCHAR(1000) NULL ,
	category             VARCHAR(10) NOT NULL ,
	reward_image         VARCHAR(100) NULL ,
	reward_text          VARCHAR(100) NULL ,
	total_reservations   INT DEFAULT  0  NOT NULL ,
	company_id           INT NOT NULL 
);

CREATE UNIQUE INDEX XPKGame ON Game
(game_id   ASC);

ALTER TABLE Game
	ADD CONSTRAINT  XPKGame PRIMARY KEY (game_id);

CREATE TABLE PointShopItem
(
	pointShopItem_id     INT NOT NULL ,
	name                 VARCHAR(20) NOT NULL ,
	price                INT DEFAULT  0  NOT NULL ,
	image_address        VARCHAR(100) NULL ,
	description          VARCHAR(100) NULL ,
	game_id              INT NOT NULL 
);

CREATE UNIQUE INDEX XPKPointShopItem ON PointShopItem
(pointShopItem_id   ASC);

ALTER TABLE PointShopItem
	ADD CONSTRAINT  XPKPointShopItem PRIMARY KEY (pointShopItem_id);

CREATE TABLE User
(
	user_id              INT NOT NULL ,
	id                   VARCHAR(20) NOT NULL ,
	password             VARCHAR(20) NOT NULL ,
	email                VARCHAR(30) NOT NULL ,
	name                 VARCHAR(20) NOT NULL ,
	phone_number         VARCHAR(20) NOT NULL ,
	birthday             DATE NOT NULL ,
	gender               INT NOT NULL ,
	interests            INT NULL ,
	point                INT DEFAULT  0  NOT NULL 
);

CREATE UNIQUE INDEX XPKUser ON User
(user_id   ASC);

ALTER TABLE User
	ADD CONSTRAINT  XPKUser PRIMARY KEY (user_id);

CREATE TABLE Reservation
(
	reservation_id       CHAR(18) NOT NULL ,
	reservation_date     CHAR(18) NOT NULL ,
	game_id              INT NOT NULL ,
	user_id              INT NOT NULL 
);

CREATE UNIQUE INDEX XPKReservation ON Reservation
(reservation_id   ASC);

ALTER TABLE Reservation
	ADD CONSTRAINT  XPKReservation PRIMARY KEY (reservation_id);

ALTER TABLE Game
	ADD (CONSTRAINT R_7 FOREIGN KEY (company_id) REFERENCES Company (company_id));

ALTER TABLE PointShopItem
	ADD (CONSTRAINT R_9 FOREIGN KEY (game_id) REFERENCES Game (game_id));

ALTER TABLE Reservation
	ADD (CONSTRAINT R_6 FOREIGN KEY (game_id) REFERENCES Game (game_id));

ALTER TABLE Reservation
	ADD (CONSTRAINT R_8 FOREIGN KEY (user_id) REFERENCES User (user_id));

CREATE  TRIGGER  tD_Company AFTER DELETE ON Company for each row
-- erwin Builtin Trigger
-- DELETE trigger on Company 
DECLARE NUMROWS INTEGER;
BEGIN
    /* erwin Builtin Trigger */
    /* Company  Game on parent delete restrict */
    /* ERWIN_RELATION:CHECKSUM="0000d19a", PARENT_OWNER="", PARENT_TABLE="Company"
    CHILD_OWNER="", CHILD_TABLE="Game"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_7", FK_COLUMNS="company_id" */
    SELECT count(*) INTO NUMROWS
      FROM Game
      WHERE
        /*  %JoinFKPK(Game,:%Old," = "," AND") */
        Game.company_id = :old.company_id;
    IF (NUMROWS > 0)
    THEN
      raise_application_error(
        -20001,
        'Cannot delete Company because Game exists.'
      );
    END IF;


-- erwin Builtin Trigger
END;
/

CREATE  TRIGGER tU_Company AFTER UPDATE ON Company for each row
-- erwin Builtin Trigger
-- UPDATE trigger on Company 
DECLARE NUMROWS INTEGER;
BEGIN
  /* erwin Builtin Trigger */
  /* Company  Game on parent update restrict */
  /* ERWIN_RELATION:CHECKSUM="0000fbd7", PARENT_OWNER="", PARENT_TABLE="Company"
    CHILD_OWNER="", CHILD_TABLE="Game"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_7", FK_COLUMNS="company_id" */
  IF
    /* %JoinPKPK(:%Old,:%New," <> "," OR ") */
    :old.company_id <> :new.company_id
  THEN
    SELECT count(*) INTO NUMROWS
      FROM Game
      WHERE
        /*  %JoinFKPK(Game,:%Old," = "," AND") */
        Game.company_id = :old.company_id;
    IF (NUMROWS > 0)
    THEN 
      raise_application_error(
        -20005,
        'Cannot update Company because Game exists.'
      );
    END IF;
  END IF;


-- erwin Builtin Trigger
END;
/


CREATE  TRIGGER  tD_Game AFTER DELETE ON Game for each row
-- erwin Builtin Trigger
-- DELETE trigger on Game 
DECLARE NUMROWS INTEGER;
BEGIN
    /* erwin Builtin Trigger */
    /* Game  PointShopItem on parent delete restrict */
    /* ERWIN_RELATION:CHECKSUM="0001cc40", PARENT_OWNER="", PARENT_TABLE="Game"
    CHILD_OWNER="", CHILD_TABLE="PointShopItem"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_9", FK_COLUMNS="game_id" */
    SELECT count(*) INTO NUMROWS
      FROM PointShopItem
      WHERE
        /*  %JoinFKPK(PointShopItem,:%Old," = "," AND") */
        PointShopItem.game_id = :old.game_id;
    IF (NUMROWS > 0)
    THEN
      raise_application_error(
        -20001,
        'Cannot delete Game because PointShopItem exists.'
      );
    END IF;

    /* erwin Builtin Trigger */
    /* Game  Reservation on parent delete restrict */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Game"
    CHILD_OWNER="", CHILD_TABLE="Reservation"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_6", FK_COLUMNS="game_id" */
    SELECT count(*) INTO NUMROWS
      FROM Reservation
      WHERE
        /*  %JoinFKPK(Reservation,:%Old," = "," AND") */
        Reservation.game_id = :old.game_id;
    IF (NUMROWS > 0)
    THEN
      raise_application_error(
        -20001,
        'Cannot delete Game because Reservation exists.'
      );
    END IF;


-- erwin Builtin Trigger
END;
/

CREATE  TRIGGER tI_Game BEFORE INSERT ON Game for each row
-- erwin Builtin Trigger
-- INSERT trigger on Game 
DECLARE NUMROWS INTEGER;
BEGIN
    /* erwin Builtin Trigger */
    /* Company  Game on child insert restrict */
    /* ERWIN_RELATION:CHECKSUM="0000ddf7", PARENT_OWNER="", PARENT_TABLE="Company"
    CHILD_OWNER="", CHILD_TABLE="Game"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_7", FK_COLUMNS="company_id" */
    SELECT count(*) INTO NUMROWS
      FROM Company
      WHERE
        /* %JoinFKPK(:%New,Company," = "," AND") */
        :new.company_id = Company.company_id;
    IF (
      /* %NotnullFK(:%New," IS NOT NULL AND") */
      
      NUMROWS = 0
    )
    THEN
      raise_application_error(
        -20002,
        'Cannot insert Game because Company does not exist.'
      );
    END IF;


-- erwin Builtin Trigger
END;
/

CREATE  TRIGGER tU_Game AFTER UPDATE ON Game for each row
-- erwin Builtin Trigger
-- UPDATE trigger on Game 
DECLARE NUMROWS INTEGER;
BEGIN
  /* erwin Builtin Trigger */
  /* Game  PointShopItem on parent update restrict */
  /* ERWIN_RELATION:CHECKSUM="000314a4", PARENT_OWNER="", PARENT_TABLE="Game"
    CHILD_OWNER="", CHILD_TABLE="PointShopItem"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_9", FK_COLUMNS="game_id" */
  IF
    /* %JoinPKPK(:%Old,:%New," <> "," OR ") */
    :old.game_id <> :new.game_id
  THEN
    SELECT count(*) INTO NUMROWS
      FROM PointShopItem
      WHERE
        /*  %JoinFKPK(PointShopItem,:%Old," = "," AND") */
        PointShopItem.game_id = :old.game_id;
    IF (NUMROWS > 0)
    THEN 
      raise_application_error(
        -20005,
        'Cannot update Game because PointShopItem exists.'
      );
    END IF;
  END IF;

  /* erwin Builtin Trigger */
  /* Game  Reservation on parent update restrict */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Game"
    CHILD_OWNER="", CHILD_TABLE="Reservation"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_6", FK_COLUMNS="game_id" */
  IF
    /* %JoinPKPK(:%Old,:%New," <> "," OR ") */
    :old.game_id <> :new.game_id
  THEN
    SELECT count(*) INTO NUMROWS
      FROM Reservation
      WHERE
        /*  %JoinFKPK(Reservation,:%Old," = "," AND") */
        Reservation.game_id = :old.game_id;
    IF (NUMROWS > 0)
    THEN 
      raise_application_error(
        -20005,
        'Cannot update Game because Reservation exists.'
      );
    END IF;
  END IF;

  /* erwin Builtin Trigger */
  /* Company  Game on child update restrict */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Company"
    CHILD_OWNER="", CHILD_TABLE="Game"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_7", FK_COLUMNS="company_id" */
  SELECT count(*) INTO NUMROWS
    FROM Company
    WHERE
      /* %JoinFKPK(:%New,Company," = "," AND") */
      :new.company_id = Company.company_id;
  IF (
    /* %NotnullFK(:%New," IS NOT NULL AND") */
    
    NUMROWS = 0
  )
  THEN
    raise_application_error(
      -20007,
      'Cannot update Game because Company does not exist.'
    );
  END IF;


-- erwin Builtin Trigger
END;
/


CREATE  TRIGGER tI_PointShopItem BEFORE INSERT ON PointShopItem for each row
-- erwin Builtin Trigger
-- INSERT trigger on PointShopItem 
DECLARE NUMROWS INTEGER;
BEGIN
    /* erwin Builtin Trigger */
    /* Game  PointShopItem on child insert restrict */
    /* ERWIN_RELATION:CHECKSUM="0000e4ba", PARENT_OWNER="", PARENT_TABLE="Game"
    CHILD_OWNER="", CHILD_TABLE="PointShopItem"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_9", FK_COLUMNS="game_id" */
    SELECT count(*) INTO NUMROWS
      FROM Game
      WHERE
        /* %JoinFKPK(:%New,Game," = "," AND") */
        :new.game_id = Game.game_id;
    IF (
      /* %NotnullFK(:%New," IS NOT NULL AND") */
      
      NUMROWS = 0
    )
    THEN
      raise_application_error(
        -20002,
        'Cannot insert PointShopItem because Game does not exist.'
      );
    END IF;


-- erwin Builtin Trigger
END;
/

CREATE  TRIGGER tU_PointShopItem AFTER UPDATE ON PointShopItem for each row
-- erwin Builtin Trigger
-- UPDATE trigger on PointShopItem 
DECLARE NUMROWS INTEGER;
BEGIN
  /* erwin Builtin Trigger */
  /* Game  PointShopItem on child update restrict */
  /* ERWIN_RELATION:CHECKSUM="0000e3a9", PARENT_OWNER="", PARENT_TABLE="Game"
    CHILD_OWNER="", CHILD_TABLE="PointShopItem"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_9", FK_COLUMNS="game_id" */
  SELECT count(*) INTO NUMROWS
    FROM Game
    WHERE
      /* %JoinFKPK(:%New,Game," = "," AND") */
      :new.game_id = Game.game_id;
  IF (
    /* %NotnullFK(:%New," IS NOT NULL AND") */
    
    NUMROWS = 0
  )
  THEN
    raise_application_error(
      -20007,
      'Cannot update PointShopItem because Game does not exist.'
    );
  END IF;


-- erwin Builtin Trigger
END;
/


CREATE  TRIGGER  tD_User AFTER DELETE ON User for each row
-- erwin Builtin Trigger
-- DELETE trigger on User 
DECLARE NUMROWS INTEGER;
BEGIN
    /* erwin Builtin Trigger */
    /* User  Reservation on parent delete restrict */
    /* ERWIN_RELATION:CHECKSUM="0000cfa6", PARENT_OWNER="", PARENT_TABLE="User"
    CHILD_OWNER="", CHILD_TABLE="Reservation"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_8", FK_COLUMNS="user_id" */
    SELECT count(*) INTO NUMROWS
      FROM Reservation
      WHERE
        /*  %JoinFKPK(Reservation,:%Old," = "," AND") */
        Reservation.user_id = :old.user_id;
    IF (NUMROWS > 0)
    THEN
      raise_application_error(
        -20001,
        'Cannot delete User because Reservation exists.'
      );
    END IF;


-- erwin Builtin Trigger
END;
/

CREATE  TRIGGER tU_User AFTER UPDATE ON User for each row
-- erwin Builtin Trigger
-- UPDATE trigger on User 
DECLARE NUMROWS INTEGER;
BEGIN
  /* erwin Builtin Trigger */
  /* User  Reservation on parent update restrict */
  /* ERWIN_RELATION:CHECKSUM="0000f984", PARENT_OWNER="", PARENT_TABLE="User"
    CHILD_OWNER="", CHILD_TABLE="Reservation"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_8", FK_COLUMNS="user_id" */
  IF
    /* %JoinPKPK(:%Old,:%New," <> "," OR ") */
    :old.user_id <> :new.user_id
  THEN
    SELECT count(*) INTO NUMROWS
      FROM Reservation
      WHERE
        /*  %JoinFKPK(Reservation,:%Old," = "," AND") */
        Reservation.user_id = :old.user_id;
    IF (NUMROWS > 0)
    THEN 
      raise_application_error(
        -20005,
        'Cannot update User because Reservation exists.'
      );
    END IF;
  END IF;


-- erwin Builtin Trigger
END;
/


CREATE  TRIGGER tI_Reservation BEFORE INSERT ON Reservation for each row
-- erwin Builtin Trigger
-- INSERT trigger on Reservation 
DECLARE NUMROWS INTEGER;
BEGIN
    /* erwin Builtin Trigger */
    /* User  Reservation on child insert restrict */
    /* ERWIN_RELATION:CHECKSUM="0001d204", PARENT_OWNER="", PARENT_TABLE="User"
    CHILD_OWNER="", CHILD_TABLE="Reservation"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_8", FK_COLUMNS="user_id" */
    SELECT count(*) INTO NUMROWS
      FROM User
      WHERE
        /* %JoinFKPK(:%New,User," = "," AND") */
        :new.user_id = User.user_id;
    IF (
      /* %NotnullFK(:%New," IS NOT NULL AND") */
      
      NUMROWS = 0
    )
    THEN
      raise_application_error(
        -20002,
        'Cannot insert Reservation because User does not exist.'
      );
    END IF;

    /* erwin Builtin Trigger */
    /* Game  Reservation on child insert restrict */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Game"
    CHILD_OWNER="", CHILD_TABLE="Reservation"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_6", FK_COLUMNS="game_id" */
    SELECT count(*) INTO NUMROWS
      FROM Game
      WHERE
        /* %JoinFKPK(:%New,Game," = "," AND") */
        :new.game_id = Game.game_id;
    IF (
      /* %NotnullFK(:%New," IS NOT NULL AND") */
      
      NUMROWS = 0
    )
    THEN
      raise_application_error(
        -20002,
        'Cannot insert Reservation because Game does not exist.'
      );
    END IF;


-- erwin Builtin Trigger
END;
/

CREATE  TRIGGER tU_Reservation AFTER UPDATE ON Reservation for each row
-- erwin Builtin Trigger
-- UPDATE trigger on Reservation 
DECLARE NUMROWS INTEGER;
BEGIN
  /* erwin Builtin Trigger */
  /* User  Reservation on child update restrict */
  /* ERWIN_RELATION:CHECKSUM="0001d91c", PARENT_OWNER="", PARENT_TABLE="User"
    CHILD_OWNER="", CHILD_TABLE="Reservation"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_8", FK_COLUMNS="user_id" */
  SELECT count(*) INTO NUMROWS
    FROM User
    WHERE
      /* %JoinFKPK(:%New,User," = "," AND") */
      :new.user_id = User.user_id;
  IF (
    /* %NotnullFK(:%New," IS NOT NULL AND") */
    
    NUMROWS = 0
  )
  THEN
    raise_application_error(
      -20007,
      'Cannot update Reservation because User does not exist.'
    );
  END IF;

  /* erwin Builtin Trigger */
  /* Game  Reservation on child update restrict */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Game"
    CHILD_OWNER="", CHILD_TABLE="Reservation"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_6", FK_COLUMNS="game_id" */
  SELECT count(*) INTO NUMROWS
    FROM Game
    WHERE
      /* %JoinFKPK(:%New,Game," = "," AND") */
      :new.game_id = Game.game_id;
  IF (
    /* %NotnullFK(:%New," IS NOT NULL AND") */
    
    NUMROWS = 0
  )
  THEN
    raise_application_error(
      -20007,
      'Cannot update Reservation because Game does not exist.'
    );
  END IF;


-- erwin Builtin Trigger
END;
/

