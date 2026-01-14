/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.5.5-10.1.21-MariaDB : Database - student_assign_courses_system
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`student_assign_courses_system` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `student_assign_courses_system`;

/*Table structure for table `contact_messages` */

DROP TABLE IF EXISTS `contact_messages`;

CREATE TABLE `contact_messages` (
  `contactId` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`contactId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `contact_messages` */

insert  into `contact_messages`(`contactId`,`email`,`firstName`,`lastName`,`message`,`phone`) values (1,'mukhtiar@example.com','Mukhtiar','Ali','I want details about course registration','03021135133'),(2,'ahmedali1234@gmail.com','Ahmed','Ali','I want details about course registration','03011135133'),(3,'mukhtiarali567880@gmail.com','Mukhtiar','Ali','I want details about course registration','03091135133'),(4,'kamranali12345@gmail.com','Kamran','Ali','To improve your quality of education.','03341135133');

/*Table structure for table `courses` */

DROP TABLE IF EXISTS `courses`;

CREATE TABLE `courses` (
  `courseId` int(11) NOT NULL AUTO_INCREMENT,
  `courseCode` varchar(255) DEFAULT NULL,
  `courseName` varchar(255) DEFAULT NULL,
  `creditHours` int(11) NOT NULL,
  `semester` int(11) NOT NULL,
  `instructor_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`courseId`),
  KEY `FK1kswo6qqebbdy2kq0kx6udof7` (`instructor_id`),
  CONSTRAINT `FK1kswo6qqebbdy2kq0kx6udof7` FOREIGN KEY (`instructor_id`) REFERENCES `instructors` (`instId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `courses` */

insert  into `courses`(`courseId`,`courseCode`,`courseName`,`creditHours`,`semester`,`instructor_id`) values (1,'java 301','java',3,3,2),(2,'DS 301','Data Structures',3,3,1),(3,'web 301','Web Technology',3,3,1),(4,'fp301','Fundamental Programming',3,1,2),(5,'DBMS(301)','Data Base Management System',3,3,NULL),(6,'OS(301)','Operating System',2,2,NULL),(7,'math(201)','Mathematics',3,1,NULL),(8,'eng(301)','English',3,1,NULL),(9,'ISl(201)','Islamayat ',3,4,5),(10,'DS(201)','Discreate',3,3,NULL);

/*Table structure for table `enrollments` */

DROP TABLE IF EXISTS `enrollments`;

CREATE TABLE `enrollments` (
  `enrollmentId` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  `enrollment_date` date DEFAULT NULL,
  `instId` int(11) DEFAULT NULL,
  PRIMARY KEY (`enrollmentId`),
  KEY `FKho8mcicp4196ebpltdn9wl6co` (`course_id`),
  KEY `FK8kf1u1857xgo56xbfmnif2c51` (`student_id`),
  KEY `FK1svdmtijcg3m3o9yedfqa2xd8` (`instId`),
  CONSTRAINT `FK1svdmtijcg3m3o9yedfqa2xd8` FOREIGN KEY (`instId`) REFERENCES `instructors` (`instId`),
  CONSTRAINT `FK8kf1u1857xgo56xbfmnif2c51` FOREIGN KEY (`student_id`) REFERENCES `students` (`stdId`),
  CONSTRAINT `FKho8mcicp4196ebpltdn9wl6co` FOREIGN KEY (`course_id`) REFERENCES `courses` (`courseId`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;

/*Data for the table `enrollments` */

insert  into `enrollments`(`enrollmentId`,`course_id`,`student_id`,`enrollment_date`,`instId`) values (17,2,2,'2026-01-07',1),(22,2,1,'2026-01-11',2),(23,2,6,'2026-01-13',4);

/*Table structure for table `instructors` */

DROP TABLE IF EXISTS `instructors`;

CREATE TABLE `instructors` (
  `instId` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `cnic` varchar(255) DEFAULT NULL,
  `contactNumber` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fatherName` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`instId`),
  KEY `FKds2m3jgxj98sd5mr1qw23ecjp` (`user_id`),
  CONSTRAINT `FKds2m3jgxj98sd5mr1qw23ecjp` FOREIGN KEY (`user_id`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `instructors` */

insert  into `instructors`(`instId`,`address`,`cnic`,`contactNumber`,`department`,`email`,`fatherName`,`firstName`,`gender`,`lastName`,`surname`,`user_id`) values (1,'Islamabad','41306-60574703-1','03091135133','Information Technology','hujajali12345@gmail.com','Taj Muhammad','Hujaj','Male','Ali','Qambarni',4),(2,'Chapal Sun city Phase I house no 12 karachi.','41306-60574703-0','03081135133','Computer Science','irfanali12345@gmail.com',' Muhammad Nawaz','Irfan','Male','Ali','Kandaro',5),(4,'Block 456, City','98765-4321098-7','03009876543','Computer Science','jane@example.com','David Smith','Jane','Female','Smith','JS',11),(5,'Islamabad','41302-60574703-1','03021135133','Information Technology','amjadmirjatt1122@gmail.com','Rasool Bux ','Amjad','Male','Mir','Jatt',12),(6,'Islamabad.','4130660574704','03391135133','Information Technology','saeedali880@gmail.com','Muhammad Ali','Saeed','Male','Ali','Magsi',13);

/*Table structure for table `students` */

DROP TABLE IF EXISTS `students`;

CREATE TABLE `students` (
  `stdId` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `cnic` varchar(255) DEFAULT NULL,
  `contactNumber` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fatherName` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `program` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`stdId`),
  KEY `FKdt1cjx5ve5bdabmuuf3ibrwaq` (`user_id`),
  CONSTRAINT `FKdt1cjx5ve5bdabmuuf3ibrwaq` FOREIGN KEY (`user_id`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `students` */

insert  into `students`(`stdId`,`address`,`cnic`,`contactNumber`,`email`,`fatherName`,`firstName`,`gender`,`lastName`,`program`,`surname`,`user_id`) values (1,'house no 120 model colony karachi.','41306-60574703-1','03091135133','nadeemali56780@gmail.com','Basharat','Tanveer','Male','Ali','BSSE','Punjabi',2),(2,'Sultanabad karachi.','41306-6057470-2','03021135133','mukhtiarali567880@gmail.com','Arz Muhammad','Mukhtiar','Male','Ali','BSIT(hons)','Chandio',3),(6,'Street 123, City','12345-6789012-3','03001234567','john@example.com','Michael Doe','John','Male','Doe','BSCS','JD',10);

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `userPassword` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

/*Data for the table `users` */

insert  into `users`(`userId`,`role`,`userName`,`userPassword`) values (1,'ADMIN','adminController123@gmail.com','conroller@1122334455'),(2,'STUDENT','arslanali56780@gmail.com','ali56780'),(3,'STUDENT','mukhtiarali567880@gmail.com','ali56780'),(4,'INSTRUCTOR','hujajali12345@gmail.com','hujajali12345'),(5,'INSTRUCTOR','irfanali12345@gmail.com','ali12345'),(6,'INSTRUCTOR','ameenali1122@gmail.com','ali1122'),(7,'STUDENT','mushahidali567880@gmail.com','ali5670'),(8,'STUDENT','mushahidali567880@gmail.com','56780'),(9,'STUDENT','mushahidali567880@gmail.com','66780'),(10,'STUDENT','john_doe','password123'),(11,'INSTRUCTOR','jane_smith','pass456'),(12,'INSTRUCTOR','amjadmirjatt1122@gmail.com','amjad1122'),(13,'INSTRUCTOR','saeedali880@gmail.com','saeed880');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
