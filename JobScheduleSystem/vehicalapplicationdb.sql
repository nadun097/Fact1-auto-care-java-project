-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 07, 2024 at 06:26 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `vehicalapplicationdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `jobtable`
--

CREATE TABLE `jobtable` (
  `ID` int(11) NOT NULL,
  `Customer_Name` varchar(100) NOT NULL,
  `Customer_Mail` varchar(100) NOT NULL,
  `Phone` varchar(100) NOT NULL,
  `Times` varchar(100) NOT NULL,
  `VehivalNo` varchar(20) NOT NULL,
  `Date` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `jobtable`
--

INSERT INTO `jobtable` (`ID`, `Customer_Name`, `Customer_Mail`, `Phone`, `Times`, `VehivalNo`, `Date`) VALUES
(1, 'rawr', 'szfs', 'sfsaf', 'job2, job3, jo', 'wrw', ''),
(4, 'nadun', 'SDASdsasdased', 'sasasa', '', '12122', '2024-08-15'),
(5, '12', '123', '4124', 'job1, job3, job4, job7, jo', '123', '2024-08-21'),
(6, 'eqred', 'wqrwq', 'qwrwq', 'job3, jo', 'qwrw', '2024-08-21'),
(7, 'text', 'wetew', 'wtrfew', 'job2, job3, job5, jo', 'erfewr', '2024-08-23'),
(8, 'w2', 'fd', 'rdwer', 'job2, job3, jo', 'efr', '2024-08-14'),
(9, '2ewrdew', 'wwww', 'qw', 'job3, jo', 'sdd', '2024-07-31'),
(10, 'new', 'new', 'new', 'job3, job5, jo', 'new', '2024-08-22'),
(11, '12', '12', '12', '', '12', '2024-08-08'),
(12, 'ww', 'weew', 'we', 'job1, job3, jo', 'wwe', '2024-08-15');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `Name` varchar(50) NOT NULL,
  `ID` int(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `Position` varchar(50) NOT NULL,
  `Email` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`Name`, `ID`, `Password`, `Position`, `Email`) VALUES
('nadun', 1, '1234', '1', ''),
('nadun', 2, '1234', 'no', 'nadunanjana31@gmail.com'),
('swemini', 3, '1234', 'employee', 'sewmidananjana@gmail.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `jobtable`
--
ALTER TABLE `jobtable`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `jobtable`
--
ALTER TABLE `jobtable`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
