/*
Navicat MySQL Data Transfer

Source Server         : current
Source Server Version : 50096
Source Host           : localhost:3306
Source Database       : community

Target Server Type    : MYSQL
Target Server Version : 50096
File Encoding         : 65001

Date: 2020-04-06 10:55:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `id` bigint(20) NOT NULL auto_increment,
  `title` varchar(50) default NULL,
  `description` text,
  `gmt_create` bigint(20) default NULL,
  `gmt_modified` bigint(20) default NULL,
  `creator` bigint(20) default NULL,
  `comment_count` int(11) default '0',
  `view_count` int(11) default '0',
  `like_count` int(11) default '0',
  `tag` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
