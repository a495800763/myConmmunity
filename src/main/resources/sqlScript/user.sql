/*
Navicat MySQL Data Transfer

Source Server         : current
Source Server Version : 50096
Source Host           : localhost:3306
Source Database       : community

Target Server Type    : MYSQL
Target Server Version : 50096
File Encoding         : 65001

Date: 2020-04-06 10:56:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL auto_increment,
  `account_id` varchar(100) default NULL,
  `name` varchar(50) default NULL,
  `token` varchar(36) default NULL,
  `gmt_create` bigint(20) default NULL,
  `gmt_modified` bigint(20) default NULL,
  `bio` varchar(255) default NULL,
  `avatar_url` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
