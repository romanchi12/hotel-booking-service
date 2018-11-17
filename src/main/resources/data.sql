insert into CATEGORY(CATEGORY_ID,DESCRIPTION) values (1,'Single room');
insert into CATEGORY(CATEGORY_ID,DESCRIPTION) values (2,'Double room');
insert into CATEGORY(CATEGORY_ID,DESCRIPTION) values (3,'Quadriple room');

insert into BOOKING_OPTION(DESCRIPTION, PRICE) values ('Breakfast', 100);
insert into BOOKING_OPTION(DESCRIPTION, PRICE) values ('Parking', 50);
insert into BOOKING_OPTION(DESCRIPTION, PRICE) values ('SPA', 150);

insert into ROOM(CATEGORY_ID,NUMBER,PRICE) values (1, 101, 200);
insert into ROOM(CATEGORY_ID,NUMBER,PRICE) values (1, 102, 200);
insert into ROOM(CATEGORY_ID,NUMBER,PRICE) values (1, 103, 200);
insert into ROOM(CATEGORY_ID,NUMBER,PRICE) values (1, 104, 300);
insert into ROOM(CATEGORY_ID,NUMBER,PRICE) values (1, 105, 300);
insert into ROOM(CATEGORY_ID,NUMBER,PRICE) values (1, 106, 500);
insert into ROOM(CATEGORY_ID,NUMBER,PRICE) values (2, 201, 200);
insert into ROOM(CATEGORY_ID,NUMBER,PRICE) values (2, 202, 200);
insert into ROOM(CATEGORY_ID,NUMBER,PRICE) values (2, 203, 300);
insert into ROOM(CATEGORY_ID,NUMBER,PRICE) values (2, 204, 500);
insert into ROOM(CATEGORY_ID,NUMBER,PRICE) values (3, 205, 500);
insert into ROOM(CATEGORY_ID,NUMBER,PRICE) values (3, 206, 500);

-- for testing

insert into CLIENT(USERNAME) values ('Mykola');

insert into BOOKING(USER_ID,ROOM_ID,FROM_DATE,UNTIL_DATE,PRICE) values (1, 4, '2018-11-01', '2018-11-03', 400);