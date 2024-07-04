DROP DATABASE IF EXISTS `Stellar`;

CREATE database IF NOT EXISTS `Stellar` ;

USE `Stellar` ;

-- -----------------------------------------------------
-- Table  `Stellar`.`Usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Usuarios`(
  `IdUsuario` INT NOT NULL AUTO_INCREMENT,
  `Senha` VARCHAR(45) NOT NULL,
  `Login` VARCHAR(45) NOT NULL,
  `Tipo` TINYINT NOT NULL,
  PRIMARY KEY (`IdUsuario`));

-- -----------------------------------------------------
-- Table `Stellar`.`Hospedes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Hospedes`(
  `IdHospede` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(45) NOT NULL,
  `Sobrenome` VARCHAR(45) NOT NULL,
  `DataNasc` DATE NOT NULL,
  `Documento` VARCHAR(45) NOT NULL,
  `Nacionalidade` VARCHAR(45) NOT NULL,
  `Pronome` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `IdUsuarioHospede` INT NOT NULL,
  PRIMARY KEY (`IdHospede`, `IdUsuarioHospede`),
 
  CONSTRAINT `fk_Hospedes_Usuarios1`
    FOREIGN KEY (`IdUsuarioHospede`)
    REFERENCES `Stellar`.`Usuarios`(`IdUsuario`)
    )
;


-- -----------------------------------------------------
-- Table `Stellar`.`Servicos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS  `Servicos` (
  `IdServico` INT NOT NULL AUTO_INCREMENT,
  `PrecoServico` FLOAT NOT NULL,
  `NomeServico` VARCHAR(45) NOT NULL,
  `Quantidade` INT NOT NULL,
  PRIMARY KEY (`IdServico`))
;


-- -----------------------------------------------------
-- Table `Stellar`.`Hospedagens`
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Table `Stellar`.`HospedagensQuartos`
-- -----------------------------------------------------
-- CREATE TABLE  IF NOT EXISTS `HospedagensQuartos` (
--  `IdHospedagemQuarto` INT NOT NULL AUTO_INCREMENT,
--  `IdQuarto` INT NOT NULL,
--  `IdHospedagem` INT NOT NULL,
--  `IdHospede` INT NOT NULL,
--  PRIMARY KEY (`IdHospedagemQuarto`, `IdQuarto`, `IdHospedagem`, `IdHospede`),
--
--  CONSTRAINT `fk_Hospedagens_has_Quartos_Quartos1`
--    FOREIGN KEY (`IdQuarto`)
--    REFERENCES `Stellar`.`Quartos` (`IdQuarto`)
--   ,
--  CONSTRAINT `fk_HospedagensQuartos_Hospedagens1`
--    FOREIGN KEY (`IdHospedagem`)
--    REFERENCES `Stellar`.`Hospedagens` (`IdHospedagem`)
--    ,
--  CONSTRAINT `fk_HospedagensQuartos_Hospedes1`
--    FOREIGN KEY (`IdHospede`)
--    --REFERENCES `Stellar`.`Hospedes` (`IdHospede`)
  --  )
-- ;

-- -----------------------------------------------------
-- Table `Stellar`.`Quartos`
-- -----------------------------------------------------
CREATE TABLE  IF NOT EXISTS `Quartos` (
  `IdQuarto` INT NOT NULL AUTO_INCREMENT,
  `TipoQuarto` INT,
  `MaxPessoas` INT NOT NULL,
  `Manutencao` VARCHAR(45) NOT NULL,
  `TipoCama` VARCHAR(45) NOT NULL,
  `Frigobar` TINYINT NOT NULL,
  `ArCondicionado` TINYINT NOT NULL,
  `Banheira` TINYINT NOT NULL,
  `TV` TINYINT NOT NULL,
  `PrecoDiaria` FLOAT NOT NULL,
  PRIMARY KEY (`IdQuarto`))
;
CREATE TABLE  IF NOT EXISTS `Hospedagens` (
  `IdHospedagem` INT NOT NULL AUTO_INCREMENT,
  `Checkin` DATE NOT NULL,
  `Checkout` DATE NOT NULL,
  `IdQuartoHospedagens` INT NOT NULL,
  `IdHospedeHospedagens` INT NOT NULL,  
  PRIMARY KEY (`IdHospedagem`),  
  CONSTRAINT `fk_Quartos_Quartos1`
    FOREIGN KEY (`IdQuartoHospedagens`)
    REFERENCES `Stellar`.`Quartos` (`IdQuarto`)
    ,

  CONSTRAINT `fk_Hospedagens_Hospedes1`
    FOREIGN KEY (`IdHospedeHospedagens`)
    REFERENCES `Stellar`.`Hospedes` (`IdHospede`)
  );


-- -----------------------------------------------------
-- Table `Stellar`.`Funcionarios`
-- -----------------------------------------------------
CREATE TABLE  IF NOT EXISTS `Funcionarios` (
  `IdFuncionario` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(45) NOT NULL,
  `Sobrenome` VARCHAR(45) NOT NULL,
  `E-mail` VARCHAR(45) NOT NULL,
  `Telefone` INT NOT NULL,
  `Pronomes` VARCHAR(45) NOT NULL,
  `Setor` VARCHAR(45) NOT NULL,
  `Funcao` VARCHAR(45) NOT NULL,
  `Salario` FLOAT NOT NULL,
  `IdUsuarioFuncionario` INT NOT NULL,
  `CPF` VARCHAR(45) NOT NULL,
  `NivelDeAcesso` INT NOT NULL,
  PRIMARY KEY (`IdFuncionario`,`IdUsuarioFuncionario`),
  CONSTRAINT `fk_Funcionarios_Usuarios1`
    FOREIGN KEY (`IdUsuarioFuncionario`)
    REFERENCES `Stellar`.`Usuarios`(`IdUsuario`)
    )
;


-- -----------------------------------------------------
-- Table `Stellar`.`Atividades`
-- -----------------------------------------------------
CREATE TABLE  IF NOT EXISTS `Atividades` (
  `IdAtividade` INT NOT NULL AUTO_INCREMENT,
  `Horario` VARCHAR(45) NOT NULL,
  `HorarioFim` VARCHAR(45) NOT NULL,
  `IdFuncionarioAtividade` INT NOT NULL,
  `IdadeMinima` INT NOT NULL,
  `NomeAtividade` VARCHAR(45) NOT NULL,
  `Data` DATE NOT NULL,
  `Capacidade` INT NOT NULL,
  PRIMARY KEY (`IdAtividade`, `IdFuncionarioAtividade`),

  CONSTRAINT `fk_Atividades_Funcionarios1`
    FOREIGN KEY (`IdFuncionarioAtividade`)
    REFERENCES `Stellar`.`Funcionarios` (`IdFuncionario`)
   )
;


-- -----------------------------------------------------
-- Table `Stellar`.`ServicosConsumidos`
-- -----------------------------------------------------
CREATE TABLE  IF NOT EXISTS `ServicosConsumidos`(
  `IdServicoConsumido` INT NOT NULL AUTO_INCREMENT,
  `IdHospedeServicos` INT NOT NULL,
  `IdServicoServicos` INT NOT NULL,
  PRIMARY KEY (`IdServicoConsumido`, `IdHospedeServicos`, `IdServicoServicos`),

  CONSTRAINT `fk_Hospedes_has_Servicos_Hospedes1`
    FOREIGN KEY (`IdHospedeServicos`)
    REFERENCES `Stellar`.`Hospedes` (`IdHospede`)
   ,
  CONSTRAINT `fk_Hospedes_has_Servicos_Servicos1`
    FOREIGN KEY (`IdServicoServicos`)
    REFERENCES `Stellar`.`Servicos` (`IdServico`)
    )
;

-- -----------------------------------------------------
-- Table `Stellar`.`AtividadesHospedes`
-- -----------------------------------------------------
CREATE TABLE  IF NOT EXISTS `AtividadesHospedes` (
  `IdAtividadesHospedes` INT NOT NULL AUTO_INCREMENT,
  `IdHospedeAtividades` INT NOT NULL,
  `IdAtividadeAtividades` INT NOT NULL,
  PRIMARY KEY (`IdAtividadesHospedes`, `IdHospedeAtividades`, `IdAtividadeAtividades`),

  CONSTRAINT `fk_Hospedes_has_AtividadesHospedess1`
    FOREIGN KEY (`IdHospedeAtividades`)
    REFERENCES `Stellar`.`Hospedes` (`IdHospede`)
    ,
  CONSTRAINT `fk_Hospedes_has_Atividades_Atividades1`
    FOREIGN KEY (`IdAtividadeAtividades`)
    REFERENCES `Stellar`.`Atividades` (`IdAtividade`)
    )
;

/*Tabela Usuarios*/

insert into Usuarios ( Senha, Login, Tipo)
values ('M@iones3','MAZDARX7',0 ),
('eido','Cralos Ícaro',0 ),
('Geromel','Andrei',0 ),
('Bernas','Mico',0 ),
('blaze', 'FelipeNeto',0 ),
('Azul', 'Smurfette',0 ),
('Gorro', 'PapaSmurf',0 ),
('Azar', 'Desastrado',0 ),
('Bobo','Joca',0 ),
('Forte','Robusto',0 ),
('Ruiva', 'Sassette' ,0),
('Burro', 'Gênio',0 ),
('Feliz', 'Ranzinza',0 ),
('Horrível', 'Habilidoso',0 ),
('Bonitão', 'Vaidoso',0 ),
('Véio', 'VovôSmurf',0 ),
('Bonzinho', 'DevilSmurf',0 ),
('Bom', 'Tuffy',0 ),
('Rastreio', 'Caçador',0 ),
('Véia', 'VovóSmurf',0 ),
    ('Admin', 'Admin',1 ),
    ('Gabriel', 'Gabriel',1 ),
    ('Bernardo', 'Bernardo',1 ),
    ('Andrei', 'Andrei',1 ),
    ('Erik', 'Erik',1 ),
('João', 'Monark',0 ),
('Mau', 'Ele',0 ),
('Pinas', 'Gustas',0 ),
('Erik', 'Roncas',0 ),
('Roxo', 'William',0 ),
('SpringTrap', 'Afton',0 ),
('Azu', 'Marill',0 ),
('Kan', 'Gaskan',0 ),
('Drago', 'Nite',0 ),
('Stara', 'Ptor',0 ),
('Burro', 'Dragão',0 ),
('Ogro', 'Shrek',0 ),
('Princesa', 'Fiona',0 ),
('Rei', 'Leônidas',0 ),
('MelhorCidade', 'Esparta',0 ),
('Filósofo', 'Aristóteles',0 ),
('Inteligente', 'Newtown',0 ),
('Balela', 'Zero',0 ),
('Cowboy', 'JohnMarston',0 ),
('RDR2', 'ArthurMorgan',0 ),
('Mago', 'DavyJones',0 ),
('Carrara', 'Augustinho',0 ),
('Only', 'Beiçola',0 );
 
 

/*Tabela Hóspedes*/
-- insert into Hospedes (Nome, Sobrenome, DataNasc, Documento, Nacionalidade, Pronome, Email,IdUsuarioHospede) values ('Stacee', 'Glisenan', '1986-09-01', '607.332.182-77', 'Uganda', 'Genderfluid', 'sglisenan0@mail.ru',1);
-- insert into Hospedes (Nome, Sobrenome, DataNasc, Documento, Nacionalidade, Pronome, Email,IdUsuarioHospede) values ('Elisa', 'Volette', '1984-11-06', '751.425.013-76', 'Peru', 'Female', 'evolette1@stanford.edu',2);
-- insert into Hospedes (Nome, Sobrenome, DataNasc, Documento, Nacionalidade, Pronome, Email,IdUsuarioHospede) values ('Maxwell', 'Haycraft', '1977-02-16', '316.149.686-40', 'Brazil', 'Male', 'mhaycraft2@wikispaces.com',3);
-- insert into Hospedes (Nome, Sobrenome, DataNasc, Documento, Nacionalidade, Pronome, Email,IdUsuarioHospede) values ('Gussy', 'Fries', '2003-10-16', '395.415.201-14', 'China', 'Female', 'gfries3@stumbleupon.com',4);
-- insert into Hospedes (Nome, Sobrenome, DataNasc, Documento, Nacionalidade, Pronome, Email,IdUsuarioHospede) values ('Duff', 'Mungham', '2023-09-21', '805.097.764-25', 'Philippines', 'Male', 'dmungham4@histats.com',5);
-- insert into Hospedes (Nome, Sobrenome, DataNasc, Documento, Nacionalidade, Pronome, Email,IdUsuarioHospede) values ('Kristo', 'Honnan', '2019-02-21', '636.015.493-06', 'Mongolia', 'Male', 'khonnan5@ovh.net',6);
-- insert into Hospedes (Nome, Sobrenome, DataNasc, Documento, Nacionalidade, Pronome, Email,IdUsuarioHospede) values ('Teodoor', 'Skeels', '2008-05-23', '421.381.619-25', 'Sweden', 'Male', 'tskeels6@themeforest.net',7);
-- insert into Hospedes (Nome, Sobrenome, DataNasc, Documento, Nacionalidade, Pronome, Email,IdUsuarioHospede) values ('Malachi', 'Barnewille', '1999-04-14', '427.559.825-55', 'France', 'Male', 'mbarnewille7@newyorker.com',8);
-- insert into Hospedes (Nome, Sobrenome, DataNasc, Documento, Nacionalidade, Pronome, Email,IdUsuarioHospede) values ('Wallis', 'Bree', '1990-12-15', '636.068.749-13', 'Norway', 'Female', 'wbree8@instagram.com',9);
-- insert into Hospedes (Nome, Sobrenome, DataNasc, Documento, Nacionalidade, Pronome, Email,IdUsuarioHospede) values ('Marius', 'Bratton', '1994-10-31', '298.583.262-13', 'Brazil', 'Male', 'mbratton9@mashable.com',10);
-- insert into Hospedes (Nome, Sobrenome, DataNasc, Documento, Nacionalidade, Pronome, Email,IdUsuarioHospede) values ('Obadiah', 'Aylesbury', '2017-04-14', '129.346.434-18', 'China', 'Male', 'oaylesburya@europa.eu',11);
-- insert into Hospedes (Nome, Sobrenome, DataNasc, Documento, Nacionalidade, Pronome, Email,IdUsuarioHospede) values ('Wright', 'Alkin', '1980-09-19', '949.735.668-02', 'Yemen', 'Male', 'walkinb@surveymonkey.com',12);
-- insert into Hospedes (Nome, Sobrenome, DataNasc, Documento, Nacionalidade, Pronome, Email,IdUsuarioHospede) values ('Rikki', 'Youson', '1994-06-10', '833.407.132-18', 'Thailand', 'Male', 'ryousonc@vk.com',13);
-- insert into Hospedes (Nome, Sobrenome, DataNasc, Documento, Nacionalidade, Pronome, Email,IdUsuarioHospede) values ('Benedicto', 'Hodgin', '1970-12-16', '198.397.145-67', 'Iran', 'Male', 'bhodgind@photobucket.com',14);
-- insert into Hospedes (Nome, Sobrenome, DataNasc, Documento, Nacionalidade, Pronome, Email,IdUsuarioHospede) values ('Lennie', 'Lembrick', '1975-06-23', '882.476.072-06', 'Pakistan', 'Male', 'llembricke@un.org',15);
-- insert into Hospedes (Nome, Sobrenome, DataNasc, Documento, Nacionalidade, Pronome, Email,IdUsuarioHospede) values ('Eachelle', 'Gonoude', '1998-09-26', '685.862.163-47', 'China', 'Female', 'egonoudef@is.gd',16);
-- insert into Hospedes (Nome, Sobrenome, DataNasc, Documento, Nacionalidade, Pronome, Email,IdUsuarioHospede) values ('Freddie', 'Millott', '2012-06-22', '363.678.979-99', 'Greece', 'Female', 'fmillottg@xing.com',17);
-- insert into Hospedes (Nome, Sobrenome, DataNasc, Documento, Nacionalidade, Pronome, Email,IdUsuarioHospede) values ('Had', 'Babar', '2015-08-13', '269.188.281-56', 'Russia', 'Male', 'hbabarh@addtoany.com',18);
-- insert into Hospedes (Nome, Sobrenome, DataNasc, Documento, Nacionalidade, Pronome, Email,IdUsuarioHospede) values ('Amanda', 'Harbach', '1979-10-08', '700.716.283-32', 'United States', 'Female', 'aharbachi@google.fr',19);
-- insert into Hospedes (Nome, Sobrenome, DataNasc, Documento, Nacionalidade, Pronome, Email,IdUsuarioHospede) values ('Lurlene', 'Crampsy', '1966-01-08', '480.398.712-27', 'Bosnia and Herzegovina', 'Female', 'lcrampsyj@tiny.cc',20);
-- 
-- /*Tabela funcionarios*/
-- 
 insert into Funcionarios (Nome, Sobrenome, Funcao, Salario, IdUsuarioFuncionario, NivelDeAcesso,CPF) values ('Admin', 'Admin', 'Admininastro', 9526.14, 21,6,'000.000.000-01');
 insert into Funcionarios (Nome, Sobrenome, Funcao, Salario, IdUsuarioFuncionario, NivelDeAcesso,CPF) values ('Gabriel', 'Mohr', 'Dev', 9016.38, 22,6,'000.000.000-02');
 insert into Funcionarios (Nome, Sobrenome, Funcao, Salario, IdUsuarioFuncionario, NivelDeAcesso,CPF) values ('Bernardo', 'Oliveira', 'Dev', 7291.94, 23,6,'000.000.000-03');
 insert into Funcionarios (Nome, Sobrenome, Funcao, Salario, IdUsuarioFuncionario, NivelDeAcesso,CPF) values ('Andrei', 'Ludwig', 'Dev', 5890.71, 24,6,'000.000.000-04');
 insert into Funcionarios (Nome, Sobrenome, Funcao, Salario, IdUsuarioFuncionario, NivelDeAcesso,CPF) values ('Erik', 'Roncaglio', 'Dev', 6530.11, 25,6,'000.000.000-05');
-- insert into Funcionarios (Nome, Sobrenome, Funcao, Salario, IdUsuarioFuncionario, NivelDeAcesso,CPF) values ('Kym', 'Wakerley', 'Zeladora', 1477.57, 26,0,'00000000006');
-- insert into Funcionarios (Nome, Sobrenome, Funcao, Salario, IdUsuarioFuncionario, NivelDeAcesso,CPF) values ('Aymer', 'Tunbridge', 'Recepcionista', 5917.57, 27,0,'00000000007');
-- insert into Funcionarios (Nome, Sobrenome, Funcao, Salario, IdUsuarioFuncionario, NivelDeAcesso,CPF) values ('Lindie', 'Kaygill', 'Jardineira', 8184.42, 28,0,'00000000008');
-- insert into Funcionarios (Nome, Sobrenome, Funcao, Salario, IdUsuarioFuncionario, NivelDeAcesso,CPF) values ('Suzi', 'Rickarsey', 'Jardineira Specialist IV', 4905.58, 29,0,'00000000009');
-- insert into Funcionarios (Nome, Sobrenome, Funcao, Salario, IdUsuarioFuncionario, NivelDeAcesso,CPF) values ('Jonell', 'Devine', 'Garçom', 2276.33, 30,0,'00000000010');
-- insert into Funcionarios (Nome, Sobrenome, Funcao, Salario, IdUsuarioFuncionario, NivelDeAcesso,CPF) values ('Mae', 'McNess', 'Garçom', 9164.21, 31,0,'00000000011');
-- insert into Funcionarios (Nome, Sobrenome, Funcao, Salario, IdUsuarioFuncionario, NivelDeAcesso,CPF) values ('Moishe', 'McCrea', 'Secretário', 6212.93, 32,0,'00000000012');
-- insert into Funcionarios (Nome, Sobrenome, Funcao, Salario, IdUsuarioFuncionario, NivelDeAcesso,CPF) values ('Winny', 'Butterworth', 'Secretário', 2643.54, 33,0,'00000000013');
-- insert into Funcionarios (Nome, Sobrenome, Funcao, Salario, IdUsuarioFuncionario, NivelDeAcesso,CPF) values ('Wynn', 'MacAllester', 'Gerente', 9734.61, 34,0,'00000000014');
-- insert into Funcionarios (Nome, Sobrenome, Funcao, Salario, IdUsuarioFuncionario, NivelDeAcesso,CPF) values ('Charmaine', 'Chilcott', 'camareira', 3291.06, 35,0,'00000000015');
-- insert into Funcionarios (Nome, Sobrenome, Funcao, Salario, IdUsuarioFuncionario, NivelDeAcesso,CPF) values ('Hortense', 'Dwyr', 'Diarista', 7856.25, 36,0,'00000000016');
-- insert into Funcionarios (Nome, Sobrenome, Funcao, Salario, IdUsuarioFuncionario, NivelDeAcesso,CPF) values ('Chadwick', 'Ewen', 'Faxineiro', 7086.47, 37,0,'00000000017');
-- insert into Funcionarios (Nome, Sobrenome, Funcao, Salario, IdUsuarioFuncionario, NivelDeAcesso,CPF) values ('Tally', 'Liffe', 'Diarista', 5948.04, 38,0,'00000000018');
-- insert into Funcionarios (Nome, Sobrenome, Funcao, Salario, IdUsuarioFuncionario, NivelDeAcesso,CPF) values ('Letisha', 'Huggon', 'Diarista', 9692.93, 39,0,'00000000019');
-- insert into Funcionarios (Nome, Sobrenome, Funcao, Salario, IdUsuarioFuncionario, NivelDeAcesso,CPF) values ('Mose', 'Jozwicki', 'Diarista', 2530.39, 40,0,'00000000020');
-- 
-- /*Tabela Quartos*/
-- 
-- 
-- 
-- /*Tabela hospedagens*/
-- 
-- insert into Hospedagens (Checkin, Checkout, IdQuartoHospedagens, IdHospedeHospedagens) values ('2023-01-28', '2023-10-29', 1,1);
-- insert into Hospedagens (Checkin, Checkout, IdQuartoHospedagens, IdHospedeHospedagens) values ('2022-11-29', '2023-05-27', 2,1);
-- insert into Hospedagens (Checkin, Checkout, IdQuartoHospedagens, IdHospedeHospedagens) values ('2023-08-08', '2023-06-28', 3,1);
-- insert into Hospedagens (Checkin, Checkout, IdQuartoHospedagens, IdHospedeHospedagens) values ('2022-06-04', '2023-04-23', 4,1);
-- insert into Hospedagens (Checkin, Checkout, IdQuartoHospedagens, IdHospedeHospedagens) values ('2023-06-30', '2023-11-18', 5,1);
-- insert into Hospedagens (Checkin, Checkout, IdQuartoHospedagens, IdHospedeHospedagens) values ('2022-06-11', '2023-01-06', 6,1);
-- insert into Hospedagens (Checkin, Checkout, IdQuartoHospedagens, IdHospedeHospedagens) values ('2023-06-21', '2023-08-04', 7,1);
-- insert into Hospedagens (Checkin, Checkout, IdQuartoHospedagens, IdHospedeHospedagens) values ('2023-04-30', '2023-09-11', 8,1);
-- insert into Hospedagens (Checkin, Checkout, IdQuartoHospedagens, IdHospedeHospedagens) values ('2022-08-06', '2023-08-28', 9,1);
-- insert into Hospedagens (Checkin, Checkout, IdQuartoHospedagens, IdHospedeHospedagens) values ('2023-03-10', '2023-06-29', 10,1);
-- insert into Hospedagens (Checkin, Checkout, IdQuartoHospedagens, IdHospedeHospedagens) values ('2022-02-24', '2023-05-10', 11,1);
-- insert into Hospedagens (Checkin, Checkout, IdQuartoHospedagens, IdHospedeHospedagens) values ('2023-01-08', '2023-06-21', 12,1);
-- insert into Hospedagens (Checkin, Checkout, IdQuartoHospedagens, IdHospedeHospedagens) values ('2022-12-23', '2022-12-28', 13,1);
-- insert into Hospedagens (Checkin, Checkout, IdQuartoHospedagens, IdHospedeHospedagens) values ('2023-05-07', '2023-07-09', 14,1);
-- insert into Hospedagens (Checkin, Checkout, IdQuartoHospedagens, IdHospedeHospedagens) values ('2023-02-28', '2022-12-24', 15,1);
-- insert into Hospedagens (Checkin, Checkout, IdQuartoHospedagens, IdHospedeHospedagens) values ('2022-09-15', '2023-01-22', 16,1);
-- insert into Hospedagens (Checkin, Checkout, IdQuartoHospedagens, IdHospedeHospedagens) values ('2022-12-14', '2023-06-10', 17,1);
-- insert into Hospedagens (Checkin, Checkout, IdQuartoHospedagens, IdHospedeHospedagens) values ('2022-03-06', '2023-03-24', 18,1);
-- insert into Hospedagens (Checkin, Checkout, IdQuartoHospedagens, IdHospedeHospedagens) values ('2022-05-31', '2023-05-11', 19,1);
-- insert into Hospedagens (Checkin, Checkout, IdQuartoHospedagens, IdHospedeHospedagens) values ('2022-02-07', '2023-05-30', 20,1);
-- 
-- /*Tabela Atividades */
-- 
-- insert into Atividades (Horario, HorarioFim, IdadeMinima, NomeAtividade, data, IdFuncionarioAtividade, Capacidade) values ('8:08 AM', '10:05 AM', 9, 'Passeio a cavalo com guia', '2023-09-02', 1,20);
-- insert into Atividades (Horario, HorarioFim, IdadeMinima, NomeAtividade, data, IdFuncionarioAtividade, Capacidade) values ('9:10 AM', '4:55 AM', 4, 'Passeio de buggy', '2022-07-27', 2,20);
-- insert into Atividades (Horario, HorarioFim, IdadeMinima, NomeAtividade, data, IdFuncionarioAtividade, Capacidade) values ('10:57 AM', '11:59 AM', 12, 'Pesca', '2022-09-23', 3,20);
-- insert into Atividades (Horario, HorarioFim, IdadeMinima, NomeAtividade, data, IdFuncionarioAtividade, Capacidade) values ('11:20 AM', '12:56 AM', 4, 'Trilha de offroad 4x4', '2023-10-01', 4,20);
-- insert into Atividades (Horario, HorarioFim, IdadeMinima, NomeAtividade, data, IdFuncionarioAtividade, Capacidade) values ('6:41 AM', '10:00 AM', 15, 'Trilha a pé com guia', '2023-12-07', 5,20);
-- insert into Atividades (Horario, HorarioFim, IdadeMinima, NomeAtividade, data, IdFuncionarioAtividade, Capacidade) values ('9:40 AM', '10:27 AM', 7, 'Alpinismo', '2022-10-27', 6,20);
-- insert into Atividades (Horario, HorarioFim, IdadeMinima, NomeAtividade, data, IdFuncionarioAtividade, Capacidade) values ('10:03 AM', '4:06 AM', 15, 'Surf', '2023-12-20', 7,20);
-- insert into Atividades (Horario, HorarioFim, IdadeMinima, NomeAtividade, data, IdFuncionarioAtividade, Capacidade) values ('11:15 AM', '6:37 AM', 10, 'Natação', '2022-08-13', 8,20);
-- insert into Atividades (Horario, HorarioFim, IdadeMinima, NomeAtividade, data, IdFuncionarioAtividade, Capacidade) values ('8:17 AM', '2:32 AM', 11, 'Futebol', '2023-01-19', 9,20);
-- insert into Atividades (Horario, HorarioFim, IdadeMinima, NomeAtividade, data, IdFuncionarioAtividade, Capacidade) values ('9:55 AM', '11:47 AM', 12, 'Vôlei', '2022-08-09', 10,20);
-- insert into Atividades (Horario, HorarioFim, IdadeMinima, NomeAtividade, data, IdFuncionarioAtividade, Capacidade) values ('11:12 AM', '1:06 AM', 4, 'Hipismo', '2023-09-21', 11,20);
-- insert into Atividades (Horario, HorarioFim, IdadeMinima, NomeAtividade, data, IdFuncionarioAtividade, Capacidade) values ('10:03 AM', '1:37 AM', 14, 'Luau', '2022-02-06', 12,20);
-- insert into Atividades (Horario, HorarioFim, IdadeMinima, NomeAtividade, data, IdFuncionarioAtividade, Capacidade) values ('11:08 AM', '2:46 AM', 15, 'Yoga', '2023-06-28', 13,20);
-- insert into Atividades (Horario, HorarioFim, IdadeMinima, NomeAtividade, data, IdFuncionarioAtividade, Capacidade) values ('10:40 AM', '8:08 AM', 8, 'Oficina de artesanato', '2022-11-29', 14,20);
-- insert into Atividades (Horario, HorarioFim, IdadeMinima, NomeAtividade, data, IdFuncionarioAtividade, Capacidade) values ('7:45 AM', '6:54 AM', 13, 'Oficina de jardinagem', '2023-01-18', 15,20);
-- insert into Atividades (Horario, HorarioFim, IdadeMinima, NomeAtividade, data, IdFuncionarioAtividade, Capacidade) values ('9:24 AM', '9:55 AM', 14, 'Caça ao tesouro', '2023-02-06', 16,20);
-- insert into Atividades (Horario, HorarioFim, IdadeMinima, NomeAtividade, data, IdFuncionarioAtividade, Capacidade) values ('10:31 AM', '5:12 AM', 6, 'Passeio de trator', '2022-11-27', 17,20);
-- insert into Atividades (Horario, HorarioFim, IdadeMinima, NomeAtividade, data, IdFuncionarioAtividade, Capacidade) values ('7:09 AM', '1:52 AM', 18, 'Visita ao mirante', '2022-05-23', 18,20);
-- insert into Atividades (Horario, HorarioFim, IdadeMinima, NomeAtividade, data, IdFuncionarioAtividade, Capacidade) values ('11:07 AM', '9:40 AM', 17, 'Teatro ao ar livre', '2022-11-01', 19,20);
-- insert into Atividades (Horario, HorarioFim, IdadeMinima, NomeAtividade, data, IdFuncionarioAtividade, Capacidade) values ('11:36 AM', '1:20 AM', 16, 'Pratica de cerâmica com guia', '2022-08-17', 20,20);
-- 
-- /*Tabela Servicos*/
-- 
-- insert into Servicos (PrecoServico, NomeServico, Quantidade) values (249.96, 'Massagemn',2);
-- insert into Servicos (PrecoServico, NomeServico, Quantidade) values (260.5, 'Frigobar',2);
-- insert into Servicos (PrecoServico, NomeServico, Quantidade) values (129.43, 'Máquina de venda',2);
-- insert into Servicos (PrecoServico, NomeServico, Quantidade) values (93.83, 'Café da manhã',2);
-- insert into Servicos (PrecoServico, NomeServico, Quantidade) values (89.98, 'Sala de jogos',2);
-- insert into Servicos (PrecoServico, NomeServico, Quantidade) values (108.29, 'Internet',2);
-- insert into Servicos (PrecoServico, NomeServico, Quantidade) values (117.71, 'TV a cabo',2);
-- insert into Servicos (PrecoServico, NomeServico, Quantidade) values (257.47, 'Aluguel de filmes',2);
-- insert into Servicos (PrecoServico, NomeServico, Quantidade) values (47.94, 'Ar condicionado',2);
-- insert into Servicos (PrecoServico, NomeServico, Quantidade) values (110.91, 'Kart',2);
-- insert into Servicos (PrecoServico, NomeServico, Quantidade) values (57.17, 'Aula de surf',2);
-- insert into Servicos (PrecoServico, NomeServico, Quantidade) values (56.23, 'Aula de hipismo',2);
-- insert into Servicos (PrecoServico, NomeServico, Quantidade) values (18.3, 'Academia',2);
-- insert into Servicos (PrecoServico, NomeServico, Quantidade) values (296.42, 'Festa Pagode',2);
-- insert into Servicos (PrecoServico, NomeServico, Quantidade) values (189.84, 'Bailão',2);
-- insert into Servicos (PrecoServico, NomeServico, Quantidade) values (246.18, 'Quiroprata',2);
-- insert into Servicos (PrecoServico, NomeServico, Quantidade) values (252.25, 'Sauna',2);
-- insert into Servicos (PrecoServico, NomeServico, Quantidade) values (229.48, 'Babás',2);
-- insert into Servicos (PrecoServico, NomeServico, Quantidade) values (184.17, 'Lavanderia',2);
-- insert into Servicos (PrecoServico, NomeServico, Quantidade) values (275.8, 'Aula de dança do ventre',2);
-- 
-- /*Tabela AtividadesHospedes*/
-- 
-- insert into AtividadesHospedes(IdHospedeAtividades, IdAtividadesHospedes,IdAtividadeAtividades)
-- values (1,1,1),
-- (2,2,2),
-- (3,3,3),
-- (4,4,4),
-- (5,5,5),
-- (6,6,6),
-- (7,7,7),
-- (8,8,8),
-- (9,9,9),
-- (10,10,10),
-- (11,11,11),
-- (12,12,12),
-- (13,13,13),
-- (14,14,14),
-- (15,15,15),
-- (16,16,16),
-- (17,17,17),
-- (18,18,18),
-- (19,19,19),
-- (20,20,20);
-- 
-- 
-- /*Tabela serviços consumidos*/
-- 
-- insert into ServicosConsumidos (IdHospedeServicos, IdServicoServicos, IdHospedagemServicos)
-- values(1,1,1),
-- (2,2,2),
-- (3,3,3),
-- (4,4,4),
-- (5,5,5),
-- (6,6,6),
-- (7,7,7),
-- (8,8,8),
-- (9,9,9),
-- (10,10,10),
-- (11,11,11),
-- (12,12,12),
-- (13,13,13),
-- (14,14,14),
-- (15,15,15),
-- (16,16,16),
-- (17,17,17),
-- (18,18,18),
-- (19,19,19),
-- (20,20,20);
-- 
-- /*Tabela HospedagensQuartos*/
-- 
-- -- insert into HospedagensQuartos (IdHospede, IdQuarto, IdHospedagem)
-- -- values(1,1,1),
-- -- (2,2,2),
-- -- (3,3,3),
-- -- (4,4,4),
-- -- (5,5,5),
-- -- (6,6,6),
-- -- (7,7,7),
-- -- (8,8,8),
-- -- (9,9,9),
-- -- (10,10,10),
-- -- (11,11,11),
-- -- (12,12,12),
-- -- (13,13,13),
-- -- (14,14,14),
-- -- (15,15,15),
-- -- (16,16,16),
-- -- (17,17,17),
-- -- (18,18,18),
-- -- (19,19,19),
-- -- (20,20,20);
-- 
insert into Quartos (MaxPessoas, Manutencao, TipoCama, Frigobar, ArCondicionado, Banheira, TV, PrecoDiaria,TipoQuarto) values (3, 0, 'Solteiro', 0, 0, 0, 1, 1907.04, 2);
insert into Quartos (MaxPessoas, Manutencao, TipoCama, Frigobar, ArCondicionado, Banheira, TV, PrecoDiaria,TipoQuarto) values (1, 0, 'Solteiro', 0, 0, 0, 1, 1484.32, 1);
insert into Quartos (MaxPessoas, Manutencao, TipoCama, Frigobar, ArCondicionado, Banheira, TV, PrecoDiaria,TipoQuarto) values (2, 0, 'Solteiro', 1, 0, 0, 0, 1102.29, 3);
insert into Quartos (MaxPessoas, Manutencao, TipoCama, Frigobar, ArCondicionado, Banheira, TV, PrecoDiaria,TipoQuarto) values (1, 1, 'Solteiro', 0, 1, 0, 1, 459.85, 2);
insert into Quartos (MaxPessoas, Manutencao, TipoCama, Frigobar, ArCondicionado, Banheira, TV, PrecoDiaria,TipoQuarto) values (3, 0, 'Solteiro', 0, 0, 0, 1, 1547.8, 1);
insert into Quartos (MaxPessoas, Manutencao, TipoCama, Frigobar, ArCondicionado, Banheira, TV, PrecoDiaria,TipoQuarto) values (2, 1, 'Solteiro', 0, 0, 1, 1, 449.5, 3);
insert into Quartos (MaxPessoas, Manutencao, TipoCama, Frigobar, ArCondicionado, Banheira, TV, PrecoDiaria,TipoQuarto) values (1, 1, 'Solteiro', 0, 0, 1, 1, 633.46, 2);
insert into Quartos (MaxPessoas, Manutencao, TipoCama, Frigobar, ArCondicionado, Banheira, TV, PrecoDiaria,TipoQuarto) values (1, 0, 'Solteiro', 0, 0, 0, 1, 1673.81, 1);
insert into Quartos (MaxPessoas, Manutencao, TipoCama, Frigobar, ArCondicionado, Banheira, TV, PrecoDiaria,TipoQuarto) values (3, 0, 'Solteiro', 0, 0, 0, 1, 1298.17, 3);
insert into Quartos (MaxPessoas, Manutencao, TipoCama, Frigobar, ArCondicionado, Banheira, TV, PrecoDiaria,TipoQuarto) values (4, 1, 'Solteiro', 1, 1, 1, 0, 1963.8, 2);
insert into Quartos (MaxPessoas, Manutencao, TipoCama, Frigobar, ArCondicionado, Banheira, TV, PrecoDiaria,TipoQuarto) values (2, 0, 'Casal', 1, 1, 1, 1, 508.69, 1);
insert into Quartos (MaxPessoas, Manutencao, TipoCama, Frigobar, ArCondicionado, Banheira, TV, PrecoDiaria,TipoQuarto) values (2, 1, 'Casal', 1, 0, 0, 0, 735.41, 3);
insert into Quartos (MaxPessoas, Manutencao, TipoCama, Frigobar, ArCondicionado, Banheira, TV, PrecoDiaria,TipoQuarto) values (2, 1, 'Casal', 0, 0, 1, 1, 1478.15, 1);
insert into Quartos (MaxPessoas, Manutencao, TipoCama, Frigobar, ArCondicionado, Banheira, TV, PrecoDiaria,TipoQuarto) values (4, 1, 'Casal', 0, 1, 1, 0, 765.21, 2);
insert into Quartos (MaxPessoas, Manutencao, TipoCama, Frigobar, ArCondicionado, Banheira, TV, PrecoDiaria,TipoQuarto) values (4, 1, 'Casal', 0, 0, 0, 1, 576.8, 2);
insert into Quartos (MaxPessoas, Manutencao, TipoCama, Frigobar, ArCondicionado, Banheira, TV, PrecoDiaria,TipoQuarto) values (2, 0, 'Casal', 1, 1, 0, 1, 773.33, 3);
insert into Quartos (MaxPessoas, Manutencao, TipoCama, Frigobar, ArCondicionado, Banheira, TV, PrecoDiaria,TipoQuarto) values (2, 0, 'Casal', 0, 1, 0, 0, 1593.7, 1);
insert into Quartos (MaxPessoas, Manutencao, TipoCama, Frigobar, ArCondicionado, Banheira, TV, PrecoDiaria,TipoQuarto) values (4, 0, 'Casal', 1, 0, 0, 1, 733.04, 3);
insert into Quartos (MaxPessoas, Manutencao, TipoCama, Frigobar, ArCondicionado, Banheira, TV, PrecoDiaria,TipoQuarto) values (5, 0, 'Casal', 0, 0, 0, 1, 387.28, 2);
insert into Quartos (MaxPessoas, Manutencao, TipoCama, Frigobar, ArCondicionado, Banheira, TV, PrecoDiaria,TipoQuarto) values (2, 1, 'Casal', 1, 0, 1, 0, 927.53, 1);
/*Selects*/

Select count(*) from Usuarios;
Select count(*) from Hospedes;
Select count(*) from Servicos;
Select count(*) from Hospedagens;
Select count(*) from Quartos;
Select count(*) from Funcionarios;
Select count(*) from Atividades;
Select count(*) from ServicosConsumidos;
Select count(*) from AtividadesHospedes;

/*Select ALL*/

select * from Funcionarios;
select * from Usuarios;
select * from Atividades;
select * from Hospedes;
select * from AtividadesHospedes;
select * from ServicosConsumidos;
select * from Servicos;
select * from Hospedagens;
select * from Quartos;



/*Select Join*/

-- SELECT * FROM Funcionarios INNER JOIN Usuarios ON Funcionarios.IdUsuarioFuncionario = Usuarios.IdUsuario;
-- 
-- SELECT * FROM Funcionarios INNER JOIN Atividades ON Funcionarios.IdFuncionario = Atividades.IdFuncionarioAtividade;
-- 
-- SELECT * FROM Usuarios INNER JOIN Hospedes ON Usuarios.IdUsuario = Hospedes.IdUsuarioHospede;
-- 
-- SELECT * FROM AtividadesHospedes INNER JOIN Hospedes ON AtividadesHospedes.IdHospedeAtividades = Hospedes.IdHospede INNER JOIN Atividades ON AtividadesHospedes.IdAtividadeAtividades = Atividades.IdAtividade;
-- 
-- SELECT * FROM Hospedagens INNER JOIN Hospedes ON Hospedagens.IdHospedagem = Hospedes.IdHospede;
-- 
-- SELECT * FROM ServicosConsumidos INNER JOIN Hospedes ON ServicosConsumidos.IdHospede = Hospedes.IdHospede INNER JOIN Servicos ON ServicosConsumidos.IdServico = Servicos.IdServico INNER JOIN Hospedagens ON ServicosConsumidos.IdHospedagem = Hospedagens.IdHospedagem;


/*Update funcionarios*/
-- update Funcionarios
-- set Nome = 'Miguel', Sobrenome = 'Almeida', Funcao = 'Zelador', Salario = 9016.38
-- where IdFuncionario=1;
-- update Funcionarios
-- set Nome = 'Junior', Sobrenome = 'Silva', Funcao = 'Camareiro', Salario = 9526.14
-- where IdFuncionario=2;
-- update Funcionarios
-- set Nome = 'Marcos', Sobrenome = 'Silva', Funcao = 'Recepcionista', Salario = 5890.71
-- where IdFuncionario=3;
-- update Funcionarios
-- set Nome = 'Romario', Sobrenome = 'Mendes', Funcao = 'Recepcionista', Salario = 5890.71
-- where IdFuncionario=4;
-- update Funcionarios
-- set Nome = 'Lucas', Sobrenome = 'Oliveira', Funcao = 'Diarista', Salario = 6212.93
-- where IdFuncionario=5;
-- update Funcionarios
-- set Nome = 'Sabrina', Sobrenome = 'Lima', Funcao = 'Secretaria', Salario = 6232.93
-- where IdFuncionario=6;
-- update Funcionarios
-- set Nome = 'Ana', Sobrenome = 'Amaral', Funcao = 'Gerente', Salario = 9734.61
-- where IdFuncionario=7;
-- update Funcionarios
-- set Nome = 'Rosalia', Sobrenome = 'Helena', Funcao = 'Jardineira', Salario = 3400.61
-- where IdFuncionario=8;
-- update Funcionarios
-- set Nome = 'lucio', Sobrenome = 'Carvalho', Funcao = 'Faxineiro', Salario = 4500.43
-- where IdFuncionario=9;
-- update Funcionarios
-- set Nome = 'Maria', Sobrenome = 'Eliza', Funcao = 'Recepcionista', Salario = 6200.23
-- where IdFuncionario=10;
-- 
-- /*Update Servicos*/
-- update Servicos
-- set PrecoServico= 250.00, NomeServico='aula de boxe'
-- where IdServico=1;
-- update Servicos
-- set PrecoServico= 10.00, NomeServico='prato do dia'
-- where IdServico=11;
-- update Servicos
-- set PrecoServico= 157.09, NomeServico='aula de capoeira'
-- where IdServico=7;
-- update Servicos
-- set PrecoServico= 243.11, NomeServico='aula de futebol'
-- where IdServico=12;
-- update Servicos
-- set PrecoServico= 143.33, NomeServico='pôker'
-- where IdServico=18;
-- update Servicos
-- set PrecoServico= 111.11, NomeServico='aula de karatê'
-- where IdServico=5;
-- update Servicos
-- set PrecoServico= 250.00, NomeServico='aula de kickbox'
-- where IdServico=9;
-- update Servicos
-- set PrecoServico= 250.00, NomeServico='piscina'
-- where IdServico=7;
-- update Servicos
-- set PrecoServico= 132.00, NomeServico='sauna'
-- where IdServico=19;
-- update Servicos
-- set PrecoServico= 250.00, NomeServico='bugy'
-- where IdServico=20;
-- 
-- /*Update Hospedagens*/
-- update Hospedagens
-- set Checkin = '2023-12-13', Checkout = '2023-12-16'
-- where  IdHospedagem = 1;
-- update Hospedagens
-- set Checkin = '2023-07-01', Checkout = '2023-07-07'
-- where  IdHospedagem = 2;
-- update Hospedagens
-- set Checkin = '2023-02-09', Checkout = '2023-02-14'
-- where  IdHospedagem = 3;
-- update Hospedagens
-- set Checkin = '2023-05-16', Checkout = '2023-05-25'
-- where  IdHospedagem = 4;
-- update Hospedagens
-- set Checkin = '2023-08-30', Checkout = '2023-09-05'
-- where  IdHospedagem = 5;
-- update Hospedagens
-- set Checkin = '2023-06-04', Checkout = '2023-06-10'
-- where  IdHospedagem = 6;
-- update Hospedagens
-- set Checkin = '2023-03-12', Checkout = '2023-03-17'
-- where  IdHospedagem = 7;
-- update Hospedagens
-- set Checkin = '2023-03-16', Checkout = '2023-03-20'
-- where  IdHospedagem = 8;
-- update Hospedagens
-- set Checkin = '2023-04-04', Checkout = '2023-04-07'
-- where  IdHospedagem = 9;
-- update Hospedagens
-- set Checkin = '2023-10-10', Checkout = '2023-10-15'
-- where  IdHospedagem = 10;
-- 
-- /*Update Usuários*/
-- update Usuarios
-- set Senha = '12345', Login='Roncas'
-- where  IdUsuario = 1;
-- update Usuarios
-- set Senha = '67891', Login='Bernas'
-- where  IdUsuario = 2;
-- update Usuarios
-- set Senha = '101112', Login='Andras'
-- where  IdUsuario = 3;
-- update Usuarios
-- set Senha = '126890', Login='Luigigigie'
-- where  IdUsuario = 4;
-- update Usuarios
-- set Senha = '61701', Login= 'Muliro'
-- where  IdUsuario = 5;
-- update Usuarios
-- set Senha = '01234', Login='Gagas'
-- where  IdUsuario = 6;
-- update Usuarios
-- set Senha = '901890', Login='Gustas'
-- where  IdUsuario = 7;
-- update Usuarios
-- set Senha = '448768', Login='Kakas'
-- where  IdUsuario = 8;
-- update Usuarios
-- set Senha = '48765', Login='Charlão'
-- where  IdUsuario = 9;
-- update Usuarios
-- set Senha = '4165', Login='Clóvis'
-- where  IdUsuario = 10;
-- 
-- 
-- /*Update Quartos*/
-- 
-- update Quartos
-- set MaxPessoas = 1 , Manutencao = 0 , TipoCama = 'Solteiro', Frigobar = 0 , ArCondicionado = 1 , Banheira = 0 , TV = 0 , PrecoDiaria = 925.50
-- where IdQuarto = 1;
-- 
-- update Quartos
-- set MaxPessoas = 2 , Manutencao = 1 , TipoCama = 'Solteiro', Frigobar = 1 , ArCondicionado = 0 , Banheira = 1 , TV = 1 , PrecoDiaria = 350.00
-- where IdQuarto = 2;
-- 
-- update Quartos
-- set MaxPessoas = 3 , Manutencao = 0 , TipoCama = 'Casal', Frigobar = 0 , ArCondicionado = 1 , Banheira = 1 , TV = 1 , PrecoDiaria = 700.00
-- where IdQuarto = 3;
-- 
-- update Quartos
-- set MaxPessoas = 4 , Manutencao = 1 , TipoCama = 'Solteiro', Frigobar = 1 , ArCondicionado = 1 , Banheira = 1 , TV = 1 , PrecoDiaria = 1500.50
-- where IdQuarto = 4;
-- 
-- update Quartos
-- set MaxPessoas = 2 , Manutencao = 0 , TipoCama = 'Casal', Frigobar = 1 , ArCondicionado = 1 , Banheira = 0 , TV = 1 , PrecoDiaria = 550.00
-- where IdQuarto = 5;
-- 
-- update Quartos
-- set MaxPessoas = 4 , Manutencao = 1 , TipoCama = 'Solteiro', Frigobar = 0 , ArCondicionado = 1 , Banheira = 0 , TV = 0 , PrecoDiaria = 550.00
-- where IdQuarto = 6;
-- 
-- update Quartos
-- set MaxPessoas = 1 , Manutencao = 0 , TipoCama = 'Solteiro', Frigobar = 1 , ArCondicionado = 1 , Banheira = 1 , TV = 1 , PrecoDiaria = 950.00
-- where IdQuarto = 7;
-- 
-- update Quartos
-- set MaxPessoas = 2 , Manutencao = 0 , TipoCama = 'Casal', Frigobar = 1 , ArCondicionado = 1 , Banheira = 1 , TV = 1 , PrecoDiaria = 1000.00
-- where IdQuarto = 8;
-- 
-- update Quartos
-- set MaxPessoas = 4 , Manutencao = 1 , TipoCama = 'Casal', Frigobar = 0 , ArCondicionado = 1 , Banheira = 0 , TV = 0 , PrecoDiaria = 350.00
-- where IdQuarto = 9;
-- 
-- update Quartos
-- set MaxPessoas = 1 , Manutencao = 0 , TipoCama = 'Solteiro', Frigobar = 1 , ArCondicionado = 1 , Banheira = 1 , TV = 0 , PrecoDiaria = 450.00
-- where IdQuarto = 10;
-- 
-- 
-- /*Update ServicosConsumidos*/
-- update ServicosConsumidos
-- set IdHospede= 1,IdServico = 1, IdHospedagem=1
-- where  IdServicoConsumidos= 1;
-- update ServicosConsumidos
-- set IdHospede= 2,IdServico = 2, IdHospedagem=2
-- where  IdServicoConsumidos= 2;
-- update ServicosConsumidos
-- set IdHospede= 3,IdServico = 3, IdHospedagem=3
-- where  IdServicoConsumidos= 3;
-- update ServicosConsumidos
-- set IdHospede= 4,IdServico = 4, IdHospedagem=4
-- where  IdServicoConsumidos= 4;
-- update ServicosConsumidos
-- set IdHospede= 5,IdServico = 5, IdHospedagem=5
-- where  IdServicoConsumidos= 5;
-- update ServicosConsumidos
-- set IdHospede= 6,IdServico = 6, IdHospedagem=6
-- where  IdServicoConsumidos= 6;
-- update ServicosConsumidos
-- set IdHospede= 7,IdServico = 7, IdHospedagem=7
-- where  IdServicoConsumidos= 7;
-- update ServicosConsumidos
-- set IdHospede= 8,IdServico = 8, IdHospedagem=8
-- where  IdServicoConsumidos= 8;
-- update ServicosConsumidos
-- set IdHospede= 9,IdServico = 9, IdHospedagem=9
-- where  IdServicoConsumidos= 9;
-- update ServicosConsumidos
-- set IdHospede= 10,IdServico = 10, IdHospedagem=10
-- where  IdServicoConsumidos= 10;
-- 
-- 
-- /*update atividades*/
-- 
-- update Atividades
-- set NomeAtividade='aula de boxe'
-- where IdAtividade=1;
-- 
-- update Atividades
-- set NomeAtividade ='aula de vôlei'
-- where IdAtividade=11;
-- 
-- update Atividades
-- set NomeAtividade= 'aula de capoeira'
-- where IdAtividade=7;
-- 
-- update Atividades
-- set NomeAtividade='aula de futebol'
-- where IdAtividade=12;
-- 
-- update Atividades
-- set NomeAtividade='pôker'
-- where IdAtividade=18;
-- 
-- update Atividades
-- set NomeAtividade='aula de karatê'
-- where IdAtividade=5;
-- 
-- update Atividades
-- set NomeAtividade='aula de kickbox'
-- where IdAtividade=9;
-- 
-- update Atividades
-- set NomeAtividade='piscina'
-- where IdAtividade=7;
-- 
-- update Atividades
-- set NomeAtividade='sauna'
-- where IdAtividade=19;
-- 
-- update Atividades
-- set NomeAtividade='bugy'
-- where IdAtividade=20;
-- 
-- 
-- /*update AtividadesHospedes*/
-- 
-- update AtividadesHospedes
-- set IdHospede=1, IdAtividade=1
-- where IdHospedeAtividade=1;
-- 
-- update AtividadesHospedes
-- set  IdHospede=2, IdAtividade=2
-- where IdHospedeAtividade=2;
-- 
-- update AtividadesHospedes
-- set  IdHospede=3, IdAtividade=3
-- where IdHospedeAtividade=3;
-- 
-- update AtividadesHospedes
-- set  IdHospede=4, IdAtividade=4
-- where IdHospedeAtividade=4;
-- 
-- update AtividadesHospedes
-- set  IdHospede=5, IdAtividade=5
-- where IdHospedeAtividade=5;
-- 
-- update AtividadesHospedes
-- set  IdHospede=6, IdAtividade=6
-- where IdHospedeAtividade=6;
-- 
-- update AtividadesHospedes
-- set  IdHospede=7, IdAtividade=7
-- where IdHospedeAtividade=7;
-- 
-- update AtividadesHospedes
-- set  IdHospede=8, IdAtividade=8
-- where IdHospedeAtividade=8;
-- 
-- update AtividadesHospedes
-- set  IdHospede=9, IdAtividade=9
-- where IdHospedeAtividade=9;
-- 
-- update AtividadesHospedes
-- set  IdHospede=10, IdAtividade=10
-- where IdHospedeAtividade=10;
-- 
-- 
-- /*update Hospede*/
-- 
-- Update Hospedes
-- set Nome = 'Jorge', Sobrenome = 'Oliveira', DataNasc = '2000-03-03', Documento = '111.222.333-44', Nacionalidade = 'Brasileiro', Pronome = 'Ele/dele', Email = 'jorge013@gmail.com', IdUsuario = 1
-- where  IdHospede = 1;
-- 
-- Update Hospedes
-- set Nome = 'João', Sobrenome = 'Souza', DataNasc = '2001-02-07', Nacionalidade = 'Americano', Pronome = 'Ele/dele', Email = 'joaop12@gmail.com', IdUsuario = 2
-- where  IdHospede = 2;
-- 
-- Update Hospedes
-- set Nome = 'Maria', Sobrenome = 'Rocha', DataNasc = '2002-11-11', Documento = '111.222.333-41', Nacionalidade = 'Brasileiro', Pronome = 'Ela/dela', Email = 'maria222@gmail.com', IdUsuario = 3
-- where  IdHospede = 3;
-- 
-- Update Hospedes
-- set Nome = 'Andrei', Sobrenome = 'Amaral', DataNasc = '2003-09-17', Nacionalidade = 'Americano', Pronome = 'Ele/dele', Email = 'AndreiAm@gmail.com', IdUsuario = 4
-- where  IdHospede = 4;
-- 
-- Update Hospedes
-- set Nome = 'Gustavo', Sobrenome = 'Luiz', DataNasc = '2004-12-22', Documento = '111.222.333-45', Nacionalidade = 'Brasileiro', Pronome = 'Ele/dele', Email = 'gusta942@gmail.com', IdUsuario = 5
-- where  IdHospede = 5;
-- 
-- Update Hospedes
-- set Nome = 'Bernardo', Sobrenome = 'Oliveira', DataNasc = '2001-01-26', Documento = '111.222.333-46', Nacionalidade = 'Brasileiro', Pronome = 'Ele/dele', Email = 'be.d0aa@gmail.com', IdUsuario = 6
-- where  IdHospede = 6;
-- 
-- Update Hospedes
-- set Nome = 'Erik', Sobrenome = 'Roncaglio', DataNasc = '2005-06-30', Documento = '111.222.333-47', Nacionalidade = 'Brasileiro', Pronome = 'Ele/dele', Email = 'erik.r01@gmail.com', IdUsuario = 7
-- where  IdHospede = 7;
-- 
-- Update Hospedes
-- set Nome = 'Gabriel', Sobrenome = 'Mohr', DataNasc = '2003-04-15', Documento = '111.222.333-48', Nacionalidade = 'Brasileiro', Pronome = 'Ele/dele', Email = 'gab328n@gmail.com', IdUsuario = 8
-- where  IdHospede = 8;
-- 
-- Update Hospedes
-- set Nome = 'Luigi', Sobrenome = 'Scharam', DataNasc = '2002-07-02', Documento = '111.222.333-49', Nacionalidade = 'Brasileiro', Pronome = 'Ele/dele', Email = 'luigi23@gmail.com', IdUsuario = 9
-- where  IdHospede = 9;
-- 
-- Update Hospedes
-- set Nome = 'Muliro', Sobrenome = 'Silveira', DataNasc = '2004-10-18', Documento = '111.222.333-50', Nacionalidade = 'Brasileiro', Pronome = 'Ele/dele', Email = 'mulibro248@gmail.com', IdUsuario = 10
-- where  IdHospede = 10;
-- 
-- /* Deletes */
-- 
-- SET foreign_key_checks = 0;
-- 
-- /* Delete usuarios*/
-- 
-- DELETE FROM Usuarios WHERE IdUsuario = 1;
-- DELETE FROM Usuarios WHERE IdUsuario = 2;
-- DELETE FROM Usuarios WHERE IdUsuario = 3;
-- DELETE FROM Usuarios WHERE IdUsuario = 4;
-- DELETE FROM Usuarios WHERE IdUsuario = 5;
-- 
-- /* Delete Hospedes*/
-- 
-- DELETE FROM Hospedes WHERE IdHospede = 1;
-- DELETE FROM Hospedes WHERE IdHospede = 2;
-- DELETE FROM Hospedes WHERE IdHospede = 3;
-- DELETE FROM Hospedes WHERE IdHospede = 4;
-- DELETE FROM Hospedes WHERE IdHospede = 5;
-- 
-- /* Delete Atividades*/
-- 
-- DELETE FROM Atividades Where IdAtividade = 1;
-- DELETE FROM Atividades Where IdAtividade = 2;
-- DELETE FROM Atividades Where IdAtividade = 3;
-- DELETE FROM Atividades Where IdAtividade = 4;
-- DELETE FROM Atividades Where IdAtividade = 5;
-- 
-- /* Delete Hospedagens*/
-- 
-- DELETE FROM Hospedagens Where IdHospedagem = 1;
-- DELETE FROM Hospedagens Where IdHospedagem = 2;
-- DELETE FROM Hospedagens Where IdHospedagem = 3;
-- DELETE FROM Hospedagens Where IdHospedagem = 4;
-- DELETE FROM Hospedagens Where IdHospedagem = 5;
-- 
-- /* Delete Servicos*/
-- 
-- DELETE FROM Servicos Where IdServico = 1;
-- DELETE FROM Servicos Where IdServico = 2;
-- DELETE FROM Servicos Where IdServico = 3;
-- DELETE FROM Servicos Where IdServico = 4;
-- DELETE FROM Servicos Where IdServico = 5;
-- 
-- /* Delete ServicosConsumidos*/
-- 
-- DELETE FROM ServicosConsumidos Where IdServicoConsumidos = 1;
-- DELETE FROM ServicosConsumidos Where IdServicoConsumidos = 2;
-- DELETE FROM ServicosConsumidos Where IdServicoConsumidos = 3;
-- DELETE FROM ServicosConsumidos Where IdServicoConsumidos = 4;
-- DELETE FROM ServicosConsumidos Where IdServicoConsumidos = 5;
-- 
-- /* Delete Quartos */
-- 
-- DELETE FROM Quartos Where IdQuarto = 1;
-- DELETE FROM Quartos Where IdQuarto = 2;
-- DELETE FROM Quartos Where IdQuarto = 3;
-- DELETE FROM Quartos Where IdQuarto = 4;
-- DELETE FROM Quartos Where IdQuarto = 5;
-- 
-- /* Delete AtividadesHospedes */
-- 
-- DELETE FROM AtividadesHospedes Where IdHospedeAtividade = 1;
-- DELETE FROM AtividadesHospedes Where IdHospedeAtividade = 2;
-- DELETE FROM AtividadesHospedes Where IdHospedeAtividade = 3;
-- DELETE FROM AtividadesHospedes Where IdHospedeAtividade = 4;
-- DELETE FROM AtividadesHospedes Where IdHospedeAtividade = 5;
-- 
-- 
-- /* Delete Funcionarios */
-- 
-- DELETE FROM Funcionarios Where IdFuncionario = 1;
-- DELETE FROM Funcionarios Where IdFuncionario = 2;
-- DELETE FROM Funcionarios Where IdFuncionario = 3;
-- DELETE FROM Funcionarios Where IdFuncionario = 4;
-- DELETE FROM Funcionarios Where IdFuncionario = 5;
