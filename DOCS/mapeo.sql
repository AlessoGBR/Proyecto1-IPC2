DROP DATABASE IF EXISTS Proyecto1;
CREATE DATABASE IF NOT EXISTS Proyecto1;
USE Proyecto1;


DROP TABLE IF EXISTS `TipoUsuario`;
CREATE TABLE `TipoUsuario` (
  `idTipoUsuario` int NOT NULL AUTO_INCREMENT,
  `nombre_tipo` varchar(15) NOT NULL,
  PRIMARY KEY (`idTipoUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `Usuario`;
CREATE TABLE `Usuario` (
  `nombre_usuario` varchar(50) NOT NULL,
  `password` varchar(200) NOT NULL,
  `idTipoUsuario` int NOT NULL,
  PRIMARY KEY (`nombre_usuario`,`idTipoUsuario`),
  KEY `fk_Usuario_TipoUsuario1_idx` (`idTipoUsuario`),
  CONSTRAINT `fk_Usuario_TipoUsuario1` FOREIGN KEY (`idTipoUsuario`) REFERENCES `TipoUsuario` (`idTipoUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `Perfil`;
CREATE TABLE `Perfil` (
  `idPerfil` int NOT NULL AUTO_INCREMENT,
  `fotoPerfil` text,
  `descripcion` varchar(200) DEFAULT NULL,
  `nombre_usuario` varchar(50) NOT NULL,
  PRIMARY KEY (`idPerfil`,`nombre_usuario`),
  KEY `fk_Perfil_Usuario_idx` (`nombre_usuario`),
  CONSTRAINT `fk_Perfil_Usuario` FOREIGN KEY (`nombre_usuario`) REFERENCES `Usuario` (`nombre_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `Etiqueta`;
CREATE TABLE `Etiqueta` (
  `nombre_etiqueta` varchar(50) NOT NULL,
  PRIMARY KEY (`nombre_etiqueta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `Categoria`;
CREATE TABLE `Categoria` (
  `nombre_Categoria` varchar(50) NOT NULL,
  PRIMARY KEY (`nombre_Categoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `Perfil_Categoria`;
CREATE TABLE `Perfil_Categoria` (
  `idPerfil` int NOT NULL,
  `nombre_usuario` varchar(50) NOT NULL,
  `nombre_Categoria` varchar(50) NOT NULL,
  PRIMARY KEY (`idPerfil`,`nombre_usuario`,`nombre_Categoria`),
  KEY `fk_table1_Categoria2_idx` (`nombre_Categoria`),
  CONSTRAINT `fk_table1_Categoria2` FOREIGN KEY (`nombre_Categoria`) REFERENCES `Categoria` (`nombre_Categoria`),
  CONSTRAINT `fk_table1_Perfil1` FOREIGN KEY (`idPerfil`, `nombre_usuario`) REFERENCES `Perfil` (`idPerfil`, `nombre_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `Perfil_Etiquetas`;
CREATE TABLE `Perfil_Etiquetas` (
  `nombre_etiqueta` varchar(50) NOT NULL,
  `idPerfil` int NOT NULL,
  `nombre_usuario` varchar(50) NOT NULL,
  PRIMARY KEY (`nombre_etiqueta`,`idPerfil`,`nombre_usuario`),
  KEY `fk_Perfil_Etiquetas_Perfil1_idx` (`idPerfil`,`nombre_usuario`),
  CONSTRAINT `fk_Perfil_Etiquetas_Etiqueta1` FOREIGN KEY (`nombre_etiqueta`) REFERENCES `Etiqueta` (`nombre_etiqueta`),
  CONSTRAINT `fk_Perfil_Etiquetas_Perfil1` FOREIGN KEY (`idPerfil`, `nombre_usuario`) REFERENCES `Perfil` (`idPerfil`, `nombre_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `Revista`;
CREATE TABLE `Revista` (
  `idRevista` int NOT NULL AUTO_INCREMENT,
  `revista` text,
  `titulo` varchar(50) NOT NULL,
  `descripcion` varchar(150) NOT NULL,
  `no_version` varchar(50) DEFAULT NULL,
  `aprobada` tinyint NOT NULL,
  `suscripciones` tinyint NOT NULL,
  `comentarios` tinyint NOT NULL,
  `reacciones` tinyint NOT NULL,
  `fecha` date DEFAULT NULL,
  `nombre_usuario` varchar(50) NOT NULL,
  `denegada` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`idRevista`,`nombre_usuario`),
  KEY `fk_Revista_Usuario1_idx` (`nombre_usuario`),
  CONSTRAINT `fk_Revista_Usuario1` FOREIGN KEY (`nombre_usuario`) REFERENCES `Usuario` (`nombre_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `Comentario`;
CREATE TABLE `Comentario` (
  `idComentario` int NOT NULL AUTO_INCREMENT,
  `comentario` varchar(200) NOT NULL,
  `fecha` date NOT NULL,
  `nombre_usuario` varchar(50) NOT NULL,
  PRIMARY KEY (`idComentario`,`nombre_usuario`),
  KEY `fk_Comentario_Usuario1_idx` (`nombre_usuario`),
  CONSTRAINT `fk_Comentario_Usuario1` FOREIGN KEY (`nombre_usuario`) REFERENCES `Usuario` (`nombre_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `Reaccion`;
CREATE TABLE `Reaccion` (
  `idReaccion` int NOT NULL AUTO_INCREMENT,
  `reacciones` tinyint NOT NULL,
  `fecha` date NOT NULL,
  `nombre_usuario` varchar(50) NOT NULL,
  PRIMARY KEY (`idReaccion`,`nombre_usuario`),
  KEY `fk_Reaccion_Usuario1_idx` (`nombre_usuario`),
  CONSTRAINT `fk_Reaccion_Usuario1` FOREIGN KEY (`nombre_usuario`) REFERENCES `Usuario` (`nombre_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `Etiqueta_Revista`;
CREATE TABLE `Etiqueta_Revista` (
  `nombre_etiqueta` varchar(50) NOT NULL,
  `idRevista` int NOT NULL,
  PRIMARY KEY (`nombre_etiqueta`,`idRevista`),
  KEY `fk_Etiqueta_Revista_Etiqueta1_idx` (`nombre_etiqueta`),
  KEY `fk_Etiqueta_Revista_Revista1_idx` (`idRevista`),
  CONSTRAINT `fk_Etiqueta_Revista_Etiqueta1` FOREIGN KEY (`nombre_etiqueta`) REFERENCES `Etiqueta` (`nombre_etiqueta`),
  CONSTRAINT `fk_Etiqueta_Revista_Revista1` FOREIGN KEY (`idRevista`) REFERENCES `Revista` (`idRevista`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `Categoria_Revista`;
CREATE TABLE `Categoria_Revista` (
  `nombre_Categoria` varchar(50) NOT NULL,
  `idRevista` int NOT NULL,
  PRIMARY KEY (`nombre_Categoria`,`idRevista`),
  KEY `fk_table1_Revista1_idx` (`idRevista`),
  CONSTRAINT `fk_table1_Categoria1` FOREIGN KEY (`nombre_Categoria`) REFERENCES `Categoria` (`nombre_Categoria`),
  CONSTRAINT `fk_table1_Revista1` FOREIGN KEY (`idRevista`) REFERENCES `Revista` (`idRevista`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `Comentario_Revista`;
CREATE TABLE `Comentario_Revista` (
  `idComentario` int NOT NULL,
  `nombre_usuario` varchar(50) NOT NULL,
  `idRevista` int NOT NULL,
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`idComentario`,`nombre_usuario`,`idRevista`),
  KEY `fk_comentario_Revista_Revista1_idx` (`idRevista`),
  CONSTRAINT `fk_comentario_Revista_Comentario1` FOREIGN KEY (`idComentario`, `nombre_usuario`) REFERENCES `Comentario` (`idComentario`, `nombre_usuario`),
  CONSTRAINT `fk_comentario_Revista_Revista1` FOREIGN KEY (`idRevista`) REFERENCES `Revista` (`idRevista`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `Reaccion_Revista`;
CREATE TABLE `Reaccion_Revista` (
  `idReaccion` int NOT NULL,
  `idRevista` int NOT NULL,
  `nombre_usuario` varchar(50) NOT NULL,
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`idReaccion`,`idRevista`,`nombre_usuario`),
  KEY `fk_Reaccion_Revista_Revista1_idx` (`idRevista`,`nombre_usuario`),
  KEY `fk_Reaccion_Revista_Reaccion1` (`idReaccion`,`nombre_usuario`),
  CONSTRAINT `fk_Reaccion_Revista_Reaccion1` FOREIGN KEY (`idReaccion`, `nombre_usuario`) REFERENCES `Reaccion` (`idReaccion`, `nombre_usuario`),
  CONSTRAINT `fk_Reaccion_Revista_Revista1` FOREIGN KEY (`idRevista`) REFERENCES `Revista` (`idRevista`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `Suscripcion`;
CREATE TABLE `Suscripcion` (
  `idSuscripcion` int NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `nombre_usuario` varchar(50) NOT NULL,
  `idRevista` int NOT NULL,
  PRIMARY KEY (`idSuscripcion`,`nombre_usuario`,`idRevista`),
  KEY `fk_Suscripcion_Usuario1_idx` (`nombre_usuario`),
  KEY `fk_Suscripcion_Revista1_idx` (`idRevista`),
  CONSTRAINT `fk_Suscripcion_Revista1` FOREIGN KEY (`idRevista`) REFERENCES `Revista` (`idRevista`),
  CONSTRAINT `fk_Suscripcion_Usuario1` FOREIGN KEY (`nombre_usuario`) REFERENCES `Usuario` (`nombre_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `Anunciante`;
CREATE TABLE `Anunciante` (
  `idAnunciante` int NOT NULL AUTO_INCREMENT,
  `cartera` double DEFAULT NULL,
  `nombre_usuario` varchar(50) NOT NULL,
  PRIMARY KEY (`idAnunciante`,`nombre_usuario`),
  KEY `fk_Anunciante_Usuario1_idx` (`nombre_usuario`),
  CONSTRAINT `fk_Anunciante_Usuario1` FOREIGN KEY (`nombre_usuario`) REFERENCES `Usuario` (`nombre_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `Anuncio`;
CREATE TABLE `Anuncio` (
  `idAnuncio` int NOT NULL AUTO_INCREMENT,
  `texto` mediumtext,
  `url_video` varchar(200) DEFAULT NULL,
  `path_imagen` varchar(200) DEFAULT NULL,
  `activo` tinyint DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `fecha_final` date DEFAULT NULL,
  `pago` double DEFAULT NULL,
  `nombre_anunciante` int NOT NULL,
  `tipo` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idAnuncio`,`nombre_anunciante`),
  KEY `fk_Anuncio_Anunciante1_idx` (`nombre_anunciante`),
  CONSTRAINT `fk_Anuncio_Anunciante1` FOREIGN KEY (`nombre_anunciante`) REFERENCES `Anunciante` (`idAnunciante`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `Anuncio_Etiquetas`;
CREATE TABLE `Anuncio_Etiquetas` (
  `idAnuncio` int NOT NULL,
  `nombre_etiqueta` varchar(50) NOT NULL,
  PRIMARY KEY (`idAnuncio`,`nombre_etiqueta`),
  KEY `fk_Anuncio_Etiquetas_Etiqueta1_idx` (`nombre_etiqueta`),
  CONSTRAINT `fk_Anuncio_Etiquetas_Anuncio1` FOREIGN KEY (`idAnuncio`) REFERENCES `Anuncio` (`idAnuncio`),
  CONSTRAINT `fk_Anuncio_Etiquetas_Etiqueta1` FOREIGN KEY (`nombre_etiqueta`) REFERENCES `Etiqueta` (`nombre_etiqueta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO TipoUsuario(nombre_tipo) VALUES("Administrador");
INSERT INTO TipoUsuario(nombre_tipo) VALUES("Lector");
INSERT INTO TipoUsuario(nombre_tipo) VALUES("Editor");
INSERT INTO TipoUsuario(nombre_tipo) VALUES("Anunciante");

INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Ropa");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Comida");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Carros");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Animales");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Perro");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Cocina");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Carpinteria");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Tecnología");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Computadoras");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Monitor");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Mouse");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Silla");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Mesa");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Audifonos");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Case");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Ventilador");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Gorra");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Real Madrid");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Barcelona");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("PSG");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Messi");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Cristiano Ronaldo");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Programación");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("USAC");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("CUNOC");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Ingeniería");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Base de Datos");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Thanos");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Baseball");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Gato");

