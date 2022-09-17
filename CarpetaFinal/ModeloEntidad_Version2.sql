 

 
CREATE TABLE `USUARIO` (
  `id_usuario` INT NOT NULL COMMENT 'Identificador único de usuario',
  `contraseña` VARCHAR(45) NOT NULL COMMENT 'contraseña a la que accede al sistema. la contrasela se almacena cifrada de acuerdo a las politicas de seguridad',
  PRIMARY KEY (`id_usuario`))
COMMENT = 'La tabla que almacena a todos los usuarios registrados en el sistema.';



CREATE TABLE `TIPO_USUARIO` (
  `id_tipo_usuario` INT NOT NULL COMMENT 'Identificador único de tipo de usuario.',
  `descripción` VARCHAR(45) NULL COMMENT 'la descripcion del rol, que adquiere cada usuario. Como primero modelo en el sistema cuenta con dos roles:\nAdministrador\nDashboard',
  PRIMARY KEY (`id_tipo_usuario`))
COMMENT = 'Tabla que contiene los tipos de usuario que se manejan.';


CREATE TABLE `CONSULTA` (
  `id_consulta` INT NOT NULL AUTO_INCREMENT COMMENT 'Identificador único de la consulta que realiza el usuario.',
  `fecha_inicio` DATETIME NOT NULL COMMENT 'Campo que funciona como filtro, en este caso es el rango inicial.',
  `tiempoConsulta` INT NOT NULL COMMENT 'Campo que funciona como filtro. En este caso es el rango final.',
  `USUARIO_id_usuario` INT NOT NULL,
  PRIMARY KEY (`id_consulta`, `USUARIO_id_usuario`),
  INDEX `fk_CONSULTA_USUARIO1_idx` (`USUARIO_id_usuario` ASC) ,
  CONSTRAINT `fk_CONSULTA_USUARIO1`
    FOREIGN KEY (`USUARIO_id_usuario`)
    REFERENCES `USUARIO` (`id_usuario`))
COMMENT = 'Tabla que registra las consultas hechas por un usuario. La consulta se hace dentro de un rango de tiempo.';



CREATE TABLE `USUARIO_has_CONSULTA` (
  `USUARIO_id_usuario` INT NOT NULL COMMENT 'Identificador unico del usuario, relacionado a la tabla usuario',
  `CONSULTAS_id_consulta` INT NOT NULL COMMENT 'Identificador unico de la consulta que realiza el usuario, relacionada con la tabla de consultas',
  PRIMARY KEY (`USUARIO_id_usuario`, `CONSULTAS_id_consulta`),
  INDEX `fk_USUARIO_has_CONSULTAS_CONSULTAS1_idx` (`CONSULTAS_id_consulta` ASC) ,
  INDEX `fk_USUARIO_has_CONSULTAS_USUARIO1_idx` (`USUARIO_id_usuario` ASC) ,
  CONSTRAINT `fk_USUARIO_has_CONSULTAS_USUARIO1`
    FOREIGN KEY (`USUARIO_id_usuario`)
    REFERENCES `USUARIO` (`id_usuario`),
  CONSTRAINT `fk_USUARIO_has_CONSULTAS_CONSULTAS1`
    FOREIGN KEY (`CONSULTAS_id_consulta`)
    REFERENCES `CONSULTA` (`id_consulta`));



CREATE TABLE`SERVICIO` (
  `id_servicio` INT NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(45) NULL,
  PRIMARY KEY (`id_servicio`));


CREATE TABLE `CAPTURA` (
  `id_captura` INT NOT NULL AUTO_INCREMENT,
  `direccionFisica` VARCHAR(45) NULL,
  `DireccionIp_source` VARCHAR(45) NULL,
  `direccionIp_destino` VARCHAR(45) NULL,
  `puertoDestino` VARCHAR(45) NULL,
  `id_servicio` INT NOT NULL,
  PRIMARY KEY (`id_captura`, `id_servicio`),
  INDEX `fk_Captura_Servicio1_idx` (`id_servicio` ASC) ,
  CONSTRAINT `fk_Captura_Servicio1`
    FOREIGN KEY (`id_servicio`)
    REFERENCES `SERVICIO` (`id_servicio`));



CREATE TABLE `PAQUETE` (
  `id_paquete` INT NOT NULL AUTO_INCREMENT,
  `longitud` INT NULL,
  `fechaCaptura` VARCHAR(50) NULL,
  `id_consulta` INT NOT NULL,
  `id_captura` INT NOT NULL,
  `id_servicio` INT NOT NULL,
  PRIMARY KEY (`id_paquete`, `id_consulta`, `id_captura`, `id_servicio`),
  INDEX `fk_PAQUETE_CONSULTAS1_idx` (`id_consulta` ASC) ,
  INDEX `fk_PAQUETE_CAPTURA1_idx` (`id_captura` ASC, `id_servicio` ASC) ,
  CONSTRAINT `fk_PAQUETE_CONSULTAS1`
    FOREIGN KEY (`id_consulta`)
    REFERENCES `CONSULTA` (`id_consulta`),
  CONSTRAINT `fk_PAQUETE_CAPTURA1`
    FOREIGN KEY (`id_captura` , `id_servicio`)
    REFERENCES `CAPTURA` (`id_captura` , `id_servicio`));



CREATE TABLE `PROTOCOLO` (
  `id_protocolo` INT NOT NULL,
  `descripcion` VARCHAR(45) NULL,
  PRIMARY KEY (`id_protocolo`));



CREATE TABLE `CAPTURA_has_PROTOCOLO` (
  `CAPTURA_id_captura` INT NOT NULL,
  `CAPTURA_Servicio_id_servicio` INT NOT NULL,
  `PROTOCOLO_id_protocolo` INT NOT NULL,
  PRIMARY KEY (`CAPTURA_id_captura`, `CAPTURA_Servicio_id_servicio`, `PROTOCOLO_id_protocolo`),
  INDEX `fk_CAPTURA_has_PROTOCOLO_PROTOCOLO1_idx` (`PROTOCOLO_id_protocolo` ASC) VISIBLE,
  INDEX `fk_CAPTURA_has_PROTOCOLO_CAPTURA1_idx` (`CAPTURA_id_captura` ASC, `CAPTURA_Servicio_id_servicio` ASC) ,
  CONSTRAINT `fk_CAPTURA_has_PROTOCOLO_CAPTURA1`
    FOREIGN KEY (`CAPTURA_id_captura` , `CAPTURA_Servicio_id_servicio`)
    REFERENCES `CAPTURA` (`id_captura` , `id_servicio`),
  CONSTRAINT `fk_CAPTURA_has_PROTOCOLO_PROTOCOLO1`
    FOREIGN KEY (`PROTOCOLO_id_protocolo`)
    REFERENCES `PROTOCOLO` (`id_protocolo`));




