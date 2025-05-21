-- MySQL dump 10.13  Distrib 8.0.39, for Win64 (x86_64)
--
-- Host: localhost    Database: demoinsuranceapp
-- ------------------------------------------------------
-- Server version	8.0.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `auth_group`
--

DROP TABLE IF EXISTS `auth_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auth_group` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_group`
--

LOCK TABLES `auth_group` WRITE;
/*!40000 ALTER TABLE `auth_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `auth_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_group_permissions`
--

DROP TABLE IF EXISTS `auth_group_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auth_group_permissions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `group_id` int NOT NULL,
  `permission_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_group_permissions_group_id_permission_id_0cd325b0_uniq` (`group_id`,`permission_id`),
  KEY `auth_group_permissio_permission_id_84c5c92e_fk_auth_perm` (`permission_id`),
  CONSTRAINT `auth_group_permissio_permission_id_84c5c92e_fk_auth_perm` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`),
  CONSTRAINT `auth_group_permissions_group_id_b120cbf9_fk_auth_group_id` FOREIGN KEY (`group_id`) REFERENCES `auth_group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_group_permissions`
--

LOCK TABLES `auth_group_permissions` WRITE;
/*!40000 ALTER TABLE `auth_group_permissions` DISABLE KEYS */;
/*!40000 ALTER TABLE `auth_group_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_permission`
--

DROP TABLE IF EXISTS `auth_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auth_permission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `content_type_id` int NOT NULL,
  `codename` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_permission_content_type_id_codename_01ab375a_uniq` (`content_type_id`,`codename`),
  CONSTRAINT `auth_permission_content_type_id_2f476e4b_fk_django_co` FOREIGN KEY (`content_type_id`) REFERENCES `django_content_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_permission`
--

LOCK TABLES `auth_permission` WRITE;
/*!40000 ALTER TABLE `auth_permission` DISABLE KEYS */;
INSERT INTO `auth_permission` VALUES (1,'Can add log entry',1,'add_logentry'),(2,'Can change log entry',1,'change_logentry'),(3,'Can delete log entry',1,'delete_logentry'),(4,'Can view log entry',1,'view_logentry'),(5,'Can add permission',2,'add_permission'),(6,'Can change permission',2,'change_permission'),(7,'Can delete permission',2,'delete_permission'),(8,'Can view permission',2,'view_permission'),(9,'Can add group',3,'add_group'),(10,'Can change group',3,'change_group'),(11,'Can delete group',3,'delete_group'),(12,'Can view group',3,'view_group'),(13,'Can add user',4,'add_user'),(14,'Can change user',4,'change_user'),(15,'Can delete user',4,'delete_user'),(16,'Can view user',4,'view_user'),(17,'Can add content type',5,'add_contenttype'),(18,'Can change content type',5,'change_contenttype'),(19,'Can delete content type',5,'delete_contenttype'),(20,'Can view content type',5,'view_contenttype'),(21,'Can add session',6,'add_session'),(22,'Can change session',6,'change_session'),(23,'Can delete session',6,'delete_session'),(24,'Can view session',6,'view_session'),(25,'Can add inkasso',7,'add_inkasso'),(26,'Can change inkasso',7,'change_inkasso'),(27,'Can delete inkasso',7,'delete_inkasso'),(28,'Can view inkasso',7,'view_inkasso'),(29,'Can add vertrag',8,'add_vertrag'),(30,'Can change vertrag',8,'change_vertrag'),(31,'Can delete vertrag',8,'delete_vertrag'),(32,'Can view vertrag',8,'view_vertrag'),(33,'Can add kunde',9,'add_kunde'),(34,'Can change kunde',9,'change_kunde'),(35,'Can delete kunde',9,'delete_kunde'),(36,'Can view kunde',9,'view_kunde');
/*!40000 ALTER TABLE `auth_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_user`
--

DROP TABLE IF EXISTS `auth_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auth_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `password` varchar(128) NOT NULL,
  `last_login` datetime(6) DEFAULT NULL,
  `is_superuser` tinyint(1) NOT NULL,
  `username` varchar(150) NOT NULL,
  `first_name` varchar(150) NOT NULL,
  `last_name` varchar(150) NOT NULL,
  `email` varchar(254) NOT NULL,
  `is_staff` tinyint(1) NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `date_joined` datetime(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_user`
--

LOCK TABLES `auth_user` WRITE;
/*!40000 ALTER TABLE `auth_user` DISABLE KEYS */;
INSERT INTO `auth_user` VALUES (1,'pbkdf2_sha256$870000$yyAro2skA90mavFusJPUwW$hB06vh7rAdJ2k1UNRE4FpgQwdf4GpKszMRx3S/6qfVY=','2025-04-22 12:31:52.439779',1,'ET','','','achouak.hassini@29forward.com',1,1,'2025-02-21 13:17:56.930963'),(2,'pbkdf2_sha256$870000$KPFEUFE8pzUSebABXn0U2j$jRPRnAfxvbK3d9f93pCLKxUPGJGUTsCW+hQbSvlN77U=','2025-05-20 13:50:46.464202',0,'frauheidelberg','Sandra','Heidelberg','test@email.com',0,1,'2025-02-21 13:28:09.000000');
/*!40000 ALTER TABLE `auth_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_user_groups`
--

DROP TABLE IF EXISTS `auth_user_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auth_user_groups` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `group_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_user_groups_user_id_group_id_94350c0c_uniq` (`user_id`,`group_id`),
  KEY `auth_user_groups_group_id_97559544_fk_auth_group_id` (`group_id`),
  CONSTRAINT `auth_user_groups_group_id_97559544_fk_auth_group_id` FOREIGN KEY (`group_id`) REFERENCES `auth_group` (`id`),
  CONSTRAINT `auth_user_groups_user_id_6a12ed8b_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_user_groups`
--

LOCK TABLES `auth_user_groups` WRITE;
/*!40000 ALTER TABLE `auth_user_groups` DISABLE KEYS */;
/*!40000 ALTER TABLE `auth_user_groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_user_user_permissions`
--

DROP TABLE IF EXISTS `auth_user_user_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auth_user_user_permissions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `permission_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_user_user_permissions_user_id_permission_id_14a6b632_uniq` (`user_id`,`permission_id`),
  KEY `auth_user_user_permi_permission_id_1fbb5f2c_fk_auth_perm` (`permission_id`),
  CONSTRAINT `auth_user_user_permi_permission_id_1fbb5f2c_fk_auth_perm` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`),
  CONSTRAINT `auth_user_user_permissions_user_id_a95ead1b_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_user_user_permissions`
--

LOCK TABLES `auth_user_user_permissions` WRITE;
/*!40000 ALTER TABLE `auth_user_user_permissions` DISABLE KEYS */;
/*!40000 ALTER TABLE `auth_user_user_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `django_admin_log`
--

DROP TABLE IF EXISTS `django_admin_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `django_admin_log` (
  `id` int NOT NULL AUTO_INCREMENT,
  `action_time` datetime(6) NOT NULL,
  `object_id` longtext,
  `object_repr` varchar(200) NOT NULL,
  `action_flag` smallint unsigned NOT NULL,
  `change_message` longtext NOT NULL,
  `content_type_id` int DEFAULT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `django_admin_log_content_type_id_c4bce8eb_fk_django_co` (`content_type_id`),
  KEY `django_admin_log_user_id_c564eba6_fk_auth_user_id` (`user_id`),
  CONSTRAINT `django_admin_log_content_type_id_c4bce8eb_fk_django_co` FOREIGN KEY (`content_type_id`) REFERENCES `django_content_type` (`id`),
  CONSTRAINT `django_admin_log_user_id_c564eba6_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`),
  CONSTRAINT `django_admin_log_chk_1` CHECK ((`action_flag` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `django_admin_log`
--

LOCK TABLES `django_admin_log` WRITE;
/*!40000 ALTER TABLE `django_admin_log` DISABLE KEYS */;
INSERT INTO `django_admin_log` VALUES (1,'2025-02-21 13:28:09.869333','2','frauheidelberg',1,'[{\"added\": {}}]',4,1),(2,'2025-02-21 13:31:04.914671','2','frauheidelberg',2,'[{\"changed\": {\"fields\": [\"First name\", \"Last name\", \"Email address\"]}}]',4,1);
/*!40000 ALTER TABLE `django_admin_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `django_content_type`
--

DROP TABLE IF EXISTS `django_content_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `django_content_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `app_label` varchar(100) NOT NULL,
  `model` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `django_content_type_app_label_model_76bd3d3b_uniq` (`app_label`,`model`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `django_content_type`
--

LOCK TABLES `django_content_type` WRITE;
/*!40000 ALTER TABLE `django_content_type` DISABLE KEYS */;
INSERT INTO `django_content_type` VALUES (1,'admin','logentry'),(3,'auth','group'),(2,'auth','permission'),(4,'auth','user'),(5,'contenttypes','contenttype'),(7,'firstdjangoapp','inkasso'),(9,'firstdjangoapp','kunde'),(8,'firstdjangoapp','vertrag'),(6,'sessions','session');
/*!40000 ALTER TABLE `django_content_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `django_migrations`
--

DROP TABLE IF EXISTS `django_migrations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `django_migrations` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `app` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `applied` datetime(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `django_migrations`
--

LOCK TABLES `django_migrations` WRITE;
/*!40000 ALTER TABLE `django_migrations` DISABLE KEYS */;
INSERT INTO `django_migrations` VALUES (1,'contenttypes','0001_initial','2025-02-06 13:21:43.175928'),(2,'auth','0001_initial','2025-02-06 13:21:45.617209'),(3,'admin','0001_initial','2025-02-06 13:21:46.269152'),(4,'admin','0002_logentry_remove_auto_add','2025-02-06 13:21:46.288164'),(5,'admin','0003_logentry_add_action_flag_choices','2025-02-06 13:21:46.302445'),(6,'contenttypes','0002_remove_content_type_name','2025-02-06 13:21:46.533761'),(7,'auth','0002_alter_permission_name_max_length','2025-02-06 13:21:46.762328'),(8,'auth','0003_alter_user_email_max_length','2025-02-06 13:21:46.826019'),(9,'auth','0004_alter_user_username_opts','2025-02-06 13:21:46.843023'),(10,'auth','0005_alter_user_last_login_null','2025-02-06 13:21:47.001822'),(11,'auth','0006_require_contenttypes_0002','2025-02-06 13:21:47.001822'),(12,'auth','0007_alter_validators_add_error_messages','2025-02-06 13:21:47.031702'),(13,'auth','0008_alter_user_username_max_length','2025-02-06 13:21:47.240308'),(14,'auth','0009_alter_user_last_name_max_length','2025-02-06 13:21:47.407058'),(15,'auth','0010_alter_group_name_max_length','2025-02-06 13:21:47.457425'),(16,'auth','0011_update_proxy_permissions','2025-02-06 13:21:47.473160'),(17,'auth','0012_alter_user_first_name_max_length','2025-02-06 13:21:47.685422'),(18,'firstdjangoapp','0001_initial','2025-02-06 13:21:48.192968'),(19,'firstdjangoapp','0002_rename_id_vertrag_v_id_remove_inkasso_inkasso_datum_and_more','2025-02-06 13:21:50.610461'),(20,'firstdjangoapp','0003_kunde_partner_geburtsdatum','2025-02-06 13:21:50.752753'),(21,'firstdjangoapp','0004_alter_kunde_partner_geburtsdatum','2025-02-06 13:21:50.779321'),(22,'firstdjangoapp','0005_inkasso_inkasso_faelligkeitstage_and_more','2025-02-06 13:21:51.160424'),(23,'firstdjangoapp','0006_alter_kunde_partner_steuerid','2025-02-06 13:21:51.174193'),(24,'firstdjangoapp','0007_alter_kunde_partner_steuerid_and_more','2025-02-06 13:21:51.196076'),(25,'firstdjangoapp','0008_alter_vertrag_vertrag_versicherungsssumme','2025-02-06 13:21:51.217325'),(26,'sessions','0001_initial','2025-02-06 13:21:51.345645'),(27,'firstdjangoapp','0009_rename_partner_name_kunde_partner_nachname_and_more','2025-02-10 09:35:23.970345'),(28,'firstdjangoapp','0010_inkasso_inkasso_status_and_more','2025-02-10 12:53:42.515066'),(29,'firstdjangoapp','0011_rename_inkasso_zahlungsmethode_inkasso_inkasso_zahlungsstatus_and_more','2025-02-10 13:18:00.132684'),(30,'firstdjangoapp','0012_kunde_partner_status','2025-02-10 14:05:41.902496'),(31,'firstdjangoapp','0013_alter_kunde_partner_status_and_more','2025-02-10 14:36:19.899371'),(32,'firstdjangoapp','0014_alter_vertrag_vertrag_status','2025-02-10 14:37:32.562381'),(33,'firstdjangoapp','0015_alter_vertrag_vertrag_status','2025-02-10 14:40:03.858803'),(34,'firstdjangoapp','0016_alter_inkasso_inkasso_status','2025-02-10 14:57:29.223405'),(35,'firstdjangoapp','0017_alter_kunde_partner_beruf_and_more','2025-02-11 14:15:52.519659'),(36,'firstdjangoapp','0018_alter_kunde_p_id_alter_kunde_partner_beruf_and_more','2025-02-12 10:06:08.041545'),(37,'firstdjangoapp','0019_alter_kunde_partner_beruf_and_more','2025-02-12 10:29:55.098375'),(38,'firstdjangoapp','0020_alter_kunde_partner_beruf_and_more','2025-02-12 10:43:22.021808'),(39,'firstdjangoapp','0021_alter_inkasso_ink_id_alter_vertrag_v_id_and_more','2025-02-12 10:55:38.855255'),(40,'firstdjangoapp','0022_alter_kunde_partner_email_and_more','2025-02-12 11:56:53.834123'),(41,'firstdjangoapp','0023_alter_kunde_partner_bankverbindung','2025-02-12 12:04:26.961620'),(42,'firstdjangoapp','0024_alter_vertrag_p_id','2025-02-12 15:29:19.291415'),(43,'firstdjangoapp','0025_alter_vertrag_p_id','2025-02-12 16:16:39.502353'),(44,'firstdjangoapp','0026_alter_inkasso_p_id_alter_inkasso_v_id','2025-02-12 17:29:28.932378'),(45,'firstdjangoapp','0027_alter_vertrag_p_id','2025-02-12 18:42:04.095084'),(46,'firstdjangoapp','0028_rename_p_id_vertrag_kundenname','2025-02-13 10:15:02.579409'),(47,'firstdjangoapp','0029_alter_kunde_partner_email','2025-02-13 11:32:22.179977'),(48,'firstdjangoapp','0030_alter_kunde_partner_kundentyp_and_more','2025-02-13 13:07:24.577766'),(49,'firstdjangoapp','0031_alter_kunde_partner_bankverbindung_and_more','2025-02-17 09:21:45.808146'),(50,'firstdjangoapp','0032_rename_partner_adresse_kunde_partner_adresse_and_more','2025-02-24 16:08:06.307892'),(51,'firstdjangoapp','0033_alter_kunde_p_id','2025-02-24 16:16:28.002877'),(52,'firstdjangoapp','0034_alter_kunde_partner_vorname','2025-02-24 16:20:15.334146'),(53,'firstdjangoapp','0035_rename_partner_vorname_kunde_vorname','2025-02-24 16:24:40.227109'),(54,'firstdjangoapp','0036_rename_partner_adresse_kunde_adresse_and_more','2025-02-24 16:29:10.510649'),(55,'firstdjangoapp','0037_rename_status_kunde_status_k_and_more','2025-02-28 12:24:45.579806'),(56,'firstdjangoapp','0038_rename_vertrag_versicherungsart_vertrag_versicherungsart_and_more','2025-02-28 12:39:05.959826'),(57,'firstdjangoapp','0039_rename_tarifgrppe_vertrag_tarifgruppe','2025-02-28 12:39:52.534617'),(58,'firstdjangoapp','0040_alter_kunde_geburtsdatum','2025-02-28 15:44:36.164332'),(59,'firstdjangoapp','0041_alter_kunde_adresse_alter_kunde_nachname_and_more','2025-04-17 11:37:40.202430'),(60,'firstdjangoapp','0042_remove_kunde_adresse_kunde_hausnummer_kunde_plz_and_more','2025-04-20 12:43:46.389303'),(61,'firstdjangoapp','0043_alter_kunde_geburtsdatum','2025-04-20 13:44:48.578060'),(62,'firstdjangoapp','0044_alter_kunde_telefonnummer','2025-04-20 13:46:37.294944'),(63,'firstdjangoapp','0045_alter_kunde_geburtsdatum','2025-04-20 13:51:28.955288'),(64,'firstdjangoapp','0046_alter_kunde_vorname','2025-04-22 12:45:38.068487'),(65,'firstdjangoapp','0047_alter_kunde_telefonnummer','2025-04-22 12:52:48.370086');
/*!40000 ALTER TABLE `django_migrations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `django_session`
--

DROP TABLE IF EXISTS `django_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `django_session` (
  `session_key` varchar(40) NOT NULL,
  `session_data` longtext NOT NULL,
  `expire_date` datetime(6) NOT NULL,
  PRIMARY KEY (`session_key`),
  KEY `django_session_expire_date_a5c62663` (`expire_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `django_session`
--

LOCK TABLES `django_session` WRITE;
/*!40000 ALTER TABLE `django_session` DISABLE KEYS */;
INSERT INTO `django_session` VALUES ('0pbufhnrx59jyo11ira0b9av3oh3bada','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1uCa8C:2BFfUw-kzKSQNAZb0osMV-lrGKGCiF-cAvj8qkoD_4Y','2025-05-21 08:27:56.000441'),('1bli2fevv8gu6ai9aaybrgr00zqlunoc','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1u4zpp:tCV8vmmLX-XO6bAotJ7u8-1WJTqydbT07z5NpulMJWc','2025-04-30 10:17:37.204485'),('2bmuy1cjhnnivc26xpb3dowq5teokybk','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1tmYnx:hkzGIRFYkJdhfwIeUbwZmUsTpnm2fWvQ8MBYDTAScDE','2025-03-10 13:47:29.571448'),('3zupmoampc5q8msgi2zc0lxyu0kccmpb','.eJxVjMsOwiAQRf-FtSFAeY1L934DYQaQqoGktCvjv2uTLnR7zzn3xULc1hq2kZcwJ3Zmkp1-N4z0yG0H6R7brXPqbV1m5LvCDzr4taf8vBzu30GNo37rQoYyFvTgE5A2GWKZUEtUziSbvEDtSTqQwpocjQInrJKTsk4JgELs_QH-czeH:1u7Cn2:6LRM3g5tRZ5ktc7q1K2sOOKTlhf-3p4cOf2GeRIQdO0','2025-05-06 12:31:52.452297'),('4pp3gcuj5g0v6hmjine9agyzr3o6cv6w','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1uCcy6:4SxzgAjvr8qhsPqltdjsMaZy6zGEUBKk8dF41shCQZ0','2025-05-21 11:29:42.359407'),('4zqjh1d3d2m5lvao7nfqso6jz7djdftk','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1tmYdb:oPS5HHlSjXBiQHmWQJ1mOQ8lTxY_xkuUX398JtTiRf4','2025-03-10 13:36:47.062415'),('599htoqg6g20r7d9xegx6dyo9de2lnik','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1u2tlA:fO15DXkF3Glat7NcL2QJCDcxOt7qP2lCtcbSRq4NmF8','2025-04-24 15:24:08.579820'),('5f5xvfjqym319ndxz06mgq0lbotc08qw','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1tmZQF:4XlXT6Y2SEiyfcWWv17FRBypTwVRXekd0wo12oFRygI','2025-03-10 14:27:03.915678'),('78dd6evyeftver7ohwrqmkm8e5fje6fw','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1uHHyR:WfrHYyY6sM_H1QtcMH1woS-145o810Co1p8UQsPgehg','2025-06-03 08:05:19.891646'),('7vhm5bry2oh7w3pbg8z6ffrgaj3f4m72','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1u3EDK:gIlX1cnVW_mWpDisBDUFI4aiSsjfFIZFfBAs6IcC930','2025-04-25 13:14:34.811113'),('8fsuw4l5ho0gmvnvd8jl19dyz1qp1pr9','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1u3EG6:P2vttrqhDfi3WDfSCpKlJNTYVwk7V-5qZKr2b5u3VvU','2025-04-25 13:17:26.177784'),('as1qt03mgxqhx5ipo8mgb32ni7oygpzn','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1uCaSa:1IDWkDMDszXduHGFny-ScQPQS5gq2XM79vT06HrCR-g','2025-05-21 08:49:00.384279'),('ba8u4vong3s82nk9rxw3grkcvvaqnpet','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1uHNMk:Xe6xik1yu4yLrL_x3gnGI1ZkSwp9HQAI9_whqzT9Gd0','2025-06-03 13:50:46.473210'),('buik7srkbgqw7pgukdxzf963a0zgv8qr','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1u3FCo:u9fpEbx3P1hKzb4bvbjT1-0YVzBr8e23YOOSbixnVaU','2025-04-25 14:18:06.554077'),('df5xj1jy2wmzgk7h0lilpo7qdmt3wuij','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1uGuk9:idy3jKhbkxdjbhG8pzK6343-vshV4WK1CduPh7S8V7k','2025-06-02 07:17:01.081543'),('e8k4lnhzwzkwm4eo64em2t4xbp0xtpop','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1uGuqT:KUSgM7IcF60kdKT1A6M2YwmqOjaencVgGUMB-wd-3Gk','2025-06-02 07:23:33.221554'),('eu5a1xxtircg7ixvsvd6cvlovh1v11t2','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1uGu4q:l6crhsFatm98IH4_7bkKt1DpeIbjPEymehoiV0vgh0w','2025-06-02 06:34:20.389494'),('ev3atoyfldvsxn6q6byqee6b9ry67kft','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1u3EIp:8-V0ZoMyXVkzK6A1AE9x0mLcZzCt9t4Peuf5tOVRHh0','2025-04-25 13:20:15.189903'),('ffp9yj7rr3bmzfhqtxa6ed9uxu6j7mfd','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1uBYM6:PUzWCgPgj1huq4fgbkCQwG5MXH_5LdCIoRe_8uI7jZI','2025-05-18 12:22:02.359516'),('hja8cs8758mmgtbl8028d06pn3l8jlm8','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1uBYYD:R5jKJ9XnVgBsoIbTuXz5AtmgGCymb-AuYLee-JyPkkk','2025-05-18 12:34:33.351944'),('i8n1oem2aze5grx9uiks36648d4t5zyw','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1uCd2B:6SqdxDaEZ-TCdPALalQeLVNUPgs_2nUULYihRTGoXyo','2025-05-21 11:33:55.589478'),('ik0ccx41oc26d75ghkugyehfodzrac5z','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1uGv12:d9wGE_qXnCoEJ8uAzkRTlJtJfI3iSVKbxAgGJQDaGmE','2025-06-02 07:34:28.708225'),('kmxw1dnxdimv482n91rwyzrog81n60dk','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1u504I:Ps6AC3UPy1q4O_44ZDpSL6QtY6KQ4bqY2m7iqBnL8sM','2025-04-30 10:32:34.077261'),('kmzjyrwdyh713f2iupmehnbnhwpg7saf','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1u3EMx:vFHUiHNcbOguDlN8QSR5Rz8IfjlmnMm-PTknqxVzFYM','2025-04-25 13:24:31.814192'),('kzpsu5jie8n2tmgmdnr22164u5wjasha','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1u3Elq:LFQE3dRS9396eU9koy_ccKbJgFcPmVg6Quq7U7MzVf0','2025-04-25 13:50:14.373204'),('lecj8suk8fs48bsnhboyqqf2lahmj9oz','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1u2stn:MwrHWl9VL6tG0go5N4iZ1uTJmT1E3mYUiUSboP5KlEY','2025-04-24 14:28:59.579704'),('lg1b15z7e7745j08k20bx35xizbaok3f','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1u3EOt:UYPgS0j4rTzolQWGCpKZL81F2TE3ZQ_kgWpEFSU0ie4','2025-04-25 13:26:31.058078'),('lhbuc2ze7wfw08sk7uqxu0iab2qn07kh','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1u4zv6:ruLrWCeRZKMRKEzsQluDzBCV3ubQ6Qm6MXqeh5_tIu0','2025-04-30 10:23:04.411852'),('ltjy5xerzf91mlfv3cyb97i4mjd7uh01','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1uCcwE:njLZO26dUfC4x5E9T5LnvyD1KHvWbH8TW71oxUXMXE4','2025-05-21 11:27:46.698839'),('mxhlpjawijz4ze45chlru3c40gg8863r','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1tnxrd:QxL00yrSr-n4TxsmVBAx6uIStJSXRwlMPGf6kwu__Jw','2025-03-14 10:45:05.554697'),('nfd641srkewa9h1ypz31eg8jv71si95o','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1u2sOK:FF4kTAwU_ob--mjIHfZe0UIEgkQyVp9AAO_qEuXv9fA','2025-04-24 13:56:28.944301'),('oywzn4ut6r75cm8dwrrsj2hbx1h29t4s','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1uBYFd:HkDBHLHlFqRSMlypZbyr8K6Rpn074KrZAYiOu7xufAM','2025-05-18 12:15:21.591475'),('pr6jlmegc2f9z9hb5cbbgwp83e4me7wi','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1uCaPy:rcZyRYiwtc3s3qOoU7IPvQ45I7neA2cLzrjR93zC_FA','2025-05-21 08:46:18.920604'),('q0ecxk6iqkhi9lwft562hw3fspedw1ba','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1u2sxK:6TNiRQRHyEyowFZc-WUouyr4GXcqvY4hKOGva0k3rfU','2025-04-24 14:32:38.658437'),('qpizyechadymlt8oz7icefae3eunecz0','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1u3ENe:Bi7Tn144IJQ7q9vD3eZ_SlCEprWG4IyIubpwIMjlJ_M','2025-04-25 13:25:14.796352'),('qytepwa4mwt9mtxcmpdriid22r2gjpav','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1uCcFD:rKGO2xtxFu3jTN3mOmmOuG_r0HxcYwZuiZ36rocAKoo','2025-05-21 10:43:19.236512'),('r7nq9ycm8vsz7v1w79ecxton9j6hch78','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1u2srn:GkeQxB__U_Tfrgy2SyYLelFDYd_tCPkdghyfNrECIo0','2025-04-24 14:26:55.027679'),('svz3rndv4opgt57xbz3y9qty7hnd9q27','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1u4zL3:bEfkPlfoP5JRu0dDSoXa0gPeyXh5B7iCchfloZXNLkg','2025-04-30 09:45:49.812956'),('swhmxbf669v7mkj2td9mule7wdepqk6t','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1uGutu:hjNb-eqy08WWxHJbr6xvEf2CfdW986AUAzjsWV1PFJk','2025-06-02 07:27:06.180290'),('t057r13fsq303d0efvb4sdp220ctaifo','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1uCaWl:Sawnp38RvUNa6PYKX444IzXlc7yxtjUDONfUqVoub5o','2025-05-21 08:53:19.022741'),('tithygv1377fsadtv6onqcqgmlqpr0qb','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1uGuwR:InrkR2opqenuHprR6V8icPGd5Fmt22VSXnBvuW3sX9I','2025-06-02 07:29:43.507678'),('tqo9h1g3mf7j1uasojkwcj7u9lgnjbng','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1u3EMH:S9XhnI7GHnDGIjK74s2tMe5uickUK_rGzcITboCnHTU','2025-04-25 13:23:49.029528'),('useo5itvkg2fwkkc324qjxple4dwkqou','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1uGuum:FVSgFxF-JB0V1bc_Us8KRqYXeiqRCeDOBTh7EaG6AWk','2025-06-02 07:28:00.314791'),('v1jh1ocux8t7phjmgarit41z7ggnwlpj','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1u2syO:t3vjaO6Gog7APPvDeBRuw0jGvOrICXfocT6DBoSgn8o','2025-04-24 14:33:44.743376'),('v9wg9ck6pcp95ue2auea4os3rj7l48rc','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1u3GEy:q2QVlYjsWp1fX6XFGy5qLPATrfDuPubZudpErWhhnNA','2025-04-25 15:24:24.091951'),('vii0slow71enpq2homzl7e406zfagr12','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1u2sqv:WZwU1FxvYEVFU5yNApJiJQL-bthOfSQnUyy18PI2Mf0','2025-04-24 14:26:01.391217'),('wnze1hmwmwprm76vocyiyt067jpwqd5g','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1uBYUM:Bq-DDIK19wPi5jJWrYaJWwk5pK4E7stbG71nC2un0no','2025-05-18 12:30:34.690309'),('x9j1u04zs7swxgctmd8697elrf7dsij4','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1uGuSf:9Sfn-MT8YBBW9yrU79GUUxHVXQOZ0U0zwUSaizj2qW8','2025-06-02 06:58:57.283386'),('xcpi4kzbvr7h937qh38xqvlhg239znxk','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1u3EHM:VVDethpbQ5uSo0dEmcbz2xwS9njpiDu9go1YF0T9LyI','2025-04-25 13:18:44.553124'),('xwa728e3ekyz441pjlsa3up4daa997dm','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1tx3OO:nB1dPDd7H9k-A5ZvQAmuzzDCejtOa6s1y8GoGXK1svk','2025-04-08 12:28:28.623922'),('xyinsw3if6euhtcpv8f43k4cqrcqgz73','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1u3G7F:oUwdhhzgd-Z0ThiOX1pkUDtIhCARLft87qwbmEW4fFE','2025-04-25 15:16:25.528261'),('z7r0niyar634pt2693ogn6trusaqaifa','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1uBYPp:YNrrImP_Fwdo9gFMH-USTwiciJb2fA0RLWtWhYTvAv8','2025-05-18 12:25:53.365524'),('zc38jwzzv44bfki635ih5i362e0txlit','.eJxVjMsOwiAURP-FtSHQenm4dO83EG4HbNVAUtqV8d9tky50OXPOzFuEuC5jWFuawwRxEZ04_XYch2cqO8AjlnuVQy3LPLHcFXnQJm8V6XU93L-DMbZxWxMULPncG3TeekMKWvuczxpkHA1b9kqTh2ECMztQ6p0mFRm21xCfL8nON4E:1u4zrB:oeuUCwVqEI0sqUCoWmhdU372GbZhFNtvDPLOnjelKKc','2025-04-30 10:19:01.410812');
/*!40000 ALTER TABLE `django_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `firstdjangoapp_inkasso`
--

DROP TABLE IF EXISTS `firstdjangoapp_inkasso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `firstdjangoapp_inkasso` (
  `inkasso_betrag` double NOT NULL,
  `ink_id` int NOT NULL AUTO_INCREMENT,
  `inkasso_buchungsreferenz` varchar(20) NOT NULL,
  `inkasso_mahnstufe` int NOT NULL,
  `p_id_id` int NOT NULL,
  `v_id_id` int NOT NULL,
  `inkasso_faelligkeitstage` int NOT NULL,
  `inkasso_zahlungseingang` date NOT NULL,
  `inkasso_zahlungsziel` date NOT NULL,
  `inkasso_status` varchar(20) NOT NULL,
  `inkasso_zahlungsstatus` varchar(20) NOT NULL,
  PRIMARY KEY (`ink_id`),
  KEY `firstdjangoapp_inkas_p_id_id_a4f654d0_fk_firstdjan` (`p_id_id`),
  KEY `firstdjangoapp_inkas_v_id_id_4c849491_fk_firstdjan` (`v_id_id`),
  CONSTRAINT `firstdjangoapp_inkas_p_id_id_a4f654d0_fk_firstdjan` FOREIGN KEY (`p_id_id`) REFERENCES `firstdjangoapp_kunde` (`p_id`),
  CONSTRAINT `firstdjangoapp_inkas_v_id_id_4c849491_fk_firstdjan` FOREIGN KEY (`v_id_id`) REFERENCES `firstdjangoapp_vertrag` (`v_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `firstdjangoapp_inkasso`
--

LOCK TABLES `firstdjangoapp_inkasso` WRITE;
/*!40000 ALTER TABLE `firstdjangoapp_inkasso` DISABLE KEYS */;
INSERT INTO `firstdjangoapp_inkasso` VALUES (55,1,'00124',1,1,1,16,'2025-02-28','2025-02-28','undefiniert','bezahlt');
/*!40000 ALTER TABLE `firstdjangoapp_inkasso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `firstdjangoapp_kunde`
--

DROP TABLE IF EXISTS `firstdjangoapp_kunde`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `firstdjangoapp_kunde` (
  `Nachname` varchar(256) NOT NULL,
  `p_id` int NOT NULL AUTO_INCREMENT,
  `Bankverbindung` varchar(20) NOT NULL,
  `Beruf` varchar(20) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `Nationalität` varchar(10) NOT NULL,
  `SteuerID` varchar(20) NOT NULL,
  `Telefonnummer` varchar(128) NOT NULL,
  `Geburtsdatum` date NOT NULL,
  `Vorname` varchar(128) NOT NULL,
  `Kundentyp` varchar(20) NOT NULL,
  `Rolle` varchar(20) NOT NULL,
  `Zahlungsmethode` varchar(20) NOT NULL,
  `Beziehungsstatus` varchar(20) NOT NULL,
  `Raucher` varchar(20) NOT NULL,
  `Status_K` varchar(20) NOT NULL,
  `Hausnummer` int NOT NULL,
  `PLZ` int NOT NULL,
  `Stadt` varchar(256) NOT NULL,
  `Strasse` varchar(256) NOT NULL,
  PRIMARY KEY (`p_id`),
  UNIQUE KEY `partner_email` (`Email`),
  UNIQUE KEY `partner_steuerID` (`SteuerID`),
  UNIQUE KEY `partner_telefonnummer` (`Telefonnummer`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `firstdjangoapp_kunde`
--

LOCK TABLES `firstdjangoapp_kunde` WRITE;
/*!40000 ALTER TABLE `firstdjangoapp_kunde` DISABLE KEYS */;
INSERT INTO `firstdjangoapp_kunde` VALUES ('Mueller',1,'ER22341160','Malerrr','hans@berlin.com','DE','6651008007','0981678959','1922-09-08','Hans','privatkunde','versicherungsnehmer','sepa','ledig','ja','aktiv',0,0,'00','00'),('Bison',2,'TZ55588903','singer','mariah@opera.it','IT','1123011123','0039665405','1980-11-08','Mariah','privatkunde','versicherungsnehmer','paypal','ledig','nein','aktiv',0,0,'00','00'),('Callas',3,'TT77654321','Polizist','mike@email.com','US','1123456783','3451234567','1926-09-08','Mike','privatkunde','versicherungsnehmer','sepa','ledig','ja','inaktiv',0,0,'Wallas','00'),('cipolla',4,'ZZ66687543','engineer','mario@rr.it','it','1123456780','0033765866','1926-09-08','mario','begünstigter','versicherungsnehmer','sepa','ledig','nein','inaktiv',0,0,'00','00'),('cipolla',5,'TT88765678','L','mario@email.com','Ungarn','3345109866','0044568765','1988-09-08','mario','privatkunde','versicherungsnehmer','sepa','ledig','ja','inaktiv',0,0,'00','00'),('Moeped',6,'DE66540909','maler','tricento@gmail.com','deutsch','2210985647','0157765909','2077-04-11','Antonio','privatkunde','versicherungsnehmer','sepa','ledig','ja','inaktiv',0,0,'00','00');
/*!40000 ALTER TABLE `firstdjangoapp_kunde` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `firstdjangoapp_vertrag`
--

DROP TABLE IF EXISTS `firstdjangoapp_vertrag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `firstdjangoapp_vertrag` (
  `v_id` int NOT NULL AUTO_INCREMENT,
  `kundenname_id` int NOT NULL,
  `Beitragshoehe` double NOT NULL,
  `Vertragnummer` varchar(20) NOT NULL,
  `Risikoadresse` varchar(30) NOT NULL,
  `Selbstbeteiligung` double NOT NULL,
  `VersichererID` varchar(10) NOT NULL,
  `Versicherungsssumme` double NOT NULL,
  `Beginndatum` date NOT NULL,
  `Endedatum` date NOT NULL,
  `Zahlungsintervall` varchar(20) NOT NULL,
  `Status_V` varchar(20) NOT NULL,
  `Versicherungsart` varchar(20) NOT NULL,
  `Tarifgruppe` varchar(20) NOT NULL,
  PRIMARY KEY (`v_id`),
  UNIQUE KEY `vertrag_nummer` (`Vertragnummer`),
  KEY `firstdjangoapp_vertr_kundenname_id_bfce8cdd_fk_firstdjan` (`kundenname_id`),
  CONSTRAINT `firstdjangoapp_vertr_kundenname_id_bfce8cdd_fk_firstdjan` FOREIGN KEY (`kundenname_id`) REFERENCES `firstdjangoapp_kunde` (`p_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `firstdjangoapp_vertrag`
--

LOCK TABLES `firstdjangoapp_vertrag` WRITE;
/*!40000 ALTER TABLE `firstdjangoapp_vertrag` DISABLE KEYS */;
INSERT INTO `firstdjangoapp_vertrag` VALUES (1,1,38876,'1','berlin',55,'5546tr',667.9,'2024-02-12','2040-02-12','monatlich','aktiv','haftpflicht','haftpflicht_single'),(2,1,345,'2','Erlangen 98856',8,'99i8',5547,'2020-10-13','2027-02-13','monatlich','aktiv','haftpflicht','haftpflicht_single');
/*!40000 ALTER TABLE `firstdjangoapp_vertrag` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-20 17:19:18
