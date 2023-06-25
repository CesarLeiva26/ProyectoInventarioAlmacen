CREATE TABLE usuarioauth (
    idusuario INT AUTO_INCREMENT PRIMARY KEY,
    nomusuario VARCHAR(100),
    email VARCHAR(200),
    password VARCHAR(300),
    nombres VARCHAR(100),
    apellidos VARCHAR(100),
    activo BOOLEAN
);

CREATE TABLE rolauth (
    idrol INT AUTO_INCREMENT PRIMARY KEY,
    nomrol VARCHAR(300)
);

insert rolauth(nomrol)values("ADMIN")

CREATE TABLE usuario_rolauth (
    idusuario INT NOT NULL,
    idrol INT NOT NULL,
    PRIMARY KEY (idusuario, idrol),
    FOREIGN KEY (idusuario) REFERENCES usuarioauth (idusuario),
    FOREIGN KEY (idrol) REFERENCES rolauth (idrol)
);



CREATE TABLE rol (
  idRol int AUTO_INCREMENT PRIMARY KEY,
  nombreRol varchar(20) NOT NULL
);

CREATE TABLE usuario (
  idUsuario int AUTO_INCREMENT PRIMARY KEY,
  idRol int not null,
  nombre varchar(60) not null,
  apellidos varchar(60) not null,
  usuario varchar(60)not null,
  correo varchar(60),
  telefono varchar(10),
  FOREIGN KEY (idRol) REFERENCES rol(idRol)
);

CREATE TABLE entidad (
  idEntidad int AUTO_INCREMENT PRIMARY KEY,
  entidad varchar(15) not null,
  nombreEntidad varchar(40) not null,
  tipo varchar(30) not null,
  direccion varchar(30) not null,
  correo varchar(50) null,
  telefono varchar(10) null,
  notas varchar(100) null
);

CREATE TABLE sucursal (
  idSucursal int AUTO_INCREMENT PRIMARY KEY,
  nomSucursal varchar(100) NOT NULL,
  idEntidad int NOT NULL,
  notas varchar(100) NULL,
  FOREIGN KEY (idEntidad) REFERENCES entidad (idEntidad)
);

CREATE TABLE estado (
  idEstado int AUTO_INCREMENT PRIMARY KEY,
  nomEstado varchar(50) not null
);

CREATE TABLE lote (
  idLote int AUTO_INCREMENT PRIMARY KEY,
  lote varchar(15) not null,
  fechaFab datetime not null,
  fechaVen datetime
);

CREATE TABLE unidad (
  idUnidad int AUTO_INCREMENT PRIMARY KEY,
  descripcion varchar(30) not null
);

CREATE TABLE producto (
  idProducto int AUTO_INCREMENT PRIMARY KEY,
  producto varchar(20) not null,
  descripcion varchar(255) not null,
  idUnidad int not null,
  envase varchar(50) not null,
  peso decimal(10,2),
  FOREIGN KEY (idUnidad) REFERENCES unidad(idUnidad)
);

CREATE TABLE ubicacion (
  idUbicacion int AUTO_INCREMENT PRIMARY KEY,
  ubicacion varchar(25),
  fechaCreacion date
);

CREATE TABLE stock (
  idStock int AUTO_INCREMENT PRIMARY KEY,
  idProducto int,
  idUbicacion int,
  idLote int,
  cantidad int,
  idEstado int,
  FOREIGN KEY (idProducto) REFERENCES producto (idProducto),
  FOREIGN KEY (idUbicacion) REFERENCES ubicacion (idUbicacion),
  FOREIGN KEY (idLote) REFERENCES lote (idLote),
  FOREIGN KEY (idEstado) REFERENCES estado (idEstado)
);

CREATE TABLE recepcion (
  idRecepcion int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  tipoMov varchar(50),
  fechaMov date,
  idEntidad int,
  idSucursal int,
  docResp varchar(10),
  numDocResp varchar(15),
  notas varchar(100),
  FOREIGN KEY (idEntidad) REFERENCES entidad(idEntidad),
  FOREIGN KEY (idSucursal) REFERENCES sucursal(idSucursal)
);

CREATE TABLE detallerecepcion (
  idDetalleRe int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  idRecepcion int,
  idProducto int,
  cantidad int,
  idUbicacion int,
  idLote int,
  idEstado int,
  FOREIGN KEY (idRecepcion) REFERENCES recepcion(idRecepcion),
  FOREIGN KEY (idProducto) REFERENCES producto(idProducto),
  FOREIGN KEY (idUbicacion) REFERENCES ubicacion(idUbicacion),
  FOREIGN KEY (idLote) REFERENCES lote(idLote),
  FOREIGN KEY (idEstado) REFERENCES estado(idEstado)
);

INSERT INTO rol (nombreRol) VALUES 
('Administrador'),
('Recepcionista'),
('Almacenero'),
('Sectorista');

INSERT INTO usuario (idRol, nombre, apellidos, usuario, correo, telefono) VALUES
(1, 'Cristiano', 'Santos', 'CR7', 'Crisantos@gmail.com', '123456789'),
(2, 'Lionel', 'Messi', 'LM10', 'Liomessi@gmail.com', '987654321'),
(3, 'Neymar', 'Santos', 'NS10', 'Neysantos@gmail.com', '123456789');

INSERT INTO entidad (entidad, nombreEntidad, tipo, direccion, correo, telefono, notas) VALUES 
    ('20100039207', 'RANSA COMERCIAL S.A.C', 'CLIENTE', 'Av. Primera 123', 'ransa@gmail.com', '123456789', 'Notas de RANSA COMERCIAL'),
    ('20100070546', 'REICOLITE PERUANA S.A.', 'PROVEEDOR', 'Calle Segunda 456', 'reicolite@gmail.com', '987654321', 'Notas de REICOLITE PERUANA'),
    ('20100141583', 'FARMEX S A', 'CLIENTE', 'Jr. Tercera 789', 'farmex@hotmail.com', '555555555', 'Notas de FARMEX S.A.'),
    ('20100244471', 'SOCIEDAD ANONIMA FAUSTO PIAGGIO', 'CLIENTE', 'Av. Cuarta 987', 'fausto.piaggio@gmail.com', '111111111', 'Notas de SOCIEDAD ANONIMA FAUSTO PIAGGIO'),
    ('20100277213', 'EUROPLAST S.A.C.', 'CLIENTE', 'Calle Quinta 654', 'europlast@gmail.com', '222222222', 'Notas de EUROPLAST S.A.C.'),
    ('20100334624', 'BRENNTAG PERU S.A.C', 'CLIENTE', 'Jr. Sexta 321', 'brenntag@yahoo.com', '333333333', 'Notas de BRENNTAG PERU S.A.C.'),
    ('20100370001', 'JULIA SEVILLA SA', 'CLIENTE', 'Av. Séptima 654', 'julia.sevilla@gmail.com', '444444444', 'Notas de JULIA SEVILLA SA'),
    ('20100402727', 'UNITRADE S.A.C.', 'CLIENTE', 'Calle Octava 321', 'unitrade@hotmail.com', '555555555', 'Notas de UNITRADE S.A.C.'),
    ('20101637221', 'HORTUS S A', 'CLIENTE', 'Jr. Novena 987', 'hortus@yahoo.com', '666666666', 'Notas de HORTUS S.A.'),
    ('20101655394', 'INDUSTRIAS NIKO S A', 'CLIENTE', 'Av. Décima 123', 'niko.industrias@gmail.com', '777777777', 'Notas de INDUSTRIAS NIKO S.A.'),
    ('20103272964', 'EMPRESA AGRICOLA SAN JUAN S.A', 'PROVEEDOR', 'Calle Once 456', 'sanjuan.agricola@hotmail.com', '888888888', 'Notas de EMPRESA AGRICOLA SAN JUAN'),
    ('20103941980', 'CONSULTORES VETERINARIOS Y AGRICOLAS SRL', 'PROVEEDOR', 'Jr. Doce 789', 'consultoresvetagri@gmail.com', '999999999', 'Notas de CONSULTORES VETERINARIOS Y AGRICOLAS'),
    ('20104121374', 'PALMAS DEL ESPINO S.A.', 'PROVEEDOR', 'Av. Trece 987', 'palmas.espino@gmail.com', '101010101', 'Notas de PALMAS DEL ESPINO S.A.'),
    ('20104420282', 'AGROINDUSTRIAS AIB S.A', 'PROVEEDOR', 'Calle Catorce 321', 'agroindustrias.aib@yahoo.com', '121212121', 'Notas de AGROINDUSTRIAS AIB S.A.'),
    ('20104860762', 'AUTOSERVICIO SAN ISIDRO S.A', 'PROVEEDOR', 'Jr. Quince 654', 'autoservicio.sanisidro@hotmail.com', '131313131', 'Notas de AUTOSERVICIO SAN ISIDRO S.A.'),
    ('20104902864', 'EXPORTADORA FRUTICOLA DEL SUR SA', 'PROVEEDOR', 'Av. Dieciséis 321', 'exportadora.fruticola@gmail.com', '141414141', 'Notas de EXPORTADORA FRUTICOLA DEL SUR'),
    ('20106109401', 'SAN ISIDRO REPRESENTACIONES S A', 'PROVEEDOR', 'Calle Diecisiete 987', 'sanisro.representaciones@yahoo.com', '151515151', 'Notas de SAN ISIDRO REPRESENTACIONES S.A.');

INSERT INTO sucursal (nomSucursal, idEntidad, notas) VALUES
  ('Sucursal A', 5, 'Notas de la Sucursal A'),
  ('Sucursal B', 5, 'Notas de la Sucursal B'),
  ('Sucursal C', 1, 'Notas de la Sucursal C'),
  ('Sucursal D', 4, 'Notas de la Sucursal D'),
  ('Sucursal A', 7, 'Notas de la Sucursal A'),
  ('Sucursal B', 3, 'Notas de la Sucursal B'),
  ('Sucursal C', 2, 'Notas de la Sucursal C'),
  ('Sucursal D', 2, 'Notas de la Sucursal D'),
  ('Sucursal E', 6, 'Notas de la Sucursal E');
   
INSERT INTO estado (nomEstado) VALUES 
    ('DISPONIBLE'),
    ('NO DISPONIBLE'),
    ('DEVO DISPONIBLE'),
    ('EN PROCESO'),
    ('FINALIZADO'),
    ('PENDIENTE'),
    ('CANCELADO'),
    ('APROBADO'),
    ('RECHAZADO');
   
INSERT INTO lote (lote, fechaFab, fechaVen) values
	('AC22092590','2022-09-01 00:00:00','2025-09-01 00:00:00'),
	('FG210119-01','2021-01-01 00:00:00','2023-01-01 00:00:00'),
	('20200401','2022-11-11 00:00:00','2024-11-11 00:00:00'),
	('1007171523','2023-02-01 00:00:00','2025-02-01 00:00:00'),
	('1007171524','2023-02-01 00:00:00','2025-02-01 00:00:00'),
	('411129997','2021-03-03 00:00:00','2023-03-03 00:00:00'),
	('AC21071801','2021-07-07 00:00:00','2023-07-07 00:00:00'),
	('AD19060870','2019-08-30 00:00:00','2021-08-30 00:00:00'),
	('AA22020298','2022-02-01 00:00:00','2025-02-01 00:00:00'),
	('AA22102745','2022-10-01 00:00:00','2024-10-01 00:00:00'),
	('AA22123037','2022-12-01 00:00:00','2024-12-01 00:00:00'),
	('Q22T220572','2022-12-01 00:00:00','2024-12-01 00:00:00'),
	('22L001-01','2022-12-15 00:00:00','2024-12-14 00:00:00'),
	('42123293','2022-05-05 00:00:00','2024-05-05 00:00:00'),
	('TQ22T220061','2022-02-02 00:00:00','2024-02-02 00:00:00'),
	('TQ22T220158','2022-04-04 00:00:00','2024-04-04 00:00:00'),
	('12202200','2022-01-01 00:00:00','2024-01-01 00:00:00'),
	('AD19070965','2019-07-21 00:00:00','2021-07-21 00:00:00'),
	('AA22091042','2022-09-01 00:00:00','2024-09-01 00:00:00'),
	('AA22092575','2022-09-01 00:00:00','2024-09-01 00:00:00'),
	('Q23T230097','2023-02-01 00:00:00','2025-02-01 00:00:00'),
	('Q23T230151','2023-03-03 00:00:00','2025-03-03 00:00:00'),
	('Q23T230220','2023-04-04 00:00:00','2025-04-04 00:00:00'),
	('Q23T230152','2023-03-03 00:00:00','2025-03-03 00:00:00'),
	('Q23T230224','2023-04-04 00:00:00','2025-04-04 00:00:00'),
	('Q23T230150','2023-03-03 00:00:00','2025-03-03 00:00:00'),
	('TQ22T220157','2022-04-04 00:00:00','2024-04-04 00:00:00'),
	('Q22T220157','2022-04-04 00:00:00','2024-04-04 00:00:00'),
	('TQ22T220524','2022-11-01 00:00:00','2024-11-01 00:00:00'),
	('411920241','2021-11-01 00:00:00','2023-11-01 00:00:00'),
	('2001805844','2022-02-01 00:00:00','2024-02-01 00:00:00'),
	('AA23010145','2023-01-01 00:00:00','2025-01-01 00:00:00'),
	('AA23012678','2023-01-01 00:00:00','2025-01-01 00:00:00'),
	('SLP2K1909K','2022-10-01 00:00:00','2025-10-01 00:00:00'),
	('SLP2K1921K','2022-10-01 00:00:00','2025-10-01 00:00:00'),
	('SLP2J1893J','2022-10-01 00:00:00','2025-10-01 00:00:00'),
	('SLP2K1924K','2022-10-01 00:00:00','2025-10-01 00:00:00'),
	('SLP2J1888J','2022-10-01 00:00:00','2025-10-01 00:00:00'),
	('SLP2K1914K','2022-10-01 00:00:00','2025-10-01 00:00:00'),
	('SLP2K1925K','2022-10-01 00:00:00','2025-10-01 00:00:00'),
	('SLP2J1896J','2022-10-01 00:00:00','2025-10-01 00:00:00'),
	('SY19102003','2019-10-10 00:00:00','2021-10-10 00:00:00'),
	('SY20012014','2020-01-01 00:00:00','2022-01-01 00:00:00'),
	('SY20110221','2020-11-11 00:00:00','2022-11-11 00:00:00'),
	('SY22122013','2022-12-12 00:00:00','2024-12-12 00:00:00'),
	('SY22122012','2022-12-12 00:00:00','2024-12-12 00:00:00'),
	('SY22122011','2022-12-12 00:00:00','2024-12-12 00:00:00'),
	('AD20102046','2020-04-04 00:00:00','2023-04-04 00:00:00'),
	('RE20102046','2022-01-01 00:00:00','2024-01-01 00:00:00'),
	('RE19081152','2022-12-12 00:00:00','2024-12-12 00:00:00'),
	('AC22061594','2022-06-06 00:00:00','2024-06-06 00:00:00'),
	('AD20040597','2020-04-01 00:00:00','2022-04-01 00:00:00'),
	('AA21061407','2021-06-01 00:00:00','2023-06-01 00:00:00'),
	('AA22112898','2022-11-11 00:00:00','2024-11-11 00:00:00'),
	('AA22051393','2022-05-01 00:00:00','2024-05-01 00:00:00'),
	('SCA1C21005','2021-03-03 00:00:00','2024-03-03 00:00:00'),
	('SCA1G21011','2022-05-01 00:00:00','2024-05-01 00:00:00'),
	('SCA1E21008','2022-07-01 00:00:00','2024-07-01 00:00:00');

INSERT INTO unidad (descripcion) VALUES
('GRAMO'),
('KILOGRAMO'),
('LITRO'),
('MILILITRO'),
('UNIDAD'),
('SACO');

INSERT INTO producto (producto, descripcion, idUnidad, envase, peso) VALUES
	('LHP001480','AMIGAN 50 SC PROFPERU GFA X 200 LT', 1, 'BIDON', 1.2),
	('LHP002331','ATRAMET COMBI 50 SC PROFPERU COEX X 1 LT', 1, 'BIDON', 1.2),
	('LHP002404','ATRAMET COMBI 50 SC PROFPERU GFA X 4 L', 1, 'BOTELLA', 1.5),
	('LHP002480','ATRAMET COMBI 50 SC PROFPERU GFA X 200 L', 1, 'BOTELLA', 2.2),
	('LHP003321','COTTONEX 50 SC PROFPERU BIDON X 1 LT', 3, 'BOTELLA', 2.2),
	('LHP004054','GALIGAN 240 EC PROFPERU COEX X 250 CC', 1, 'BOTELLA', 1.2),
	('LHP004331','GALIGAN 240 EC PROFPERU COEX X 1 LT', 2, 'BOTELLA', 1.2),
	('LHP004424','GALIGAN 240 EC PROFPERU COEX X 4 LT', 2, 'BIDON', 2.2),
	('LHP005321','GLYPHOGAN 48 SL PROFPERU BIDON X 1 LT', 3, 'BIDON', 1.2),
	('LHP005405','GLYPHOGAN 48 SL PROFPERU GFA X 5 LITROS', 1, 'BIDON', 1.2),
	('LHP005420','GLYPHOGAN 48 SL PROFPERU GFA X 20 LITROS', 1, 'BIDON', 1.2),
	('LHP005480','GLYPHOGAN 48 SL PROFPERU GFA X 200 LT', 3, 'BIDON', 1.2),
	('LHP005505','GLYPHOGAN 48 SL GAL X 5 LITROS AK', 1, 'BIDON', .2),
	('LHP005580','GLYPHOGAN 48 SL DRUM X 200 LT AK', 1, 'BIDON', 1.2),
	('LHP006321','GLYPHOGAN 48 SL FRA PET X 1 LT AK', 1, 'BIDON', 1.2),
	('LHP006331','IGUANA 20 SL PROFPERU COEX X 1 LT', 4, 'BIDON', 1.2),
	('LHP006405','IGUANA 20 SL PROFPERU COEX X 5 LT', 3, 'BIDON', 1.5),
	('LHP006480','IGUANA 20 SL PROFPERU GFA X 200 LT', 1, 'BIDON', 1.2),
	('LHP00732','AFALON 500 SC X 1LITRO ', 1, 'BIDON', 1.2),
	('LHP007321','AFALON 500 SC PROFPERU BIDON X 1 LT', 1, 'BIDON', 1.5),
	('LHP007420','AFALON 500 SC PROFPERU GFA X 20 LT', 1, 'BIDON', 1.2),
	('LHP008305','AGIL 100 EC PROFPERU COEX X 500 CC', 5, 'BIDON', 1.2),
	('LHP008331','AGIL 100 EC PROFPERU COEX X 1 LT', 1, 'BOTELLA', 1.2),
	('LHP009331','AMETREX 50 SC PROFPERU X 1 LT', 1, 'BOTELLA', 1.2),
	('LHP009424','AMETREX 50 SC PROFPERU X 4 LT ', 6, 'BIDON', 1.5),
	('LHP009480','AMETREX 50 SC PROFPERU GFA X 200 LT', 1, 'BOTELLA', 1.2),
	('LHP009525','AMETREX 50 SC PROFPERU COEX X 5 LT', 1, 'BIDON', 1.2),
	('LHP010331','ATRANEX 50 SC PROFPERU COEX X 1 LT', 1, 'BOTELLA', 1.2),
	('LHP010424','ATRANEX 50 SC PROFPERU COEX X 4 LT ', 1, 'BOTELLA', 1.2),
	('LHP010480','ATRANEX 50 SC PROFPERU GFA X 200 LT', 3, 'BIDON', 1.2),
	('LHP010525','ATRANEX 50 SC  PROFPERU COEX X 5 LS', 1, 'BIDON', 1.2),
	('LHP011321','LINUREX 50 SC PROFPERU BIDON X 1 LT', 1, 'BIDON', 1.2),
	('LHP012424','TRIFLUREX 48 EC PROFPERU X 4 LT', 3, 'BIDON', 1.2),
	('LHP012700','TRIFLUREX 48 EC PROFPERU TBR x 200 LT', 1, 'BIDON', 1.2),
	('LHP013420','BURNER 150 SL PROFPERU GFA X 20 LT', 1, 'BIDON', 1.2),
	('LHP013424','BURNER 150 SL PROFPERU COEX X 4 LT', 1, 'BIDON', 1.2),
	('LHP015404','DEFERON 720 SL PROFPERU GFA X 4 LT', 5, 'BIDON', 1.2),
	('LHP015405','DEFERON 720 SL PROFPERU X 5 LT', 1, 'BIDON', 1.2),
	('LHP015420','DEFERON 720 SL PROFPERU GFA X 20 LT', 1, 'BIDON', 1.2);

INSERT INTO ubicacion (ubicacion, fechaCreacion) VALUES
    ('01-01-001-RA1', '2023-05-22'),
    ('01-01-001-RA2', '2023-05-21'),
    ('01-01-001-RE1', '2023-05-20'),
    ('01-01-001-RF1', '2023-05-19'),
    ('01-01-001-RF2', '2023-05-18'),
    ('01-01-002-RB1', '2023-05-17'),
    ('01-01-002-RB2', '2023-05-16'),
    ('01-01-002-RC1', '2023-05-15'),
    ('01-01-002-RE1', '2023-05-14'),
    ('01-01-002-RE2', '2023-05-13'),
    ('01-01-002-RF1', '2023-05-12'),
    ('01-01-002-RF2', '2023-05-11'),
    ('01-01-003-RA1', '2023-05-10'),
    ('01-01-003-RA2', '2023-05-09'),
    ('01-01-003-RB1', '2023-05-08'),
    ('01-01-003-RB2', '2023-05-07'),
    ('01-01-003-RC1', '2023-05-06'),
    ('01-01-003-RC2', '2023-05-05'),
    ('01-01-012-RB2', '2023-03-27'),
    ('01-01-012-RE1', '2023-03-26'),
    ('01-01-012-RE2', '2023-03-25'),
    ('01-01-012-RF2', '2023-03-24'),
    ('01-01-013-RA1', '2023-03-23'),
    ('01-01-013-RA2', '2023-03-22'),
    ('01-01-013-RB1', '2023-03-21'),
    ('01-01-013-RB2', '2023-03-20'),
    ('01-01-013-RC1', '2023-03-19'),
    ('01-01-013-RC2', '2023-03-18'),
    ('01-01-014-RC2', '2023-03-17'),
    ('01-01-014-RD1', '2023-03-16'),
    ('01-01-014-RD2', '2023-03-15'),
    ('01-01-014-RE1', '2023-03-14'),
    ('01-01-014-RF1', '2023-03-13'),
    ('01-01-014-RF2', '2023-03-12');

INSERT INTO stock (idProducto, idUbicacion, idLote, cantidad, idEstado) VALUES
    (8, 3, 8, 200, 4),
    (8, 6, 1, 200, 4),
    (8, 1, 2, 200, 4),
    (8, 8, 2, 200, 4),
    (8, 2, 8, 200, 4),
    (9, 4, 3, 100, 7),
    (9, 6, 3, 100, 7),
    (9, 1, 3, 100, 7),
    (9, 2, 1, 100, 7),
    (9, 4, 3, 100, 7),
   (10, 6, 2, 200, 4),
    (11, 8, 4, 200, 4),
    (12, 3, 5, 200, 4),
    (15, 2, 3, 100, 7),
    (15, 2, 1, 200, 4),
    (15, 8, 4, 200, 4),
    (17, 2, 3, 100, 7),
    (17, 2, 1, 200, 4),
    (17, 8, 4, 200, 4),
    (19, 2, 3, 100, 7),
    (19, 2, 1, 200, 4),
    (19, 8, 4, 200, 4),
    (20, 2, 3, 100, 7),
    (20, 2, 1, 200, 4),
    (20, 8, 4, 200, 4);


 INSERT INTO recepcion (tipoMov, fechaMov, idEntidad, idSucursal, docResp, numDocResp, notas) VALUES 
    ('Recepción Proveedor', '2023-05-23', 7, 1, 'GR', '003-012834', 'Notas de la recepción 1'),
    ('Recepción Proveedor', '2023-05-24', 6, 2, 'GR','003-045464', 'Notas de la recepción 2'),
    ('Recepción Proveedor', '2023-05-25', 1, 3, 'GR','003-057943', 'Notas de la recepción 3'),
    ('Recepción Aduanera', '2023-05-26', 5, 2, 'DUA','118-07928303', 'Notas de la recepción 4'),
    ('Recepción Aduanera', '2023-05-27', 3, 3, 'DUA','118-02827492', 'Notas de la recepción 5');
   
INSERT INTO detallerecepcion (idRecepcion, idProducto, cantidad, idUbicacion, idLote, idEstado) VALUES
    (1, 15, 50, 1, 1, 1),
    (1, 10, 80, 2, 1, 1),
    (2, 1, 8, 1, 2, 1),
    (2, 3, 12, 3, 1, 1),
    (3, 23, 70, 2, 3, 1),
    (4, 12, 20, 2, 3, 1),
   (5, 19, 34, 2, 3, 1);

  



-- PROCEDURES
REATE PROCEDURE SpInsertarlote(IN lote VARCHAR(15), IN fechaFab DATETIME)
BEGIN
    DECLARE fechaVen DATETIME;
    SET fechaVen = DATE_ADD(fechaFab, INTERVAL 2 YEAR);
    INSERT INTO lote (lote, fechaFab, fechaVen)
    VALUES (lote, fechaFab, fechaVen);
END



CREATE PROCEDURE ST_GUARDAR_RECEPCION(
    IN tipomov VARCHAR(50),
    IN fechamov DATE,
    IN identidad INT,
    IN idsucursal INT,
    IN docresp VARCHAR(50),
    IN numdocresp VARCHAR(15),
    IN notas VARCHAR(100),
    IN detallesrecepcion JSON
)
BEGIN
    DECLARE idRecepcion INT;
    DECLARE totalDetalles INT;
    DECLARE i INT DEFAULT 0;
    DECLARE detalle JSON;

    -- Manejo de errores
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SELECT 'Error al guardar la recepción. Se realizó rollback.' AS message;
    END;

    START TRANSACTION;

    -- Inserción en la tabla recepcion
    INSERT INTO recepcion (tipomov, fechamov, identidad, idsucursal, docresp, numdocresp, notas)
    VALUES (tipomov, fechamov, identidad, idsucursal, docresp, numdocresp, notas);

    SET idRecepcion = LAST_INSERT_ID();
    SET totalDetalles = JSON_LENGTH(detallesrecepcion);

    WHILE i < totalDetalles DO
        -- Obtener los datos de cada detalle
        SET detalle = JSON_EXTRACT(detallesrecepcion, CONCAT('$[', i, ']'));
        SET @idProducto = JSON_EXTRACT(detalle, '$.idproducto');
        SET @idUbicacion = JSON_EXTRACT(detalle, '$.idubicacion');
        SET @idLote = JSON_EXTRACT(detalle, '$.idlote');
        SET @idEstado = JSON_EXTRACT(detalle, '$.idestado');
        SET @entrada = JSON_EXTRACT(detalle, '$.cantidad');

        -- Validar si el registro existe en la tabla stock
        SET @registroExistente = (SELECT COUNT(*) FROM stock WHERE idproducto = @idProducto AND idubicacion = @idUbicacion AND idlote = @idLote);

        IF @registroExistente > 0 THEN
            -- Actualización de la tabla stock
            UPDATE stock
            SET cantidad = cantidad + CAST(@entrada AS SIGNED)
            WHERE idproducto = @idProducto AND idubicacion = @idUbicacion AND idlote = @idLote;
        ELSE
            -- Inserción de un nuevo registro en la tabla stock
            INSERT INTO stock (idproducto, idubicacion, idlote, idestado, cantidad)
            VALUES (@idProducto, @idUbicacion, @idLote, @idEstado, @entrada);
        END IF;

        -- Inserción en la tabla detallerecepcion
        INSERT INTO detallerecepcion (idrecepcion, idproducto, idubicacion, idlote, idestado, cantidad)
        VALUES (idRecepcion, @idProducto, @idUbicacion, @idLote, @idEstado, @entrada);

        SET i = i + 1;
    END WHILE;

    COMMIT;
    SELECT 'Recepción guardada correctamente' AS message;
END
 




