create table city(
	id VARCHAR(20) NOT null COMMENT '主键' ,
	province_id VARCHAR(20) DEFAULT '' COMMENT '省id',
	city_name VARCHAR(20) DEFAULT '' COMMENT '城市名' ,
	description VARCHAR(255) DEFAULT '' COMMENT '描述',
	PRIMARY key (id) USING BTREE
) ENGINE=INNODB DEFAULT CHARSET=utf8