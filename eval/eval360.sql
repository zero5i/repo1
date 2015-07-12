# Host: 192.168.66.10  (Version: 5.6.17)
# Date: 2015-07-12 18:49:03
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
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

#
# Data for table "t_evaluate"
#

INSERT INTO `t_evaluate` VALUES (1,1,'200506',1.00,1,0.00,0.00,0.00,0.00,0.00,0.00,0.00,NULL,NULL),(2,1,'201506',11.00,2,0.00,0.00,0.00,0.00,0.00,0.00,0.00,NULL,NULL),(3,1,'201406',33.00,1,0.00,0.00,0.00,0.00,0.00,0.00,0.00,NULL,NULL),(4,2,'201306',0.00,4,0.00,0.00,0.00,0.00,0.00,0.00,0.00,NULL,NULL),(5,2,'201406',0.00,1,0.00,0.00,0.00,0.00,0.00,0.00,0.00,NULL,NULL),(6,1,'201407',33.00,2,0.00,0.00,0.00,0.00,0.00,0.00,0.00,NULL,NULL),(7,1,'201405',133.00,3,0.00,0.00,0.00,0.00,0.00,0.00,0.00,NULL,NULL),(8,2,'201507',21863.00,3,65211.00,13111.00,11441.00,12241.00,5214.00,1341.00,0.00,'2015-07-05 19:44:05','2015-07-12 13:39:47'),(9,3,'201507',14015.00,3,51234.00,21113.00,11221.00,1122.00,2342.00,1421.00,0.00,'2015-07-05 20:57:58','2015-07-12 18:25:58'),(10,1,'201507',21135.00,3,133421.00,51212.00,41331.00,10121.00,5411.00,4211.00,0.00,'2015-07-05 21:05:30','2015-07-11 21:19:12'),(11,4,'201507',0.00,4,113314.00,61132.00,32112.00,6221.00,3113.00,1231.00,0.00,'2015-07-05 21:44:06','2015-07-11 18:17:20'),(12,5,'201507',4321.00,2,12.00,1.00,0.00,123.00,0.00,0.00,0.00,'2015-07-05 21:58:19','2015-07-05 21:58:19'),(13,6,'201507',4321.00,1,11.00,14.00,83.00,663.00,665.00,74.00,0.00,'2015-07-05 22:04:32','2015-07-05 22:04:32'),(14,7,'201507',4321.00,4,34.00,456.00,876.00,6778.00,686.00,786.00,0.00,'2015-07-05 22:17:24','2015-07-05 22:17:24'),(15,8,'201507',4321.00,1,34354.00,5345.00,5435.00,343.00,22.00,79.00,0.00,'2015-07-05 22:22:53','2015-07-05 22:22:53'),(16,9,'201507',4321.00,3,34354.00,5345.00,5435.00,343.00,22.00,79.00,0.00,'2015-07-05 22:24:00','2015-07-05 22:24:00'),(17,10,'201507',109778.00,4,113134.00,2424.00,234.00,234.00,422.00,42.00,0.00,'2015-07-05 22:31:29','2015-07-12 02:00:40'),(18,11,'201507',-30969.00,3,134211.00,52113.00,32211.00,12341.00,3256.00,3321.00,0.00,'2015-07-07 10:13:28','2015-07-11 21:21:49'),(19,12,'201507',4321.00,1,5.00,6.00,7.00,8.00,9.00,10.00,0.00,'2015-07-08 19:54:22','2015-07-08 19:54:22'),(20,13,'201507',4321.00,2,1.00,2.00,2.00,35.00,5.00,6.00,0.00,'2015-07-09 09:28:28','2015-07-09 09:28:28'),(21,14,'201507',40594.00,3,134111.00,42212.00,31212.00,13331.00,3321.00,3441.00,0.00,'2015-07-10 21:02:02','2015-07-11 23:47:09'),(22,15,'201507',-225000.00,4,350000.00,150000.00,100000.00,300000.00,5000.00,20000.00,NULL,'2015-07-11 22:43:36','2015-07-11 22:43:36');

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
  `create_date` datetime DEFAULT NULL COMMENT '记录创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '记录更新时间',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

#
# Data for table "t_shop"
#

INSERT INTO `t_shop` VALUES (1,1,'徐家汇',7,3,25,70,125,NULL,'2015-07-11 21:19:12'),(2,1,'黄海中路店',0,3,722,63,953,'1899-12-29 00:00:00','2015-07-12 13:39:47'),(3,1,'静安寺店',6,3,22,63,953,'1899-12-29 00:00:00','2015-07-12 18:25:57'),(4,1,'江苏路店',14,2,23,32,123,'2015-07-05 21:44:06','2015-07-11 18:17:20'),(10,1,'江泽民西餐厅',14,3,234,244,113,'2015-07-05 22:31:29','2015-07-12 02:00:40'),(11,1,'上南店',9,1,50,30,120,'2015-07-07 10:13:28','2015-07-11 21:21:49'),(14,1,'王府井饭店',6,0,212,112,133,'2015-07-10 21:02:02','2015-07-11 23:47:09'),(15,1,'东方路店',9,1,100,50,500,'2015-07-11 22:43:36','2015-07-11 22:43:36');

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

INSERT INTO `t_user` VALUES (1,'oZf8yty0yxkzviCVi0sVutI95iB8','3de25300-68e4-4fce-859e-a1f1683440e3',0,NULL,'2015-07-12 18:25:23');

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

INSERT INTO `t_weixin` VALUES ('access_token','abUJO8bsBGEjBWq5JEgj-NXKYSDrcpna45q7RWSOrRhMPVJ31TWhp60Q7yKLZG5DUdwUQuRp6mKYayPZnr0a1-jH6_SH-jwR5Eetmf6hZ70','2015-07-12 17:38:44'),('jsapi_ticket','sM4AOVdWfPE4DxkXGEs8VDc15Duc2WQOFGdoReXhXVLNwyFgBNtlzPWO2U4lcpjoKviMHXaTSqf8BWfq5lScfA','2015-07-12 17:38:44');
