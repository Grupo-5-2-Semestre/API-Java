create database trackio;
use trackio;

create table Empresa(
idEmpresa int Primary Key auto_increment,
nomeEmpresa varchar(45),
emailEmpresa varchar(45),
telefoneEmpresa varchar(18),
cnpj varchar(18),
senhaEmpresa varchar(45)
);
insert into Empresa (nomeEmpresa,emailEmpresa,telefoneEmpresa,cnpj,senhaEmpresa) values 
("C6BANK","c6bank@gmail.com", "1126987630", "12345678901234", "#Urubu100"),
("E&Y", "ey@gmail.com", "1120930585", "98765432101234", "#Urubu200");
create table Usuario(
idUsuario int Primary Key auto_increment,
nomeUsuario varchar(45),
emailUsuario varchar(45),
senhaUsuario varchar(45),
fkEmpresa int not null,
foreign key(fkEmpresa) references Empresa(idEmpresa)
);
insert into Usuario (nomeUsuario,emailUsuario,senhaUsuario,fkEmpresa) values
("Marcos", "marcos.roberto@c6bank.com", "#Gf2022", 1),
("Roberto", "roberto.marcos@ey.com.br", "#Gf2023", 2);
create table Funcionario(
idFuncionario int Primary Key auto_increment,
nomeFuncionario varchar(45),
emailFuncionario varchar(45),
telefoneFuncionario varchar(15),
fkUsuario int not null,
foreign key(fkUsuario) references Usuario(idUsuario)
);
insert into Funcionario (nomeFuncionario,emailFuncionario,telefoneFuncionario,fkUsuario) values
("Ana Clara", "ana.clara@c6bank.com", "1125665255", 2),
("Clara Ana", "clara.ana@ey.com.br", "1120930585", 1);
create table Maquina(
 idMaquina int Primary Key auto_increment,
 numeroSerie varchar(20),
 marca varchar(30),
 fkFuncionario int not null,
 foreign key(fkFuncionario) references Funcionario(idFuncionario)
);
insert into Maquina (numeroSerie,marca,fkFuncionario) values
("F600", "Samsung", 2),
("E200", "Dell", 1);
create table Componente(
idComponente int Primary Key auto_increment,
nomeComponente varchar(45)
);
insert into Componente (nomeComponente) values
("CPU"),
("memoria RAM");
create table ComponenteMaquina (
 idMaquinaComponete int Primary Key auto_increment,
 fkComponente int not null,
 fkMaquina int not null,
 statusMC int not null,
 foreign key(fkComponente) references Componente(idComponente),
foreign key(fkMaquina) references Maquina(idMaquina)
);
insert into ComponenteMaquina (fkComponente,fkMaquina,statusMC) values
(1,2,3),
(2,1,4);
create table TiposValores (
 idTipoValor int Primary Key auto_increment,
 descIndicador varchar(50) not null
);
insert into TiposValores (descIndicador) values
("%"),
("GM");
create table LogMaquina(
medicao decimal(5,2),
dataHora datetime default current_timeStamp,
fkComponente int,
fkMaquina int,
foreign key(fkComponente) references Componente(idComponente),
foreign key(fkMaquina) references Maquina(idMaquina)
);
insert into LogMaquina (medicao,fkComponente,fkMaquina) values
("10.2", 1, 2),
("12.4", 2, 1);
