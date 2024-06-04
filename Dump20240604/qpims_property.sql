-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: qpims
-- ------------------------------------------------------
-- Server version	8.3.0

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
-- Table structure for table `property`
--

DROP TABLE IF EXISTS `property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `property` (
  `PropertyID` int NOT NULL AUTO_INCREMENT,
  `StreetName` varchar(100) NOT NULL,
  `StreetNumber` int NOT NULL,
  `Suburb` varchar(45) NOT NULL,
  `State` varchar(45) NOT NULL,
  `BuiltYear` date NOT NULL,
  `Bathrooms` int DEFAULT NULL,
  `Bedrooms` int DEFAULT NULL,
  `CarParks` int DEFAULT NULL,
  `PropertyManager` varchar(45) DEFAULT NULL,
  `PropertyType` varchar(45) NOT NULL,
  `Customer_CustomerID` int DEFAULT NULL,
  PRIMARY KEY (`PropertyID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `property`
--

LOCK TABLES `property` WRITE;
/*!40000 ALTER TABLE `property` DISABLE KEYS */;
INSERT INTO `property` VALUES (10,'Calder Street',1,'Pak Avenue','QLD','1990-03-04',1,2,2,'Eliza','House',15),(11,'Jhonny',2,'Sins','QLD','2004-03-12',2,3,2,'Mia Khalifa','House',15),(12,'qwdf',1,'qwdf','QLD','2004-03-07',2,2,3,'sac','House',NULL),(13,'Tirth',2,'Beserker','QLD','2004-05-07',1,1,1,'Pat','House',NULL),(14,'Tirth',12,'Cullen','QLD','2004-04-06',3,2,2,'Pat','House',15),(15,'Calder',12,'Park Ave','QLD','2004-05-06',2,2,2,'Mountain','House',NULL),(16,'Calder',12,'Park Ave','QLD','2004-05-06',2,2,2,'Mountain','House',15);
/*!40000 ALTER TABLE `property` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-04 21:10:33
