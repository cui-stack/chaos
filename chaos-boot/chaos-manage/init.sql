/*
Navicat MySQL Data Transfer

Source Server         : okya-prod
Source Server Version : 80016
Source Host           : okya-prod-2020.mysql.rds.aliyuncs.com:3306
Source Database       : chaos

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2020-08-04 16:26:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for chaos_admin
-- ----------------------------
DROP TABLE IF EXISTS `chaos_admin`;
CREATE TABLE `chaos_admin` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `mu` varchar(64) NOT NULL,
  `version` int(11) NOT NULL DEFAULT '1',
  `is_delete` tinyint(2) NOT NULL DEFAULT '0',
  `modify_time` datetime NOT NULL,
  `create_time` datetime NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `qq` varchar(255) DEFAULT NULL,
  `zip` varchar(255) DEFAULT NULL,
  `addr` varchar(255) DEFAULT NULL,
  `lastlogin_time` datetime DEFAULT NULL,
  `login_times` int(11) unsigned DEFAULT NULL,
  `ip` varchar(255) NOT NULL,
  `status` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '0正常 1被封',
  `platform_mu` varchar(64) NOT NULL,
  `sort` bigint(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE,
  UNIQUE KEY `inx_mu` (`mu`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=153 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for chaos_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `chaos_admin_role`;
CREATE TABLE `chaos_admin_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `mu` varchar(64) NOT NULL,
  `admin_mu` varchar(255) NOT NULL,
  `role_mu` varchar(255) NOT NULL,
  `create_time` datetime NOT NULL,
  `modify_time` datetime NOT NULL,
  `is_delete` tinyint(2) NOT NULL DEFAULT '0',
  `version` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for chaos_log
-- ----------------------------
DROP TABLE IF EXISTS `chaos_log`;
CREATE TABLE `chaos_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `mu` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` datetime NOT NULL,
  `modify_time` datetime NOT NULL,
  `is_delete` tinyint(2) NOT NULL DEFAULT '0',
  `version` int(11) NOT NULL DEFAULT '1',
  `user_mu` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `ip` varchar(100) NOT NULL,
  `uri` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `time` int(11) DEFAULT NULL,
  `request` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `response` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24563 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for chaos_permission
-- ----------------------------
DROP TABLE IF EXISTS `chaos_permission`;
CREATE TABLE `chaos_permission` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `mu` varchar(64) NOT NULL,
  `isroot` tinyint(2) DEFAULT '0',
  `supmu` varchar(255) DEFAULT NULL,
  `link` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `create_time` datetime NOT NULL,
  `modify_time` datetime NOT NULL,
  `is_delete` tinyint(2) NOT NULL DEFAULT '0',
  `version` int(11) NOT NULL DEFAULT '1',
  `platform_mu` varchar(64) NOT NULL,
  `icon` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for chaos_platform
-- ----------------------------
DROP TABLE IF EXISTS `chaos_platform`;
CREATE TABLE `chaos_platform` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `mu` varchar(64) NOT NULL,
  `create_time` datetime NOT NULL,
  `modify_time` datetime NOT NULL,
  `is_delete` tinyint(2) unsigned NOT NULL DEFAULT '0',
  `version` int(11) unsigned NOT NULL DEFAULT '1',
  `name` varchar(64) NOT NULL COMMENT '平台名称',
  `sort` bigint(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `inx_mu` (`mu`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for chaos_role
-- ----------------------------
DROP TABLE IF EXISTS `chaos_role`;
CREATE TABLE `chaos_role` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `mu` varchar(64) NOT NULL,
  `name` varchar(255) NOT NULL,
  `info` varchar(255) DEFAULT NULL,
  `index_link` varchar(255) NOT NULL,
  `create_time` datetime NOT NULL,
  `modify_time` datetime NOT NULL,
  `is_delete` tinyint(2) NOT NULL DEFAULT '0',
  `version` int(11) NOT NULL DEFAULT '1',
  `platform_mu` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for chaos_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `chaos_role_permission`;
CREATE TABLE `chaos_role_permission` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `mu` varchar(64) NOT NULL,
  `role_mu` varchar(64) NOT NULL,
  `permission_mu` varchar(64) NOT NULL,
  `create_time` datetime NOT NULL,
  `modify_time` datetime NOT NULL,
  `is_delete` tinyint(2) NOT NULL DEFAULT '0',
  `version` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
