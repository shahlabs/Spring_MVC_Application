-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: finalproj
-- ------------------------------------------------------
-- Server version	5.7.12-log

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
-- Table structure for table `airplane`
--

DROP TABLE IF EXISTS `airplane`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `airplane` (
  `airplane_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `airlineName` varchar(255) DEFAULT NULL,
  `owner` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`airplane_id`),
  UNIQUE KEY `airplane_id` (`airplane_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airplane`
--

LOCK TABLES `airplane` WRITE;
/*!40000 ALTER TABLE `airplane` DISABLE KEYS */;
INSERT INTO `airplane` VALUES (1,'British Airways','Alex'),(2,'Emirates','Shameed'),(3,'GoAir','Wadia'),(4,'Indigo','Rahul Bhatia'),(5,'JetBlue','Robin'),(6,'American Airlines','Doug Parker'),(7,'Virgin Airlines','Anand');
/*!40000 ALTER TABLE `airplane` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cities`
--

DROP TABLE IF EXISTS `cities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cities` (
  `cityname` varchar(255) NOT NULL,
  PRIMARY KEY (`cityname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cities`
--

LOCK TABLES `cities` WRITE;
/*!40000 ALTER TABLE `cities` DISABLE KEYS */;
INSERT INTO `cities` VALUES ('Bangalore'),('Bangkok'),('Beijing'),('Boston'),('Chennai'),('Cincinnati'),('Cochi'),('Dallas'),('Delhi'),('Dubai'),('Hyderabad'),('Manila'),('Mumbai'),('New York'),('San Jose'),('Seattle'),('Tampa'),('Tokyo');
/*!40000 ALTER TABLE `cities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flightdetails`
--

DROP TABLE IF EXISTS `flightdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flightdetails` (
  `flight_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `airplane_id` bigint(20) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `arrDate` varchar(255) DEFAULT NULL,
  `arrivaltime` varchar(255) DEFAULT NULL,
  `deptDate` varchar(255) DEFAULT NULL,
  `depttime` varchar(255) DEFAULT NULL,
  `dest` varchar(255) DEFAULT NULL,
  `flight_name` varchar(255) DEFAULT NULL,
  `fromPlace` varchar(255) DEFAULT NULL,
  `totalSeats` int(11) DEFAULT NULL,
  `travelClass` varchar(255) DEFAULT NULL,
  `availableSeats` int(11) DEFAULT NULL,
  PRIMARY KEY (`flight_id`),
  KEY `FKB06727B29AD266CC` (`airplane_id`),
  CONSTRAINT `FKB06727B29AD266CC` FOREIGN KEY (`airplane_id`) REFERENCES `airplane` (`airplane_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flightdetails`
--

LOCK TABLES `flightdetails` WRITE;
/*!40000 ALTER TABLE `flightdetails` DISABLE KEYS */;
INSERT INTO `flightdetails` VALUES (4,1,800,'07/15/2016','14:00','07/15/2016','21:30','Boston','BA123','Mumbai',6,'Economy',6),(5,1,50,'07/15/2016','17:30','07/15/2016','15:00','Delhi','BA777','Mumbai',6,'Economy',4),(11,2,800,'07/15/2016','23:00','07/15/2016','2:00','Dallas','EM789','Dubai',7,'Economy',6),(13,1,10,'07/15/2016','1:30','07/15/2016','12:00','Delhi','BA322','Mumbai',8,'Economy',7);
/*!40000 ALTER TABLE `flightdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passenger`
--

DROP TABLE IF EXISTS `passenger`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `passenger` (
  `passenger_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `dob` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `phonenum` varchar(255) DEFAULT NULL,
  `creditCardNumber` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`passenger_id`),
  UNIQUE KEY `passenger_id` (`passenger_id`),
  KEY `FKC7AF549A6AC1E386` (`creditCardNumber`),
  CONSTRAINT `FKC7AF549A6AC1E386` FOREIGN KEY (`creditCardNumber`) REFERENCES `payment` (`creditCardNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passenger`
--

LOCK TABLES `passenger` WRITE;
/*!40000 ALTER TABLE `passenger` DISABLE KEYS */;
INSERT INTO `passenger` VALUES (1,'Boston','07/16/2016','priyanka@gmail.com','Priyanka','F','Paladi','6788957',123456789),(15,'j','07/01/2016','p@gmail.com','pp','M','pp','12345678',12345678),(16,'Mumbai','07/01/2016','labdhi0601@gmail.com','labdhi','F','shah','12345678',12345),(17,'India','05/11/2016','harvi611@yahoo.com','Harvi','F','Shah','12345678',1234),(18,'Mulund','07/01/2016','ananth@gmail.com','Anand','M','Iyer','45678902',3456),(20,'Mumbai','07/01/2016','rihana@yahoo.com','Rihana','F','Mehra','78923456',1288),(21,'Dubai','07/01/2016','jill@gmail.com','Jill','F','Shah','56788811',12889),(22,'Mumbai','07/01/2016','ratna@gmail.com','Ratna','F','Shah','12345678',7788906),(23,'Mumbai','06/16/2016','labdhi0601@gmail.com','Labdhi','F','Shah','12345678',888444);
/*!40000 ALTER TABLE `passenger` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment` (
  `creditCardNumber` bigint(20) NOT NULL,
  `bankName` varchar(255) DEFAULT NULL,
  `exp_month` varchar(255) DEFAULT NULL,
  `exp_year` varchar(255) DEFAULT NULL,
  `fullName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`creditCardNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1234,'PNB','12','2022','Harvi Shah'),(1288,'Kotak','12','2022','Rihana'),(3456,'BNP','12','2022','Anand Iyer'),(12345,'SBI','12','2022','Labdhi Shah'),(12889,'BOB','12','2022','Jill'),(888444,'ICICI','12','2022','Labdhi Shah'),(7788906,'ICICI','12','2022','Ratna Shah'),(12345678,'ICICI','12','2028','PP'),(123456789,'ICICI','12','2025','Priyanka');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket` (
  `ticket_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `flight_id` bigint(20) DEFAULT NULL,
  `passenger_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ticket_id`),
  UNIQUE KEY `ticket_id` (`ticket_id`),
  KEY `FKCBE86B0CB414AF68` (`passenger_id`),
  KEY `FKCBE86B0C708D93EA` (`flight_id`),
  CONSTRAINT `FKCBE86B0C708D93EA` FOREIGN KEY (`flight_id`) REFERENCES `flightdetails` (`flight_id`),
  CONSTRAINT `FKCBE86B0CB414AF68` FOREIGN KEY (`passenger_id`) REFERENCES `passenger` (`passenger_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (1,4,1),(3,4,16),(4,4,17),(6,4,20);
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'labdhi','admin','labdhi'),(2,'abc','customer','abc'),(3,'labs','customer','labs'),(4,'abcd','customer','abcd'),(5,'ankit','customer','ankit');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'finalproj'
--

--
-- Dumping routines for database 'finalproj'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-16 20:12:47
