-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: smart_liquor_shop
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Create database if not exists
CREATE DATABASE IF NOT EXISTS smart_liquor_shop;

-- Use the database
USE smart_liquor_shop;

-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: smart_liquor_shop
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
--
-- Table structure for table `agencyproduct`
--

DROP TABLE IF EXISTS `agencyproduct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `agencyproduct` (
  `APID` int(5) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `APName` varchar(80) NOT NULL,
  `APTotalUnits` int NOT NULL,
  `APWeightOfUnit` varchar(45) NOT NULL,
  `APBuyingPricePerUnit` float NOT NULL,
  `APMarketPricePerUnit` float NOT NULL,
  `APSellingPricePerUnit` float NOT NULL,
  `APMDate` varchar(10) NOT NULL,
  `APEDate` varchar(10) NOT NULL,
  `APADate` varchar(10) NOT NULL,
  `APDADate` varchar(10) NOT NULL,
  `APBarcode` varchar(45) NOT NULL,
  PRIMARY KEY (`APID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agencyproduct`
--

LOCK TABLES `agencyproduct` WRITE;
/*!40000 ALTER TABLE `agencyproduct` DISABLE KEYS */;
INSERT INTO `agencyproduct` VALUES (00017,'Label5',60,'1000 ML',8500,9500,9400,'2026-02-01','2027-03-13','2026-02-06','2027-02-01','123456789'),(00018,'Black label',60,'1000 ML',10500,12500,12400,'2026-02-01','2027-03-13','2026-02-06','2027-02-01','123456788');
/*!40000 ALTER TABLE `agencyproduct` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `allowance`
--

DROP TABLE IF EXISTS `allowance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `allowance` (
  `AID` int(4) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `ATitle` varchar(45) NOT NULL,
  `ADescription` varchar(100) NOT NULL,
  `AType` varchar(15) NOT NULL,
  `AValue` float NOT NULL,
  PRIMARY KEY (`AID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `allowance`
--

LOCK TABLES `allowance` WRITE;
/*!40000 ALTER TABLE `allowance` DISABLE KEYS */;
/*!40000 ALTER TABLE `allowance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `allowancepay`
--

DROP TABLE IF EXISTS `allowancepay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `allowancepay` (
  `APEID` int(5) unsigned zerofill NOT NULL,
  `APAID` int(4) unsigned zerofill NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `allowancepay`
--

LOCK TABLES `allowancepay` WRITE;
/*!40000 ALTER TABLE `allowancepay` DISABLE KEYS */;
/*!40000 ALTER TABLE `allowancepay` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bakeryproduct`
--

DROP TABLE IF EXISTS `bakeryproduct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bakeryproduct` (
  `BPID` int(4) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `BPName` varchar(80) NOT NULL,
  `BPType` varchar(15) NOT NULL,
  `BPWeight` varchar(45) NOT NULL,
  `BPDescription` varchar(250) NOT NULL,
  `BPPrice` float NOT NULL,
  `BPStatus` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`BPID`),
  UNIQUE KEY `BPID_UNIQUE` (`BPID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bakeryproduct`
--

LOCK TABLES `bakeryproduct` WRITE;
/*!40000 ALTER TABLE `bakeryproduct` DISABLE KEYS */;
/*!40000 ALTER TABLE `bakeryproduct` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `basicsalaryscheme`
--

DROP TABLE IF EXISTS `basicsalaryscheme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `basicsalaryscheme` (
  `BSSID` int(4) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `BSSTitle` varchar(45) NOT NULL,
  `BSSAmount` float NOT NULL,
  `BSSAddedDate` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`BSSID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `basicsalaryscheme`
--

LOCK TABLES `basicsalaryscheme` WRITE;
/*!40000 ALTER TABLE `basicsalaryscheme` DISABLE KEYS */;
/*!40000 ALTER TABLE `basicsalaryscheme` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill` (
  `BNo` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `BCashierID` varchar(45) NOT NULL,
  `BDate` date NOT NULL,
  `BTime` varchar(8) NOT NULL,
  `BClearance` varchar(20) NOT NULL,
  PRIMARY KEY (`BNo`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
INSERT INTO `bill` VALUES (00000000049,'administrator','2026-02-06','02:12:22','Pending'),(00000000050,'administrator','2026-02-06','04:07:39','Pending'),(00000000051,'administrator','2026-02-06','04:22:28','Pending'),(00000000052,'administrator','2026-02-06','04:27:44','Pending'),(00000000053,'administrator','2026-02-06','07:17:27','Pending'),(00000000054,'administrator','2026-02-06','08:03:45','Pending'),(00000000055,'administrator','2026-02-06','08:06:14','Pending'),(00000000056,'administrator','2026-03-07','16:11:08','Pending'),(00000000057,'administrator','2026-03-08','10:31:12','Pending'),(00000000058,'administrator','2026-03-08','10:36:44','Pending'),(00000000062,'administrator','2026-03-22','15:02:58','Pending'),(00000000063,'administrator','2026-03-22','15:07:32','Pending'),(00000000064,'administrator','2026-03-22','15:11','Pending'),(00000000065,'administrator','2026-03-22','15:14:30','Pending'),(00000000066,'administrator','2026-03-22','15:21:23','Pending'),(00000000067,'administrator','2026-03-22','15:40:20','Pending'),(00000000068,'administrator','2026-03-22','15:45:05','Pending'),(00000000069,'administrator','2026-03-22','15:53:25','Pending'),(00000000070,'administrator','2026-03-22','15:58:16','Pending'),(00000000071,'administrator','2026-03-22','16:00:56','Pending'),(00000000072,'administrator','2026-03-22','16:06:16','Pending'),(00000000073,'administrator','2026-03-22','16:08:09','Pending'),(00000000074,'administrator','2026-03-22','17:47:40','Pending'),(00000000075,'administrator','2026-03-22','17:49:01','Pending'),(00000000076,'administrator','2026-03-22','18:02:15','Pending'),(00000000077,'administrator','2026-03-22','18:03:34','Pending'),(00000000078,'administrator','2026-03-22','18:22:33','Pending'),(00000000079,'administrator','2026-03-22','18:28:34','Pending');
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bottlecap`
--

DROP TABLE IF EXISTS `bottlecap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bottlecap` (
  `bCId` int NOT NULL AUTO_INCREMENT,
  `bCTagId` int NOT NULL,
  `bCSpecs` varchar(45) DEFAULT NULL,
  `bCRegisteredDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `bCAvailabilityStatus` varchar(45) NOT NULL,
  `bCAllocationStatus` varchar(45) NOT NULL,
  PRIMARY KEY (`bCId`),
  UNIQUE KEY `bCTagId_UNIQUE` (`bCTagId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bottlecap`
--

LOCK TABLES `bottlecap` WRITE;
/*!40000 ALTER TABLE `bottlecap` DISABLE KEYS */;
INSERT INTO `bottlecap` VALUES (1,569055,'','2026-03-07 21:10:55','AVAILABLE','ALLOCATED'),(4,657730,'','2026-03-07 22:30:43','NOT AVAILABLE','NOT ALLOCATED'),(5,123526,'hg','2026-03-07 23:16:40','AVAILABLE','NOT ALLOCATED'),(6,873173,'hg','2026-03-08 10:22:16','AVAILABLE','ALLOCATED'),(7,763784,'hg','2026-03-08 10:22:30','AVAILABLE','ALLOCATED'),(8,363585,'hg','2026-03-08 10:22:38','AVAILABLE','ALLOCATED'),(9,954532,'hg','2026-03-08 10:22:46','AVAILABLE','ALLOCATED'),(10,775636,'hg','2026-03-08 10:22:55','AVAILABLE','NOT ALLOCATED');
/*!40000 ALTER TABLE `bottlecap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `EID` int(5) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `EName` varchar(100) NOT NULL,
  `ENIC` varchar(12) NOT NULL,
  `EAddress` varchar(100) NOT NULL,
  `EGender` varchar(6) NOT NULL,
  `EDOB` date NOT NULL,
  `ETitle` varchar(45) NOT NULL,
  `EPhone` int NOT NULL,
  `EBankName` varchar(40) NOT NULL,
  `EAccNo` bigint NOT NULL,
  `EBSSID` int NOT NULL,
  PRIMARY KEY (`EID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `incomestatement`
--

DROP TABLE IF EXISTS `incomestatement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `incomestatement` (
  `ISID` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `ISType` varchar(15) DEFAULT NULL,
  `ISPeriod` varchar(20) DEFAULT NULL,
  `ISAmount` double DEFAULT NULL,
  PRIMARY KEY (`ISID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `incomestatement`
--

LOCK TABLES `incomestatement` WRITE;
/*!40000 ALTER TABLE `incomestatement` DISABLE KEYS */;
/*!40000 ALTER TABLE `incomestatement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
  `IID` int(8) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `IName` varchar(80) NOT NULL,
  `IUnitsPerBlock` int DEFAULT NULL,
  `IBlocks` int DEFAULT NULL,
  `IWeightOfUnit` varchar(45) NOT NULL,
  `IBuyingPricePerUnit` float NOT NULL,
  `IExpireDate` date NOT NULL,
  `IAddedDate` date NOT NULL,
  `IMinQuantityLimit` int NOT NULL,
  `IAvailableQuantity` int DEFAULT NULL,
  PRIMARY KEY (`IID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `itemwithdraw`
--

DROP TABLE IF EXISTS `itemwithdraw`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `itemwithdraw` (
  `IWID` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `IWIID` int(11) unsigned zerofill NOT NULL,
  `IWDescription` varchar(100) NOT NULL,
  `IWQuantity` int NOT NULL,
  `IWUser` varchar(45) NOT NULL,
  `IWDate` date NOT NULL,
  `IWTime` varchar(10) NOT NULL,
  PRIMARY KEY (`IWID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itemwithdraw`
--

LOCK TABLES `itemwithdraw` WRITE;
/*!40000 ALTER TABLE `itemwithdraw` DISABLE KEYS */;
/*!40000 ALTER TABLE `itemwithdraw` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jobtitle`
--

DROP TABLE IF EXISTS `jobtitle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jobtitle` (
  `JTID` int NOT NULL,
  `JTitle` varchar(45) NOT NULL,
  PRIMARY KEY (`JTID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobtitle`
--

LOCK TABLES `jobtitle` WRITE;
/*!40000 ALTER TABLE `jobtitle` DISABLE KEYS */;
/*!40000 ALTER TABLE `jobtitle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordermenuitem`
--

DROP TABLE IF EXISTS `ordermenuitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordermenuitem` (
  `OMIID` int(5) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `OMIImage` longblob,
  `OMIName` varchar(80) DEFAULT NULL,
  `OMIDescription` varchar(250) DEFAULT NULL,
  `OMIWeight` varchar(45) DEFAULT NULL,
  `OMIPrice` float DEFAULT NULL,
  `OMIStatus` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`OMIID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordermenuitem`
--

LOCK TABLES `ordermenuitem` WRITE;
/*!40000 ALTER TABLE `ordermenuitem` DISABLE KEYS */;
/*!40000 ALTER TABLE `ordermenuitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `OID` int(6) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `OOMID` int(5) unsigned zerofill DEFAULT NULL,
  `OType` varchar(100) DEFAULT NULL,
  `ODetails` varchar(100) DEFAULT NULL,
  `OQuantity` varchar(45) DEFAULT NULL,
  `ODeliveryDate` date DEFAULT NULL,
  `ODeliveryTime` varchar(50) DEFAULT NULL,
  `OCustomerName` varchar(80) DEFAULT NULL,
  `OCustomerNIC` varchar(12) DEFAULT NULL,
  `OCustomerPhone` int DEFAULT NULL,
  `OTakenDate` date DEFAULT NULL,
  `OTakenTime` varchar(50) DEFAULT NULL,
  `OTakenUID` int(5) unsigned zerofill DEFAULT NULL,
  `OStatus` varchar(20) DEFAULT NULL,
  `ODeliveredDate` date DEFAULT NULL,
  `ODeliveredTime` varchar(50) DEFAULT NULL,
  `OProcessingStatus` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`OID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `otherexpenses`
--

DROP TABLE IF EXISTS `otherexpenses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `otherexpenses` (
  `OEID` int(7) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `OETitle` varchar(45) NOT NULL,
  `OEDescription` varchar(100) NOT NULL,
  `OEPeriod` varchar(45) NOT NULL,
  `OEAmount` double NOT NULL,
  `OEPaidDate` varchar(12) NOT NULL,
  `OEAddedDate` varchar(12) NOT NULL,
  PRIMARY KEY (`OEID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otherexpenses`
--

LOCK TABLES `otherexpenses` WRITE;
/*!40000 ALTER TABLE `otherexpenses` DISABLE KEYS */;
/*!40000 ALTER TABLE `otherexpenses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paysheet`
--

DROP TABLE IF EXISTS `paysheet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paysheet` (
  `PSID` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `PSEID` int(5) unsigned zerofill DEFAULT NULL,
  `PSEName` varchar(45) DEFAULT NULL,
  `PSENIC` varchar(12) DEFAULT NULL,
  `PSBSSTitle` varchar(45) DEFAULT NULL,
  `PSBSSAmount` double DEFAULT NULL,
  `PSTotalAllowances` double DEFAULT NULL,
  `PSBank` varchar(40) DEFAULT NULL,
  `PSAccount` bigint DEFAULT NULL,
  `PSGeneratedDate` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`PSID`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paysheet`
--

LOCK TABLES `paysheet` WRITE;
/*!40000 ALTER TABLE `paysheet` DISABLE KEYS */;
/*!40000 ALTER TABLE `paysheet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paysheetallowance`
--

DROP TABLE IF EXISTS `paysheetallowance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paysheetallowance` (
  `PSAID` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `PSAPSID` int(10) unsigned zerofill DEFAULT NULL,
  `PSAAID` int(4) unsigned zerofill DEFAULT NULL,
  `PSAATitle` varchar(45) DEFAULT NULL,
  `PSAAType` varchar(15) DEFAULT NULL,
  `PSAAAmount` float DEFAULT NULL,
  PRIMARY KEY (`PSAID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paysheetallowance`
--

LOCK TABLES `paysheetallowance` WRITE;
/*!40000 ALTER TABLE `paysheetallowance` DISABLE KEYS */;
/*!40000 ALTER TABLE `paysheetallowance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase`
--

DROP TABLE IF EXISTS `purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase` (
  `PID` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `PItemID` int(5) unsigned zerofill NOT NULL DEFAULT '00000',
  `PSupplierID` int(4) unsigned zerofill DEFAULT '0000',
  `PType` varchar(10) NOT NULL,
  `PDate` varchar(10) DEFAULT NULL,
  `PStatus` varchar(15) DEFAULT NULL,
  `PBankInfo` varchar(200) DEFAULT 'None',
  `PBankPaidDate` varchar(12) DEFAULT 'None',
  PRIMARY KEY (`PID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase`
--

LOCK TABLES `purchase` WRITE;
/*!40000 ALTER TABLE `purchase` DISABLE KEYS */;
INSERT INTO `purchase` VALUES (0000000019,00017,0009,'Agency','2026-02-06','Pending','None','None'),(0000000020,00018,0009,'Agency','2026-02-06','Pending','None','None');
/*!40000 ALTER TABLE `purchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salesitem`
--

DROP TABLE IF EXISTS `salesitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salesitem` (
  `SIID` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `SIPID` int(11) unsigned zerofill NOT NULL,
  `SIPName` varchar(45) NOT NULL,
  `SIWeight` varchar(20) NOT NULL,
  `SIQuantity` int NOT NULL,
  `SIUnitPrice` float NOT NULL,
  `SITotalAmount` double NOT NULL,
  `SIBNo` int NOT NULL,
  `SIType` varchar(20) NOT NULL,
  `SIItemUnlockStatus` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`SIID`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salesitem`
--

LOCK TABLES `salesitem` WRITE;
/*!40000 ALTER TABLE `salesitem` DISABLE KEYS */;
INSERT INTO `salesitem` VALUES (00000000067,00000000017,'Label5','1000 ML',2,9400,18800,49,'Agency Product',0),(00000000068,00000000018,'Black label','1000 ML',1,12400,12400,50,'Agency Product',0),(00000000069,00000000017,'Label5','1000 ML',2,9400,18800,50,'Agency Product',0),(00000000070,00000000017,'Label5','1000 ML',1,9400,9400,51,'Agency Product',0),(00000000071,00000000018,'Black label','1000 ML',1,12400,12400,51,'Agency Product',0),(00000000072,00000000017,'Label5','1000 ML',1,9400,9400,52,'Agency Product',0),(00000000073,00000000018,'Black label','1000 ML',2,12400,24800,52,'Agency Product',0),(00000000074,00000000017,'Label5','1000 ML',1,9400,9400,53,'Agency Product',0),(00000000075,00000000018,'Black label','1000 ML',1,12400,12400,54,'Agency Product',0),(00000000076,00000000017,'Label5','1000 ML',1,9400,9400,55,'Agency Product',0),(00000000077,00000000018,'Black label','1000 ML',2,12400,24800,56,'Agency Product',0),(00000000078,00000000017,'Label5','1000 ML',2,9400,18800,57,'Agency Product',0),(00000000079,00000000018,'Black label','1000 ML',1,12400,12400,58,'Agency Product',0),(00000000083,00000000017,'Label5','1000 ML',1,9400,9400,62,'Agency Product',0),(00000000084,00000000017,'Label5','1000 ML',1,9400,9400,63,'Agency Product',0),(00000000085,00000000017,'Label5','1000 ML',1,9400,9400,64,'Agency Product',0),(00000000086,00000000018,'Black label','1000 ML',1,12400,12400,65,'Agency Product',0),(00000000087,00000000018,'Black label','1000 ML',1,12400,12400,66,'Agency Product',0),(00000000088,00000000018,'Black label','1000 ML',1,12400,12400,67,'Agency Product',0),(00000000089,00000000017,'Label5','1000 ML',1,9400,9400,68,'Agency Product',0),(00000000090,00000000018,'Black label','1000 ML',1,12400,12400,69,'Agency Product',0),(00000000091,00000000018,'Black label','1000 ML',1,12400,12400,70,'Agency Product',0),(00000000092,00000000017,'Label5','1000 ML',1,9400,9400,71,'Agency Product',0),(00000000093,00000000018,'Black label','1000 ML',1,12400,12400,72,'Agency Product',0),(00000000094,00000000018,'Black label','1000 ML',1,12400,12400,73,'Agency Product',0),(00000000095,00000000018,'Black label','1000 ML',1,12400,12400,74,'Agency Product',0),(00000000096,00000000017,'Label5','1000 ML',1,9400,9400,75,'Agency Product',0),(00000000097,00000000018,'Black label','1000 ML',1,12400,12400,76,'Agency Product',0),(00000000098,00000000018,'Black label','1000 ML',1,12400,12400,77,'Agency Product',0),(00000000099,00000000018,'Black label','1000 ML',1,12400,12400,78,'Agency Product',0),(00000000100,00000000018,'Black label','1000 ML',2,12400,24800,79,'Agency Product',0);
/*!40000 ALTER TABLE `salesitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplierinfo`
--

DROP TABLE IF EXISTS `supplierinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplierinfo` (
  `SIID` int(4) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `SIName` varchar(80) NOT NULL,
  `SIAddress` varchar(100) NOT NULL,
  `SIPhone1` int NOT NULL,
  `SIPhone2` int DEFAULT NULL,
  `SIEmail` varchar(80) DEFAULT NULL,
  `SIType` varchar(20) DEFAULT NULL,
  `SIBankName` varchar(80) NOT NULL,
  `SIAccNo` bigint NOT NULL,
  PRIMARY KEY (`SIID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplierinfo`
--

LOCK TABLES `supplierinfo` WRITE;
/*!40000 ALTER TABLE `supplierinfo` DISABLE KEYS */;
INSERT INTO `supplierinfo` VALUES (0009,'Laal Rajapaksha','12/A katunayake road, Veyangoda',779988456,112233654,'laalRajapaksh@gmail.com','Agency','BOC Veyangoda',844556321);
/*!40000 ALTER TABLE `supplierinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `systemuser`
--

DROP TABLE IF EXISTS `systemuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `systemuser` (
  `SUUID` int NOT NULL,
  `SUEID` varchar(45) NOT NULL,
  PRIMARY KEY (`SUUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `systemuser`
--

LOCK TABLES `systemuser` WRITE;
/*!40000 ALTER TABLE `systemuser` DISABLE KEYS */;
/*!40000 ALTER TABLE `systemuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag_register`
--

DROP TABLE IF EXISTS `tag_register`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tag_register` (
  `id` int NOT NULL AUTO_INCREMENT,
  `productId` int unsigned NOT NULL,
  `tagId` varchar(45) NOT NULL,
  `createdDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `registeredStatus` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_product` (`productId`),
  CONSTRAINT `fk_product` FOREIGN KEY (`productId`) REFERENCES `agencyproduct` (`APID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag_register`
--

LOCK TABLES `tag_register` WRITE;
/*!40000 ALTER TABLE `tag_register` DISABLE KEYS */;
INSERT INTO `tag_register` VALUES (1,18,'569055','2026-03-08 21:27:13','UN-REGISTERED'),(2,18,'123526','2026-03-08 21:27:13','UN-REGISTERED'),(9,17,'873173','2026-03-21 15:14:23','UN-REGISTERED'),(15,17,'873173','2026-03-21 16:50:51','UN-REGISTERED'),(16,17,'763784','2026-03-21 16:50:51','UN-REGISTERED'),(17,17,'123526','2026-03-21 17:46:55','UN-REGISTERED'),(18,17,'873173','2026-03-21 17:46:55','UN-REGISTERED'),(19,17,'123526','2026-03-21 18:03:14','UN-REGISTERED'),(20,17,'873173','2026-03-21 18:03:14','UN-REGISTERED'),(21,18,'123526','2026-03-21 18:14:51','UN-REGISTERED'),(22,18,'123526','2026-03-21 18:37:58','UN-REGISTERED'),(23,17,'123526','2026-03-21 19:16:49','UN-REGISTERED'),(24,17,'873173','2026-03-21 19:16:49','UN-REGISTERED'),(25,18,'123526','2026-03-21 19:27:03','UN-REGISTERED'),(26,18,'873173','2026-03-21 19:27:03','REGISTERED'),(27,17,'763784','2026-03-21 21:20:29','UN-REGISTERED'),(28,17,'363585','2026-03-21 21:20:29','UN-REGISTERED'),(29,17,'775636','2026-03-22 12:03:10','UN-REGISTERED'),(30,18,'763784','2026-03-22 13:11:10','REGISTERED'),(31,18,'363585','2026-03-22 13:33:38','REGISTERED'),(32,17,'954532','2026-03-22 14:49:51','REGISTERED');
/*!40000 ALTER TABLE `tag_register` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `UID` varchar(45) NOT NULL,
  `UName` varchar(80) NOT NULL,
  `UPassword` varchar(100) NOT NULL,
  `UType` varchar(11) NOT NULL,
  `UStatus` varchar(10) NOT NULL,
  PRIMARY KEY (`UID`),
  UNIQUE KEY `UID_UNIQUE` (`UID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('200115902738','Tharaka Rajapaksha','e10adc3949ba59abbe56e057f20f883e','Admin','Active'),('administrator','administrator','21232f297a57a5a743894a0e4a801fc3','Admin','Active');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'smart_liquor_shop'
--

--
-- Dumping routines for database 'smart_liquor_shop'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-03-22 22:35:42
