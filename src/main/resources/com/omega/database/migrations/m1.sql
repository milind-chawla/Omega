-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 27, 2016 at 07:56 PM
-- Server version: 10.1.10-MariaDB
-- PHP Version: 7.0.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `user1`
--

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `ID` bigint(20) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `IMAGE` varchar(255) NOT NULL DEFAULT 'resources/images/thumbnail1.jpg'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`ID`, `NAME`, `IMAGE`) VALUES
(1, 'Book 1', 'resources/images/thumbnail1.jpg'),
(2, 'Book 2', 'resources/images/thumbnail1.jpg'),
(3, 'Book 3x', 'resources/images/thumbnail1.jpg'),
(4, 'Book 4x', 'resources/images/thumbnail1.jpg'),
(23, 'Learn Clojure', 'resources/images/thumbnail1.jpg'),
(24, 'Book 1.1', 'resources/images/thumbnail1.jpg'),
(25, 'Book 1.1', 'resources/images/thumbnail1.jpg'),
(26, 'Book 1.1', 'resources/images/thumbnail1.jpg'),
(27, 'Book 1.2', 'resources/images/thumbnail1.jpg'),
(28, 'Book 1.3', 'resources/images/thumbnail1.jpg'),
(36, 'Bkk', 'resources/images/thumbnail1.jpg'),
(37, 'Book 1.1cx', 'resources/images/thumbnail1.jpg'),
(38, 'Book 1.1d', 'resources/images/thumbnail1.jpg'),
(39, 'Bv3', 'resources/images/thumbnail1.jpg'),
(40, 'Noname 1', 'resources/images/thumbnail1.jpg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `book`
--
ALTER TABLE `book`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
