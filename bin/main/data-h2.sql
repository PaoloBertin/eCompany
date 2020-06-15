--INSERT INTO users (username, password, enabled) VALUES ('paolo.bertin', 'admin', 1);
--INSERT INTO users (username, password, enabled) VALUES ('mario.rossi','user1',1);
--INSERT INTO users (username, password, enabled) VALUES ('giuseppe.verdi','user2',1);
--INSERT INTO users (username, password, enabled) VALUES ('giuseppe.garibaldi','user3',1);

--INSERT INTO authorities(username,authority) VALUES ('paolo.bertin','ROLE_ADMIN');
--INSERT INTO authorities(username,authority) VALUES ('mario.rossi','ROLE_USER');
--INSERT INTO authorities(username,authority) VALUES ('giuseppe.verdi','ROLE_USER');
--INSERT INTO authorities(username,authority) VALUES ('giuseppe.garibaldi','ROLE_USER');

-- Create the Groups
--INSERT INTO groups(group_name) values ('Users');
--INSERT INTO groups(group_name) values ('Administrators');

-- Map the Groups to Roles
--INSERT INTO group_authorities(group_id, authority) SELECT id,'ROLE_USER' FROM groups WHERE group_name='Users';
-- Administrators are both a ROLE_USER and ROLE_ADMIN
--INSERT INTO group_authorities(group_id, authority) SELECT id,'ROLE_USER' FROM groups WHERE group_name='Administrators';
--INSERT INTO group_authorities(group_id, authority) SELECT id,'ROLE_ADMIN' FROM groups WHERE group_name='Administrators';

-- Map the users to Groups
--INSERT INTO group_members(group_id, username) SELECT id,'paolo.bertin' FROM groups WHERE group_name='Administrators';
--INSERT INTO group_members(group_id, username) SELECT id,'mario.rossi' FROM groups WHERE group_name='Users';
--INSERT INTO group_members(group_id, username) SELECT id,'giuseppe.verdi' FROM groups WHERE group_name='Users';
--INSERT INTO group_members(group_id, username) SELECT id,'giuseppe.garibaldi' FROM groups WHERE group_name='Users';

INSERT INTO address(id, city, street, house_number, zip_code, country, state) VALUES(1, 'Battaglia Terme', 'Via G. Mazzini', '6', '35041', 'Padova', 'Italia');
INSERT INTO address(id, city, street, house_number, zip_code, country, state) VALUES(2, 'Battaglia Terme', 'Vicolo Pio X', '11', '35041', 'Padova', 'Italia');
INSERT INTO address(id, city, street, house_number, zip_code, country, state) VALUES(3, 'Battaglia Terme', 'Via A. Volta', '7', '35041', 'Padova', 'Italia');

INSERT INTO contacts(id, email, cellular, landline_phone) VALUES (1, 'paolo.bertin@dmail.com', '3338323285', '0429779334');
INSERT INTO contacts(id, email, cellular, landline_phone) VALUES (2, 'mario.rossi@dmail.com', '3458323285', '0497793348');
INSERT INTO contacts(id, email, cellular, landline_phone) VALUES (3, 'giuseppe.verdi@dmail.com', '3628323285', '0429776334');

INSERT INTO customers(customerid, firstname, lastname, username, password, email, address_id, contact_id) VALUES(1, 'Paolo', 'Bertin', 'paolo.bertin', 'admin', 'paolo.bertin@dmail.com', 1, 1);
INSERT INTO customers(customerid, firstname, lastname, username, password, email, address_id, contact_id) VALUES(2, 'Mario', 'Rossi', 'mario.rossi', 'user', 'mario.rossi@dmail.com', 2, 2);
INSERT INTO customers(customerid, firstname, lastname, username, password, email, address_id, contact_id) VALUES(3, 'Giuseppe', 'Verdi', 'giuseppe.verdi', 'user', 'giuseppe.verdi@dmail.com', 3, 3);

INSERT INTO role(id, name) VALUES (1, 'ROLE_USER');
INSERT INTO role(id, name) VALUES (2, 'ROLE_ADMIN');

-- User have one role
INSERT INTO customer_role(customerid, id) VALUES (2, 1);
INSERT INTO customer_role(customerid, id) VALUES (3, 1);

-- Admin has two roles
INSERT INTO customer_role(customerid, id) VALUES (1, 1);
INSERT INTO customer_role(customerid, id) VALUES (1, 2);

INSERT INTO categories(categoryid, name) VALUES(1, 'Books');
INSERT INTO categories(categoryid, name) VALUES(2, 'CD');
INSERT INTO categories(categoryid, name) VALUES(3, 'DVD');
INSERT INTO categories(categoryid, name) VALUES(4, 'Giochi');
INSERT INTO categories(categoryid, name) VALUES(5, 'Cartoleria');
INSERT INTO categories(categoryid, name) VALUES(6, 'Elettronica');

INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(1, 'Da Visual Basic a Java', 'Con Visual Basic oggi si possono produrre applicazioni allo stato dell''arte: event-driven, Internet-ready, con logica ad oggetti, o meglio a componenti. Tuttavia Visual Basic ha una limitazione evidente: funziona solo in ambienti Microsoft', '8883780450', 29.90, FILE_READ('classpath:static/images/cover/vb2java.jpg'),1);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(2, 'Resurrection', 'Even though it under performed at the box office last Christmas, I really enjoyed "Star Trek: Insurrection." Maybe it was the title. Maybe it was the time of year.', '8883780451', 39.90, FILE_READ('classpath:static/images/cover/resurrection.jpg'), 2);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(3, 'Java Web Services', 'Java web services" consente allo sviluppatore Java di entrare nel mondo del Web Services.', '1449365116', 29.90, FILE_READ('classpath:static/images/cover/jws.jpg'), 1);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(4, 'Enterprise JavaBeans', 'Enterprise JavaBeans e'' la guida definitiva a EJB 2.0, completa dei piu'' recenti sviluppi tecnologici.', '0596158025', 49.90, FILE_READ('classpath:static/images/cover/ejb.jpg'), 1);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(5, 'Java Server Pages', 'Gli sviluppatori hanno compreso il potenziale di Enterprise Java per la costruzione di applicazioni distribuite fin dal 1999.', '0596158035', 49.90, FILE_READ('classpath:static/images/cover/jsp.jpg'), 1);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(6, 'When dream and day unite', 'Il primo album dei Dream Theater', '0596158045', 19.90, FILE_READ('classpath:static/images/cover/When_dream_and_day_unite.jpg'), 2);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(7, 'Images and world', 'Il secondo album dei Dream Theater', '0596158055', 19.90, FILE_READ('classpath:static/images/cover/Images_and_Words.jpg'), 2);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(8, 'Awake', 'Il terzo album dei Dream Theater', '0596158065', 19.90, FILE_READ('classpath:static/images/cover/Awake.jpg'), 2);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(9, 'A Change of season', 'Il quarto album dei Dream Theater', '0596158075', 19.90, FILE_READ('classpath:static/images/cover/A_Change_of_ Seasons.jpg'), 2);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(10, 'Falling into infinity', 'Il quinto album dei Dream Theater', '0596158085', 19.90, FILE_READ('classpath:static/images/cover/Falling_into_Infinity.jpg'), 2);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(11, 'Live in Marquee', 'Il sesto album dei Dream Theater', '0596158095', 19.90, FILE_READ('classpath:static/images/cover/Live_at_the_Marquee.jpg'), 2);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(12, 'Scenes from a memory', 'Il settimo album dei Dream Theater', '0596158105', 19.90, FILE_READ('classpath:static/images/cover/Scenes_from_a_Memory.jpg'), 2);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(13, 'Solo. A Star Wars Story', 'Tempi duri per la Galassia, forze oscure tramano nell''ombra e minacciano la Repubblica.', '0596158125', 15.90, FILE_READ('classpath:static/images/cover/Solo_A_Star_Wars_Story.jpg'), 3);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(14, 'Ready Player One', 'Nel 2045, il mondo reale e'' un luogo impervio e ostile. Gli unici momenti in cui Wade Watts si sente veramente vivo e'' quando si immerge in OASIS, un intero universo virtuale dove evade la maggior parte dell''umanita'' per trascorre le proprie giornate.', '0596158135', 15.90, FILE_READ('classpath:static/images/cover/ready-player-one.jpg'), 3);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(15, 'L''isola dei cani', 'Un ragazzo parte alla ricerca del suo cane, esiliato per via di un''influenza canina. Insieme, proveranno a sovvertire le regole del sistema', '0596158145', 16.90, FILE_READ('classpath:static/images/cover/isola-dei-cani.jpg'), 3);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(16, 'Etica del discorso', 'Se l''esigenza di capire e interpretare la vita sociale sta oggi al centro ella filosofia e delle scienze umane, in questo libro Habermas offre concrete indicazioni', '0596158155', 16.15, FILE_READ('classpath:static/images/cover/etica-del-discorso.jpg'), 1);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(17, 'Stime dei lavori edili.', 'Milano, Hoepli, 1917, 16mo legatura editoriale originale, pp. XII-325-64. Prima edizione.', '0596158165', 25.00, FILE_READ('classpath:static/images/cover/stima-dei-lavori-edili.jpg'), 1);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(18, 'Manuale di Java 9', 'Il Manuale di Java 9 e'' stato strutturato per soddisfare le aspettative di aspiranti programmatori: nulla e'' dato per scontato, e'' possibile imparare a programmare partendo da zero', '9788820383022', 42.00, FILE_READ('classpath:static/images/cover/manuale-java9.jpg'), 1);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(19, 'Dai fondamenti agli oggetti. Corso di programmazione Java.', 'Questo testo e'' un vero e proprio corso di programmazione accessibile anche a chi non abbia alcuna conoscenza della materia, e nel contempo tocca e approfondisce le tematiche e gli aspetti fondamentali della programmazione. ', '9788865188996', 33.15, FILE_READ('classpath:static/images/cover/dai-fondamenti-agli-oggetti.jpg'), 1);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(20, 'Applicare UML e i pattern. Analisi e progettazione orientata agli oggetti.', 'Scritto per la prima volta nell''edizione originale nel 1995, questo testo negli anni e'' stato tradotto in piu'' di 20 lingue.', '9788891901033', 33.15, FILE_READ('classpath:static/images/cover/applicare-uml-e-i-pattern.jpg'), 1);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(21, 'Ba Martina Spaghettina', 'Martina si e'' appena svegliata: una rapida pettinata con la spazzola e ha subito fame. Se le accarezzi il pancino lei ti rispondero'' e ti faro'' capire quanto desidera la sua pappa.', '5010993438136', 43, FILE_READ('classpath:static/images/cover/ba-martina-spaghettina.jpg'), 4);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(22, 'Agenda giornaliera 2019', 'L''agenda giornaliera Moleskine copre tutto l''anno, da gennaio a dicembre.', '8058341715642', 19, FILE_READ('classpath:static/images/cover/agenda-giornaliera-2020.jpg'), 5);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(23, 'Cuffie iTek Taurus H300', 'iTek TAURUS H300 sono cuffie leggere, regolabili, e con materiale soffice in simil-pelle per dare il massimo comfort nell''utilizzo anche di lunga durata', '8032539913843', 24, FILE_READ('classpath:static/images/cover/cuffie-iTek-Taurus-H300.jpg'), 6);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(24, 'Intelligenza artificiale - Vol. 1', 'Un classico nell''ambito della letteratura sull''intelligenza artificiale, apprezzato per la sua presentazione equilibrata e precisa e per l''ampiezza e l''approfondimento dei contenuti.', '9788871925936', 40, FILE_READ('classpath:static/images/cover/9788871925936.jpg'), 1);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(25, 'Fondamenti di controlli automatici', 'Questo libro presenta gli elementi di base della teoria dei sistemi e del controllo.', '9788838668821', 49, FILE_READ('classpath:static/images/cover/9788838668821.jpg'), 1);


INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES( 1, 1, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES( 2, 1, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES( 3, 1, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES( 4, 1, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES( 5, 1, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES( 6, 1, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES( 7, 1, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES( 8, 1, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES( 9, 1, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(10, 1, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(11, 1, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(12, 1, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(13, 1, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(14, 1, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(15, 1, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(16, 1, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(17, 1, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(18, 1, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(19, 1, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(20, 1, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(21, 1, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(22, 1, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(23, 1, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');

INSERT INTO movement(movementid, datemovement, totalamount, state, customerid) VALUES( 1, '2018-10-10 00:00:00', 169.50, 'processato', 2);
INSERT INTO movement(movementid, datemovement, totalamount, state, customerid) VALUES( 2, '2018-10-10 00:00:00',  49.90, 'processato', 2);
INSERT INTO movement(movementid, datemovement, totalamount, state, customerid) VALUES( 3, '2018-10-10 00:00:00',  59.70, 'processato', 2);
INSERT INTO movement(movementid, datemovement, totalamount, state, customerid) VALUES( 4, '2018-10-10 00:00:00',  74.90, 'processato', 2);
INSERT INTO movement(movementid, datemovement, totalamount, state, customerid) VALUES( 5, '2018-12-08 00:00:00',  50.00, 'processato', 1);
INSERT INTO movement(movementid, datemovement, totalamount, state, customerid) VALUES( 6, '2018-10-10 00:00:00', 100.00, 'processato', 1);
INSERT INTO movement(movementid, datemovement, totalamount, state, customerid) VALUES( 7, '2018-10-10 00:00:00', 150.00, 'processato', 1);
INSERT INTO movement(movementid, datemovement, totalamount, state, customerid) VALUES( 8, '2018-10-10 00:00:00',  50.00, 'processato', 1);
INSERT INTO movement(movementid, datemovement, totalamount, state, customerid) VALUES( 9, '2018-10-10 00:00:00',  50.00, 'processato', 3);
INSERT INTO movement(movementid, datemovement, totalamount, state, customerid) VALUES(10, '2018-12-08 00:00:00',  50.00, 'processato', 3);

INSERT INTO lineitem(lineitemid, productid, quantity) VALUES(1,   1, 1);
INSERT INTO lineitem(lineitemid, productid, quantity) VALUES(2,   2, 2);
INSERT INTO lineitem(lineitemid, productid, quantity) VALUES(3,   3, 2);
INSERT INTO lineitem(lineitemid, productid, quantity) VALUES(4,   5, 1);
INSERT INTO lineitem(lineitemid, productid, quantity) VALUES(5,  10, 3);
INSERT INTO lineitem(lineitemid, productid, quantity) VALUES(6,  17, 1);
INSERT INTO lineitem(lineitemid, productid, quantity) VALUES(7,   4, 1);
INSERT INTO lineitem(lineitemid, productid, quantity) VALUES(8,   7, 2);
INSERT INTO lineitem(lineitemid, productid, quantity) VALUES(9,   8, 1);
INSERT INTO lineitem(lineitemid, productid, quantity) VALUES(10, 13, 4);
INSERT INTO lineitem(lineitemid, productid, quantity) VALUES(11,  6, 1);
INSERT INTO lineitem(lineitemid, productid, quantity) VALUES(12,  9, 1);
INSERT INTO lineitem(lineitemid, productid, quantity) VALUES(13, 11, 1);
INSERT INTO lineitem(lineitemid, productid, quantity) VALUES(14, 14, 1);
INSERT INTO lineitem(lineitemid, productid, quantity) VALUES(15, 12, 1);
INSERT INTO lineitem(lineitemid, productid, quantity) VALUES(16, 16, 1);
INSERT INTO lineitem(lineitemid, productid, quantity) VALUES(17, 15, 1);
INSERT INTO lineitem(lineitemid, productid, quantity) VALUES(18, 20, 1);
INSERT INTO lineitem(lineitemid, productid, quantity) VALUES(19, 21, 1);
INSERT INTO lineitem(lineitemid, productid, quantity) VALUES(20, 22, 1);

INSERT INTO movement_lineitems(Movement_movementid, lineitems_lineitemid) VALUES(1, 1);
INSERT INTO movement_lineitems(Movement_movementid, lineitems_lineitemid) VALUES(1, 2);
INSERT INTO movement_lineitems(Movement_movementid, lineitems_lineitemid) VALUES(1, 3);
INSERT INTO movement_lineitems(Movement_movementid, lineitems_lineitemid) VALUES(2, 4);
INSERT INTO movement_lineitems(Movement_movementid, lineitems_lineitemid) VALUES(3, 5);
INSERT INTO movement_lineitems(Movement_movementid, lineitems_lineitemid) VALUES(4, 6);
INSERT INTO movement_lineitems(Movement_movementid, lineitems_lineitemid) VALUES(4, 7);
INSERT INTO movement_lineitems(Movement_movementid, lineitems_lineitemid) VALUES(5, 8);
INSERT INTO movement_lineitems(Movement_movementid, lineitems_lineitemid) VALUES(5, 9);
INSERT INTO movement_lineitems(Movement_movementid, lineitems_lineitemid) VALUES(5, 10);
INSERT INTO movement_lineitems(Movement_movementid, lineitems_lineitemid) VALUES(6, 11);
INSERT INTO movement_lineitems(Movement_movementid, lineitems_lineitemid) VALUES(6, 12);
INSERT INTO movement_lineitems(Movement_movementid, lineitems_lineitemid) VALUES(7, 13);
INSERT INTO movement_lineitems(Movement_movementid, lineitems_lineitemid) VALUES(8, 14);
INSERT INTO movement_lineitems(Movement_movementid, lineitems_lineitemid) VALUES(8, 15);
INSERT INTO movement_lineitems(Movement_movementid, lineitems_lineitemid) VALUES(9, 16);
INSERT INTO movement_lineitems(Movement_movementid, lineitems_lineitemid) VALUES(9, 17);
INSERT INTO movement_lineitems(Movement_movementid, lineitems_lineitemid) VALUES(9, 18);
INSERT INTO movement_lineitems(Movement_movementid, lineitems_lineitemid) VALUES(9, 19);
INSERT INTO movement_lineitems(Movement_movementid, lineitems_lineitemid) VALUES(10, 20);
