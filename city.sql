CREATE TABLE `city` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `province_id` varchar(20) DEFAULT '' COMMENT '省id',
  `city_name` varchar(20) DEFAULT '' COMMENT '城市名',
  `description` varchar(255) DEFAULT '' COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;