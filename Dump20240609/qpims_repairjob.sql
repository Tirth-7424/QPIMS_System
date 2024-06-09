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
-- Table structure for table `repairjob`
--

DROP TABLE IF EXISTS `repairjob`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `repairjob` (
  `JobID` int NOT NULL AUTO_INCREMENT,
  `JobDescription` varchar(100) DEFAULT NULL,
  `JobStartDate` date NOT NULL,
  `JobcompletionDate` date DEFAULT NULL,
  `JobCost` double NOT NULL,
  `JobServiceMan` varchar(45) NOT NULL,
  `JobStatus` varchar(45) NOT NULL,
  `JobType` varchar(45) NOT NULL,
  `property_PropertyID` int NOT NULL,
  PRIMARY KEY (`JobID`,`property_PropertyID`),
  KEY `fk_repairjob_property1_idx` (`property_PropertyID`),
  CONSTRAINT `fk_repairjob_property1` FOREIGN KEY (`property_PropertyID`) REFERENCES `property` (`PropertyID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `repairjob`
--

LOCK TABLES `repairjob` WRITE;
/*!40000 ALTER TABLE `repairjob` DISABLE KEYS */;
INSERT INTO `repairjob` VALUES (9,'Done in Half Hour!!!','2004-05-05','2004-05-06',100.35,'Tirth','Ongoing','Extermination',10),(10,'Done in 15 mins','2004-05-05','2004-05-06',200.4,'Tirth','Completed','Electrical',11),(11,'NIL','2004-05-05',NULL,200.5,'Tirth','Ongoing','Cleaning',10),(12,'rtyukijc','2007-09-07',NULL,124.8,'Pat','Ongoing','Extermination',12);
/*!40000 ALTER TABLE `repairjob` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-09 18:35:53
