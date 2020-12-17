/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50715
Source Host           : localhost:3306
Source Database       : chaos

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2020-11-25 15:52:19
*/
CREATE DATABASE chaos CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for chaos_admin
-- ----------------------------
DROP TABLE IF EXISTS `chaos_admin`;
CREATE TABLE `chaos_admin` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `mu` varchar(64) NOT NULL,
  `modify_time` datetime NOT NULL,
  `create_time` datetime NOT NULL,
  `is_delete` tinyint(2) NOT NULL DEFAULT '0',
  `version` int(11) NOT NULL DEFAULT '1',
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `qq` varchar(255) DEFAULT NULL,
  `zip` varchar(255) DEFAULT NULL,
  `addr` varchar(255) DEFAULT NULL,
  `lastlogin_time` datetime DEFAULT NULL,
  `login_times` int(11) unsigned DEFAULT '0',
  `ip` varchar(255) DEFAULT NULL,
  `status` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '0正常 1被封',
  `platform_mu` varchar(64) NOT NULL,
  `sort` bigint(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `inx_username` (`username`) USING BTREE,
  UNIQUE KEY `inx_mu` (`mu`) USING BTREE,
  UNIQUE KEY `index_phone_plt_unique` (`phone`,`platform_mu`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='所有用户';

-- ----------------------------
-- Records of chaos_admin
-- ----------------------------
INSERT INTO `chaos_admin` VALUES ('1', '2', '2020-06-04 10:17:22', '2020-04-13 00:13:58', '0', '3', 'admin', '0192023a7bbd73250516f069df18b500', 'admin', '18888888888', null, null, 'admin123', null, '2020-11-24 23:21:59', '126', '113.87.128.77', '0', '1', '0');

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
  PRIMARY KEY (`id`),
  UNIQUE KEY `inx_mu` (`mu`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of chaos_admin_role
-- ----------------------------
INSERT INTO `chaos_admin_role` VALUES ('1', '10', '2', '1253273444540895233', '2020-04-28 23:43:42', '2020-06-04 10:17:22', '0', '2');

-- ----------------------------
-- Table structure for chaos_env
-- ----------------------------
DROP TABLE IF EXISTS `chaos_env`;
CREATE TABLE `chaos_env` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `mu` varchar(64) NOT NULL,
  `version` int(11) NOT NULL DEFAULT '1',
  `is_delete` tinyint(2) NOT NULL DEFAULT '0',
  `modify_time` datetime NOT NULL,
  `create_time` datetime NOT NULL,
  `link` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `info` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `inx_mu` (`mu`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of chaos_env
-- ----------------------------

-- ----------------------------
-- Table structure for chaos_link
-- ----------------------------
DROP TABLE IF EXISTS `chaos_link`;
CREATE TABLE `chaos_link` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `mu` varchar(64) NOT NULL,
  `version` int(11) NOT NULL DEFAULT '1',
  `is_delete` tinyint(2) NOT NULL DEFAULT '0',
  `modify_time` datetime NOT NULL,
  `create_time` datetime NOT NULL,
  `link` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `info` varchar(255) NOT NULL,
  `hits` int(11) DEFAULT '0',
  `sort` bigint(11) DEFAULT '0',
  `status` tinyint(2) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `inx_mu` (`mu`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of chaos_link
-- ----------------------------

-- ----------------------------
-- Table structure for chaos_log
-- ----------------------------
DROP TABLE IF EXISTS `chaos_log`;
CREATE TABLE `chaos_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `mu` varchar(64) CHARACTER SET utf8mb4 NOT NULL,
  `create_time` datetime NOT NULL,
  `modify_time` datetime NOT NULL,
  `is_delete` tinyint(2) NOT NULL DEFAULT '0',
  `version` int(11) NOT NULL DEFAULT '1',
  `user_mu` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL,
  `ip` varchar(100) NOT NULL,
  `uri` varchar(1000) CHARACTER SET utf8mb4 DEFAULT NULL,
  `time` int(11) DEFAULT NULL,
  `request` varchar(1000) CHARACTER SET utf8mb4 DEFAULT NULL,
  `response` varchar(1000) CHARACTER SET utf8mb4 DEFAULT NULL,
  `platform` varchar(100) DEFAULT NULL,
  `env` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `inx_mu` (`mu`) USING BTREE,
  KEY `inx_user_mu` (`user_mu`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of chaos_log
-- ----------------------------

-- ----------------------------
-- Table structure for chaos_permission
-- ----------------------------
DROP TABLE IF EXISTS `chaos_permission`;
CREATE TABLE `chaos_permission` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `mu` varchar(64) NOT NULL,
  `create_time` datetime NOT NULL,
  `modify_time` datetime NOT NULL,
  `is_delete` tinyint(2) NOT NULL DEFAULT '0',
  `version` int(11) NOT NULL DEFAULT '1',
  `isroot` tinyint(2) DEFAULT '0',
  `supmu` varchar(255) DEFAULT NULL,
  `link` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `platform_mu` varchar(64) NOT NULL,
  `icon` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `inx_mu` (`mu`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of chaos_permission
-- ----------------------------
INSERT INTO `chaos_permission` VALUES ('1', '100', '2020-04-05 13:48:50', '2020-08-21 22:50:56', '0', '2', '1', '', '', '控制台', '1', 'el-icon-menu');
INSERT INTO `chaos_permission` VALUES ('2', '101', '2020-04-05 13:48:50', '2020-04-05 13:48:50', '0', '1', '0', '100', '/admin', '管理员', '1', null);
INSERT INTO `chaos_permission` VALUES ('3', '102', '2020-04-17 18:11:31', '2020-04-17 18:11:36', '0', '1', '0', '100', '/role', '角色', '1', null);
INSERT INTO `chaos_permission` VALUES ('4', '103', '2020-04-17 18:23:28', '2020-04-17 18:23:31', '0', '1', '0', '100', '/permission', '资源', '1', null);
INSERT INTO `chaos_permission` VALUES ('5', '1253281684146245634', '2020-04-23 19:16:53', '2020-04-23 19:16:53', '0', '1', '0', '100', '/platform', '平台', '1', null);
INSERT INTO `chaos_permission` VALUES ('6', '1255328926126325761', '2020-04-29 10:51:54', '2020-04-29 10:51:54', '0', '1', '0', '100', '/log', '日志', '1', null);
INSERT INTO `chaos_permission` VALUES ('7', '1255341292318715906', '2020-04-29 11:41:02', '2020-04-29 11:41:02', '0', '1', '0', '100', '/onlineAdmin', '在线管理员', '1', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of chaos_platform
-- ----------------------------
INSERT INTO `chaos_platform` VALUES ('1', '1', '2020-04-23 19:17:11', '2020-04-23 19:17:14', '0', '1', '混沌后台', '0');

-- ----------------------------
-- Table structure for chaos_role
-- ----------------------------
DROP TABLE IF EXISTS `chaos_role`;
CREATE TABLE `chaos_role` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `mu` varchar(64) NOT NULL,
  `create_time` datetime NOT NULL,
  `modify_time` datetime NOT NULL,
  `is_delete` tinyint(2) NOT NULL DEFAULT '0',
  `version` int(11) NOT NULL DEFAULT '1',
  `name` varchar(255) NOT NULL,
  `info` varchar(255) DEFAULT NULL,
  `index_link` varchar(255) NOT NULL,
  `platform_mu` varchar(64) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `inx_mu` (`mu`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of chaos_role
-- ----------------------------
INSERT INTO `chaos_role` VALUES ('1', '1253273444540895233', '2020-04-23 18:44:09', '2020-05-17 16:29:50', '0', '3', 'ADMIN', '混沌管理员', '/index', '1');
INSERT INTO `chaos_role` VALUES ('2', '1326089091246256130', '2020-11-10 17:07:32', '2020-11-10 17:07:50', '0', '2', '服务调用者', 'SV', '', '1');

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
  PRIMARY KEY (`id`),
  UNIQUE KEY `inx_mu` (`mu`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of chaos_role_permission
-- ----------------------------
INSERT INTO `chaos_role_permission` VALUES ('1', '101', '1253273444540895233', '101', '2020-04-05 13:57:01', '2020-04-05 13:57:01', '0', '1');
INSERT INTO `chaos_role_permission` VALUES ('2', '102', '1253273444540895233', '102', '2020-04-17 18:12:39', '2020-04-17 18:12:42', '0', '1');
INSERT INTO `chaos_role_permission` VALUES ('3', '103', '1253273444540895233', '103', '2020-04-17 18:23:54', '2020-04-17 18:23:57', '0', '1');
INSERT INTO `chaos_role_permission` VALUES ('4', '104', '1253273444540895233', '1253281684146245634', '2020-04-23 19:36:18', '2020-04-23 19:36:20', '0', '1');
INSERT INTO `chaos_role_permission` VALUES ('5', '105', '1253273444540895233', '1255328926126325761', '2020-04-29 10:53:20', '2020-04-29 10:53:23', '0', '1');
INSERT INTO `chaos_role_permission` VALUES ('6', '106', '1253273444540895233', '1255341292318715906', '2020-04-29 11:41:28', '2020-04-29 11:41:30', '0', '1');
