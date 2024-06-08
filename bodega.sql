-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 08-06-2024 a las 22:54:04
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bodega`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cont_com`
--

CREATE TABLE `cont_com` (
  `id_cont_com` int(11) NOT NULL,
  `id_cont_sim_2` int(11) DEFAULT NULL,
  `id_com_2` int(11) DEFAULT NULL,
  `id_cxc_com` int(11) DEFAULT NULL,
  `id_cont_sim_1` int(11) DEFAULT NULL,
  `observaciones` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `tipo_cuenta` varchar(255) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `update_at` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cont_sim_1`
--

CREATE TABLE `cont_sim_1` (
  `id_cont_sim_1` int(11) NOT NULL,
  `codigo` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `update_at` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cont_sim_2`
--

CREATE TABLE `cont_sim_2` (
  `id_cont_sim_2` int(11) NOT NULL,
  `codigo` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `tipo_cuenta` varchar(255) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `update_at` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cxc_com`
--

CREATE TABLE `cxc_com` (
  `id_cxc_com` int(11) NOT NULL,
  `id_fact_sim_1` int(11) DEFAULT NULL,
  `id_cxc_sim_2` int(11) DEFAULT NULL,
  `id_cxc_sim_1` int(11) DEFAULT NULL,
  `id_fac_com` int(11) DEFAULT NULL,
  `nombre_com` varchar(255) DEFAULT NULL,
  `date_pay` date DEFAULT NULL,
  `valor` decimal(10,0) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `created_at` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cxc_sim_1`
--

CREATE TABLE `cxc_sim_1` (
  `id_cxc_sim_1` int(11) NOT NULL,
  `cedula` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `dir_cxc` varchar(255) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `update_at` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cxc_sim_2`
--

CREATE TABLE `cxc_sim_2` (
  `id_cxc_sim_2` int(11) NOT NULL,
  `codigo` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `update_at` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `fact_sim_1`
--

CREATE TABLE `fact_sim_1` (
  `id_fact_sim_1` int(11) NOT NULL,
  `ruc` varchar(255) DEFAULT NULL,
  `nombre_fac_cli` varchar(255) DEFAULT NULL,
  `dir` varchar(255) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `update_at` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `fac_com`
--

CREATE TABLE `fac_com` (
  `id_fac_com` int(11) NOT NULL,
  `id_fac_sim_2` int(11) DEFAULT NULL,
  `id_iv_sim1` int(11) DEFAULT NULL,
  `ruc` varchar(255) DEFAULT NULL,
  `nombre_fac_cli` varchar(255) DEFAULT NULL,
  `dir` varchar(255) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `update_at` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `fac_sim_2`
--

CREATE TABLE `fac_sim_2` (
  `id_fac_sim_2` int(11) NOT NULL,
  `codigo_ciu` varchar(255) DEFAULT NULL,
  `nombre_fac_ciu` varchar(255) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `update_at` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inv_com_2`
--

CREATE TABLE `inv_com_2` (
  `id_com_2` int(11) NOT NULL,
  `id_iv_sim1` int(11) DEFAULT NULL,
  `id_sim_2` int(11) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `update_at` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inv_sim_1`
--

CREATE TABLE `inv_sim_1` (
  `id_iv_sim1` int(11) NOT NULL,
  `id_use` int(11) DEFAULT NULL,
  `codigo` varchar(255) DEFAULT NULL,
  `nombre_inv` varchar(255) DEFAULT NULL,
  `precio_inv` decimal(10,0) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `update_at` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inv_sim_2`
--

CREATE TABLE `inv_sim_2` (
  `id_sim_2` int(11) NOT NULL,
  `nombre_sim_2` varchar(255) DEFAULT NULL,
  `tipo_mov` varchar(255) DEFAULT NULL,
  `codigo` int(11) DEFAULT NULL,
  `signo` varchar(255) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `update_at` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `id_use` int(11) NOT NULL,
  `usuario` varchar(255) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cont_com`
--
ALTER TABLE `cont_com`
  ADD PRIMARY KEY (`id_cont_com`),
  ADD KEY `id_cont_sim_2` (`id_cont_sim_2`),
  ADD KEY `id_com_2` (`id_com_2`),
  ADD KEY `id_cxc_com` (`id_cxc_com`),
  ADD KEY `id_cont_sim_1` (`id_cont_sim_1`);

--
-- Indices de la tabla `cont_sim_1`
--
ALTER TABLE `cont_sim_1`
  ADD PRIMARY KEY (`id_cont_sim_1`);

--
-- Indices de la tabla `cont_sim_2`
--
ALTER TABLE `cont_sim_2`
  ADD PRIMARY KEY (`id_cont_sim_2`);

--
-- Indices de la tabla `cxc_com`
--
ALTER TABLE `cxc_com`
  ADD PRIMARY KEY (`id_cxc_com`),
  ADD KEY `id_cxc_sim_2` (`id_cxc_sim_2`),
  ADD KEY `id_fac_com` (`id_fac_com`),
  ADD KEY `id_fact_sim_1` (`id_fact_sim_1`);

--
-- Indices de la tabla `cxc_sim_1`
--
ALTER TABLE `cxc_sim_1`
  ADD PRIMARY KEY (`id_cxc_sim_1`);

--
-- Indices de la tabla `cxc_sim_2`
--
ALTER TABLE `cxc_sim_2`
  ADD PRIMARY KEY (`id_cxc_sim_2`);

--
-- Indices de la tabla `fact_sim_1`
--
ALTER TABLE `fact_sim_1`
  ADD PRIMARY KEY (`id_fact_sim_1`);

--
-- Indices de la tabla `fac_com`
--
ALTER TABLE `fac_com`
  ADD PRIMARY KEY (`id_fac_com`),
  ADD KEY `id_fac_sim_2` (`id_fac_sim_2`),
  ADD KEY `id_iv_sim1` (`id_iv_sim1`);

--
-- Indices de la tabla `fac_sim_2`
--
ALTER TABLE `fac_sim_2`
  ADD PRIMARY KEY (`id_fac_sim_2`);

--
-- Indices de la tabla `inv_com_2`
--
ALTER TABLE `inv_com_2`
  ADD PRIMARY KEY (`id_com_2`),
  ADD KEY `id_iv_sim1` (`id_iv_sim1`),
  ADD KEY `id_sim_2` (`id_sim_2`);

--
-- Indices de la tabla `inv_sim_1`
--
ALTER TABLE `inv_sim_1`
  ADD PRIMARY KEY (`id_iv_sim1`),
  ADD KEY `id_use` (`id_use`);

--
-- Indices de la tabla `inv_sim_2`
--
ALTER TABLE `inv_sim_2`
  ADD PRIMARY KEY (`id_sim_2`);

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_use`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cont_com`
--
ALTER TABLE `cont_com`
  ADD CONSTRAINT `cont_com_ibfk_1` FOREIGN KEY (`id_cont_sim_2`) REFERENCES `cont_sim_2` (`id_cont_sim_2`),
  ADD CONSTRAINT `cont_com_ibfk_2` FOREIGN KEY (`id_com_2`) REFERENCES `inv_com_2` (`id_com_2`),
  ADD CONSTRAINT `cont_com_ibfk_3` FOREIGN KEY (`id_cxc_com`) REFERENCES `cxc_com` (`id_cxc_com`),
  ADD CONSTRAINT `cont_com_ibfk_4` FOREIGN KEY (`id_cont_sim_1`) REFERENCES `cont_sim_1` (`id_cont_sim_1`);

--
-- Filtros para la tabla `cxc_com`
--
ALTER TABLE `cxc_com`
  ADD CONSTRAINT `cxc_com_ibfk_1` FOREIGN KEY (`id_fact_sim_1`) REFERENCES `fact_sim_1` (`id_fact_sim_1`),
  ADD CONSTRAINT `cxc_com_ibfk_2` FOREIGN KEY (`id_cxc_sim_2`) REFERENCES `cxc_sim_2` (`id_cxc_sim_2`),
  ADD CONSTRAINT `cxc_com_ibfk_3` FOREIGN KEY (`id_fac_com`) REFERENCES `fac_com` (`id_fac_com`),
  ADD CONSTRAINT `cxc_com_ibfk_4` FOREIGN KEY (`id_fact_sim_1`) REFERENCES `cxc_sim_1` (`id_cxc_sim_1`);

--
-- Filtros para la tabla `fac_com`
--
ALTER TABLE `fac_com`
  ADD CONSTRAINT `fac_com_ibfk_1` FOREIGN KEY (`id_fac_sim_2`) REFERENCES `fac_sim_2` (`id_fac_sim_2`),
  ADD CONSTRAINT `fac_com_ibfk_2` FOREIGN KEY (`id_iv_sim1`) REFERENCES `inv_sim_1` (`id_iv_sim1`);

--
-- Filtros para la tabla `inv_com_2`
--
ALTER TABLE `inv_com_2`
  ADD CONSTRAINT `inv_com_2_ibfk_1` FOREIGN KEY (`id_iv_sim1`) REFERENCES `inv_sim_1` (`id_iv_sim1`),
  ADD CONSTRAINT `inv_com_2_ibfk_2` FOREIGN KEY (`id_sim_2`) REFERENCES `inv_sim_2` (`id_sim_2`);

--
-- Filtros para la tabla `inv_sim_1`
--
ALTER TABLE `inv_sim_1`
  ADD CONSTRAINT `inv_sim_1_ibfk_1` FOREIGN KEY (`id_use`) REFERENCES `user` (`id_use`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
