CREATE DATABASE  IF NOT EXISTS `Gimnasio` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `Gimnasio`;
-- MariaDB dump 10.19  Distrib 10.6.16-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: Gimnasio
-- ------------------------------------------------------
-- Server version	8.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Cliente`
--

DROP TABLE IF EXISTS `Cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Cliente` (
  `idcliente` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) DEFAULT NULL,
  `Apellidos` varchar(45) DEFAULT NULL,
  `Fecha Nacimiento` varchar(45) DEFAULT NULL,
  `Lesiones` varchar(45) DEFAULT NULL,
  `Objetivo` varchar(45) DEFAULT NULL,
  `idEntrenador` int DEFAULT NULL,
  PRIMARY KEY (`idcliente`),
  KEY `fk_Cliente_1_idx` (`idEntrenador`),
  CONSTRAINT `fk_Cliente_1` FOREIGN KEY (`idEntrenador`) REFERENCES `Entrenador` (`idEntrenador`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cliente`
--

LOCK TABLES `Cliente` WRITE;
/*!40000 ALTER TABLE `Cliente` DISABLE KEYS */;
INSERT INTO `Cliente` VALUES (1,'Pau','Rodrigo','17/05/2005','Si','A topeeee',1);
/*!40000 ALTER TABLE `Cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Cliente_SEQ`
--

DROP TABLE IF EXISTS `Cliente_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Cliente_SEQ` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cliente_SEQ`
--

LOCK TABLES `Cliente_SEQ` WRITE;
/*!40000 ALTER TABLE `Cliente_SEQ` DISABLE KEYS */;
INSERT INTO `Cliente_SEQ` VALUES (1);
/*!40000 ALTER TABLE `Cliente_SEQ` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Ejercicio`
--

DROP TABLE IF EXISTS `Ejercicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Ejercicio` (
  `idEjercicio` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) DEFAULT NULL,
  `Peso` double DEFAULT NULL,
  `Series` int DEFAULT NULL,
  `Repeticiones` int DEFAULT NULL,
  `Descanso` varchar(45) DEFAULT NULL,
  `Dificultad` varchar(45) DEFAULT NULL,
  `idjercicio` int NOT NULL,
  PRIMARY KEY (`idEjercicio`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Ejercicio`
--

LOCK TABLES `Ejercicio` WRITE;
/*!40000 ALTER TABLE `Ejercicio` DISABLE KEYS */;
INSERT INTO `Ejercicio` VALUES (1,'Abs',0,3,12,'15\'\'','Facil',0);
/*!40000 ALTER TABLE `Ejercicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Ejercicio_SEQ`
--

DROP TABLE IF EXISTS `Ejercicio_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Ejercicio_SEQ` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Ejercicio_SEQ`
--

LOCK TABLES `Ejercicio_SEQ` WRITE;
/*!40000 ALTER TABLE `Ejercicio_SEQ` DISABLE KEYS */;
INSERT INTO `Ejercicio_SEQ` VALUES (1);
/*!40000 ALTER TABLE `Ejercicio_SEQ` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Entrenador`
--

DROP TABLE IF EXISTS `Entrenador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Entrenador` (
  `idEntrenador` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idEntrenador`)
) ENGINE=InnoDB AUTO_INCREMENT=211 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Entrenador`
--

LOCK TABLES `Entrenador` WRITE;
/*!40000 ALTER TABLE `Entrenador` DISABLE KEYS */;
INSERT INTO `Entrenador` VALUES (1,'Pepe'),(2,'Juan'),(3,'Paco'),(52,'fd'),(102,''),(152,''),(202,'Juanka'),(203,'pp'),(204,'pp'),(205,'pp'),(206,'pp'),(207,'pp'),(208,'pp'),(209,'pp'),(210,'pp');
/*!40000 ALTER TABLE `Entrenador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Entrenador_SEQ`
--

DROP TABLE IF EXISTS `Entrenador_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Entrenador_SEQ` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Entrenador_SEQ`
--

LOCK TABLES `Entrenador_SEQ` WRITE;
/*!40000 ALTER TABLE `Entrenador_SEQ` DISABLE KEYS */;
INSERT INTO `Entrenador_SEQ` VALUES (301);
/*!40000 ALTER TABLE `Entrenador_SEQ` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-30 18:44:10
