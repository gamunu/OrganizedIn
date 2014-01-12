CREATE DATABASE  IF NOT EXISTS `inven` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `inven`;
-- MySQL dump 10.13  Distrib 5.6.10, for Win64 (x86_64)
--
-- Host: localhost    Database: inven
-- ------------------------------------------------------
-- Server version	5.6.10

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
-- Table structure for table `auditentry`
--

DROP TABLE IF EXISTS `auditentry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auditentry` (
  `auditno` int(11) NOT NULL AUTO_INCREMENT,
  `auditsdate` date DEFAULT NULL,
  `auditedate` date DEFAULT NULL,
  `counductby` varchar(300) DEFAULT NULL,
  `note` text,
  PRIMARY KEY (`auditno`)
) ENGINE=InnoDB AUTO_INCREMENT=3444 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auditentry`
--

LOCK TABLES `auditentry` WRITE;
/*!40000 ALTER TABLE `auditentry` DISABLE KEYS */;
INSERT INTO `auditentry` VALUES (3443,'2013-09-13','2013-09-13','sfdsfdsfdsds,fsfadsfds,fasdfasfs','afsdfsdafdsf asf sfsdf d');
/*!40000 ALTER TABLE `auditentry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `audititems`
--

DROP TABLE IF EXISTS `audititems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `audititems` (
  `auditno` int(11) NOT NULL DEFAULT '0',
  `branch` varchar(30) DEFAULT NULL,
  `serialNo` varchar(200) NOT NULL DEFAULT '',
  `Name` varchar(100) DEFAULT NULL,
  `brand` varchar(100) DEFAULT NULL,
  `model` varchar(100) DEFAULT NULL,
  `status` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`auditno`,`serialNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `audititems`
--

LOCK TABLES `audititems` WRITE;
/*!40000 ALTER TABLE `audititems` DISABLE KEYS */;
/*!40000 ALTER TABLE `audititems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `branch`
--

DROP TABLE IF EXISTS `branch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `branch` (
  `branchid` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(200) DEFAULT NULL,
  `itmanager` varchar(200) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`branchid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branch`
--

LOCK TABLES `branch` WRITE;
/*!40000 ALTER TABLE `branch` DISABLE KEYS */;
INSERT INTO `branch` VALUES (1,'HO','U.S Sen','Colombo 8'),(2,'HO','U.S Sen','Mathara'),(3,'HO','U.S Sen','Kandy');
/*!40000 ALTER TABLE `branch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feedback` (
  `feedbackid` int(11) NOT NULL AUTO_INCREMENT,
  `ticketid` int(11) NOT NULL,
  `senderid` int(11) NOT NULL,
  `receiverid` int(11) NOT NULL,
  `message` text NOT NULL,
  `status` varchar(10) DEFAULT NULL,
  `highlight` char(1) DEFAULT NULL,
  PRIMARY KEY (`feedbackid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES (2,2,1,1,'','closed','Y');
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grn`
--

DROP TABLE IF EXISTS `grn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grn` (
  `grnno` int(11) NOT NULL AUTO_INCREMENT,
  `supplier` varchar(100) DEFAULT NULL,
  `grndes` varchar(100) DEFAULT NULL,
  `paymentid` int(11) DEFAULT NULL,
  `ordernum` int(11) DEFAULT NULL,
  PRIMARY KEY (`grnno`),
  KEY `fk_grn` (`paymentid`),
  CONSTRAINT `fk_grn` FOREIGN KEY (`paymentid`) REFERENCES `payment` (`paymentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grn`
--

LOCK TABLES `grn` WRITE;
/*!40000 ALTER TABLE `grn` DISABLE KEYS */;
/*!40000 ALTER TABLE `grn` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iannouncement`
--

DROP TABLE IF EXISTS `iannouncement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iannouncement` (
  `iannouncementid` int(11) NOT NULL AUTO_INCREMENT,
  `componentid` varchar(10) DEFAULT NULL,
  `subject` varchar(50) DEFAULT NULL,
  `targetGroup` varchar(100) DEFAULT NULL,
  `Announcement` text,
  `moddate` date DEFAULT NULL,
  `status` varchar(45) DEFAULT 'Available',
  PRIMARY KEY (`iannouncementid`),
  UNIQUE KEY `componenetid_unique` (`componentid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iannouncement`
--

LOCK TABLES `iannouncement` WRITE;
/*!40000 ALTER TABLE `iannouncement` DISABLE KEYS */;
INSERT INTO `iannouncement` VALUES (1,'8778','jkjljkj','jkljkjjkjlj','drsfads','1970-01-01','Unvailable');
/*!40000 ALTER TABLE `iannouncement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imanualnew`
--

DROP TABLE IF EXISTS `imanualnew`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imanualnew` (
  `imanulnew` int(11) NOT NULL AUTO_INCREMENT,
  `componentid` varchar(10) DEFAULT NULL,
  `itemname` varchar(30) DEFAULT NULL,
  `brand` varchar(30) DEFAULT NULL,
  `model` varchar(50) DEFAULT NULL,
  `manualname` varchar(50) DEFAULT NULL,
  `filepath` varchar(500) DEFAULT NULL,
  `moddate` date DEFAULT NULL,
  `mstatus` varchar(45) DEFAULT 'Available',
  PRIMARY KEY (`imanulnew`),
  UNIQUE KEY `componenetid_unique` (`componentid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imanualnew`
--

LOCK TABLES `imanualnew` WRITE;
/*!40000 ALTER TABLE `imanualnew` DISABLE KEYS */;
INSERT INTO `imanualnew` VALUES (1,'j87897`','jkkhkj','Acer','uiouiu','assignment dilini.pdf','G:assignment dilini.pdf','1970-01-01','Available'),(2,'DF432','NameItem','Acer','HEllo','SysConfig.dat','D:SysConfig.dat','2013-09-10','Available');
/*!40000 ALTER TABLE `imanualnew` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invenhistory`
--

DROP TABLE IF EXISTS `invenhistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invenhistory` (
  `historyid` int(11) NOT NULL AUTO_INCREMENT,
  `serialno` varchar(200) DEFAULT NULL,
  `event` varchar(200) DEFAULT NULL,
  `historydate` date DEFAULT NULL,
  PRIMARY KEY (`historyid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invenhistory`
--

LOCK TABLES `invenhistory` WRITE;
/*!40000 ALTER TABLE `invenhistory` DISABLE KEYS */;
INSERT INTO `invenhistory` VALUES (1,'MOUSE','Added To Replaced List','2013-09-12'),(2,'MOUSE','Removed From Replaced Items List','2013-09-30'),(6,'printer01','Added To Broken List','2013-09-30'),(7,'printer01','Removed From Broken List','2013-09-30');
/*!40000 ALTER TABLE `invenhistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inventory` (
  `inventoryid` int(11) NOT NULL AUTO_INCREMENT,
  `serialno` varchar(200) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `brand` varchar(200) DEFAULT NULL,
  `model` varchar(50) DEFAULT NULL,
  `username` varchar(40) DEFAULT NULL,
  `position` varchar(40) DEFAULT NULL,
  `ram` varchar(200) DEFAULT NULL,
  `processor` varchar(60) DEFAULT NULL,
  `harddisk` varchar(60) DEFAULT NULL,
  `type` varchar(200) DEFAULT NULL,
  `front` char(1) DEFAULT NULL,
  `parentserialno` varchar(200) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `datepurchased` date DEFAULT NULL,
  `supplierid` int(11) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `ups` varchar(60) DEFAULT NULL,
  `printer_type` varchar(60) DEFAULT NULL,
  `bstatus` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`inventoryid`),
  UNIQUE KEY `inventory_unique` (`serialno`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory`
--

LOCK TABLES `inventory` WRITE;
/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
INSERT INTO `inventory` VALUES (1,'PC13','fkjkj','hjhj','dkjdsk',NULL,NULL,'hkjh','kjjhk','hjkhjk','PC',NULL,NULL,NULL,NULL,2,1,NULL,NULL,NULL),(2,'PC234','kjkjk','kjj','53894dfhj','dfkjgkdflgj','','545kljlk','lkj','klj','PC',NULL,NULL,NULL,NULL,2,3,NULL,NULL,NULL);
/*!40000 ALTER TABLE `inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `messageid` int(11) NOT NULL AUTO_INCREMENT,
  `subject` varchar(200) DEFAULT NULL,
  `ticketid` int(11) DEFAULT NULL,
  `parentid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `message` text,
  `ctime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`messageid`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (7,NULL,1,NULL,1,'fdgdfgdsgf','2013-09-26 06:54:53'),(8,NULL,1,7,1,'fdgdfgdsgfdfgsdf','2013-09-26 06:54:53'),(10,NULL,2,NULL,1,'sdfads fasd fdsf adsf dsfasfdsfdsf','2013-09-26 06:54:53'),(13,NULL,2,10,2,'Hello!','2013-09-26 06:54:53'),(14,NULL,2,13,1,'Hello this is an message. not an a..','2013-09-26 07:04:09'),(15,NULL,2,14,1,'gfytfhy t f','2013-09-26 08:36:44'),(17,NULL,1,8,1,'vcvcxdfgd','2013-09-26 15:26:20'),(18,NULL,1,17,1,'vcvcxdfgdsfs','2013-09-26 15:26:21'),(19,NULL,1,18,1,'dfgdfgd ','2013-09-26 16:16:47'),(20,NULL,1,19,1,'dfgdfgd gdfg','2013-09-26 16:16:49'),(21,NULL,1,20,2,'helooo','2013-09-26 16:51:06'),(22,NULL,1,21,2,'dfdsfasdfsdf','2013-09-26 17:55:02'),(23,NULL,1,22,1,'dfsadfsd','2013-09-26 18:15:54'),(24,NULL,1,23,2,'hello...','2013-09-26 18:18:57'),(25,NULL,1,24,2,'hello','2013-09-26 18:48:49'),(26,NULL,1,25,1,'Helpp','2013-09-26 19:07:48'),(27,NULL,1,26,2,'fgdfgdf','2013-09-26 19:08:34'),(28,NULL,1,27,1,'Hsdgdkgjk dfg','2013-09-26 19:08:54'),(29,NULL,1,28,2,'dfdsjfkjdj f dfjksd','2013-09-26 19:09:06'),(30,NULL,1,29,2,'cccc','2013-09-26 19:23:48'),(31,NULL,1,29,1,'ddsfdf','2013-09-26 19:24:03'),(32,NULL,1,31,2,'HI','2013-09-26 19:39:49'),(33,NULL,1,32,2,':P','2013-09-26 19:40:35'),(34,NULL,1,33,1,':P :P','2013-09-26 19:40:52'),(35,NULL,1,34,2,'He he!','2013-09-26 19:41:16'),(36,NULL,2,15,1,'dgfdg\ndsdfg\n','2013-09-26 20:13:29'),(37,NULL,2,36,1,'ertretre','2013-09-28 08:46:49'),(38,NULL,1,35,1,'hello','2013-09-28 13:32:03'),(39,NULL,2,37,1,'erer','2013-09-28 21:04:08'),(40,NULL,2,39,1,'fdsfdfdsf','2013-09-28 21:12:56'),(41,NULL,2,40,1,'dfgfgf','2013-09-28 21:13:11'),(42,NULL,2,41,1,'dfdsfdf','2013-09-29 18:40:52'),(43,NULL,2,42,2,'dsfdsf','2013-09-29 18:41:55'),(44,NULL,2,43,1,'sss','2013-09-29 18:42:18'),(45,NULL,2,44,2,'dsfdsfss','2013-09-29 18:42:29');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `msupplier`
--

DROP TABLE IF EXISTS `msupplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `msupplier` (
  `supplierid` int(11) NOT NULL AUTO_INCREMENT,
  `companyname` varchar(200) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `startingdate` date DEFAULT NULL,
  `duration` varchar(60) DEFAULT NULL,
  `serialno` varchar(200) DEFAULT NULL,
  `producttype` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`supplierid`),
  UNIQUE KEY `Msupplier_unique` (`serialno`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `msupplier`
--

LOCK TABLES `msupplier` WRITE;
/*!40000 ALTER TABLE `msupplier` DISABLE KEYS */;
/*!40000 ALTER TABLE `msupplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `orderid` int(11) NOT NULL AUTO_INCREMENT,
  `branchid` int(11) DEFAULT NULL,
  `orderdate` date DEFAULT NULL,
  `laptop` varchar(50) DEFAULT NULL,
  `pc` varchar(50) DEFAULT NULL,
  `printers` varchar(50) DEFAULT NULL,
  `others` int(11) DEFAULT NULL,
  `otherinfo` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`orderid`),
  KEY `fk_order_branch` (`branchid`),
  CONSTRAINT `fk_order_branch` FOREIGN KEY (`branchid`) REFERENCES `branch` (`branchid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orgemail`
--

DROP TABLE IF EXISTS `orgemail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orgemail` (
  `emailid` int(11) NOT NULL AUTO_INCREMENT,
  `subject` text,
  `message` text,
  `userform` varchar(30) DEFAULT NULL,
  `userto` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`emailid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orgemail`
--

LOCK TABLES `orgemail` WRITE;
/*!40000 ALTER TABLE `orgemail` DISABLE KEYS */;
/*!40000 ALTER TABLE `orgemail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment` (
  `paymentid` int(11) NOT NULL AUTO_INCREMENT,
  `recieptno` varchar(30) DEFAULT NULL,
  `totamount` double DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `pdate` date DEFAULT NULL,
  `purchaseid` int(11) DEFAULT NULL,
  PRIMARY KEY (`paymentid`),
  KEY `fk_payment` (`purchaseid`),
  CONSTRAINT `fk_payment` FOREIGN KEY (`purchaseid`) REFERENCES `purchase` (`purchaseid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1,'RN002',525,'Cheque','2013-09-18',1);
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissions`
--

DROP TABLE IF EXISTS `permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permissions` (
  `permid` int(11) NOT NULL AUTO_INCREMENT,
  `permdesc` varchar(50) NOT NULL,
  PRIMARY KEY (`permid`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissions`
--

LOCK TABLES `permissions` WRITE;
/*!40000 ALTER TABLE `permissions` DISABLE KEYS */;
INSERT INTO `permissions` VALUES (1,'can:login'),(2,'create:user'),(3,'view:tickets'),(4,'view:ticket'),(5,'view:feedbacks'),(6,'make:feedback'),(7,'create:ticket'),(8,'reopen:ticket'),(9,'close:ticket'),(10,'change:priority'),(11,'delete:ticket'),(12,'open:ticket'),(13,'view:message'),(14,'create:message'),(15,'reply:message'),(16,'delete:message'),(17,'change:status'),(18,'modify:warranty'),(19,'insert:warranty'),(20,'view:warranty'),(21,'modify:replacement'),(22,'insert:replacement'),(23,'view:replacement'),(24,'has:root');
/*!40000 ALTER TABLE `permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase`
--

DROP TABLE IF EXISTS `purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchase` (
  `purchaseid` int(11) NOT NULL AUTO_INCREMENT,
  `invoiceno` varchar(30) DEFAULT NULL,
  `purdate` date DEFAULT NULL,
  `item` varchar(100) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `supplier` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`purchaseid`),
  UNIQUE KEY `unique_purchase` (`invoiceno`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase`
--

LOCK TABLES `purchase` WRITE;
/*!40000 ALTER TABLE `purchase` DISABLE KEYS */;
INSERT INTO `purchase` VALUES (1,'IN234','2013-09-19','Laptop',4,'Ewis');
/*!40000 ALTER TABLE `purchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `replacement`
--

DROP TABLE IF EXISTS `replacement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `replacement` (
  `replacementid` int(11) NOT NULL AUTO_INCREMENT,
  `reson` text,
  `replacedate` date DEFAULT NULL,
  `olduser` varchar(50) DEFAULT NULL,
  `oldlocation` int(11) DEFAULT NULL,
  `newlocation` int(11) DEFAULT NULL,
  `newserialno` varchar(200) DEFAULT NULL,
  `serialno` varchar(200) DEFAULT NULL,
  `responsibleuser` int(11) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`replacementid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `replacement`
--

LOCK TABLES `replacement` WRITE;
/*!40000 ALTER TABLE `replacement` DISABLE KEYS */;
INSERT INTO `replacement` VALUES (2,'fasdfdsfs','2013-09-12','IUEIOIIOD',1,NULL,NULL,'PC01',NULL,'null');
/*!40000 ALTER TABLE `replacement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roleperm`
--

DROP TABLE IF EXISTS `roleperm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roleperm` (
  `roleid` int(11) NOT NULL,
  `permid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roleperm`
--

LOCK TABLES `roleperm` WRITE;
/*!40000 ALTER TABLE `roleperm` DISABLE KEYS */;
INSERT INTO `roleperm` VALUES (1,17),(1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,10),(1,8),(1,9),(1,11),(1,12),(1,13),(1,14),(1,19),(1,20),(1,24),(1,15),(1,22),(1,18),(1,21),(1,16),(1,23),(2,17),(2,1),(2,2),(2,3),(2,4),(2,5),(2,6),(2,7),(2,8),(2,9),(2,14),(2,15);
/*!40000 ALTER TABLE `roleperm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `roleid` int(11) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(50) NOT NULL,
  PRIMARY KEY (`roleid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'administrator'),(2,'supportassistant'),(3,'user');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `setting`
--

DROP TABLE IF EXISTS `setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `setting` (
  `settingsid` int(11) NOT NULL AUTO_INCREMENT,
  `sname` varchar(200) NOT NULL,
  `svalue` text,
  PRIMARY KEY (`settingsid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `setting`
--

LOCK TABLES `setting` WRITE;
/*!40000 ALTER TABLE `setting` DISABLE KEYS */;
INSERT INTO `setting` VALUES (1,'hostname','smtp.gmail.com'),(2,'emailpassword','bDcnpHhycIZd7duDbL4Hpw=='),(3,'emailusername','gamunuud@gmail.com');
/*!40000 ALTER TABLE `setting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplier` (
  `supplierid` int(11) NOT NULL AUTO_INCREMENT,
  `companyname` varchar(200) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `purchase` int(11) DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  PRIMARY KEY (`supplierid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (1,'Ewis','0112545878',NULL,NULL,'ewiscom@ewis.com','Address ewis, Colombo',NULL),(2,'Next Computers','0118455221',NULL,NULL,'nextcom@next.lk','Address Next, Colombo',NULL),(3,'Happy Supplier','0715600880',NULL,NULL,'happyhappy@happy.com','Colombo 5',NULL),(7,'Hello Supplier','0715652205',NULL,NULL,'helo@hellp.com','Address supplier',NULL);
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket` (
  `ticketid` int(11) NOT NULL AUTO_INCREMENT,
  `subject` text,
  `message` text,
  `status` varchar(10) DEFAULT NULL,
  `userid` varchar(20) DEFAULT NULL,
  `priority` varchar(20) DEFAULT NULL,
  `highlight` char(1) DEFAULT NULL,
  `othighlight` char(1) DEFAULT NULL,
  `ctime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ticketid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (2,'Enter subject here..','Enter message here..\n\nfg\ndfg\ndf\ng\ndf\ng\ndfs\ngdfs\ng\ndfs','open','1','high','N','N','2013-09-26 06:08:18'),(3,'Enter subject here..','Enter message here..','open','1','low','N','N','2013-09-26 12:26:44'),(4,'DEFR KKLEO KLDOE','EF  sdfds sf sfsdf sfsefewaf f afsd fcxvd fsdf xafe cvxzd sd ds..........','open','1','medium','N','Y','2013-10-01 06:41:20'),(6,'Enter subject here..','Enter message here..','open','1','high','N','Y','2013-10-01 06:46:06'),(7,'dsfsd','f','open','1','high','N','Y','2013-10-01 06:46:36'),(8,'Enter subject here..','Enter message here..','open','1','high','N','Y','2013-10-01 07:08:16');
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userhistory`
--

DROP TABLE IF EXISTS `userhistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userhistory` (
  `entryid` int(11) NOT NULL AUTO_INCREMENT,
  `logintime` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `logiouttime` timestamp(6) NOT NULL DEFAULT '0000-00-00 00:00:00.000000',
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`entryid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userhistory`
--

LOCK TABLES `userhistory` WRITE;
/*!40000 ALTER TABLE `userhistory` DISABLE KEYS */;
/*!40000 ALTER TABLE `userhistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `roleid` int(11) NOT NULL,
  `email` varchar(200) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,1,'gamunuud@gmail.com',NULL,'12345','Gamunu Bandara','gamunu'),(2,1,'gamunuud@gmail.com',NULL,'12345','Gamunu Bandara','kalpani');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warranty`
--

DROP TABLE IF EXISTS `warranty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `warranty` (
  `warrantyid` int(11) NOT NULL AUTO_INCREMENT,
  `dateofpurchase` date DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  `expirydate` date DEFAULT NULL,
  `serialno` varchar(30) DEFAULT NULL,
  `producttype` varchar(30) DEFAULT NULL,
  `supplierid` int(11) DEFAULT NULL,
  PRIMARY KEY (`warrantyid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warranty`
--

LOCK TABLES `warranty` WRITE;
/*!40000 ALTER TABLE `warranty` DISABLE KEYS */;
/*!40000 ALTER TABLE `warranty` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-10-01  1:03:27
