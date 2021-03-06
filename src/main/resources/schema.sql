CREATE TABLE BOOKING (
  BOOKING_ID    int NOT NULL auto_increment,
  CUSTOMER_ID   int NOT NULL, 
  ROOM_ID       int NOT NULL, 
  FROM_DATE     date NOT NULL, 
  UNTIL_DATE    date NOT NULL, 
  ROOM_PRICE    int NOT NULL, 
  SUMMARY_PRICE int NOT NULL,
  PRIMARY KEY (BOOKING_ID));
CREATE TABLE BOOKING_OPTION (
  OPTION_TYPE_ID int NOT NULL, 
  BOOKING_ID     int NOT NULL, 
  PRICE          int NOT NULL,
  PRIMARY KEY (OPTION_TYPE_ID, 
  BOOKING_ID));
CREATE TABLE CATEGORY (
  CATEGORY_ID int NOT NULL,
  DESCRIPTION varchar(255) NOT NULL, 
  PRIMARY KEY (CATEGORY_ID));
CREATE TABLE CUSTOMER (
  CUSTOMER_ID int NOT NULL auto_increment,
  NAME        varchar(255) NOT NULL, 
  PRIMARY KEY (CUSTOMER_ID));
CREATE TABLE OPTION_TYPE (
  OPTION_TYPE_ID int NOT NULL,
  DESCRIPTION    varchar(255) NOT NULL, 
  PRIMARY KEY (OPTION_TYPE_ID));
CREATE TABLE ROOM (
  ROOM_ID     int NOT NULL,
  CATEGORY_ID int NOT NULL, 
  NUMBER      int NOT NULL, 
  PRICE       int NOT NULL, 
  PRIMARY KEY (ROOM_ID));
CREATE TABLE ROOM_OPTION (
  OPTION_TYPE_ID int NOT NULL, 
  ROOM_ID        int NOT NULL, 
  PRICE          int NOT NULL,
  PRIMARY KEY (OPTION_TYPE_ID, 
  ROOM_ID));
ALTER TABLE BOOKING ADD CONSTRAINT FKBOOKING36416 FOREIGN KEY (ROOM_ID) REFERENCES ROOM (ROOM_ID);
ALTER TABLE ROOM ADD CONSTRAINT FKROOM730787 FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORY (CATEGORY_ID);
ALTER TABLE BOOKING_OPTION ADD CONSTRAINT FKBOOKING_OP397926 FOREIGN KEY (OPTION_TYPE_ID) REFERENCES OPTION_TYPE (OPTION_TYPE_ID);
ALTER TABLE BOOKING_OPTION ADD CONSTRAINT FKBOOKING_OP899898 FOREIGN KEY (BOOKING_ID) REFERENCES BOOKING (BOOKING_ID);
ALTER TABLE BOOKING ADD CONSTRAINT FKBOOKING637122 FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER (CUSTOMER_ID);
ALTER TABLE ROOM_OPTION ADD CONSTRAINT FKROOM_OPTIO738000 FOREIGN KEY (OPTION_TYPE_ID) REFERENCES OPTION_TYPE (OPTION_TYPE_ID);
ALTER TABLE ROOM_OPTION ADD CONSTRAINT FKROOM_OPTIO638092 FOREIGN KEY (ROOM_ID) REFERENCES ROOM (ROOM_ID);