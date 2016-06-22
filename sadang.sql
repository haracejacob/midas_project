-- MySQL dump 10.13  Distrib 5.1.41, for Win32 (ia32)
--
-- Host: localhost    Database: sadang
-- ------------------------------------------------------
-- Server version	5.1.41-community

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
-- Table structure for table `board`
--

DROP TABLE IF EXISTS `board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `board` (
  `board_seq` int(8) unsigned NOT NULL AUTO_INCREMENT,
  `category_seq` int(8) unsigned NOT NULL,
  `board_name` text NOT NULL,
  `subject` text NOT NULL,
  `board_contents` text NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `file_name` text,
  PRIMARY KEY (`board_seq`),
  KEY `category_seq` (`category_seq`),
  CONSTRAINT `board_ibfk_1` FOREIGN KEY (`category_seq`) REFERENCES `category` (`category_seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board`
--

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
/*!40000 ALTER TABLE `board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `category_seq` int(8) unsigned NOT NULL AUTO_INCREMENT,
  `user_seq` int(4) unsigned NOT NULL,
  `category_name` text NOT NULL,
  `category_hash` text NOT NULL,
  PRIMARY KEY (`category_seq`),
  KEY `user_seq` (`user_seq`),
  CONSTRAINT `category_ibfk_1` FOREIGN KEY (`user_seq`) REFERENCES `user` (`user_seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (5,13,'ToDo','*D7B97B7705ED6D1BA1470AD6B34B5116B9555986'),(6,13,'Bookmark',''),(7,13,'Done',''),(8,14,'ToDo',''),(9,14,'Bookmark',''),(10,14,'Done',''),(11,15,'ToDo',''),(12,15,'Bookmark',''),(13,15,'Done',''),(14,16,'ToDo',''),(15,16,'Bookmark',''),(16,16,'Done','');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_member`
--

DROP TABLE IF EXISTS `category_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category_member` (
  `category_seq` int(8) unsigned NOT NULL,
  `user_seq` int(8) unsigned NOT NULL,
  KEY `category_seq` (`category_seq`),
  KEY `user_seq` (`user_seq`),
  CONSTRAINT `category_member_ibfk_1` FOREIGN KEY (`category_seq`) REFERENCES `category` (`category_seq`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `category_member_ibfk_2` FOREIGN KEY (`user_seq`) REFERENCES `user` (`user_seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_member`
--

LOCK TABLES `category_member` WRITE;
/*!40000 ALTER TABLE `category_member` DISABLE KEYS */;
INSERT INTO `category_member` VALUES (5,13),(6,13),(7,13),(8,14),(9,14),(10,14),(11,15),(12,15),(13,15),(14,16),(15,16),(16,16);
/*!40000 ALTER TABLE `category_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `todo`
--

DROP TABLE IF EXISTS `todo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `todo` (
  `todo_seq` int(8) unsigned NOT NULL AUTO_INCREMENT,
  `category_seq` int(8) unsigned NOT NULL,
  `user_seq` int(8) unsigned NOT NULL,
  `start_date` text NOT NULL,
  `end_date` text,
  `contents` text NOT NULL,
  `complete` int(4) NOT NULL DEFAULT '0',
  `bookmark` int(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`todo_seq`),
  KEY `category_seq` (`category_seq`),
  KEY `user_seq` (`user_seq`),
  CONSTRAINT `todo_ibfk_1` FOREIGN KEY (`category_seq`) REFERENCES `category` (`category_seq`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `todo_ibfk_2` FOREIGN KEY (`user_seq`) REFERENCES `user` (`user_seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `todo`
--

LOCK TABLES `todo` WRITE;
/*!40000 ALTER TABLE `todo` DISABLE KEYS */;
INSERT INTO `todo` VALUES (1,5,15,'2016-5-28',NULL,'??',0,0),(2,5,15,'2016-5-28',NULL,'testsets',0,0),(3,5,15,'2016-5-28',NULL,'??',0,0),(4,5,15,'2016-5-28',NULL,'??',0,0),(5,5,15,'2016-5-28',NULL,'????',0,0),(6,5,15,'2016-5-28',NULL,'???',0,0),(7,5,15,'2016-5-28',NULL,'??',0,0),(8,5,15,'2016-5-28',NULL,'??',0,0),(9,5,15,'2016-5-28','','한글',0,0),(10,5,15,'2016-5-28',NULL,'할일',0,0),(11,5,15,'2016-5-29',NULL,'할일',0,0),(12,5,15,'2016-5-29',NULL,'test',0,0),(13,5,15,'2016-5-29',NULL,'teststs',0,0),(14,5,15,'2016-5-29',NULL,'',0,0),(15,5,15,'2016-5-29',NULL,'tsetset',0,0),(16,5,15,'2016-5-29',NULL,'1234',0,0),(17,5,15,'2016-5-29',NULL,'1231321',0,0),(18,5,15,'2016-5-29',NULL,'esse11',0,0),(19,5,15,'2016-5-29',NULL,'setetset',0,0),(20,5,15,'2016-5-29',NULL,'sesttseet',0,0),(21,5,15,'2016-5-29',NULL,'12321321313',0,0),(22,5,15,'2016-5-29',NULL,'123213tt',0,0),(23,5,15,'2016-5-29',NULL,'tset',0,0);
/*!40000 ALTER TABLE `todo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_seq` int(8) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` text,
  `user_passwd` text NOT NULL,
  `user_email` text NOT NULL,
  `user_phone` text,
  PRIMARY KEY (`user_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='유저 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (13,NULL,'*A4B6157319038724E3560894F7F932C8886EBFCF','test2@sadang.com',NULL),(14,NULL,'*A4B6157319038724E3560894F7F932C8886EBFCF','test3@sadang.com',NULL),(15,NULL,'*A4B6157319038724E3560894F7F932C8886EBFCF','test@sadang.com',NULL),(16,NULL,'*A4B6157319038724E3560894F7F932C8886EBFCF','kim5@sadang.com',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-29  3:00:16
