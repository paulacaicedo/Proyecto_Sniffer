-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: proyectobd
-- ------------------------------------------------------
-- Server version	5.7.31-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `consulta`
--

DROP TABLE IF EXISTS `consulta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consulta` (
  `id_consulta` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificador único de la consulta que realiza el usuario.',
  `fecha_inicio` datetime NOT NULL COMMENT 'Campo que funciona como filtro, en este caso es el rango inicial.',
  `tiempoConsulta` int(11) NOT NULL COMMENT 'Campo que funciona como filtro. En este caso es el rango final.',
  `USUARIO_id_usuario` int(11) NOT NULL,
  PRIMARY KEY (`id_consulta`,`USUARIO_id_usuario`),
  KEY `fk_CONSULTA_USUARIO1_idx` (`USUARIO_id_usuario`),
  CONSTRAINT `fk_CONSULTA_USUARIO1` FOREIGN KEY (`USUARIO_id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1 COMMENT='Tabla que registra las consultas hechas por un usuario. La consulta se hace dentro de un rango de tiempo.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consulta`
--

LOCK TABLES `consulta` WRITE;
/*!40000 ALTER TABLE `consulta` DISABLE KEYS */;
INSERT INTO `consulta` VALUES (1,'2021-10-21 00:00:00',5,2),(2,'2021-10-21 00:00:00',5,2),(3,'2021-10-21 00:00:00',5,2),(4,'2021-10-21 00:00:00',5,2),(5,'2021-10-21 00:00:00',5,2),(6,'2021-10-21 00:00:00',5,2),(7,'2021-10-21 00:00:00',5,2),(8,'2021-10-21 00:00:00',5,2),(9,'2021-10-21 00:00:00',5,2),(10,'2021-10-21 00:00:00',5,2),(11,'2021-10-21 00:00:00',5,2),(12,'2021-10-21 00:00:00',5,2),(13,'2021-10-21 00:00:00',5,1),(14,'2021-10-21 00:00:00',5,1),(15,'2021-10-21 00:00:00',5,1);
/*!40000 ALTER TABLE `consulta` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-21 19:00:53
