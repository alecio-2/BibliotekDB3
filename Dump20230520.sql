CREATE DATABASE  IF NOT EXISTS `bibliotek` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bibliotek`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: bibliotek
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `anstalld`
--

DROP TABLE IF EXISTS `anstalld`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `anstalld` (
  `anvandareNr` int NOT NULL,
  `anstalldTyp` varchar(45) NOT NULL,
  PRIMARY KEY (`anvandareNr`),
  CONSTRAINT `anvandareNrFKAns` FOREIGN KEY (`anvandareNr`) REFERENCES `anvandare` (`anvandareNr`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `anstalld`
--

LOCK TABLES `anstalld` WRITE;
/*!40000 ALTER TABLE `anstalld` DISABLE KEYS */;
INSERT INTO `anstalld` VALUES (1,'Manager'),(5,'Anstalld'),(8,'Bibliotekarie');
/*!40000 ALTER TABLE `anstalld` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `anvandare`
--

DROP TABLE IF EXISTS `anvandare`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `anvandare` (
  `anvandareNr` int NOT NULL AUTO_INCREMENT,
  `losenord` varchar(45) DEFAULT NULL,
  `anvandareTyp` varchar(10) NOT NULL,
  `fNamn` varchar(20) NOT NULL,
  `eNamn` varchar(20) NOT NULL,
  `gatuNamn` varchar(30) DEFAULT NULL,
  `gatuNr` smallint DEFAULT NULL,
  `postNr` int DEFAULT NULL,
  `ort` varchar(30) DEFAULT NULL,
  `email` varchar(30) NOT NULL,
  `telefonNr` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`anvandareNr`),
  KEY `anvandareTypFKAnv_idx` (`anvandareTyp`),
  KEY `email_idx` (`email`),
  CONSTRAINT `anvandareTypFKAnv` FOREIGN KEY (`anvandareTyp`) REFERENCES `anvandaretyp` (`anvandareTyp`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `anvandare`
--

LOCK TABLES `anvandare` WRITE;
/*!40000 ALTER TABLE `anvandare` DISABLE KEYS */;
INSERT INTO `anvandare` VALUES (1,'11234','Anstalld','Betil ','Johansson','Bergkullen',12,90628,'Umea','betiljohansson@gmail.com','0769876543'),(2,'21234','Forskare','Lisa','Robertsson','Dalkullen',13,90628,'Umea','lisarobertsson@yahoo.com','0736985214'),(3,'31234','Larare','Markus','Fredriksson','Sveavagen',20,90628,'Umea','markusfredriksson@hotmail.com','0745896231'),(4,'41234','Student','Elias','Stenberg','Sturegatan',30,10044,'Stockholm','eliasstenberg@outlook.com','0712346789'),(5,'51234','Anstalld','Aron','Brown','Sveavagen',5,11239,'Stockholm','aronbrown@yahoo.com','0756789812'),(6,'61234','Student','Martin','Johnsson','Nyttgatan',55,36545,'Luleå','martinjohnsson@yahoo.com','0736985671'),(7,'71234','Student','Mazgin','Taleb','Umeågatan',123,12345,'Umeå','mm@yahoo.com','0765879523'),(8,'81234','Anstalld','Peter','Bjornsson ','Varugatan',22,16514,'Stockholm','peterbjorsson@gmail.com','0762597654');
/*!40000 ALTER TABLE `anvandare` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `anvandaretyp`
--

DROP TABLE IF EXISTS `anvandaretyp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `anvandaretyp` (
  `anvandareTyp` varchar(10) NOT NULL,
  `antalLaneGrans` smallint NOT NULL,
  PRIMARY KEY (`anvandareTyp`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `anvandaretyp`
--

LOCK TABLES `anvandaretyp` WRITE;
/*!40000 ALTER TABLE `anvandaretyp` DISABLE KEYS */;
INSERT INTO `anvandaretyp` VALUES ('Anstalld',999),('Forskare',20),('Larare',10),('Student',5);
/*!40000 ALTER TABLE `anvandaretyp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `artikel`
--

DROP TABLE IF EXISTS `artikel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `artikel` (
  `artikelNr` int NOT NULL AUTO_INCREMENT,
  `sab` varchar(10) NOT NULL,
  `titel` varchar(45) NOT NULL,
  `artist` varchar(45) NOT NULL,
  `utgava` smallint NOT NULL DEFAULT '1',
  `artikelGenre` varchar(15) NOT NULL,
  `artikelKategori` varchar(45) NOT NULL,
  `isbn` varchar(30) NOT NULL,
  `statusTyp` varchar(45) NOT NULL DEFAULT 'Tillganglig',
  PRIMARY KEY (`artikelNr`),
  KEY `artikelKategori_idx` (`artikelKategori`),
  KEY `titel_idx` (`titel`),
  KEY `artist_idx` (`artist`) /*!80000 INVISIBLE */,
  KEY `isbn_idx` (`isbn`),
  CONSTRAINT `artikelKategoriFKArt` FOREIGN KEY (`artikelKategori`) REFERENCES `artkategori` (`artikelKategori`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artikel`
--

LOCK TABLES `artikel` WRITE;
/*!40000 ALTER TABLE `artikel` DISABLE KEYS */;
INSERT INTO `artikel` VALUES (1,'PZ LEE','To Kill a Mockingbird','Harper Lee',3,'Fiction','Standard','978-3-16-148410-1','Lanad'),(2,'FB TOL','The Lord of the Rings','J. R. R. Tolkien',1,'Fantasy','DVD','978-3-16-148411-9','Lanad'),(3,'PZ SAI','The Catcher in the Rye','J.D. Salinger',2,'Fiction','Course literature','978-3-16-148416-2','Lanad'),(4,'SC KUH','The Structure of Scientific Revolutions','Thomas S. Kuhn',2,'Science','Reference literature','978-3-16-148410-0','Tillganglig'),(5,'IJ STU','1984','George Orwell',1,'Science Fiction','Standard','978-3-16-148419-9','Tillganglig'),(6,'KL VWX','Pride and Prejudice','Jane Austen',5,'Romance','Course literature','978-3-16-148420-5','Lanad'),(7,'MN YZA','The Great Gatsby','F. Scott Fitzgerald',2,'Classic','DVD','978-3-16-148421-2','Lanad'),(8,'OP BCD','The Alchemist','Paulo Coelho',2,'Spirituality','Reference literature','978-3-16-148422-9','Tillganglig'),(22,'ABC DEF','Brave New World','Aldous Huxley',3,'Science Fiction','Standard','978-3-16-148415-7','Lanad'),(23,'GHI JKL','The Hobbit','J.R.R. Tolkien',1,'Fantasy','Course literature','978-3-16-148417-9','Lanad'),(24,'MNO PQR','Crime and Punishment','Fyodor Dostoevsky',2,'Classic','Reference literature','978-3-16-148418-6','Tillganglig'),(25,'STU VWX','The Alchemist','Paulo Coelho',1,'Fiction','Standard','978-3-16-148419-3','Lanad'),(26,'YZA BCD','Harry Potter and the Sorcerer\'s Stone','J.K. Rowling',1,'Fantasy','DVD','978-3-16-148420-9','Lanad'),(27,'EFG HIJ','The Catcher in the Rye','J.D. Salinger',3,'Fiction','Standard','978-3-16-148421-6','Lanad'),(28,'KLM NOP','To Kill a Mockingbird','Harper Lee',2,'Fiction','Course literature','978-3-16-148422-3','Tillganglig'),(29,'QRS TUV','The Odyssey','Homer',1,'Classic','Reference literature','978-3-16-148423-0','Tillganglig'),(40,'DEF GHI','The Chronicles of Narnia','C.S. Lewis',1,'Fantasy','Standard','978-3-16-148424-7','Lanad'),(41,'JKL MNO','The Picture of Dorian Gray','Oscar Wilde',2,'Classic','Course literature','978-3-16-148425-4','Tillganglig'),(42,'PQR STU','The Hitchhiker\'s Guide to the Galaxy','Douglas Adams',3,'Science Fiction','DVD','978-3-16-148426-1','Lanad'),(43,'VWX YZA','The Book Thief','Markus Zusak',2,'Fiction','Standard','978-3-16-148427-8','Lanad'),(44,'BCD EFG','Moby-Dick','Herman Melville',2,'Adventure','Course literature','978-3-16-148428-5','Tillganglig'),(45,'GHI JKL','The Secret Life of Bees','Sue Monk Kidd',1,'Fiction','DVD','978-3-16-148429-2','Lanad'),(46,'MNO PQR','The Kite Runner','Khaled Hosseini',2,'Drama','Standard','978-3-16-148430-8','Lanad'),(47,'STU VWX','The Girl with the Dragon Tattoo','Stieg Larsson',3,'Mystery','Course literature','978-3-16-148431-5','Tillganglig'),(48,'YZA BCD','Frankenstein','Mary Shelley',1,'Gothic','Reference literature','978-3-16-148432-2','Tillganglig'),(49,'EFG HIJ','The Count of Monte Cristo','Alexandre Dumas',2,'Adventure','Standard','978-3-16-148433-9','Lanad'),(52,'PZ LEE','To Kill a Mockingbird','Harper Lee',3,'Fiction','Standard','978-3-16-148435-3','Tillganglig'),(53,'FB TOL','The Lord of the Rings','J.R.R. Tolkien',1,'Fantasy','DVD','978-3-16-148436-0','Tillganglig'),(54,'PZ SAI','The Catcher in the Rye','J.D. Salinger',2,'Fiction','Course literature','978-3-16-148437-7','Tillganglig'),(55,'SC KUH','The Structure of Scientific Revolutions','Thomas S. Kuhn',2,'Science','Standard','978-3-16-148438-4','Tillganglig'),(56,'IJ STU','1984','George Orwell',1,'Science Fiction','Standard','978-3-16-148439-1','Tillganglig'),(57,'KL VWX','Pride and Prejudice','Jane Austen',5,'Romance','Course literature','978-3-16-148440-7','Tillganglig'),(58,'MN YZA','The Great Gatsby','F. Scott Fitzgerald',2,'Classic','DVD','978-3-16-148441-4','Tillganglig'),(59,'OP BCD','The Alchemist','Paulo Coelho',2,'Spirituality','Standard','978-3-16-148442-1','Tillganglig'),(60,'ABC DEF','Brave New World','Aldous Huxley',3,'Science Fiction','Standard','978-3-16-148443-8','Tillganglig'),(61,'GHI JKL','The Odyssey','Homer',1,'Classic','Standard','978-3-16-148444-5','Tillganglig'),(62,'MNO PQR','Crime and Punishment','Fyodor Dostoevsky',2,'Classic','Course literature','978-3-16-148445-2','Tillganglig'),(63,'STU VWX','The Alchemist','Paulo Coelho',1,'Fiction','Standard','978-3-16-148446-9','Tillganglig'),(64,'YZA BCD','Harry Potter and the Sorcerer\'s Stone','J.K. Rowling',1,'Fantasy','DVD','978-3-16-148447-6','Lanad'),(65,'EFG HIJ','The Count of Monte Cristo','Alexandre Dumas',2,'Adventure','Standard','978-3-16-148448-3','Tillganglig'),(66,'KLM NOP','The Picture of Dorian Gray','Oscar Wilde',2,'Classic','Course literature','978-3-16-148449-0','Tillganglig');
/*!40000 ALTER TABLE `artikel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `artkategori`
--

DROP TABLE IF EXISTS `artkategori`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `artkategori` (
  `artikelKategori` varchar(45) NOT NULL,
  `tidLaneGrans` int NOT NULL,
  PRIMARY KEY (`artikelKategori`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artkategori`
--

LOCK TABLES `artkategori` WRITE;
/*!40000 ALTER TABLE `artkategori` DISABLE KEYS */;
INSERT INTO `artkategori` VALUES ('Course literature',30),('DVD',14),('Reference literature',0),('Standard',60);
/*!40000 ALTER TABLE `artkategori` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hashtag`
--

DROP TABLE IF EXISTS `hashtag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hashtag` (
  `hashtagNr` int NOT NULL AUTO_INCREMENT,
  `hashtagNamn` varchar(45) NOT NULL,
  PRIMARY KEY (`hashtagNr`),
  KEY `hashtagNamn_idx` (`hashtagNamn`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hashtag`
--

LOCK TABLES `hashtag` WRITE;
/*!40000 ALTER TABLE `hashtag` DISABLE KEYS */;
INSERT INTO `hashtag` VALUES (5,'a'),(4,'e'),(1,'g'),(3,'Rye'),(2,'s');
/*!40000 ALTER TABLE `hashtag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hashtagartikel`
--

DROP TABLE IF EXISTS `hashtagartikel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hashtagartikel` (
  `hashtagNr` int NOT NULL,
  `artikelNr` int NOT NULL,
  PRIMARY KEY (`hashtagNr`,`artikelNr`),
  KEY `artikelNr_idx` (`artikelNr`),
  CONSTRAINT `artikelNrFKhas` FOREIGN KEY (`artikelNr`) REFERENCES `artikel` (`artikelNr`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `hashtagNrFKhas` FOREIGN KEY (`hashtagNr`) REFERENCES `hashtag` (`hashtagNr`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hashtagartikel`
--

LOCK TABLES `hashtagartikel` WRITE;
/*!40000 ALTER TABLE `hashtagartikel` DISABLE KEYS */;
INSERT INTO `hashtagartikel` VALUES (1,1),(3,1),(4,1),(5,1),(1,2),(3,2),(4,2),(5,2),(1,3),(2,3),(3,3),(4,3),(5,3),(1,4),(3,4),(4,4),(5,4);
/*!40000 ALTER TABLE `hashtagartikel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inlamningsdatum`
--

DROP TABLE IF EXISTS `inlamningsdatum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inlamningsdatum` (
  `lanNr` int NOT NULL,
  `artikelNr` int NOT NULL,
  `inlamningsDatum` date DEFAULT NULL,
  PRIMARY KEY (`lanNr`,`artikelNr`),
  CONSTRAINT `lanNr_artikelNrFKInl` FOREIGN KEY (`lanNr`, `artikelNr`) REFERENCES `lanartikel` (`lanNr`, `artikelNr`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inlamningsdatum`
--

LOCK TABLES `inlamningsdatum` WRITE;
/*!40000 ALTER TABLE `inlamningsdatum` DISABLE KEYS */;
INSERT INTO `inlamningsdatum` VALUES (26,1,'2023-05-19'),(59,5,'2023-05-20'),(78,28,'2023-05-19'),(84,58,'2023-05-20');
/*!40000 ALTER TABLE `inlamningsdatum` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `tr_uppdatera_artikelstatus_vid_retur` AFTER INSERT ON `inlamningsdatum` FOR EACH ROW BEGIN
    UPDATE artikel
    SET statusTyp = 'Tillganglig'
    WHERE artikelNr = NEW.artikelNr;
     
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `lan`
--

DROP TABLE IF EXISTS `lan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lan` (
  `lanNr` int NOT NULL AUTO_INCREMENT,
  `anvandareNr` int DEFAULT NULL,
  PRIMARY KEY (`lanNr`),
  KEY `anvandareNr_idx` (`anvandareNr`),
  CONSTRAINT `anvandareNrFKlan` FOREIGN KEY (`anvandareNr`) REFERENCES `anvandare` (`anvandareNr`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lan`
--

LOCK TABLES `lan` WRITE;
/*!40000 ALTER TABLE `lan` DISABLE KEYS */;
INSERT INTO `lan` VALUES (26,1),(51,1),(61,1),(62,1),(80,1),(52,2),(59,2),(64,2),(82,2),(83,2),(53,3),(54,3),(65,3),(66,3),(67,3),(68,3),(69,3),(70,3),(71,3),(72,4),(73,4),(74,4),(75,4),(76,4),(77,4),(78,4),(79,4),(58,5),(60,5),(56,6),(57,6),(55,7),(63,8),(81,8),(84,8);
/*!40000 ALTER TABLE `lan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lanartikel`
--

DROP TABLE IF EXISTS `lanartikel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lanartikel` (
  `lanNr` int NOT NULL,
  `artikelNr` int NOT NULL,
  `laneDatum` date NOT NULL DEFAULT (curdate()),
  `forfalloDatum` date NOT NULL,
  PRIMARY KEY (`lanNr`,`artikelNr`),
  KEY `artikelNrFKLA_idx` (`artikelNr`),
  CONSTRAINT `artikelNrFKLA` FOREIGN KEY (`artikelNr`) REFERENCES `artikel` (`artikelNr`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `lanNrFKLA` FOREIGN KEY (`lanNr`) REFERENCES `lan` (`lanNr`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lanartikel`
--

LOCK TABLES `lanartikel` WRITE;
/*!40000 ALTER TABLE `lanartikel` DISABLE KEYS */;
INSERT INTO `lanartikel` VALUES (26,1,'2023-02-10','2023-05-10'),(51,2,'2023-02-18','2023-05-01'),(52,42,'2023-04-18','2023-05-01'),(53,49,'2023-05-18','2023-07-18'),(54,45,'2023-05-18','2023-06-01'),(55,26,'2023-05-18','2023-06-01'),(56,46,'2023-05-18','2023-07-18'),(57,22,'2023-05-19','2023-07-19'),(58,43,'2023-05-19','2023-07-19'),(59,5,'2023-05-19','2023-07-19'),(60,7,'2023-05-19','2023-06-02'),(65,3,'2023-05-19','2023-06-19'),(69,6,'2023-05-19','2023-06-19'),(71,1,'2023-05-19','2023-07-19'),(72,25,'2023-05-19','2023-07-19'),(74,27,'2023-05-19','2023-07-19'),(75,40,'2023-05-19','2023-07-19'),(77,23,'2023-05-19','2023-06-19'),(78,28,'2023-05-19','2023-06-19'),(83,64,'2023-05-20','2023-06-03'),(84,58,'2023-05-20','2023-06-03');
/*!40000 ALTER TABLE `lanartikel` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `lanartikel_BEFORE_INSERT` BEFORE INSERT ON `lanartikel` FOR EACH ROW BEGIN
DECLARE artikelKategori VARCHAR(45);

SELECT a.artikelKategori INTO artikelKategori
FROM artikel a
WHERE a.artikelNr = NEW.artikelNr;

SET NEW.forfalloDatum = (select bibliotek.new_uf_forfalloDatum(artikelKategori));
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `forhindra_dubbel_lan` BEFORE INSERT ON `lanartikel` FOR EACH ROW BEGIN
  DECLARE art_status VARCHAR(45);
  
  SELECT statusTyp INTO art_status
  FROM artikel
  WHERE artikelNr = NEW.artikelNr;
  
  IF art_status = 'Lanad' THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'Artikeln är redan lånad, reservera istället.';
  END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `tr_lanartikel_insert` BEFORE INSERT ON `lanartikel` FOR EACH ROW BEGIN
  DECLARE lanade_artiklar INT;
  DECLARE anvandar_typ VARCHAR(45);
  DECLARE max_artikel INT;
  DECLARE inlamnade_artiklar INT;
  
  SELECT COUNT(*) INTO lanade_artiklar
  FROM lanartikel 
  JOIN lan ON lanartikel.lanNr = lan.lanNr
  WHERE lan.anvandareNr = (
    SELECT anvandareNr 
    FROM lan 
    WHERE lanNr = NEW.lanNr
  );
  
  SELECT COUNT(*) INTO inlamnade_artiklar
  FROM inlamningsdatum 
  JOIN lan ON inlamningsdatum.lanNr = lan.lanNr
  WHERE lan.anvandareNr = (
    SELECT anvandareNr 
    FROM lan 
    WHERE lanNr = NEW.lanNr
  );

  SELECT anvandareTyp INTO anvandar_typ
  FROM anvandare
  WHERE anvandareNr = (
    SELECT anvandareNr 
    FROM lan 
    WHERE lanNr = NEW.lanNr
  );

  SET max_artikel = CASE anvandar_typ
    WHEN 'Anstalld' THEN 999
    WHEN 'Forskare' THEN 20
    WHEN 'Larare' THEN 10
    ELSE 5
  END;

  IF lanade_artiklar-inlamnade_artiklar >= max_artikel THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'Du har nått max antalet för lånade artiklar.';
  END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `lanartikel_AFTER_INSERT` AFTER INSERT ON `lanartikel` FOR EACH ROW BEGIN
IF NEW.forfalloDatum = NEW.laneDatum THEN
	SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Referenslitteratur kan inte lånas ut.';
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `tr_uppdatera_artikelstatus_vid_lan` AFTER INSERT ON `lanartikel` FOR EACH ROW BEGIN
    UPDATE artikel
    SET statusTyp = 'Lanad'
    WHERE artikelNr = NEW.artikelNr;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation` (
  `reservationNr` int NOT NULL AUTO_INCREMENT,
  `anvandareNr` int NOT NULL,
  `artikelNr` int NOT NULL,
  `reservationDatum` date NOT NULL DEFAULT (curdate()),
  PRIMARY KEY (`reservationNr`),
  KEY `anvandareNrFKRez_idx` (`anvandareNr`),
  KEY `artikelNrFKRez_idx` (`artikelNr`),
  CONSTRAINT `anvandareNrFKRez` FOREIGN KEY (`anvandareNr`) REFERENCES `anvandare` (`anvandareNr`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `artikelNrFKRez` FOREIGN KEY (`artikelNr`) REFERENCES `artikel` (`artikelNr`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES (1,1,42,'2023-05-02'),(2,2,2,'2023-05-03'),(3,8,1,'2023-05-11');
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `tr_reservation_insert` BEFORE INSERT ON `reservation` FOR EACH ROW BEGIN
  DECLARE lanade_artiklar INT;
  DECLARE anvandar_typ VARCHAR(45);
  DECLARE max_artikel INT;
  DECLARE inlamnade_artiklar INT;
  
  SELECT COUNT(*) INTO lanade_artiklar
  FROM lanartikel 
  JOIN lan ON lanartikel.lanNr = lan.lanNr
  WHERE lan.anvandareNr = NEW.anvandareNr;
  
  SELECT COUNT(*) INTO inlamnade_artiklar
  FROM inlamningsdatum 
  JOIN lan ON inlamningsdatum.lanNr = lan.lanNr
  WHERE lan.anvandareNr = NEW.anvandareNr;

  SELECT anvandareTyp INTO anvandar_typ
  FROM anvandare
  WHERE anvandareNr = NEW.anvandareNr;

  SET max_artikel = CASE anvandar_typ
    WHEN 'Anstalld' THEN 999
    WHEN 'Forskare' THEN 20
    WHEN 'Larare' THEN 10
    ELSE 5
  END;

  IF lanade_artiklar-inlamnade_artiklar >= max_artikel THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'Anvandaren har natt max antalet for lanade artiklar, kan ej reservera mer.';
  END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `utgivare`
--

DROP TABLE IF EXISTS `utgivare`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utgivare` (
  `artikelNr` int NOT NULL,
  `utgivare` varchar(45) NOT NULL,
  PRIMARY KEY (`artikelNr`),
  CONSTRAINT `artikelNrFKUtg` FOREIGN KEY (`artikelNr`) REFERENCES `artikel` (`artikelNr`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utgivare`
--

LOCK TABLES `utgivare` WRITE;
/*!40000 ALTER TABLE `utgivare` DISABLE KEYS */;
INSERT INTO `utgivare` VALUES (1,'J.B. Lippincott'),(3,'Little, Brown and Company'),(4,'University of Chicago Press');
/*!40000 ALTER TABLE `utgivare` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'bibliotek'
--

--
-- Dumping routines for database 'bibliotek'
--
/*!50003 DROP FUNCTION IF EXISTS `new_uf_forfalloDatum` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `new_uf_forfalloDatum`(p_artikelKategori VARCHAR(45)) RETURNS date
    READS SQL DATA
BEGIN
  DECLARE forfalloDatum DATE;
  DECLARE kategori VARCHAR(45);
  
  
  SELECT artKategori.artikelKategori INTO kategori 
  FROM artKategori 
  WHERE artKategori.artikelKategori = p_artikelKategori;
  
  IF kategori = "Standard" THEN
	SET forfalloDatum = ADDDATE(curdate(), INTERVAL 2 MONTH);
  ELSEIF kategori = "Course literature" THEN
	SET forfalloDatum = ADDDATE(curdate(), INTERVAL 1 MONTH);
  ELSEIF kategori = "DVD" THEN
	SET forfalloDatum = ADDDATE(curdate(), INTERVAL 2 WEEK);
    ELSE SET forfalloDatum = curdate();
    END IF;
    RETURN forfalloDatum;
    
    END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-20 21:04:05
