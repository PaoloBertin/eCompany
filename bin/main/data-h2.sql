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

INSERT INTO categories(categoryid, name, version) VALUES(1, 'Libri', 0);
INSERT INTO categories(categoryid, name, version) VALUES(2, 'CD', 0);
INSERT INTO categories(categoryid, name, version) VALUES(3, 'DVD', 0);
INSERT INTO categories(categoryid, name, version) VALUES(4, 'Giochi', 0);
INSERT INTO categories(categoryid, name, version) VALUES(5, 'Cartoleria', 0);
INSERT INTO categories(categoryid, name, version) VALUES(6, 'Elettronica', 0);

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
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(53, 'Carte Da Gioco Ramino Italia', 'L''astuccio Ramino Italia Dal Negro include due mazzi da poker in cartoncino.', '8020320036222', 12.00, FILE_READ('classpath:static/images/cover/8020320036222.jpg'), 4);
INSERT INTO products(productid, name, description, isbn, price, image, categoryid) VALUES(54, 'Apple iPhone 11', 'Un nuovo sistema a doppia fotocamera, per inquadrare più cose intorno a te.', '0190199221130', 796, FILE_READ('classpath:static/images/cover/0190199221130.jpg'), 6);

INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES( 1,  1, '8883780450', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES( 2,  2, 'B0002', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES( 3,  3, 'B0003', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES( 4,  4, 'B0004', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES( 5,  5, 'B0005', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES( 6,  6, 'B0006', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES( 7,  7, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES( 8,  8, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES( 9,  9, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(10, 10, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(11, 11, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(12, 12, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(13, 13, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(14, 14, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(15, 15, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(16, 16, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(17, 17, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(18, 18, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(19, 19, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(20, 20, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(21, 21, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(22, 22, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(23, 23, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(24, 24, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(25, 25, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(26, 26, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(27, 27, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(28, 28, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(29, 29, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(30, 30, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(31, 31, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(32, 32, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(33, 33, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(34, 34, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(35, 35, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(36, 36, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(37, 37, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(38, 38, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(39, 39, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(40, 40, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(41, 41, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(42, 42, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(43, 43, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(44, 44, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(45, 45, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(46, 46, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(47, 47, 'B0001', 20, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(48, 48, 'B0001', 30, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(49, 49, 'B0001', 30, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(50, 50, 'B0001', 17, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(51, 51, 'B0001', 21, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(52, 52, 'B0001',  6, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(53, 53, 'B0001',  7, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');
INSERT INTO warehouse(warehouseid, product_id, sku, cost, unit, quantity, reorder_quantity, inventory_value, reorder, container, location) VALUES(54, 54, 'B0001', 500, 'pz', 10, 5, 25.0, false, 'T032', 'F2S1');

INSERT INTO movement(movementid, datemovement, totalamount, state, customerid) VALUES( 1, '2018-10-10 00:00:00', 169.50, 'processato', 2);
INSERT INTO movement(movementid, datemovement, totalamount, state, customerid) VALUES( 2, '2018-10-10 00:00:00',  49.90, 'processato', 2);
INSERT INTO movement(movementid, datemovement, totalamount, state, customerid) VALUES( 3, '2018-10-10 00:00:00',  59.70, 'processato', 2);
INSERT INTO movement(movementid, datemovement, totalamount, state, customerid) VALUES( 4, '2018-10-10 00:00:00',  74.90, 'processato', 2);
INSERT INTO movement(movementid, datemovement, totalamount, state, customerid) VALUES( 5, '2018-12-08 00:00:00', 246.60, 'processato', 1);
INSERT INTO movement(movementid, datemovement, totalamount, state, customerid) VALUES( 6, '2018-10-10 00:00:00',  79.60, 'processato', 1);
INSERT INTO movement(movementid, datemovement, totalamount, state, customerid) VALUES( 7, '2018-10-10 00:00:00',  39.80, 'processato', 1);
INSERT INTO movement(movementid, datemovement, totalamount, state, customerid) VALUES( 8, '2018-10-10 00:00:00',  71.60, 'processato', 1);
INSERT INTO movement(movementid, datemovement, totalamount, state, customerid) VALUES( 9, '2018-10-10 00:00:00', 109.20, 'processato', 3);
INSERT INTO movement(movementid, datemovement, totalamount, state, customerid) VALUES(10, '2018-12-08 00:00:00',  19.00, 'processato', 3);

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
