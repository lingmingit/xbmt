/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.23 : Database - xbmt_www
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`xbmt_www` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `xbmt_www`;

/*Table structure for table `t_sys_functionaction` */

DROP TABLE IF EXISTS `t_sys_functionaction`;

CREATE TABLE `t_sys_functionaction` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL,
  `createTime` datetime DEFAULT NULL,
  `description` varchar(250) COLLATE utf8_bin DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  `isEnable` bit(1) DEFAULT NULL,
  `isSystem` bit(1) DEFAULT NULL,
  `name` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `numbers` varchar(80) COLLATE utf8_bin NOT NULL,
  `simpleName` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `actionName` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `buttonId` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `creator_id` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `modifier_id` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKD8A18B992B7F6F` (`modifier_id`),
  KEY `FKD8A18B476685FA` (`creator_id`),
  CONSTRAINT `FK1j3rv0xyk7hs9a7q2ollaxu3y` FOREIGN KEY (`creator_id`) REFERENCES `t_sys_user` (`id`),
  CONSTRAINT `FKD8A18B476685FA` FOREIGN KEY (`creator_id`) REFERENCES `t_sys_user` (`id`),
  CONSTRAINT `FKD8A18B992B7F6F` FOREIGN KEY (`modifier_id`) REFERENCES `t_sys_user` (`id`),
  CONSTRAINT `FKlyg21intcbf70gc50wj0j6lp7` FOREIGN KEY (`modifier_id`) REFERENCES `t_sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_sys_functionaction` */

insert  into `t_sys_functionaction`(`id`,`createTime`,`description`,`modifyTime`,`isEnable`,`isSystem`,`name`,`numbers`,`simpleName`,`actionName`,`buttonId`,`creator_id`,`modifier_id`) values ('402896e451479dff015147b8bc4b0000','2015-11-27 14:54:27','','2015-11-27 15:55:09','','\0','新增','001',NULL,'addAction','btnAdd','1','1'),('402896e451479dff015147bb13b30001','2015-11-27 14:57:01','','2015-11-27 15:55:09','','\0','编辑','002',NULL,'editAction','btnEdit','1','1'),('402896e451479dff015147e5fc6c0002','2015-11-27 15:43:53','','2015-11-27 15:55:09','','\0','删除','003',NULL,'actionDelete','btnDelete','1','1'),('402896e451479dff015147ed5cb40003','2015-11-27 15:51:56','','2015-11-27 15:55:09','','\0','启用','004',NULL,'actionEnable','btnEnable','1','1'),('402896e451479dff015147ede1940004','2015-11-27 15:52:30','','2015-11-27 15:55:09','','\0','禁用','005',NULL,'actionUnEnable','btnUnEnable','1','1'),('402896e451479dff015147ee4e4b0005','2015-11-27 15:52:58','','2015-11-27 15:55:09','','\0','保存','006',NULL,'actionSave','btnSave','1','1'),('402896e451479dff015147eeb8b10006','2015-11-27 15:53:25','','2015-11-27 15:55:09','','\0','批量删除','007',NULL,'actionDeletes','btnDeletes','1','1'),('402896e451479dff015147ef7e6f0007','2015-11-27 15:54:16','','2015-11-27 15:55:09','','\0','批量启用','008',NULL,'actionEnables','btnEnables','1','1'),('402896e451479dff015147f0085e0008','2015-11-27 15:54:51','','2015-11-27 15:55:22','\0','\0','批量禁用','009',NULL,'actionUnEnable','btnUnEnable','1','1');

/*Table structure for table `t_sys_menu` */

DROP TABLE IF EXISTS `t_sys_menu`;

CREATE TABLE `t_sys_menu` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL,
  `createTime` datetime DEFAULT NULL,
  `description` varchar(250) COLLATE utf8_bin DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  `isEnable` bit(1) DEFAULT NULL,
  `isSystem` bit(1) DEFAULT NULL,
  `name` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `numbers` varchar(80) COLLATE utf8_bin NOT NULL,
  `simpleName` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `isLeaf` bit(1) NOT NULL,
  `levels` int(11) DEFAULT NULL,
  `longNumber` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `backUrl` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `functionName` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `menuIcon` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `seqNo` int(11) DEFAULT NULL,
  `url` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `creator_id` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `modifier_id` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `module_id` varchar(32) COLLATE utf8_bin NOT NULL,
  `parent_id` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2B50BAFCE4C606FB` (`module_id`),
  KEY `FK2B50BAFC992B7F6F` (`modifier_id`),
  KEY `FK2B50BAFC476685FA` (`creator_id`),
  KEY `FK2B50BAFCAC1E1C49` (`parent_id`),
  CONSTRAINT `FK2B50BAFC476685FA` FOREIGN KEY (`creator_id`) REFERENCES `t_sys_user` (`id`),
  CONSTRAINT `FK2B50BAFC992B7F6F` FOREIGN KEY (`modifier_id`) REFERENCES `t_sys_user` (`id`),
  CONSTRAINT `FK2B50BAFCAC1E1C49` FOREIGN KEY (`parent_id`) REFERENCES `t_sys_menu` (`id`),
  CONSTRAINT `FK2B50BAFCE4C606FB` FOREIGN KEY (`module_id`) REFERENCES `t_sys_modules` (`id`),
  CONSTRAINT `FK82cygmydlmqjx6nxdvo8dwom4` FOREIGN KEY (`creator_id`) REFERENCES `t_sys_user` (`id`),
  CONSTRAINT `FKruq7mkoush2dl06it6oklx73n` FOREIGN KEY (`module_id`) REFERENCES `t_sys_modules` (`id`),
  CONSTRAINT `FKswatlmcaxm0s2gg24plwvkyav` FOREIGN KEY (`modifier_id`) REFERENCES `t_sys_user` (`id`),
  CONSTRAINT `FKt5h06d99mjybjk3p6r8sl8enb` FOREIGN KEY (`parent_id`) REFERENCES `t_sys_menu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_sys_menu` */

insert  into `t_sys_menu`(`id`,`createTime`,`description`,`modifyTime`,`isEnable`,`isSystem`,`name`,`numbers`,`simpleName`,`isLeaf`,`levels`,`longNumber`,`backUrl`,`functionName`,`menuIcon`,`seqNo`,`url`,`creator_id`,`modifier_id`,`module_id`,`parent_id`) values ('402896e451a9e28b0151a9f062c00003','2015-12-16 16:38:02',NULL,'2015-12-16 16:38:02','\0','\0','11','11',NULL,'',1,'11','11',NULL,'',1,'11','1',NULL,'402896e4511ecf4001511ed7e5d40000',NULL),('402896e5522fcdd801522ff88dd70004','2016-01-11 17:16:04',NULL,'2016-01-11 17:16:04','\0','\0','22','22',NULL,'',1,'22','22',NULL,'',22,'22','1',NULL,'402896e4511ecf4001511ed7e5d40000',NULL);

/*Table structure for table `t_sys_menufunaction` */

DROP TABLE IF EXISTS `t_sys_menufunaction`;

CREATE TABLE `t_sys_menufunaction` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL,
  `description` varchar(250) COLLATE utf8_bin DEFAULT NULL,
  `seq` int(11) DEFAULT NULL,
  `actionName` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `buttonIdName` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `action_id` varchar(32) COLLATE utf8_bin NOT NULL,
  `parent_id` varchar(32) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK974D9CF9F920598C` (`action_id`),
  KEY `FK974D9CF9AC1E1C49` (`parent_id`),
  CONSTRAINT `FK974D9CF9AC1E1C49` FOREIGN KEY (`parent_id`) REFERENCES `t_sys_menu` (`id`),
  CONSTRAINT `FK974D9CF9F920598C` FOREIGN KEY (`action_id`) REFERENCES `t_sys_functionaction` (`id`),
  CONSTRAINT `FKe9xqeohk5j2el0sun2m54arq9` FOREIGN KEY (`parent_id`) REFERENCES `t_sys_menu` (`id`),
  CONSTRAINT `FKod6lpua0m8sr8nq56dgsgafoj` FOREIGN KEY (`action_id`) REFERENCES `t_sys_functionaction` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_sys_menufunaction` */

insert  into `t_sys_menufunaction`(`id`,`description`,`seq`,`actionName`,`buttonIdName`,`action_id`,`parent_id`) values ('402896e451a9e28b0151a9f062c40004',NULL,NULL,'11','11','402896e451479dff015147b8bc4b0000','402896e451a9e28b0151a9f062c00003'),('402896e451a9e28b0151a9f062c40005',NULL,NULL,'22','22','402896e451479dff015147bb13b30001','402896e451a9e28b0151a9f062c00003'),('402896e5522fcdd801522ff8ebee0006',NULL,NULL,'33','33','402896e451479dff015147bb13b30001','402896e5522fcdd801522ff88dd70004'),('402896e5522fcdd801522ff986ad0008',NULL,NULL,'55','55','402896e451479dff015147ee4e4b0005','402896e5522fcdd801522ff88dd70004'),('402896e5522fcdd801522ffa7f4e0009',NULL,NULL,'66','66','402896e451479dff015147ef7e6f0007','402896e5522fcdd801522ff88dd70004');

/*Table structure for table `t_sys_modules` */

DROP TABLE IF EXISTS `t_sys_modules`;

CREATE TABLE `t_sys_modules` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL,
  `createTime` datetime DEFAULT NULL,
  `description` varchar(250) COLLATE utf8_bin DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  `isEnable` bit(1) DEFAULT NULL,
  `isSystem` bit(1) DEFAULT NULL,
  `name` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `numbers` varchar(80) COLLATE utf8_bin NOT NULL,
  `simpleName` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `belongSystem` varchar(6) COLLATE utf8_bin DEFAULT NULL,
  `moduleIcon` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `moduleLogoIcon` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `moduleURL` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `seqNo` int(11) DEFAULT NULL,
  `creator_id` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `modifier_id` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB834C0CA992B7F6F` (`modifier_id`),
  KEY `FKB834C0CA476685FA` (`creator_id`),
  CONSTRAINT `FK5rmb6icmiq9i9n7bobijlk03f` FOREIGN KEY (`creator_id`) REFERENCES `t_sys_user` (`id`),
  CONSTRAINT `FKB834C0CA476685FA` FOREIGN KEY (`creator_id`) REFERENCES `t_sys_user` (`id`),
  CONSTRAINT `FKB834C0CA992B7F6F` FOREIGN KEY (`modifier_id`) REFERENCES `t_sys_user` (`id`),
  CONSTRAINT `FKn0poar28900tx8kdpyf0xh864` FOREIGN KEY (`modifier_id`) REFERENCES `t_sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_sys_modules` */

insert  into `t_sys_modules`(`id`,`createTime`,`description`,`modifyTime`,`isEnable`,`isSystem`,`name`,`numbers`,`simpleName`,`belongSystem`,`moduleIcon`,`moduleLogoIcon`,`moduleURL`,`seqNo`,`creator_id`,`modifier_id`) values ('402890c4563b112201563b128af50000','2016-07-30 17:11:26','','2016-07-30 17:43:28','','\0','77','77',NULL,NULL,'',NULL,'',77,'1','1'),('402890c4563b112201563b13567d0001','2016-07-30 17:12:18','','2016-07-30 17:12:18','\0','\0','88','88',NULL,NULL,'',NULL,'',88,'1',NULL),('402890c4563b21c901563b27316e0000','2016-07-30 17:33:59','9991','2016-07-30 17:54:38','\0','\0','9991','999',NULL,NULL,'',NULL,'111',9991,'1','1'),('402890c4563b379301563b38a4d30000','2016-07-30 17:53:03','kk777','2016-07-30 17:53:59','','\0','kk','kk',NULL,NULL,'',NULL,'',7,'1','1'),('402896e4511ecf4001511ed7e5d40000','2015-11-19 16:24:04','test','2015-11-24 16:49:39','','\0','系统管理','001',NULL,NULL,'',NULL,'',1,'1','1'),('402896e4511ecf4001511ed81ab00001','2015-11-19 16:24:17','','2015-11-24 16:49:39','','\0','客户管理','002',NULL,NULL,'',NULL,'',2,'1','1'),('402896e4511ecf4001511ed85fd80002','2015-11-19 16:24:35','','2015-11-24 16:49:39','','\0','供应链管理','003',NULL,NULL,'',NULL,'',3,'1','1'),('402896e451377f83015138446ad90003','2015-11-24 14:53:06','','2015-11-24 16:49:39','','\0','004','004',NULL,NULL,'',NULL,'',4,'1','1'),('402896e451377f8301513844995d0004','2015-11-24 14:53:18','','2016-07-30 17:33:39','\0','\0','005','005',NULL,NULL,'',NULL,'',5,'1','1'),('402896e8562b7fb101562b90b4710001','2016-07-27 16:55:18','','2016-07-27 16:55:18','\0','\0','007','007',NULL,NULL,'',NULL,'',7,'1','1'),('402896e8562b7fb101562b95e6f30002','2016-07-27 17:00:34','','2016-07-27 17:00:59','\0','\0','009','009',NULL,NULL,'',NULL,'',9,'1','1'),('402896e8562b9a2701562b9a93220000','2016-07-27 17:06:05','','2016-07-27 17:07:38','\0','\0','102','100',NULL,NULL,'',NULL,'',102,'1','1'),('402896e85630b127015630b551010000','2016-07-28 16:53:24','','2016-07-28 16:53:24','\0','\0','44','44',NULL,NULL,'',NULL,'',44,'1',NULL),('402896e85630b127015630b589a50001','2016-07-28 16:53:38','','2016-07-28 16:53:38','\0','\0','55','55',NULL,NULL,'',NULL,'',55,'1',NULL);

/*Table structure for table `t_sys_user` */

DROP TABLE IF EXISTS `t_sys_user`;

CREATE TABLE `t_sys_user` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL,
  `createTime` datetime DEFAULT NULL,
  `description` varchar(250) COLLATE utf8_bin DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  `isEnable` bit(1) DEFAULT NULL,
  `isSystem` bit(1) DEFAULT NULL,
  `name` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `numbers` varchar(80) COLLATE utf8_bin NOT NULL,
  `simpleName` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `homeAddress` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `isArchive` bit(1) DEFAULT NULL,
  `isDelete` bit(1) DEFAULT NULL,
  `isLocked` bit(1) DEFAULT NULL,
  `mobilePhone` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `qq` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `sex` varchar(3) COLLATE utf8_bin DEFAULT NULL,
  `telephone` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `userType` varchar(6) COLLATE utf8_bin DEFAULT NULL,
  `creator_id` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `modifier_id` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2B549168992B7F6F` (`modifier_id`),
  KEY `FK2B549168476685FA` (`creator_id`),
  CONSTRAINT `FK2B549168476685FA` FOREIGN KEY (`creator_id`) REFERENCES `t_sys_user` (`id`),
  CONSTRAINT `FK2B549168992B7F6F` FOREIGN KEY (`modifier_id`) REFERENCES `t_sys_user` (`id`),
  CONSTRAINT `FKi56vc5j8vmnssposin36a78a7` FOREIGN KEY (`creator_id`) REFERENCES `t_sys_user` (`id`),
  CONSTRAINT `FKsgqgfs6k9o0c4dtan7kt1y119` FOREIGN KEY (`modifier_id`) REFERENCES `t_sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_sys_user` */

insert  into `t_sys_user`(`id`,`createTime`,`description`,`modifyTime`,`isEnable`,`isSystem`,`name`,`numbers`,`simpleName`,`age`,`birthday`,`email`,`homeAddress`,`isArchive`,`isDelete`,`isLocked`,`mobilePhone`,`password`,`qq`,`sex`,`telephone`,`userType`,`creator_id`,`modifier_id`) values ('1',NULL,NULL,NULL,'','','user','user',NULL,NULL,NULL,NULL,NULL,'\0','\0','\0','13912345678','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,NULL,NULL,NULL,NULL),('402890c4563b11a801563b151f1c0001','2016-07-30 17:14:15','1111','2016-07-30 17:32:09','','\0','zz','zz',NULL,12,'2016-07-13','','12','\0','\0','\0','zz','zz','','1','zz',NULL,'1','1'),('402890c4563b11a801563b1b5e880002','2016-07-30 17:21:04','','2016-07-30 17:29:13','\0','\0','ii','iii',NULL,56,'2016-07-19','','34','\0','\0','\0','ii','ii','','1','',NULL,'1','1'),('402896e4511e66bc01511e83508d0000','2015-11-19 14:51:41','备注信息22','2016-07-30 17:26:56','','\0','测试11','test',NULL,11,'2015-11-11','test11@xbmt.net','11','\0','\0','\0','13912345611','123456','','1','',NULL,'1','1'),('402896e4511e66bc01511e95bfc50001','2015-11-19 15:11:49','sdfsdfdsf','2016-07-30 17:30:09','','\0','啊啊啊','aaa',NULL,34,'2015-11-12','aaa@xbmt.net','水电费','\0','\0','\0','13912345678','aaa','1234567855','1','028-8456711',NULL,'1','1'),('402896e4511e66bc01511e9653040002','2015-11-19 15:12:26','232323','2015-11-19 17:09:53','','\0','不不不','bbb',NULL,23,'2015-11-18','bbb@xbmt.net','四川成都高新区','\0','\0','\0','13912345444','bbb','1234567844','0','021-84567123',NULL,'1','1'),('402896e4511ef88601511efe19b20000','2015-11-19 17:05:47','11','2015-11-19 17:09:48','\0','\0','eee11','eee',NULL,11,'2015-11-19','','eee11','\0','\0','\0','13912345611','12','11111111','0','028-8456711',NULL,'1','1'),('402896e45122881e015122bd90bd0000','2015-11-20 10:33:47','ff','2015-11-20 10:33:47','\0','\0','fff','fff',NULL,56,'2015-11-20','ffff@xbmt.net','fff','\0','\0','\0','13912345622','fff','11111111','0','028-84567123',NULL,'1',NULL),('402896e45123fbce015123fe20870000','2015-11-20 16:23:55','ggg','2015-11-20 16:23:55','\0','\0','ggg','ggg',NULL,99,'2015-11-20','ggg@xbmt.net','ggg','\0','\0','\0','13912345678','ggg','23232323','1','028-84567155',NULL,'1',NULL);

/*Table structure for table `t_tst_test` */

DROP TABLE IF EXISTS `t_tst_test`;

CREATE TABLE `t_tst_test` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL,
  `createTime` datetime DEFAULT NULL,
  `description` varchar(250) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(350) COLLATE utf8_bin DEFAULT NULL,
  `number` varchar(150) COLLATE utf8_bin DEFAULT NULL,
  `seq` int(11) DEFAULT NULL,
  `creator_id` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK57760047476685FA` (`creator_id`),
  CONSTRAINT `FK57760047476685FA` FOREIGN KEY (`creator_id`) REFERENCES `t_sys_user` (`id`),
  CONSTRAINT `FKaa71inqjse8t8iw98bcsmkgj8` FOREIGN KEY (`creator_id`) REFERENCES `t_sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_tst_test` */

insert  into `t_tst_test`(`id`,`createTime`,`description`,`name`,`number`,`seq`,`creator_id`) values ('1',NULL,'111','111','111',11,NULL),('2',NULL,'222','222','222',22,NULL),('3',NULL,'333','333','333',33,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
