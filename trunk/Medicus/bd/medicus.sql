# MySQL-Front 3.2  (Build 7.16)

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES 'latin1' */;

# Host: localhost    Database: medicus
# ------------------------------------------------------
# Server version 5.0.27-community-nt

#
# Table structure for table obras_sociales
#

DROP TABLE IF EXISTS `obras_sociales`;
CREATE TABLE `obras_sociales` (
  `Id` int(11) NOT NULL auto_increment,
  `codigo` varchar(11) NOT NULL,
  `nombre` varchar(150) NOT NULL,
  `monto` double(5,2) unsigned NOT NULL default '0.00',
  PRIMARY KEY  (`Id`),
  UNIQUE KEY `codigo` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Dumping data for table obras_sociales
#

INSERT INTO `obras_sociales` VALUES (2,'swiss','Swiss Medical Group',187.6);
INSERT INTO `obras_sociales` VALUES (8,'medife','Medife S.A.',87.6);
INSERT INTO `obras_sociales` VALUES (11,'osde','Obra Social de Empresas',204.6);


#
# Table structure for table pacientes
#

DROP TABLE IF EXISTS `pacientes`;
CREATE TABLE `pacientes` (
  `Id` int(11) NOT NULL auto_increment,
  `legajo` int(11) NOT NULL default '0',
  `edad` int(3) unsigned NOT NULL default '0',
  `obra_social_codigo` varchar(11) NOT NULL,
  PRIMARY KEY  (`Id`),
  UNIQUE KEY `uk_legajo` (`legajo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Dumping data for table pacientes
#

INSERT INTO `pacientes` VALUES (2,800608,43,'osde');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
