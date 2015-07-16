# Host: 192.168.66.10  (Version: 5.6.17)
# Date: 2015-07-16 17:02:09
# Generator: MySQL-Front 5.3  (Build 4.214)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "t_city"
#

DROP TABLE IF EXISTS `t_city`;
CREATE TABLE `t_city` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `city_name` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父节点Id',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

#
# Data for table "t_city"
#

INSERT INTO `t_city` VALUES (1,'上海',NULL),(3,'江苏',NULL),(4,'浙江',NULL),(5,'徐汇区',1),(6,'普陀区',1),(7,'杨浦区',1),(8,'虹口区',1),(9,'浦东新区',1),(10,'闵行区',1),(11,'嘉定区',1),(12,'宝山区',1),(13,'南通市',3),(14,'扬州市',3),(15,'泰州市',3),(16,'徐州市',3),(17,'南京市',3),(18,'苏州市',3),(19,'丽江市',4),(20,'嘉兴市',4),(21,'杭州市',4);

#
# Structure for table "t_evaluate"
#

DROP TABLE IF EXISTS `t_evaluate`;
CREATE TABLE `t_evaluate` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '店铺Id(外键)',
  `evaluate_date` varchar(6) NOT NULL DEFAULT '' COMMENT '评测日期YYYYMM',
  `evaluate_value` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '测评结果值',
  `status_type_code` int(2) NOT NULL DEFAULT '0' COMMENT '评测状态CODE',
  `monthly_sales` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '月销售额',
  `monthly_purchase` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '月采购额',
  `monthly_salary` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '每月工资',
  `monthly_rent` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '每月租金',
  `monthly_energy` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '每月能耗',
  `monthly_other_pay` decimal(10,2) DEFAULT '0.00' COMMENT '其他开销',
  `monthly_group_buy` decimal(10,2) DEFAULT '0.00' COMMENT '团购收入',
  `create_date` datetime DEFAULT NULL COMMENT '记录创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '记录更新时间',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `shop_eval` (`shop_id`,`evaluate_date`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

#
# Data for table "t_evaluate"
#

INSERT INTO `t_evaluate` VALUES (24,18,'201507',24089.00,4,34456.00,444.00,355.00,3566.00,5646.00,356.00,564.00,'2015-07-12 22:39:41','2015-07-13 21:35:40'),(25,19,'201507',56882.00,3,132241.00,22113.00,11341.00,34221.00,2241.00,5443.00,5663.00,'2015-07-12 22:46:19','2015-07-13 21:38:58'),(26,20,'201507',47579.00,4,100000.00,25412.00,8000.00,3333.00,1425.00,14251.00,98524.00,'2015-07-13 08:44:26','2015-07-13 08:44:26');

#
# Structure for table "t_shop"
#

DROP TABLE IF EXISTS `t_shop`;
CREATE TABLE `t_shop` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户Id(外键)',
  `shop_name` varchar(64) NOT NULL DEFAULT '' COMMENT '店铺名',
  `city_id` int(11) NOT NULL DEFAULT '0' COMMENT '城市外键',
  `type_code` int(11) DEFAULT NULL COMMENT '餐厅类型值',
  `pos_count` int(11) NOT NULL DEFAULT '0' COMMENT '餐位数',
  `per_pay` int(10) DEFAULT NULL COMMENT '人均消费',
  `space_size` int(11) DEFAULT NULL COMMENT '前厅面积',
  `food_count` int(11) NOT NULL DEFAULT '0' COMMENT '菜品数量',
  `create_date` datetime DEFAULT NULL COMMENT '记录创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '记录更新时间',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

#
# Data for table "t_shop"
#

INSERT INTO `t_shop` VALUES (18,1,'南大街店',13,5,123,121,313,661,'2015-07-12 22:39:41','2015-07-13 21:35:39'),(19,1,'东方路店',6,4,321,67,446,789,'2015-07-12 22:46:18','2015-07-13 21:38:58'),(20,1,'张三',14,2,100,80,89,58,'2015-07-13 08:44:26','2015-07-13 08:44:26');

#
# Structure for table "t_user"
#

DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(64) DEFAULT NULL COMMENT '微信openid',
  `login_token` varchar(128) DEFAULT NULL COMMENT '用户访问令牌',
  `init_flag` int(1) DEFAULT '0' COMMENT '初始化Flag',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

#
# Data for table "t_user"
#

INSERT INTO `t_user` VALUES (1,'oZf8yty0yxkzviCVi0sVutI95iB8','afa0c7e8-35f7-4a05-936d-2c968491ee11',0,NULL,'2015-07-13 21:35:21');

#
# Structure for table "t_weixin"
#

DROP TABLE IF EXISTS `t_weixin`;
CREATE TABLE `t_weixin` (
  `key_name` varchar(32) NOT NULL DEFAULT '' COMMENT '微信中要持久的名称',
  `hold_val` text COMMENT '保存的微信相关值',
  `create_date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`key_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "t_weixin"
#

INSERT INTO `t_weixin` VALUES ('access_token','uy4zH0HVSdoFanjabU8qTf32unTKblsXIPB6u8dFQGaJI50s5fsFHm6nxjaJMsNKaOnWi6RbDo7PlcBP55lCaOH33yFzM-wM9zTQW4oM8dk','2015-07-16 16:57:22'),('jsapi_ticket','sM4AOVdWfPE4DxkXGEs8VDc15Duc2WQOFGdoReXhXVKilK1ZB9CXneYMDB4WKYHt6h1ORbn_39brRi5XjANqDw','2015-07-16 16:57:22');
