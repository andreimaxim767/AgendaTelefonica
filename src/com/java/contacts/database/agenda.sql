-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 28, 2013 at 12:45 PM
-- Server version: 5.6.12-log
-- PHP Version: 5.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `contacts`
--
CREATE DATABASE IF NOT EXISTS `agenda` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `agenda`;

-- --------------------------------------------------------

--
-- Table structure for table `contact`
--

CREATE TABLE IF NOT EXISTS `contact` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nume` varchar(50) NOT NULL COMMENT 'Nume ',
  `prenume` varchar(50) NOT NULL COMMENT 'Prenume ',
  `cnp` varchar(13) NOT NULL COMMENT 'CNP',
  `telefon` varchar(20) NOT NULL COMMENT 'Numar Telefon',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=43 ;

--
-- Dumping data for table `contact`
--

INSERT INTO `contact` (`id`, `nume`, `prenume`, `cnp`, `telefon`) VALUES
(1, 'Andrei', 'Gheorghe', '1870608340437', '0744557785'),
(2, 'Constantin', 'Titu', '1780685427757', '0757556887'),
(3, 'Dumitru', 'Patrascu', '1680508698547', '0247412134'),
(4, 'Liviu', 'Mihai', '1870608340437', '0758230577'),
(5, 'Marian', 'Pamfil', '1750417340014', '0767249771'),
(6, 'Marius', 'Anton', '1850612320541', '0723442412');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
