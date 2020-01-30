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
-- Table structure for table `batiment`
--

DROP TABLE IF EXISTS `batiment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `batiment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `campus_id` int(11) NOT NULL,
  `nom_batiment` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `adresse_batiment` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_F5FAB00CAF5D55E1` (`campus_id`),
  CONSTRAINT `FK_F5FAB00CAF5D55E1` FOREIGN KEY (`campus_id`) REFERENCES `campus` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `batiment`
--

LOCK TABLES `batiment` WRITE;
/*!40000 ALTER TABLE `batiment` DISABLE KEYS */;
INSERT INTO `batiment` VALUES (1,1,'batiment hei','21 rue de toul'),(2,2,'batiment yncrea','bd vauban'),(3,2,'batiment Ensen','vauban'),(4,1,'Segard - HEI','rue Norbert Segard'),(5,1,'Toul - HEI','13, rue de Toul'),(6,1,'Prépa - HEI','31 bis rue Colbert'),(7,2,'Urbawood','58, AlléeMarie-Thérèse Vicot-Lermitte, 59000 Lille'),(8,3,'Segard - ISA','2, rue Norbert Segard'),(9,4,'Vauban - ISEN','41, Boulevard Vauban'),(10,2,'Adimaker','12, rue Norbert Segard');
/*!40000 ALTER TABLE `batiment` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-30 15:09:00
