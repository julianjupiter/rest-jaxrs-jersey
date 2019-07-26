--
-- Database
--

CREATE SCHEMA `restjaxrsjersey`;
USE `restjaxrsjersey`;

--
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;

CREATE TABLE `contact` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `last_name` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `mobile_number` varchar(255) NOT NULL,
  `email_address` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Initial rows for `contact` table
--

INSERT INTO `contact` (`id`, `last_name`, `first_name`, `mobile_number`, `email_address`, `address`) VALUES
(1, 'Rizal', 'Jose', '09167894560', 'joserizal@gmail.com', 'Calamba, Laguna'),
(2, 'Bonifacio', 'Andres', '09167894561', 'andresbonifacio@gmail.com', 'Tondo, Manila'),
(3, 'Aguinaldo', 'Emilio', '09167894562', 'emilioaguinaldo@gmail.com', 'Kawit, Cavite');