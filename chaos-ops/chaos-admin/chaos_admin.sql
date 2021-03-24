/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50715
Source Host           : localhost:3306
Source Database       : chaos

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2021-03-24 14:49:56
*/

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
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 COMMENT='所有用户';

-- ----------------------------
-- Records of chaos_admin
-- ----------------------------
INSERT INTO `chaos_admin` VALUES ('1', '2', '2021-02-20 15:09:59', '2020-04-13 00:13:58', '0', '5', 'admin', '0192023a7bbd73250516f069df18b500', 'admin', '18888888888', null, null, 'admin123', null, '2021-03-24 14:44:23', '215', '127.0.0.1', '0', '1', '0');
INSERT INTO `chaos_admin` VALUES ('2', '1337254595762692098', '2020-12-11 12:35:15', '2020-12-11 12:35:15', '0', '1', 'admina', '0192023a7bbd73250516f069df18b500', '', '18825257777', '', '', 'admin123', '', '2021-02-19 15:07:34', '14', '127.0.0.1', '0', '2', '18561515');
INSERT INTO `chaos_admin` VALUES ('3', '1337280503714250753', '2021-03-24 14:45:21', '2020-12-11 14:18:12', '0', '2', 'chaos-manage-local', 'f9c1af4e4258b6031e6e0889f990299f', '', '18811111111', '', '', 'chaos-manage-local', '', '2021-03-24 14:46:18', '13', '192.168.99.1', '0', '1', '18567692');
INSERT INTO `chaos_admin` VALUES ('4', '1337280687114387457', '2021-03-24 14:45:10', '2020-12-11 14:18:56', '0', '2', 'chaos-manage-dev', '154ec44b2ae8d8c4145d1ba79bb62f2a', '', '18822222222', '', '', 'chaos-manage-dev', '', null, '0', '127.0.0.1', '0', '1', '18567735');
INSERT INTO `chaos_admin` VALUES ('5', '1337280796422144001', '2021-03-24 14:45:01', '2020-12-11 14:19:22', '0', '2', 'chaos-manage', 'a25db2d07278f647d2dc32ce8b22e98a', '', '18833333333', '', '', 'chaos-manage', '', null, '0', '127.0.0.1', '0', '1', '18567762');

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
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of chaos_admin_role
-- ----------------------------
INSERT INTO `chaos_admin_role` VALUES ('1', '10', '2', '1253273444540895233', '2020-04-28 23:43:42', '2021-02-20 15:09:59', '0', '4');
INSERT INTO `chaos_admin_role` VALUES ('38', '1374613201436565506', '1337280796422144001', '1326089091246256130', '2021-03-24 14:45:01', '2021-03-24 14:45:01', '0', '1');
INSERT INTO `chaos_admin_role` VALUES ('39', '1374613240657502209', '1337280687114387457', '1326089091246256130', '2021-03-24 14:45:10', '2021-03-24 14:45:10', '0', '1');
INSERT INTO `chaos_admin_role` VALUES ('40', '1374613285649801218', '1337280503714250753', '1326089091246256130', '2021-03-24 14:45:21', '2021-03-24 14:45:21', '0', '1');

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
  `info` varchar(255) DEFAULT NULL,
  `hits` int(11) DEFAULT '0',
  `sort` bigint(11) DEFAULT '0',
  `status` tinyint(2) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `inx_mu` (`mu`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of chaos_link
-- ----------------------------
INSERT INTO `chaos_link` VALUES ('1', '1363407049721970690', '3', '0', '2021-02-23 11:36:32', '2021-02-21 16:35:46', 'https://www.baidu.com', '百度', '百度任务人未发生发顺丰是发送到发交水电费水电费水电费森的说法胜多负少的防晒霜', '0', '24796746', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=5244 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of chaos_log
-- ----------------------------

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
  `info` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `inx_mu` (`mu`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of chaos_platform
-- ----------------------------
INSERT INTO `chaos_platform` VALUES ('1', '1', '2020-04-23 19:17:11', '2021-03-06 23:01:04', '0', '5', '管理后台', '0', '管理后台，账号，权限，日志');
INSERT INTO `chaos_platform` VALUES ('2', '2', '2020-12-11 11:36:24', '2020-12-18 10:19:51', '0', '2', '业务后台', '18557984', '');

-- ----------------------------
-- Table structure for chaos_resource
-- ----------------------------
DROP TABLE IF EXISTS `chaos_resource`;
CREATE TABLE `chaos_resource` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `mu` varchar(64) NOT NULL,
  `create_time` datetime NOT NULL,
  `modify_time` datetime NOT NULL,
  `is_delete` tinyint(2) NOT NULL DEFAULT '0',
  `version` int(11) NOT NULL DEFAULT '1',
  `is_root` tinyint(2) DEFAULT '0',
  `sup_mu` varchar(255) DEFAULT NULL,
  `link` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `platform_mu` varchar(64) NOT NULL,
  `icon` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `inx_mu` (`mu`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of chaos_resource
-- ----------------------------
INSERT INTO `chaos_resource` VALUES ('1', '100', '2020-04-05 13:48:50', '2021-03-09 18:00:14', '0', '3', '1', '100', '/', '控制台', '1', 'el-icon-menu');
INSERT INTO `chaos_resource` VALUES ('2', '101', '2020-04-05 13:48:50', '2020-04-05 13:48:50', '0', '1', '0', '100', '/admin', '管理员', '1', null);
INSERT INTO `chaos_resource` VALUES ('3', '102', '2020-04-17 18:11:31', '2020-04-17 18:11:36', '0', '1', '0', '100', '/role', '角色', '1', null);
INSERT INTO `chaos_resource` VALUES ('4', '103', '2020-04-17 18:23:28', '2020-04-17 18:23:31', '0', '1', '0', '100', '/resource', '资源', '1', null);
INSERT INTO `chaos_resource` VALUES ('5', '1253281684146245634', '2020-04-23 19:16:53', '2020-04-23 19:16:53', '0', '1', '0', '100', '/platform', '平台', '1', null);
INSERT INTO `chaos_resource` VALUES ('6', '1255328926126325761', '2020-04-29 10:51:54', '2020-04-29 10:51:54', '0', '1', '0', '100', '/log', '日志', '1', null);
INSERT INTO `chaos_resource` VALUES ('7', '1255341292318715906', '2020-04-29 11:41:02', '2020-04-29 11:41:02', '0', '1', '0', '100', '/onlineAdmin', '在线管理员', '1', null);
INSERT INTO `chaos_resource` VALUES ('8', '1337252580647084034', '2020-12-11 12:27:15', '2020-12-11 12:27:15', '0', '1', '0', '1337252842627506178', '/welcome', '欢迎', '2', '');
INSERT INTO `chaos_resource` VALUES ('9', '1337252842627506178', '2020-12-11 12:28:17', '2020-12-11 12:28:48', '0', '2', '1', '', '', '控制台', '2', 'el-icon-menu');
INSERT INTO `chaos_resource` VALUES ('11', '1363403616281493506', '2021-02-21 16:22:08', '2021-02-21 16:22:08', '0', '1', '0', '100', '/link', '链接', '1', '');
INSERT INTO `chaos_resource` VALUES ('12', '1363404118352265218', '2021-02-21 16:24:07', '2021-03-09 18:32:51', '0', '2', '0', '100', '/dashboard', 'Dashboard', '1', '');
INSERT INTO `chaos_resource` VALUES ('17', '1369226408566571010', '2021-03-09 17:59:49', '2021-03-09 17:59:49', '0', '1', '0', '100', '/welcome', '欢迎', '1', 'el-icon-menu');

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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of chaos_role
-- ----------------------------
INSERT INTO `chaos_role` VALUES ('1', '1253273444540895233', '2020-04-23 18:44:09', '2020-05-17 16:29:50', '0', '3', 'ADMIN', '混沌管理员', '/welcome', '1');
INSERT INTO `chaos_role` VALUES ('2', '1326089091246256130', '2020-11-10 17:07:32', '2021-02-20 15:13:35', '0', '3', 'SV', '服务调用者', '/', '1');
INSERT INTO `chaos_role` VALUES ('3', '1337251670378258433', '2020-12-11 12:23:38', '2020-12-11 12:23:38', '0', '1', 'ADMIN', '超级管理员', '/welcome', '2');

-- ----------------------------
-- Table structure for chaos_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `chaos_role_resource`;
CREATE TABLE `chaos_role_resource` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `mu` varchar(64) NOT NULL,
  `role_mu` varchar(64) NOT NULL,
  `resource_mu` varchar(64) NOT NULL,
  `create_time` datetime NOT NULL,
  `modify_time` datetime NOT NULL,
  `is_delete` tinyint(2) NOT NULL DEFAULT '0',
  `version` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `inx_mu` (`mu`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of chaos_role_resource
-- ----------------------------
INSERT INTO `chaos_role_resource` VALUES ('1', '101', '1253273444540895233', '101', '2020-04-05 13:57:01', '2020-04-05 13:57:01', '0', '1');
INSERT INTO `chaos_role_resource` VALUES ('2', '102', '1253273444540895233', '102', '2020-04-17 18:12:39', '2020-04-17 18:12:42', '0', '1');
INSERT INTO `chaos_role_resource` VALUES ('3', '103', '1253273444540895233', '103', '2020-04-17 18:23:54', '2020-04-17 18:23:57', '0', '1');
INSERT INTO `chaos_role_resource` VALUES ('4', '104', '1253273444540895233', '1253281684146245634', '2020-04-23 19:36:18', '2020-04-23 19:36:20', '0', '1');
INSERT INTO `chaos_role_resource` VALUES ('5', '105', '1253273444540895233', '1255328926126325761', '2020-04-29 10:53:20', '2020-04-29 10:53:23', '0', '1');
INSERT INTO `chaos_role_resource` VALUES ('6', '106', '1253273444540895233', '1255341292318715906', '2020-04-29 11:41:28', '2020-04-29 11:41:30', '0', '1');
INSERT INTO `chaos_role_resource` VALUES ('14', '1363403689467904002', '1253273444540895233', '1363403616281493506', '2021-02-21 16:22:25', '2021-02-21 16:22:25', '0', '1');
INSERT INTO `chaos_role_resource` VALUES ('15', '1363406105596723202', '1253273444540895233', '1363404118352265218', '2021-02-21 16:32:01', '2021-02-21 16:32:01', '0', '1');
INSERT INTO `chaos_role_resource` VALUES ('17', '1369235874783518721', '1253273444540895233', '1369226408566571010', '2021-03-09 18:37:26', '2021-03-09 18:37:26', '0', '1');

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
