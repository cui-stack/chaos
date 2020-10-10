CREATE TABLE `NewTable` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '物理主键',
  `mu` varchar(64) NOT NULL COMMENT '逻辑主键',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `version` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '版本号',
  `key` varchar(255) NOT NULL,
  `value` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `inx_mu` (`mu`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

