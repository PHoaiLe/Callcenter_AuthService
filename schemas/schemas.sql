CREATE DATABASE  IF NOT EXISTS `callcenter_auth` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `callcenter_auth`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: callcenter_auth
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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id` varchar(512) NOT NULL,
  `auth_info_id` varchar(512) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `status` int unsigned NOT NULL,
  `register_type` int unsigned NOT NULL,
  `created_at` datetime NOT NULL,
  `role` int unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `FK_status_TO_account_status_idx` (`status`),
  KEY `FK_register_type_TO_account_register_type_idx` (`register_type`),
  CONSTRAINT `FK_register_type_TO_account_register_type` FOREIGN KEY (`register_type`) REFERENCES `account_register_type` (`id`),
  CONSTRAINT `FK_status_TO_account_status` FOREIGN KEY (`status`) REFERENCES `account_status` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES ('81dcd250-9ba6-4407-8c50-cc04cc5e3f3a','1ae0dae6-82fb-4ee7-8a9e-c3357dc28157',1,0,'2024-10-14 17:45:56',1),('85b9ee3d-0932-49a3-a873-aa5f15b50a47','6a76a176-1ce0-4a34-af90-0c3b8ba1af96',1,0,'2024-10-14 17:35:18',1);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_register_type`
--

DROP TABLE IF EXISTS `account_register_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_register_type` (
  `id` int unsigned NOT NULL,
  `name` varchar(90) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `value` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `value_UNIQUE` (`value`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_register_type`
--

LOCK TABLES `account_register_type` WRITE;
/*!40000 ALTER TABLE `account_register_type` DISABLE KEYS */;
INSERT INTO `account_register_type` VALUES (0,'email and password authentication','EAP_AUTHENTICATION'),(1,'google authentication','GOOGLE_AUTHENTICATION'),(2,'facebook authentication','FACEBOOK_AUTHENTICATION');
/*!40000 ALTER TABLE `account_register_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_status`
--

DROP TABLE IF EXISTS `account_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_status` (
  `id` int unsigned NOT NULL,
  `name` varchar(90) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `value` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `value_UNIQUE` (`value`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_status`
--

LOCK TABLES `account_status` WRITE;
/*!40000 ALTER TABLE `account_status` DISABLE KEYS */;
INSERT INTO `account_status` VALUES (0,'blocked','BLOCKED'),(1,'initialized','INITIALIZED'),(2,'available','AVAILABLE');
/*!40000 ALTER TABLE `account_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `banned_permission`
--

DROP TABLE IF EXISTS `banned_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `banned_permission` (
  `account_id` varchar(512) NOT NULL,
  `permission_id` int unsigned NOT NULL,
  `role_id` int unsigned NOT NULL,
  PRIMARY KEY (`account_id`,`permission_id`),
  KEY `FK_banned_permission_TO_permission_idx` (`permission_id`),
  KEY `FK_banned_permission_TO_role_idx` (`role_id`),
  CONSTRAINT `FK_banned_permission_TO_permission` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `FK_banned_permission_TO_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banned_permission`
--

LOCK TABLES `banned_permission` WRITE;
/*!40000 ALTER TABLE `banned_permission` DISABLE KEYS */;
INSERT INTO `banned_permission` VALUES ('85b9ee3d-0932-49a3-a873-aa5f15b50a47',0,1);
/*!40000 ALTER TABLE `banned_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `email_password_authentication`
--

DROP TABLE IF EXISTS `email_password_authentication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `email_password_authentication` (
  `id` varchar(512) NOT NULL,
  `email` varchar(512) NOT NULL,
  `password` longtext NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `email_password_authentication`
--

LOCK TABLES `email_password_authentication` WRITE;
/*!40000 ALTER TABLE `email_password_authentication` DISABLE KEYS */;
INSERT INTO `email_password_authentication` VALUES ('1ae0dae6-82fb-4ee7-8a9e-c3357dc28157','phuongle123@gmail.com','$2a$10$doReJMvPUnLADkFezJQ0QuQOcL4h9WL8OqHhbkoDjb7OlelhquluG'),('6a76a176-1ce0-4a34-af90-0c3b8ba1af96','phuongle@gmail.com','$2a$16$KRt59RKRGyZrsEVpnGHtx.q2gGfDmTXkph5GJpWg0YEc/m3pizt3u');
/*!40000 ALTER TABLE `email_password_authentication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permission` (
  `id` int unsigned NOT NULL,
  `name` varchar(90) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `value` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `endpoint` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `value_UNIQUE` (`value`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (0,'get full customer info','GET_FULL_CUSTOMER_INFO','GET::/apis/customer/list'),(1,'get list of customers','GET_LIST_OF_CUSTOMER','POST::/apis/'),(2,'get list of stored address','GET_LIST_OF_ADDRESSES','GET::/apis/'),(3,'get a stored address','GET_STORED_ADDRESS','GET::/apis/'),(4,'update customer info','UPDATE_CUSTOMER_INFO','PUT::/apis/'),(5,'create customer info','CREATE_CUSTOMER_INFO','POST::/apis'),(6,'change password','CHANGE_PASSWORD','POST::/apis/'),(7,'update account status','UPDATE_ACCOUNT_STATUS','PUT::/apis/');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provided_refresh_token`
--

DROP TABLE IF EXISTS `provided_refresh_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `provided_refresh_token` (
  `id` varchar(512) NOT NULL,
  `token` longtext NOT NULL,
  `account_id` varchar(512) NOT NULL,
  `created_at` datetime NOT NULL,
  `expired_at` datetime NOT NULL,
  `used_at` datetime DEFAULT NULL,
  `is_available` tinyint NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `FK_provided_refresh_token_TO_account_idx` (`account_id`),
  CONSTRAINT `FK_provided_refresh_token_TO_account` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provided_refresh_token`
--

LOCK TABLES `provided_refresh_token` WRITE;
/*!40000 ALTER TABLE `provided_refresh_token` DISABLE KEYS */;
INSERT INTO `provided_refresh_token` VALUES ('18f4adb0-8d33-4d26-8443-192b92a52d30','eyJhbGciOiJIUzI1NiJ9.eyJVU1IiOjEsIlVSVCI6MCwiTlVPIjoiZWM5NjI2ODQtNTJhYy00N2I4LTgzYTItMzY1Yjc3ODVjOGE3IiwiQUlEIjoiODFkY2QyNTAtOWJhNi00NDA3LThjNTAtY2MwNGNjNWUzZjNhIn0.tv2Kd35IwwxrtDNPqrSd_a1Mm3RuGVZsBXJZIS0gDOg','81dcd250-9ba6-4407-8c50-cc04cc5e3f3a','2024-10-15 15:28:33','2024-10-15 15:28:35',NULL,0),('1b358924-5b36-4b29-b2ba-13e223980168','$2a$10$0l2.NjdWbQvoWtWk9Fq6HOQKTcvQBIxqV/eAEO9BV2bz5t74S9H9i','81dcd250-9ba6-4407-8c50-cc04cc5e3f3a','2024-11-04 12:42:20','2024-11-04 13:12:20',NULL,1),('4d4eed36-7b56-4a49-a039-d4f0857a41d7','$2a$10$yZ7T7jLEGiaCpwK/adgmbuu6vaOkdYgzKSKRS.RLY3cXX2nGsOKR6','81dcd250-9ba6-4407-8c50-cc04cc5e3f3a','2024-11-04 12:27:17','2024-11-04 12:57:17',NULL,1),('64317efe-1d6a-4e81-8514-15b8e55138ab','$2a$10$JtpHo/JQkCLJo3umDA.WKe8aFq2sc1/6bbVnP.0pC20C6jiBkrIV2','81dcd250-9ba6-4407-8c50-cc04cc5e3f3a','2024-11-04 17:26:41','2024-11-04 17:56:41',NULL,1),('77fddf0f-2c1a-4e87-899d-66feaa99d35a','$2a$10$sqsy83hwyN0IRFWS/dhjDe9uDdBse5/yG8ILJc5OcCCIoe2RWx7Sq','81dcd250-9ba6-4407-8c50-cc04cc5e3f3a','2024-11-04 14:45:52','2024-11-04 15:15:52',NULL,1),('78254e8f-ecc4-44f3-8a5b-b87e9f9d4349','$2a$10$TaS7p0tf08aSDuFgkHFFFu.wi8ycCB3FPFH7lCUFO4k4prrscn2W2','81dcd250-9ba6-4407-8c50-cc04cc5e3f3a','2024-12-12 17:40:11','2024-12-12 18:10:11','2024-12-12 17:41:31',0),('7c580c05-5961-415f-a177-8dd180376ea3','$2a$10$qavV9N7qgwrvXgP6nZFup.iHhmCokrl./DW1V4JnDP6MhUQKOWqHa','81dcd250-9ba6-4407-8c50-cc04cc5e3f3a','2024-12-12 17:33:26','2024-12-12 18:03:26','2024-12-12 17:35:20',0),('87fc4d55-a176-46e9-be8b-5b4439fdd490','$2a$10$Amu5/xDrYJNvY2iyEyKMseLb4r2P3s4Pzl3L9PyQS/wtwxeZlsb0.','81dcd250-9ba6-4407-8c50-cc04cc5e3f3a','2024-12-12 17:35:20','2024-12-12 18:05:20','2024-12-12 17:40:11',0),('8d0414c0-9f97-44bd-a546-9bc43201329b','$2a$10$i04I9qvTkGzWEKZqa4SNkO9R12DCbtD3lJDgTvOiu2nAhnNMu1jw6','81dcd250-9ba6-4407-8c50-cc04cc5e3f3a','2024-11-04 17:01:01','2024-11-04 17:31:01',NULL,1),('a6b6ac8f-86bc-49c0-b28e-f5dc2bdac2bc','$2a$10$zmQMTjU2C56z7Fq4DYrPlOtpxYjF8g/mww69d8E10TTWPo59n1F6W','81dcd250-9ba6-4407-8c50-cc04cc5e3f3a','2024-12-12 17:32:37','2024-12-12 18:02:37','2024-12-12 17:32:48',0),('b6b5403c-929d-417e-a729-adf8cb1ab829','$2a$10$wkv6Bue4.DQHZ.Gyk/u1nefEv8h73tF/WFZ572su7gyfzd0vuV85S','81dcd250-9ba6-4407-8c50-cc04cc5e3f3a','2024-12-12 17:41:31','2024-12-12 18:11:31',NULL,1),('c0cb6b9a-f4e7-4be3-8a62-8d8c7831dbd6','$2a$10$HRDi3BcJRY0yhjKiTMVeT.UEThdguN1e9JXcjrN3gUGbHCHThlcPu','81dcd250-9ba6-4407-8c50-cc04cc5e3f3a','2024-12-12 17:32:48','2024-12-12 18:02:48','2024-12-12 17:33:26',0),('c9efcf92-76bc-4de0-bee3-4b5c609d20ff','$2a$10$QtolM1fSGPrSQ.JMNyFEL.luNeDCIX4kK1b0RI4bngDk7evRQkh7C','85b9ee3d-0932-49a3-a873-aa5f15b50a47','2024-11-04 17:15:33','2024-11-04 17:45:33',NULL,1),('ef32f1e2-dab0-4a85-a342-2d84ab508e17','$2a$10$L1y5LLpj6TU31Yi7InI/fOM1cS.1fFCXiiCzNYYcpAa.h8semRDae','81dcd250-9ba6-4407-8c50-cc04cc5e3f3a','2024-12-11 12:36:47','2024-12-11 13:06:47','2024-12-11 12:45:47',1);
/*!40000 ALTER TABLE `provided_refresh_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int unsigned NOT NULL,
  `name` varchar(90) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `value` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `refer_table` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `value_UNIQUE` (`value`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (0,'admin','ADMIN_NO1',NULL),(1,'customer','CUSTOMER','role_customer'),(2,'driver','DRIVER','driver_role');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_customer`
--

DROP TABLE IF EXISTS `role_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_customer` (
  `id` int unsigned NOT NULL,
  `permission_id` int unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `permission_id_UNIQUE` (`permission_id`),
  CONSTRAINT `FK_customer_role_TO_permission` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_customer`
--

LOCK TABLES `role_customer` WRITE;
/*!40000 ALTER TABLE `role_customer` DISABLE KEYS */;
INSERT INTO `role_customer` VALUES (1,0),(2,2),(3,3);
/*!40000 ALTER TABLE `role_customer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-13  0:36:13
