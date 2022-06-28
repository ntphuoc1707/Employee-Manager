-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 28, 2022 at 04:42 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `employeemanager`
--

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `id` int(11) NOT NULL,
  `name` varchar(150) NOT NULL,
  `phone` varchar(11) NOT NULL,
  `id_team` int(11) NOT NULL,
  `gender` tinyint(4) NOT NULL,
  `birthday` datetime NOT NULL,
  `salary_per_hour` double NOT NULL,
  `address` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`id`, `name`, `phone`, `id_team`, `gender`, `birthday`, `salary_per_hour`, `address`) VALUES
(1, 'Phuoc', '0123456789', 1, 0, '2022-06-16 00:00:00', 10, 'binh phuoc'),
(2, 'Phuoc', '0123456789', 1, 0, '2022-06-16 00:00:00', 2, 'binh phuoc'),
(4, 'Phuoc', '0123456789', 1, 0, '2022-06-16 00:00:00', 2, 'binh phuoc'),
(5, 'Phuoc', '0123456789', 1, 0, '2022-06-16 00:00:00', 2, 'binh phuoc');

-- --------------------------------------------------------

--
-- Table structure for table `team`
--

CREATE TABLE `team` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `team`
--

INSERT INTO `team` (`id`, `name`) VALUES
(1, 'Manager'),
(2, 'IT'),
(4, 'IT2');

-- --------------------------------------------------------

--
-- Table structure for table `working`
--

CREATE TABLE `working` (
  `id_emp` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `hour` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `working`
--

INSERT INTO `working` (`id_emp`, `date`, `hour`) VALUES
(1, '2022-06-16 00:00:00', 8);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_emp_team` (`id_team`);

--
-- Indexes for table `team`
--
ALTER TABLE `team`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `working`
--
ALTER TABLE `working`
  ADD PRIMARY KEY (`id_emp`,`date`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `team`
--
ALTER TABLE `team`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `FK_emp_team` FOREIGN KEY (`id_team`) REFERENCES `team` (`id`);

--
-- Constraints for table `working`
--
ALTER TABLE `working`
  ADD CONSTRAINT `FK_emp_working` FOREIGN KEY (`id_emp`) REFERENCES `employee` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
