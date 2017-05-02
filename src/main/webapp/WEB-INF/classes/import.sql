INSERT INTO Role (id, name) VALUES (1, 'ADMIN');
INSERT INTO Role (id, name) VALUES (2, 'LANGILE');
INSERT INTO Role (id, name) VALUES (3, 'KONT');

INSERT INTO OperationType (id, name) VALUES (1,'Produktiboa');
INSERT INTO OperationType (id, name) VALUES (2,'Inproduktiboa');

INSERT INTO Operation (id, name, operationTypeId) VALUES (1, 'Programazioa', 1);
INSERT INTO Operation (id, name, operationTypeId) VALUES (2, 'Formakuntza', 2);
INSERT INTO Operation (id, name, operationTypeId) VALUES (3, 'Bilera internoa', 2);
INSERT INTO Operation (id, name, operationTypeId) VALUES (4, 'Bilera bezeroarekin', 2);

INSERT INTO Client (id, name, contactName, email, phone) VALUES (1,'Irizar S. Coop.','Edurne Sanjuan','esanjuan@irizar.com','987987555');

INSERT INTO Material (id, name) VALUES (1,'Toshiba 1TB Disko externoa');
INSERT INTO Material (id, name) VALUES (2,'Modem Zyxel 984B');

INSERT INTO Section (id, name) VALUES (1, 'Zuzendaritza');
INSERT INTO Section (id, name) VALUES (2, 'Garapena');
INSERT INTO Section (id, name) VALUES (3, 'Web');
INSERT INTO Section (id, name) VALUES (4, 'SAT');

INSERT INTO Worker (id, email, hourCost, lastName1, lastName2, name, password, roleId, sectionId) VALUES (1,'jasudupe@elkarmedia.com',60,'Sudupe','Unanue','Jose Antonio','elkarmedia',1,1);
INSERT INTO Worker (id, email, hourCost, lastName1, lastName2, name, password, roleId, sectionId) VALUES (2,'jon@elkarmedia.com',30,'Arriaga','Zangitu','Jon','elkarmedia',2,2);