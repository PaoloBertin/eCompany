-- admin.ecompany     password = admin
-- user.ecompany      password = user
-- mario.rossi        password = user
-- giuseppe.verdi     password = user
-- giuseppe.garibaldi password = user
INSERT INTO users (username, password, enabled) VALUES ('admin', '$2a$10$xQm0oqzToTzplW8Xn9vEouNcV7uIFUzVLuFIZm3txt4WTaD.iTCW2', 1);
INSERT INTO users (username, password, enabled) VALUES ('user.ecompany',  '$2a$10$/P0J7Qo9eAexCmG6bQdM5uQVXNyZ7yutm8sc6qCP2IJj1ZSppTgu.', 1);
INSERT INTO users (username, password, enabled) VALUES ('mario.rossi','$2a$10$AN9KMoBs3GLy9ttjE4jsGODh25qA5xVo1IfX3kyHdFaC5JP2eJ45C',1);
INSERT INTO users (username, password, enabled) VALUES ('giuseppe.verdi','$2a$10$6ougvztJT0if6CTPXPytQuFESws6sq4VEmxkWkg3shNUU/CSbJZ6y',1);
INSERT INTO users (username, password, enabled) VALUES ('giuseppe.garibaldi','$2a$10$rYz10KiusKJ0EQ14b6zyVeVb1fxq617nAZOLE0HHpHtb8VZwGHusG',0);

INSERT INTO authorities(username,authority) VALUES ('admin','ROLE_ADMIN');
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
INSERT INTO group_members(group_id, username) SELECT id,'admin' FROM groups WHERE group_name='Administrators';
INSERT INTO group_members(group_id, username) SELECT id,'user.ecompany' FROM groups WHERE group_name='Users';
INSERT INTO group_members(group_id, username) SELECT id,'mario.rossi' FROM groups WHERE group_name='Users';
INSERT INTO group_members(group_id, username) SELECT id,'giuseppe.verdi' FROM groups WHERE group_name='Users';
INSERT INTO group_members(group_id, username) SELECT id,'giuseppe.garibaldi' FROM groups WHERE group_name='Users';

INSERT INTO address(id, city, street, house_number, zip_code, country, state) VALUES(  1, 'Battaglia Terme', 'Via A. Ecompany', '8', '35041', 'Padova', 'Italia');
INSERT INTO address(id, city, street, house_number, zip_code, country, state) VALUES(  2, 'Battaglia Terme', 'Via U. Ecompany', '7', '35041', 'Padova', 'Italia');
INSERT INTO address(id, city, street, house_number, zip_code, country, state) VALUES(  3, 'Battaglia Terme', 'Vicolo Pio X', '11', '35041', 'Padova', 'Italia');
INSERT INTO address(id, city, street, house_number, zip_code, country, state) VALUES(  4, 'Battaglia Terme', 'Via A. Volta', '7', '35041', 'Padova', 'Italia');
INSERT INTO address(id, city, street, house_number, zip_code, country, state) VALUES(  5, 'Battaglia Terme', 'Via G. Garibaldi', '21', '35041', 'Padova', 'Italia');
INSERT INTO address(id, city, street, house_number, zip_code, country, state) VALUES(101, 'Pontault-Combault', 'Route de Paris', '21', '77340', 'Pontault', 'France');
INSERT INTO address(id, city, street, house_number, zip_code, country, state) VALUES(102, 'Asti', 'Piazza Vittorio Veneto', '45', '35041', 'Asti', 'Italia');

INSERT INTO contacts(id, email, cellular, landline_phone) VALUES (  1, 'admin.ecompany@dmail.com', '3628323289', '0429776337');
INSERT INTO contacts(id, email, cellular, landline_phone) VALUES (  2, 'user.ecompany@dmail.com', '3628323299', '0429776339');
INSERT INTO contacts(id, email, cellular, landline_phone) VALUES (  3, 'mario.rossi@dmail.com', '3458323286', '0497793348');
INSERT INTO contacts(id, email, cellular, landline_phone) VALUES (  4, 'giuseppe.verdi@dmail.com', '3628323287', '0429776335');
INSERT INTO contacts(id, email, cellular, landline_phone) VALUES (  5, 'giuseppe.garibaldi@dmail.com', '3628323288', '0429776336');
INSERT INTO contacts(id, email, cellular, landline_phone) VALUES (101, 'miamland@dmail.com', '3658323288', '0429876336');
INSERT INTO contacts(id, email, cellular, landline_phone) VALUES (102, 'kui@dmail.com', '3628326288', '0429796336');

INSERT INTO customers(customerid, firstname, lastname, username, password, email, address_id, contact_id) VALUES(1, 'Admin', 'Ecompany', 'admin', '$2a$10$xQm0oqzToTzplW8Xn9vEouNcV7uIFUzVLuFIZm3txt4WTaD.iTCW2', 'admin.ecompany@dmail.com', 1, 1);
INSERT INTO customers(customerid, firstname, lastname, username, password, email, address_id, contact_id) VALUES(2, 'User', 'Ecompany', 'user.ecompany', '$2a$10$/P0J7Qo9eAexCmG6bQdM5uQVXNyZ7yutm8sc6qCP2IJj1ZSppTgu.', 'user.ecompany@dmail.com', 2, 2);
INSERT INTO customers(customerid, firstname, lastname, username, password, email, address_id, contact_id) VALUES(3, 'Mario', 'Rossi', 'mario.rossi', '$2a$10$AN9KMoBs3GLy9ttjE4jsGODh25qA5xVo1IfX3kyHdFaC5JP2eJ45C', 'mario.rossi@dmail.com', 3, 3);
INSERT INTO customers(customerid, firstname, lastname, username, password, email, address_id, contact_id) VALUES(4, 'Giuseppe', 'Verdi', 'giuseppe.verdi', '$2a$10$6ougvztJT0if6CTPXPytQuFESws6sq4VEmxkWkg3shNUU/CSbJZ6y', 'giuseppe.verdi@dmail.com', 4, 4);
INSERT INTO customers(customerid, firstname, lastname, username, password, email, address_id, contact_id) VALUES(5, 'Giuseppe', 'Garibaldi', 'giuseppe.garibaldi', '$2a$10$rYz10KiusKJ0EQ14b6zyVeVb1fxq617nAZOLE0HHpHtb8VZwGHusG', 'giuseppe.garibaldi@dmail.com', 5, 5);

INSERT INTO suppliers(id, name, vat, legal_form, registered_office, address_id, contact_id, version) VALUES (1,  'MIAMLAND',   'FR818007445', 'S.P.A', 'Route de Paris 26 77340 Pontault-Combault', 101, 101, 0);
INSERT INTO suppliers(id, name, vat, legal_form, registered_office, address_id, contact_id, version) VALUES (2, 'KUI S.R.L', 'IT09376250016', 'S.R.L', 'Piazza Vittorio Alfieri 45', 102, 102, 0);

INSERT INTO categories(categoryid, name, version)
    VALUES ( 1, 'Libri', 0),
           ( 2, 'CD', 0),
           ( 3, 'DVD', 0),
           ( 4, 'Giochi', 0),
           ( 5, 'Cartoleria', 0),
           ( 6, 'Elettronica', 0);

INSERT INTO products(productid, name, subtitle, description, isbn, price, image, categoryid)
    VALUES ( 1, 'Da Visual Basic a Java', '', 'Con Visual Basic oggi si possono produrre applicazioni allo stato dell''arte: event-driven, Internet-ready, con logica ad oggetti, o meglio a componenti. Tuttavia Visual Basic ha una limitazione evidente: funziona solo in ambienti Microsoft', '8883780450', 29.90, FILE_READ('classpath:static/images/cover/vb2java.jpg'),1),
           ( 2, 'Resurrection', '', 'Even though it under performed at the box office last Christmas, I really enjoyed "Star Trek: Insurrection." Maybe it was the title. Maybe it was the time of year.', '8883780451', 39.90, FILE_READ('classpath:static/images/cover/resurrection.jpg'), 2),
           ( 3, 'Java Web Services', '', 'Java web services" consente allo sviluppatore Java di entrare nel mondo del Web Services.', '1449365116', 29.90, FILE_READ('classpath:static/images/cover/jws.jpg'), 1),
           ( 4, 'Enterprise JavaBeans', '', 'Enterprise JavaBeans e'' la guida definitiva a EJB 2.0, completa dei piu'' recenti sviluppi tecnologici.', '0596158025', 49.90, FILE_READ('classpath:static/images/cover/ejb.jpg'), 1),
           ( 5, 'Java Server Pages', '', 'Gli sviluppatori hanno compreso il potenziale di Enterprise Java per la costruzione di applicazioni distribuite fin dal 1999.', '0596158035', 49.90, FILE_READ('classpath:static/images/cover/jsp.jpg'), 1),
           ( 6, 'When dream and day unite', '', 'Il primo album dei Dream Theater', '0596158045', 19.90, FILE_READ('classpath:static/images/cover/When_dream_and_day_unite.jpg'), 2),
           ( 7, 'Images and world', '', 'Il secondo album dei Dream Theater', '0596158055', 19.90, FILE_READ('classpath:static/images/cover/Images_and_Words.jpg'), 2),
           ( 8, 'Awake', '', 'Il terzo album dei Dream Theater', '0596158065', 19.90, FILE_READ('classpath:static/images/cover/Awake.jpg'), 2),
           ( 9, 'A Change of season', '', 'Il quarto album dei Dream Theater', '0596158075', 19.90, FILE_READ('classpath:static/images/cover/A_Change_of_ Seasons.jpg'), 2),
           (10, 'Falling into infinity', '', 'Il quinto album dei Dream Theater', '0596158085', 19.90, FILE_READ('classpath:static/images/cover/Falling_into_Infinity.jpg'), 2),
           (11, 'Live in Marquee', '', 'Il sesto album dei Dream Theater', '0596158095', 19.90, FILE_READ('classpath:static/images/cover/Live_at_the_Marquee.jpg'), 2),
           (12, 'Scenes from a memory', '', 'Il settimo album dei Dream Theater', '0596158105', 19.90, FILE_READ('classpath:static/images/cover/Scenes_from_a_Memory.jpg'), 2),
           (13, 'Solo. A Star Wars Story', '', 'Tempi duri per la Galassia, forze oscure tramano nell''ombra e minacciano la Repubblica.', '0596158125', 15.90, FILE_READ('classpath:static/images/cover/Solo_A_Star_Wars_Story.jpg'), 3),
           (14, 'Ready Player One', '', 'Nel 2045,  il mondo reale e'' un luogo impervio e ostile. Gli unici momenti in cui Wade Watts si sente veramente vivo e'' quando si immerge in OASIS, un intero universo virtuale dove evade la maggior parte dell''umanita'' per trascorre le proprie giornate.', '0596158135', 15.90, FILE_READ('classpath:static/images/cover/ready-player-one.jpg'), 3),
           (15, 'L''isola dei cani', '', 'Un ragazzo parte alla ricerca del suo cane, esiliato per via di un''influenza canina. Insieme, proveranno a sovvertire le regole del sistema', '0596158145', 16.90, FILE_READ('classpath:static/images/cover/isola-dei-cani.jpg'), 3),
           (16, 'Etica del discorso', '', 'Se l''esigenza di capire e interpretare la vita sociale sta oggi al centro ella filosofia e delle scienze umane, in questo libro Habermas offre concrete indicazioni', '0596158155', 16.15, FILE_READ('classpath:static/images/cover/etica-del-discorso.jpg'), 1),
           (17, 'Stime dei lavori edili.', '', 'Milano, Hoepli, 1917, 16mo legatura editoriale originale, pp. XII-325-64. Prima edizione.', '0596158165', 25.00, FILE_READ('classpath:static/images/cover/stima-dei-lavori-edili.jpg'), 1),
           (18, 'Manuale di Java 9', '', 'Il Manuale di Java 9 e'' stato strutturato per soddisfare le aspettative di aspiranti programmatori: nulla e'' dato per scontato, e'' possibile imparare a programmare partendo da zero', '9788820383022', 42.00, FILE_READ('classpath:static/images/cover/manuale-java9.jpg'), 1),
           (19, 'Dai fondamenti agli oggetti', 'Corso di programmazione Java', 'Questo testo e'' un vero e proprio corso di programmazione accessibile anche a chi non abbia alcuna conoscenza della materia, e nel contempo tocca e approfondisce le tematiche e gli aspetti fondamentali della programmazione. ', '9788865188996', 33.15, FILE_READ('classpath:static/images/cover/dai-fondamenti-agli-oggetti.jpg'), 1),
           (20, 'Applicare UML e i pattern', 'Analisi e progettazione orientata agli oggetti', 'Scritto per la prima volta nell''edizione originale nel 1995, questo testo negli anni e'' stato tradotto in piu'' di 20 lingue.', '9788891901033', 33.15, FILE_READ('classpath:static/images/cover/applicare-uml-e-i-pattern.jpg'), 1),
           (21, 'Ba Martina Spaghettina', '', 'Martina si e'' appena svegliata: una rapida pettinata con la spazzola e ha subito fame. Se le accarezzi il pancino lei ti rispondero'' e ti faro'' capire quanto desidera la sua pappa.', '5010993438136', 43, FILE_READ('classpath:static/images/cover/ba-martina-spaghettina.jpg'), 4),
           (22, 'Agenda giornaliera 2019', '', 'L''agenda giornaliera Moleskine copre tutto l''anno, da gennaio a dicembre.', '8058341715642', 19, FILE_READ('classpath:static/images/cover/agenda-giornaliera-2020.jpg'), 5),
           (23, 'Cuffie iTek Taurus H300', '', 'iTek TAURUS H300 sono cuffie leggere, regolabili, e con materiale soffice in simil-pelle per dare il massimo comfort nell''utilizzo anche di lunga durata', '8032539913843', 24, FILE_READ('classpath:static/images/cover/cuffie-iTek-Taurus-H300.jpg'), 6),
           (24, 'Intelligenza artificiale - Vol. 1', '', 'Un classico nell''ambito della letteratura sull''intelligenza artificiale, apprezzato per la sua presentazione equilibrata e precisa e per l''ampiezza e l''approfondimento dei contenuti.', '9788871925936', 40, FILE_READ('classpath:static/images/cover/9788871925936.jpg'), 1),
           (25, 'Fondamenti di controlli automatici', '', 'Questo libro presenta gli elementi di base della teoria dei sistemi e del controllo.', '9788838668821', 49, FILE_READ('classpath:static/images/cover/9788838668821.jpg'), 1),
           (26, 'Microeconomia', '', 'La sesta edizione italiana, basata su Frank Cartwright Global Edition, presenta diverse interessanti novità di rilievo rispetto alla precedente.', '9788838693243', 52, FILE_READ('classpath:static/images/cover/9788838693243.jpg'), 1),
           (27, 'Statistica e calcolo con R', '', 'Questo libro è il risultato dell''esperienza di diversi anni di insegnamento, presso l''Università di Bologna, in corsi di introduzione all''informatica e di programmazione per il calcolo numerico (AM) e di statistica (ELP).', '9788838693984', 29, FILE_READ('classpath:static/images/cover/9788838693984.jpg'), 1),
           (28, 'La matematica degli dèi e gli algoritmi degli uomini', '', 'I numeri sono un''invenzione della mente o una scoperta con cui la mente accerta l''esistenza di qualcosa che è nel mondo?', '9788845978289', 7.99, FILE_READ('classpath:static/images/cover/9788845978289.jpg'), 1),
           (29, 'Caos', '', 'Dare senso al mondo, alle esperienze quotidiane e, ancor più, agli eventi che ci sorprendono o dai quali può dipendere la nostra sopravvivenza', '9788815358776', 10.99, FILE_READ('classpath:static/images/cover/9788815358776.jpg'), 1),
           (30, 'Lezioni di matematica attuariale delle assicurazioni danni', '', 'Lezioni di matematica attuariale delle assicurazioni danni', '9788867807048', 10.99, FILE_READ('classpath:static/images/cover/9788867807048.jpg'), 1),
           (31, 'Logica matematica', '', 'In questo libro sono presentate tutte le sfaccettature della logica matematica', '9781523624935', 1.99, FILE_READ('classpath:static/images/cover/9781523624935.jpg'), 1),
           (32, 'I teoremi di incompletezza', '', 'Nel 1930 i teoremi di incompletezza di Kurt Gödel cambiarono il corso della filosofia della scienza.', '9788815352101', 8.49, FILE_READ('classpath:static/images/cover/9788815352101.jpg'), 1),
           (33, 'Dalla macchina di Turing a P/NP', '', 'Dalla macchina di Turing a P/NP', '9788838691041', 12.00, FILE_READ('classpath:static/images/cover/9788838691041.jpg'), 1),
           (34, 'L''enigma di Turing', 'Genesi e apologia di un genio matematico', 'Se volessimo trovare un esempio concreto di autentica vita vissuta all’insegna dell’art pour l’art, motto dei simbolisti e decadentisti del XIX secolo, Turing sarebbe indubbiamente un caso paradigmatico', '9788857554655', 12.99, FILE_READ('classpath:static/images/cover/9788857554655.jpg'), 1),
           (35, 'Oggetti concorrenza distribuzione.', '', 'Questo testo raccoglie le note del corso di programmazione concorrente e distribuita', '9788874888030', 24.70, FILE_READ('classpath:static/images/cover/9788874888030.jpg'), 1),
           (36, 'Accetto miracoli', '', 'Accetto Miracoli è con ogni probabilità, il disco più atteso di questo Natale 2019', '0602508304392', 24.70, FILE_READ('classpath:static/images/cover/0602508304392.jpg'), 2),
           (37, 'Colpa delle favole', '', 'Dopo aver pubblicato “I TUOI PARTICOLARI”, singolo già disco di platino con cui ha partecipato alla sessantanovesima edizione del Festival di Sanremo, ULTIMO anticipa ai suoi fan questo nuovo inedito', '3615936866780', 15.75, FILE_READ('classpath:static/images/cover/3615936866780.jpg'), 2),
           (38, 'Mina Fossati', '', 'Mina e Fossati insieme, per un nuovo album di inediti.', '0194397022421', 26.50, FILE_READ('classpath:static/images/cover/0194397022421.jpg'), 2),
           (39, 'Mission', '', 'Nel cuore del continente sud americano, alla metà del XVIII secolo, una comunità di indios capeggiata da alcuni gesuiti cerca di opporsi alle mire espansionistiche di spagnoli e portoghesi.', '5051891126862', 9.99, FILE_READ('classpath:static/images/cover/5051891126862.jpg'), 3),
           (40, 'Maraviglioso Boccaccio', '', '1348: a Firenze infuria la peste, mietendo sempre più vittime. Un gruppo di 10 giovani cerca rifugio in una casa di campagna, per tentare di sfuggire al contagio.', '8057092004869', 9.99, FILE_READ('classpath:static/images/cover/8057092004869.jpg'), 3),
           (41, 'Elizabeth', '', 'Elisabetta I per difendere il suo regno deve affrontare diversi ostacoli.', '5050582518832', 9.99, FILE_READ('classpath:static/images/cover/5050582518832.jpg'), 3),
           (42, 'Kagemusha', '', 'Il principe Shingen, che sogna di riunificare il Giappone nel Cinquecento, ha al suo seguito un sosia grazie al quale riesce a mantenere segreta la morte del Re per ingannare il nemico e salvaguardare il regno', '8010312035869', 9.99, FILE_READ('classpath:static/images/cover/8010312035869.jpg'), 3),
           (43, 'Le crociate', '', 'Balian, un giovane fabbro francese, disperato per la perdita della moglie e del figlio ancora piccolo, decide di seguire nella sua sacra missione colui che dice di essere il suo vero padre', '5051891084803', 9.99, FILE_READ('classpath:static/images/cover/5051891084803.jpg'), 3),
           (44, 'Alexander', '', 'La storia del conquistatore più famoso della storia, Alessandro, un uomo che a 27 anni riesce a impadronirsi quasi per intero del mondo allora conosciuto', '7321958389362', 9.99, FILE_READ('classpath:static/images/cover/7321958389362.jpg'), 3),
           (45, 'Robin Hood', '', 'In un''epoca di oppressione e feroce tirannia, un fuorilegge divenne l''eroe che salvò una nazione e ispirò generazioni a combattere per la libertà', '5053083017149', 9.99, FILE_READ('classpath:static/images/cover/5053083017149.jpg'), 3),
           (46, 'Il gladiatore', '', 'Maximus è il generale romano prediletto da Marco Aurelio, ma proprio per questo odiato da Commodo, figlio dell''imperatore.', '5053083156510', 9.99, FILE_READ('classpath:static/images/cover/5053083156510.jpg'), 3),
           (47, 'The Eagle', '', 'Nel 140 d.C. l''Impero Romano si estende fino alla Britannia, anche se non ha il controllo di tutte le regioni e l''estremo nord è governato dalle tribù ribelli della Caledonia.', '8032807067841', 9.99, FILE_READ('classpath:static/images/cover/8032807067841.jpg'), 3),
           (48, 'Cyber Flipper', '', 'Flipper elettronico con luci e suoni', '8001097539673', 45.99, FILE_READ('classpath:static/images/cover/8001097539673.jpg'), 4),
           (49, 'Monopoly', '', 'Monopoly, il gioco di contrattazione più famoso del mondo.', '5010993414314', 25.00, FILE_READ('classpath:static/images/cover/5010993414314.jpg'), 4),
           (50, 'Poker Romano', '', 'I giochi di dadi erano molto popolari nell&rsquoAntica Roma. Dagli schiavi all''Imperatore, tutti vi giocavano.', '8058773201294', 25.00, FILE_READ('classpath:static/images/cover/8058773201294.jpg'), 4),
           (51, 'Scacchi Dama Tria', '', 'Game Set Scacchi e Dama Tria 36 x 36 CmContenitore in legno da 36 x 36 cm con scomparti. Scacchiera intarsiata, bifacciale. Pedine e scacchi in legno (altezza Re 65 mm), con regole di gioco', '8001097539093', 29.50, FILE_READ('classpath:static/images/cover/8001097539093.jpg'), 4),
           (52, 'Mazzo 52 Carte da Gioco Trevigiane', '', '52 Carte da Gioco TrevigianeMazzo da 52 carte Triestine. Solide e leggere, dalla stampa professionale e dal design unico e inconfondibile costruite interamente in plastica.', '8020320036222', 12.00, FILE_READ('classpath:static/images/cover/8020320036222.jpg'), 4),
           (53, 'Carte Da Gioco Ramino Italia', '', 'L''astuccio Ramino Italia Dal Negro include due mazzi da poker in cartoncino.', '8001097210435', 12.00, FILE_READ('classpath:static/images/cover/8001097210435.jpg'), 4),
           (54, 'Apple iPhone 11', '', 'Un nuovo sistema a doppia fotocamera, per inquadrare più cose intorno a te.', '0190199221130', 796, FILE_READ('classpath:static/images/cover/0190199221130.jpg'), 6);

INSERT INTO price_lists(id, name, product_code, price, version)
    VALUES (  1, 'base', '8883780450', 29.90, 0);
    VALUES (  2, 'base', '8883780450', 29.90, 0);
    VALUES (  3, 'base', '8883780450', 29.90, 0);
    VALUES (  4, 'base', '8883780450', 29.90, 0);
    VALUES (  5, 'base', '8883780450', 29.90, 0);
    VALUES (  6, 'base', '8883780450', 29.90, 0);
    VALUES (  7, 'base', '8883780450', 29.90, 0);
    VALUES (  8, 'base', '8883780450', 29.90, 0);
    VALUES (  9, 'base', '8883780450', 29.90, 0);
    VALUES ( 10, 'base', '8883780450', 29.90, 0);
    VALUES ( 11, 'base', '8883780450', 29.90, 0);
    VALUES ( 12, 'base', '8883780450', 29.90, 0);
    VALUES ( 13, 'base', '8883780450', 29.90, 0);
    VALUES ( 14, 'base', '8883780450', 29.90, 0);
    VALUES ( 15, 'base', '8883780450', 29.90, 0);
    VALUES ( 16, 'base', '8883780450', 29.90, 0);
    VALUES ( 17, 'base', '8883780450', 29.90, 0);
    VALUES ( 18, 'base', '8883780450', 29.90, 0);
    VALUES ( 19, 'base', '8883780450', 29.90, 0);
    VALUES ( 20, 'base', '8883780450', 29.90, 0);
    VALUES ( 21, 'base', '8883780450', 29.90, 0);
    VALUES ( 22, 'base', '8883780450', 29.90, 0);
    VALUES ( 23, 'base', '8883780450', 29.90, 0);
    VALUES ( 24, 'base', '8883780450', 29.90, 0);
    VALUES ( 25, 'base', '8883780450', 29.90, 0);
    VALUES ( 26, 'base', '8883780450', 29.90, 0);
    VALUES ( 27, 'base', '8883780450', 29.90, 0);
    VALUES ( 28, 'base', '8883780450', 29.90, 0);
    VALUES ( 29, 'base', '8883780450', 29.90, 0);
    VALUES ( 30, 'base', '8883780450', 29.90, 0);
    VALUES ( 31, 'base', '8883780450', 29.90, 0);
    VALUES ( 32, 'base', '8883780450', 29.90, 0);
    VALUES ( 33, 'base', '8883780450', 29.90, 0);
    VALUES ( 34, 'base', '8883780450', 29.90, 0);
    VALUES ( 35, 'base', '8883780450', 29.90, 0);
    VALUES ( 36, 'base', '8883780450', 29.90, 0);
    VALUES ( 37, 'base', '8883780450', 29.90, 0);
    VALUES ( 38, 'base', '8883780450', 29.90, 0);
    VALUES ( 39, 'base', '8883780450', 29.90, 0);
    VALUES ( 40, 'base', '8883780450', 29.90, 0);
    VALUES ( 41, 'base', '8883780450', 29.90, 0);
    VALUES ( 42, 'base', '8883780450', 29.90, 0);
    VALUES ( 43, 'base', '8883780450', 29.90, 0);
    VALUES ( 44, 'base', '8883780450', 29.90, 0);
    VALUES ( 45, 'base', '8883780450', 29.90, 0);
    VALUES ( 46, 'base', '8883780450', 29.90, 0);
    VALUES ( 47, 'base', '8883780450', 29.90, 0);
    VALUES ( 48, 'base', '8883780450', 29.90, 0);
    VALUES ( 49, 'base', '8883780450', 29.90, 0);
    VALUES ( 50, 'base', '8883780450', 29.90, 0);
    VALUES ( 51, 'base', '8883780450', 29.90, 0);
    VALUES ( 52, 'base', '8883780450', 29.90, 0);
    VALUES ( 53, 'base', '8883780450', 29.90, 0);
    VALUES ( 54, 'base', '8883780450', 29.90, 0);

INSERT INTO warehouses(id, name, version)
    VALUES( 1, 'magazzino01',  0),
          ( 2, 'magazzino02',  0),
          ( 3, 'magazzino03',  0),
          ( 4, 'magazzino04',  0),
          ( 5, 'magazzino05',  0);

INSERT INTO documentations(id, warehouse_id, causal, document, document_date, document_number, line_item_id, version)
    VALUES (  1,  1, 'PURCHASE', 'TRANSPORT_DOCUMENT', '2018-10-09',  10,   1, 0),
           (  2,  1, 'PURCHASE', 'TRANSPORT_DOCUMENT', '2018-10-09',  22,   2, 0),
           (  3,  1,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-10',  11,   3, 0),
           (  4,  1,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-10',  12,   4, 0),
           (  5,  1, 'PURCHASE', 'TRANSPORT_DOCUMENT', '2018-10-11',   2,   5, 0),
           (  6,  1, 'PURCHASE', 'TRANSPORT_DOCUMENT', '2018-10-11',  42,   6, 0),
           (  7,  1,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-12',  13,   7, 0),
           (  8,  1,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-12',  14,   8, 0),
           (  9,  1,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-12',  15,   9, 0),
           ( 10,  1,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-12',  16,  10, 0),
           ( 11,  1, 'PURCHASE', 'TRANSPORT_DOCUMENT', '2018-10-13',  26,  11, 0),
           ( 12,  1, 'PURCHASE', 'TRANSPORT_DOCUMENT', '2018-10-13',  37,  12, 0),
           ( 13,  1,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-14',  17,  13, 0),
           ( 14,  1,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  14, 0),
           ( 15,  1,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  15, 0),
           ( 16,  1,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  16, 0),
           ( 17,  1,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  17, 0),
           ( 18,  1,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  18, 0),
           ( 19,  1,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  19, 0),
           ( 20,  1,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  20, 0),
           ( 21,  1,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  21, 0),
           ( 22,  1,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  22, 0),
           ( 23,  1,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  23, 0),
           ( 24,  1,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  24, 0),
           ( 25,  1,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  25, 0),
           ( 26,  2,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  26, 0),
           ( 27,  2,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  27, 0),
           ( 28,  2,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  28, 0),
           ( 29,  2,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  29, 0),
           ( 30,  2,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  30, 0),
           ( 31,  2,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  31, 0),
           ( 32,  2,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  32, 0),
           ( 33,  2,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  33, 0),
           ( 34,  2,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  34, 0),
           ( 35,  2,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  35, 0),
           ( 36,  3,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  36, 0),
           ( 37,  3,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  37, 0),
           ( 38,  3,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  38, 0),
           ( 39,  3,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  39, 0),
           ( 40,  3,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  40, 0),
           ( 41,  3,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  40, 0),
           ( 42,  3,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  40, 0),
           ( 43,  3,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  40, 0),
           ( 44,  3,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  40, 0),
           ( 45,  3,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  40, 0),
           ( 46,  3,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  40, 0),
           ( 47,  3,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  40, 0),
           ( 48,  3,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  40, 0),
           ( 49,  3,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  40, 0),
           ( 50,  3,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  40, 0),
           ( 51,  3,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  40, 0),
           ( 52,  3,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  40, 0),
           ( 53,  3,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  40, 0),
           ( 54,  3,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  40, 0),
           ( 55,  3,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  40, 0),
           ( 56,  3,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  40, 0),
           ( 57,  3,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  40, 0),
           ( 58,  3,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  40, 0),
           ( 59,  3,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  40, 0),
           ( 60,  3,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  40, 0),
           ( 61,  3,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  40, 0),
           ( 62,  3,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  40, 0),
           ( 63,  3,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  40, 0),
           ( 64,  3,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  40, 0),
           ( 65,  3,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  40, 0),
           ( 66,  4,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  40, 0),
           ( 67,  4,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  40, 0),
           ( 68,  4,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  40, 0),
           ( 69,  4,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  40, 0),
           ( 70,  4,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  40, 0),
           ( 71,  4,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  40, 0),
           ( 72,  4,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  40, 0),
           ( 73,  4,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  40, 0),
           ( 74,  4,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  40, 0),
           ( 75,  4,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  40, 0),
           ( 76,  4,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  40, 0),
           ( 77,  4,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  40, 0),
           ( 78,  4,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  40, 0),
           ( 79,  4,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  40, 0),
           ( 80,  4,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  40, 0),
           ( 81,  4,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  40, 0),
           ( 82,  4,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  40, 0),
           ( 83,  4,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  18,  40, 0),
           ( 84,  4,     'SALE', 'TRANSPORT_DOCUMENT', '2018-10-15',  19,  40, 0);

INSERT INTO warehouse_journal(id, documentation_id, version)
    VALUES (  1,  1, 0),
           (  2,  2, 0),
           (  3,  3, 0),
           (  4,  4, 0),
           (  5,  5, 0),
           (  6,  6, 0),
           (  7,  7, 0),
           (  8,  8, 0),
           (  9,  9, 0),
           ( 10, 10, 0),
           ( 11, 11, 0),
           ( 12, 12, 0),
           ( 13, 13, 0),
           ( 14, 14, 0),
           ( 15, 15, 0),
           ( 16, 16, 0),
           ( 17, 17, 0),
           ( 18, 18, 0),
           ( 19, 19, 0),
           ( 20, 20, 0),
           ( 21, 21, 0),
           ( 22, 22, 0),
           ( 23, 23, 0),
           ( 24, 24, 0),
           ( 25, 25, 0),
           ( 26, 26, 0),
           ( 27, 27, 0),
           ( 28, 28, 0),
           ( 29, 29, 0),
           ( 30, 30, 0),
           ( 31, 31, 0),
           ( 32, 32, 0),
           ( 33, 33, 0),
           ( 34, 34, 0),
           ( 35, 35, 0),
           ( 36, 36, 0),
           ( 37, 37, 0),
           ( 38, 38, 0),
           ( 39, 39, 0),
           ( 40, 40, 0),
           ( 41, 41, 0),
           ( 42, 42, 0),
           ( 43, 43, 0),
           ( 44, 44, 0),
           ( 45, 45, 0),
           ( 46, 46, 0),
           ( 47, 47, 0),
           ( 48, 48, 0),
           ( 49, 49, 0),
           ( 50, 50, 0),
           ( 51, 51, 0),
           ( 52, 52, 0),
           ( 53, 53, 0),
           ( 54, 54, 0),
           ( 55, 55, 0),
           ( 56, 56, 0),
           ( 57, 57, 0),
           ( 58, 58, 0),
           ( 59, 59, 0),
           ( 60, 60, 0),
           ( 61, 61, 0),
           ( 62, 62, 0),
           ( 63, 63, 0),
           ( 64, 64, 0),
           ( 65, 65, 0),
           ( 66, 66, 0),
           ( 67, 67, 0),
           ( 68, 68, 0),
           ( 69, 69, 0),
           ( 70, 70, 0),
           ( 71, 71, 0),
           ( 72, 72, 0),
           ( 73, 73, 0),
           ( 74, 74, 0),
           ( 75, 75, 0),
           ( 76, 76, 0),
           ( 77, 77, 0),
           ( 78, 78, 0),
           ( 79, 79, 0),
           ( 80, 80, 0),
           ( 81, 81, 0),
           ( 82, 82, 0),
           ( 83, 83, 0),
           ( 84, 84, 0);

INSERT INTO warehouse_card_products(id, product_code, sku, reorder_quantity, unit, container, location, version)
    VALUES( 1,   1,    '8883780450',  5, 'pz', 'T001', 'A0001', 0),
          ( 2,   2,    '8883780451',  5, 'pz', 'T002', 'B0001', 0),
          ( 3,   3,    '1449365116',  5, 'pz', 'T001', 'A0002', 0),
          ( 4,   4,    '0596158025',  5, 'pz', 'T001', 'A0003', 0),
          ( 5,   5,    '0596158035',  5, 'pz', 'T001', 'A0004', 0),
          ( 6,   6,    '0596158045',  5, 'pz', 'T002', 'B0002', 0),
          ( 7,   7,    '0596158055',  5, 'pz', 'T002', 'B0003', 0),
          ( 8,   8,    '0596158065',  5, 'pz', 'T002', 'B0004', 0),
          ( 9,   9,    '0596158075',  5, 'pz', 'T002', 'B0005', 0),
          (10,  10,    '0596158085',  5, 'pz', 'T002', 'B0006', 0),
          (11,  11,    '0596158095',  5, 'pz', 'T002', 'B0007', 0),
          (12,  12,    '0596158105',  5, 'pz', 'T002', 'B0008', 0),
          (13,  13,    '0596158125',  5, 'pz', 'T003', 'C0001', 0),
          (14,  14,    '0596158135',  5, 'pz', 'T003', 'C0002', 0),
          (15,  15,    '0596158145',  5, 'pz', 'T003', 'C0003', 0),
          (16,  16,    '0596158155',  5, 'pz', 'T001', 'A0005', 0),
          (17,  17,    '0596158165',  5, 'pz', 'T001', 'A0006', 0),
          (18,  18, '9788820383022',  5, 'pz', 'T001', 'A0007', 0),
          (19,  19, '9788865188996',  5, 'pz', 'T001', 'A0008', 0),
          (20,  20, '9788891901033',  5, 'pz', 'T001', 'A0009', 0),
          (21,  21, '5010993438136',  5, 'pz', 'T004', 'D0001', 0),
          (22,  22, '8058341715642',  5, 'pz', 'T005', 'E0001', 0),
          (23,  23, '8032539913843',  5, 'pz', 'T006', 'F0001', 0),
          (24,  24, '9788871925936',  5, 'pz', 'T001', 'A0010', 0),
          (25,  25, '9788838668821',  5, 'pz', 'T001', 'A0011', 0),
          (26,  26, '9788838693243',  5, 'pz', 'T001', 'A0012', 0),
          (27,  27, '9788838693984',  5, 'pz', 'T001', 'A0013', 0),
          (28,  28, '9788845978289',  5, 'pz', 'T001', 'A0014', 0),
          (29,  29, '9788815358776',  5, 'pz', 'T001', 'A0015', 0),
          (30,  30, '9788867807048',  5, 'pz', 'T001', 'A0016', 0),
          (31,  31, '9781523624935',  5, 'pz', 'T001', 'A0017', 0),
          (32,  32, '9788815352101',  5, 'pz', 'T001', 'A0018', 0),
          (33,  33, '9788838691041',  5, 'pz', 'T001', 'A0019', 0),
          (34,  34, '9788857554655',  5, 'pz', 'T001', 'A0020', 0),
          (35,  35, '9788874888030',  5, 'pz', 'T001', 'A0021', 0),
          (36,  36, '0602508304392',  5, 'pz', 'T002', 'B0009', 0),
          (37,  37, '3615936866780',  5, 'pz', 'T002', 'B0010', 0),
          (38,  38, '0194397022421',  5, 'pz', 'T002', 'B0011', 0),
          (39,  39, '5051891126862',  5, 'pz', 'T003', 'C0004', 0),
          (40,  40, '8057092004869',  5, 'pz', 'T003', 'C0005', 0),
          (41,  41, '5050582518832',  5, 'pz', 'T003', 'C0006', 0),
          (42,  42, '8010312035869',  5, 'pz', 'T003', 'C0007', 0),
          (43,  43, '5051891084803',  5, 'pz', 'T003', 'C0008', 0),
          (44,  44, '7321958389362',  5, 'pz', 'T003', 'C0009', 0),
          (45,  45, '5053083017149',  5, 'pz', 'T003', 'C0010', 0),
          (46,  46, '5053083156510',  5, 'pz', 'T003', 'C0011', 0),
          (47,  47, '8032807067841',  5, 'pz', 'T003', 'C0012', 0),
          (48,  48, '8001097539673',  5, 'pz', 'T004', 'D0002', 0),
          (49,  49, '5010993414314',  5, 'pz', 'T004', 'D0003', 0),
          (50,  50, '8058773201294',  5, 'pz', 'T004', 'D0004', 0),
          (51,  51, '8001097539093',  5, 'pz', 'T004', 'D0005', 0),
          (52,  52, '8020320036222',  5, 'pz', 'T004', 'D0006', 0),
          (53,  53, '8001097210435',  5, 'pz', 'T004', 'D0007', 0),
          (54,  54, '8001097210435',  5, 'pz', 'T004', 'D0008', 0);

INSERT INTO warehouse_cards(id, warehouse_id, line_item_id, warehouse_card_product_id, stock, inventory_value, version)
    VALUES ( 1,  1,  1,  1, 20,  400, 0),
           ( 2,  1,  1,  2, 10,    0, 0),
           ( 3,  1,  1,  3, 20,    0, 0),
           ( 4,  1,  1,  4, 10,    0, 0),
           ( 5,  1,  1,  5, 35,    0, 0),
           ( 6,  1,  1,  6, 15,    0, 0),
           ( 7,  1,  1,  7, 15,    0, 0),
           ( 8,  1,  1,  8, 15,    0, 0),
           ( 9,  1,  1,  9, 15,    0, 0),
           (10,  1,  1, 10, 15,    0, 0),
           (11,  1,  1, 11, 15,    0, 0),
           (12,  1,  1, 12, 15,    0, 0),
           (13,  1,  1, 13, 10,    0, 0),
           (14,  1,  1, 14, 10,    0, 0),
           (15,  1,  1, 15, 20,    0, 0),
           (16,  1,  1, 16, 20,    0, 0),
           (17,  1,  1, 17, 10,    0, 0),
           (18,  1,  1, 18, 10,    0, 0),
           (19,  1,  1, 19, 20,    0, 0),
           (20,  1,  1, 20, 10,    0, 0),
           (21,  1,  1, 21, 10,    0, 0),
           (22,  1,  1, 22, 10,    0, 0),
           (23,  1,  1, 23 ,10,    0, 0),
           (24,  1,  1, 24, 10,    0, 0),
           (25,  1,  1, 25, 10,    0, 0),
           (26,  2,  1, 26, 10,    0, 0),
           (27,  2,  1, 27, 20,    0, 0),
           (28,  2,  1, 28, 10,    0, 0),
           (29,  2,  1, 29, 10,    0, 0),
           (30,  2,  1, 30, 10,    0, 0),
           (31,  2,  1, 31, 10,    0, 0),
           (32,  2,  1, 32, 10,    0, 0),
           (33,  2,  1, 33, 10,    0, 0),
           (34,  2,  1, 34, 10,    0, 0),
           (35,  2,  1, 35, 10,    0, 0),
           (36,  3,  1, 36, 10,    0, 0),
           (37,  3,  1, 37, 10,    0, 0),
           (38,  3,  1, 38, 10,    0, 0),
           (39,  3,  1, 39, 10,    0, 0),
           (40,  3,  1, 40, 10,    0, 0),
           (41,  3,  1, 41, 10,    0, 0),
           (42,  3,  1, 42, 10,    0, 0),
           (43,  3,  1, 43, 10,    0, 0),
           (44,  3,  1, 44, 10,    0, 0),
           (45,  3,  1, 45, 10,    0, 0),
           (46,  3,  1, 46, 10,    0, 0),
           (47,  3,  1, 47, 10,    0, 0),
           (48,  3,  1, 48, 30,    0, 0),
           (49,  3,  1, 49, 10,    0, 0),
           (50,  3,  1, 50, 10,    0, 0),
           (51,  3,  1, 51, 10,    0, 0),
           (52,  3,  1, 52, 10,    0, 0),
           (53,  3,  1, 53, 10,    0, 0),
           (54,  3,  1, 54, 10,    0, 0),
           (55,  3,  1,  1, 20,    0, 0),
           (56,  3,  1,  2, 10,    0, 0),
           (57,  3,  1,  3, 20,    0, 0),
           (58,  3,  1,  4, 10,    0, 0),
           (59,  3,  1,  5, 10,    0, 0),
           (60,  3,  1,  6, 10,    0, 0),
           (61,  3,  1,  7, 10,    0, 0),
           (62,  3,  1,  8, 10,    0, 0),
           (63,  3,  1,  9, 15,    0, 0),
           (64,  3,  1, 10, 15,    0, 0),
           (65,  3,  1, 11, 15,    0, 0),
           (66,  4,  1, 12, 15,    0, 0),
           (67,  4,  1, 13, 10,    0, 0),
           (68,  4,  1, 14, 10,    0, 0),
           (69,  4,  1, 15, 20,    0, 0),
           (70,  4,  1, 16, 20,    0, 0),
           (71,  4,  1, 17, 10,    0, 0),
           (72,  4,  1, 18, 10,    0, 0),
           (73,  4,  1, 19, 20,    0, 0),
           (74,  4,  1, 20, 10,    0, 0),
           (75,  4,  1, 21, 10,    0, 0),
           (76,  4,  1, 22, 10,    0, 0),
           (77,  4,  1, 23, 10,    0, 0),
           (78,  4,  1, 24, 10,    0, 0),
           (79,  4,  1, 25, 10,    0, 0),
           (80,  4,  1, 26, 10,    0, 0),
           (81,  4,  1, 27, 10,    0, 0),
           (82,  4,  1, 28, 10,    0, 0),
           (83,  4,  1, 29, 10,    0, 0),
           (84,  4,  1, 30, 10,    0, 0);

INSERT INTO transport_documents(id, document_date, document_number, transferor_code, transferee_code, version)
    VALUES(  1, '2018-10-09',  1, 's-00001', 'c-00001', 0),
          (  2, '2018-10-09',  1, 's-00001', 'c-00001', 0),
          (  3, '2018-10-09',  1, 's-00001', 'c-00001', 0),
          (  4, '2018-10-09',  1, 's-00001', 'c-00001', 0),
          (  5, '2018-10-09',  1, 's-00001', 'c-00001', 0),
          (  6, '2018-10-09',  1, 's-00001', 'c-00001', 0);

INSERT INTO invoices(id, document_date, document_number, transferor_code, transferee_code, version)
    VALUES(  1, '2018-10-09',  1, 's-00001', 'c-00001', 0),
          (  2, '2018-10-09',  1, 's-00001', 'c-00001', 0),
          (  3, '2018-10-09',  1, 's-00001', 'c-00001', 0),
          (  4, '2018-10-09',  1, 's-00001', 'c-00001', 0),
          (  5, '2018-10-09',  1, 's-00001', 'c-00001', 0),
          (  6, '2018-10-09',  1, 's-00001', 'c-00001', 0),
          (  7, '2018-10-09',  1, 's-00001', 'c-00001', 0),
          (  8, '2018-10-09',  1, 's-00001', 'c-00001', 0),
          (  9, '2018-10-09',  1, 's-00001', 'c-00001', 0);

INSERT INTO purchase_orders(id, date_purchase, total_amount, state, customerid)
    VALUES( 1, '2018-10-10 00:00:00', 169.50, 'processato', 3),
          ( 2, '2018-10-10 00:00:00',  49.90, 'processato', 3),
          ( 3, '2018-10-10 00:00:00',  59.70, 'processato', 3),
          ( 4, '2018-10-10 00:00:00',  74.90, 'processato', 3),
          ( 5, '2018-12-08 00:00:00', 246.60, 'processato', 1),
          ( 6, '2018-10-10 00:00:00',  79.60, 'processato', 1),
          ( 7, '2018-10-10 00:00:00',  39.80, 'processato', 1),
          ( 8, '2018-10-10 00:00:00',  71.60, 'processato', 1),
          ( 9, '2018-10-10 00:00:00', 109.20, 'processato', 2),
          (10, '2018-12-08 00:00:00',  19.00, 'processato', 2);

INSERT INTO line_items(id, product_id, quantity, price, version)
    VALUES( 1,  1, 1, 10.0, 0),
          ( 2,  2, 2, 10.0, 0),
          ( 3,  3, 2, 10.0, 0),
          ( 4,  5, 1, 10.0, 0),
          ( 5, 10, 3, 10.0, 0),
          ( 6, 17, 1, 10.0, 0),
          ( 7,  4, 1, 10.0, 0),
          ( 8,  7, 2, 10.0, 0),
          ( 9,  8, 1, 10.0, 0),
          (10, 13, 4, 10.0, 0),
          (11,  6, 1, 10.0, 0),
          (12,  9, 1, 10.0, 0),
          (13, 11, 1, 10.0, 0),
          (14, 14, 1, 10.0, 0),
          (15, 12, 1, 10.0, 0),
          (16, 16, 1, 10.0, 0),
          (17, 15, 1, 10.0, 0),
          (18, 20, 1, 10.0, 0),
          (19, 21, 1, 10.0, 0),
          (20, 22, 1, 10.0, 0),
          (21,  1, 1, 10.0, 0),
          (22,  2, 2, 10.0, 0),
          (23,  3, 2, 10.0, 0),
          (24,  5, 1, 10.0, 0),
          (25, 10, 3, 10.0, 0),
          (26, 17, 1, 10.0, 0),
          (27,  4, 1, 10.0, 0),
          (28,  7, 2, 10.0, 0),
          (29,  8, 1, 10.0, 0),
          (30, 13, 4, 10.0, 0),
          (31,  6, 1, 10.0, 0),
          (32,  9, 1, 10.0, 0),
          (33, 11, 1, 10.0, 0),
          (34, 14, 1, 10.0, 0),
          (35, 12, 1, 10.0, 0),
          (36, 16, 1, 10.0, 0),
          (37, 15, 1, 10.0, 0),
          (38, 20, 1, 10.0, 0),
          (39, 21, 1, 10.0, 0),
          (40, 22, 1, 10.0, 0);


INSERT INTO purchase_orders_lineitems(purchase_orders_id, line_items_line_item_id)
    VALUES ( 1,  1),
           ( 1,  2),
           ( 1,  3),
           ( 2,  4),
           ( 3,  5),
           ( 4,  6),
           ( 4,  7),
           ( 5,  8),
           ( 5,  9),
           ( 5, 10),
           ( 6, 11),
           ( 6, 12),
           ( 7, 13),
           ( 8, 14),
           ( 8, 15),
           ( 9, 16),
           ( 9, 17),
           ( 9, 18),
           ( 9, 19),
           (10, 20);

INSERT INTO sales_orders(id, date_sale, total_amount, state, supplier_id)
    VALUES( 1, '2018-10-10 00:00:00', 169.50, 'processato', 2),
          ( 2, '2018-10-10 00:00:00',  49.90, 'processato', 2),
          ( 3, '2018-10-10 00:00:00',  59.70, 'processato', 2),
          ( 4, '2018-10-10 00:00:00',  74.90, 'processato', 1),
          ( 5, '2018-12-08 00:00:00', 246.60, 'processato', 1),
          ( 6, '2018-10-10 00:00:00',  79.60, 'processato', 1),
          ( 7, '2018-10-10 00:00:00',  39.80, 'processato', 1),
          ( 8, '2018-10-10 00:00:00',  71.60, 'processato', 1),
          ( 9, '2018-10-10 00:00:00', 109.20, 'processato', 2),
          (10, '2018-12-08 00:00:00',  19.00, 'processato', 2);

INSERT INTO sales_orders_lineitems(sales_orders_id, line_items_line_item_id)
    VALUES(1,  21),
          (1,  22),
          (1,  23),
          (2,  24),
          (3,  25),
          (4,  26),
          (4,  27),
          (5,  28),
          (5,  29),
          (5,  30),
          (6,  31),
          (6,  32),
          (7,  33),
          (8,  34),
          (8,  35),
          (9,  36),
          (9,  37),
          (9,  38),
          (9,  39),
          (10, 40);

INSERT INTO accounts(id, name, balance)
    VALUES(1, 'eCompany', 100000);
