CREATE DATABASE  IF NOT EXISTS `pmh` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `pmh`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win32 (AMD64)
--
-- Host: localhost    Database: pmh
-- ------------------------------------------------------
-- Server version	5.5.54-log

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
-- Table structure for table `companies`
--

DROP TABLE IF EXISTS `companies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `companies` (
  `COMPANY_ID` int(11) NOT NULL AUTO_INCREMENT,
  `COMPANY_NAME` varchar(100) NOT NULL,
  `COMPANY_ADDRESS` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`COMPANY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `companies`
--

LOCK TABLES `companies` WRITE;
/*!40000 ALTER TABLE `companies` DISABLE KEYS */;
INSERT INTO `companies` VALUES (1,'Luxoft','Ukraine'),(2,'Softserve','Ukraine'),(3,'Ciklum','Ukraine'),(5,'Firma 1','Ukraine'),(6,'Test','Ukr');
/*!40000 ALTER TABLE `companies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `companies_customers`
--

DROP TABLE IF EXISTS `companies_customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `companies_customers` (
  `COMPANY_ID` int(11) NOT NULL,
  `CUSTOMER_ID` int(11) NOT NULL,
  PRIMARY KEY (`COMPANY_ID`,`CUSTOMER_ID`),
  KEY `fk_companies_has_customers_customers1_idx` (`CUSTOMER_ID`),
  KEY `fk_companies_has_customers_companies1_idx` (`COMPANY_ID`),
  CONSTRAINT `fk_companies_has_customers_companies1` FOREIGN KEY (`COMPANY_ID`) REFERENCES `companies` (`COMPANY_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_companies_has_customers_customers1` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customers` (`CUSTOMER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `companies_customers`
--

LOCK TABLES `companies_customers` WRITE;
/*!40000 ALTER TABLE `companies_customers` DISABLE KEYS */;
INSERT INTO `companies_customers` VALUES (1,1),(2,2),(3,3),(5,6);
/*!40000 ALTER TABLE `companies_customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `companies_developers`
--

DROP TABLE IF EXISTS `companies_developers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `companies_developers` (
  `COMPANY_ID` int(11) NOT NULL,
  `DEVELOPER_ID` int(11) NOT NULL,
  PRIMARY KEY (`COMPANY_ID`,`DEVELOPER_ID`),
  KEY `fk_companies_has_developers_developers1_idx` (`DEVELOPER_ID`),
  KEY `fk_companies_has_developers_companies1_idx` (`COMPANY_ID`),
  CONSTRAINT `fk_companies_has_developers_companies1` FOREIGN KEY (`COMPANY_ID`) REFERENCES `companies` (`COMPANY_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_companies_has_developers_developers1` FOREIGN KEY (`DEVELOPER_ID`) REFERENCES `developers` (`DEVELOPER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `companies_developers`
--

LOCK TABLES `companies_developers` WRITE;
/*!40000 ALTER TABLE `companies_developers` DISABLE KEYS */;
INSERT INTO `companies_developers` VALUES (1,1),(2,2),(3,3),(2,4),(1,5);
/*!40000 ALTER TABLE `companies_developers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `companies_projects`
--

DROP TABLE IF EXISTS `companies_projects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `companies_projects` (
  `COMPANY_ID` int(11) NOT NULL,
  `PROJECT_ID` int(11) NOT NULL,
  PRIMARY KEY (`COMPANY_ID`,`PROJECT_ID`),
  KEY `fk_companies_has_projects_projects1_idx` (`PROJECT_ID`),
  KEY `fk_companies_has_projects_companies1_idx` (`COMPANY_ID`),
  CONSTRAINT `fk_companies_has_projects_companies1` FOREIGN KEY (`COMPANY_ID`) REFERENCES `companies` (`COMPANY_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_companies_has_projects_projects1` FOREIGN KEY (`PROJECT_ID`) REFERENCES `projects` (`PROJECT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `companies_projects`
--

LOCK TABLES `companies_projects` WRITE;
/*!40000 ALTER TABLE `companies_projects` DISABLE KEYS */;
INSERT INTO `companies_projects` VALUES (1,1),(1,2),(1,3),(2,4),(2,5),(3,6);
/*!40000 ALTER TABLE `companies_projects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customers` (
  `CUSTOMER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CUSTOMER_NAME` varchar(100) NOT NULL,
  `CUSTOMER_ADDRESS` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`CUSTOMER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,'Soft Trade','Ukraine'),(2,'Soft Prom','Ukraine'),(3,'Softline','Ukraine'),(6,'Firma 2','Ukraine'),(7,'Customer-1','Ukraine');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers_projects`
--

DROP TABLE IF EXISTS `customers_projects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customers_projects` (
  `CUSTOMER_ID` int(11) NOT NULL,
  `PROJECT_ID` int(11) NOT NULL,
  PRIMARY KEY (`CUSTOMER_ID`,`PROJECT_ID`),
  KEY `fk_customers_has_projects_projects1_idx` (`PROJECT_ID`),
  KEY `fk_customers_has_projects_customers1_idx` (`CUSTOMER_ID`),
  CONSTRAINT `fk_customers_has_projects_customers1` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customers` (`CUSTOMER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_customers_has_projects_projects1` FOREIGN KEY (`PROJECT_ID`) REFERENCES `projects` (`PROJECT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers_projects`
--

LOCK TABLES `customers_projects` WRITE;
/*!40000 ALTER TABLE `customers_projects` DISABLE KEYS */;
INSERT INTO `customers_projects` VALUES (1,1),(1,2),(1,3),(2,4),(2,5),(3,6),(7,7);
/*!40000 ALTER TABLE `customers_projects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `developers`
--

DROP TABLE IF EXISTS `developers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `developers` (
  `DEVELOPER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) NOT NULL,
  `EXPERIENCE` int(11) NOT NULL,
  `SALARY` int(11) DEFAULT NULL,
  PRIMARY KEY (`DEVELOPER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `developers`
--

LOCK TABLES `developers` WRITE;
/*!40000 ALTER TABLE `developers` DISABLE KEYS */;
INSERT INTO `developers` VALUES (1,'Petr Simonov',2,2000),(2,'Peter Romanenko',3,3500),(3,'Andrei Komarov',2,2100),(4,'Konstantin Geiko',2,2000),(5,'Roman Yarosh',2,1500),(7,'0',0,0),(8,'Тет',2,2000),(9,'Ntcn',12,1233),(10,'qwe',2,2000),(11,'Ярош Р.А.',15,2000);
/*!40000 ALTER TABLE `developers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `developers_projects`
--

DROP TABLE IF EXISTS `developers_projects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `developers_projects` (
  `DEVELOPER_ID` int(11) NOT NULL,
  `PROJECT_ID` int(11) NOT NULL,
  PRIMARY KEY (`DEVELOPER_ID`,`PROJECT_ID`),
  KEY `fk_developers_has_projects_projects1_idx` (`PROJECT_ID`),
  KEY `fk_developers_has_projects_developers1_idx` (`DEVELOPER_ID`),
  CONSTRAINT `fk_developers_has_projects_developers1` FOREIGN KEY (`DEVELOPER_ID`) REFERENCES `developers` (`DEVELOPER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_developers_has_projects_projects1` FOREIGN KEY (`PROJECT_ID`) REFERENCES `projects` (`PROJECT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `developers_projects`
--

LOCK TABLES `developers_projects` WRITE;
/*!40000 ALTER TABLE `developers_projects` DISABLE KEYS */;
INSERT INTO `developers_projects` VALUES (1,1),(5,1),(2,2),(4,2),(3,3);
/*!40000 ALTER TABLE `developers_projects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `developers_skills`
--

DROP TABLE IF EXISTS `developers_skills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `developers_skills` (
  `DEVELOPER_ID` int(11) NOT NULL,
  `SKILL_ID` int(11) NOT NULL,
  PRIMARY KEY (`DEVELOPER_ID`,`SKILL_ID`),
  KEY `fk_developers_has_skills_skills1_idx` (`SKILL_ID`),
  KEY `fk_developers_has_skills_developers_idx` (`DEVELOPER_ID`),
  CONSTRAINT `fk_developers_has_skills_developers` FOREIGN KEY (`DEVELOPER_ID`) REFERENCES `developers` (`DEVELOPER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_developers_has_skills_skills1` FOREIGN KEY (`SKILL_ID`) REFERENCES `skills` (`SKILL_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `developers_skills`
--

LOCK TABLES `developers_skills` WRITE;
/*!40000 ALTER TABLE `developers_skills` DISABLE KEYS */;
INSERT INTO `developers_skills` VALUES (1,1),(2,1),(3,1),(5,1),(1,2),(4,2),(9,3),(9,4),(9,5),(9,6),(11,8);
/*!40000 ALTER TABLE `developers_skills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projects`
--

DROP TABLE IF EXISTS `projects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `projects` (
  `PROJECT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PROJECT_NAME` varchar(100) NOT NULL,
  `COST` int(11) DEFAULT NULL,
  PRIMARY KEY (`PROJECT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projects`
--

LOCK TABLES `projects` WRITE;
/*!40000 ALTER TABLE `projects` DISABLE KEYS */;
INSERT INTO `projects` VALUES (1,'Project 1',4500),(2,'Project 2',6500),(3,'Project 3',3100),(4,'Project 4',NULL),(5,'Project 5',NULL),(6,'Project 6',NULL),(7,'Project-1 new',1235);
/*!40000 ALTER TABLE `projects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `skills`
--

DROP TABLE IF EXISTS `skills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `skills` (
  `SKILL_ID` int(11) NOT NULL AUTO_INCREMENT,
  `SKILL_NAME` varchar(100) NOT NULL,
  PRIMARY KEY (`SKILL_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `skills`
--

LOCK TABLES `skills` WRITE;
/*!40000 ALTER TABLE `skills` DISABLE KEYS */;
INSERT INTO `skills` VALUES (1,'Java'),(2,'C#'),(3,'Java'),(4,'JPA'),(5,'Hibernate'),(6,'test'),(8,'Java EE');
/*!40000 ALTER TABLE `skills` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-30 19:23:26
