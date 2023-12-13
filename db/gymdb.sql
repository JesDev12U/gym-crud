create database GYM;
use GYM;

create table Pagos(
    ID_Pago int PRIMARY KEY auto_increment,
    Desc_Pago varchar(30) not null,
    Fec_Pago date not null,
    Form_Pago varchar(10) not null,
    Mont_Pago double not null
);

create table Inscripciones(
    ID_Ins int PRIMARY KEY auto_increment,
    Fec_Ins date not null,
    ID_Pago int not null,
    constraint fk_ID_Pago1 foreign key (ID_Pago) references Pagos (ID_Pago)
);

create table Clientes(
    ID_Cli varchar(5) PRIMARY KEY,
    Nom_Cli varchar(30) not null,
    ApPat_Cli varchar(15) not null,
    ApMat_Cli varchar(15) not null,
    Est_Cli varchar(15) not null,
    ID_Ins int, -- Cambio aquí eliminando el not null
    constraint fk_ID_Ins1 foreign key (ID_Ins) references Inscripciones (ID_Ins)
);

create table Visitas(
    ID_Vis varchar(5) PRIMARY KEY,
    Nom_Vis varchar(30) not null,
    ApPat_Vis varchar(15) not null,
    ApMat_Vis varchar(15) not null,
    ID_Pago int not null,
    constraint fk_ID_Pago2 foreign key (ID_Pago) references Pagos (ID_Pago)
);

create table Mensualidades(
    ID_Men int PRIMARY KEY auto_increment,
    ID_Cli varchar(5) not null,
    ID_Pago int not null,
    constraint fk_ID_Cli1 foreign key (ID_Cli) references Clientes (ID_Cli),
    constraint fk_ID_Pago3 foreign key (ID_Pago) references Pagos (ID_Pago) -- Cambio aquí en el nombre de la llave*/
);

create table ClienteSemanal(
    ID_Sem varchar(5) PRIMARY KEY,
    Nom_Sem varchar(30) not null,
    ApPat_Sem varchar(15) not null,
    ApMat_Sem varchar(15) not null,
    Est_Sem varchar(15) not null,
    ID_Pago int not null,
    constraint fk_ID_Pago4 foreign key (ID_Pago) references Pagos (ID_Pago) -- Cambio aquí en el nombre de la llave*/
);

create table Inventario(
    ID_Prod int PRIMARY KEY AUTO_INCREMENT,
    T_Prod varchar(10) not null,
    Nom_Prod varchar(30) not null,
    Pre_Prod double not null,
    UE_Prod int not null,
    Est_Prod bool not null
);

create table Ventas(
    ID_Venta int PRIMARY KEY auto_increment,
    ID_Pago int not null,
    constraint fk_ID_Pago5 foreign key (ID_Pago) references Pagos (ID_Pago), -- Cambio aquí en el nombre de la llave*/
    Hor_Venta datetime not null
);

create table DetalleDeLaVenta(
    ID_Venta int not null,
    constraint fk_ID_Venta1 foreign key (ID_Venta) references Ventas (ID_Venta),
    ID_Prod int not null,
    constraint fk_ID_Prod1 foreign key (ID_Prod) references Inventario (ID_Prod),
    Cantidad_V int not null,
    Importe_V double not null
);

create table InscripcionKick(
    ID_Kick int PRIMARY KEY auto_increment,
    Nom_Kick varchar(30) not null,
    ApPat_Kick varchar(15) not null,
    ApMat_Kick varchar(15) not null,
    FecNac_Kick date not null,
    Clase_Kick varchar(5) not null,
    Tel_Kick varchar(10) not null,
    ID_Pago int not null,
    constraint fk_ID_Pago7 foreign key (ID_Pago) references Pagos (ID_Pago)
);

create table PagoKick(
    ID_PagoK int PRIMARY KEY auto_increment,
    ID_Kick int, -- Cambio aquí eliminando el PRIMARY KEY
    constraint fk_ID_Kick1 foreign key (ID_Kick) references InscripcionKick (ID_Kick),
    ID_Pago int not null,
    constraint fk_ID_Pago6 foreign key (ID_Pago) references Pagos (ID_Pago),
    FecVig_PagoK date not null
);

create table pagosemanales(
	ID_PSem int PRIMARY KEY auto_increment,
    ID_CliSem varchar(5) not null,
    ID_Pago int not null,
    constraint fk_ID_Cli1 foreign key (ID_CliSem) references ClienteSemanal (ID_CliSem),
    constraint fk_ID_Pago3 foreign key (ID_Pago) references Pagos (ID_Pago)
);