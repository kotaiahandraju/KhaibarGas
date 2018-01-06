/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.5.27 : Database - khaibar
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`khaibar` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `khaibar`;

/*Table structure for table `accessoriesmaster` */

DROP TABLE IF EXISTS `accessoriesmaster`;

CREATE TABLE `accessoriesmaster` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_time` timestamp NULL DEFAULT NULL,
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `typeofaccessory` varchar(255) DEFAULT NULL,
  `suppliername` varchar(255) DEFAULT NULL,
  `madein` varchar(255) DEFAULT NULL,
  `lponumber` varchar(255) DEFAULT NULL,
  `accessoriesstatus` varchar(255) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `accessoriesmaster` */

/*Table structure for table `addgas` */

DROP TABLE IF EXISTS `addgas`;

CREATE TABLE `addgas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_time` timestamp NULL DEFAULT NULL,
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `fillingStationId` varchar(255) DEFAULT NULL,
  `gasInKgs` varchar(255) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `addgas` */

insert  into `addgas`(`id`,`created_time`,`updated_time`,`fillingStationId`,`gasInKgs`) values (1,'2017-12-29 15:09:11','2017-12-29 18:54:38','34','400'),(2,'2017-12-29 15:10:27','2017-12-29 18:54:42','57','400');

/*Table structure for table `companymaster` */

DROP TABLE IF EXISTS `companymaster`;

CREATE TABLE `companymaster` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_time` timestamp NULL DEFAULT NULL,
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `companycode` varchar(255) DEFAULT NULL,
  `companyname` varchar(255) DEFAULT NULL,
  `contactpersonname` varchar(255) DEFAULT NULL,
  `contactpersonmobile` varchar(255) DEFAULT NULL,
  `emailid` varchar(255) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `typeofcompany` varchar(255) DEFAULT NULL,
  `companyAddress` varchar(1000) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `companymaster` */

insert  into `companymaster`(`id`,`created_time`,`updated_time`,`companycode`,`companyname`,`contactpersonname`,`contactpersonmobile`,`emailid`,`remarks`,`typeofcompany`,`companyAddress`,`status`) values (1,'2017-12-27 15:16:19','2017-12-28 12:39:40','9045859','Khaibar gas','kotaiah','9010410484','andraju.kotaiah@gmail.com','','Owner','','1'),(2,'2017-12-27 15:16:57','2017-12-28 12:46:43','9000990','HP','ravi','90104104102','RAJU@GMAIL.COM','','Competitor','','1');

/*Table structure for table `customercylinders` */

DROP TABLE IF EXISTS `customercylinders`;

CREATE TABLE `customercylinders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_time` timestamp NULL DEFAULT NULL,
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `customerId` varchar(255) DEFAULT NULL,
  `accessoriesId` varchar(255) DEFAULT NULL,
  `cylinderId` varchar(255) DEFAULT NULL,
  `quantity` varchar(155) DEFAULT NULL,
  `price` varchar(255) DEFAULT NULL,
  `discount` varchar(255) DEFAULT NULL,
  `grandTotal` varchar(255) DEFAULT NULL,
  `vat` varchar(255) DEFAULT NULL,
  `cylinderDeliverTruck` varchar(255) DEFAULT NULL,
  `cylinderReturnTruck` varchar(255) DEFAULT NULL,
  `cylinderreturn` varchar(255) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `customercylinders` */

insert  into `customercylinders`(`id`,`created_time`,`updated_time`,`customerId`,`accessoriesId`,`cylinderId`,`quantity`,`price`,`discount`,`grandTotal`,`vat`,`cylinderDeliverTruck`,`cylinderReturnTruck`,`cylinderreturn`) values (1,'2017-12-28 11:01:25','2017-12-28 13:03:46','1',NULL,'5','1','100','0.00','100.00','5','1','','1'),(2,'2017-12-28 13:03:33','2017-12-28 13:07:50','1',NULL,'1','1','100','0.00','100.00','5','1','','1'),(3,'2017-12-28 13:03:42','2017-12-28 13:03:42','1',NULL,NULL,'1','45','0.00','45.00','5','1',NULL,NULL),(4,'2017-12-28 13:07:47','2017-12-28 13:07:47','1','5',NULL,'1','45','0.00','45.00','5','1',NULL,NULL),(5,'2017-12-28 13:12:34','2017-12-28 14:01:27','1',NULL,'6','1','100','0.00','100.00','5','1','','1'),(6,'2017-12-28 13:12:38','2017-12-28 13:12:38','1','5',NULL,'1','45','0.00','45.00','5','1',NULL,NULL),(7,'2017-12-28 13:18:36','2017-12-28 13:18:36','1','5',NULL,'1','45','0.00','45.00','5','1',NULL,NULL);

/*Table structure for table `customermaster` */

DROP TABLE IF EXISTS `customermaster`;

CREATE TABLE `customermaster` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_time` timestamp NULL DEFAULT NULL,
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `customerid` varchar(255) DEFAULT NULL,
  `customername` varchar(255) DEFAULT NULL,
  `customeraddress` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `landline` varchar(255) DEFAULT NULL,
  `authorizedperson` varchar(255) DEFAULT NULL,
  `contactperson` varchar(255) DEFAULT NULL,
  `customertype` varchar(255) DEFAULT NULL,
  `cylinderId` varchar(255) DEFAULT NULL,
  `netAmount` varchar(255) DEFAULT '0',
  `payedAmount` varchar(255) DEFAULT '0',
  `dueAmount` varchar(255) DEFAULT '0',
  `previousDueAmount` varchar(255) DEFAULT NULL,
  `invoiceId` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `customermaster` */

insert  into `customermaster`(`id`,`created_time`,`updated_time`,`customerid`,`customername`,`customeraddress`,`mobile`,`landline`,`authorizedperson`,`contactperson`,`customertype`,`cylinderId`,`netAmount`,`payedAmount`,`dueAmount`,`previousDueAmount`,`invoiceId`,`status`) values (1,'2017-12-27 15:22:41','2017-12-28 13:58:55','COM10001','KOTAIAH','VIJAYAWADA','9999999999','456456','koti','KOTI','COMMERCIAL','6','152.25','166','152','0',NULL,'1'),(2,'2017-12-27 15:23:43','2017-12-28 16:07:47','DOM10002','Balu','vijayawada','787878787878787878','565656','Balu','ravi','DOMESTIC',NULL,'0','0','0',NULL,NULL,'1'),(3,'2017-12-27 15:24:10','2017-12-28 16:07:36','IND10003','BALU','JKK','990030303030','90809808','ravi','raju','INDUSTIAL',NULL,'0','0','0',NULL,NULL,'1');

/*Table structure for table `cylindermaster` */

DROP TABLE IF EXISTS `cylindermaster`;

CREATE TABLE `cylindermaster` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_time` timestamp NULL DEFAULT NULL,
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `cylinderid` varchar(255) DEFAULT NULL,
  `store` varchar(255) DEFAULT NULL,
  `size` varchar(255) DEFAULT NULL,
  `capacity` varchar(255) DEFAULT NULL,
  `cylinderstatus` varchar(255) DEFAULT NULL,
  `customerid` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `lponumber` varchar(255) DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL,
  `madein` varchar(255) DEFAULT NULL,
  `expirydate` timestamp NULL DEFAULT NULL,
  `ownercompany` varchar(255) DEFAULT NULL,
  `fillingstationId` varchar(255) DEFAULT NULL,
  `truckId` varchar(255) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `cylindermaster` */

insert  into `cylindermaster`(`id`,`created_time`,`updated_time`,`cylinderid`,`store`,`size`,`capacity`,`cylinderstatus`,`customerid`,`location`,`lponumber`,`color`,`madein`,`expirydate`,`ownercompany`,`fillingstationId`,`truckId`,`remarks`,`status`) values (1,'2017-12-27 15:18:12','2017-12-28 13:46:44','S000000001','1','1','11','7',NULL,'Vijayawada','LPO1000001','orange','kotaiah','2020-12-09 00:00:00','1','1','1','','1'),(2,'2017-12-27 15:18:42','2017-12-28 13:06:48','M000000002','1','2','22','5',NULL,'Vijayawada','LPO1000001','red','kotaiah','2017-12-28 00:00:00','1','1',NULL,'','1'),(3,'2017-12-27 15:18:55','2017-12-28 13:06:50','M000000003','1','2','22','5',NULL,'Vijayawada','LPO1000001','red','kotaiah','2017-12-30 00:00:00','1','1',NULL,'','1'),(4,'2017-12-27 15:19:10','2017-12-28 13:06:47','L000000004','1','3','44','5',NULL,'Vijayawada','LPO1000001','red','kotaiah','2017-12-29 00:00:00','1',NULL,NULL,'','1'),(5,'2017-12-27 15:19:28','2017-12-28 13:03:47','S000000005','1','1','11','7','1','Vijayawada','LPO1000001','red','kotaiah','2017-12-29 00:00:00','1','1','1','','1'),(6,'2017-12-28 11:04:58','2017-12-28 14:01:33','S000000006','100','1','11','8',NULL,'Vijayawada','LPO1000001','red','kotaiah','2020-12-09 00:00:00','2','1','1','','1'),(7,'2017-12-28 13:47:32','2017-12-28 13:47:32','S000000007','1','1','11','1',NULL,'Vijayawada','LPO1000001','red','kotaiah','2020-12-09 00:00:00','1',NULL,NULL,'','1');

/*Table structure for table `cylinderstatus` */

DROP TABLE IF EXISTS `cylinderstatus`;

CREATE TABLE `cylinderstatus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `status` enum('0','1') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `cylinderstatus` */

insert  into `cylinderstatus`(`id`,`name`,`status`) values (1,'Empty','1'),(2,'FillingStation','1'),(3,'Filled','1'),(4,'QualityCheck','1'),(5,'Truck','1'),(6,'Delivered','1'),(7,'Returned','1'),(8,'MissedCylinder','1');

/*Table structure for table `cylindertransaction` */

DROP TABLE IF EXISTS `cylindertransaction`;

CREATE TABLE `cylindertransaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_time` timestamp NULL DEFAULT NULL,
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `cylindetId` varchar(255) DEFAULT NULL,
  `cylinderStatus` varchar(255) DEFAULT NULL,
  `customerId` varchar(255) DEFAULT NULL,
  `fillingStation` varchar(255) DEFAULT NULL,
  `truckId` varchar(255) DEFAULT NULL,
  `storename` varchar(255) DEFAULT NULL,
  `createdBy` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `cylindertransaction` */

insert  into `cylindertransaction`(`id`,`created_time`,`updated_time`,`cylindetId`,`cylinderStatus`,`customerId`,`fillingStation`,`truckId`,`storename`,`createdBy`) values (1,'2017-12-27 15:35:46','2017-12-27 15:35:46','1','2',NULL,'1',NULL,NULL,'1'),(2,'2017-12-27 15:35:46','2017-12-27 15:35:46','5','2',NULL,'1',NULL,NULL,'1'),(3,'2017-01-27 15:36:02','2017-12-30 10:37:53','1','3',NULL,'1',NULL,NULL,'1'),(4,'2017-12-27 15:36:02','2017-12-27 15:36:02','5','3',NULL,'1',NULL,NULL,'1'),(5,'2017-12-27 15:36:16','2017-12-27 15:36:16','1','4',NULL,'1',NULL,NULL,'1'),(6,'2017-12-27 15:36:16','2017-12-27 15:36:16','5','4',NULL,'1',NULL,NULL,'1'),(7,'2017-12-27 15:36:32','2017-12-27 15:36:32','1','5',NULL,NULL,'1',NULL,'1'),(8,'2017-12-27 15:36:32','2017-12-27 15:36:32','5','5',NULL,NULL,'1',NULL,'1'),(9,'2017-12-27 15:53:49','2017-12-27 15:53:49','1','1',NULL,NULL,NULL,'1','1'),(10,'2017-12-28 10:52:43','2017-12-28 10:52:43','1','2',NULL,'1',NULL,NULL,'1'),(11,'2017-12-28 13:01:00','2017-12-28 13:01:00','2','2',NULL,'1',NULL,NULL,'1'),(12,'2017-12-28 13:01:00','2017-12-28 13:01:00','3','2',NULL,'1',NULL,NULL,'1'),(13,'2017-12-28 13:01:20','2017-12-28 13:01:20','1','3',NULL,'1',NULL,NULL,'1'),(14,'2017-12-28 13:01:42','2017-12-28 13:01:42','1','4',NULL,'1',NULL,NULL,'1'),(15,'2017-12-28 13:02:14','2017-12-28 13:02:14','1','5',NULL,NULL,'1',NULL,'1');

/*Table structure for table `cylindertypes` */

DROP TABLE IF EXISTS `cylindertypes`;

CREATE TABLE `cylindertypes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_time` timestamp NULL DEFAULT NULL,
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `name` varchar(255) DEFAULT NULL,
  `capacity` varchar(255) DEFAULT NULL,
  `amount` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `cylindertypes` */

insert  into `cylindertypes`(`id`,`created_time`,`updated_time`,`name`,`capacity`,`amount`) values (1,'2017-11-27 15:07:06','2017-12-01 14:10:43','Small - 11KG','11',NULL),(2,'2017-11-27 15:07:06','2017-12-01 12:31:13','Medium - 22KG','22',NULL),(3,'2017-11-27 15:07:06','2017-12-01 12:31:23','Large - 44KG','44',NULL);

/*Table structure for table `expensetracker` */

DROP TABLE IF EXISTS `expensetracker`;

CREATE TABLE `expensetracker` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amount` varchar(200) DEFAULT NULL,
  `accountHead` varchar(255) DEFAULT NULL,
  `dateOfExpense` varchar(255) DEFAULT NULL,
  `itemDescription` varchar(255) DEFAULT NULL,
  `paymentType` varchar(255) DEFAULT NULL,
  `paymentRemarks` varchar(255) DEFAULT NULL,
  `created_time` timestamp NULL DEFAULT NULL,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `expensetracker` */

/*Table structure for table `fillingstationmaster` */

DROP TABLE IF EXISTS `fillingstationmaster`;

CREATE TABLE `fillingstationmaster` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_time` timestamp NULL DEFAULT NULL,
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `gasavailability` varchar(255) DEFAULT '0',
  `numberoffillingmachines` varchar(255) DEFAULT NULL,
  `quantity` varchar(255) DEFAULT NULL,
  `gascapacity` varchar(255) DEFAULT NULL,
  `usedGas` varchar(255) DEFAULT '0',
  `closingBalanceGas` varchar(255) DEFAULT '0',
  `unitpoint` varchar(255) DEFAULT NULL,
  `stationname` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `fillingstationmaster` */

insert  into `fillingstationmaster`(`id`,`created_time`,`updated_time`,`gasavailability`,`numberoffillingmachines`,`quantity`,`gascapacity`,`usedGas`,`closingBalanceGas`,`unitpoint`,`stationname`,`status`) values (1,'2017-12-27 15:25:08','2017-12-29 15:09:11','2700','5','554','5000000','33','2667','STATION ONE','station one','1'),(2,'2017-12-27 15:25:38','2017-12-28 13:40:13','50000','9','9999','50000','0','50000','station two','station two','1'),(3,'2017-12-29 15:10:04','2017-12-29 15:11:36','700','4','88','500','0','700','station three','station three','1');

/*Table structure for table `items` */

DROP TABLE IF EXISTS `items`;

CREATE TABLE `items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createdtime` timestamp NULL DEFAULT NULL,
  `updatedtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(955) DEFAULT NULL,
  `price` varchar(255) DEFAULT NULL,
  `discount` varchar(255) DEFAULT NULL,
  `itemType` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `items` */

insert  into `items`(`id`,`createdtime`,`updatedtime`,`name`,`description`,`price`,`discount`,`itemType`,`status`) values (1,'2017-12-08 21:29:55','2017-12-08 21:29:55','Small - 11KG','Small Cylinder','200',NULL,'Cylinder','1'),(2,'2017-12-08 21:30:19','2017-12-08 21:30:19','Medium - 22KG','Medium  Cylinder','300',NULL,'Cylinder','1'),(3,'2017-12-08 21:30:40','2017-12-08 21:30:40','Large - 44KG','Large Cylinder','400',NULL,'Cylinder','1'),(4,'2017-12-08 21:31:03','2017-12-08 21:31:03','Truck1','truck','555555',NULL,'Truck','1'),(5,'2017-12-21 13:02:57','2017-12-21 13:02:57','Gas pipe ','gas pipe ','345',NULL,'Accessories','1');

/*Table structure for table `khaibar_users` */

DROP TABLE IF EXISTS `khaibar_users`;

CREATE TABLE `khaibar_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_time` timestamp NULL DEFAULT NULL,
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `userName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `roleId` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `khaibar_users` */

insert  into `khaibar_users`(`id`,`created_time`,`updated_time`,`userName`,`password`,`roleId`,`status`) values (1,NULL,'2017-11-24 17:18:09','admin','admin','1','1'),(2,NULL,'2017-12-28 17:31:53','user','user','2','1');

/*Table structure for table `lpoitems` */

DROP TABLE IF EXISTS `lpoitems`;

CREATE TABLE `lpoitems` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createdtime` timestamp NULL DEFAULT NULL,
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `itemid` varchar(255) DEFAULT NULL,
  `lponumber` varchar(255) DEFAULT NULL,
  `quantity` varchar(255) DEFAULT NULL,
  `price` varchar(255) DEFAULT NULL,
  `totalprice` varchar(255) DEFAULT NULL,
  `discount` varchar(255) DEFAULT NULL,
  `grandtotal` varchar(255) DEFAULT NULL,
  `manufacturingdate` varchar(255) DEFAULT NULL,
  `expirydate` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

/*Data for the table `lpoitems` */

insert  into `lpoitems`(`id`,`createdtime`,`updated_time`,`itemid`,`lponumber`,`quantity`,`price`,`totalprice`,`discount`,`grandtotal`,`manufacturingdate`,`expirydate`) values (12,'2017-12-27 15:20:37','2017-12-27 15:20:37','1','LPO1000001','3','1000','3000.00','0.00','3000.00','05-Dec-2019','09-Dec-2020'),(13,'2017-12-27 15:20:37','2017-12-27 15:20:37','2','LPO1000001','444','2000','888000.00','0.00','888000.00','16-Dec-2020','01-Dec-2021'),(14,'2017-12-27 15:20:37','2017-12-27 15:20:37','3','LPO1000001','22','500','11000.00','0.00','11000.00','29-Dec-2021','30-Dec-2021'),(15,'2017-12-27 15:20:37','2017-12-27 15:20:37','4','LPO1000001','44','44','1936.00','0.00','1936.00','','');

/*Table structure for table `lpomaster` */

DROP TABLE IF EXISTS `lpomaster`;

CREATE TABLE `lpomaster` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_time` timestamp NULL DEFAULT NULL,
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lponumber` varchar(255) DEFAULT NULL,
  `item` varchar(255) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `suppliername` varchar(255) DEFAULT NULL,
  `supplieraddress` varchar(255) DEFAULT NULL,
  `suppliercontactno` varchar(255) DEFAULT NULL,
  `supplieremail` varchar(255) DEFAULT NULL,
  `amount` varchar(255) DEFAULT NULL,
  `paidamount` varchar(255) DEFAULT NULL,
  `dueamount` varchar(255) DEFAULT NULL,
  `expiryDate` timestamp NULL DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `lpomaster` */

insert  into `lpomaster`(`id`,`created_time`,`updated_time`,`lponumber`,`item`,`remarks`,`suppliername`,`supplieraddress`,`suppliercontactno`,`supplieremail`,`amount`,`paidamount`,`dueamount`,`expiryDate`,`status`) values (1,'2017-12-27 15:12:17','2017-12-27 15:20:36','LPO1000001','','','kotaiah','vijayawada','9010410484','andraju.kotaiah@gmail.com','903936','902000','1936',NULL,'1');

/*Table structure for table `months1` */

DROP TABLE IF EXISTS `months1`;

CREATE TABLE `months1` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `months1` */

insert  into `months1`(`id`,`name`) values (1,'January'),(2,'February'),(3,'March'),(4,'April'),(5,'May'),(6,'June'),(7,'July'),(8,'August'),(9,'September'),(10,'October'),(11,'November'),(12,'December');

/*Table structure for table `roles` */

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_time` timestamp NULL DEFAULT NULL,
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `name` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `roles` */

/*Table structure for table `staffmaster` */

DROP TABLE IF EXISTS `staffmaster`;

CREATE TABLE `staffmaster` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_time` timestamp NULL DEFAULT NULL,
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `staffcode` varchar(255) DEFAULT NULL,
  `staffno` varchar(255) DEFAULT NULL,
  `designation` varchar(255) DEFAULT NULL,
  `nationality` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `customertype` varchar(255) DEFAULT NULL,
  `documents` varchar(255) DEFAULT NULL,
  `active` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staffmaster` */

/*Table structure for table `storesmaster` */

DROP TABLE IF EXISTS `storesmaster`;

CREATE TABLE `storesmaster` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_time` timestamp NULL DEFAULT NULL,
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `storeid` varchar(255) DEFAULT NULL,
  `storename` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `storesmaster` */

insert  into `storesmaster`(`id`,`created_time`,`updated_time`,`storeid`,`storename`,`location`,`status`) values (1,'2017-12-27 15:17:14','2017-12-27 15:17:14','Store000001','STORE1','Vijayawada','1'),(2,'2017-12-27 15:17:27','2017-12-27 15:17:27','Store000002','STORE2','BANGALORE','1');

/*Table structure for table `tariffmaster` */

DROP TABLE IF EXISTS `tariffmaster`;

CREATE TABLE `tariffmaster` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_time` timestamp NULL DEFAULT NULL,
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `itemId` varchar(255) DEFAULT NULL,
  `assetcode` varchar(255) DEFAULT NULL,
  `assetdescription` varchar(255) DEFAULT NULL,
  `rate` varchar(255) DEFAULT NULL,
  `alloweddiscount` varchar(255) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `tariffmaster` */

insert  into `tariffmaster`(`id`,`created_time`,`updated_time`,`itemId`,`assetcode`,`assetdescription`,`rate`,`alloweddiscount`,`remarks`,`status`) values (1,'2017-12-27 15:37:21','2017-12-28 16:08:32','1','5678','Small item','100','6','','1'),(2,'2017-12-28 11:00:23','2017-12-28 16:08:46','5','4546','gas pipe','45','4','','1');

/*Table structure for table `trucksmaster` */

DROP TABLE IF EXISTS `trucksmaster`;

CREATE TABLE `trucksmaster` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_time` timestamp NULL DEFAULT NULL,
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `trucknumber` varchar(255) DEFAULT NULL,
  `registrationexpirydate` timestamp NULL DEFAULT NULL,
  `civildefensecardexpirydate` timestamp NULL DEFAULT NULL,
  `typeOfService` varchar(255) DEFAULT NULL,
  `servicedue` timestamp NULL DEFAULT NULL,
  `make` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `capacityoftruck` varchar(255) DEFAULT NULL,
  `lponumber` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `trucksmaster` */

insert  into `trucksmaster`(`id`,`created_time`,`updated_time`,`trucknumber`,`registrationexpirydate`,`civildefensecardexpirydate`,`typeOfService`,`servicedue`,`make`,`description`,`capacityoftruck`,`lponumber`,`status`) values (1,'2017-12-27 15:21:07','2017-12-27 16:28:04','truck1','2017-12-29 00:00:00','2017-12-30 00:00:00','Maintenance','2017-12-30 00:00:00','bbbbb','','5656','LPO1000001','1'),(2,'2017-12-27 15:21:28','2017-12-29 17:01:57','truck2','2017-12-28 00:00:00','2017-12-30 00:00:00','Agency Repairs','2017-12-30 00:00:00','aaaa','SDFSFD','645646','LPO1000001','0');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
