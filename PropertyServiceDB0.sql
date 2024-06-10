-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: qpims
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `CustomerID` int NOT NULL AUTO_INCREMENT,
  `Fname` varchar(45) DEFAULT NULL,
  `Lname` varchar(45) NOT NULL,
  `Address` varchar(100) DEFAULT NULL,
  `PhoneNumber` int NOT NULL,
  PRIMARY KEY (`CustomerID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Adam','Harding','07553 Gay Station Suite 316, Bryanview, SC 62748',776422413),(2,'Brittany','Thomas','183 Yellow St, Emu Park 1902',1119089129),(3,'Thomas','Clewes','27 Hill St, Emerald, QLD, 4390',411293842),(4,'Harry','Larry','89 Elder st, Logan, QLD, 4401',993848371);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `property`
--

LOCK TABLES `property` WRITE;
/*!40000 ALTER TABLE `property` DISABLE KEYS */;
INSERT INTO `property` VALUES (1,'Hill',22,'Bundaberg','QLD','1978-09-01',1,3,4,'Peter Griffin','House',NULL),(2,'Berlin',303,'Logan','QLD','1995-12-23',1,1,1,'Ronald McDonald','Apartment',2),(3,'Grenada',41,'Park Avenue','QLD','1963-08-08',2,4,3,'Peter Griffin','House',1),(4,'Helsburg',66,'Rockhampton City','QLD','1899-09-09',1,2,1,'Peter Griffin','Unit',4);
/*!40000 ALTER TABLE `property` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `repairjob`
--

LOCK TABLES `repairjob` WRITE;
/*!40000 ALTER TABLE `repairjob` DISABLE KEYS */;
INSERT INTO `repairjob` VALUES (1,'Water leaking through roof in kitchen','2024-06-01',NULL,385.95,'Chad Gilmore','Ongoing','Structural',1),(2,'Entire house needed to be rewired to meet new standards','2023-09-09','2023-10-01',3500,'Chad Gilmore','Completed','Electrical',3),(3,'Cockroach infestation','2023-05-02',NULL,455,'Leslie Martin','Cancelled','Extermination',1),(4,'Mowed lawn','2024-05-05','2024-05-05',50,'Chad Gilmore','Completed','Gardening',3);
/*!40000 ALTER TABLE `repairjob` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userlogin`
--

DROP TABLE IF EXISTS `userlogin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userlogin` (
  `LoginID` varchar(45) NOT NULL,
  `LoginPassword` varchar(45) NOT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `Fname` varchar(45) DEFAULT NULL,
  `Lname` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`LoginID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userlogin`
--

LOCK TABLES `userlogin` WRITE;
/*!40000 ALTER TABLE `userlogin` DISABLE KEYS */;
/*!40000 ALTER TABLE `userlogin` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-10 23:55:01
