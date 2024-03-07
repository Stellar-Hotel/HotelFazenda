-- MySQL Workbench Forward Engineering

DROP DATABASE IF EXISTS `mydb`;

CREATE database IF NOT EXISTS `mydb` ;

USE `mydb` ;

-- -----------------------------------------------------
-- Table  `mydb`.`Usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `USUARIOS`(
  `id_usuario` INT NOT NULL AUTO_INCREMENT,
  `senha` VARCHAR(45) NOT NULL,
  `nivel_de_acesso` INT NOT NULL,
  `login` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_usuario`));

-- -----------------------------------------------------
-- Table `mydb`.`Hospedes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HOSPEDES`(
  `Hospede_id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `sobrenome` VARCHAR(45) NOT NULL,
  `data_nasc` DATE NOT NULL,
  `CPF` VARCHAR(45) NOT NULL,
  `Nacionalidade` VARCHAR(45) NOT NULL,
  `Pronome` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `id_usuario` INT NOT NULL,
  PRIMARY KEY (`Hospede_id`, `id_usuario`),
 
  CONSTRAINT `fk_Hospedes_Usuarios1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `mydb`.`Usuarios`(`id_usuario`)
    )
;


-- -----------------------------------------------------
-- Table `mydb`.`servicos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS  `SERVICOS` (
  `id_servicos` INT NOT NULL AUTO_INCREMENT,
  `preco_servico` FLOAT NOT NULL,
  `nome_servico` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_servicos`))
;


-- -----------------------------------------------------
-- Table `mydb`.`Hospedagens`
-- -----------------------------------------------------
CREATE TABLE  IF NOT EXISTS `HOSPEDAGENS` (
  `Hospedagens_id` INT NOT NULL AUTO_INCREMENT,
  `checkin` DATE NOT NULL,
  `checkout` DATE NOT NULL,
  PRIMARY KEY (`Hospedagens_id`))
;


-- -----------------------------------------------------
-- Table `mydb`.`Quartos`
-- -----------------------------------------------------
CREATE TABLE  IF NOT EXISTS `QUARTOS` (
  `id_Quartos` INT NOT NULL AUTO_INCREMENT,
  `max_pessoas` INT NOT NULL,
  `manutencao` VARCHAR(45) NOT NULL,
  `tipo_cama` VARCHAR(45) NOT NULL,
  `frigobar` TINYINT NOT NULL,
  `ar_condicionado` TINYINT NOT NULL,
  `banheira` TINYINT NOT NULL,
  `tv` TINYINT NOT NULL,
  `preco_quarto_dia` FLOAT NOT NULL,
  PRIMARY KEY (`id_Quartos`))
;


-- -----------------------------------------------------
-- Table `mydb`.`Funcionarios`
-- -----------------------------------------------------
CREATE TABLE  IF NOT EXISTS `FUNCIONARIOS` (
  `funcionario_id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `sobrenome` VARCHAR(45) NOT NULL,
  `funcao` VARCHAR(45) NOT NULL,
  `salario` FLOAT NOT NULL,
  `id_usuario` INT NOT NULL,
  PRIMARY KEY (`funcionario_id`, `id_usuario`),
  CONSTRAINT `fk_Funcionarios_Usuários1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `mydb`.`Usuarios`(`id_usuario`)
    )
;


-- -----------------------------------------------------
-- Table `mydb`.`Atividades`
-- -----------------------------------------------------
CREATE TABLE  IF NOT EXISTS `ATIVIDADES` (
  `id_atividade` INT NOT NULL AUTO_INCREMENT,
  `horario` VARCHAR(45) NOT NULL,
  `horario_fim` VARCHAR(45) NOT NULL,
  `funcionario_id` INT NOT NULL,
  `restricao_idade` INT NOT NULL,
  `nome_atividade` VARCHAR(45) NOT NULL,
  `data` DATE NOT NULL,
  PRIMARY KEY (`id_atividade`, `funcionario_id`),

  CONSTRAINT `fk_Atividades_Funcionarios1`
    FOREIGN KEY (`funcionario_id`)
    REFERENCES `mydb`.`Funcionarios` (`funcionario_id`)
   )
;


-- -----------------------------------------------------
-- Table `mydb`.`servicos_consumidos`
-- -----------------------------------------------------
CREATE TABLE  IF NOT EXISTS `SERVICOS_CONSUMIDOS`(
  `id_servicos_consumidos` INT NOT NULL AUTO_INCREMENT,
  `id_hospede` INT NOT NULL,
  `id_serviço` INT NOT NULL,
  `id_hospedagens` INT NOT NULL,
  PRIMARY KEY (`id_servicos_consumidos`, `id_hospede`, `id_serviço`, `id_hospedagens`),

  CONSTRAINT `fk_Hospedes_has_servicos_Hospedes1`
    FOREIGN KEY (`id_hospede`)
    REFERENCES `mydb`.`Hospedes` (`Hospede_id`)
   ,
  CONSTRAINT `fk_Hospedes_has_servicos_servicos1`
    FOREIGN KEY (`id_serviço`)
    REFERENCES `mydb`.`servicos` (`id_servicos`)
    ,
  CONSTRAINT `fk_Hospedes_has_serv`
    FOREIGN KEY (`id_hospedagens`)
    REFERENCES `mydb`.`Hospedagens` (`Hospedagens_id`)
    )
;


-- -----------------------------------------------------
-- Table `mydb`.`Hospedagens_quartos`
-- -----------------------------------------------------
CREATE TABLE  IF NOT EXISTS `HOSPEDAGENS_QUARTOS` (
  `id_Hospedagem_quartos` INT NOT NULL AUTO_INCREMENT,
  `id_Quartos` INT NOT NULL,
  `Hospedagens_id` INT NOT NULL,
  `id_Hospede` INT NOT NULL,
  PRIMARY KEY (`id_Hospedagem_quartos`, `id_Quartos`, `Hospedagens_id`, `id_Hospede`),

  CONSTRAINT `fk_Hospedagens_has_Quartos_Quartos1`
    FOREIGN KEY (`id_Quartos`)
    REFERENCES `mydb`.`Quartos` (`id_Quartos`)
   ,
  CONSTRAINT `fk_Hospedagens_quartos_Hospedagens1`
    FOREIGN KEY (`Hospedagens_id`)
    REFERENCES `mydb`.`Hospedagens` (`Hospedagens_id`)
    ,
  CONSTRAINT `fk_Hospedagens_quartos_Hospedes1`
    FOREIGN KEY (`id_Hospede`)
    REFERENCES `mydb`.`Hospedes` (`id_usuario`)
    )
;


-- -----------------------------------------------------
-- Table `mydb`.`Atividades_hospede`
-- -----------------------------------------------------
CREATE TABLE  IF NOT EXISTS `ATIVIDADES_HOSPEDE` (
  `id_hospede_atividade` INT NOT NULL AUTO_INCREMENT,
  `Hospede_id` INT NOT NULL,
  `id_atividade` INT NOT NULL,
  PRIMARY KEY (`id_hospede_atividade`, `Hospede_id`, `id_atividade`),

  CONSTRAINT `fk_Hospedes_has_Atividades_Hospedes1`
    FOREIGN KEY (`Hospede_id`)
    REFERENCES `mydb`.`Hospedes` (`Hospede_id`)
    ,
  CONSTRAINT `fk_Hospedes_has_Atividades_Atividades1`
    FOREIGN KEY (`id_atividade`)
    REFERENCES `mydb`.`Atividades` (`id_atividade`)
    )
;

/*Tabela Usuarios*/

insert into USUARIOS ( senha, nivel_de_acesso, login)
values ('M@iones3', 0,'MAZDARX7' ),
('eido', 0,'Cralos Ícaro' ),
('Geromel', 1,'Andrei' ),
('Bernas', 0,'Mico' ),
('blaze', 1,'FelipeNeto' ),
('Azul', 1,'Smurfette' ),
('Gorro', 1,'PapaSmurf' ),
('Azar', 0,'Desastrado' ),
('Bobo', 1,'Joca' ),
('Forte', 0,'Robusto' ),
('Ruiva', 1,'Sassette' ),
('Burro', 1,'Gênio' ),
('Feliz', 0,'Ranzinza' ),
('Horrível', 1,'Habilidoso' ),
('Bonitão', 0,'Vaidoso' ),
('Véio', 0,'VovôSmurf' ),
('Bonzinho', 1,'DevilSmurf' ),
('Bom', 0,'Tuffy' ),
('Rastreio', 1,'Caçador' ),
('Véia', 1,'VovóSmurf' ),
('AgroBoy', 1,'Fazendeiro' ),
('Pedreiro', 1,'TimberSmurf' ),
('BadBoy', 0,'AngelSmurf' ),
('Woolly', 1,'WoolySmurf' ),
('Andras', 0,'fgyuvu' ),
('João', 0,'Monark' ),
('Mau', 1,'Ele' ),
('Pinas', 0,'Gustas' ),
('Erik', 1,'Roncas' ),
('Roxo', 1,'William' ),
('SpringTrap', 1,'Afton' ),
('Azu', 0,'Marill' ),
('Kan', 1,'Gaskan' ),
('Drago', 0,'Nite' ),
('Stara', 1,'Ptor' ),
('Burro', 1,'Dragão' ),
('Ogro', 0,'Shrek' ),
('Princesa', 1,'Fiona' ),
('Rei', 0,'Leônidas' ),
('MelhorCidade', 0,'Esparta' ),
('Filósofo', 1,'Aristóteles' ),
('Inteligente', 0,'Newtown' ),
('Balela', 1,'Zero' ),
('Cowboy', 1,'JohnMarston' ),
('RDR2', 1,'ArthurMorgan' ),
('Mago', 1,'DavyJones' ),
('Carrara', 0,'Augustinho' ),
('Only', 1,'Beiçola' );
 
 

/*Tabela Hóspedes*/
insert into Hospedes (nome, sobrenome, data_nasc, CPF, Nacionalidade, Pronome, email,id_usuario) values ('Stacee', 'Glisenan', '1986-09-01', '607.332.182-77', 'Uganda', 'Genderfluid', 'sglisenan0@mail.ru',1);
insert into Hospedes (nome, sobrenome, data_nasc, CPF, Nacionalidade, Pronome, email,id_usuario) values ('Elisa', 'Volette', '1984-11-06', '751.425.013-76', 'Peru', 'Female', 'evolette1@stanford.edu',2);
insert into Hospedes (nome, sobrenome, data_nasc, CPF, Nacionalidade, Pronome, email,id_usuario) values ('Maxwell', 'Haycraft', '1977-02-16', '316.149.686-40', 'Brazil', 'Male', 'mhaycraft2@wikispaces.com',3);
insert into Hospedes (nome, sobrenome, data_nasc, CPF, Nacionalidade, Pronome, email,id_usuario) values ('Gussy', 'Fries', '2003-10-16', '395.415.201-14', 'China', 'Female', 'gfries3@stumbleupon.com',4);
insert into Hospedes (nome, sobrenome, data_nasc, CPF, Nacionalidade, Pronome, email,id_usuario) values ('Duff', 'Mungham', '2023-09-21', '805.097.764-25', 'Philippines', 'Male', 'dmungham4@histats.com',5);
insert into Hospedes (nome, sobrenome, data_nasc, CPF, Nacionalidade, Pronome, email,id_usuario) values ('Kristo', 'Honnan', '2019-02-21', '636.015.493-06', 'Mongolia', 'Male', 'khonnan5@ovh.net',6);
insert into Hospedes (nome, sobrenome, data_nasc, CPF, Nacionalidade, Pronome, email,id_usuario) values ('Teodoor', 'Skeels', '2008-05-23', '421.381.619-25', 'Sweden', 'Male', 'tskeels6@themeforest.net',7);
insert into Hospedes (nome, sobrenome, data_nasc, CPF, Nacionalidade, Pronome, email,id_usuario) values ('Malachi', 'Barnewille', '1999-04-14', '427.559.825-55', 'France', 'Male', 'mbarnewille7@newyorker.com',8);
insert into Hospedes (nome, sobrenome, data_nasc, CPF, Nacionalidade, Pronome, email,id_usuario) values ('Wallis', 'Bree', '1990-12-15', '636.068.749-13', 'Norway', 'Female', 'wbree8@instagram.com',9);
insert into Hospedes (nome, sobrenome, data_nasc, CPF, Nacionalidade, Pronome, email,id_usuario) values ('Marius', 'Bratton', '1994-10-31', '298.583.262-13', 'Brazil', 'Male', 'mbratton9@mashable.com',10);
insert into Hospedes (nome, sobrenome, data_nasc, CPF, Nacionalidade, Pronome, email,id_usuario) values ('Obadiah', 'Aylesbury', '2017-04-14', '129.346.434-18', 'China', 'Male', 'oaylesburya@europa.eu',11);
insert into Hospedes (nome, sobrenome, data_nasc, CPF, Nacionalidade, Pronome, email,id_usuario) values ('Wright', 'Alkin', '1980-09-19', '949.735.668-02', 'Yemen', 'Male', 'walkinb@surveymonkey.com',12);
insert into Hospedes (nome, sobrenome, data_nasc, CPF, Nacionalidade, Pronome, email,id_usuario) values ('Rikki', 'Youson', '1994-06-10', '833.407.132-18', 'Thailand', 'Male', 'ryousonc@vk.com',13);
insert into Hospedes (nome, sobrenome, data_nasc, CPF, Nacionalidade, Pronome, email,id_usuario) values ('Benedicto', 'Hodgin', '1970-12-16', '198.397.145-67', 'Iran', 'Male', 'bhodgind@photobucket.com',14);
insert into Hospedes (nome, sobrenome, data_nasc, CPF, Nacionalidade, Pronome, email,id_usuario) values ('Lennie', 'Lembrick', '1975-06-23', '882.476.072-06', 'Pakistan', 'Male', 'llembricke@un.org',15);
insert into Hospedes (nome, sobrenome, data_nasc, CPF, Nacionalidade, Pronome, email,id_usuario) values ('Eachelle', 'Gonoude', '1998-09-26', '685.862.163-47', 'China', 'Female', 'egonoudef@is.gd',16);
insert into Hospedes (nome, sobrenome, data_nasc, CPF, Nacionalidade, Pronome, email,id_usuario) values ('Freddie', 'Millott', '2012-06-22', '363.678.979-99', 'Greece', 'Female', 'fmillottg@xing.com',17);
insert into Hospedes (nome, sobrenome, data_nasc, CPF, Nacionalidade, Pronome, email,id_usuario) values ('Had', 'Babar', '2015-08-13', '269.188.281-56', 'Russia', 'Male', 'hbabarh@addtoany.com',18);
insert into Hospedes (nome, sobrenome, data_nasc, CPF, Nacionalidade, Pronome, email,id_usuario) values ('Amanda', 'Harbach', '1979-10-08', '700.716.283-32', 'United States', 'Female', 'aharbachi@google.fr',19);
insert into Hospedes (nome, sobrenome, data_nasc, CPF, Nacionalidade, Pronome, email,id_usuario) values ('Lurlene', 'Crampsy', '1966-01-08', '480.398.712-27', 'Bosnia and Herzegovina', 'Female', 'lcrampsyj@tiny.cc',20);

/*Tabela funcionarios*/

insert into Funcionarios (nome, sobrenome, funcao, salario, id_usuario) values ('Gerry', 'Delucia', 'Camareiro', 9526.14, 21);
insert into Funcionarios (nome, sobrenome, funcao, salario, id_usuario) values ('Editha', 'Stede', 'Zeladora', 9016.38, 22);
insert into Funcionarios (nome, sobrenome, funcao, salario, id_usuario) values ('Agosto', 'Franchyonok', 'Zelador', 7291.94, 23);
insert into Funcionarios (nome, sobrenome, funcao, salario, id_usuario) values ('Giselbert', 'Duthy', 'Faxineira', 5890.71, 24);
insert into Funcionarios (nome, sobrenome, funcao, salario, id_usuario) values ('L;urette', 'Smalridge', 'camareira', 6530.11, 25);
insert into Funcionarios (nome, sobrenome, funcao, salario, id_usuario) values ('Kym', 'Wakerley', 'Zeladora', 1477.57, 26);
insert into Funcionarios (nome, sobrenome, funcao, salario, id_usuario) values ('Aymer', 'Tunbridge', 'Recepcionista', 5917.57, 27);
insert into Funcionarios (nome, sobrenome, funcao, salario, id_usuario) values ('Lindie', 'Kaygill', 'Jardineira', 8184.42, 28);
insert into Funcionarios (nome, sobrenome, funcao, salario, id_usuario) values ('Suzi', 'Rickarsey', 'Jardineira Specialist IV', 4905.58, 29);
insert into Funcionarios (nome, sobrenome, funcao, salario, id_usuario) values ('Jonell', 'Devine', 'Garçom', 2276.33, 30);
insert into Funcionarios (nome, sobrenome, funcao, salario, id_usuario) values ('Mae', 'McNess', 'Garçom', 9164.21, 31);
insert into Funcionarios (nome, sobrenome, funcao, salario, id_usuario) values ('Moishe', 'McCrea', 'Secretário', 6212.93, 32);
insert into Funcionarios (nome, sobrenome, funcao, salario, id_usuario) values ('Winny', 'Butterworth', 'Secretário', 2643.54, 33);
insert into Funcionarios (nome, sobrenome, funcao, salario, id_usuario) values ('Wynn', 'MacAllester', 'Gerente', 9734.61, 34);
insert into Funcionarios (nome, sobrenome, funcao, salario, id_usuario) values ('Charmaine', 'Chilcott', 'camareira', 3291.06, 35);
insert into Funcionarios (nome, sobrenome, funcao, salario, id_usuario) values ('Hortense', 'Dwyr', 'Diarista', 7856.25, 36);
insert into Funcionarios (nome, sobrenome, funcao, salario, id_usuario) values ('Chadwick', 'Ewen', 'Faxineiro', 7086.47, 37);
insert into Funcionarios (nome, sobrenome, funcao, salario, id_usuario) values ('Tally', 'Liffe', 'Diarista', 5948.04, 38);
insert into Funcionarios (nome, sobrenome, funcao, salario, id_usuario) values ('Letisha', 'Huggon', 'Diarista', 9692.93, 39);
insert into Funcionarios (nome, sobrenome, funcao, salario, id_usuario) values ('Mose', 'Jozwicki', 'Diarista', 2530.39, 40);

/*Tabela Quartos*/

insert into Quartos (max_pessoas, manutencao, tipo_cama, frigobar, ar_condicionado, banheira, tv, preco_quarto_dia) values (2, 1, 'Casal', 1, 0, 1, 0, 927.53);
insert into Quartos (max_pessoas, manutencao, tipo_cama, frigobar, ar_condicionado, banheira, tv, preco_quarto_dia) values (5, 0, 'Casal', 0, 0, 0, 1, 387.28);
insert into Quartos (max_pessoas, manutencao, tipo_cama, frigobar, ar_condicionado, banheira, tv, preco_quarto_dia) values (4, 0, 'Casal', 1, 0, 0, 1, 733.04);
insert into Quartos (max_pessoas, manutencao, tipo_cama, frigobar, ar_condicionado, banheira, tv, preco_quarto_dia) values (2, 0, 'Casal', 0, 1, 0, 0, 1593.7);
insert into Quartos (max_pessoas, manutencao, tipo_cama, frigobar, ar_condicionado, banheira, tv, preco_quarto_dia) values (4, 1, 'Casal', 0, 0, 0, 1, 576.8);
insert into Quartos (max_pessoas, manutencao, tipo_cama, frigobar, ar_condicionado, banheira, tv, preco_quarto_dia) values (2, 0, 'Casal', 1, 1, 0, 1, 773.33);
insert into Quartos (max_pessoas, manutencao, tipo_cama, frigobar, ar_condicionado, banheira, tv, preco_quarto_dia) values (2, 1, 'Casal', 0, 0, 1, 1, 1478.15);
insert into Quartos (max_pessoas, manutencao, tipo_cama, frigobar, ar_condicionado, banheira, tv, preco_quarto_dia) values (4, 1, 'Casal', 0, 1, 1, 0, 765.21);
insert into Quartos (max_pessoas, manutencao, tipo_cama, frigobar, ar_condicionado, banheira, tv, preco_quarto_dia) values (2, 1, 'Casal', 1, 0, 0, 0, 735.41);
insert into Quartos (max_pessoas, manutencao, tipo_cama, frigobar, ar_condicionado, banheira, tv, preco_quarto_dia) values (2, 0, 'Casal', 1, 1, 1, 1, 508.69);
insert into Quartos (max_pessoas, manutencao, tipo_cama, frigobar, ar_condicionado, banheira, tv, preco_quarto_dia) values (4, 1, 'Solteiro', 1, 1, 1, 0, 1963.8);
insert into Quartos (max_pessoas, manutencao, tipo_cama, frigobar, ar_condicionado, banheira, tv, preco_quarto_dia) values (3, 0, 'Solteiro', 0, 0, 0, 1, 1298.17);
insert into Quartos (max_pessoas, manutencao, tipo_cama, frigobar, ar_condicionado, banheira, tv, preco_quarto_dia) values (1, 0, 'Solteiro', 0, 0, 0, 1, 1673.81);
insert into Quartos (max_pessoas, manutencao, tipo_cama, frigobar, ar_condicionado, banheira, tv, preco_quarto_dia) values (1, 1, 'Solteiro', 0, 0, 1, 1, 633.46);
insert into Quartos (max_pessoas, manutencao, tipo_cama, frigobar, ar_condicionado, banheira, tv, preco_quarto_dia) values (2, 1, 'Solteiro', 0, 0, 1, 1, 449.5);
insert into Quartos (max_pessoas, manutencao, tipo_cama, frigobar, ar_condicionado, banheira, tv, preco_quarto_dia) values (3, 0, 'Solteiro', 0, 0, 0, 1, 1547.8);
insert into Quartos (max_pessoas, manutencao, tipo_cama, frigobar, ar_condicionado, banheira, tv, preco_quarto_dia) values (1, 1, 'Solteiro', 0, 1, 0, 1, 459.85);
insert into Quartos (max_pessoas, manutencao, tipo_cama, frigobar, ar_condicionado, banheira, tv, preco_quarto_dia) values (2, 0, 'Solteiro', 1, 0, 0, 0, 1102.29);
insert into Quartos (max_pessoas, manutencao, tipo_cama, frigobar, ar_condicionado, banheira, tv, preco_quarto_dia) values (1, 0, 'Solteiro', 0, 0, 0, 1, 1484.32);
insert into Quartos (max_pessoas, manutencao, tipo_cama, frigobar, ar_condicionado, banheira, tv, preco_quarto_dia) values (3, 0, 'Solteiro', 0, 0, 0, 1, 1907.04);


/*Tabela hospedagens*/

insert into Hospedagens (checkin, checkout, Hospedagens_id) values ('2023-01-28', '2023-10-29', 1);
insert into Hospedagens (checkin, checkout, Hospedagens_id) values ('2022-11-29', '2023-05-27', 2);
insert into Hospedagens (checkin, checkout, Hospedagens_id) values ('2023-08-08', '2023-06-28', 3);
insert into Hospedagens (checkin, checkout, Hospedagens_id) values ('2022-06-04', '2023-04-23', 4);
insert into Hospedagens (checkin, checkout, Hospedagens_id) values ('2023-06-30', '2023-11-18', 5);
insert into Hospedagens (checkin, checkout, Hospedagens_id) values ('2022-06-11', '2023-01-06', 6);
insert into Hospedagens (checkin, checkout, Hospedagens_id) values ('2023-06-21', '2023-08-04', 7);
insert into Hospedagens (checkin, checkout, Hospedagens_id) values ('2023-04-30', '2023-09-11', 8);
insert into Hospedagens (checkin, checkout, Hospedagens_id) values ('2022-08-06', '2023-08-28', 9);
insert into Hospedagens (checkin, checkout, Hospedagens_id) values ('2023-03-10', '2023-06-29', 10);
insert into Hospedagens (checkin, checkout, Hospedagens_id) values ('2022-02-24', '2023-05-10', 11);
insert into Hospedagens (checkin, checkout, Hospedagens_id) values ('2023-01-08', '2023-06-21', 12);
insert into Hospedagens (checkin, checkout, Hospedagens_id) values ('2022-12-23', '2022-12-28', 13);
insert into Hospedagens (checkin, checkout, Hospedagens_id) values ('2023-05-07', '2023-07-09', 14);
insert into Hospedagens (checkin, checkout, Hospedagens_id) values ('2023-02-28', '2022-12-24', 15);
insert into Hospedagens (checkin, checkout, Hospedagens_id) values ('2022-09-15', '2023-01-22', 16);
insert into Hospedagens (checkin, checkout, Hospedagens_id) values ('2022-12-14', '2023-06-10', 17);
insert into Hospedagens (checkin, checkout, Hospedagens_id) values ('2022-03-06', '2023-03-24', 18);
insert into Hospedagens (checkin, checkout, Hospedagens_id) values ('2022-05-31', '2023-05-11', 19);
insert into Hospedagens (checkin, checkout, Hospedagens_id) values ('2022-02-07', '2023-05-30', 20);

/*Tabela Atividades */

insert into Atividades (horario, horario_fim, restricao_idade, nome_atividade, data, funcionario_id) values ('8:08 AM', '10:05 AM', 9, 'Passeio a cavalo com guia', '2023-09-02', 1);
insert into Atividades (horario, horario_fim, restricao_idade, nome_atividade, data, funcionario_id) values ('9:10 AM', '4:55 AM', 4, 'Passeio de buggy', '2022-07-27', 2);
insert into Atividades (horario, horario_fim, restricao_idade, nome_atividade, data, funcionario_id) values ('10:57 AM', '11funcionario_id:59 AM', 12, 'Pesca', '2022-09-23', 3);
insert into Atividades (horario, horario_fim, restricao_idade, nome_atividade, data, funcionario_id) values ('11:20 AM', '12:56 AM', 4, 'Trilha de offroad 4x4', '2023-10-01', 4);
insert into Atividades (horario, horario_fim, restricao_idade, nome_atividade, data, funcionario_id) values ('6:41 AM', '10:00 AM', 15, 'Trilha a pé com guia', '2023-12-07', 5);
insert into Atividades (horario, horario_fim, restricao_idade, nome_atividade, data, funcionario_id) values ('9:40 AM', '10:27 AM', 7, 'Alpinismo', '2022-10-27', 6);
insert into Atividades (horario, horario_fim, restricao_idade, nome_atividade, data, funcionario_id) values ('10:03 AM', '4:06 AM', 15, 'Surf', '2023-12-20', 7);
insert into Atividades (horario, horario_fim, restricao_idade, nome_atividade, data, funcionario_id) values ('11:15 AM', '6:37 AM', 10, 'Natação', '2022-08-13', 8);
insert into Atividades (horario, horario_fim, restricao_idade, nome_atividade, data, funcionario_id) values ('8:17 AM', '2:32 AM', 11, 'Futebol', '2023-01-19', 9);
insert into Atividades (horario, horario_fim, restricao_idade, nome_atividade, data, funcionario_id) values ('9:55 AM', '11:47 AM', 12, 'Vôlei', '2022-08-09', 10);
insert into Atividades (horario, horario_fim, restricao_idade, nome_atividade, data, funcionario_id) values ('11:12 AM', '1:06 AM', 4, 'Hipismo', '2023-09-21', 11);
insert into Atividades (horario, horario_fim, restricao_idade, nome_atividade, data, funcionario_id) values ('10:03 AM', '1:37 AM', 14, 'Luau', '2022-02-06', 12);
insert into Atividades (horario, horario_fim, restricao_idade, nome_atividade, data, funcionario_id) values ('11:08 AM', '2:46 AM', 15, 'Yoga', '2023-06-28', 13);
insert into Atividades (horario, horario_fim, restricao_idade, nome_atividade, data, funcionario_id) values ('10:40 AM', '8:08 AM', 8, 'Oficina de artesanato', '2022-11-29', 14);
insert into Atividades (horario, horario_fim, restricao_idade, nome_atividade, data, funcionario_id) values ('7:45 AM', '6:54 AM', 13, 'Oficina de jardinagem', '2023-01-18', 15);
insert into Atividades (horario, horario_fim, restricao_idade, nome_atividade, data, funcionario_id) values ('9:24 AM', '9:55 AM', 14, 'Caça ao tesouro', '2023-02-06', 16);
insert into Atividades (horario, horario_fim, restricao_idade, nome_atividade, data, funcionario_id) values ('10:31 AM', '5:12 AM', 6, 'Passeio de trator', '2022-11-27', 17);
insert into Atividades (horario, horario_fim, restricao_idade, nome_atividade, data, funcionario_id) values ('7:09 AM', '1:52 AM', 18, 'Visita ao mirante', '2022-05-23', 18);
insert into Atividades (horario, horario_fim, restricao_idade, nome_atividade, data, funcionario_id) values ('11:07 AM', '9:40 AM', 17, 'Teatro ao ar livre', '2022-11-01', 19);
insert into Atividades (horario, horario_fim, restricao_idade, nome_atividade, data, funcionario_id) values ('11:36 AM', '1:20 AM', 16, 'Pratica de cerâmica com guia', '2022-08-17', 20);

/*Tabela Servicos*/

insert into Servicos (preco_servico, nome_servico) values (249.96, 'Massagemn');
insert into Servicos (preco_servico, nome_servico) values (260.5, 'Frigobar');
insert into Servicos (preco_servico, nome_servico) values (129.43, 'Máquina de venda');
insert into Servicos (preco_servico, nome_servico) values (93.83, 'Café da manhã');
insert into Servicos (preco_servico, nome_servico) values (89.98, 'Sala de jogos');
insert into Servicos (preco_servico, nome_servico) values (108.29, 'Internet');
insert into Servicos (preco_servico, nome_servico) values (117.71, 'TV a cabo');
insert into Servicos (preco_servico, nome_servico) values (257.47, 'Aluguel de filmes');
insert into Servicos (preco_servico, nome_servico) values (47.94, 'Ar condicionado');
insert into Servicos (preco_servico, nome_servico) values (110.91, 'Kart');
insert into Servicos (preco_servico, nome_servico) values (57.17, 'Aula de surf');
insert into Servicos (preco_servico, nome_servico) values (56.23, 'Aula de hipismo');
insert into Servicos (preco_servico, nome_servico) values (18.3, 'Academia');
insert into Servicos (preco_servico, nome_servico) values (296.42, 'Festa Pagode');
insert into Servicos (preco_servico, nome_servico) values (189.84, 'Bailão');
insert into Servicos (preco_servico, nome_servico) values (246.18, 'Quiroprata');
insert into Servicos (preco_servico, nome_servico) values (252.25, 'Sauna');
insert into Servicos (preco_servico, nome_servico) values (229.48, 'Babás');
insert into Servicos (preco_servico, nome_servico) values (184.17, 'Lavanderia');
insert into Servicos (preco_servico, nome_servico) values (275.8, 'Aula de dança do ventre');

/*Tabela Atividades_hospede*/

insert into Atividades_hospede(Hospede_id, id_atividade)
values (1,1),
(2,2),
(3,3),
(4,4),
(5,5),
(6,6),
(7,7),
(8,8),
(9,9),
(10,10),
(11,11),
(12,12),
(13,13),
(14,14),
(15,15),
(16,16),
(17,17),
(18,18),
(19,19),
(20,20);


/*Tabela serviços consumidos*/

insert into servicos_consumidos (id_hospede, id_serviço, id_hospedagens)
values(1,1,1),
(2,2,2),
(3,3,3),
(4,4,4),
(5,5,5),
(6,6,6),
(7,7,7),
(8,8,8),
(9,9,9),
(10,10,10),
(11,11,11),
(12,12,12),
(13,13,13),
(14,14,14),
(15,15,15),
(16,16,16),
(17,17,17),
(18,18,18),
(19,19,19),
(20,20,20);

/*Tabela hospedagens_quartos*/

insert into Hospedagens_quartos (id_hospede, id_quartos, Hospedagens_id)
values(1,1,1),
(2,2,2),
(3,3,3),
(4,4,4),
(5,5,5),
(6,6,6),
(7,7,7),
(8,8,8),
(9,9,9),
(10,10,10),
(11,11,11),
(12,12,12),
(13,13,13),
(14,14,14),
(15,15,15),
(16,16,16),
(17,17,17),
(18,18,18),
(19,19,19),
(20,20,20);

/*Selects*/

Select count(*) from Usuarios;
Select count(*) from Hospedes;
Select count(*) from Servicos;
Select count(*) from Hospedagens;
Select count(*) from Quartos;
Select count(*) from Funcionarios;
Select count(*) from Atividades;
Select count(*) from servicos_consumidos;
Select count(*) from hospedagens_quartos;
Select count(*) from ATIVIDADES_HOSPEDE;

/*Select ALL*/

select * from Funcionarios;
select * from Usuarios;
select * from Atividades;
select * from Hospedes;
select * from Atividades_hospede;
select * from servicos_consumidos;
select * from servicos;
select * from Hospedagens;
select * from Hospedagens_quartos;
select * from Quartos;



/*Select Join*/

SELECT * FROM Funcionarios INNER JOIN Usuarios ON Funcionarios.id_usuario = Usuarios.id_usuario;

SELECT * FROM Funcionarios INNER JOIN Atividades ON Funcionarios.funcionario_id = Atividades.funcionario_id;

SELECT * FROM Usuarios INNER JOIN Hospedes ON Usuarios.id_usuario = Hospedes.id_usuario;

SELECT * FROM Atividades_hospede INNER JOIN Hospedes ON Atividades_hospede.Hospede_id = Hospedes.Hospede_id INNER JOIN Atividades ON Atividades_hospede.id_atividade = Atividades.id_atividade;

SELECT * FROM Hospedagens INNER JOIN Hospedes ON Hospedagens.Hospedagens_id = Hospedes.Hospede_id;

SELECT * FROM Hospedagens_quartos INNER JOIN Quartos ON Hospedagens_quartos.id_Quartos = Quartos.id_Quartos INNER JOIN Hospedagens ON Hospedagens_quartos.Hospedagens_id = Hospedagens.Hospedagens_id INNER JOIN Hospedes ON Hospedagens_quartos.Hospedagens_id = Hospede_id;

SELECT * FROM servicos_consumidos INNER JOIN Hospedes ON servicos_consumidos.id_hospede = Hospedes.hospede_id INNER JOIN servicos ON servicos_consumidos.id_serviço = servicos.id_servicos INNER JOIN Hospedagens ON servicos_consumidos.id_hospedagens = Hospedagens.Hospedagens_id;



/*Update funcionarios*/
update Funcionarios
set nome = 'Miguel', sobrenome = 'Almeida', funcao = 'Zelador', salario = 9016.38
where funcionario_id=1;
update Funcionarios
set nome = 'Junior', sobrenome = 'Silva', funcao = 'Camareiro', salario = 9526.14
where funcionario_id=2;
update Funcionarios
set nome = 'Marcos', sobrenome = 'Silva', funcao = 'Recepcionista', salario = 5890.71
where funcionario_id=3;
update Funcionarios
set nome = 'Romario', sobrenome = 'Mendes', funcao = 'Recepcionista', salario = 5890.71
where funcionario_id=4;
update Funcionarios
set nome = 'Lucas', sobrenome = 'Oliveira', funcao = 'Diarista', salario = 6212.93
where funcionario_id=5;
update Funcionarios
set nome = 'Sabrina', sobrenome = 'Lima', funcao = 'Secretaria', salario = 6232.93
where funcionario_id=6;
update Funcionarios
set nome = 'Ana', sobrenome = 'Amaral', funcao = 'Gerente', salario = 9734.61
where funcionario_id=7;
update Funcionarios
set nome = 'Rosalia', sobrenome = 'Helena', funcao = 'Jardineira', salario = 3400.61
where funcionario_id=8;
update Funcionarios
set nome = 'lucio', sobrenome = 'Carvalho', funcao = 'Faxineiro', salario = 4500.43
where funcionario_id=9;
update Funcionarios
set nome = 'Maria', sobrenome = 'Eliza', funcao = 'Recepcionista', salario = 6200.23
where funcionario_id=10;

/*Update Servicos*/
update Servicos
set preco_servico= 250.00, nome_servico='aula de boxe'
where id_servicos=1;
update Servicos
set preco_servico= 10.00, nome_servico='prato do dia'
where id_servicos=11;
update Servicos
set preco_servico= 157.09, nome_servico='aula de capoeira'
where id_servicos=7;
update Servicos
set preco_servico= 243.11, nome_servico='aula de futebol'
where id_servicos=12;
update Servicos
set preco_servico= 143.33, nome_servico='pôker'
where id_servicos=18;
update Servicos
set preco_servico= 111.11, nome_servico='aula de karatê'
where id_servicos=5;
update Servicos
set preco_servico= 250.00, nome_servico='aula de kickbox'
where id_servicos=9;
update Servicos
set preco_servico= 250.00, nome_servico='piscina'
where id_servicos=7;
update Servicos
set preco_servico= 132.00, nome_servico='sauna'
where id_servicos=19;
update Servicos
set preco_servico= 250.00, nome_servico='bugy'
where id_servicos=20;

/*Update Hospedagens*/
update Hospedagens
set checkin = '2023-12-13', checkout = '2023-12-16'
where  hospedagens_id = 1;
update Hospedagens
set checkin = '2023-07-01', checkout = '2023-07-07'
where  hospedagens_id = 2;
update Hospedagens
set checkin = '2023-02-09', checkout = '2023-02-14'
where  hospedagens_id = 3;
update Hospedagens
set checkin = '2023-05-16', checkout = '2023-05-25'
where  hospedagens_id = 4;
update Hospedagens
set checkin = '2023-08-30', checkout = '2023-09-05'
where  hospedagens_id = 5;
update Hospedagens
set checkin = '2023-06-04', checkout = '2023-06-10'
where  hospedagens_id = 6;
update Hospedagens
set checkin = '2023-03-12', checkout = '2023-03-17'
where  hospedagens_id = 7;
update Hospedagens
set checkin = '2023-03-16', checkout = '2023-03-20'
where  hospedagens_id = 8;
update Hospedagens
set checkin = '2023-04-04', checkout = '2023-04-07'
where  hospedagens_id = 9;
update Hospedagens
set checkin = '2023-10-10', checkout = '2023-10-15'
where  hospedagens_id = 10;

/*Update Usuários*/
update Usuarios
set senha = '12345',nivel_de_acesso = 1, login='Roncas'
where  id_usuario = 1;
update Usuarios
set senha = '67891',nivel_de_acesso = 0, login='Bernas'
where  id_usuario = 2;
update Usuarios
set senha = '101112',nivel_de_acesso = 1, login='Andras'
where  id_usuario = 3;
update Usuarios
set senha = '126890',nivel_de_acesso = 1, login='Luigigigie'
where  id_usuario = 4;
update Usuarios
set senha = '61701',nivel_de_acesso = 0, login= 'Muliro'
where  id_usuario = 5;
update Usuarios
set senha = '01234',nivel_de_acesso = 1, login='Gagas'
where  id_usuario = 6;
update Usuarios
set senha = '901890',nivel_de_acesso = 1, login='Gustas'
where  id_usuario = 7;
update Usuarios
set senha = '448768',nivel_de_acesso = 1, login='Kakas'
where  id_usuario = 8;
update Usuarios
set senha = '48765',nivel_de_acesso = 1, login='Charlão'
where  id_usuario = 9;
update Usuarios
set senha = '4165',nivel_de_acesso = 1, login='Clóvis'
where  id_usuario = 10;


/*Update Quartos*/

update Quartos
set max_pessoas = 1 , manutencao = 0 , tipo_cama = 'Solteiro', frigobar = 0 , ar_condicionado = 1 , banheira = 0 , tv = 0 , preco_quarto_dia = 925.50
where id_Quartos = 1;

update Quartos
set max_pessoas = 2 , manutencao = 1 , tipo_cama = 'Solteiro', frigobar = 1 , ar_condicionado = 0 , banheira = 1 , tv = 1 , preco_quarto_dia = 350.00
where id_Quartos = 2;

update Quartos
set max_pessoas = 3 , manutencao = 0 , tipo_cama = 'Casal', frigobar = 0 , ar_condicionado = 1 , banheira = 1 , tv = 1 , preco_quarto_dia = 700.00
where id_Quartos = 3;

update Quartos
set max_pessoas = 4 , manutencao = 1 , tipo_cama = 'Solteiro', frigobar = 1 , ar_condicionado = 1 , banheira = 1 , tv = 1 , preco_quarto_dia = 1500.50
where id_Quartos = 4;

update Quartos
set max_pessoas = 2 , manutencao = 0 , tipo_cama = 'Casal', frigobar = 1 , ar_condicionado = 1 , banheira = 0 , tv = 1 , preco_quarto_dia = 550.00
where id_Quartos = 5;

update Quartos
set max_pessoas = 4 , manutencao = 1 , tipo_cama = 'Solteiro', frigobar = 0 , ar_condicionado = 1 , banheira = 0 , tv = 0 , preco_quarto_dia = 550.00
where id_Quartos = 6;

update Quartos
set max_pessoas = 1 , manutencao = 0 , tipo_cama = 'Solteiro', frigobar = 1 , ar_condicionado = 1 , banheira = 1 , tv = 1 , preco_quarto_dia = 950.00
where id_Quartos = 7;

update Quartos
set max_pessoas = 2 , manutencao = 0 , tipo_cama = 'Casal', frigobar = 1 , ar_condicionado = 1 , banheira = 1 , tv = 1 , preco_quarto_dia = 1000.00
where id_Quartos = 8;

update Quartos
set max_pessoas = 4 , manutencao = 1 , tipo_cama = 'Casal', frigobar = 0 , ar_condicionado = 1 , banheira = 0 , tv = 0 , preco_quarto_dia = 350.00
where id_Quartos = 9;

update Quartos
set max_pessoas = 1 , manutencao = 0 , tipo_cama = 'Solteiro', frigobar = 1 , ar_condicionado = 1 , banheira = 1 , tv = 0 , preco_quarto_dia = 450.00
where id_Quartos = 10;


/*Update Servicos_CONSUMIDOS*/
update `SERVICOS_CONSUMIDOS`
set id_hospede= 1,id_serviço = 1, id_hospedagens=1
where  id_servicos_consumidos= 1;
update `SERVICOS_CONSUMIDOS`
set id_hospede= 2,id_serviço = 2, id_hospedagens=2
where  id_servicos_consumidos= 2;
update `SERVICOS_CONSUMIDOS`
set id_hospede= 3,id_serviço = 3, id_hospedagens=3
where  id_servicos_consumidos= 3;
update `SERVICOS_CONSUMIDOS`
set id_hospede= 4,id_serviço = 4, id_hospedagens=4
where  id_servicos_consumidos= 4;
update `SERVICOS_CONSUMIDOS`
set id_hospede= 5,id_serviço = 5, id_hospedagens=5
where  id_servicos_consumidos= 5;
update `SERVICOS_CONSUMIDOS`
set id_hospede= 6,id_serviço = 6, id_hospedagens=6
where  id_servicos_consumidos= 6;
update `SERVICOS_CONSUMIDOS`
set id_hospede= 7,id_serviço = 7, id_hospedagens=7
where  id_servicos_consumidos= 7;
update `SERVICOS_CONSUMIDOS`
set id_hospede= 8,id_serviço = 8, id_hospedagens=8
where  id_servicos_consumidos= 8;
update `SERVICOS_CONSUMIDOS`
set id_hospede= 9,id_serviço = 9, id_hospedagens=9
where  id_servicos_consumidos= 9;
update `SERVICOS_CONSUMIDOS`
set id_hospede= 10,id_serviço = 10, id_hospedagens=10
where  id_servicos_consumidos= 10;


/*update atividades*/

update Atividades
set nome_atividade='aula de boxe'
where id_atividade=1;

update Atividades
set nome_atividade ='aula de vôlei'
where id_atividade=11;

update Atividades
set nome_atividade= 'aula de capoeira'
where id_atividade=7;

update Atividades
set nome_atividade='aula de futebol'
where id_atividade=12;

update Atividades
set nome_atividade='pôker'
where id_atividade=18;

update Atividades
set nome_atividade='aula de karatê'
where id_atividade=5;

update Atividades
set nome_atividade='aula de kickbox'
where id_atividade=9;

update Atividades
set nome_atividade='piscina'
where id_atividade=7;

update Atividades
set nome_atividade='sauna'
where id_atividade=19;

update Atividades
set nome_atividade='bugy'
where id_atividade=20;

/*update Hospedagen_quartos*/

update Hospedagens_quartos
set id_quartos=1, Hospedagens_id=1, id_Hospede=1
where id_Hospedagem_quartos=1;


update Hospedagens_quartos
set id_quartos=2, Hospedagens_id=2, id_Hospede=2
where id_Hospedagem_quartos=2;


update Hospedagens_quartos
set id_quartos=3, Hospedagens_id=3, id_Hospede=3
where id_Hospedagem_quartos=3;


update Hospedagens_quartos
set id_quartos=4, Hospedagens_id=4, id_Hospede=4
where id_Hospedagem_quartos=4;


update Hospedagens_quartos
set id_quartos=5, Hospedagens_id=5, id_Hospede=5
where id_Hospedagem_quartos=5;


update Hospedagens_quartos
set id_quartos=6, Hospedagens_id=6, id_Hospede=6
where id_Hospedagem_quartos=6;


update Hospedagens_quartos
set id_quartos=7, Hospedagens_id=7, id_Hospede=7
where id_Hospedagem_quartos=7;


update Hospedagens_quartos
set id_quartos=8, Hospedagens_id=8, id_Hospede=8
where id_Hospedagem_quartos=8;


update Hospedagens_quartos
set id_quartos=9, Hospedagens_id=9, id_Hospede=9
where id_Hospedagem_quartos=9;


update Hospedagens_quartos
set id_quartos=1, Hospedagens_id=10, id_Hospede=10
where id_Hospedagem_quartos=10;

/*update Atividades_hospede*/

update Atividades_hospede
set Hospede_id=1, id_atividade=1
where id_hospede_atividade=1;

update Atividades_hospede
set  Hospede_id=2, id_atividade=2
where id_hospede_atividade=2;

update Atividades_hospede
set  Hospede_id=3, id_atividade=3
where id_hospede_atividade=3;

update Atividades_hospede
set  Hospede_id=4, id_atividade=4
where id_hospede_atividade=4;

update Atividades_hospede
set  Hospede_id=5, id_atividade=5
where id_hospede_atividade=5;

update Atividades_hospede
set  Hospede_id=6, id_atividade=6
where id_hospede_atividade=6;

update Atividades_hospede
set  Hospede_id=7, id_atividade=7
where id_hospede_atividade=7;

update Atividades_hospede
set  Hospede_id=8, id_atividade=8
where id_hospede_atividade=8;

update Atividades_hospede
set  Hospede_id=9, id_atividade=9
where id_hospede_atividade=9;

update Atividades_hospede
set  Hospede_id=10, id_atividade=10
where id_hospede_atividade=10;


/*update Hospede*/

Update Hospedes
set nome = 'Jorge', sobrenome = 'Oliveira', data_nasc = '2000-03-03', CPF = '111.222.333-44', Nacionalidade = 'Brasileiro', Pronome = 'Ele/dele', email = 'jorge013@gmail.com', id_usuario = 1
where  Hospede_id = 1;

Update Hospedes
set nome = 'João', sobrenome = 'Souza', data_nasc = '2001-02-07', Nacionalidade = 'Americano', Pronome = 'Ele/dele', email = 'joaop12@gmail.com', id_usuario = 2
where  Hospede_id = 2;

Update Hospedes
set nome = 'Maria', sobrenome = 'Rocha', data_nasc = '2002-11-11', CPF = '111.222.333-41', Nacionalidade = 'Brasileiro', Pronome = 'Ela/dela', email = 'maria222@gmail.com', id_usuario = 3
where  Hospede_id = 3;

Update Hospedes
set nome = 'Andrei', sobrenome = 'Amaral', data_nasc = '2003-09-17', Nacionalidade = 'Americano', Pronome = 'Ele/dele', email = 'AndreiAm@gmail.com', id_usuario = 4
where  Hospede_id = 4;

Update Hospedes
set nome = 'Gustavo', sobrenome = 'Luiz', data_nasc = '2004-12-22', CPF = '111.222.333-45', Nacionalidade = 'Brasileiro', Pronome = 'Ele/dele', email = 'gusta942@gmail.com', id_usuario = 5
where  Hospede_id = 5;

Update Hospedes
set nome = 'Bernardo', sobrenome = 'Oliveira', data_nasc = '2001-01-26', CPF = '111.222.333-46', Nacionalidade = 'Brasileiro', Pronome = 'Ele/dele', email = 'be.d0aa@gmail.com', id_usuario = 6
where  Hospede_id = 6;

Update Hospedes
set nome = 'Erik', sobrenome = 'Roncaglio', data_nasc = '2005-06-30', CPF = '111.222.333-47', Nacionalidade = 'Brasileiro', Pronome = 'Ele/dele', email = 'erik.r01@gmail.com', id_usuario = 7
where  Hospede_id = 7;

Update Hospedes
set nome = 'Gabriel', sobrenome = 'Mohr', data_nasc = '2003-04-15', CPF = '111.222.333-48', Nacionalidade = 'Brasileiro', Pronome = 'Ele/dele', email = 'gab328n@gmail.com', id_usuario = 8
where  Hospede_id = 8;

Update Hospedes
set nome = 'Luigi', sobrenome = 'Scharam', data_nasc = '2002-07-02', CPF = '111.222.333-49', Nacionalidade = 'Brasileiro', Pronome = 'Ele/dele', email = 'luigi23@gmail.com', id_usuario = 9
where  Hospede_id = 9;

Update Hospedes
set nome = 'Muliro', sobrenome = 'Silveira', data_nasc = '2004-10-18', CPF = '111.222.333-50', Nacionalidade = 'Brasileiro', Pronome = 'Ele/dele', email = 'mulibro248@gmail.com', id_usuario = 10
where  Hospede_id = 10;

/* Deletes */

SET foreign_key_checks = 0;

/* Delete usuarios*/

DELETE FROM Usuarios WHERE id_usuario = 1;
DELETE FROM Usuarios WHERE id_usuario = 2;
DELETE FROM Usuarios WHERE id_usuario = 3;
DELETE FROM Usuarios WHERE id_usuario = 4;
DELETE FROM Usuarios WHERE id_usuario = 5;

/* Delete Hospedes*/

DELETE FROM Hospedes WHERE Hospede_id = 1;
DELETE FROM Hospedes WHERE Hospede_id = 2;
DELETE FROM Hospedes WHERE Hospede_id = 3;
DELETE FROM Hospedes WHERE Hospede_id = 4;
DELETE FROM Hospedes WHERE Hospede_id = 5;

/* Delete Atividades*/

DELETE FROM Atividades Where id_atividade = 1;
DELETE FROM Atividades Where id_atividade = 2;
DELETE FROM Atividades Where id_atividade = 3;
DELETE FROM Atividades Where id_atividade = 4;
DELETE FROM Atividades Where id_atividade = 5;

/* Delete Hospedagens*/

DELETE FROM Hospedagens Where Hospedagens_id = 1;
DELETE FROM Hospedagens Where Hospedagens_id = 2;
DELETE FROM Hospedagens Where Hospedagens_id = 3;
DELETE FROM Hospedagens Where Hospedagens_id = 4;
DELETE FROM Hospedagens Where Hospedagens_id = 5;

/* Delete Servicos*/

DELETE FROM servicos Where id_servicos = 1;
DELETE FROM servicos Where id_servicos = 2;
DELETE FROM servicos Where id_servicos = 3;
DELETE FROM servicos Where id_servicos = 4;
DELETE FROM servicos Where id_servicos = 5;

/* Delete Servicos_consumidos*/

DELETE FROM servicos_consumidos Where id_servicos_consumidos = 1;
DELETE FROM servicos_consumidos Where id_servicos_consumidos = 2;
DELETE FROM servicos_consumidos Where id_servicos_consumidos = 3;
DELETE FROM servicos_consumidos Where id_servicos_consumidos = 4;
DELETE FROM servicos_consumidos Where id_servicos_consumidos = 5;

/* Delete Quartos */

DELETE FROM Quartos Where id_Quartos = 1;
DELETE FROM Quartos Where id_Quartos = 2;
DELETE FROM Quartos Where id_Quartos = 3;
DELETE FROM Quartos Where id_Quartos = 4;
DELETE FROM Quartos Where id_Quartos = 5;

/* Delete Atividades_hospede */

DELETE FROM Atividades_hospede Where id_hospede_atividade = 1;
DELETE FROM Atividades_hospede Where id_hospede_atividade = 2;
DELETE FROM Atividades_hospede Where id_hospede_atividade = 3;
DELETE FROM Atividades_hospede Where id_hospede_atividade = 4;
DELETE FROM Atividades_hospede Where id_hospede_atividade = 5;

/* Delete Hospedagens_quartos */

DELETE FROM Hospedagens_quartos Where id_Hospedagem_quartos = 1;
DELETE FROM Hospedagens_quartos Where id_Hospedagem_quartos = 2;
DELETE FROM Hospedagens_quartos Where id_Hospedagem_quartos = 3;
DELETE FROM Hospedagens_quartos Where id_Hospedagem_quartos = 4;
DELETE FROM Hospedagens_quartos Where id_Hospedagem_quartos = 5;

/* Delete Funcionarios */

DELETE FROM Funcionarios Where funcionario_id = 1;
DELETE FROM Funcionarios Where funcionario_id = 2;
DELETE FROM Funcionarios Where funcionario_id = 3;
DELETE FROM Funcionarios Where funcionario_id = 4;
DELETE FROM Funcionarios Where funcionario_id = 5;
