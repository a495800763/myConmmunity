/*
Navicat MySQL Data Transfer

Source Server         : current
Source Server Version : 50096
Source Host           : localhost:3306
Source Database       : community

Target Server Type    : MYSQL
Target Server Version : 50096
File Encoding         : 65001

Date: 2020-04-06 10:56:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tagcategory
-- ----------------------------
DROP TABLE IF EXISTS `tagcategory`;
CREATE TABLE `tagcategory` (
  `id` int(11) NOT NULL auto_increment,
  `category_name` varchar(128) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
