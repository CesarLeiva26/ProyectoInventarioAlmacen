USE  bdinventarioalmacen;

CREATE TABLE  rol (
	idRol INT PRIMARY KEY AUTO_INCREMENT, 
	nombreRol varchar(20)
);

CREATE TABLE  usuario (
	idUsuario INT PRIMARY KEY AUTO_INCREMENT, 
	idRol int ,
	nombre varchar(60),
	apellidos varchar(60),
	usuario varchar (60),
	correo varchar (60),
	telefono varchar(10),
	FOREIGN KEY (idRol) REFERENCES rol(idRol)

);

CREATE TABLE unidad	(
 idUnidad INT PRIMARY KEY AUTO_INCREMENT ,
 descripcion varchar(30)
);

CREATE TABLE  producto (
  idProducto INT PRIMARY KEY AUTO_INCREMENT ,
  descripcion VARCHAR(255),
  idUnidad INT,
  foreign key (idunidad) references unidad(idUnidad),
  envase VARCHAR(50),
  peso DECIMAL(10, 2)
);

CREATE TABLE  entidad (
    IdEntidad INT auto_increment PRIMARY KEY,
     Entidad varchar(15) ,
     nombreEntidad varchar(40),
     tipo varchar(30),
     direccion varchar(30),
     correo varchar(50),
     telefono varchar(10),
     notas varchar(100)
) ;

INSERT INTO entidad (Entidad, nombreEntidad, tipo, direccion, correo, telefono, notas)
VALUES 
    (20100039207, 'RANSA COMERCIAL S.A.C', 'CLIENTE', 'Av. Primera 123', 'ransa@gmail.com', '123456789', 'Notas de RANSA COMERCIAL'),
    (20100070546, 'REICOLITE PERUANA S.A.', 'CLIENTE', 'Calle Segunda 456', 'reicolite@gmail.com', '987654321', 'Notas de REICOLITE PERUANA'),
    (20100141583, 'FARMEX S A', 'CLIENTE', 'Jr. Tercera 789', 'farmex@hotmail.com', '555555555', 'Notas de FARMEX S.A.'),
    (20100244471, 'SOCIEDAD ANONIMA FAUSTO PIAGGIO', 'CL', 'Av. Cuarta 987', 'fausto.piaggio@gmail.com', '111111111', 'Notas de SOCIEDAD ANONIMA FAUSTO PIAGGIO'),
    (20100277213, 'EUROPLAST S.A.C.', 'CLIENTE', 'Calle Quinta 654', 'europlast@gmail.com', '222222222', 'Notas de EUROPLAST S.A.C.'),
    (20100334624, 'BRENNTAG PERU S.A.C', 'CLIENTE', 'Jr. Sexta 321', 'brenntag@yahoo.com', '333333333', 'Notas de BRENNTAG PERU S.A.C.'),
    (20100370001, 'JULIA SEVILLA SA', 'CLIENTE', 'Av. Séptima 654', 'julia.sevilla@gmail.com', '444444444', 'Notas de JULIA SEVILLA SA'),
    (20100402727, 'UNITRADE S.A.C.', 'PROVEEDOR', 'Calle Octava 321', 'unitrade@hotmail.com', '555555555', 'Notas de UNITRADE S.A.C.'),
    (20101637221, 'HORTUS S A', 'PROVEEDOR', 'Jr. Novena 987', 'hortus@yahoo.com', '666666666', 'Notas de HORTUS S.A.');
   

CREATE TABLE sucursal (
	idSucursal INT PRIMARY KEY AUTO_INCREMENT ,
	nomSucursal VARCHAR(100) NOT NULL,
	idEntidad int,
	notas VARCHAR(100),
	FOREIGN KEY (idEntidad) REFERENCES entidad (idEntidad)
);


CREATE TABLE lote(
	idLote  INT PRIMARY KEY AUTO_INCREMENT,
	lote varchar(15),
	fechaFab datetime,
	dechaVen datetime 
);

CREATE table ubicacion (
	idUbicacion INT PRIMARY KEY AUTO_INCREMENT,
	ubicacion VARCHAR(25),
 	fechaCreacion DATE
)


CREATE TABLE estado (
    idEstado INT PRIMARY KEY AUTO_INCREMENT,
    nomEstado VARCHAR(50)
);



CREATE TABLE stock (
	idStock INT PRIMARY KEY AUTO_INCREMENT,
	idProducto INT ,
	idUbicacion int,
	idLote int,
	cantidad INT,
	idEstado INT,
	FOREIGN KEY (idProducto) REFERENCES producto (idProducto),
	FOREIGN KEY (idUbicacion) REFERENCES ubicacion (idUbicacion),
	FOREIGN KEY (idLote) REFERENCES lote (idLote),
	FOREIGN KEY (idEstado) REFERENCES estado (idEstado)
);

CREATE TABLE recepcion (
    idRecepcion INT PRIMARY KEY AUTO_INCREMENT,
    tipoMov VARCHAR(50) NULL,
    fechaMov DATE NULL,
    docResp VARCHAR(10) NULL,
    numDocResp VARCHAR(15) NULL,
    idEntidad int NULL,
    idSucursal INT NULL,
    notas VARCHAR(100) NULL,
    FOREIGN KEY (idEntidad) REFERENCES entidad (idEntidad),
	FOREIGN KEY (idSucursal) REFERENCES sucursal (idSucursal)
);
 	

CREATE TABLE detalleRecepcion (
    idDetalleRe INT PRIMARY KEY AUTO_INCREMENT,
    idRecepcion INT NULL,
    idProducto INT(15) NULL,
    idLote int,
    idUbicacion int,
    cantidad INT NULL,
	ubicacion VARCHAR(15) NULL,
    pallet VARCHAR(15) NULL,
	idEstado INT NULL,
    FOREIGN KEY (idRecepcion) REFERENCES recepcion (idRecepcion),
	FOREIGN KEY (idProducto) REFERENCES producto (idProducto),
	FOREIGN KEY (idLote) REFERENCES lote (idLote),
	FOREIGN KEY (idUbicacion) REFERENCES ubicacion (idUbicacion),
	FOREIGN KEY (idEstado) REFERENCES estado (idEstado)
);
 

INSERT INTO UNIDAD ( descripcion)
VALUES ('Kilogramo'),
       ('Mililitro'),
       ('Litro');

INSERT INTO PRODUCTO ( descripcion, idUnidad, envase, peso)
VALUES ('Fertilizante orgánico líquido', 2, 'Botella de 500 ml', 0.75),
       ( 'Insecticida natural en polvo', 1, 'Bolsa de 1 kg', 1.0),
       ('Fungicida sistémico',2, 'Frasco de 250 ml', 0.3),
       ( 'Estimulador de raíces', 3, 'Botella de 1 litro', 1.2),
       ( 'Nutriente foliar con micronutrientes',2, 'Botella de 750 ml', 0.9);
