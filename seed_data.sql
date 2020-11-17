CREATE DATABASE  IF NOT EXISTS `totalwar_test` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `totalwar_test`;
-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: totalwar_test
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `arm`
--

DROP TABLE IF EXISTS `arm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `arm` (
  `armID` int NOT NULL,
  `name` varchar(30) NOT NULL,
  `race` varchar(20) NOT NULL,
  `type` varchar(20) NOT NULL,
  `cost` int NOT NULL,
  `scale` int NOT NULL,
  `hp` int NOT NULL,
  `attack` int NOT NULL,
  `armor` int NOT NULL,
  `speed` int NOT NULL,
  PRIMARY KEY (`armID`),
  KEY `armRace_idx` (`race`),
  CONSTRAINT `armRace` FOREIGN KEY (`race`) REFERENCES `race` (`race`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `arm`
--

LOCK TABLES `arm` WRITE;
/*!40000 ALTER TABLE `arm` DISABLE KEYS */;
INSERT INTO `arm` VALUES (1,'Executioners','Darkelves','swordsman',1240,100,5415,67,8,50),(2,'Black Guard','Darkelves','spearman',1885,100,5661,63,7,84),(3,'Darkshards','Darkelves','archer',1600,100,4350,83,29,75),(4,'Doomfire Warlocks','Darkelves','range rider',957,30,6389,74,19,51),(5,'Dark Riders','Darkelves','cavalry',915,30,4468,83,16,52),(6,'Kharibdyss','Darkelves','behemoth',926,1,2161,77,19,56),(7,'Shades','Darkelves','archer',1909,100,4785,77,1,73),(8,'War Hydra','Darkelves','behemoth',1306,1,2013,76,15,74),(9,'Swordmasters','Highelves','swordsman',963,100,4661,85,17,51),(10,'Phoenix Guard','Highelves','spearman',546,100,4434,51,6,53),(11,'Sea Guard','Highelves','archer',829,100,5296,73,3,50),(12,'Ellyrian Reaver','Highelves','range rider',815,30,4139,58,21,70),(13,'Dragon Princes','Highelves','cavalry',721,30,5124,74,17,84),(14,'Star Dragon','Highelves','behemoth',1973,1,2076,70,3,66),(15,'Sisters of Avelorn','Highelves','archer',830,100,4573,88,18,81),(16,'Phoenix','Highelves','behemoth',1684,1,2123,78,17,56),(17,'Great Eagle','Highelves','behemoth',1815,1,2039,65,10,89),(18,'Skink Cohort','Lizardmen','swordsman',1354,100,6467,56,16,72),(19,'Saurus Spears','Lizardmen','spearman',902,100,4177,66,14,56),(20,'Chameleon Skinks','Lizardmen','archer',1718,100,5689,52,14,58),(21,'Horned Ones','Lizardmen','cavalry',1549,30,6192,73,17,84),(22,'Stegadon','Lizardmen','behemoth',1797,1,2385,79,6,60),(23,'Carnosaur','Lizardmen','behemoth',1822,1,2360,70,28,86),(24,'Ripperdactyl Riders','Lizardmen','range rider',1639,30,4503,57,5,72),(25,'Salamander','Lizardmen','archer',884,100,5070,51,4,73),(26,'Bastiladon','Lizardmen','behemoth',1316,1,2053,87,18,73),(27,'Night Runners','Skaven','swordsman',669,100,6380,88,1,83),(28,'Stormvermin','Skaven','spearman',1821,100,6238,85,1,64),(29,'Ratling','Skaven','archer',1348,100,4940,66,16,73),(30,'Doomwheel','Skaven','range rider',977,30,5216,63,3,82),(31,'Warplock Jezzails','Skaven','archer',1293,100,4927,62,5,77),(32,'Poison Globadiers','Skaven','archer',1636,100,5552,51,21,50),(33,'Death Runners','Skaven','swordsman',1968,100,6394,66,6,62),(34,'Doomflayers','Skaven','range rider',1471,30,5691,71,2,51),(35,'Hell Pit Abomination','Skaven','behemoth',772,1,2146,66,7,73);
/*!40000 ALTER TABLE `arm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `composition`
--

DROP TABLE IF EXISTS `composition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `composition` (
  `compID` int NOT NULL AUTO_INCREMENT,
  `troopID` int NOT NULL,
  `armID` int NOT NULL,
  PRIMARY KEY (`compID`),
  KEY `ofTroop_idx` (`troopID`),
  KEY `hasArms_idx` (`armID`),
  CONSTRAINT `hasArms` FOREIGN KEY (`armID`) REFERENCES `arm` (`armID`),
  CONSTRAINT `ofTroop` FOREIGN KEY (`troopID`) REFERENCES `troop` (`troopID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `composition`
--

LOCK TABLES `composition` WRITE;
/*!40000 ALTER TABLE `composition` DISABLE KEYS */;
INSERT INTO `composition` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,3),(5,2,5),(6,2,2),(7,2,7),(8,2,7),(9,2,7);
/*!40000 ALTER TABLE `composition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lord`
--

DROP TABLE IF EXISTS `lord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lord` (
  `lordID` int NOT NULL,
  `name` varchar(20) NOT NULL,
  `race` varchar(20) NOT NULL,
  `hp` int NOT NULL,
  `attack` int NOT NULL,
  `armor` int NOT NULL,
  `speed` int NOT NULL,
  PRIMARY KEY (`lordID`),
  KEY `lordRace_idx` (`race`),
  CONSTRAINT `lordRace` FOREIGN KEY (`race`) REFERENCES `race` (`race`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lord`
--

LOCK TABLES `lord` WRITE;
/*!40000 ALTER TABLE `lord` DISABLE KEYS */;
INSERT INTO `lord` VALUES (1,'Malekith','Darkelves',4280,140,23,37),(2,'Morathi','Darkelves',4840,234,12,35),(3,'Hellebron','Darkelves',4620,143,12,31),(4,'Tyrion','Highelves',5830,276,26,35),(5,'Teclis','Highelves',5600,289,23,32),(6,'Alith','Highelves',5170,212,26,36),(7,'Mazdamundi','Lizardmen',6330,196,34,26),(8,'Kroq-Gar','Lizardmen',6720,173,34,29),(9,'Tehenhauin','Lizardmen',6880,145,43,20),(10,'Headtaker','Skaven',3220,265,8,46),(11,'Skrolk','Skaven',3970,137,12,40),(12,'Craventail','Skaven',3610,192,10,45);
/*!40000 ALTER TABLE `lord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `race`
--

DROP TABLE IF EXISTS `race`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `race` (
  `race` varchar(20) NOT NULL,
  `location` varchar(45) NOT NULL,
  `description` varchar(500) NOT NULL,
  PRIMARY KEY (`race`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `race`
--

LOCK TABLES `race` WRITE;
/*!40000 ALTER TABLE `race` DISABLE KEYS */;
INSERT INTO `race` VALUES ('Darkelves','Naggaroth','The Dark Elves are a sadistic, cruel race who dwell in the cold, bleak land of Naggaroth after long ago being exiled from Ulthuan.'),('Highelves','Ulthuan','The High Elves are an ancient, proud race who dwell on the island-continent of Ulthuan, once home to all Elves.'),('Lizardmen','Lustria','Lizardmen are not a single race but a society of different cold-blooded creatures who dwell in temple-cities in southern jungles. '),('Skaven','Underground','The Skaven are a race of vicious mutant rat-men who lurk in a vast Under-Empire below the world. ');
/*!40000 ALTER TABLE `race` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `troop`
--

DROP TABLE IF EXISTS `troop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `troop` (
  `troopID` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `userID` int NOT NULL,
  `lordID` int NOT NULL,
  `memo` varchar(60) NOT NULL,
  PRIMARY KEY (`troopID`),
  KEY `leadedBy_idx` (`lordID`),
  KEY `userID_idx` (`userID`),
  CONSTRAINT `leadedBy` FOREIGN KEY (`lordID`) REFERENCES `lord` (`lordID`),
  CONSTRAINT `userID` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `troop`
--

LOCK TABLES `troop` WRITE;
/*!40000 ALTER TABLE `troop` DISABLE KEYS */;
INSERT INTO `troop` VALUES (1,'harry\'s first',1,3,'This is Harry\'s first troop'),(2,'harry\'s second',1,1,'This is Harry\'s second troop');
/*!40000 ALTER TABLE `troop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `userID` int NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(15) NOT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'harry@gmail.com','123456'),(2,'ronald@gmail.com','654321'),(4,'admin','213');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wartest`
--

DROP TABLE IF EXISTS `wartest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wartest` (
  `warID` int NOT NULL,
  `troopID1` int NOT NULL,
  `troopID2` int NOT NULL,
  `location` varchar(30) NOT NULL,
  `victor` int NOT NULL,
  `arms_left` int NOT NULL,
  PRIMARY KEY (`warID`),
  KEY `troop1_idx` (`troopID1`),
  KEY `troop2_idx` (`troopID2`),
  CONSTRAINT `troop1` FOREIGN KEY (`troopID1`) REFERENCES `troop` (`troopID`),
  CONSTRAINT `troop2` FOREIGN KEY (`troopID2`) REFERENCES `troop` (`troopID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wartest`
--

LOCK TABLES `wartest` WRITE;
/*!40000 ALTER TABLE `wartest` DISABLE KEYS */;
/*!40000 ALTER TABLE `wartest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'totalwar_test'
--

--
-- Dumping routines for database 'totalwar_test'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-17 10:57:47
