CREATE TABLE `NewTable` (
`id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT ,
`mu`  varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL ,
`create_time`  datetime NOT NULL ,
`modify_time`  datetime NOT NULL ,
`is_delete`  tinyint(2) unsigned NOT NULL DEFAULT 0 ,
`version`  int(11) unsigned NOT NULL DEFAULT 1 ,
PRIMARY KEY (`id`),
UNIQUE KEY `inx_mu` (`mu`) USING BTREE
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

