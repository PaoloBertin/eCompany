-- admin.ecompany     password = admin
-- user.ecompany      password = user
-- mario.rossi        password = user
-- giuseppe.verdi     password = user
-- giuseppe.garibaldi password = user
INSERT INTO users (username, password, enabled) VALUES ('admin.ecompany', '$2a$10$xQm0oqzToTzplW8Xn9vEouNcV7uIFUzVLuFIZm3txt4WTaD.iTCW2', 1);
INSERT INTO users (username, password, enabled) VALUES ('user.ecompany',  '$2a$10$/P0J7Qo9eAexCmG6bQdM5uQVXNyZ7yutm8sc6qCP2IJj1ZSppTgu.', 1);
INSERT INTO users (username, password, enabled) VALUES ('mario.rossi','$2a$10$AN9KMoBs3GLy9ttjE4jsGODh25qA5xVo1IfX3kyHdFaC5JP2eJ45C',1);
INSERT INTO users (username, password, enabled) VALUES ('giuseppe.verdi','$2a$10$6ougvztJT0if6CTPXPytQuFESws6sq4VEmxkWkg3shNUU/CSbJZ6y',1);
INSERT INTO users (username, password, enabled) VALUES ('giuseppe.garibaldi','$2a$10$rYz10KiusKJ0EQ14b6zyVeVb1fxq617nAZOLE0HHpHtb8VZwGHusG',0);

INSERT INTO authorities(username,authority) VALUES ('admin.ecompany','ROLE_ADMIN');
INSERT INTO authorities(username,authority) VALUES ('user.ecompany','ROLE_USER');
INSERT INTO authorities(username,authority) VALUES ('mario.rossi','ROLE_USER');
INSERT INTO authorities(username,authority) VALUES ('giuseppe.verdi','ROLE_USER');
INSERT INTO authorities(username,authority) VALUES ('giuseppe.garibaldi','ROLE_USER');

-- Create the Groups
INSERT INTO groups(group_name) values ('Users');
INSERT INTO groups(group_name) values ('Administrators');

-- Map the Groups to Roles
INSERT INTO group_authorities(group_id, authority) SELECT id,'ROLE_USER' FROM groups WHERE group_name='Users';
-- Administrators are both a ROLE_USER and ROLE_ADMIN
INSERT INTO group_authorities(group_id, authority) SELECT id,'ROLE_USER' FROM groups WHERE group_name='Administrators';
INSERT INTO group_authorities(group_id, authority) SELECT id,'ROLE_ADMIN' FROM groups WHERE group_name='Administrators';

-- Map the users to Groups
INSERT INTO group_members(group_id, username) SELECT id,'admin.ecompany' FROM groups WHERE group_name='Administrators';
INSERT INTO group_members(group_id, username) SELECT id,'user.ecompany' FROM groups WHERE group_name='Users';
INSERT INTO group_members(group_id, username) SELECT id,'mario.rossi' FROM groups WHERE group_name='Users';
INSERT INTO group_members(group_id, username) SELECT id,'giuseppe.verdi' FROM groups WHERE group_name='Users';
INSERT INTO group_members(group_id, username) SELECT id,'giuseppe.garibaldi' FROM groups WHERE group_name='Users';

INSERT INTO address(id, city, street, house_number, zip_code, country, state) VALUES(1, 'Battaglia Terme', 'Via A. Ecompany', '8', '35041', 'Padova', 'Italia');
INSERT INTO address(id, city, street, house_number, zip_code, country, state) VALUES(2, 'Battaglia Terme', 'Via U. Ecompany', '7', '35041', 'Padova', 'Italia');
INSERT INTO address(id, city, street, house_number, zip_code, country, state) VALUES(3, 'Battaglia Terme', 'Vicolo Pio X', '11', '35041', 'Padova', 'Italia');
INSERT INTO address(id, city, street, house_number, zip_code, country, state) VALUES(4, 'Battaglia Terme', 'Via A. Volta', '7', '35041', 'Padova', 'Italia');
INSERT INTO address(id, city, street, house_number, zip_code, country, state) VALUES(5, 'Battaglia Terme', 'Via G. Garibaldi', '21', '35041', 'Padova', 'Italia');

INSERT INTO contacts(id, email, cellular, landline_phone) VALUES (1, 'admin.ecompany@dmail.com', '3628323289', '0429776337');
INSERT INTO contacts(id, email, cellular, landline_phone) VALUES (2, 'user.ecompany@dmail.com', '3628323299', '0429776339');
INSERT INTO contacts(id, email, cellular, landline_phone) VALUES (3, 'mario.rossi@dmail.com', '3458323286', '0497793348');
INSERT INTO contacts(id, email, cellular, landline_phone) VALUES (4, 'giuseppe.verdi@dmail.com', '3628323287', '0429776335');
INSERT INTO contacts(id, email, cellular, landline_phone) VALUES (5, 'giuseppe.garibaldi@dmail.com', '3628323288', '0429776336');

INSERT INTO customers(customerid, firstname, lastname, username, password, email, address_id, contact_id) VALUES(1, 'Admin', 'Ecompany', 'admin.ecompany', '$2a$10$xQm0oqzToTzplW8Xn9vEouNcV7uIFUzVLuFIZm3txt4WTaD.iTCW2', 'admin.ecompany@dmail.com', 1, 1);
INSERT INTO customers(customerid, firstname, lastname, username, password, email, address_id, contact_id) VALUES(2, 'User', 'Ecompany', 'user.ecompany', '$2a$10$/P0J7Qo9eAexCmG6bQdM5uQVXNyZ7yutm8sc6qCP2IJj1ZSppTgu.', 'user.ecompany@dmail.com', 2, 2);
INSERT INTO customers(customerid, firstname, lastname, username, password, email, address_id, contact_id) VALUES(3, 'Mario', 'Rossi', 'mario.rossi', '$2a$10$AN9KMoBs3GLy9ttjE4jsGODh25qA5xVo1IfX3kyHdFaC5JP2eJ45C', 'mario.rossi@dmail.com', 3, 3);
INSERT INTO customers(customerid, firstname, lastname, username, password, email, address_id, contact_id) VALUES(4, 'Giuseppe', 'Verdi', 'giuseppe.verdi', '$2a$10$6ougvztJT0if6CTPXPytQuFESws6sq4VEmxkWkg3shNUU/CSbJZ6y', 'giuseppe.verdi@dmail.com', 4, 4);
INSERT INTO customers(customerid, firstname, lastname, username, password, email, address_id, contact_id) VALUES(5, 'Giuseppe', 'Garibaldi', 'giuseppe.garibaldi', '$2a$10$rYz10KiusKJ0EQ14b6zyVeVb1fxq617nAZOLE0HHpHtb8VZwGHusG', 'giuseppe.garibaldi@dmail.com', 5, 5);

INSERT INTO role(id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO role(id, name) VALUES (2, 'ROLE_USER');

-- User have one role
INSERT INTO customer_role(customerid, id) VALUES (1, 1);
INSERT INTO customer_role(customerid, id) VALUES (2, 2);

-- Admin has two roles
INSERT INTO customer_role(customerid, id) VALUES (1, 1);
INSERT INTO customer_role(customerid, id) VALUES (1, 2);

INSERT INTO categories(categoryid, name, version) VALUES( 1, 'Libri', 0);
INSERT INTO categories(categoryid, name, version) VALUES( 2, 'CD', 0);
INSERT INTO categories(categoryid, name, version) VALUES( 3, 'DVD', 0);
INSERT INTO categories(categoryid, name, version) VALUES( 4, 'Giochi', 0);
INSERT INTO categories(categoryid, name, version) VALUES( 5, 'Cartoleria', 0);
INSERT INTO categories(categoryid, name, version) VALUES( 6, 'Elettronica', 0);

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
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(26, 'Microeconomia', 'La sesta edizione italiana, basata su Frank Cartwright Global Edition, presenta diverse interessanti novità di rilievo rispetto alla precedente.', '9788838693243', 52, FILE_READ('classpath:static/images/cover/9788838693243.jpg'), 1);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(27, 'Statistica e calcolo con R', 'Questo libro è il risultato dell''esperienza di diversi anni di insegnamento, presso l''Università di Bologna, in corsi di introduzione all''informatica e di programmazione per il calcolo numerico (AM) e di statistica (ELP).', '9788838693984', 29, FILE_READ('classpath:static/images/cover/9788838693984.jpg'), 1);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(28, 'La matematica degli dèi e gli algoritmi degli uomini', 'I numeri sono un''invenzione della mente o una scoperta con cui la mente accerta l''esistenza di qualcosa che è nel mondo?', '9788845978289', 7.99, FILE_READ('classpath:static/images/cover/9788845978289.jpg'), 1);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(29, 'Caos', 'Dare senso al mondo, alle esperienze quotidiane e, ancor più, agli eventi che ci sorprendono o dai quali può dipendere la nostra sopravvivenza', '9788815358776', 10.99, FILE_READ('classpath:static/images/cover/9788815358776.jpg'), 1);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(30, 'Lezioni di matematica attuariale delle assicurazioni danni', 'Lezioni di matematica attuariale delle assicurazioni danni', '9788867807048', 10.99, FILE_READ('classpath:static/images/cover/9788867807048.jpg'), 1);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(31, 'Logica matematica', 'In questo libro sono presentate tutte le sfaccettature della logica matematica', '9781523624935', 1.99, FILE_READ('classpath:static/images/cover/9781523624935.jpg'), 1);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(32, 'I teoremi di incompletezza', 'Nel 1930 i teoremi di incompletezza di Kurt Gödel cambiarono il corso della filosofia della scienza.', '9788815352101', 8.49, FILE_READ('classpath:static/images/cover/9788815352101.jpg'), 1);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(33, 'Dalla macchina di Turing a P/NP', 'Dalla macchina di Turing a P/NP', '9788838691041', 12.00, FILE_READ('classpath:static/images/cover/9788838691041.jpg'), 1);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(34, 'L''enigma di Turing. Genesi e apologia di un genio matematico', 'Se volessimo trovare un esempio concreto di autentica vita vissuta all’insegna dell’art pour l’art, motto dei simbolisti e decadentisti del XIX secolo, Turing sarebbe indubbiamente un caso paradigmatico', '9788857554655', 12.99, FILE_READ('classpath:static/images/cover/9788857554655.jpg'), 1);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(35, 'Oggetti concorrenza distribuzione.', 'Questo testo raccoglie le note del corso di programmazione concorrente e distribuita', '9788874888030', 24.70, FILE_READ('classpath:static/images/cover/9788874888030.jpg'), 1);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(36, 'Accetto miracoli', 'Accetto Miracoli è con ogni probabilità, il disco più atteso di questo Natale 2019', '0602508304392', 24.70, FILE_READ('classpath:static/images/cover/0602508304392.jpg'), 2);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(37, 'Colpa delle favole', 'Dopo aver pubblicato “I TUOI PARTICOLARI”, singolo già disco di platino con cui ha partecipato alla sessantanovesima edizione del Festival di Sanremo, ULTIMO anticipa ai suoi fan questo nuovo inedito', '3615936866780', 15.75, FILE_READ('classpath:static/images/cover/3615936866780.jpg'), 2);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(38, 'Mina Fossati', 'Mina e Fossati insieme, per un nuovo album di inediti.', '0194397022421', 26.50, FILE_READ('classpath:static/images/cover/0194397022421.jpg'), 2);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(39, 'Mission', 'Nel cuore del continente sud americano, alla metà del XVIII secolo, una comunità di indios capeggiata da alcuni gesuiti cerca di opporsi alle mire espansionistiche di spagnoli e portoghesi.', '5051891126862', 9.99, FILE_READ('classpath:static/images/cover/5051891126862.jpg'), 3);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(40, 'Maraviglioso Boccaccio', '1348: a Firenze infuria la peste, mietendo sempre più vittime. Un gruppo di 10 giovani cerca rifugio in una casa di campagna, per tentare di sfuggire al contagio.', '8057092004869', 9.99, FILE_READ('classpath:static/images/cover/8057092004869.jpg'), 3);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(41, 'Elizabeth', 'Elisabetta I per difendere il suo regno deve affrontare diversi ostacoli.', '5050582518832', 9.99, FILE_READ('classpath:static/images/cover/5050582518832.jpg'), 3);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(42, 'Kagemusha', 'Il principe Shingen, che sogna di riunificare il Giappone nel Cinquecento, ha al suo seguito un sosia grazie al quale riesce a mantenere segreta la morte del Re per ingannare il nemico e salvaguardare il regno', '8010312035869', 9.99, FILE_READ('classpath:static/images/cover/8010312035869.jpg'), 3);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(43, 'Le crociate', 'Balian, un giovane fabbro francese, disperato per la perdita della moglie e del figlio ancora piccolo, decide di seguire nella sua sacra missione colui che dice di essere il suo vero padre', '5051891084803', 9.99, FILE_READ('classpath:static/images/cover/5051891084803.jpg'), 3);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(44, 'Alexander', 'La storia del conquistatore più famoso della storia, Alessandro, un uomo che a 27 anni riesce a impadronirsi quasi per intero del mondo allora conosciuto', '7321958389362', 9.99, FILE_READ('classpath:static/images/cover/7321958389362.jpg'), 3);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(45, 'Robin Hood', 'In un''epoca di oppressione e feroce tirannia, un fuorilegge divenne l''eroe che salvò una nazione e ispirò generazioni a combattere per la libertà', '5053083017149', 9.99, FILE_READ('classpath:static/images/cover/5053083017149.jpg'), 3);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(46, 'Il gladiatore', 'Maximus è il generale romano prediletto da Marco Aurelio, ma proprio per questo odiato da Commodo, figlio dell''imperatore.', '5053083156510', 9.99, FILE_READ('classpath:static/images/cover/5053083156510.jpg'), 3);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(47, 'The Eagle', 'Nel 140 d.C. l''Impero Romano si estende fino alla Britannia, anche se non ha il controllo di tutte le regioni e l''estremo nord è governato dalle tribù ribelli della Caledonia.', '8032807067841', 9.99, FILE_READ('classpath:static/images/cover/8032807067841.jpg'), 3);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(48, 'Cyber Flipper', 'Flipper elettronico con luci e suoni', '8001097539673', 45.99, FILE_READ('classpath:static/images/cover/8001097539673.jpg'), 4);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(49, 'Monopoly', 'Monopoly, il gioco di contrattazione più famoso del mondo.', '5010993414314', 25.00, FILE_READ('classpath:static/images/cover/5010993414314.jpg'), 4);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(50, 'Poker Romano', 'I giochi di dadi erano molto popolari nell&rsquoAntica Roma. Dagli schiavi all''Imperatore, tutti vi giocavano.', '8058773201294', 25.00, FILE_READ('classpath:static/images/cover/8058773201294.jpg'), 4);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(51, 'Scacchi Dama Tria', 'Game Set Scacchi e Dama Tria 36 x 36 CmContenitore in legno da 36 x 36 cm con scomparti. Scacchiera intarsiata, bifacciale. Pedine e scacchi in legno (altezza Re 65 mm), con regole di gioco', ' 8001097539093', 29.50, FILE_READ('classpath:static/images/cover/8001097539093.jpg'), 4);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(52, 'Mazzo 52 Carte da Gioco Trevigiane', '52 Carte da Gioco TrevigianeMazzo da 52 carte Triestine. Solide e leggere, dalla stampa professionale e dal design unico e inconfondibile costruite interamente in plastica.', ' 8020320036222', 12.00, FILE_READ('classpath:static/images/cover/8020320036222.jpg'), 4);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(53, 'Carte Da Gioco Ramino Italia', 'L''astuccio Ramino Italia Dal Negro include due mazzi da poker in cartoncino.', '8001097210435', 12.00, FILE_READ('classpath:static/images/cover/8001097210435.jpg'), 4);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(54, 'Apple iPhone 11', 'Un nuovo sistema a doppia fotocamera, per inquadrare più cose intorno a te.', '0190199221130', 796, FILE_READ('classpath:static/images/cover/0190199221130.jpg'), 6);

INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES( 1,   'magazzino01',    1,    '8883780450',     20, 'pz', 10, 5,   25, false, 'T001', 'A0001');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES( 2,   'magazzino01',    2,    '8883780451',   26.5, 'pz', 10, 5, 26.5, false, 'T002', 'B0001');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES( 3,   'magazzino01',    3,    '1449365116',     20, 'pz', 10, 5,   20, false, 'T001', 'A0002');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES( 4,   'magazzino01',    4,    '0596158025',     35, 'pz', 10, 5,   35, false, 'T001', 'A0003');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES( 5,   'magazzino01',    5,    '0596158035',     35, 'pz', 10, 5,   35, false, 'T001', 'A0004');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES( 6,   'magazzino01',    6,    '0596158045',     15, 'pz', 10, 5,   15, false, 'T002', 'B0002');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES( 7,   'magazzino01',    7,    '0596158055',     15, 'pz', 10, 5,   15, false, 'T002', 'B0003');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES( 8,   'magazzino01',    8,    '0596158065',     15, 'pz', 10, 5,   15, false, 'T002', 'B0004');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES( 9,   'magazzino01',    9,    '0596158075',     15, 'pz', 10, 5,   15, false, 'T002', 'B0005');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(10,   'magazzino01',   10,    '0596158085',     15, 'pz', 10, 5,   15, false, 'T002', 'B0006');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(11,   'magazzino01',   11,    '0596158095',     15, 'pz', 10, 5,   15, false, 'T002', 'B0007');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(12,   'magazzino01',   12,    '0596158105',     15, 'pz', 10, 5,   15, false, 'T002', 'B0008');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(13,   'magazzino01',   13,    '0596158125',     10, 'pz', 10, 5,   10, false, 'T003', 'C0001');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(14,   'magazzino01',   14,    '0596158135',     11, 'pz', 10, 5,   11, false, 'T003', 'C0002');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(15,   'magazzino01',   15,    '0596158145',     20, 'pz', 10, 5,   20, false, 'T003', 'C0003');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(16,   'magazzino01',   16,    '0596158155',     20, 'pz', 10, 5,   20, false, 'T001', 'A0005');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(17,   'magazzino01',   17,    '0596158165',     17, 'pz', 10, 5, 11.5, false, 'T001', 'A0006');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(18,   'magazzino01',   18, '9788820383022',     28, 'pz', 10, 5,   28, false, 'T001', 'A0007');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(19,   'magazzino01',   19, '9788865188996',     20, 'pz', 10, 5,   20, false, 'T001', 'A0008');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(20,   'magazzino01',   20, '9788891901033',     22, 'pz', 10, 5,   22, false, 'T001', 'A0009');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(21,   'magazzino01',   21, '5010993438136',     28, 'pz', 10, 5,   28, false, 'T004', 'D0001');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(22,   'magazzino01',   22, '8058341715642',   12.5, 'pz', 10, 5, 12.5, false, 'T005', 'E0001');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(23,   'magazzino01',   23, '8032539913843',     16, 'pz', 10, 5,   16, false, 'T006', 'F0001');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(24,   'magazzino01',   24, '9788871925936',   26.5, 'pz', 10, 5, 26.5, false, 'T001', 'A0010');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(25,   'magazzino01',   25, '9788838668821',     32, 'pz', 10, 5,   32, false, 'T001', 'A0011');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(26,   'magazzino01',   26, '9788838693243',     35, 'pz', 10, 5,   35, false, 'T001', 'A0012');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(27,   'magazzino01',   27, '9788838693984',     20, 'pz', 10, 5,   20, false, 'T001', 'A0013');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(28,   'magazzino01',   28, '9788845978289',      5, 'pz', 10, 5,    5, false, 'T001', 'A0014');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(29,   'magazzino01',   29, '9788815358776',     11, 'pz', 10, 5,   11, false, 'T001', 'A0015');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(30,   'magazzino01',   30, '9788867807048',     11, 'pz', 10, 5,   11, false, 'T001', 'A0016');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(31,   'magazzino01',   31, '9781523624935',      2, 'pz', 10, 5,    2, false, 'T001', 'A0017');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(32,   'magazzino01',   32, '9788815352101',      8, 'pz', 10, 5,    8, false, 'T001', 'A0018');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(33,   'magazzino01',   33, '9788838691041',      8, 'pz', 10, 5,    8, false, 'T001', 'A0019');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(34,   'magazzino01',   34, '9788857554655',     13, 'pz', 10, 5,   13, false, 'T001', 'A0020');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(35,   'magazzino01',   35, '9788874888030',     17, 'pz', 10, 5,   17, false, 'T001', 'A0021');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(36,   'magazzino01',   36, '0602508304392',     17, 'pz', 10, 5,   17, false, 'T002', 'B0009');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(37,   'magazzino01',   37, '3615936866780',     10, 'pz', 10, 5,   10, false, 'T002', 'B0010');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(38,   'magazzino01',   38, '0194397022421',     17, 'pz', 10, 5,   17, false, 'T002', 'B0011');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(39,   'magazzino01',   39, '5051891126862',      7, 'pz', 10, 5,    7, false, 'T003', 'C0004');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(40,   'magazzino01',   40, '8057092004869',      7, 'pz', 10, 5,    7, false, 'T003', 'C0005');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(41,   'magazzino01',   41, '5050582518832',      7, 'pz', 10, 5,    7, false, 'T003', 'C0006');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(42,   'magazzino01',   42, '8010312035869',      7, 'pz', 10, 5,    7, false, 'T003', 'C0007');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(43,   'magazzino01',   43, '5051891084803',      7, 'pz', 10, 5,    7, false, 'T003', 'C0008');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(44,   'magazzino01',   44, '7321958389362',      7, 'pz', 10, 5,    7, false, 'T003', 'C0009');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(45,   'magazzino01',   45, '5053083017149',      7, 'pz', 10, 5,    7, false, 'T003', 'C0010');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(46,   'magazzino01',   46, '5053083156510',      7, 'pz', 10, 5,    7, false, 'T003', 'C0011');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(47,   'magazzino01',   47, '8032807067841',      7, 'pz', 10, 5,    7, false, 'T003', 'C0012');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(48,   'magazzino01',   48, '8001097539673',     30, 'pz', 10, 5,   30, false, 'T004', 'D0002');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(49,   'magazzino01',   49, '5010993414314',     30, 'pz', 10, 5,   30, false, 'T004', 'D0003');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(50,   'magazzino01',   50, '8058773201294',     17, 'pz', 10, 5,   17, false, 'T004', 'D0004');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(51,   'magazzino01',   51, '8001097539093',     21, 'pz', 10, 5,   21, false, 'T004', 'D0005');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(52,   'magazzino01',   52, '8020320036222',      6, 'pz', 10, 5,    6, false, 'T004', 'D0006');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(53,   'magazzino01',   53, '8001097210435',      7, 'pz', 10, 5,    7, false, 'T004', 'D0007');
INSERT INTO warehouse(warehouseid, name, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(54,   'magazzino01',   54, '0190199221130',    500, 'pz', 10, 5,  500, false, 'T006', 'F0002');

INSERT INTO purchase_orders(id, date_purchase, total_amount, state, customerid) VALUES( 1, '2018-10-10 00:00:00', 169.50, 'processato', 3);
INSERT INTO purchase_orders(id, date_purchase, total_amount, state, customerid) VALUES( 2, '2018-10-10 00:00:00',  49.90, 'processato', 3);
INSERT INTO purchase_orders(id, date_purchase, total_amount, state, customerid) VALUES( 3, '2018-10-10 00:00:00',  59.70, 'processato', 3);
INSERT INTO purchase_orders(id, date_purchase, total_amount, state, customerid) VALUES( 4, '2018-10-10 00:00:00',  74.90, 'processato', 3);
INSERT INTO purchase_orders(id, date_purchase, total_amount, state, customerid) VALUES( 5, '2018-12-08 00:00:00', 246.60, 'processato', 1);
INSERT INTO purchase_orders(id, date_purchase, total_amount, state, customerid) VALUES( 6, '2018-10-10 00:00:00',  79.60, 'processato', 1);
INSERT INTO purchase_orders(id, date_purchase, total_amount, state, customerid) VALUES( 7, '2018-10-10 00:00:00',  39.80, 'processato', 1);
INSERT INTO purchase_orders(id, date_purchase, total_amount, state, customerid) VALUES( 8, '2018-10-10 00:00:00',  71.60, 'processato', 1);
INSERT INTO purchase_orders(id, date_purchase, total_amount, state, customerid) VALUES( 9, '2018-10-10 00:00:00', 109.20, 'processato', 2);
INSERT INTO purchase_orders(id, date_purchase, total_amount, state, customerid) VALUES(10, '2018-12-08 00:00:00',  19.00, 'processato', 2);

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

INSERT INTO purchase_orders_lineitems(purchase_orders_id, lineitems_lineitemid) VALUES(1, 1);
INSERT INTO purchase_orders_lineitems(purchase_orders_id, lineitems_lineitemid) VALUES(1, 2);
INSERT INTO purchase_orders_lineitems(purchase_orders_id, lineitems_lineitemid) VALUES(1, 3);
INSERT INTO purchase_orders_lineitems(purchase_orders_id, lineitems_lineitemid) VALUES(2, 4);
INSERT INTO purchase_orders_lineitems(purchase_orders_id, lineitems_lineitemid) VALUES(3, 5);
INSERT INTO purchase_orders_lineitems(purchase_orders_id, lineitems_lineitemid) VALUES(4, 6);
INSERT INTO purchase_orders_lineitems(purchase_orders_id, lineitems_lineitemid) VALUES(4, 7);
INSERT INTO purchase_orders_lineitems(purchase_orders_id, lineitems_lineitemid) VALUES(5, 8);
INSERT INTO purchase_orders_lineitems(purchase_orders_id, lineitems_lineitemid) VALUES(5, 9);
INSERT INTO purchase_orders_lineitems(purchase_orders_id, lineitems_lineitemid) VALUES(5, 10);
INSERT INTO purchase_orders_lineitems(purchase_orders_id, lineitems_lineitemid) VALUES(6, 11);
INSERT INTO purchase_orders_lineitems(purchase_orders_id, lineitems_lineitemid) VALUES(6, 12);
INSERT INTO purchase_orders_lineitems(purchase_orders_id, lineitems_lineitemid) VALUES(7, 13);
INSERT INTO purchase_orders_lineitems(purchase_orders_id, lineitems_lineitemid) VALUES(8, 14);
INSERT INTO purchase_orders_lineitems(purchase_orders_id, lineitems_lineitemid) VALUES(8, 15);
INSERT INTO purchase_orders_lineitems(purchase_orders_id, lineitems_lineitemid) VALUES(9, 16);
INSERT INTO purchase_orders_lineitems(purchase_orders_id, lineitems_lineitemid) VALUES(9, 17);
INSERT INTO purchase_orders_lineitems(purchase_orders_id, lineitems_lineitemid) VALUES(9, 18);
INSERT INTO purchase_orders_lineitems(purchase_orders_id, lineitems_lineitemid) VALUES(9, 19);
INSERT INTO purchase_orders_lineitems(purchase_orders_id, lineitems_lineitemid) VALUES(10, 20);
