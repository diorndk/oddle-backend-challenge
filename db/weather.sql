/*Table structure for table `city` */

DROP TABLE IF EXISTS `city`;

CREATE TABLE `city` (
  `city_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `city_name` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  PRIMARY KEY (`city_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

/*Table structure for table `city_weather` */

DROP TABLE IF EXISTS `city_weather`;

CREATE TABLE `city_weather` (
  `city_weather_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `city_id` bigint(20) DEFAULT NULL,
  `humidity` int(11) NOT NULL,
  `pressure` int(11) NOT NULL,
  `temp_max` double NOT NULL,
  `temp_min` double NOT NULL,
  `temperature` double NOT NULL,
  `base` varchar(255) DEFAULT NULL,
  `visibility` int(11) NOT NULL,
  `weather_date` datetime NOT NULL,
  PRIMARY KEY (`city_weather_id`),
  KEY `FKsv5d86cf6rj9rrkfm9gdy0ejh` (`city_id`),
  CONSTRAINT `FKsv5d86cf6rj9rrkfm9gdy0ejh` FOREIGN KEY (`city_id`) REFERENCES `city` (`city_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

/*Table structure for table `city_weather_mapping` */

DROP TABLE IF EXISTS `city_weather_mapping`;

CREATE TABLE `city_weather_mapping` (
  `city_weather_id` bigint(20) NOT NULL,
  `weather_id` bigint(20) NOT NULL,
  KEY `FKccck1e6q1fg8sncpj2x3oyk3m` (`weather_id`),
  KEY `FKjbapapncxlkri7oxdtxwt4su2` (`city_weather_id`),
  CONSTRAINT `FKccck1e6q1fg8sncpj2x3oyk3m` FOREIGN KEY (`weather_id`) REFERENCES `weather` (`weather_id`),
  CONSTRAINT `FKjbapapncxlkri7oxdtxwt4su2` FOREIGN KEY (`city_weather_id`) REFERENCES `city_weather` (`city_weather_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `weather` */

DROP TABLE IF EXISTS `weather`;

CREATE TABLE `weather` (
  `weather_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `description` text DEFAULT NULL,
  `main` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`weather_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
