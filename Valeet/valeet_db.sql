-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 03-11-2019 a las 14:32:14
-- Versión del servidor: 10.4.6-MariaDB
-- Versión de PHP: 7.3.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `valeet_db`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `val_billing`
--

CREATE TABLE `val_billing` (
  `billing_id` int(11) NOT NULL,
  `booking_id` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `nit` bigint(20) NOT NULL,
  `total_due` decimal(10,2) NOT NULL,
  `auth_number` bigint(20) NOT NULL,
  `control_number` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `billing_date` date NOT NULL,
  `payment_status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `val_booking`
--

CREATE TABLE `val_booking` (
  `booking_id` int(11) NOT NULL,
  `vehicle_id` int(11) NOT NULL,
  `garage_id` int(11) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `start_time` time NOT NULL,
  `end_time` time NOT NULL,
  `total_time` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `val_garage`
--

CREATE TABLE `val_garage` (
  `garage_id` int(11) NOT NULL,
  `person_id` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `address` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `zone` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `rate` decimal(10,2) NOT NULL,
  `lat` double(10,6) NOT NULL,
  `long` double(10,6) NOT NULL,
  `total_spots` int(11) NOT NULL,
  `free_spots` int(11) NOT NULL,
  `occupied_spots` int(11) NOT NULL,
  `status` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `val_person`
--

CREATE TABLE `val_person` (
  `person_id` int(11) NOT NULL,
  `first_name` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `first_last_name` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `second_last_name` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `telegram_id` int(30) NOT NULL,
  `personal_id` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `last_response` int(11) NOT NULL DEFAULT 0,
  `status` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `val_vehicle`
--

CREATE TABLE `val_vehicle` (
  `vehicle_id` int(11) NOT NULL,
  `person_id` int(11) NOT NULL,
  `license_plate` varchar(10) COLLATE utf8_spanish_ci NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `val_billing`
--
ALTER TABLE `val_billing`
  ADD PRIMARY KEY (`billing_id`),
  ADD KEY `val_billing_val_booking` (`booking_id`);

--
-- Indices de la tabla `val_booking`
--
ALTER TABLE `val_booking`
  ADD PRIMARY KEY (`booking_id`),
  ADD KEY `val_booking_val_garage` (`garage_id`),
  ADD KEY `val_booking_val_vehicle` (`vehicle_id`);

--
-- Indices de la tabla `val_garage`
--
ALTER TABLE `val_garage`
  ADD PRIMARY KEY (`garage_id`),
  ADD KEY `val_garage_val_person` (`person_id`);

--
-- Indices de la tabla `val_person`
--
ALTER TABLE `val_person`
  ADD PRIMARY KEY (`person_id`);

--
-- Indices de la tabla `val_vehicle`
--
ALTER TABLE `val_vehicle`
  ADD PRIMARY KEY (`vehicle_id`),
  ADD KEY `val_automobile_val_person` (`person_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `val_billing`
--
ALTER TABLE `val_billing`
  MODIFY `billing_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `val_booking`
--
ALTER TABLE `val_booking`
  MODIFY `booking_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `val_garage`
--
ALTER TABLE `val_garage`
  MODIFY `garage_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `val_person`
--
ALTER TABLE `val_person`
  MODIFY `person_id` int(11) NOT NULL AUTO_INCREMENT;


--
-- AUTO_INCREMENT de la tabla `val_vehicle`
--
ALTER TABLE `val_vehicle`
  MODIFY `vehicle_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `val_billing`
--
ALTER TABLE `val_billing`
  ADD CONSTRAINT `val_billing_val_booking` FOREIGN KEY (`booking_id`) REFERENCES `val_booking` (`booking_id`);

--
-- Filtros para la tabla `val_booking`
--
ALTER TABLE `val_booking`
  ADD CONSTRAINT `val_booking_val_garage` FOREIGN KEY (`garage_id`) REFERENCES `val_garage` (`garage_id`),
  ADD CONSTRAINT `val_booking_val_vehicle` FOREIGN KEY (`vehicle_id`) REFERENCES `val_vehicle` (`vehicle_id`);

--
-- Filtros para la tabla `val_garage`
--
ALTER TABLE `val_garage`
  ADD CONSTRAINT `val_garage_val_person` FOREIGN KEY (`person_id`) REFERENCES `val_person` (`person_id`);

--
-- Filtros para la tabla `val_vehicle`
--
ALTER TABLE `val_vehicle`
  ADD CONSTRAINT `val_automobile_val_person` FOREIGN KEY (`person_id`) REFERENCES `val_person` (`person_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
