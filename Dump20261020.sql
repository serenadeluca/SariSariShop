-- MySQL dump 10.13  Distrib 8.0.46, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: sarisarishop
-- ------------------------------------------------------
-- Server version	8.0.46

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
-- Table structure for table `amministratore`
--

DROP TABLE IF EXISTS `amministratore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `amministratore` (
  `id_amministratore` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password_hash` varchar(255) NOT NULL,
  `livello_accesso` int DEFAULT '1',
  `ultimo_login` timestamp NULL DEFAULT NULL,
  `notifica_nuovi_ordini` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id_amministratore`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `amministratore`
--

LOCK TABLES `amministratore` WRITE;
/*!40000 ALTER TABLE `amministratore` DISABLE KEYS */;
INSERT INTO `amministratore` VALUES (1,'admin','admin@sarisarishop.it','240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9',2,'2026-07-20 09:55:50',1),(2,'Serena','serenadeluca51@gmail.com','5ce2cb8b6514b4b875a0782fcf2f87d2e5575e19eee9cc41c8d761f5826c4142',1,NULL,1);
/*!40000 ALTER TABLE `amministratore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categorie`
--

DROP TABLE IF EXISTS `categorie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorie` (
  `id_categoria` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `descrizione` text,
  `url_nome_prodotto` varchar(100) NOT NULL,
  PRIMARY KEY (`id_categoria`),
  UNIQUE KEY `nome` (`nome`),
  UNIQUE KEY `url_nome_prodotto` (`url_nome_prodotto`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorie`
--

LOCK TABLES `categorie` WRITE;
/*!40000 ALTER TABLE `categorie` DISABLE KEYS */;
INSERT INTO `categorie` VALUES (1,'Bevande','Prodotti liquidi e bibite','bevande'),(2,'Congelato','Prodotti surgelati','congelato'),(3,'Conserve','Cibi in scatola e conserve','conserve'),(4,'Cura Personale','Prodotti per la cura del corpo','cura-personale'),(5,'Dolci e Colazione','Dolci, biscotti e prodotti per la colazione','dolci-colazione'),(6,'Latte e Caffè','Prodotti a base di latte e caffè','latte-caffe'),(7,'Legumi e Farine','Legumi secchi e farine','legumi-farine'),(8,'Non Food','Prodotti non alimentari','non-food'),(9,'Noodles e Riso','Noodles e riso filippino','noodles-riso'),(10,'Salse e Condimenti','Salse e condimenti filippini','salse-condimenti'),(11,'Snack','Snack e merendine','snack');
/*!40000 ALTER TABLE `categorie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dettaglio_ordine`
--

DROP TABLE IF EXISTS `dettaglio_ordine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dettaglio_ordine` (
  `id_dettaglio` int NOT NULL AUTO_INCREMENT,
  `id_ordine` int DEFAULT NULL,
  `id_prodotto` int DEFAULT NULL,
  `quantita` int NOT NULL,
  `prezzo_unitario` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id_dettaglio`),
  KEY `id_ordine` (`id_ordine`),
  KEY `id_prodotto` (`id_prodotto`),
  CONSTRAINT `dettaglio_ordine_ibfk_1` FOREIGN KEY (`id_ordine`) REFERENCES `ordine` (`id_ordine`) ON DELETE CASCADE,
  CONSTRAINT `dettaglio_ordine_ibfk_2` FOREIGN KEY (`id_prodotto`) REFERENCES `prodotto` (`id_prodotto`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dettaglio_ordine`
--

LOCK TABLES `dettaglio_ordine` WRITE;
/*!40000 ALTER TABLE `dettaglio_ordine` DISABLE KEYS */;
INSERT INTO `dettaglio_ordine` VALUES (11,14,21,7,5.50),(12,15,29,3,0.50),(13,15,23,6,1.60),(14,15,28,4,2.00),(15,16,38,6,0.50),(16,17,26,4,4.00),(17,17,30,2,0.50);
/*!40000 ALTER TABLE `dettaglio_ordine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `immagine_prodotto`
--

DROP TABLE IF EXISTS `immagine_prodotto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `immagine_prodotto` (
  `id_immagine` int NOT NULL AUTO_INCREMENT,
  `id_prodotto` int DEFAULT NULL,
  `url_immagine` text NOT NULL,
  PRIMARY KEY (`id_immagine`),
  KEY `id_prodotto` (`id_prodotto`),
  CONSTRAINT `immagine_prodotto_ibfk_1` FOREIGN KEY (`id_prodotto`) REFERENCES `prodotto` (`id_prodotto`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `immagine_prodotto`
--

LOCK TABLES `immagine_prodotto` WRITE;
/*!40000 ALTER TABLE `immagine_prodotto` DISABLE KEYS */;
INSERT INTO `immagine_prodotto` VALUES (1,7,'img/prodotti/lucky-me-pancit-canton.jpg'),(2,18,'img/prodotti/skyflakes.jpg'),(3,19,'img/prodotti/datu-puti.jpg'),(5,21,'img/prodotti/ube-halaya.jpg'),(6,22,'img/prodotti/silver-swan.jpg'),(7,23,'img/prodotti/alibaba.jpg');
/*!40000 ALTER TABLE `immagine_prodotto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `indirizzo_utente`
--

DROP TABLE IF EXISTS `indirizzo_utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `indirizzo_utente` (
  `id_indirizzo` int NOT NULL AUTO_INCREMENT,
  `id_utente` int DEFAULT NULL,
  `indirizzo_riga1` varchar(255) NOT NULL,
  `indirizzo_riga2` varchar(255) DEFAULT NULL,
  `citta` varchar(100) NOT NULL,
  `codice_postale` varchar(20) NOT NULL,
  `nazione` varchar(100) NOT NULL,
  PRIMARY KEY (`id_indirizzo`),
  KEY `id_utente` (`id_utente`),
  CONSTRAINT `indirizzo_utente_ibfk_1` FOREIGN KEY (`id_utente`) REFERENCES `utente` (`id_utente`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `indirizzo_utente`
--

LOCK TABLES `indirizzo_utente` WRITE;
/*!40000 ALTER TABLE `indirizzo_utente` DISABLE KEYS */;
INSERT INTO `indirizzo_utente` VALUES (6,15,'via maddaloni 33','','Caserta','9737e2','Italia'),(7,16,'via delle arti e dei mestieri 33','','Caserta','9737e2','Italia'),(8,18,'via delle arti e dei mestieri 33','','Caserta','9737e2','Italia');
/*!40000 ALTER TABLE `indirizzo_utente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordine`
--

DROP TABLE IF EXISTS `ordine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordine` (
  `id_ordine` int NOT NULL AUTO_INCREMENT,
  `id_utente` int DEFAULT NULL,
  `id_indirizzo` int DEFAULT NULL,
  `data_ordine` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `totale_ordine` decimal(10,2) NOT NULL,
  `stato_ordine` varchar(50) DEFAULT 'In elaborazione',
  `metodo_pagamento` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_ordine`),
  KEY `id_indirizzo` (`id_indirizzo`),
  KEY `fk_ordine_utente` (`id_utente`),
  CONSTRAINT `fk_ordine_utente` FOREIGN KEY (`id_utente`) REFERENCES `utente` (`id_utente`) ON DELETE CASCADE,
  CONSTRAINT `ordine_ibfk_2` FOREIGN KEY (`id_indirizzo`) REFERENCES `indirizzo_utente` (`id_indirizzo`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordine`
--

LOCK TABLES `ordine` WRITE;
/*!40000 ALTER TABLE `ordine` DISABLE KEYS */;
INSERT INTO `ordine` VALUES (14,15,6,'2026-07-23 15:37:02',38.50,'In elaborazione','Carta'),(15,16,7,'2026-07-24 10:27:45',19.10,'In elaborazione','Carta'),(16,16,7,'2026-07-24 15:13:09',3.00,'annullato','Carta'),(17,18,8,'2026-07-28 09:53:38',17.00,'In elaborazione','Carta');
/*!40000 ALTER TABLE `ordine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prodotto`
--

DROP TABLE IF EXISTS `prodotto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prodotto` (
  `id_prodotto` int NOT NULL AUTO_INCREMENT,
  `id_categoria` int DEFAULT NULL,
  `nome` varchar(255) NOT NULL,
  `url_nome_prodotto` varchar(255) NOT NULL,
  `descrizione` text,
  `prezzo` decimal(10,2) NOT NULL,
  `quantita_da_tenere` varchar(50) DEFAULT NULL,
  `pubblico` tinyint(1) DEFAULT '0',
  `data_creazione` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `data_aggiornamento` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `immagine` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_prodotto`),
  UNIQUE KEY `url_nome_prodotto` (`url_nome_prodotto`),
  KEY `id_categoria` (`id_categoria`),
  CONSTRAINT `prodotto_ibfk_1` FOREIGN KEY (`id_categoria`) REFERENCES `categorie` (`id_categoria`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prodotto`
--

LOCK TABLES `prodotto` WRITE;
/*!40000 ALTER TABLE `prodotto` DISABLE KEYS */;
INSERT INTO `prodotto` VALUES (7,9,'Lucky Me Pancit Canton','lucky-me-pancit-canton','Noodles filippini',1.17,'100',1,'2026-07-20 14:52:44','2026-07-24 08:48:23','luckyme.jpg'),(18,11,'SkyFlakes Crackers Original','skyflakes-crackers-original','Crackers filippini croccanti',2.50,'100',1,'2026-07-20 14:52:44','2026-07-21 08:51:21','skyflakes.jpg'),(19,10,'Datu Puti Soy Sauce','datu-puti-soy-sauce','Salsa di soia filippina',3.23,'100',1,'2026-07-20 14:52:44','2026-07-24 15:53:04','datuputi.jpg'),(21,NULL,'Ube Halaya Jam','ube-halaya-jam','Crema dolce di Ube',5.32,'100',1,'2026-07-20 14:52:44','2026-07-24 08:00:17','ubejam.jpg'),(22,NULL,'Silver Swan Soy Sauce','silver-swan-soy-sauce','Salsa di soia Silver Swan',3.88,'100',1,'2026-07-20 14:52:44','2026-07-24 08:48:23','silverswan.jpg'),(23,NULL,'AlibabÃ  coconut milk for cooking','alibaba-coconut-milk-for-cooking','Latte di cocco da cucina',1.60,'10 pz',1,'2026-07-24 07:46:16','2026-07-24 09:31:33','alibaba.jpg'),(24,8,'Aceite de Alcamforado IPI (50 ml)','aceite-de-alcamforado-ipi-50-ml','Il classico rimedio filippino a base di canfora. Un olio da massaggio per uso esterno, ideale per fornire rapido sollievo da dolori muscolari, distorsioni e lievi infiammazioni.',3.50,'20',1,'2026-07-24 09:37:40','2026-07-24 09:50:42','aceitedealcamphorado.jpg'),(25,3,'Buenas Nata de Coco in Syrup - Green (340 g)','buenas-nata-de-coco-in-syrup-green-340-g','Deliziosi cubetti di gelatina di cocco (Nata de Coco) conservati in sciroppo dolce. La vivace variante verde dona colore e una consistenza irresistibilmente gommosa ai tuoi dessert filippini, dal Buko Pandan al classico Halo-Halo.',4.00,'33pz',1,'2026-07-24 09:42:59','2026-07-24 09:50:42','coconutgelgreen.jpg'),(26,3,'Buenas Nata de Coco in Syrup - red (340 g)','buenas-nata-de-coco-in-syrup-red-340-g','Deliziosi cubetti di gelatina di cocco (Nata de Coco) conservati in sciroppo dolce. Questa vivace variante rossa è perfetta per aggiungere un tocco di colore acceso e una consistenza irresistibilmente gommosa ai tuoi dessert filippini preferiti.',4.00,'33pz',1,'2026-07-24 09:44:19','2026-07-24 09:50:42','coconutgelred.jpg'),(27,4,'Cream Silk Ultimate Reborn - Standout Straight (Tri-Oleo Conditioner)','cream-silk-ultimate-reborn-standout-straight-tri-oleo-conditioner','La popolarissima variante rosa \"Standout Straight\" di Cream Silk. Progettata per domare l\'effetto crespo e regalare capelli fino a 10 volte più lisci, incredibilmente morbidi e luminosi.',4.50,'22',1,'2026-07-24 09:46:31','2026-07-24 09:50:42','CreamsilkPink.jpg'),(28,4,'Eskinol Classic White Facial Deep Cleanser','eskinol-classic-white-facial-deep-cleanser','Il leggendario detergente viso profondo Eskinol nella sua versione classica bianca/rosa. Rimuove lo sporco in profonditÃ , combatte l\'eccesso di sebo e aiuta a uniformare l\'incarnato.',2.00,'20',1,'2026-07-24 09:48:31','2026-07-24 15:22:26','eskinolpink.jpg'),(29,5,'Fudgee Barr - Vanilla Cream-Filled Vanilla Cake Bar (39g)','fudgee-barr-vanilla-cream-filled-vanilla-cake-bar-39g','Una soffice tortina alla vaniglia con un ricco e cremoso ripieno alla vaniglia. Uno snack dolce, delicato e irresistibile, ideale per ogni momento della giornata.',0.50,'50pz',1,'2026-07-24 09:54:29','2026-07-24 09:54:29','fudgeebarvanilla.jpg'),(30,5,'Fudgee Barr - Milk Custard Cream-Filled Vanilla Cake Bar (39g)','fudgee-barr-milk-custard-cream-filled-vanilla-cake-bar-39g','Una soffice tortina alla vaniglia arricchita da un ricco e cremoso ripieno di crema custarda al latte. Uno snack dolce, vellutato e irresistibile per una pausa golosa.',0.50,'50pz',1,'2026-07-24 09:56:06','2026-07-24 09:56:06','fudgeebarmilk.jpg'),(31,7,'Polar Bear Glutinous Rice Flour - Malagkit Powder','polar-bear-glutinous-rice-flour-malagkit-powder','Polar Bear Glutinous Rice Flour - Malagkit Powder',2.20,'100pz',1,'2026-07-24 09:57:40','2026-07-24 09:57:40','gloutinousflour.jpg'),(32,7,'Regent Golden Sweet Corn Snack (60g)','regent-golden-sweet-corn-snack-60g','I croccanti e deliziosi snack al gusto di mais dolce della Regent. Uno sfizio leggero e saporito, famoso per la sua forma tondeggiante e l\'inconfondibile dolcezza salata.',1.20,'50pz',1,'2026-07-24 09:59:12','2026-07-24 09:59:12','goldensweetcorn.jpg'),(33,11,'Good Servant Bakery - Special Hopia Mongo','good-servant-bakery-special-hopia-mongo','Il tradizionale pasticcino filippino Hopia con ripieno dolce di fagioli mungo, fresco da forno e senza conservanti. Un classico irresistibile della tradizione di Kawit, Cavite.',5.00,'200pz',1,'2026-07-24 10:00:51','2026-07-24 10:00:51','hopiamongo.jpg'),(34,6,'Krem-Top Coffee Creamer (400g)','krem-top-coffee-creamer-400g','Il celebre creamer in polvere per caffÃ¨ Krem-Top. Con la sua formula ricca e vellutata, rende ogni tazza di caffÃ¨ piÃ¹ cremosa e deliziosa.',3.50,'200',1,'2026-07-24 10:01:53','2026-07-24 10:02:08','kremtop.jpg'),(35,10,'Mama Sita\'s Achuete - Annatto Powder (10g)','mama-sitas-achuete-annatto-powder-10g','Polvere naturale di achuete (annatto) a marchio Mama Sita\'s. Perfetta per dare il caratteristico colore dorato-rossastro e un sapore unico a stufati, salse e piatti tradizionali filippini.',0.50,'200',1,'2026-07-24 10:03:40','2026-07-24 10:03:40','mamasitasachuete.jpg'),(37,1,'Mogu Mogu Passion Fruit Flavored Drink with Nata de Coco (320ml)','mogu-mogu-passion-fruit-flavored-drink-with-nata-de-coco-320ml','La celebre bevanda al gusto di frutto della passione a marchio Mogu Mogu, arricchita con deliziosi cubetti masticabili di nata de coco.',1.80,'221',1,'2026-07-24 10:32:53','2026-07-24 10:32:53','mogumogupassion.jpg'),(38,1,'Tang Mango Instant Drink Mix (20g)','tang-mango-instant-drink-mix-20g','Il celebre preparato solubile per bevanda al gusto di mango Tang. Arricchito con 100% vitamina C ed estratti di vera frutta.',0.50,'300',1,'2026-07-24 10:34:17','2026-07-24 10:34:17','tangmango.jpg'),(39,6,'Alaska Evaporada Evaporated Creamer (360 ml)','alaska-evaporada-evaporated-creamer-360-ml','La crema evaporata numero uno nelle Filippine! L\'ingrediente segreto e immancabile per donare una consistenza ricca, cremosa e un sapore inconfondibile al tuo Halo-Halo e a tante altre ricette.',2.50,'150 pz',1,'2026-07-24 10:37:57','2026-07-24 10:37:57','alaskaevaporada.jpg'),(40,4,'Johnson\'s Baby Cologne - Regular (125 mL)','johnsons-baby-cologne-regular-125-ml','L\'iconica colonia per bambini Johnson\'s nella classica profumazione Regular. Dona una sensazione di freschezza pulita e duratura, perfetta per tutta la famiglia.',4.00,'200',1,'2026-07-24 16:07:54','2026-07-24 16:07:54','jonsoncologneblu.jpg');
/*!40000 ALTER TABLE `prodotto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recensione`
--

DROP TABLE IF EXISTS `recensione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recensione` (
  `id_recensione` int NOT NULL AUTO_INCREMENT,
  `id_prodotto` int NOT NULL,
  `id_utente` int NOT NULL,
  `titolo` varchar(100) DEFAULT NULL,
  `commento` text,
  `data_pubblicazione` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `acquisto_verificato` tinyint(1) DEFAULT '0',
  `voto` int DEFAULT NULL,
  PRIMARY KEY (`id_recensione`),
  UNIQUE KEY `unica_recensione_utente` (`id_utente`,`id_prodotto`),
  KEY `id_prodotto` (`id_prodotto`),
  CONSTRAINT `recensione_ibfk_1` FOREIGN KEY (`id_prodotto`) REFERENCES `prodotto` (`id_prodotto`) ON DELETE CASCADE,
  CONSTRAINT `recensione_ibfk_2` FOREIGN KEY (`id_utente`) REFERENCES `utente` (`id_utente`) ON DELETE CASCADE,
  CONSTRAINT `check_voto` CHECK ((`voto` between 1 and 5))
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recensione`
--

LOCK TABLES `recensione` WRITE;
/*!40000 ALTER TABLE `recensione` DISABLE KEYS */;
INSERT INTO `recensione` VALUES (12,39,16,'bello','bello','2026-07-24 13:19:10',1,5),(14,38,16,'wow','wow','2026-07-28 08:42:07',1,2);
/*!40000 ALTER TABLE `recensione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utente`
--

DROP TABLE IF EXISTS `utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utente` (
  `id_utente` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password_hash` varchar(255) NOT NULL,
  `nome` varchar(50) DEFAULT NULL,
  `cognome` varchar(50) DEFAULT NULL,
  `numero` varchar(20) DEFAULT NULL,
  `data_creazione` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `attivo` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id_utente`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente`
--

LOCK TABLES `utente` WRITE;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` VALUES (15,'Mariorossi','mariorossi@gmail.com','e9b8371766c90498559a289d2197edc629da74e01a3e093ce5e8690e60dd44bd','Mario','Rossi','1223124444','2026-07-23 13:35:29',1),(16,'Serena','serenadeluca51@gmail.com','5ce2cb8b6514b4b875a0782fcf2f87d2e5575e19eee9cc41c8d761f5826c4142','Serena','De luca','3274432352','2026-07-24 08:43:25',0),(17,'Gelyn','gelyn@gmail.com','5606aa82fffe0ebfdb1e973bbccf2cd1063e7c75f8c553794b47c24ecc93345f','gelyn','fajardo','321654987','2026-07-24 15:31:57',1),(18,'marco','mfalciano@gmail.com','f25b605dff7a973c97780ab550758afc8af5eb122e66f03fb8ad88be28f0e093','marco','falciano','3209875433','2026-07-28 09:52:00',1);
/*!40000 ALTER TABLE `utente` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-10-20 15:16:10
