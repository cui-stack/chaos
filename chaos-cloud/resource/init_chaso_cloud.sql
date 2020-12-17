/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50715
Source Host           : localhost:3306
Source Database       : chaos

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2020-12-01 14:22:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for chaos_info
-- ----------------------------
DROP TABLE IF EXISTS `chaos_info`;
CREATE TABLE `chaos_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `mu` varchar(64) NOT NULL,
  `create_time` datetime NOT NULL,
  `modify_time` datetime NOT NULL,
  `is_delete` tinyint(2) NOT NULL DEFAULT '0',
  `version` int(11) NOT NULL DEFAULT '1',
  `title` varchar(64) NOT NULL,
  `info` varchar(255) NOT NULL,
  `img_url` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `inx_mu` (`mu`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of chaos_info
-- ----------------------------

-- ----------------------------
-- Table structure for chaos_user
-- ----------------------------
DROP TABLE IF EXISTS `chaos_user`;
CREATE TABLE `chaos_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `mu` varchar(64) NOT NULL,
  `openid` varchar(255) NOT NULL,
  `unionid` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT '',
  `qq` varchar(255) DEFAULT NULL,
  `zip` varchar(255) DEFAULT NULL,
  `addr` varchar(255) DEFAULT NULL,
  `nick` varchar(255) DEFAULT NULL,
  `head` varchar(255) DEFAULT NULL,
  `sex` varchar(32) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `prov` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `dist` varchar(255) DEFAULT NULL,
  `lastlogin_time` datetime NOT NULL,
  `ip` varchar(255) NOT NULL,
  `userType` tinyint(3) DEFAULT '0' COMMENT '0:游客 1：村民 ',
  `referrer` varchar(255) DEFAULT '',
  `source` varchar(64) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `modify_time` datetime NOT NULL,
  `is_delete` tinyint(2) NOT NULL DEFAULT '0',
  `version` int(11) NOT NULL DEFAULT '1',
  `intention` varchar(900) DEFAULT NULL,
  `wxcountry` varchar(255) DEFAULT NULL,
  `wxprov` varchar(255) DEFAULT NULL,
  `wxcity` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `inx_mu` (`mu`) USING BTREE,
  KEY `inx_openid` (`openid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of chaos_user
-- ----------------------------
