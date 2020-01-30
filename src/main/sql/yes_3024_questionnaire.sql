-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: yes_3024
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Table structure for table `questionnaire`
--

DROP TABLE IF EXISTS `questionnaire`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `questionnaire` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question1` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `question2` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `question3` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `question4` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `question5` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `question6` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `question7` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `question8` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `question9` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `question10` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `question11` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `question12` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `question13` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `question14` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `question15` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `question16` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `question17` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questionnaire`
--

LOCK TABLES `questionnaire` WRITE;
/*!40000 ALTER TABLE `questionnaire` DISABLE KEYS */;
INSERT INTO `questionnaire` VALUES (1,'aaa','bb','cc','dd','ee','ff','gg','hh','ii','jjj','kk','ll','mm','nn','ooo','','');
/*!40000 ALTER TABLE `questionnaire` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-30 15:09:03
