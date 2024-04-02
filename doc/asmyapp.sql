/*
Navicat MySQL Data Transfer

Source Server         : mysql8
Source Server Version : 80022
Source Host           : localhost:3306
Source Database       : myapp

Target Server Type    : MYSQL
Target Server Version : 80022
File Encoding         : 65001

Date: 2021-05-28 17:04:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `news`
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `news_id` int NOT NULL AUTO_INCREMENT COMMENT '资讯id',
  `news_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '资讯标题',
  `author_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '作者名',
  `header_url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '头像url',
  `comment_count` int DEFAULT NULL COMMENT '评论数',
  `release_date` datetime DEFAULT NULL COMMENT '发布日期',
  `type` int DEFAULT NULL COMMENT '资讯显示类型',
  PRIMARY KEY (`news_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES ('1', '山东高考人数即将突破千万，高考难度进一步加大', '高考直通车', 'https://kqz-edu.oss-cn-beijing.aliyuncs.com/myapp/video/gaokao/gk5.jpeg', '3', '2020-07-31 22:23:00', '1');
INSERT INTO `news` VALUES ('2', '外语口语笔试不再难，最新课程教你快速掌握考研技巧', '爱笑的萌博士', 'https://kqz-edu.oss-cn-beijing.aliyuncs.com/myapp/video/yingyu/yy6.jpeg', '1', '2020-07-31 21:01:17', '2');
INSERT INTO `news` VALUES ('3', '考虫四六级最新资料发布', '考虫考研', 'https://kqz-edu.oss-cn-beijing.aliyuncs.com/myapp/video/yingyu/QQ%E5%9B%BE%E7%89%8720210516060302.png', '6', '2020-07-30 13:11:32', '3');
INSERT INTO `news` VALUES ('4', '行测从55分到75分，只因这份答题技巧', '中公考研', 'https://kqz-edu.oss-cn-beijing.aliyuncs.com/myapp/video/kaogong/kg5.jpeg', '12', '2020-07-30 13:11:32', '3');
INSERT INTO `news` VALUES ('5', '《少年》高考三天倒计时，送给2020年所有高考考生，高考大捷！', '人民日报', 'https://kqz-edu.oss-cn-beijing.aliyuncs.com/myapp/video/gaokao/gk5.jpeg', '4', '2020-08-01 08:22:47', '2');
INSERT INTO `news` VALUES ('6', '高考还有一周到达战场，请大家做好准备', '高考倒计时', 'https://kqz-edu.oss-cn-beijing.aliyuncs.com/myapp/video/gaokao/gk6.jpeg', '7', '2020-07-30 12:48:37', '1');
INSERT INTO `news` VALUES ('7', 'NBA复赛赛况：开拓者加时擒灰熊，太阳胜奇才，魔术“主场”破网', '头条专题', 'https://sf1-ttcdn-tos.pstatp.com/img/mosaic-legacy/ffbc0000ad1e717b76a6~120x256.image', '23', '2020-08-01 06:49:44', '1');
INSERT INTO `news` VALUES ('8', 'NBA最有含金量总冠军？奥拉朱旺95年4次以下克上！无冠军超过2次', '网罗篮球', 'https://sf6-ttcdn-tos.pstatp.com/img/pgc-image/9f11654ff6184afd8941bcf7ccd3c5dd~120x256.image', '45', '2020-05-23 14:08:09', '2');

-- ----------------------------
-- Table structure for `news_thumb`
-- ----------------------------
DROP TABLE IF EXISTS `news_thumb`;
CREATE TABLE `news_thumb` (
  `thumb_id` int NOT NULL AUTO_INCREMENT COMMENT '缩略图id',
  `thumb_url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '缩略图url',
  `news_id` int DEFAULT NULL COMMENT '资讯id',
  PRIMARY KEY (`thumb_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of news_thumb
-- ----------------------------
INSERT INTO `news_thumb` VALUES ('1', 'http://p1-tt.byteimg.com/large/pgc-image/S6KR5958Y5X2Qt?from=pc', '1');
INSERT INTO `news_thumb` VALUES ('2', 'http://p1-tt.byteimg.com/large/pgc-image/714415d37865444ca2bef51eb1706cda?from=pc', '2');
INSERT INTO `news_thumb` VALUES ('3', 'http://p1-tt.byteimg.com/large/pgc-image/33b9831739764bdb8a157efce048ec85?from=pc', '2');
INSERT INTO `news_thumb` VALUES ('4', 'http://p3-tt.byteimg.com/large/pgc-image/c8a4e737b54d41c1a84722fc1c6d191d?from=pc', '2');
INSERT INTO `news_thumb` VALUES ('5', 'http://p6-tt.byteimg.com/large/pgc-image/S6CLixgC4HSrXD?from=pc', '3');
INSERT INTO `news_thumb` VALUES ('6', 'http://p3-tt.byteimg.com/large/pgc-image/02973348d57d4dfba2d001f82caa3fcc?from=pc', '4');
INSERT INTO `news_thumb` VALUES ('7', 'http://p1-tt.byteimg.com/large/pgc-image/a456c50fff344122b1b20ed99026c3f8?from=pc', '5');
INSERT INTO `news_thumb` VALUES ('8', 'http://p3-tt.byteimg.com/large/pgc-image/02973348d57d4dfba2d001f82caa3fcc?from=pc', '5');
INSERT INTO `news_thumb` VALUES ('9', 'http://p1-tt.byteimg.com/large/pgc-image/7add3e2a4f754d0baccc607cde132b5f?from=pc', '5');
INSERT INTO `news_thumb` VALUES ('10', 'http://p1-tt.byteimg.com/large/pgc-image/b957bfacdd134aa9a1a7e47d40d1be4b?from=pc', '6');
INSERT INTO `news_thumb` VALUES ('11', 'https://p3.pstatp.com/list/190x124/pgc-image/2b5f07505b67498e83e2faa32d637e5c', '7');
INSERT INTO `news_thumb` VALUES ('12', 'https://p3.pstatp.com/list/190x124/pgc-image/2b5f07505b67498e83e2faa32d637e5c', '8');
INSERT INTO `news_thumb` VALUES ('13', 'https://p3.pstatp.com/list/190x124/pgc-image/2b5f07505b67498e83e2faa32d637e5c', '8');
INSERT INTO `news_thumb` VALUES ('14', 'https://p3.pstatp.com/list/190x124/pgc-image/2b5f07505b67498e83e2faa32d637e5c', '8');

-- ----------------------------
-- Table structure for `qrtz_blob_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_calendars`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_cron_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CRON_EXPRESSION` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TIME_ZONE_ID` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers` VALUES ('RenrenScheduler', 'TASK_1', 'DEFAULT', '0 0/30 * * * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for `qrtz_fired_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ENTRY_ID` varchar(95) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `FIRED_TIME` bigint NOT NULL,
  `SCHED_TIME` bigint NOT NULL,
  `PRIORITY` int NOT NULL,
  `STATE` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`) USING BTREE,
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`) USING BTREE,
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`) USING BTREE,
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_job_details`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_DURABLE` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_UPDATE_DATA` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`) USING BTREE,
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('RenrenScheduler', 'TASK_1', 'DEFAULT', null, 'io.renren.modules.job.utils.ScheduleJob', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597372002E696F2E72656E72656E2E6D6F64756C65732E6A6F622E656E746974792E5363686564756C654A6F62456E7469747900000000000000010200074C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C0006706172616D7371007E00094C000672656D61726B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B7870740008746573745461736B7372000E6A6176612E7574696C2E44617465686A81014B5974190300007870770800000170DC7532287874000E3020302F3330202A202A202A203F7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B0200007870000000000000000174000672656E72656E74000CE58F82E695B0E6B58BE8AF95737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E0013000000007800);

-- ----------------------------
-- Table structure for `qrtz_locks`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `LOCK_NAME` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('RenrenScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('RenrenScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for `qrtz_paused_trigger_grps`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_scheduler_state`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `LAST_CHECKIN_TIME` bigint NOT NULL,
  `CHECKIN_INTERVAL` bigint NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('RenrenScheduler', 'iZk0etjcnmc7vlZ1621360641484', '1622192699500', '15000');

-- ----------------------------
-- Table structure for `qrtz_simple_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `REPEAT_COUNT` bigint NOT NULL,
  `REPEAT_INTERVAL` bigint NOT NULL,
  `TIMES_TRIGGERED` bigint NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_simprop_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `STR_PROP_1` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `STR_PROP_2` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `STR_PROP_3` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `INT_PROP_1` int DEFAULT NULL,
  `INT_PROP_2` int DEFAULT NULL,
  `LONG_PROP_1` bigint DEFAULT NULL,
  `LONG_PROP_2` bigint DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint DEFAULT NULL,
  `PREV_FIRE_TIME` bigint DEFAULT NULL,
  `PRIORITY` int DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_TYPE` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `START_TIME` bigint NOT NULL,
  `END_TIME` bigint DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `MISFIRE_INSTR` smallint DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`) USING BTREE,
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`) USING BTREE,
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`) USING BTREE,
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`) USING BTREE,
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`) USING BTREE,
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('RenrenScheduler', 'TASK_1', 'DEFAULT', 'TASK_1', 'DEFAULT', null, '1622194200000', '1622192400000', '5', 'WAITING', 'CRON', '1584246824000', '0', null, '2', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597372002E696F2E72656E72656E2E6D6F64756C65732E6A6F622E656E746974792E5363686564756C654A6F62456E7469747900000000000000010200074C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C0006706172616D7371007E00094C000672656D61726B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B7870740008746573745461736B7372000E6A6176612E7574696C2E44617465686A81014B5974190300007870770800000170DC7532287874000E3020302F3330202A202A202A203F7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B0200007870000000000000000174000672656E72656E74000CE58F82E695B0E6B58BE8AF95737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E0013000000007800);

-- ----------------------------
-- Table structure for `schedule_job`
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job`;
CREATE TABLE `schedule_job` (
  `job_id` bigint NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `bean_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'spring bean名称',
  `params` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '参数',
  `cron_expression` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'cron表达式',
  `status` tinyint DEFAULT NULL COMMENT '任务状态  0：正常  1：暂停',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='定时任务';

-- ----------------------------
-- Records of schedule_job
-- ----------------------------
INSERT INTO `schedule_job` VALUES ('1', 'testTask', 'renren', '0 0/30 * * * ?', '0', '参数测试', '2020-03-15 12:30:33');

-- ----------------------------
-- Table structure for `schedule_job_log`
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job_log`;
CREATE TABLE `schedule_job_log` (
  `log_id` bigint NOT NULL AUTO_INCREMENT COMMENT '任务日志id',
  `job_id` bigint NOT NULL COMMENT '任务id',
  `bean_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'spring bean名称',
  `params` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '参数',
  `status` tinyint NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `error` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '失败信息',
  `times` int NOT NULL COMMENT '耗时(单位：毫秒)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`log_id`) USING BTREE,
  KEY `job_id` (`job_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=793 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='定时任务日志';

-- ----------------------------
-- Records of schedule_job_log
-- ----------------------------
INSERT INTO `schedule_job_log` VALUES ('1', '1', 'testTask', 'renren', '0', null, '0', '2020-03-15 15:30:00');
INSERT INTO `schedule_job_log` VALUES ('2', '1', 'testTask', 'renren', '0', null, '1', '2020-03-15 16:00:00');
INSERT INTO `schedule_job_log` VALUES ('3', '1', 'testTask', 'renren', '0', null, '1', '2020-03-15 17:30:00');
INSERT INTO `schedule_job_log` VALUES ('4', '1', 'testTask', 'renren', '0', null, '1', '2020-03-15 18:00:00');
INSERT INTO `schedule_job_log` VALUES ('5', '1', 'testTask', 'renren', '0', null, '1', '2020-03-15 19:30:00');
INSERT INTO `schedule_job_log` VALUES ('6', '1', 'testTask', 'renren', '0', null, '5', '2020-03-15 20:00:00');
INSERT INTO `schedule_job_log` VALUES ('7', '1', 'testTask', 'renren', '0', null, '1', '2020-03-15 20:30:00');
INSERT INTO `schedule_job_log` VALUES ('8', '1', 'testTask', 'renren', '0', null, '2', '2020-03-15 21:00:00');
INSERT INTO `schedule_job_log` VALUES ('9', '1', 'testTask', 'renren', '0', null, '1', '2020-03-15 21:30:00');
INSERT INTO `schedule_job_log` VALUES ('10', '1', 'testTask', 'renren', '0', null, '4', '2020-03-15 22:00:00');
INSERT INTO `schedule_job_log` VALUES ('11', '1', 'testTask', 'renren', '0', null, '0', '2020-03-15 23:00:00');
INSERT INTO `schedule_job_log` VALUES ('12', '1', 'testTask', 'renren', '0', null, '1', '2020-03-16 10:00:00');
INSERT INTO `schedule_job_log` VALUES ('13', '1', 'testTask', 'renren', '0', null, '1', '2020-03-17 16:00:00');
INSERT INTO `schedule_job_log` VALUES ('14', '1', 'testTask', 'renren', '0', null, '1', '2020-03-17 16:30:00');
INSERT INTO `schedule_job_log` VALUES ('15', '1', 'testTask', 'renren', '0', null, '1', '2020-03-17 17:00:00');
INSERT INTO `schedule_job_log` VALUES ('16', '1', 'testTask', 'renren', '0', null, '2', '2020-03-17 17:30:00');
INSERT INTO `schedule_job_log` VALUES ('17', '1', 'testTask', 'renren', '0', null, '3', '2020-03-17 18:00:00');
INSERT INTO `schedule_job_log` VALUES ('18', '1', 'testTask', 'renren', '0', null, '3', '2020-03-17 18:30:00');
INSERT INTO `schedule_job_log` VALUES ('19', '1', 'testTask', 'renren', '0', null, '3', '2020-03-17 19:00:00');
INSERT INTO `schedule_job_log` VALUES ('20', '1', 'testTask', 'renren', '0', null, '0', '2020-03-22 15:30:00');
INSERT INTO `schedule_job_log` VALUES ('21', '1', 'testTask', 'renren', '0', null, '1', '2020-03-22 16:00:00');
INSERT INTO `schedule_job_log` VALUES ('22', '1', 'testTask', 'renren', '0', null, '0', '2020-04-20 12:00:00');
INSERT INTO `schedule_job_log` VALUES ('23', '1', 'testTask', 'renren', '0', null, '2', '2020-04-20 12:30:00');
INSERT INTO `schedule_job_log` VALUES ('24', '1', 'testTask', 'renren', '0', null, '0', '2020-04-20 13:00:00');
INSERT INTO `schedule_job_log` VALUES ('25', '1', 'testTask', 'renren', '0', null, '1', '2020-04-20 13:30:00');
INSERT INTO `schedule_job_log` VALUES ('26', '1', 'testTask', 'renren', '0', null, '1', '2020-04-20 14:30:00');
INSERT INTO `schedule_job_log` VALUES ('27', '1', 'testTask', 'renren', '0', null, '1', '2020-04-20 15:00:00');
INSERT INTO `schedule_job_log` VALUES ('28', '1', 'testTask', 'renren', '0', null, '1', '2020-04-20 15:30:00');
INSERT INTO `schedule_job_log` VALUES ('29', '1', 'testTask', 'renren', '0', null, '1', '2020-05-05 21:30:00');
INSERT INTO `schedule_job_log` VALUES ('30', '1', 'testTask', 'renren', '0', null, '1', '2020-05-05 22:00:00');
INSERT INTO `schedule_job_log` VALUES ('31', '1', 'testTask', 'renren', '0', null, '1', '2020-05-14 23:00:00');
INSERT INTO `schedule_job_log` VALUES ('32', '1', 'testTask', 'renren', '0', null, '1', '2020-05-14 23:30:00');
INSERT INTO `schedule_job_log` VALUES ('33', '1', 'testTask', 'renren', '0', null, '28', '2020-05-15 00:00:00');
INSERT INTO `schedule_job_log` VALUES ('34', '1', 'testTask', 'renren', '0', null, '2', '2020-05-15 00:30:00');
INSERT INTO `schedule_job_log` VALUES ('35', '1', 'testTask', 'renren', '0', null, '2', '2020-06-08 23:30:00');
INSERT INTO `schedule_job_log` VALUES ('36', '1', 'testTask', 'renren', '0', null, '14', '2020-06-09 00:00:00');
INSERT INTO `schedule_job_log` VALUES ('37', '1', 'testTask', 'renren', '0', null, '2', '2020-06-09 00:30:00');
INSERT INTO `schedule_job_log` VALUES ('38', '1', 'testTask', 'renren', '0', null, '1', '2020-06-11 15:00:00');
INSERT INTO `schedule_job_log` VALUES ('39', '1', 'testTask', 'renren', '0', null, '1', '2020-06-13 18:30:00');
INSERT INTO `schedule_job_log` VALUES ('40', '1', 'testTask', 'renren', '0', null, '1', '2020-06-13 19:00:00');
INSERT INTO `schedule_job_log` VALUES ('41', '1', 'testTask', 'renren', '0', null, '1', '2020-06-14 13:30:00');
INSERT INTO `schedule_job_log` VALUES ('42', '1', 'testTask', 'renren', '0', null, '1', '2020-06-14 14:00:00');
INSERT INTO `schedule_job_log` VALUES ('43', '1', 'testTask', 'renren', '0', null, '0', '2020-06-14 14:30:00');
INSERT INTO `schedule_job_log` VALUES ('44', '1', 'testTask', 'renren', '0', null, '1', '2020-06-18 21:00:00');
INSERT INTO `schedule_job_log` VALUES ('45', '1', 'testTask', 'renren', '0', null, '1', '2020-06-18 21:30:00');
INSERT INTO `schedule_job_log` VALUES ('46', '1', 'testTask', 'renren', '0', null, '4', '2020-06-18 22:00:00');
INSERT INTO `schedule_job_log` VALUES ('47', '1', 'testTask', 'renren', '0', null, '2', '2020-06-18 22:30:00');
INSERT INTO `schedule_job_log` VALUES ('48', '1', 'testTask', 'renren', '0', null, '1', '2020-06-18 23:00:00');
INSERT INTO `schedule_job_log` VALUES ('49', '1', 'testTask', 'renren', '0', null, '1', '2020-06-18 23:30:00');
INSERT INTO `schedule_job_log` VALUES ('50', '1', 'testTask', 'renren', '0', null, '13', '2020-06-19 00:00:00');
INSERT INTO `schedule_job_log` VALUES ('51', '1', 'testTask', 'renren', '0', null, '2', '2020-06-19 00:30:00');
INSERT INTO `schedule_job_log` VALUES ('52', '1', 'testTask', 'renren', '0', null, '0', '2020-06-19 09:00:00');
INSERT INTO `schedule_job_log` VALUES ('53', '1', 'testTask', 'renren', '0', null, '0', '2020-06-19 22:30:00');
INSERT INTO `schedule_job_log` VALUES ('54', '1', 'testTask', 'renren', '0', null, '2', '2020-06-19 23:00:00');
INSERT INTO `schedule_job_log` VALUES ('55', '1', 'testTask', 'renren', '0', null, '0', '2020-06-20 17:30:00');
INSERT INTO `schedule_job_log` VALUES ('56', '1', 'testTask', 'renren', '0', null, '1', '2020-06-20 18:00:00');
INSERT INTO `schedule_job_log` VALUES ('57', '1', 'testTask', 'renren', '0', null, '1', '2020-06-20 18:30:00');
INSERT INTO `schedule_job_log` VALUES ('58', '1', 'testTask', 'renren', '0', null, '1', '2020-06-20 19:00:00');
INSERT INTO `schedule_job_log` VALUES ('59', '1', 'testTask', 'renren', '0', null, '7', '2020-06-26 23:00:00');
INSERT INTO `schedule_job_log` VALUES ('60', '1', 'testTask', 'renren', '0', null, '1', '2020-06-26 23:30:00');
INSERT INTO `schedule_job_log` VALUES ('61', '1', 'testTask', 'renren', '0', null, '24', '2020-06-27 00:00:00');
INSERT INTO `schedule_job_log` VALUES ('62', '1', 'testTask', 'renren', '0', null, '2', '2020-06-27 12:00:00');
INSERT INTO `schedule_job_log` VALUES ('63', '1', 'testTask', 'renren', '0', null, '1', '2020-06-27 12:30:00');
INSERT INTO `schedule_job_log` VALUES ('64', '1', 'testTask', 'renren', '0', null, '1', '2020-06-27 13:00:00');
INSERT INTO `schedule_job_log` VALUES ('65', '1', 'testTask', 'renren', '0', null, '2', '2020-06-27 13:30:00');
INSERT INTO `schedule_job_log` VALUES ('66', '1', 'testTask', 'renren', '0', null, '1', '2020-07-05 20:30:00');
INSERT INTO `schedule_job_log` VALUES ('67', '1', 'testTask', 'renren', '0', null, '2', '2020-07-05 21:00:00');
INSERT INTO `schedule_job_log` VALUES ('68', '1', 'testTask', 'renren', '0', null, '1', '2020-07-05 21:30:00');
INSERT INTO `schedule_job_log` VALUES ('69', '1', 'testTask', 'renren', '0', null, '1', '2020-07-05 22:00:00');
INSERT INTO `schedule_job_log` VALUES ('70', '1', 'testTask', 'renren', '0', null, '1', '2020-07-05 22:30:00');
INSERT INTO `schedule_job_log` VALUES ('71', '1', 'testTask', 'renren', '0', null, '1', '2020-07-06 21:30:00');
INSERT INTO `schedule_job_log` VALUES ('72', '1', 'testTask', 'renren', '0', null, '1', '2020-07-06 22:00:00');
INSERT INTO `schedule_job_log` VALUES ('73', '1', 'testTask', 'renren', '0', null, '1', '2020-07-06 23:00:00');
INSERT INTO `schedule_job_log` VALUES ('74', '1', 'testTask', 'renren', '0', null, '0', '2020-07-06 23:30:00');
INSERT INTO `schedule_job_log` VALUES ('75', '1', 'testTask', 'renren', '0', null, '7', '2020-07-07 00:00:00');
INSERT INTO `schedule_job_log` VALUES ('76', '1', 'testTask', 'renren', '0', null, '2', '2020-07-07 00:30:00');
INSERT INTO `schedule_job_log` VALUES ('77', '1', 'testTask', 'renren', '0', null, '1', '2020-07-07 22:00:00');
INSERT INTO `schedule_job_log` VALUES ('78', '1', 'testTask', 'renren', '0', null, '2', '2020-07-07 22:30:00');
INSERT INTO `schedule_job_log` VALUES ('79', '1', 'testTask', 'renren', '0', null, '2', '2020-07-07 23:00:00');
INSERT INTO `schedule_job_log` VALUES ('80', '1', 'testTask', 'renren', '0', null, '0', '2020-07-07 23:30:00');
INSERT INTO `schedule_job_log` VALUES ('81', '1', 'testTask', 'renren', '0', null, '7', '2020-07-08 00:00:00');
INSERT INTO `schedule_job_log` VALUES ('82', '1', 'testTask', 'renren', '0', null, '2', '2020-07-08 00:30:00');
INSERT INTO `schedule_job_log` VALUES ('83', '1', 'testTask', 'renren', '0', null, '1', '2020-07-08 01:00:00');
INSERT INTO `schedule_job_log` VALUES ('84', '1', 'testTask', 'renren', '0', null, '6', '2020-07-13 00:00:00');
INSERT INTO `schedule_job_log` VALUES ('85', '1', 'testTask', 'renren', '0', null, '1', '2020-07-13 00:30:00');
INSERT INTO `schedule_job_log` VALUES ('86', '1', 'testTask', 'renren', '0', null, '0', '2020-07-14 23:30:00');
INSERT INTO `schedule_job_log` VALUES ('87', '1', 'testTask', 'renren', '0', null, '5', '2020-07-15 00:00:00');
INSERT INTO `schedule_job_log` VALUES ('88', '1', 'testTask', 'renren', '0', null, '0', '2020-07-15 00:30:00');
INSERT INTO `schedule_job_log` VALUES ('89', '1', 'testTask', 'renren', '0', null, '0', '2020-07-15 22:30:00');
INSERT INTO `schedule_job_log` VALUES ('90', '1', 'testTask', 'renren', '0', null, '1', '2020-07-15 23:00:00');
INSERT INTO `schedule_job_log` VALUES ('91', '1', 'testTask', 'renren', '0', null, '1', '2020-07-15 23:30:00');
INSERT INTO `schedule_job_log` VALUES ('92', '1', 'testTask', 'renren', '0', null, '2', '2020-07-19 12:00:00');
INSERT INTO `schedule_job_log` VALUES ('93', '1', 'testTask', 'renren', '0', null, '0', '2020-07-19 12:30:00');
INSERT INTO `schedule_job_log` VALUES ('94', '1', 'testTask', 'renren', '0', null, '1', '2020-07-19 13:00:00');
INSERT INTO `schedule_job_log` VALUES ('95', '1', 'testTask', 'renren', '0', null, '1', '2020-07-19 13:30:00');
INSERT INTO `schedule_job_log` VALUES ('96', '1', 'testTask', 'renren', '0', null, '0', '2020-07-19 14:00:00');
INSERT INTO `schedule_job_log` VALUES ('97', '1', 'testTask', 'renren', '0', null, '0', '2020-07-19 14:30:00');
INSERT INTO `schedule_job_log` VALUES ('98', '1', 'testTask', 'renren', '0', null, '2', '2020-07-19 15:00:00');
INSERT INTO `schedule_job_log` VALUES ('99', '1', 'testTask', 'renren', '0', null, '1', '2020-07-19 15:30:00');
INSERT INTO `schedule_job_log` VALUES ('100', '1', 'testTask', 'renren', '0', null, '0', '2020-07-19 16:30:00');
INSERT INTO `schedule_job_log` VALUES ('101', '1', 'testTask', 'renren', '0', null, '1', '2020-07-19 17:00:00');
INSERT INTO `schedule_job_log` VALUES ('102', '1', 'testTask', 'renren', '0', null, '1', '2020-07-19 17:30:00');
INSERT INTO `schedule_job_log` VALUES ('103', '1', 'testTask', 'renren', '0', null, '1', '2020-07-19 18:00:00');
INSERT INTO `schedule_job_log` VALUES ('104', '1', 'testTask', 'renren', '0', null, '1', '2020-07-19 21:00:00');
INSERT INTO `schedule_job_log` VALUES ('105', '1', 'testTask', 'renren', '0', null, '1', '2020-07-19 21:30:00');
INSERT INTO `schedule_job_log` VALUES ('106', '1', 'testTask', 'renren', '0', null, '1', '2020-07-19 22:00:00');
INSERT INTO `schedule_job_log` VALUES ('107', '1', 'testTask', 'renren', '0', null, '0', '2020-07-19 22:30:00');
INSERT INTO `schedule_job_log` VALUES ('108', '1', 'testTask', 'renren', '0', null, '1', '2020-07-19 23:00:00');
INSERT INTO `schedule_job_log` VALUES ('109', '1', 'testTask', 'renren', '0', null, '1', '2020-07-19 23:30:00');
INSERT INTO `schedule_job_log` VALUES ('110', '1', 'testTask', 'renren', '0', null, '33', '2020-07-20 00:00:00');
INSERT INTO `schedule_job_log` VALUES ('111', '1', 'testTask', 'renren', '0', null, '2', '2020-07-29 16:00:00');
INSERT INTO `schedule_job_log` VALUES ('112', '1', 'testTask', 'renren', '0', null, '2', '2020-08-01 00:30:00');
INSERT INTO `schedule_job_log` VALUES ('113', '1', 'testTask', 'renren', '0', null, '1', '2020-08-01 01:00:00');
INSERT INTO `schedule_job_log` VALUES ('114', '1', 'testTask', 'renren', '0', null, '1', '2020-08-01 01:30:00');
INSERT INTO `schedule_job_log` VALUES ('115', '1', 'testTask', 'renren', '0', null, '0', '2020-08-01 11:00:00');
INSERT INTO `schedule_job_log` VALUES ('116', '1', 'testTask', 'renren', '0', null, '1', '2020-08-01 11:30:00');
INSERT INTO `schedule_job_log` VALUES ('117', '1', 'testTask', 'renren', '0', null, '1', '2020-08-01 12:00:00');
INSERT INTO `schedule_job_log` VALUES ('118', '1', 'testTask', 'renren', '0', null, '0', '2020-08-01 12:30:00');
INSERT INTO `schedule_job_log` VALUES ('119', '1', 'testTask', 'renren', '0', null, '1', '2020-08-01 13:00:00');
INSERT INTO `schedule_job_log` VALUES ('120', '1', 'testTask', 'renren', '0', null, '0', '2020-08-01 13:30:00');
INSERT INTO `schedule_job_log` VALUES ('121', '1', 'testTask', 'renren', '0', null, '0', '2020-08-01 14:00:00');
INSERT INTO `schedule_job_log` VALUES ('122', '1', 'testTask', 'renren', '0', null, '1', '2020-08-01 14:30:00');
INSERT INTO `schedule_job_log` VALUES ('123', '1', 'testTask', 'renren', '0', null, '0', '2020-08-01 15:00:00');
INSERT INTO `schedule_job_log` VALUES ('124', '1', 'testTask', 'renren', '0', null, '1', '2020-08-01 15:30:00');
INSERT INTO `schedule_job_log` VALUES ('125', '1', 'testTask', 'renren', '0', null, '1', '2020-08-01 16:30:00');
INSERT INTO `schedule_job_log` VALUES ('126', '1', 'testTask', 'renren', '0', null, '0', '2020-08-02 14:00:00');
INSERT INTO `schedule_job_log` VALUES ('127', '1', 'testTask', 'renren', '0', null, '1', '2020-08-02 14:30:00');
INSERT INTO `schedule_job_log` VALUES ('128', '1', 'testTask', 'renren', '0', null, '1', '2020-08-02 15:00:00');
INSERT INTO `schedule_job_log` VALUES ('129', '1', 'testTask', 'renren', '0', null, '1', '2020-08-02 15:30:00');
INSERT INTO `schedule_job_log` VALUES ('130', '1', 'testTask', 'renren', '0', null, '1', '2020-08-02 16:00:00');
INSERT INTO `schedule_job_log` VALUES ('131', '1', 'testTask', 'renren', '0', null, '1', '2020-08-02 16:30:00');
INSERT INTO `schedule_job_log` VALUES ('132', '1', 'testTask', 'renren', '0', null, '1', '2020-08-02 17:00:00');
INSERT INTO `schedule_job_log` VALUES ('133', '1', 'testTask', 'renren', '0', null, '2', '2020-08-02 17:30:00');
INSERT INTO `schedule_job_log` VALUES ('134', '1', 'testTask', 'renren', '0', null, '1', '2020-08-03 22:30:00');
INSERT INTO `schedule_job_log` VALUES ('135', '1', 'testTask', 'renren', '0', null, '0', '2020-08-03 23:00:00');
INSERT INTO `schedule_job_log` VALUES ('136', '1', 'testTask', 'renren', '0', null, '1', '2020-08-03 23:30:00');
INSERT INTO `schedule_job_log` VALUES ('137', '1', 'testTask', 'renren', '0', null, '1', '2020-08-05 22:00:00');
INSERT INTO `schedule_job_log` VALUES ('138', '1', 'testTask', 'renren', '0', null, '1', '2020-08-05 23:00:00');
INSERT INTO `schedule_job_log` VALUES ('139', '1', 'testTask', 'renren', '0', null, '0', '2020-08-05 23:30:00');
INSERT INTO `schedule_job_log` VALUES ('140', '1', 'testTask', 'renren', '0', null, '4', '2020-08-06 00:00:00');
INSERT INTO `schedule_job_log` VALUES ('141', '1', 'testTask', 'renren', '0', null, '1', '2020-08-06 00:30:00');
INSERT INTO `schedule_job_log` VALUES ('142', '1', 'testTask', 'renren', '0', null, '0', '2020-08-11 09:30:00');
INSERT INTO `schedule_job_log` VALUES ('143', '1', 'testTask', 'renren', '0', null, '1', '2020-08-11 10:00:00');
INSERT INTO `schedule_job_log` VALUES ('144', '1', 'testTask', 'renren', '0', null, '2', '2020-08-16 22:30:00');
INSERT INTO `schedule_job_log` VALUES ('145', '1', 'testTask', 'renren', '0', null, '0', '2020-08-16 23:00:00');
INSERT INTO `schedule_job_log` VALUES ('146', '1', 'testTask', 'renren', '0', null, '2', '2020-08-16 23:30:00');
INSERT INTO `schedule_job_log` VALUES ('147', '1', 'testTask', 'renren', '0', null, '18', '2020-08-17 00:00:00');
INSERT INTO `schedule_job_log` VALUES ('148', '1', 'testTask', 'renren', '0', null, '1', '2020-08-17 00:30:00');
INSERT INTO `schedule_job_log` VALUES ('149', '1', 'testTask', 'renren', '0', null, '0', '2020-08-20 16:00:00');
INSERT INTO `schedule_job_log` VALUES ('150', '1', 'testTask', 'renren', '0', null, '2', '2020-08-20 16:30:00');
INSERT INTO `schedule_job_log` VALUES ('151', '1', 'testTask', 'renren', '0', null, '1', '2020-08-20 17:00:00');
INSERT INTO `schedule_job_log` VALUES ('152', '1', 'testTask', 'renren', '0', null, '0', '2020-08-20 17:30:00');
INSERT INTO `schedule_job_log` VALUES ('153', '1', 'testTask', 'renren', '0', null, '1', '2020-08-26 17:30:00');
INSERT INTO `schedule_job_log` VALUES ('154', '1', 'testTask', 'renren', '0', null, '0', '2020-09-06 18:00:00');
INSERT INTO `schedule_job_log` VALUES ('155', '1', 'testTask', 'renren', '0', null, '1', '2020-09-06 18:30:00');
INSERT INTO `schedule_job_log` VALUES ('156', '1', 'testTask', 'renren', '0', null, '0', '2020-09-06 19:00:00');
INSERT INTO `schedule_job_log` VALUES ('157', '1', 'testTask', 'renren', '0', null, '1', '2020-09-06 19:30:00');
INSERT INTO `schedule_job_log` VALUES ('158', '1', 'testTask', 'renren', '0', null, '0', '2020-09-06 20:00:00');
INSERT INTO `schedule_job_log` VALUES ('159', '1', 'testTask', 'renren', '0', null, '1', '2020-09-06 20:30:00');
INSERT INTO `schedule_job_log` VALUES ('160', '1', 'testTask', 'renren', '0', null, '1', '2020-09-06 21:00:00');
INSERT INTO `schedule_job_log` VALUES ('161', '1', 'testTask', 'renren', '0', null, '0', '2020-09-06 21:30:00');
INSERT INTO `schedule_job_log` VALUES ('162', '1', 'testTask', 'renren', '0', null, '0', '2020-09-06 22:00:00');
INSERT INTO `schedule_job_log` VALUES ('163', '1', 'testTask', 'renren', '0', null, '1', '2020-09-06 22:30:00');
INSERT INTO `schedule_job_log` VALUES ('164', '1', 'testTask', 'renren', '0', null, '1', '2020-09-06 23:00:00');
INSERT INTO `schedule_job_log` VALUES ('165', '1', 'testTask', 'renren', '0', null, '1', '2020-09-16 22:00:00');
INSERT INTO `schedule_job_log` VALUES ('166', '1', 'testTask', 'renren', '0', null, '1', '2020-09-16 22:30:00');
INSERT INTO `schedule_job_log` VALUES ('167', '1', 'testTask', 'renren', '0', null, '2', '2020-09-16 23:00:00');
INSERT INTO `schedule_job_log` VALUES ('168', '1', 'testTask', 'renren', '0', null, '2', '2020-09-16 23:30:00');
INSERT INTO `schedule_job_log` VALUES ('169', '1', 'testTask', 'renren', '0', null, '1', '2020-09-17 00:30:00');
INSERT INTO `schedule_job_log` VALUES ('170', '1', 'testTask', 'renren', '0', null, '0', '2020-09-28 10:00:00');
INSERT INTO `schedule_job_log` VALUES ('171', '1', 'testTask', 'renren', '0', null, '1', '2020-09-28 10:30:00');
INSERT INTO `schedule_job_log` VALUES ('172', '1', 'testTask', 'renren', '0', null, '2', '2020-09-28 11:00:00');
INSERT INTO `schedule_job_log` VALUES ('173', '1', 'testTask', 'renren', '0', null, '1', '2020-09-28 11:30:00');
INSERT INTO `schedule_job_log` VALUES ('174', '1', 'testTask', 'renren', '0', null, '0', '2020-10-03 11:00:00');
INSERT INTO `schedule_job_log` VALUES ('175', '1', 'testTask', 'renren', '0', null, '0', '2020-10-03 12:30:00');
INSERT INTO `schedule_job_log` VALUES ('176', '1', 'testTask', 'renren', '0', null, '0', '2020-10-03 13:00:00');
INSERT INTO `schedule_job_log` VALUES ('177', '1', 'testTask', 'renren', '0', null, '0', '2020-10-03 13:30:00');
INSERT INTO `schedule_job_log` VALUES ('178', '1', 'testTask', 'renren', '0', null, '0', '2020-10-03 22:30:00');
INSERT INTO `schedule_job_log` VALUES ('179', '1', 'testTask', 'renren', '0', null, '5', '2020-10-04 00:00:00');
INSERT INTO `schedule_job_log` VALUES ('180', '1', 'testTask', 'renren', '0', null, '2', '2020-10-04 00:30:00');
INSERT INTO `schedule_job_log` VALUES ('181', '1', 'testTask', 'renren', '0', null, '0', '2020-10-04 01:00:00');
INSERT INTO `schedule_job_log` VALUES ('182', '1', 'testTask', 'renren', '0', null, '1', '2020-10-04 01:30:00');
INSERT INTO `schedule_job_log` VALUES ('183', '1', 'testTask', 'renren', '0', null, '1', '2020-10-04 02:00:00');
INSERT INTO `schedule_job_log` VALUES ('184', '1', 'testTask', 'renren', '0', null, '1', '2020-10-04 02:30:00');
INSERT INTO `schedule_job_log` VALUES ('185', '1', 'testTask', 'renren', '0', null, '1', '2020-10-04 03:00:00');
INSERT INTO `schedule_job_log` VALUES ('186', '1', 'testTask', 'renren', '0', null, '1', '2020-10-04 03:30:00');
INSERT INTO `schedule_job_log` VALUES ('187', '1', 'testTask', 'renren', '0', null, '0', '2020-10-04 04:00:00');
INSERT INTO `schedule_job_log` VALUES ('188', '1', 'testTask', 'renren', '0', null, '2', '2021-05-15 16:00:00');
INSERT INTO `schedule_job_log` VALUES ('189', '1', 'testTask', 'renren', '0', null, '0', '2021-05-15 16:30:00');
INSERT INTO `schedule_job_log` VALUES ('190', '1', 'testTask', 'renren', '0', null, '5', '2021-05-15 17:00:00');
INSERT INTO `schedule_job_log` VALUES ('191', '1', 'testTask', 'renren', '0', null, '5', '2021-05-15 17:30:00');
INSERT INTO `schedule_job_log` VALUES ('192', '1', 'testTask', 'renren', '0', null, '6', '2021-05-16 05:00:00');
INSERT INTO `schedule_job_log` VALUES ('193', '1', 'testTask', 'renren', '0', null, '20', '2021-05-16 05:30:00');
INSERT INTO `schedule_job_log` VALUES ('194', '1', 'testTask', 'renren', '0', null, '12', '2021-05-16 06:00:00');
INSERT INTO `schedule_job_log` VALUES ('195', '1', 'testTask', 'renren', '0', null, '3', '2021-05-16 06:30:00');
INSERT INTO `schedule_job_log` VALUES ('196', '1', 'testTask', 'renren', '0', null, '0', '2021-05-16 07:00:00');
INSERT INTO `schedule_job_log` VALUES ('197', '1', 'testTask', 'renren', '0', null, '0', '2021-05-16 07:30:00');
INSERT INTO `schedule_job_log` VALUES ('198', '1', 'testTask', 'renren', '0', null, '2', '2021-05-16 08:00:00');
INSERT INTO `schedule_job_log` VALUES ('199', '1', 'testTask', 'renren', '0', null, '38', '2021-05-16 08:30:00');
INSERT INTO `schedule_job_log` VALUES ('200', '1', 'testTask', 'renren', '0', null, '2', '2021-05-16 09:00:00');
INSERT INTO `schedule_job_log` VALUES ('201', '1', 'testTask', 'renren', '0', null, '7', '2021-05-16 09:30:00');
INSERT INTO `schedule_job_log` VALUES ('202', '1', 'testTask', 'renren', '0', null, '0', '2021-05-16 10:00:00');
INSERT INTO `schedule_job_log` VALUES ('203', '1', 'testTask', 'renren', '0', null, '7', '2021-05-16 10:30:00');
INSERT INTO `schedule_job_log` VALUES ('204', '1', 'testTask', 'renren', '0', null, '2', '2021-05-16 11:00:00');
INSERT INTO `schedule_job_log` VALUES ('205', '1', 'testTask', 'renren', '0', null, '2', '2021-05-16 11:30:00');
INSERT INTO `schedule_job_log` VALUES ('206', '1', 'testTask', 'renren', '0', null, '2', '2021-05-16 12:00:00');
INSERT INTO `schedule_job_log` VALUES ('207', '1', 'testTask', 'renren', '0', null, '3', '2021-05-16 12:30:00');
INSERT INTO `schedule_job_log` VALUES ('208', '1', 'testTask', 'renren', '0', null, '2', '2021-05-16 13:00:00');
INSERT INTO `schedule_job_log` VALUES ('209', '1', 'testTask', 'renren', '0', null, '3', '2021-05-16 13:30:00');
INSERT INTO `schedule_job_log` VALUES ('210', '1', 'testTask', 'renren', '0', null, '0', '2021-05-16 14:00:00');
INSERT INTO `schedule_job_log` VALUES ('211', '1', 'testTask', 'renren', '0', null, '3', '2021-05-16 14:30:00');
INSERT INTO `schedule_job_log` VALUES ('212', '1', 'testTask', 'renren', '0', null, '2', '2021-05-16 15:00:00');
INSERT INTO `schedule_job_log` VALUES ('213', '1', 'testTask', 'renren', '0', null, '2', '2021-05-16 15:30:00');
INSERT INTO `schedule_job_log` VALUES ('214', '1', 'testTask', 'renren', '0', null, '4', '2021-05-16 16:00:00');
INSERT INTO `schedule_job_log` VALUES ('215', '1', 'testTask', 'renren', '0', null, '3', '2021-05-16 16:30:00');
INSERT INTO `schedule_job_log` VALUES ('216', '1', 'testTask', 'renren', '0', null, '5', '2021-05-16 17:00:00');
INSERT INTO `schedule_job_log` VALUES ('217', '1', 'testTask', 'renren', '0', null, '2', '2021-05-16 17:30:00');
INSERT INTO `schedule_job_log` VALUES ('218', '1', 'testTask', 'renren', '0', null, '2', '2021-05-16 18:00:00');
INSERT INTO `schedule_job_log` VALUES ('219', '1', 'testTask', 'renren', '0', null, '15', '2021-05-16 18:30:00');
INSERT INTO `schedule_job_log` VALUES ('220', '1', 'testTask', 'renren', '0', null, '4', '2021-05-16 19:00:00');
INSERT INTO `schedule_job_log` VALUES ('221', '1', 'testTask', 'renren', '0', null, '3', '2021-05-16 19:30:00');
INSERT INTO `schedule_job_log` VALUES ('222', '1', 'testTask', 'renren', '0', null, '4', '2021-05-16 20:00:00');
INSERT INTO `schedule_job_log` VALUES ('223', '1', 'testTask', 'renren', '0', null, '3', '2021-05-16 20:30:00');
INSERT INTO `schedule_job_log` VALUES ('224', '1', 'testTask', 'renren', '0', null, '0', '2021-05-16 21:00:00');
INSERT INTO `schedule_job_log` VALUES ('225', '1', 'testTask', 'renren', '0', null, '19', '2021-05-16 21:30:00');
INSERT INTO `schedule_job_log` VALUES ('226', '1', 'testTask', 'renren', '0', null, '0', '2021-05-16 22:00:00');
INSERT INTO `schedule_job_log` VALUES ('227', '1', 'testTask', 'renren', '0', null, '2', '2021-05-16 22:30:00');
INSERT INTO `schedule_job_log` VALUES ('228', '1', 'testTask', 'renren', '0', null, '3', '2021-05-16 23:00:00');
INSERT INTO `schedule_job_log` VALUES ('229', '1', 'testTask', 'renren', '0', null, '0', '2021-05-16 23:30:00');
INSERT INTO `schedule_job_log` VALUES ('230', '1', 'testTask', 'renren', '0', null, '61', '2021-05-17 00:00:00');
INSERT INTO `schedule_job_log` VALUES ('231', '1', 'testTask', 'renren', '0', null, '5', '2021-05-17 00:30:00');
INSERT INTO `schedule_job_log` VALUES ('232', '1', 'testTask', 'renren', '0', null, '2', '2021-05-17 01:00:00');
INSERT INTO `schedule_job_log` VALUES ('233', '1', 'testTask', 'renren', '0', null, '0', '2021-05-17 01:30:00');
INSERT INTO `schedule_job_log` VALUES ('234', '1', 'testTask', 'renren', '0', null, '3', '2021-05-17 02:00:00');
INSERT INTO `schedule_job_log` VALUES ('235', '1', 'testTask', 'renren', '0', null, '15', '2021-05-17 02:30:00');
INSERT INTO `schedule_job_log` VALUES ('236', '1', 'testTask', 'renren', '0', null, '5', '2021-05-17 03:00:00');
INSERT INTO `schedule_job_log` VALUES ('237', '1', 'testTask', 'renren', '0', null, '3', '2021-05-17 03:30:00');
INSERT INTO `schedule_job_log` VALUES ('238', '1', 'testTask', 'renren', '0', null, '0', '2021-05-17 04:00:00');
INSERT INTO `schedule_job_log` VALUES ('239', '1', 'testTask', 'renren', '0', null, '4', '2021-05-17 04:30:00');
INSERT INTO `schedule_job_log` VALUES ('240', '1', 'testTask', 'renren', '0', null, '13', '2021-05-17 05:00:00');
INSERT INTO `schedule_job_log` VALUES ('241', '1', 'testTask', 'renren', '0', null, '2', '2021-05-17 05:30:00');
INSERT INTO `schedule_job_log` VALUES ('242', '1', 'testTask', 'renren', '0', null, '3', '2021-05-17 06:00:00');
INSERT INTO `schedule_job_log` VALUES ('243', '1', 'testTask', 'renren', '0', null, '0', '2021-05-17 06:30:00');
INSERT INTO `schedule_job_log` VALUES ('244', '1', 'testTask', 'renren', '0', null, '2', '2021-05-17 07:00:00');
INSERT INTO `schedule_job_log` VALUES ('245', '1', 'testTask', 'renren', '0', null, '9', '2021-05-17 07:30:00');
INSERT INTO `schedule_job_log` VALUES ('246', '1', 'testTask', 'renren', '0', null, '0', '2021-05-17 08:00:00');
INSERT INTO `schedule_job_log` VALUES ('247', '1', 'testTask', 'renren', '0', null, '3', '2021-05-17 08:30:00');
INSERT INTO `schedule_job_log` VALUES ('248', '1', 'testTask', 'renren', '0', null, '2', '2021-05-17 09:00:00');
INSERT INTO `schedule_job_log` VALUES ('249', '1', 'testTask', 'renren', '0', null, '23', '2021-05-17 09:30:00');
INSERT INTO `schedule_job_log` VALUES ('250', '1', 'testTask', 'renren', '0', null, '2', '2021-05-17 10:00:00');
INSERT INTO `schedule_job_log` VALUES ('251', '1', 'testTask', 'renren', '0', null, '3', '2021-05-17 10:30:00');
INSERT INTO `schedule_job_log` VALUES ('252', '1', 'testTask', 'renren', '0', null, '0', '2021-05-17 11:00:00');
INSERT INTO `schedule_job_log` VALUES ('253', '1', 'testTask', 'renren', '0', null, '0', '2021-05-17 11:30:00');
INSERT INTO `schedule_job_log` VALUES ('254', '1', 'testTask', 'renren', '0', null, '2', '2021-05-17 12:00:00');
INSERT INTO `schedule_job_log` VALUES ('255', '1', 'testTask', 'renren', '0', null, '2', '2021-05-17 12:30:00');
INSERT INTO `schedule_job_log` VALUES ('256', '1', 'testTask', 'renren', '0', null, '2', '2021-05-17 13:00:00');
INSERT INTO `schedule_job_log` VALUES ('257', '1', 'testTask', 'renren', '0', null, '2', '2021-05-17 13:30:00');
INSERT INTO `schedule_job_log` VALUES ('258', '1', 'testTask', 'renren', '0', null, '4', '2021-05-17 14:00:00');
INSERT INTO `schedule_job_log` VALUES ('259', '1', 'testTask', 'renren', '0', null, '0', '2021-05-17 14:30:00');
INSERT INTO `schedule_job_log` VALUES ('260', '1', 'testTask', 'renren', '0', null, '0', '2021-05-17 15:00:00');
INSERT INTO `schedule_job_log` VALUES ('261', '1', 'testTask', 'renren', '0', null, '0', '2021-05-17 15:30:00');
INSERT INTO `schedule_job_log` VALUES ('262', '1', 'testTask', 'renren', '0', null, '2', '2021-05-17 16:00:00');
INSERT INTO `schedule_job_log` VALUES ('263', '1', 'testTask', 'renren', '0', null, '3', '2021-05-17 16:30:00');
INSERT INTO `schedule_job_log` VALUES ('264', '1', 'testTask', 'renren', '0', null, '2', '2021-05-17 17:00:00');
INSERT INTO `schedule_job_log` VALUES ('265', '1', 'testTask', 'renren', '0', null, '8', '2021-05-17 17:30:00');
INSERT INTO `schedule_job_log` VALUES ('266', '1', 'testTask', 'renren', '0', null, '0', '2021-05-17 18:00:00');
INSERT INTO `schedule_job_log` VALUES ('267', '1', 'testTask', 'renren', '0', null, '9', '2021-05-17 18:30:00');
INSERT INTO `schedule_job_log` VALUES ('268', '1', 'testTask', 'renren', '0', null, '6', '2021-05-17 19:00:00');
INSERT INTO `schedule_job_log` VALUES ('269', '1', 'testTask', 'renren', '0', null, '4', '2021-05-17 19:30:00');
INSERT INTO `schedule_job_log` VALUES ('270', '1', 'testTask', 'renren', '0', null, '0', '2021-05-17 20:00:00');
INSERT INTO `schedule_job_log` VALUES ('271', '1', 'testTask', 'renren', '0', null, '5', '2021-05-17 20:30:00');
INSERT INTO `schedule_job_log` VALUES ('272', '1', 'testTask', 'renren', '0', null, '2', '2021-05-17 21:00:00');
INSERT INTO `schedule_job_log` VALUES ('273', '1', 'testTask', 'renren', '0', null, '7', '2021-05-17 21:30:00');
INSERT INTO `schedule_job_log` VALUES ('274', '1', 'testTask', 'renren', '0', null, '4', '2021-05-17 22:00:00');
INSERT INTO `schedule_job_log` VALUES ('275', '1', 'testTask', 'renren', '0', null, '0', '2021-05-17 22:30:00');
INSERT INTO `schedule_job_log` VALUES ('276', '1', 'testTask', 'renren', '0', null, '2', '2021-05-17 23:00:00');
INSERT INTO `schedule_job_log` VALUES ('277', '1', 'testTask', 'renren', '0', null, '3', '2021-05-17 23:30:00');
INSERT INTO `schedule_job_log` VALUES ('278', '1', 'testTask', 'renren', '0', null, '46', '2021-05-18 00:00:00');
INSERT INTO `schedule_job_log` VALUES ('279', '1', 'testTask', 'renren', '0', null, '8', '2021-05-18 00:30:00');
INSERT INTO `schedule_job_log` VALUES ('280', '1', 'testTask', 'renren', '0', null, '2', '2021-05-18 01:00:00');
INSERT INTO `schedule_job_log` VALUES ('281', '1', 'testTask', 'renren', '0', null, '4', '2021-05-18 01:30:00');
INSERT INTO `schedule_job_log` VALUES ('282', '1', 'testTask', 'renren', '0', null, '3', '2021-05-18 02:00:00');
INSERT INTO `schedule_job_log` VALUES ('283', '1', 'testTask', 'renren', '0', null, '11', '2021-05-18 02:30:00');
INSERT INTO `schedule_job_log` VALUES ('284', '1', 'testTask', 'renren', '0', null, '0', '2021-05-18 03:00:00');
INSERT INTO `schedule_job_log` VALUES ('285', '1', 'testTask', 'renren', '0', null, '3', '2021-05-18 03:30:00');
INSERT INTO `schedule_job_log` VALUES ('286', '1', 'testTask', 'renren', '0', null, '6', '2021-05-18 04:00:00');
INSERT INTO `schedule_job_log` VALUES ('287', '1', 'testTask', 'renren', '0', null, '0', '2021-05-18 04:30:00');
INSERT INTO `schedule_job_log` VALUES ('288', '1', 'testTask', 'renren', '0', null, '2', '2021-05-18 05:00:00');
INSERT INTO `schedule_job_log` VALUES ('289', '1', 'testTask', 'renren', '0', null, '0', '2021-05-18 05:30:00');
INSERT INTO `schedule_job_log` VALUES ('290', '1', 'testTask', 'renren', '0', null, '2', '2021-05-18 06:00:00');
INSERT INTO `schedule_job_log` VALUES ('291', '1', 'testTask', 'renren', '0', null, '0', '2021-05-18 06:30:00');
INSERT INTO `schedule_job_log` VALUES ('292', '1', 'testTask', 'renren', '0', null, '2', '2021-05-18 07:00:00');
INSERT INTO `schedule_job_log` VALUES ('293', '1', 'testTask', 'renren', '0', null, '0', '2021-05-18 07:30:00');
INSERT INTO `schedule_job_log` VALUES ('294', '1', 'testTask', 'renren', '0', null, '1', '2021-05-18 08:00:00');
INSERT INTO `schedule_job_log` VALUES ('295', '1', 'testTask', 'renren', '0', null, '0', '2021-05-18 08:30:00');
INSERT INTO `schedule_job_log` VALUES ('296', '1', 'testTask', 'renren', '0', null, '0', '2021-05-18 09:00:00');
INSERT INTO `schedule_job_log` VALUES ('297', '1', 'testTask', 'renren', '0', null, '2', '2021-05-18 09:30:00');
INSERT INTO `schedule_job_log` VALUES ('298', '1', 'testTask', 'renren', '0', null, '3', '2021-05-18 10:00:00');
INSERT INTO `schedule_job_log` VALUES ('299', '1', 'testTask', 'renren', '0', null, '2', '2021-05-18 10:30:00');
INSERT INTO `schedule_job_log` VALUES ('300', '1', 'testTask', 'renren', '0', null, '34', '2021-05-18 11:00:00');
INSERT INTO `schedule_job_log` VALUES ('301', '1', 'testTask', 'renren', '0', null, '7', '2021-05-18 11:30:00');
INSERT INTO `schedule_job_log` VALUES ('302', '1', 'testTask', 'renren', '0', null, '6', '2021-05-18 12:00:00');
INSERT INTO `schedule_job_log` VALUES ('303', '1', 'testTask', 'renren', '0', null, '0', '2021-05-18 12:30:00');
INSERT INTO `schedule_job_log` VALUES ('304', '1', 'testTask', 'renren', '0', null, '2', '2021-05-18 13:00:00');
INSERT INTO `schedule_job_log` VALUES ('305', '1', 'testTask', 'renren', '0', null, '0', '2021-05-18 13:30:00');
INSERT INTO `schedule_job_log` VALUES ('306', '1', 'testTask', 'renren', '0', null, '6', '2021-05-18 14:00:00');
INSERT INTO `schedule_job_log` VALUES ('307', '1', 'testTask', 'renren', '0', null, '0', '2021-05-18 14:30:00');
INSERT INTO `schedule_job_log` VALUES ('308', '1', 'testTask', 'renren', '0', null, '4', '2021-05-18 15:00:00');
INSERT INTO `schedule_job_log` VALUES ('309', '1', 'testTask', 'renren', '0', null, '17', '2021-05-18 15:30:00');
INSERT INTO `schedule_job_log` VALUES ('310', '1', 'testTask', 'renren', '0', null, '2', '2021-05-18 16:00:00');
INSERT INTO `schedule_job_log` VALUES ('311', '1', 'testTask', 'renren', '0', null, '10', '2021-05-18 16:30:00');
INSERT INTO `schedule_job_log` VALUES ('312', '1', 'testTask', 'renren', '0', null, '0', '2021-05-18 17:00:00');
INSERT INTO `schedule_job_log` VALUES ('313', '1', 'testTask', 'renren', '0', null, '3', '2021-05-18 17:30:00');
INSERT INTO `schedule_job_log` VALUES ('314', '1', 'testTask', 'renren', '0', null, '68', '2021-05-18 18:00:00');
INSERT INTO `schedule_job_log` VALUES ('315', '1', 'testTask', 'renren', '0', null, '0', '2021-05-18 18:30:00');
INSERT INTO `schedule_job_log` VALUES ('316', '1', 'testTask', 'renren', '0', null, '0', '2021-05-18 19:00:00');
INSERT INTO `schedule_job_log` VALUES ('317', '1', 'testTask', 'renren', '0', null, '3', '2021-05-18 19:30:00');
INSERT INTO `schedule_job_log` VALUES ('318', '1', 'testTask', 'renren', '0', null, '4', '2021-05-18 20:00:00');
INSERT INTO `schedule_job_log` VALUES ('319', '1', 'testTask', 'renren', '0', null, '4', '2021-05-18 20:30:00');
INSERT INTO `schedule_job_log` VALUES ('320', '1', 'testTask', 'renren', '0', null, '3', '2021-05-18 21:00:00');
INSERT INTO `schedule_job_log` VALUES ('321', '1', 'testTask', 'renren', '0', null, '1', '2021-05-18 21:30:00');
INSERT INTO `schedule_job_log` VALUES ('322', '1', 'testTask', 'renren', '0', null, '2', '2021-05-18 22:00:00');
INSERT INTO `schedule_job_log` VALUES ('323', '1', 'testTask', 'renren', '0', null, '0', '2021-05-18 22:30:00');
INSERT INTO `schedule_job_log` VALUES ('324', '1', 'testTask', 'renren', '0', null, '0', '2021-05-18 23:00:00');
INSERT INTO `schedule_job_log` VALUES ('325', '1', 'testTask', 'renren', '0', null, '0', '2021-05-18 23:30:00');
INSERT INTO `schedule_job_log` VALUES ('326', '1', 'testTask', 'renren', '0', null, '130', '2021-05-19 00:00:00');
INSERT INTO `schedule_job_log` VALUES ('327', '1', 'testTask', 'renren', '0', null, '0', '2021-05-19 00:30:00');
INSERT INTO `schedule_job_log` VALUES ('328', '1', 'testTask', 'renren', '0', null, '7', '2021-05-19 01:00:00');
INSERT INTO `schedule_job_log` VALUES ('329', '1', 'testTask', 'renren', '0', null, '128', '2021-05-19 01:30:00');
INSERT INTO `schedule_job_log` VALUES ('330', '1', 'testTask', 'renren', '0', null, '0', '2021-05-19 02:00:00');
INSERT INTO `schedule_job_log` VALUES ('331', '1', 'testTask', 'renren', '0', null, '13', '2021-05-19 02:30:00');
INSERT INTO `schedule_job_log` VALUES ('332', '1', 'testTask', 'renren', '0', null, '4', '2021-05-19 03:00:00');
INSERT INTO `schedule_job_log` VALUES ('333', '1', 'testTask', 'renren', '0', null, '7', '2021-05-19 03:30:00');
INSERT INTO `schedule_job_log` VALUES ('334', '1', 'testTask', 'renren', '0', null, '10', '2021-05-19 04:00:00');
INSERT INTO `schedule_job_log` VALUES ('335', '1', 'testTask', 'renren', '0', null, '2', '2021-05-19 04:30:00');
INSERT INTO `schedule_job_log` VALUES ('336', '1', 'testTask', 'renren', '0', null, '0', '2021-05-19 05:00:00');
INSERT INTO `schedule_job_log` VALUES ('337', '1', 'testTask', 'renren', '0', null, '2', '2021-05-19 05:30:00');
INSERT INTO `schedule_job_log` VALUES ('338', '1', 'testTask', 'renren', '0', null, '0', '2021-05-19 06:00:00');
INSERT INTO `schedule_job_log` VALUES ('339', '1', 'testTask', 'renren', '0', null, '0', '2021-05-19 06:30:00');
INSERT INTO `schedule_job_log` VALUES ('340', '1', 'testTask', 'renren', '0', null, '0', '2021-05-19 07:00:00');
INSERT INTO `schedule_job_log` VALUES ('341', '1', 'testTask', 'renren', '0', null, '0', '2021-05-19 07:30:00');
INSERT INTO `schedule_job_log` VALUES ('342', '1', 'testTask', 'renren', '0', null, '0', '2021-05-19 08:00:00');
INSERT INTO `schedule_job_log` VALUES ('343', '1', 'testTask', 'renren', '0', null, '2', '2021-05-19 08:30:00');
INSERT INTO `schedule_job_log` VALUES ('344', '1', 'testTask', 'renren', '0', null, '3', '2021-05-19 09:00:00');
INSERT INTO `schedule_job_log` VALUES ('345', '1', 'testTask', 'renren', '0', null, '3', '2021-05-19 09:30:00');
INSERT INTO `schedule_job_log` VALUES ('346', '1', 'testTask', 'renren', '0', null, '4', '2021-05-19 10:00:00');
INSERT INTO `schedule_job_log` VALUES ('347', '1', 'testTask', 'renren', '0', null, '0', '2021-05-19 10:30:00');
INSERT INTO `schedule_job_log` VALUES ('348', '1', 'testTask', 'renren', '0', null, '2', '2021-05-19 11:00:00');
INSERT INTO `schedule_job_log` VALUES ('349', '1', 'testTask', 'renren', '0', null, '2', '2021-05-19 11:30:00');
INSERT INTO `schedule_job_log` VALUES ('350', '1', 'testTask', 'renren', '0', null, '4', '2021-05-19 12:00:00');
INSERT INTO `schedule_job_log` VALUES ('351', '1', 'testTask', 'renren', '0', null, '5', '2021-05-19 12:30:00');
INSERT INTO `schedule_job_log` VALUES ('352', '1', 'testTask', 'renren', '0', null, '0', '2021-05-19 13:00:00');
INSERT INTO `schedule_job_log` VALUES ('353', '1', 'testTask', 'renren', '0', null, '3', '2021-05-19 13:30:00');
INSERT INTO `schedule_job_log` VALUES ('354', '1', 'testTask', 'renren', '0', null, '0', '2021-05-19 14:00:00');
INSERT INTO `schedule_job_log` VALUES ('355', '1', 'testTask', 'renren', '0', null, '2', '2021-05-19 14:30:00');
INSERT INTO `schedule_job_log` VALUES ('356', '1', 'testTask', 'renren', '0', null, '0', '2021-05-19 15:00:00');
INSERT INTO `schedule_job_log` VALUES ('357', '1', 'testTask', 'renren', '0', null, '2', '2021-05-19 15:30:00');
INSERT INTO `schedule_job_log` VALUES ('358', '1', 'testTask', 'renren', '0', null, '32', '2021-05-19 16:00:00');
INSERT INTO `schedule_job_log` VALUES ('359', '1', 'testTask', 'renren', '0', null, '2', '2021-05-19 16:30:00');
INSERT INTO `schedule_job_log` VALUES ('360', '1', 'testTask', 'renren', '0', null, '2', '2021-05-19 17:00:00');
INSERT INTO `schedule_job_log` VALUES ('361', '1', 'testTask', 'renren', '0', null, '6', '2021-05-19 17:30:00');
INSERT INTO `schedule_job_log` VALUES ('362', '1', 'testTask', 'renren', '0', null, '0', '2021-05-19 18:00:00');
INSERT INTO `schedule_job_log` VALUES ('363', '1', 'testTask', 'renren', '0', null, '0', '2021-05-19 18:30:00');
INSERT INTO `schedule_job_log` VALUES ('364', '1', 'testTask', 'renren', '0', null, '2', '2021-05-19 19:00:00');
INSERT INTO `schedule_job_log` VALUES ('365', '1', 'testTask', 'renren', '0', null, '6', '2021-05-19 19:30:00');
INSERT INTO `schedule_job_log` VALUES ('366', '1', 'testTask', 'renren', '0', null, '10', '2021-05-19 20:00:00');
INSERT INTO `schedule_job_log` VALUES ('367', '1', 'testTask', 'renren', '0', null, '5', '2021-05-19 20:30:00');
INSERT INTO `schedule_job_log` VALUES ('368', '1', 'testTask', 'renren', '0', null, '0', '2021-05-19 21:00:00');
INSERT INTO `schedule_job_log` VALUES ('369', '1', 'testTask', 'renren', '0', null, '4', '2021-05-19 21:30:00');
INSERT INTO `schedule_job_log` VALUES ('370', '1', 'testTask', 'renren', '0', null, '0', '2021-05-19 22:00:00');
INSERT INTO `schedule_job_log` VALUES ('371', '1', 'testTask', 'renren', '0', null, '1', '2021-05-19 22:30:00');
INSERT INTO `schedule_job_log` VALUES ('372', '1', 'testTask', 'renren', '0', null, '0', '2021-05-19 23:00:00');
INSERT INTO `schedule_job_log` VALUES ('373', '1', 'testTask', 'renren', '0', null, '2', '2021-05-19 23:30:00');
INSERT INTO `schedule_job_log` VALUES ('374', '1', 'testTask', 'renren', '0', null, '79', '2021-05-20 00:00:00');
INSERT INTO `schedule_job_log` VALUES ('375', '1', 'testTask', 'renren', '0', null, '0', '2021-05-20 00:30:00');
INSERT INTO `schedule_job_log` VALUES ('376', '1', 'testTask', 'renren', '0', null, '2', '2021-05-20 01:00:00');
INSERT INTO `schedule_job_log` VALUES ('377', '1', 'testTask', 'renren', '0', null, '3', '2021-05-20 01:30:00');
INSERT INTO `schedule_job_log` VALUES ('378', '1', 'testTask', 'renren', '0', null, '2', '2021-05-20 02:00:00');
INSERT INTO `schedule_job_log` VALUES ('379', '1', 'testTask', 'renren', '0', null, '0', '2021-05-20 02:30:00');
INSERT INTO `schedule_job_log` VALUES ('380', '1', 'testTask', 'renren', '0', null, '2', '2021-05-20 03:00:00');
INSERT INTO `schedule_job_log` VALUES ('381', '1', 'testTask', 'renren', '0', null, '3', '2021-05-20 03:30:00');
INSERT INTO `schedule_job_log` VALUES ('382', '1', 'testTask', 'renren', '0', null, '8', '2021-05-20 04:00:00');
INSERT INTO `schedule_job_log` VALUES ('383', '1', 'testTask', 'renren', '0', null, '1', '2021-05-20 04:30:00');
INSERT INTO `schedule_job_log` VALUES ('384', '1', 'testTask', 'renren', '0', null, '0', '2021-05-20 05:00:00');
INSERT INTO `schedule_job_log` VALUES ('385', '1', 'testTask', 'renren', '0', null, '7', '2021-05-20 05:30:00');
INSERT INTO `schedule_job_log` VALUES ('386', '1', 'testTask', 'renren', '0', null, '3', '2021-05-20 06:00:00');
INSERT INTO `schedule_job_log` VALUES ('387', '1', 'testTask', 'renren', '0', null, '0', '2021-05-20 06:30:00');
INSERT INTO `schedule_job_log` VALUES ('388', '1', 'testTask', 'renren', '0', null, '0', '2021-05-20 07:00:00');
INSERT INTO `schedule_job_log` VALUES ('389', '1', 'testTask', 'renren', '0', null, '6', '2021-05-20 07:30:00');
INSERT INTO `schedule_job_log` VALUES ('390', '1', 'testTask', 'renren', '0', null, '7', '2021-05-20 08:00:00');
INSERT INTO `schedule_job_log` VALUES ('391', '1', 'testTask', 'renren', '0', null, '2', '2021-05-20 08:30:00');
INSERT INTO `schedule_job_log` VALUES ('392', '1', 'testTask', 'renren', '0', null, '0', '2021-05-20 09:00:00');
INSERT INTO `schedule_job_log` VALUES ('393', '1', 'testTask', 'renren', '0', null, '26', '2021-05-20 09:30:00');
INSERT INTO `schedule_job_log` VALUES ('394', '1', 'testTask', 'renren', '0', null, '4', '2021-05-20 10:00:00');
INSERT INTO `schedule_job_log` VALUES ('395', '1', 'testTask', 'renren', '0', null, '0', '2021-05-20 10:30:00');
INSERT INTO `schedule_job_log` VALUES ('396', '1', 'testTask', 'renren', '0', null, '8', '2021-05-20 11:00:00');
INSERT INTO `schedule_job_log` VALUES ('397', '1', 'testTask', 'renren', '0', null, '4', '2021-05-20 11:30:00');
INSERT INTO `schedule_job_log` VALUES ('398', '1', 'testTask', 'renren', '0', null, '4', '2021-05-20 12:00:00');
INSERT INTO `schedule_job_log` VALUES ('399', '1', 'testTask', 'renren', '0', null, '6', '2021-05-20 12:30:00');
INSERT INTO `schedule_job_log` VALUES ('400', '1', 'testTask', 'renren', '0', null, '2', '2021-05-20 13:00:00');
INSERT INTO `schedule_job_log` VALUES ('401', '1', 'testTask', 'renren', '0', null, '2', '2021-05-20 13:30:00');
INSERT INTO `schedule_job_log` VALUES ('402', '1', 'testTask', 'renren', '0', null, '6', '2021-05-20 14:00:00');
INSERT INTO `schedule_job_log` VALUES ('403', '1', 'testTask', 'renren', '0', null, '3', '2021-05-20 14:30:00');
INSERT INTO `schedule_job_log` VALUES ('404', '1', 'testTask', 'renren', '0', null, '4', '2021-05-20 15:00:00');
INSERT INTO `schedule_job_log` VALUES ('405', '1', 'testTask', 'renren', '0', null, '8', '2021-05-20 15:30:00');
INSERT INTO `schedule_job_log` VALUES ('406', '1', 'testTask', 'renren', '0', null, '1', '2021-05-20 16:00:00');
INSERT INTO `schedule_job_log` VALUES ('407', '1', 'testTask', 'renren', '0', null, '16', '2021-05-20 16:30:00');
INSERT INTO `schedule_job_log` VALUES ('408', '1', 'testTask', 'renren', '0', null, '3', '2021-05-20 17:00:00');
INSERT INTO `schedule_job_log` VALUES ('409', '1', 'testTask', 'renren', '0', null, '0', '2021-05-20 17:30:00');
INSERT INTO `schedule_job_log` VALUES ('410', '1', 'testTask', 'renren', '0', null, '10', '2021-05-20 18:00:00');
INSERT INTO `schedule_job_log` VALUES ('411', '1', 'testTask', 'renren', '0', null, '0', '2021-05-20 18:30:00');
INSERT INTO `schedule_job_log` VALUES ('412', '1', 'testTask', 'renren', '0', null, '0', '2021-05-20 19:00:00');
INSERT INTO `schedule_job_log` VALUES ('413', '1', 'testTask', 'renren', '0', null, '0', '2021-05-20 19:30:00');
INSERT INTO `schedule_job_log` VALUES ('414', '1', 'testTask', 'renren', '0', null, '4', '2021-05-20 20:00:00');
INSERT INTO `schedule_job_log` VALUES ('415', '1', 'testTask', 'renren', '0', null, '20', '2021-05-20 20:30:00');
INSERT INTO `schedule_job_log` VALUES ('416', '1', 'testTask', 'renren', '0', null, '0', '2021-05-20 21:00:00');
INSERT INTO `schedule_job_log` VALUES ('417', '1', 'testTask', 'renren', '0', null, '0', '2021-05-20 21:30:00');
INSERT INTO `schedule_job_log` VALUES ('418', '1', 'testTask', 'renren', '0', null, '3', '2021-05-20 22:00:00');
INSERT INTO `schedule_job_log` VALUES ('419', '1', 'testTask', 'renren', '0', null, '3', '2021-05-20 22:30:00');
INSERT INTO `schedule_job_log` VALUES ('420', '1', 'testTask', 'renren', '0', null, '3', '2021-05-20 23:00:00');
INSERT INTO `schedule_job_log` VALUES ('421', '1', 'testTask', 'renren', '0', null, '3', '2021-05-20 23:30:00');
INSERT INTO `schedule_job_log` VALUES ('422', '1', 'testTask', 'renren', '0', null, '50', '2021-05-21 00:00:00');
INSERT INTO `schedule_job_log` VALUES ('423', '1', 'testTask', 'renren', '0', null, '0', '2021-05-21 00:30:00');
INSERT INTO `schedule_job_log` VALUES ('424', '1', 'testTask', 'renren', '0', null, '5', '2021-05-21 01:00:00');
INSERT INTO `schedule_job_log` VALUES ('425', '1', 'testTask', 'renren', '0', null, '5', '2021-05-21 01:30:00');
INSERT INTO `schedule_job_log` VALUES ('426', '1', 'testTask', 'renren', '0', null, '4', '2021-05-21 02:00:00');
INSERT INTO `schedule_job_log` VALUES ('427', '1', 'testTask', 'renren', '0', null, '2', '2021-05-21 02:30:00');
INSERT INTO `schedule_job_log` VALUES ('428', '1', 'testTask', 'renren', '0', null, '2', '2021-05-21 03:00:00');
INSERT INTO `schedule_job_log` VALUES ('429', '1', 'testTask', 'renren', '0', null, '6', '2021-05-21 03:30:00');
INSERT INTO `schedule_job_log` VALUES ('430', '1', 'testTask', 'renren', '0', null, '0', '2021-05-21 04:00:00');
INSERT INTO `schedule_job_log` VALUES ('431', '1', 'testTask', 'renren', '0', null, '1', '2021-05-21 04:30:00');
INSERT INTO `schedule_job_log` VALUES ('432', '1', 'testTask', 'renren', '0', null, '2', '2021-05-21 05:00:00');
INSERT INTO `schedule_job_log` VALUES ('433', '1', 'testTask', 'renren', '0', null, '3', '2021-05-21 05:30:00');
INSERT INTO `schedule_job_log` VALUES ('434', '1', 'testTask', 'renren', '0', null, '12', '2021-05-21 06:00:00');
INSERT INTO `schedule_job_log` VALUES ('435', '1', 'testTask', 'renren', '0', null, '4', '2021-05-21 06:30:00');
INSERT INTO `schedule_job_log` VALUES ('436', '1', 'testTask', 'renren', '0', null, '6', '2021-05-21 07:00:00');
INSERT INTO `schedule_job_log` VALUES ('437', '1', 'testTask', 'renren', '0', null, '0', '2021-05-21 07:30:00');
INSERT INTO `schedule_job_log` VALUES ('438', '1', 'testTask', 'renren', '0', null, '2', '2021-05-21 08:00:00');
INSERT INTO `schedule_job_log` VALUES ('439', '1', 'testTask', 'renren', '0', null, '3', '2021-05-21 08:30:00');
INSERT INTO `schedule_job_log` VALUES ('440', '1', 'testTask', 'renren', '0', null, '0', '2021-05-21 09:00:00');
INSERT INTO `schedule_job_log` VALUES ('441', '1', 'testTask', 'renren', '0', null, '7', '2021-05-21 09:30:00');
INSERT INTO `schedule_job_log` VALUES ('442', '1', 'testTask', 'renren', '0', null, '5', '2021-05-21 10:00:00');
INSERT INTO `schedule_job_log` VALUES ('443', '1', 'testTask', 'renren', '0', null, '0', '2021-05-21 10:30:00');
INSERT INTO `schedule_job_log` VALUES ('444', '1', 'testTask', 'renren', '0', null, '3', '2021-05-21 11:00:00');
INSERT INTO `schedule_job_log` VALUES ('445', '1', 'testTask', 'renren', '0', null, '0', '2021-05-21 11:30:00');
INSERT INTO `schedule_job_log` VALUES ('446', '1', 'testTask', 'renren', '0', null, '3', '2021-05-21 12:00:00');
INSERT INTO `schedule_job_log` VALUES ('447', '1', 'testTask', 'renren', '0', null, '2', '2021-05-21 12:30:00');
INSERT INTO `schedule_job_log` VALUES ('448', '1', 'testTask', 'renren', '0', null, '3', '2021-05-21 13:00:00');
INSERT INTO `schedule_job_log` VALUES ('449', '1', 'testTask', 'renren', '0', null, '4', '2021-05-21 13:30:00');
INSERT INTO `schedule_job_log` VALUES ('450', '1', 'testTask', 'renren', '0', null, '4', '2021-05-21 14:00:00');
INSERT INTO `schedule_job_log` VALUES ('451', '1', 'testTask', 'renren', '0', null, '0', '2021-05-21 14:30:00');
INSERT INTO `schedule_job_log` VALUES ('452', '1', 'testTask', 'renren', '0', null, '3', '2021-05-21 15:00:00');
INSERT INTO `schedule_job_log` VALUES ('453', '1', 'testTask', 'renren', '0', null, '4', '2021-05-21 15:30:00');
INSERT INTO `schedule_job_log` VALUES ('454', '1', 'testTask', 'renren', '0', null, '3', '2021-05-21 16:00:00');
INSERT INTO `schedule_job_log` VALUES ('455', '1', 'testTask', 'renren', '0', null, '12', '2021-05-21 16:30:00');
INSERT INTO `schedule_job_log` VALUES ('456', '1', 'testTask', 'renren', '0', null, '5', '2021-05-21 17:00:00');
INSERT INTO `schedule_job_log` VALUES ('457', '1', 'testTask', 'renren', '0', null, '11', '2021-05-21 17:30:00');
INSERT INTO `schedule_job_log` VALUES ('458', '1', 'testTask', 'renren', '0', null, '0', '2021-05-21 18:00:00');
INSERT INTO `schedule_job_log` VALUES ('459', '1', 'testTask', 'renren', '0', null, '0', '2021-05-21 18:30:00');
INSERT INTO `schedule_job_log` VALUES ('460', '1', 'testTask', 'renren', '0', null, '13', '2021-05-21 19:00:00');
INSERT INTO `schedule_job_log` VALUES ('461', '1', 'testTask', 'renren', '0', null, '9', '2021-05-21 19:30:00');
INSERT INTO `schedule_job_log` VALUES ('462', '1', 'testTask', 'renren', '0', null, '14', '2021-05-21 20:00:00');
INSERT INTO `schedule_job_log` VALUES ('463', '1', 'testTask', 'renren', '0', null, '3', '2021-05-21 20:30:00');
INSERT INTO `schedule_job_log` VALUES ('464', '1', 'testTask', 'renren', '0', null, '2', '2021-05-21 21:00:00');
INSERT INTO `schedule_job_log` VALUES ('465', '1', 'testTask', 'renren', '0', null, '4', '2021-05-21 21:30:00');
INSERT INTO `schedule_job_log` VALUES ('466', '1', 'testTask', 'renren', '0', null, '0', '2021-05-21 22:00:00');
INSERT INTO `schedule_job_log` VALUES ('467', '1', 'testTask', 'renren', '0', null, '0', '2021-05-21 22:30:00');
INSERT INTO `schedule_job_log` VALUES ('468', '1', 'testTask', 'renren', '0', null, '3', '2021-05-21 23:00:00');
INSERT INTO `schedule_job_log` VALUES ('469', '1', 'testTask', 'renren', '0', null, '2', '2021-05-21 23:30:00');
INSERT INTO `schedule_job_log` VALUES ('470', '1', 'testTask', 'renren', '0', null, '64', '2021-05-22 00:00:00');
INSERT INTO `schedule_job_log` VALUES ('471', '1', 'testTask', 'renren', '0', null, '3', '2021-05-22 00:30:00');
INSERT INTO `schedule_job_log` VALUES ('472', '1', 'testTask', 'renren', '0', null, '8', '2021-05-22 01:00:00');
INSERT INTO `schedule_job_log` VALUES ('473', '1', 'testTask', 'renren', '0', null, '6', '2021-05-22 01:30:00');
INSERT INTO `schedule_job_log` VALUES ('474', '1', 'testTask', 'renren', '0', null, '0', '2021-05-22 02:00:00');
INSERT INTO `schedule_job_log` VALUES ('475', '1', 'testTask', 'renren', '0', null, '0', '2021-05-22 02:30:00');
INSERT INTO `schedule_job_log` VALUES ('476', '1', 'testTask', 'renren', '0', null, '5', '2021-05-22 03:00:00');
INSERT INTO `schedule_job_log` VALUES ('477', '1', 'testTask', 'renren', '0', null, '8', '2021-05-22 03:30:00');
INSERT INTO `schedule_job_log` VALUES ('478', '1', 'testTask', 'renren', '0', null, '0', '2021-05-22 04:00:00');
INSERT INTO `schedule_job_log` VALUES ('479', '1', 'testTask', 'renren', '0', null, '0', '2021-05-22 04:30:00');
INSERT INTO `schedule_job_log` VALUES ('480', '1', 'testTask', 'renren', '0', null, '0', '2021-05-22 05:00:00');
INSERT INTO `schedule_job_log` VALUES ('481', '1', 'testTask', 'renren', '0', null, '2', '2021-05-22 05:30:00');
INSERT INTO `schedule_job_log` VALUES ('482', '1', 'testTask', 'renren', '0', null, '4', '2021-05-22 06:00:00');
INSERT INTO `schedule_job_log` VALUES ('483', '1', 'testTask', 'renren', '0', null, '0', '2021-05-22 06:30:00');
INSERT INTO `schedule_job_log` VALUES ('484', '1', 'testTask', 'renren', '0', null, '1', '2021-05-22 07:00:00');
INSERT INTO `schedule_job_log` VALUES ('485', '1', 'testTask', 'renren', '0', null, '3', '2021-05-22 07:30:00');
INSERT INTO `schedule_job_log` VALUES ('486', '1', 'testTask', 'renren', '0', null, '0', '2021-05-22 08:00:00');
INSERT INTO `schedule_job_log` VALUES ('487', '1', 'testTask', 'renren', '0', null, '0', '2021-05-22 08:30:00');
INSERT INTO `schedule_job_log` VALUES ('488', '1', 'testTask', 'renren', '0', null, '0', '2021-05-22 09:00:00');
INSERT INTO `schedule_job_log` VALUES ('489', '1', 'testTask', 'renren', '0', null, '0', '2021-05-22 09:30:00');
INSERT INTO `schedule_job_log` VALUES ('490', '1', 'testTask', 'renren', '0', null, '8', '2021-05-22 10:00:00');
INSERT INTO `schedule_job_log` VALUES ('491', '1', 'testTask', 'renren', '0', null, '0', '2021-05-22 10:30:00');
INSERT INTO `schedule_job_log` VALUES ('492', '1', 'testTask', 'renren', '0', null, '8', '2021-05-22 11:00:00');
INSERT INTO `schedule_job_log` VALUES ('493', '1', 'testTask', 'renren', '0', null, '3', '2021-05-22 11:30:00');
INSERT INTO `schedule_job_log` VALUES ('494', '1', 'testTask', 'renren', '0', null, '3', '2021-05-22 12:00:00');
INSERT INTO `schedule_job_log` VALUES ('495', '1', 'testTask', 'renren', '0', null, '8', '2021-05-22 12:30:00');
INSERT INTO `schedule_job_log` VALUES ('496', '1', 'testTask', 'renren', '0', null, '0', '2021-05-22 13:00:00');
INSERT INTO `schedule_job_log` VALUES ('497', '1', 'testTask', 'renren', '0', null, '2', '2021-05-22 13:30:00');
INSERT INTO `schedule_job_log` VALUES ('498', '1', 'testTask', 'renren', '0', null, '10', '2021-05-22 14:00:00');
INSERT INTO `schedule_job_log` VALUES ('499', '1', 'testTask', 'renren', '0', null, '2', '2021-05-22 14:30:00');
INSERT INTO `schedule_job_log` VALUES ('500', '1', 'testTask', 'renren', '0', null, '2', '2021-05-22 15:00:00');
INSERT INTO `schedule_job_log` VALUES ('501', '1', 'testTask', 'renren', '0', null, '18', '2021-05-22 15:30:00');
INSERT INTO `schedule_job_log` VALUES ('502', '1', 'testTask', 'renren', '0', null, '4', '2021-05-22 16:00:00');
INSERT INTO `schedule_job_log` VALUES ('503', '1', 'testTask', 'renren', '0', null, '4', '2021-05-22 16:30:00');
INSERT INTO `schedule_job_log` VALUES ('504', '1', 'testTask', 'renren', '0', null, '8', '2021-05-22 17:00:00');
INSERT INTO `schedule_job_log` VALUES ('505', '1', 'testTask', 'renren', '0', null, '2', '2021-05-22 17:30:00');
INSERT INTO `schedule_job_log` VALUES ('506', '1', 'testTask', 'renren', '0', null, '3', '2021-05-22 18:00:00');
INSERT INTO `schedule_job_log` VALUES ('507', '1', 'testTask', 'renren', '0', null, '0', '2021-05-22 18:30:00');
INSERT INTO `schedule_job_log` VALUES ('508', '1', 'testTask', 'renren', '0', null, '2', '2021-05-22 19:00:00');
INSERT INTO `schedule_job_log` VALUES ('509', '1', 'testTask', 'renren', '0', null, '4', '2021-05-22 19:30:00');
INSERT INTO `schedule_job_log` VALUES ('510', '1', 'testTask', 'renren', '0', null, '7', '2021-05-22 20:00:00');
INSERT INTO `schedule_job_log` VALUES ('511', '1', 'testTask', 'renren', '0', null, '7', '2021-05-22 20:30:00');
INSERT INTO `schedule_job_log` VALUES ('512', '1', 'testTask', 'renren', '0', null, '6', '2021-05-22 21:00:00');
INSERT INTO `schedule_job_log` VALUES ('513', '1', 'testTask', 'renren', '0', null, '0', '2021-05-22 21:30:00');
INSERT INTO `schedule_job_log` VALUES ('514', '1', 'testTask', 'renren', '0', null, '3', '2021-05-22 22:00:00');
INSERT INTO `schedule_job_log` VALUES ('515', '1', 'testTask', 'renren', '0', null, '53', '2021-05-22 22:30:00');
INSERT INTO `schedule_job_log` VALUES ('516', '1', 'testTask', 'renren', '0', null, '21', '2021-05-22 23:00:00');
INSERT INTO `schedule_job_log` VALUES ('517', '1', 'testTask', 'renren', '0', null, '8', '2021-05-22 23:30:00');
INSERT INTO `schedule_job_log` VALUES ('518', '1', 'testTask', 'renren', '0', null, '177', '2021-05-23 00:00:00');
INSERT INTO `schedule_job_log` VALUES ('519', '1', 'testTask', 'renren', '0', null, '888', '2021-05-23 00:30:00');
INSERT INTO `schedule_job_log` VALUES ('520', '1', 'testTask', 'renren', '0', null, '5', '2021-05-23 01:00:00');
INSERT INTO `schedule_job_log` VALUES ('521', '1', 'testTask', 'renren', '0', null, '1036', '2021-05-23 01:30:00');
INSERT INTO `schedule_job_log` VALUES ('522', '1', 'testTask', 'renren', '0', null, '606', '2021-05-23 02:00:00');
INSERT INTO `schedule_job_log` VALUES ('523', '1', 'testTask', 'renren', '0', null, '7', '2021-05-23 02:30:00');
INSERT INTO `schedule_job_log` VALUES ('524', '1', 'testTask', 'renren', '0', null, '2', '2021-05-23 03:00:00');
INSERT INTO `schedule_job_log` VALUES ('525', '1', 'testTask', 'renren', '0', null, '1052', '2021-05-23 03:30:00');
INSERT INTO `schedule_job_log` VALUES ('526', '1', 'testTask', 'renren', '0', null, '0', '2021-05-23 04:00:00');
INSERT INTO `schedule_job_log` VALUES ('527', '1', 'testTask', 'renren', '0', null, '1', '2021-05-23 04:30:00');
INSERT INTO `schedule_job_log` VALUES ('528', '1', 'testTask', 'renren', '0', null, '58', '2021-05-23 05:00:00');
INSERT INTO `schedule_job_log` VALUES ('529', '1', 'testTask', 'renren', '0', null, '4', '2021-05-23 05:30:00');
INSERT INTO `schedule_job_log` VALUES ('530', '1', 'testTask', 'renren', '0', null, '777', '2021-05-23 06:00:00');
INSERT INTO `schedule_job_log` VALUES ('531', '1', 'testTask', 'renren', '0', null, '2', '2021-05-23 06:30:00');
INSERT INTO `schedule_job_log` VALUES ('532', '1', 'testTask', 'renren', '0', null, '7', '2021-05-23 07:00:00');
INSERT INTO `schedule_job_log` VALUES ('533', '1', 'testTask', 'renren', '0', null, '1843', '2021-05-23 07:30:00');
INSERT INTO `schedule_job_log` VALUES ('534', '1', 'testTask', 'renren', '0', null, '31', '2021-05-23 08:00:00');
INSERT INTO `schedule_job_log` VALUES ('535', '1', 'testTask', 'renren', '0', null, '1023', '2021-05-23 08:30:00');
INSERT INTO `schedule_job_log` VALUES ('536', '1', 'testTask', 'renren', '0', null, '5', '2021-05-23 09:00:00');
INSERT INTO `schedule_job_log` VALUES ('537', '1', 'testTask', 'renren', '0', null, '10', '2021-05-23 09:30:00');
INSERT INTO `schedule_job_log` VALUES ('538', '1', 'testTask', 'renren', '0', null, '201', '2021-05-23 10:00:02');
INSERT INTO `schedule_job_log` VALUES ('539', '1', 'testTask', 'renren', '0', null, '1305', '2021-05-23 10:30:00');
INSERT INTO `schedule_job_log` VALUES ('540', '1', 'testTask', 'renren', '0', null, '1245', '2021-05-23 11:00:00');
INSERT INTO `schedule_job_log` VALUES ('541', '1', 'testTask', 'renren', '0', null, '1042', '2021-05-23 11:30:00');
INSERT INTO `schedule_job_log` VALUES ('542', '1', 'testTask', 'renren', '0', null, '37', '2021-05-23 12:00:00');
INSERT INTO `schedule_job_log` VALUES ('543', '1', 'testTask', 'renren', '0', null, '0', '2021-05-23 12:30:00');
INSERT INTO `schedule_job_log` VALUES ('544', '1', 'testTask', 'renren', '0', null, '2', '2021-05-23 13:00:00');
INSERT INTO `schedule_job_log` VALUES ('545', '1', 'testTask', 'renren', '0', null, '2', '2021-05-23 13:30:00');
INSERT INTO `schedule_job_log` VALUES ('546', '1', 'testTask', 'renren', '0', null, '4', '2021-05-23 14:00:00');
INSERT INTO `schedule_job_log` VALUES ('547', '1', 'testTask', 'renren', '0', null, '978', '2021-05-23 14:30:00');
INSERT INTO `schedule_job_log` VALUES ('548', '1', 'testTask', 'renren', '0', null, '4', '2021-05-23 15:00:00');
INSERT INTO `schedule_job_log` VALUES ('549', '1', 'testTask', 'renren', '0', null, '31', '2021-05-23 15:30:00');
INSERT INTO `schedule_job_log` VALUES ('550', '1', 'testTask', 'renren', '0', null, '4', '2021-05-23 16:00:00');
INSERT INTO `schedule_job_log` VALUES ('551', '1', 'testTask', 'renren', '0', null, '6', '2021-05-23 16:30:00');
INSERT INTO `schedule_job_log` VALUES ('552', '1', 'testTask', 'renren', '0', null, '4', '2021-05-23 17:00:00');
INSERT INTO `schedule_job_log` VALUES ('553', '1', 'testTask', 'renren', '0', null, '11', '2021-05-23 17:30:00');
INSERT INTO `schedule_job_log` VALUES ('554', '1', 'testTask', 'renren', '0', null, '0', '2021-05-23 18:00:00');
INSERT INTO `schedule_job_log` VALUES ('555', '1', 'testTask', 'renren', '0', null, '3', '2021-05-23 18:30:00');
INSERT INTO `schedule_job_log` VALUES ('556', '1', 'testTask', 'renren', '0', null, '3', '2021-05-23 19:00:00');
INSERT INTO `schedule_job_log` VALUES ('557', '1', 'testTask', 'renren', '0', null, '0', '2021-05-23 19:30:00');
INSERT INTO `schedule_job_log` VALUES ('558', '1', 'testTask', 'renren', '0', null, '0', '2021-05-23 20:00:00');
INSERT INTO `schedule_job_log` VALUES ('559', '1', 'testTask', 'renren', '0', null, '2', '2021-05-23 20:30:00');
INSERT INTO `schedule_job_log` VALUES ('560', '1', 'testTask', 'renren', '0', null, '5', '2021-05-23 21:00:00');
INSERT INTO `schedule_job_log` VALUES ('561', '1', 'testTask', 'renren', '0', null, '3', '2021-05-23 21:30:00');
INSERT INTO `schedule_job_log` VALUES ('562', '1', 'testTask', 'renren', '0', null, '1018', '2021-05-23 22:00:00');
INSERT INTO `schedule_job_log` VALUES ('563', '1', 'testTask', 'renren', '0', null, '0', '2021-05-23 22:30:00');
INSERT INTO `schedule_job_log` VALUES ('564', '1', 'testTask', 'renren', '0', null, '3', '2021-05-23 23:00:00');
INSERT INTO `schedule_job_log` VALUES ('565', '1', 'testTask', 'renren', '0', null, '2', '2021-05-23 23:30:00');
INSERT INTO `schedule_job_log` VALUES ('566', '1', 'testTask', 'renren', '0', null, '98', '2021-05-24 00:00:00');
INSERT INTO `schedule_job_log` VALUES ('567', '1', 'testTask', 'renren', '0', null, '6', '2021-05-24 00:30:00');
INSERT INTO `schedule_job_log` VALUES ('568', '1', 'testTask', 'renren', '0', null, '56', '2021-05-24 01:00:00');
INSERT INTO `schedule_job_log` VALUES ('569', '1', 'testTask', 'renren', '0', null, '9', '2021-05-24 01:30:00');
INSERT INTO `schedule_job_log` VALUES ('570', '1', 'testTask', 'renren', '0', null, '0', '2021-05-24 02:00:00');
INSERT INTO `schedule_job_log` VALUES ('571', '1', 'testTask', 'renren', '0', null, '0', '2021-05-24 02:30:00');
INSERT INTO `schedule_job_log` VALUES ('572', '1', 'testTask', 'renren', '0', null, '1224', '2021-05-24 03:00:00');
INSERT INTO `schedule_job_log` VALUES ('573', '1', 'testTask', 'renren', '0', null, '2', '2021-05-24 03:30:00');
INSERT INTO `schedule_job_log` VALUES ('574', '1', 'testTask', 'renren', '0', null, '0', '2021-05-24 04:00:00');
INSERT INTO `schedule_job_log` VALUES ('575', '1', 'testTask', 'renren', '0', null, '0', '2021-05-24 04:30:00');
INSERT INTO `schedule_job_log` VALUES ('576', '1', 'testTask', 'renren', '0', null, '3', '2021-05-24 05:00:00');
INSERT INTO `schedule_job_log` VALUES ('577', '1', 'testTask', 'renren', '0', null, '4', '2021-05-24 05:30:00');
INSERT INTO `schedule_job_log` VALUES ('578', '1', 'testTask', 'renren', '0', null, '0', '2021-05-24 06:00:00');
INSERT INTO `schedule_job_log` VALUES ('579', '1', 'testTask', 'renren', '0', null, '1201', '2021-05-24 06:30:00');
INSERT INTO `schedule_job_log` VALUES ('580', '1', 'testTask', 'renren', '0', null, '2', '2021-05-24 07:00:00');
INSERT INTO `schedule_job_log` VALUES ('581', '1', 'testTask', 'renren', '0', null, '3', '2021-05-24 07:30:00');
INSERT INTO `schedule_job_log` VALUES ('582', '1', 'testTask', 'renren', '0', null, '1080', '2021-05-24 08:00:00');
INSERT INTO `schedule_job_log` VALUES ('583', '1', 'testTask', 'renren', '0', null, '2', '2021-05-24 08:30:00');
INSERT INTO `schedule_job_log` VALUES ('584', '1', 'testTask', 'renren', '0', null, '587', '2021-05-24 09:00:00');
INSERT INTO `schedule_job_log` VALUES ('585', '1', 'testTask', 'renren', '0', null, '520', '2021-05-24 09:30:00');
INSERT INTO `schedule_job_log` VALUES ('586', '1', 'testTask', 'renren', '0', null, '4', '2021-05-24 10:00:00');
INSERT INTO `schedule_job_log` VALUES ('587', '1', 'testTask', 'renren', '0', null, '3', '2021-05-24 10:30:00');
INSERT INTO `schedule_job_log` VALUES ('588', '1', 'testTask', 'renren', '0', null, '7', '2021-05-24 11:00:00');
INSERT INTO `schedule_job_log` VALUES ('589', '1', 'testTask', 'renren', '0', null, '2', '2021-05-24 11:30:00');
INSERT INTO `schedule_job_log` VALUES ('590', '1', 'testTask', 'renren', '0', null, '0', '2021-05-24 12:00:00');
INSERT INTO `schedule_job_log` VALUES ('591', '1', 'testTask', 'renren', '0', null, '6', '2021-05-24 12:30:00');
INSERT INTO `schedule_job_log` VALUES ('592', '1', 'testTask', 'renren', '0', null, '318', '2021-05-24 13:00:00');
INSERT INTO `schedule_job_log` VALUES ('593', '1', 'testTask', 'renren', '0', null, '1166', '2021-05-24 13:30:00');
INSERT INTO `schedule_job_log` VALUES ('594', '1', 'testTask', 'renren', '0', null, '1', '2021-05-24 14:00:00');
INSERT INTO `schedule_job_log` VALUES ('595', '1', 'testTask', 'renren', '0', null, '0', '2021-05-24 14:30:00');
INSERT INTO `schedule_job_log` VALUES ('596', '1', 'testTask', 'renren', '0', null, '3', '2021-05-24 15:00:00');
INSERT INTO `schedule_job_log` VALUES ('597', '1', 'testTask', 'renren', '0', null, '0', '2021-05-24 15:30:00');
INSERT INTO `schedule_job_log` VALUES ('598', '1', 'testTask', 'renren', '0', null, '642', '2021-05-24 16:00:00');
INSERT INTO `schedule_job_log` VALUES ('599', '1', 'testTask', 'renren', '0', null, '14', '2021-05-24 16:30:00');
INSERT INTO `schedule_job_log` VALUES ('600', '1', 'testTask', 'renren', '0', null, '1', '2021-05-24 17:00:00');
INSERT INTO `schedule_job_log` VALUES ('601', '1', 'testTask', 'renren', '0', null, '8', '2021-05-24 17:30:00');
INSERT INTO `schedule_job_log` VALUES ('602', '1', 'testTask', 'renren', '0', null, '8', '2021-05-24 18:00:00');
INSERT INTO `schedule_job_log` VALUES ('603', '1', 'testTask', 'renren', '0', null, '1237', '2021-05-24 18:30:00');
INSERT INTO `schedule_job_log` VALUES ('604', '1', 'testTask', 'renren', '0', null, '3', '2021-05-24 19:00:00');
INSERT INTO `schedule_job_log` VALUES ('605', '1', 'testTask', 'renren', '0', null, '0', '2021-05-24 19:30:00');
INSERT INTO `schedule_job_log` VALUES ('606', '1', 'testTask', 'renren', '0', null, '4', '2021-05-24 20:00:00');
INSERT INTO `schedule_job_log` VALUES ('607', '1', 'testTask', 'renren', '0', null, '1', '2021-05-24 20:30:00');
INSERT INTO `schedule_job_log` VALUES ('608', '1', 'testTask', 'renren', '0', null, '0', '2021-05-24 21:00:00');
INSERT INTO `schedule_job_log` VALUES ('609', '1', 'testTask', 'renren', '0', null, '0', '2021-05-24 21:30:00');
INSERT INTO `schedule_job_log` VALUES ('610', '1', 'testTask', 'renren', '0', null, '1264', '2021-05-24 22:00:00');
INSERT INTO `schedule_job_log` VALUES ('611', '1', 'testTask', 'renren', '0', null, '3', '2021-05-24 22:30:00');
INSERT INTO `schedule_job_log` VALUES ('612', '1', 'testTask', 'renren', '0', null, '2', '2021-05-24 23:00:00');
INSERT INTO `schedule_job_log` VALUES ('613', '1', 'testTask', 'renren', '0', null, '6', '2021-05-24 23:30:00');
INSERT INTO `schedule_job_log` VALUES ('614', '1', 'testTask', 'renren', '0', null, '51', '2021-05-25 00:00:00');
INSERT INTO `schedule_job_log` VALUES ('615', '1', 'testTask', 'renren', '0', null, '1146', '2021-05-25 00:30:00');
INSERT INTO `schedule_job_log` VALUES ('616', '1', 'testTask', 'renren', '0', null, '0', '2021-05-25 01:00:00');
INSERT INTO `schedule_job_log` VALUES ('617', '1', 'testTask', 'renren', '0', null, '0', '2021-05-25 01:30:00');
INSERT INTO `schedule_job_log` VALUES ('618', '1', 'testTask', 'renren', '0', null, '1269', '2021-05-25 02:00:00');
INSERT INTO `schedule_job_log` VALUES ('619', '1', 'testTask', 'renren', '0', null, '503', '2021-05-25 02:30:00');
INSERT INTO `schedule_job_log` VALUES ('620', '1', 'testTask', 'renren', '0', null, '773', '2021-05-25 03:00:00');
INSERT INTO `schedule_job_log` VALUES ('621', '1', 'testTask', 'renren', '0', null, '14', '2021-05-25 03:30:00');
INSERT INTO `schedule_job_log` VALUES ('622', '1', 'testTask', 'renren', '0', null, '7', '2021-05-25 04:00:00');
INSERT INTO `schedule_job_log` VALUES ('623', '1', 'testTask', 'renren', '0', null, '2', '2021-05-25 04:30:00');
INSERT INTO `schedule_job_log` VALUES ('624', '1', 'testTask', 'renren', '0', null, '1', '2021-05-25 05:00:00');
INSERT INTO `schedule_job_log` VALUES ('625', '1', 'testTask', 'renren', '0', null, '6', '2021-05-25 05:30:00');
INSERT INTO `schedule_job_log` VALUES ('626', '1', 'testTask', 'renren', '0', null, '6', '2021-05-25 06:00:00');
INSERT INTO `schedule_job_log` VALUES ('627', '1', 'testTask', 'renren', '0', null, '8', '2021-05-25 06:30:00');
INSERT INTO `schedule_job_log` VALUES ('628', '1', 'testTask', 'renren', '0', null, '2', '2021-05-25 07:00:00');
INSERT INTO `schedule_job_log` VALUES ('629', '1', 'testTask', 'renren', '0', null, '2', '2021-05-25 07:30:00');
INSERT INTO `schedule_job_log` VALUES ('630', '1', 'testTask', 'renren', '0', null, '0', '2021-05-25 08:00:00');
INSERT INTO `schedule_job_log` VALUES ('631', '1', 'testTask', 'renren', '0', null, '1069', '2021-05-25 08:30:00');
INSERT INTO `schedule_job_log` VALUES ('632', '1', 'testTask', 'renren', '0', null, '3', '2021-05-25 09:00:00');
INSERT INTO `schedule_job_log` VALUES ('633', '1', 'testTask', 'renren', '0', null, '3', '2021-05-25 09:30:00');
INSERT INTO `schedule_job_log` VALUES ('634', '1', 'testTask', 'renren', '0', null, '0', '2021-05-25 10:00:00');
INSERT INTO `schedule_job_log` VALUES ('635', '1', 'testTask', 'renren', '0', null, '5', '2021-05-25 10:30:00');
INSERT INTO `schedule_job_log` VALUES ('636', '1', 'testTask', 'renren', '0', null, '563', '2021-05-25 11:00:00');
INSERT INTO `schedule_job_log` VALUES ('637', '1', 'testTask', 'renren', '0', null, '0', '2021-05-25 11:30:00');
INSERT INTO `schedule_job_log` VALUES ('638', '1', 'testTask', 'renren', '0', null, '5', '2021-05-25 12:00:00');
INSERT INTO `schedule_job_log` VALUES ('639', '1', 'testTask', 'renren', '0', null, '916', '2021-05-25 12:30:00');
INSERT INTO `schedule_job_log` VALUES ('640', '1', 'testTask', 'renren', '0', null, '2', '2021-05-25 13:00:00');
INSERT INTO `schedule_job_log` VALUES ('641', '1', 'testTask', 'renren', '0', null, '7', '2021-05-25 13:30:00');
INSERT INTO `schedule_job_log` VALUES ('642', '1', 'testTask', 'renren', '0', null, '1282', '2021-05-25 14:00:00');
INSERT INTO `schedule_job_log` VALUES ('643', '1', 'testTask', 'renren', '0', null, '4', '2021-05-25 14:30:00');
INSERT INTO `schedule_job_log` VALUES ('644', '1', 'testTask', 'renren', '0', null, '3', '2021-05-25 15:00:00');
INSERT INTO `schedule_job_log` VALUES ('645', '1', 'testTask', 'renren', '0', null, '2', '2021-05-25 15:30:00');
INSERT INTO `schedule_job_log` VALUES ('646', '1', 'testTask', 'renren', '0', null, '4', '2021-05-25 16:00:00');
INSERT INTO `schedule_job_log` VALUES ('647', '1', 'testTask', 'renren', '0', null, '971', '2021-05-25 16:30:00');
INSERT INTO `schedule_job_log` VALUES ('648', '1', 'testTask', 'renren', '0', null, '6', '2021-05-25 17:00:00');
INSERT INTO `schedule_job_log` VALUES ('649', '1', 'testTask', 'renren', '0', null, '878', '2021-05-25 17:30:00');
INSERT INTO `schedule_job_log` VALUES ('650', '1', 'testTask', 'renren', '0', null, '3', '2021-05-25 18:00:00');
INSERT INTO `schedule_job_log` VALUES ('651', '1', 'testTask', 'renren', '0', null, '4', '2021-05-25 18:30:00');
INSERT INTO `schedule_job_log` VALUES ('652', '1', 'testTask', 'renren', '0', null, '6', '2021-05-25 19:00:00');
INSERT INTO `schedule_job_log` VALUES ('653', '1', 'testTask', 'renren', '0', null, '601', '2021-05-25 19:30:00');
INSERT INTO `schedule_job_log` VALUES ('654', '1', 'testTask', 'renren', '0', null, '0', '2021-05-25 20:00:00');
INSERT INTO `schedule_job_log` VALUES ('655', '1', 'testTask', 'renren', '0', null, '3', '2021-05-25 20:30:00');
INSERT INTO `schedule_job_log` VALUES ('656', '1', 'testTask', 'renren', '0', null, '1134', '2021-05-25 21:00:00');
INSERT INTO `schedule_job_log` VALUES ('657', '1', 'testTask', 'renren', '0', null, '7', '2021-05-25 21:30:00');
INSERT INTO `schedule_job_log` VALUES ('658', '1', 'testTask', 'renren', '0', null, '4', '2021-05-25 22:00:00');
INSERT INTO `schedule_job_log` VALUES ('659', '1', 'testTask', 'renren', '0', null, '0', '2021-05-25 22:30:00');
INSERT INTO `schedule_job_log` VALUES ('660', '1', 'testTask', 'renren', '0', null, '0', '2021-05-25 23:00:00');
INSERT INTO `schedule_job_log` VALUES ('661', '1', 'testTask', 'renren', '0', null, '2', '2021-05-25 23:30:00');
INSERT INTO `schedule_job_log` VALUES ('662', '1', 'testTask', 'renren', '0', null, '76', '2021-05-26 00:00:00');
INSERT INTO `schedule_job_log` VALUES ('663', '1', 'testTask', 'renren', '0', null, '1298', '2021-05-26 00:30:00');
INSERT INTO `schedule_job_log` VALUES ('664', '1', 'testTask', 'renren', '0', null, '7', '2021-05-26 01:00:00');
INSERT INTO `schedule_job_log` VALUES ('665', '1', 'testTask', 'renren', '0', null, '2', '2021-05-26 01:30:00');
INSERT INTO `schedule_job_log` VALUES ('666', '1', 'testTask', 'renren', '0', null, '1016', '2021-05-26 02:00:00');
INSERT INTO `schedule_job_log` VALUES ('667', '1', 'testTask', 'renren', '0', null, '7', '2021-05-26 02:30:00');
INSERT INTO `schedule_job_log` VALUES ('668', '1', 'testTask', 'renren', '0', null, '4', '2021-05-26 03:00:00');
INSERT INTO `schedule_job_log` VALUES ('669', '1', 'testTask', 'renren', '0', null, '854', '2021-05-26 03:30:00');
INSERT INTO `schedule_job_log` VALUES ('670', '1', 'testTask', 'renren', '0', null, '1064', '2021-05-26 04:00:00');
INSERT INTO `schedule_job_log` VALUES ('671', '1', 'testTask', 'renren', '0', null, '555', '2021-05-26 04:30:00');
INSERT INTO `schedule_job_log` VALUES ('672', '1', 'testTask', 'renren', '0', null, '1', '2021-05-26 05:00:00');
INSERT INTO `schedule_job_log` VALUES ('673', '1', 'testTask', 'renren', '0', null, '3', '2021-05-26 05:30:00');
INSERT INTO `schedule_job_log` VALUES ('674', '1', 'testTask', 'renren', '0', null, '0', '2021-05-26 06:00:00');
INSERT INTO `schedule_job_log` VALUES ('675', '1', 'testTask', 'renren', '0', null, '3', '2021-05-26 06:30:00');
INSERT INTO `schedule_job_log` VALUES ('676', '1', 'testTask', 'renren', '0', null, '5', '2021-05-26 07:00:00');
INSERT INTO `schedule_job_log` VALUES ('677', '1', 'testTask', 'renren', '0', null, '1199', '2021-05-26 07:30:00');
INSERT INTO `schedule_job_log` VALUES ('678', '1', 'testTask', 'renren', '0', null, '2', '2021-05-26 08:00:00');
INSERT INTO `schedule_job_log` VALUES ('679', '1', 'testTask', 'renren', '0', null, '2', '2021-05-26 08:30:00');
INSERT INTO `schedule_job_log` VALUES ('680', '1', 'testTask', 'renren', '0', null, '1258', '2021-05-26 09:00:00');
INSERT INTO `schedule_job_log` VALUES ('681', '1', 'testTask', 'renren', '0', null, '8', '2021-05-26 09:30:00');
INSERT INTO `schedule_job_log` VALUES ('682', '1', 'testTask', 'renren', '0', null, '7', '2021-05-26 10:00:00');
INSERT INTO `schedule_job_log` VALUES ('683', '1', 'testTask', 'renren', '0', null, '1165', '2021-05-26 10:30:00');
INSERT INTO `schedule_job_log` VALUES ('684', '1', 'testTask', 'renren', '0', null, '0', '2021-05-26 11:00:00');
INSERT INTO `schedule_job_log` VALUES ('685', '1', 'testTask', 'renren', '0', null, '1017', '2021-05-26 11:30:00');
INSERT INTO `schedule_job_log` VALUES ('686', '1', 'testTask', 'renren', '0', null, '2', '2021-05-26 12:00:00');
INSERT INTO `schedule_job_log` VALUES ('687', '1', 'testTask', 'renren', '0', null, '506', '2021-05-26 12:30:00');
INSERT INTO `schedule_job_log` VALUES ('688', '1', 'testTask', 'renren', '0', null, '5', '2021-05-26 13:00:00');
INSERT INTO `schedule_job_log` VALUES ('689', '1', 'testTask', 'renren', '0', null, '9', '2021-05-26 13:30:00');
INSERT INTO `schedule_job_log` VALUES ('690', '1', 'testTask', 'renren', '0', null, '0', '2021-05-26 14:00:00');
INSERT INTO `schedule_job_log` VALUES ('691', '1', 'testTask', 'renren', '0', null, '760', '2021-05-26 14:30:00');
INSERT INTO `schedule_job_log` VALUES ('692', '1', 'testTask', 'renren', '0', null, '3', '2021-05-26 15:00:00');
INSERT INTO `schedule_job_log` VALUES ('693', '1', 'testTask', 'renren', '0', null, '22', '2021-05-26 15:30:00');
INSERT INTO `schedule_job_log` VALUES ('694', '1', 'testTask', 'renren', '0', null, '4', '2021-05-26 16:00:00');
INSERT INTO `schedule_job_log` VALUES ('695', '1', 'testTask', 'renren', '0', null, '2', '2021-05-26 16:30:00');
INSERT INTO `schedule_job_log` VALUES ('696', '1', 'testTask', 'renren', '0', null, '1075', '2021-05-26 17:00:00');
INSERT INTO `schedule_job_log` VALUES ('697', '1', 'testTask', 'renren', '0', null, '476', '2021-05-26 17:30:00');
INSERT INTO `schedule_job_log` VALUES ('698', '1', 'testTask', 'renren', '0', null, '11', '2021-05-26 18:00:00');
INSERT INTO `schedule_job_log` VALUES ('699', '1', 'testTask', 'renren', '0', null, '1', '2021-05-26 18:30:00');
INSERT INTO `schedule_job_log` VALUES ('700', '1', 'testTask', 'renren', '0', null, '10', '2021-05-26 19:00:00');
INSERT INTO `schedule_job_log` VALUES ('701', '1', 'testTask', 'renren', '0', null, '0', '2021-05-26 19:30:00');
INSERT INTO `schedule_job_log` VALUES ('702', '1', 'testTask', 'renren', '0', null, '4', '2021-05-26 20:00:00');
INSERT INTO `schedule_job_log` VALUES ('703', '1', 'testTask', 'renren', '0', null, '14', '2021-05-26 20:30:00');
INSERT INTO `schedule_job_log` VALUES ('704', '1', 'testTask', 'renren', '0', null, '774', '2021-05-26 21:00:00');
INSERT INTO `schedule_job_log` VALUES ('705', '1', 'testTask', 'renren', '0', null, '1292', '2021-05-26 21:30:00');
INSERT INTO `schedule_job_log` VALUES ('706', '1', 'testTask', 'renren', '0', null, '1178', '2021-05-26 22:00:00');
INSERT INTO `schedule_job_log` VALUES ('707', '1', 'testTask', 'renren', '0', null, '854', '2021-05-26 22:30:00');
INSERT INTO `schedule_job_log` VALUES ('708', '1', 'testTask', 'renren', '0', null, '1400', '2021-05-26 23:00:00');
INSERT INTO `schedule_job_log` VALUES ('709', '1', 'testTask', 'renren', '0', null, '2', '2021-05-26 23:30:00');
INSERT INTO `schedule_job_log` VALUES ('710', '1', 'testTask', 'renren', '0', null, '55', '2021-05-27 00:00:00');
INSERT INTO `schedule_job_log` VALUES ('711', '1', 'testTask', 'renren', '0', null, '0', '2021-05-27 00:30:00');
INSERT INTO `schedule_job_log` VALUES ('712', '1', 'testTask', 'renren', '0', null, '4', '2021-05-27 01:00:00');
INSERT INTO `schedule_job_log` VALUES ('713', '1', 'testTask', 'renren', '0', null, '0', '2021-05-27 01:30:00');
INSERT INTO `schedule_job_log` VALUES ('714', '1', 'testTask', 'renren', '0', null, '6', '2021-05-27 02:00:00');
INSERT INTO `schedule_job_log` VALUES ('715', '1', 'testTask', 'renren', '0', null, '0', '2021-05-27 02:30:00');
INSERT INTO `schedule_job_log` VALUES ('716', '1', 'testTask', 'renren', '0', null, '5', '2021-05-27 03:00:00');
INSERT INTO `schedule_job_log` VALUES ('717', '1', 'testTask', 'renren', '0', null, '37', '2021-05-27 03:30:00');
INSERT INTO `schedule_job_log` VALUES ('718', '1', 'testTask', 'renren', '0', null, '598', '2021-05-27 04:00:00');
INSERT INTO `schedule_job_log` VALUES ('719', '1', 'testTask', 'renren', '0', null, '0', '2021-05-27 04:30:00');
INSERT INTO `schedule_job_log` VALUES ('720', '1', 'testTask', 'renren', '0', null, '1209', '2021-05-27 05:00:00');
INSERT INTO `schedule_job_log` VALUES ('721', '1', 'testTask', 'renren', '0', null, '834', '2021-05-27 05:30:00');
INSERT INTO `schedule_job_log` VALUES ('722', '1', 'testTask', 'renren', '0', null, '1247', '2021-05-27 06:00:00');
INSERT INTO `schedule_job_log` VALUES ('723', '1', 'testTask', 'renren', '0', null, '1398', '2021-05-27 06:30:00');
INSERT INTO `schedule_job_log` VALUES ('724', '1', 'testTask', 'renren', '0', null, '1144', '2021-05-27 07:00:00');
INSERT INTO `schedule_job_log` VALUES ('725', '1', 'testTask', 'renren', '0', null, '19', '2021-05-27 07:30:00');
INSERT INTO `schedule_job_log` VALUES ('726', '1', 'testTask', 'renren', '0', null, '4', '2021-05-27 08:00:00');
INSERT INTO `schedule_job_log` VALUES ('727', '1', 'testTask', 'renren', '0', null, '6', '2021-05-27 08:30:00');
INSERT INTO `schedule_job_log` VALUES ('728', '1', 'testTask', 'renren', '0', null, '656', '2021-05-27 09:00:00');
INSERT INTO `schedule_job_log` VALUES ('729', '1', 'testTask', 'renren', '0', null, '0', '2021-05-27 09:30:00');
INSERT INTO `schedule_job_log` VALUES ('730', '1', 'testTask', 'renren', '0', null, '3', '2021-05-27 10:00:00');
INSERT INTO `schedule_job_log` VALUES ('731', '1', 'testTask', 'renren', '0', null, '324', '2021-05-27 10:30:00');
INSERT INTO `schedule_job_log` VALUES ('732', '1', 'testTask', 'renren', '0', null, '872', '2021-05-27 11:00:00');
INSERT INTO `schedule_job_log` VALUES ('733', '1', 'testTask', 'renren', '0', null, '933', '2021-05-27 11:30:00');
INSERT INTO `schedule_job_log` VALUES ('734', '1', 'testTask', 'renren', '0', null, '425', '2021-05-27 12:00:00');
INSERT INTO `schedule_job_log` VALUES ('735', '1', 'testTask', 'renren', '0', null, '8', '2021-05-27 12:30:00');
INSERT INTO `schedule_job_log` VALUES ('736', '1', 'testTask', 'renren', '0', null, '0', '2021-05-27 13:00:00');
INSERT INTO `schedule_job_log` VALUES ('737', '1', 'testTask', 'renren', '0', null, '422', '2021-05-27 13:30:00');
INSERT INTO `schedule_job_log` VALUES ('738', '1', 'testTask', 'renren', '0', null, '1260', '2021-05-27 14:00:00');
INSERT INTO `schedule_job_log` VALUES ('739', '1', 'testTask', 'renren', '0', null, '1250', '2021-05-27 14:30:00');
INSERT INTO `schedule_job_log` VALUES ('740', '1', 'testTask', 'renren', '0', null, '9', '2021-05-27 15:00:00');
INSERT INTO `schedule_job_log` VALUES ('741', '1', 'testTask', 'renren', '0', null, '5', '2021-05-27 15:30:00');
INSERT INTO `schedule_job_log` VALUES ('742', '1', 'testTask', 'renren', '0', null, '4', '2021-05-27 16:00:00');
INSERT INTO `schedule_job_log` VALUES ('743', '1', 'testTask', 'renren', '0', null, '442', '2021-05-27 16:30:00');
INSERT INTO `schedule_job_log` VALUES ('744', '1', 'testTask', 'renren', '0', null, '1111', '2021-05-27 17:00:00');
INSERT INTO `schedule_job_log` VALUES ('745', '1', 'testTask', 'renren', '0', null, '6', '2021-05-27 17:30:00');
INSERT INTO `schedule_job_log` VALUES ('746', '1', 'testTask', 'renren', '0', null, '1', '2021-05-27 18:00:00');
INSERT INTO `schedule_job_log` VALUES ('747', '1', 'testTask', 'renren', '0', null, '7', '2021-05-27 18:30:00');
INSERT INTO `schedule_job_log` VALUES ('748', '1', 'testTask', 'renren', '0', null, '6', '2021-05-27 19:00:00');
INSERT INTO `schedule_job_log` VALUES ('749', '1', 'testTask', 'renren', '0', null, '1215', '2021-05-27 19:30:00');
INSERT INTO `schedule_job_log` VALUES ('750', '1', 'testTask', 'renren', '0', null, '1245', '2021-05-27 20:00:00');
INSERT INTO `schedule_job_log` VALUES ('751', '1', 'testTask', 'renren', '0', null, '689', '2021-05-27 20:30:00');
INSERT INTO `schedule_job_log` VALUES ('752', '1', 'testTask', 'renren', '0', null, '9', '2021-05-27 21:00:00');
INSERT INTO `schedule_job_log` VALUES ('753', '1', 'testTask', 'renren', '0', null, '0', '2021-05-27 21:30:00');
INSERT INTO `schedule_job_log` VALUES ('754', '1', 'testTask', 'renren', '0', null, '884', '2021-05-27 22:00:00');
INSERT INTO `schedule_job_log` VALUES ('755', '1', 'testTask', 'renren', '0', null, '4', '2021-05-27 22:30:00');
INSERT INTO `schedule_job_log` VALUES ('756', '1', 'testTask', 'renren', '0', null, '2', '2021-05-27 23:00:00');
INSERT INTO `schedule_job_log` VALUES ('757', '1', 'testTask', 'renren', '0', null, '16', '2021-05-27 23:30:00');
INSERT INTO `schedule_job_log` VALUES ('758', '1', 'testTask', 'renren', '0', null, '56', '2021-05-28 00:00:00');
INSERT INTO `schedule_job_log` VALUES ('759', '1', 'testTask', 'renren', '0', null, '13', '2021-05-28 00:30:00');
INSERT INTO `schedule_job_log` VALUES ('760', '1', 'testTask', 'renren', '0', null, '2', '2021-05-28 01:00:00');
INSERT INTO `schedule_job_log` VALUES ('761', '1', 'testTask', 'renren', '0', null, '9', '2021-05-28 01:30:00');
INSERT INTO `schedule_job_log` VALUES ('762', '1', 'testTask', 'renren', '0', null, '8', '2021-05-28 02:00:00');
INSERT INTO `schedule_job_log` VALUES ('763', '1', 'testTask', 'renren', '0', null, '7', '2021-05-28 02:30:00');
INSERT INTO `schedule_job_log` VALUES ('764', '1', 'testTask', 'renren', '0', null, '2', '2021-05-28 03:00:00');
INSERT INTO `schedule_job_log` VALUES ('765', '1', 'testTask', 'renren', '0', null, '2', '2021-05-28 03:30:00');
INSERT INTO `schedule_job_log` VALUES ('766', '1', 'testTask', 'renren', '0', null, '0', '2021-05-28 04:00:00');
INSERT INTO `schedule_job_log` VALUES ('767', '1', 'testTask', 'renren', '0', null, '3', '2021-05-28 04:30:00');
INSERT INTO `schedule_job_log` VALUES ('768', '1', 'testTask', 'renren', '0', null, '741', '2021-05-28 05:00:00');
INSERT INTO `schedule_job_log` VALUES ('769', '1', 'testTask', 'renren', '0', null, '0', '2021-05-28 05:30:00');
INSERT INTO `schedule_job_log` VALUES ('770', '1', 'testTask', 'renren', '0', null, '3', '2021-05-28 06:00:00');
INSERT INTO `schedule_job_log` VALUES ('771', '1', 'testTask', 'renren', '0', null, '2', '2021-05-28 06:30:00');
INSERT INTO `schedule_job_log` VALUES ('772', '1', 'testTask', 'renren', '0', null, '450', '2021-05-28 07:00:00');
INSERT INTO `schedule_job_log` VALUES ('773', '1', 'testTask', 'renren', '0', null, '5', '2021-05-28 07:30:00');
INSERT INTO `schedule_job_log` VALUES ('774', '1', 'testTask', 'renren', '0', null, '5', '2021-05-28 08:00:00');
INSERT INTO `schedule_job_log` VALUES ('775', '1', 'testTask', 'renren', '0', null, '3', '2021-05-28 08:30:00');
INSERT INTO `schedule_job_log` VALUES ('776', '1', 'testTask', 'renren', '0', null, '1697', '2021-05-28 09:00:00');
INSERT INTO `schedule_job_log` VALUES ('777', '1', 'testTask', 'renren', '0', null, '1209', '2021-05-28 09:30:00');
INSERT INTO `schedule_job_log` VALUES ('778', '1', 'testTask', 'renren', '0', null, '1', '2021-05-28 10:00:00');
INSERT INTO `schedule_job_log` VALUES ('779', '1', 'testTask', 'renren', '0', null, '7', '2021-05-28 10:30:00');
INSERT INTO `schedule_job_log` VALUES ('780', '1', 'testTask', 'renren', '0', null, '2', '2021-05-28 11:00:00');
INSERT INTO `schedule_job_log` VALUES ('781', '1', 'testTask', 'renren', '0', null, '918', '2021-05-28 11:30:00');
INSERT INTO `schedule_job_log` VALUES ('782', '1', 'testTask', 'renren', '0', null, '789', '2021-05-28 12:00:00');
INSERT INTO `schedule_job_log` VALUES ('783', '1', 'testTask', 'renren', '0', null, '16', '2021-05-28 12:30:00');
INSERT INTO `schedule_job_log` VALUES ('784', '1', 'testTask', 'renren', '0', null, '908', '2021-05-28 13:00:00');
INSERT INTO `schedule_job_log` VALUES ('785', '1', 'testTask', 'renren', '0', null, '2', '2021-05-28 13:30:00');
INSERT INTO `schedule_job_log` VALUES ('786', '1', 'testTask', 'renren', '0', null, '8', '2021-05-28 14:00:00');
INSERT INTO `schedule_job_log` VALUES ('787', '1', 'testTask', 'renren', '0', null, '10', '2021-05-28 14:30:00');
INSERT INTO `schedule_job_log` VALUES ('788', '1', 'testTask', 'renren', '0', null, '1138', '2021-05-28 15:00:00');
INSERT INTO `schedule_job_log` VALUES ('789', '1', 'testTask', 'renren', '0', null, '7', '2021-05-28 15:30:00');
INSERT INTO `schedule_job_log` VALUES ('790', '1', 'testTask', 'renren', '0', null, '5', '2021-05-28 16:00:00');
INSERT INTO `schedule_job_log` VALUES ('791', '1', 'testTask', 'renren', '0', null, '861', '2021-05-28 16:30:00');
INSERT INTO `schedule_job_log` VALUES ('792', '1', 'testTask', 'renren', '0', null, '0', '2021-05-28 17:00:00');

-- ----------------------------
-- Table structure for `sys_captcha`
-- ----------------------------
DROP TABLE IF EXISTS `sys_captcha`;
CREATE TABLE `sys_captcha` (
  `uuid` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'uuid',
  `code` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '验证码',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='系统验证码';

-- ----------------------------
-- Records of sys_captcha
-- ----------------------------
INSERT INTO `sys_captcha` VALUES ('381cfdb2-2a69-4653-87af-68d0415b19bb', '7n4ga', '2020-08-03 23:12:43');
INSERT INTO `sys_captcha` VALUES ('3de67b53-d287-41e6-85fa-5d9432e9722b', '7ny73', '2020-03-15 19:36:51');
INSERT INTO `sys_captcha` VALUES ('47a06bd4-2e07-4ae8-8d7c-252dd6d8699e', '65fwm', '2020-08-03 23:17:50');
INSERT INTO `sys_captcha` VALUES ('4909766f-fe2c-4277-8507-ed1761c38a01', 'cnxcf', '2020-08-03 23:12:20');
INSERT INTO `sys_captcha` VALUES ('5f39812a-3a62-4d17-8c3e-c03ec66e61ca', '6yxx8', '2020-08-03 23:12:40');
INSERT INTO `sys_captcha` VALUES ('6515975d-9bae-4f61-8e4d-04a874e34a28', 'b7g65', '2020-08-03 23:12:14');
INSERT INTO `sys_captcha` VALUES ('6f89997a-282b-46f9-8e61-192501c19175', '4fe4f', '2020-08-03 23:17:57');
INSERT INTO `sys_captcha` VALUES ('7fc52f6c-517c-4883-8440-e99416a9d787', 'f5b4e', '2020-08-03 23:12:39');
INSERT INTO `sys_captcha` VALUES ('a1ab51c8-c115-4e79-8da7-4d285cf3efd8', '7b3b7', '2020-08-20 16:25:58');
INSERT INTO `sys_captcha` VALUES ('a5409fe3-2a92-47cd-84e7-2845dad38591', 'benn5', '2020-08-01 13:18:24');
INSERT INTO `sys_captcha` VALUES ('b038fd02-96d8-42aa-8466-f758178b8217', '2cd82', '2020-03-15 19:36:52');
INSERT INTO `sys_captcha` VALUES ('cc8070e6-5ffc-4cc4-8cb3-7978d8439ce7', 'fe2c8', '2020-08-03 23:12:31');
INSERT INTO `sys_captcha` VALUES ('d20ed40e-2c9b-4c27-88c6-a034af18ec7b', '7nna2', '2020-03-15 19:36:45');
INSERT INTO `sys_captcha` VALUES ('demoData', 'axcwp', '2020-08-20 15:32:58');
INSERT INTO `sys_captcha` VALUES ('dfd3378c-58c3-4f62-8f01-7c5e26aab62c', 'eed52', '2020-03-15 19:36:50');
INSERT INTO `sys_captcha` VALUES ('e67fc8a1-29e3-46cc-8613-202d56e3888b', 'wmdn6', '2020-08-01 13:16:03');
INSERT INTO `sys_captcha` VALUES ('ff97f28e-4ee2-4502-8e33-5b0d5a591ca3', 'daem7', '2020-08-20 16:26:10');

-- ----------------------------
-- Table structure for `sys_config`
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `param_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'key',
  `param_value` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'value',
  `status` tinyint DEFAULT '1' COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `param_key` (`param_key`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='系统配置信息表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('1', 'CLOUD_STORAGE_CONFIG_KEY', '{\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"aliyunDomain\":\"\",\"aliyunEndPoint\":\"\",\"aliyunPrefix\":\"\",\"qcloudBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qiniuAccessKey\":\"NrgMfABZxWLo5B-YYSjoE8-AZ1EISdi1Z3ubLOeZ\",\"qiniuBucketName\":\"ios-app\",\"qiniuDomain\":\"http://7xqbwh.dl1.z0.glb.clouddn.com\",\"qiniuPrefix\":\"upload\",\"qiniuSecretKey\":\"uIwJHevMRWU0VLxFvgy0tAcOdGqasdtVlJkdy6vV\",\"type\":1}', '0', '云存储配置信息');

-- ----------------------------
-- Table structure for `sys_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '请求参数',
  `time` bigint NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='系统日志';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('1', 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":31,\"parentId\":0,\"name\":\"App接口管理\",\"type\":0,\"icon\":\"bianji\",\"orderNum\":0}]', '5', '0:0:0:0:0:0:0:1', '2020-07-19 12:44:14');
INSERT INTO `sys_log` VALUES ('2', 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":32,\"parentId\":31,\"name\":\"视频列表\",\"url\":\"app/videolist\",\"type\":1,\"icon\":\"zhedie\",\"orderNum\":0}]', '13', '0:0:0:0:0:0:0:1', '2020-07-19 12:46:04');
INSERT INTO `sys_log` VALUES ('3', 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":33,\"parentId\":32,\"name\":\"查看\",\"perms\":\"app:videolist:list,app:videolist:info\",\"type\":2,\"orderNum\":0}]', '4', '0:0:0:0:0:0:0:1', '2020-07-19 12:51:12');
INSERT INTO `sys_log` VALUES ('4', 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":34,\"parentId\":32,\"name\":\"新增\",\"perms\":\"app:videolist:save\",\"type\":2,\"orderNum\":0}]', '5', '0:0:0:0:0:0:0:1', '2020-07-19 12:51:44');
INSERT INTO `sys_log` VALUES ('5', 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":35,\"parentId\":32,\"name\":\"修改\",\"perms\":\"app:videolist:update\",\"type\":2,\"orderNum\":0}]', '4', '0:0:0:0:0:0:0:1', '2020-07-19 12:52:10');
INSERT INTO `sys_log` VALUES ('6', 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":36,\"parentId\":32,\"name\":\"删除\",\"perms\":\"app:videolist:delete\",\"type\":2,\"orderNum\":0}]', '14', '0:0:0:0:0:0:0:1', '2020-07-19 12:52:30');
INSERT INTO `sys_log` VALUES ('7', 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":37,\"parentId\":31,\"name\":\"视频分类\",\"url\":\"app/videocategory\",\"perms\":\"\",\"type\":1,\"icon\":\"log\",\"orderNum\":0}]', '9', '0:0:0:0:0:0:0:1', '2020-07-19 15:57:26');
INSERT INTO `sys_log` VALUES ('8', 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":38,\"parentId\":37,\"name\":\"查看\",\"url\":\"\",\"perms\":\"app:videocategory:list,app:videocategory:info\",\"type\":2,\"icon\":\"\",\"orderNum\":0}]', '5', '0:0:0:0:0:0:0:1', '2020-07-19 15:58:13');
INSERT INTO `sys_log` VALUES ('9', 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":39,\"parentId\":37,\"name\":\"新增\",\"url\":\"\",\"perms\":\"app:videocategory:save\",\"type\":2,\"icon\":\"\",\"orderNum\":0}]', '20', '0:0:0:0:0:0:0:1', '2020-07-19 17:39:19');
INSERT INTO `sys_log` VALUES ('10', 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":40,\"parentId\":37,\"name\":\"修改\",\"url\":\"\",\"perms\":\"app:videocategory:update\",\"type\":2,\"icon\":\"\",\"orderNum\":0}]', '14', '0:0:0:0:0:0:0:1', '2020-07-19 17:39:44');
INSERT INTO `sys_log` VALUES ('11', 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":41,\"parentId\":37,\"name\":\"删除\",\"url\":\"\",\"perms\":\"app:videocategory:delete\",\"type\":2,\"icon\":\"\",\"orderNum\":0}]', '12', '0:0:0:0:0:0:0:1', '2020-07-19 17:40:02');
INSERT INTO `sys_log` VALUES ('12', 'admin', '保存角色', 'io.renren.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":1,\"roleName\":\"app\",\"remark\":\"app端登录用户\",\"createUserId\":1,\"menuIdList\":[31,32,33,34,35,36,37,38,39,40,41,-666666],\"createTime\":\"Jul 19, 2020 5:59:37 PM\"}]', '80', '0:0:0:0:0:0:0:1', '2020-07-19 17:59:38');
INSERT INTO `sys_log` VALUES ('13', 'admin', '修改用户', 'io.renren.modules.sys.controller.SysUserController.update()', '[{\"userId\":1,\"username\":\"admin\",\"salt\":\"YzcmCZNvbXocrsz9dm8e\",\"email\":\"root@renren.io\",\"mobile\":\"13612345678\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1}]', '38', '0:0:0:0:0:0:0:1', '2020-07-19 18:00:21');
INSERT INTO `sys_log` VALUES ('14', 'admin', '保存角色', 'io.renren.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":2,\"roleName\":\"系统管理员\",\"remark\":\"后台系统管理员\",\"createUserId\":1,\"menuIdList\":[1,2,15,16,17,18,3,19,20,21,22,4,23,24,25,26,5,6,7,8,9,10,11,12,13,14,27,29,30,-666666],\"createTime\":\"Jul 19, 2020 6:01:42 PM\"}]', '47', '0:0:0:0:0:0:0:1', '2020-07-19 18:01:43');
INSERT INTO `sys_log` VALUES ('15', 'admin', '保存用户', 'io.renren.modules.sys.controller.SysUserController.save()', '[{\"userId\":4,\"username\":\"root\",\"password\":\"c6db632acaff993431124f792982b3a84ddb67b12856adc314954a45d486795d\",\"salt\":\"aH1XLPH0wBuZq2kl2Pas\",\"email\":\"abc@123.com\",\"mobile\":\"18371458987\",\"status\":1,\"roleIdList\":[2],\"createUserId\":1,\"createTime\":\"Jul 19, 2020 6:02:29 PM\"}]', '17', '0:0:0:0:0:0:0:1', '2020-07-19 18:02:30');
INSERT INTO `sys_log` VALUES ('16', 'admin', '保存角色', 'io.renren.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":3,\"roleName\":\"超级管理员\",\"remark\":\"超级管理员\",\"createUserId\":1,\"menuIdList\":[1,2,15,16,17,18,3,19,20,21,22,4,23,24,25,26,5,6,7,8,9,10,11,12,13,14,27,29,30,31,32,33,34,35,36,37,38,39,40,41,-666666],\"createTime\":\"Jul 19, 2020 6:03:06 PM\"}]', '62', '0:0:0:0:0:0:0:1', '2020-07-19 18:03:06');
INSERT INTO `sys_log` VALUES ('17', 'admin', '保存用户', 'io.renren.modules.sys.controller.SysUserController.save()', '[{\"userId\":5,\"username\":\"super\",\"password\":\"115dc8bb37d0925b7e6005c3081d58fa49b74d0ee0d0df98cb18aadef5023274\",\"salt\":\"N48VinVrrKjUkdYztiJe\",\"email\":\"123@qq.com\",\"mobile\":\"18371458526\",\"status\":1,\"roleIdList\":[3],\"createUserId\":1,\"createTime\":\"Jul 19, 2020 6:03:35 PM\"}]', '13', '0:0:0:0:0:0:0:1', '2020-07-19 18:03:35');
INSERT INTO `sys_log` VALUES ('18', 'admin', '修改用户', 'io.renren.modules.sys.controller.SysUserController.update()', '[{\"userId\":1,\"username\":\"admin\",\"salt\":\"YzcmCZNvbXocrsz9dm8e\",\"email\":\"root@renren.io\",\"mobile\":\"13612345678\",\"status\":1,\"roleIdList\":[2],\"createUserId\":1}]', '7', '0:0:0:0:0:0:0:1', '2020-07-19 18:03:51');
INSERT INTO `sys_log` VALUES ('19', 'admin', '修改用户', 'io.renren.modules.sys.controller.SysUserController.update()', '[{\"userId\":4,\"username\":\"root\",\"salt\":\"aH1XLPH0wBuZq2kl2Pas\",\"email\":\"abc@123.com\",\"mobile\":\"18371458987\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1}]', '8', '0:0:0:0:0:0:0:1', '2020-07-19 18:03:57');
INSERT INTO `sys_log` VALUES ('20', 'admin', '修改用户', 'io.renren.modules.sys.controller.SysUserController.update()', '[{\"userId\":4,\"username\":\"user\",\"salt\":\"aH1XLPH0wBuZq2kl2Pas\",\"email\":\"abc@123.com\",\"mobile\":\"18371458987\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1}]', '18', '0:0:0:0:0:0:0:1', '2020-07-19 18:04:06');
INSERT INTO `sys_log` VALUES ('21', 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":42,\"parentId\":31,\"name\":\"资讯列表\",\"url\":\"app/news\",\"type\":1,\"icon\":\"editor\",\"orderNum\":0}]', '19', '0:0:0:0:0:0:0:1', '2020-08-01 13:16:32');
INSERT INTO `sys_log` VALUES ('22', 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":43,\"parentId\":42,\"name\":\"查看\",\"url\":\"\",\"perms\":\"app:news:list,app:news:info\",\"type\":2,\"icon\":\"\",\"orderNum\":0}]', '14', '0:0:0:0:0:0:0:1', '2020-08-01 13:17:29');
INSERT INTO `sys_log` VALUES ('23', 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":44,\"parentId\":42,\"name\":\"新增\",\"url\":\"\",\"perms\":\"app:news:save\",\"type\":2,\"icon\":\"\",\"orderNum\":0}]', '4', '0:0:0:0:0:0:0:1', '2020-08-01 13:17:57');
INSERT INTO `sys_log` VALUES ('24', 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":45,\"parentId\":42,\"name\":\"修改\",\"url\":\"\",\"perms\":\"app:news:update\",\"type\":2,\"icon\":\"\",\"orderNum\":0}]', '13', '0:0:0:0:0:0:0:1', '2020-08-01 13:18:11');
INSERT INTO `sys_log` VALUES ('25', 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":46,\"parentId\":42,\"name\":\"删除\",\"url\":\"\",\"perms\":\"app:news:delete\",\"type\":2,\"icon\":\"\",\"orderNum\":0}]', '15', '0:0:0:0:0:0:0:1', '2020-08-01 13:18:25');

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint NOT NULL AUTO_INCREMENT,
  `parent_id` bigint DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '菜单图标',
  `order_num` int DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '系统管理', null, null, '0', 'system', '0');
INSERT INTO `sys_menu` VALUES ('2', '1', '管理员列表', 'sys/user', null, '1', 'admin', '1');
INSERT INTO `sys_menu` VALUES ('3', '1', '角色管理', 'sys/role', null, '1', 'role', '2');
INSERT INTO `sys_menu` VALUES ('4', '1', '菜单管理', 'sys/menu', null, '1', 'menu', '3');
INSERT INTO `sys_menu` VALUES ('5', '1', 'SQL监控', 'http://localhost:8080/renren-fast/druid/sql.html', null, '1', 'sql', '4');
INSERT INTO `sys_menu` VALUES ('6', '1', '定时任务', 'job/schedule', null, '1', 'job', '5');
INSERT INTO `sys_menu` VALUES ('7', '6', '查看', null, 'sys:schedule:list,sys:schedule:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('8', '6', '新增', null, 'sys:schedule:save', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('9', '6', '修改', null, 'sys:schedule:update', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('10', '6', '删除', null, 'sys:schedule:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('11', '6', '暂停', null, 'sys:schedule:pause', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('12', '6', '恢复', null, 'sys:schedule:resume', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('13', '6', '立即执行', null, 'sys:schedule:run', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('14', '6', '日志列表', null, 'sys:schedule:log', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('15', '2', '查看', null, 'sys:user:list,sys:user:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('16', '2', '新增', null, 'sys:user:save,sys:role:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('17', '2', '修改', null, 'sys:user:update,sys:role:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('18', '2', '删除', null, 'sys:user:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('19', '3', '查看', null, 'sys:role:list,sys:role:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('20', '3', '新增', null, 'sys:role:save,sys:menu:list', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('21', '3', '修改', null, 'sys:role:update,sys:menu:list', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('22', '3', '删除', null, 'sys:role:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('23', '4', '查看', null, 'sys:menu:list,sys:menu:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('24', '4', '新增', null, 'sys:menu:save,sys:menu:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('25', '4', '修改', null, 'sys:menu:update,sys:menu:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('26', '4', '删除', null, 'sys:menu:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('27', '1', '参数管理', 'sys/config', 'sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete', '1', 'config', '6');
INSERT INTO `sys_menu` VALUES ('29', '1', '系统日志', 'sys/log', 'sys:log:list', '1', 'log', '7');
INSERT INTO `sys_menu` VALUES ('30', '1', '文件上传', 'oss/oss', 'sys:oss:all', '1', 'oss', '6');
INSERT INTO `sys_menu` VALUES ('31', '0', 'App接口管理', null, null, '0', 'bianji', '0');
INSERT INTO `sys_menu` VALUES ('32', '31', '视频列表', 'app/videolist', null, '1', 'zhedie', '0');
INSERT INTO `sys_menu` VALUES ('33', '32', '查看', null, 'app:videolist:list,app:videolist:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('34', '32', '新增', null, 'app:videolist:save', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('35', '32', '修改', null, 'app:videolist:update', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('36', '32', '删除', null, 'app:videolist:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('37', '31', '视频分类', 'app/videocategory', '', '1', 'log', '0');
INSERT INTO `sys_menu` VALUES ('38', '37', '查看', '', 'app:videocategory:list,app:videocategory:info', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('39', '37', '新增', '', 'app:videocategory:save', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('40', '37', '修改', '', 'app:videocategory:update', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('41', '37', '删除', '', 'app:videocategory:delete', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('42', '31', '资讯列表', 'app/news', null, '1', 'editor', '0');
INSERT INTO `sys_menu` VALUES ('43', '42', '查看', '', 'app:news:list,app:news:info', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('44', '42', '新增', '', 'app:news:save', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('45', '42', '修改', '', 'app:news:update', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('46', '42', '删除', '', 'app:news:delete', '2', '', '0');

-- ----------------------------
-- Table structure for `sys_oss`
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss`;
CREATE TABLE `sys_oss` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'URL地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='文件上传';

-- ----------------------------
-- Records of sys_oss
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'app', 'app端登录用户', '1', '2020-07-19 17:59:38');
INSERT INTO `sys_role` VALUES ('2', '系统管理员', '后台系统管理员', '1', '2020-07-19 18:01:43');
INSERT INTO `sys_role` VALUES ('3', '超级管理员', '超级管理员', '1', '2020-07-19 18:03:06');

-- ----------------------------
-- Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1', '31');
INSERT INTO `sys_role_menu` VALUES ('2', '1', '32');
INSERT INTO `sys_role_menu` VALUES ('3', '1', '33');
INSERT INTO `sys_role_menu` VALUES ('4', '1', '34');
INSERT INTO `sys_role_menu` VALUES ('5', '1', '35');
INSERT INTO `sys_role_menu` VALUES ('6', '1', '36');
INSERT INTO `sys_role_menu` VALUES ('7', '1', '37');
INSERT INTO `sys_role_menu` VALUES ('8', '1', '38');
INSERT INTO `sys_role_menu` VALUES ('9', '1', '39');
INSERT INTO `sys_role_menu` VALUES ('10', '1', '40');
INSERT INTO `sys_role_menu` VALUES ('11', '1', '41');
INSERT INTO `sys_role_menu` VALUES ('12', '1', '-666666');
INSERT INTO `sys_role_menu` VALUES ('13', '2', '1');
INSERT INTO `sys_role_menu` VALUES ('14', '2', '2');
INSERT INTO `sys_role_menu` VALUES ('15', '2', '15');
INSERT INTO `sys_role_menu` VALUES ('16', '2', '16');
INSERT INTO `sys_role_menu` VALUES ('17', '2', '17');
INSERT INTO `sys_role_menu` VALUES ('18', '2', '18');
INSERT INTO `sys_role_menu` VALUES ('19', '2', '3');
INSERT INTO `sys_role_menu` VALUES ('20', '2', '19');
INSERT INTO `sys_role_menu` VALUES ('21', '2', '20');
INSERT INTO `sys_role_menu` VALUES ('22', '2', '21');
INSERT INTO `sys_role_menu` VALUES ('23', '2', '22');
INSERT INTO `sys_role_menu` VALUES ('24', '2', '4');
INSERT INTO `sys_role_menu` VALUES ('25', '2', '23');
INSERT INTO `sys_role_menu` VALUES ('26', '2', '24');
INSERT INTO `sys_role_menu` VALUES ('27', '2', '25');
INSERT INTO `sys_role_menu` VALUES ('28', '2', '26');
INSERT INTO `sys_role_menu` VALUES ('29', '2', '5');
INSERT INTO `sys_role_menu` VALUES ('30', '2', '6');
INSERT INTO `sys_role_menu` VALUES ('31', '2', '7');
INSERT INTO `sys_role_menu` VALUES ('32', '2', '8');
INSERT INTO `sys_role_menu` VALUES ('33', '2', '9');
INSERT INTO `sys_role_menu` VALUES ('34', '2', '10');
INSERT INTO `sys_role_menu` VALUES ('35', '2', '11');
INSERT INTO `sys_role_menu` VALUES ('36', '2', '12');
INSERT INTO `sys_role_menu` VALUES ('37', '2', '13');
INSERT INTO `sys_role_menu` VALUES ('38', '2', '14');
INSERT INTO `sys_role_menu` VALUES ('39', '2', '27');
INSERT INTO `sys_role_menu` VALUES ('40', '2', '29');
INSERT INTO `sys_role_menu` VALUES ('41', '2', '30');
INSERT INTO `sys_role_menu` VALUES ('42', '2', '-666666');
INSERT INTO `sys_role_menu` VALUES ('43', '3', '1');
INSERT INTO `sys_role_menu` VALUES ('44', '3', '2');
INSERT INTO `sys_role_menu` VALUES ('45', '3', '15');
INSERT INTO `sys_role_menu` VALUES ('46', '3', '16');
INSERT INTO `sys_role_menu` VALUES ('47', '3', '17');
INSERT INTO `sys_role_menu` VALUES ('48', '3', '18');
INSERT INTO `sys_role_menu` VALUES ('49', '3', '3');
INSERT INTO `sys_role_menu` VALUES ('50', '3', '19');
INSERT INTO `sys_role_menu` VALUES ('51', '3', '20');
INSERT INTO `sys_role_menu` VALUES ('52', '3', '21');
INSERT INTO `sys_role_menu` VALUES ('53', '3', '22');
INSERT INTO `sys_role_menu` VALUES ('54', '3', '4');
INSERT INTO `sys_role_menu` VALUES ('55', '3', '23');
INSERT INTO `sys_role_menu` VALUES ('56', '3', '24');
INSERT INTO `sys_role_menu` VALUES ('57', '3', '25');
INSERT INTO `sys_role_menu` VALUES ('58', '3', '26');
INSERT INTO `sys_role_menu` VALUES ('59', '3', '5');
INSERT INTO `sys_role_menu` VALUES ('60', '3', '6');
INSERT INTO `sys_role_menu` VALUES ('61', '3', '7');
INSERT INTO `sys_role_menu` VALUES ('62', '3', '8');
INSERT INTO `sys_role_menu` VALUES ('63', '3', '9');
INSERT INTO `sys_role_menu` VALUES ('64', '3', '10');
INSERT INTO `sys_role_menu` VALUES ('65', '3', '11');
INSERT INTO `sys_role_menu` VALUES ('66', '3', '12');
INSERT INTO `sys_role_menu` VALUES ('67', '3', '13');
INSERT INTO `sys_role_menu` VALUES ('68', '3', '14');
INSERT INTO `sys_role_menu` VALUES ('69', '3', '27');
INSERT INTO `sys_role_menu` VALUES ('70', '3', '29');
INSERT INTO `sys_role_menu` VALUES ('71', '3', '30');
INSERT INTO `sys_role_menu` VALUES ('72', '3', '31');
INSERT INTO `sys_role_menu` VALUES ('73', '3', '32');
INSERT INTO `sys_role_menu` VALUES ('74', '3', '33');
INSERT INTO `sys_role_menu` VALUES ('75', '3', '34');
INSERT INTO `sys_role_menu` VALUES ('76', '3', '35');
INSERT INTO `sys_role_menu` VALUES ('77', '3', '36');
INSERT INTO `sys_role_menu` VALUES ('78', '3', '37');
INSERT INTO `sys_role_menu` VALUES ('79', '3', '38');
INSERT INTO `sys_role_menu` VALUES ('80', '3', '39');
INSERT INTO `sys_role_menu` VALUES ('81', '3', '40');
INSERT INTO `sys_role_menu` VALUES ('82', '3', '41');
INSERT INTO `sys_role_menu` VALUES ('83', '3', '-666666');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '盐',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '手机号',
  `status` tinyint DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d', 'YzcmCZNvbXocrsz9dm8e', 'root@renren.io', '13612345678', '1', '1', '2016-11-11 11:11:11');
INSERT INTO `sys_user` VALUES ('4', 'user', 'c6db632acaff993431124f792982b3a84ddb67b12856adc314954a45d486795d', 'aH1XLPH0wBuZq2kl2Pas', 'abc@123.com', '18371458987', '1', '1', '2020-07-19 18:02:30');
INSERT INTO `sys_user` VALUES ('5', 'super', '115dc8bb37d0925b7e6005c3081d58fa49b74d0ee0d0df98cb18aadef5023274', 'N48VinVrrKjUkdYztiJe', '123@qq.com', '18371458526', '1', '1', '2020-07-19 18:03:35');

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('3', '5', '3');
INSERT INTO `sys_user_role` VALUES ('4', '1', '2');
INSERT INTO `sys_user_role` VALUES ('6', '4', '1');

-- ----------------------------
-- Table structure for `sys_user_token`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_token`;
CREATE TABLE `sys_user_token` (
  `user_id` bigint NOT NULL,
  `token` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'token',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE KEY `token` (`token`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='系统用户Token';

-- ----------------------------
-- Records of sys_user_token
-- ----------------------------
INSERT INTO `sys_user_token` VALUES ('1', '551ce836ec874590c5873f1c5a30ad4d', '2020-08-21 04:23:40', '2020-08-20 16:23:40');
INSERT INTO `sys_user_token` VALUES ('4', '958fb8a2b949ec2302de99500ba6bb52', '2020-07-20 06:04:27', '2020-07-19 18:04:27');
INSERT INTO `sys_user_token` VALUES ('5', '2e59fc3957b7020068919af1dd21a225', '2020-07-20 06:08:08', '2020-07-19 18:08:08');

-- ----------------------------
-- Table structure for `tb_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '手机号',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='用户';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'mark', '13612345678', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '2017-03-23 22:37:41');
INSERT INTO `tb_user` VALUES ('6', 'root', 'root', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', '2020-06-14 13:24:44');
INSERT INTO `tb_user` VALUES ('8', 'admin', 'admin', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', '2020-06-14 13:46:40');
INSERT INTO `tb_user` VALUES ('10', '123', '123', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', '2021-05-16 04:34:57');

-- ----------------------------
-- Table structure for `video_category`
-- ----------------------------
DROP TABLE IF EXISTS `video_category`;
CREATE TABLE `video_category` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of video_category
-- ----------------------------
INSERT INTO `video_category` VALUES ('1', '考研');
INSERT INTO `video_category` VALUES ('2', 'IT');
INSERT INTO `video_category` VALUES ('3', '四六级');
INSERT INTO `video_category` VALUES ('4', '考公');
INSERT INTO `video_category` VALUES ('5', '教资');
INSERT INTO `video_category` VALUES ('6', '高考');
INSERT INTO `video_category` VALUES ('7', '实用英语');
INSERT INTO `video_category` VALUES ('8', '法考');

-- ----------------------------
-- Table structure for `video_list`
-- ----------------------------
DROP TABLE IF EXISTS `video_list`;
CREATE TABLE `video_list` (
  `vid` int NOT NULL AUTO_INCREMENT COMMENT '视频id\r\n',
  `vtitle` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '视频标题\r\n',
  `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '作者姓名\r\n',
  `coverUrl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '封面图',
  `headurl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '作者头像url\r\n',
  `comment_num` int DEFAULT NULL COMMENT '用户评论数',
  `like_num` int DEFAULT NULL COMMENT '点赞数',
  `collect_num` int DEFAULT NULL COMMENT '收藏数',
  `playUrl` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '视频url',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `category_id` int DEFAULT NULL COMMENT '视频分类ID',
  PRIMARY KEY (`vid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of video_list
-- ----------------------------
INSERT INTO `video_list` VALUES ('1', '考研最大的敌人不是别人，而是走了很多弯路！', '徐涛', 'https://kqz-edu.oss-cn-beijing.aliyuncs.com/myapp/video/kaoyan/ky1.jpeg', 'https://sf1-ttcdn-tos.pstatp.com/img/pgc-image/c783a73368fa4666b7842a635c63a8bf~360x360.image', '121', '123', '122', 'http://vfx.mtime.cn/Video/2019/02/04/mp4/190204084208765161.mp4', '2020-07-14 11:21:45', '2021-05-16 05:36:05', '1');
INSERT INTO `video_list` VALUES ('2', '活动作品【励志】7分钟看完考研人的两年半（希望你能看完，后面有很多人为你加油！）|英语零基础|跨专业|延时摄影|逆袭|追梦赤子心|三本|真实记录|硬核|挑战|高考加油\r\n活动作品【励志】7分钟看完考研人的两年半（希望你能看完，后面有很多人为你加油！）|英语零基础|跨专业|延时摄影|逆袭|追梦赤子心|三本|真实记录|硬核|挑战|高考加油\r\n【励志】7分钟看完考研人的两年半', '黑桐谷歌', 'https://kqz-edu.oss-cn-beijing.aliyuncs.com/myapp/video/kaoyan/ky2.jpeg', 'https://sf6-ttcdn-tos.pstatp.com/img/mosaic-legacy/8110/752553978~360x360.image', '1300', '500', '120', 'http://vfx.mtime.cn/Video/2019/03/21/mp4/190321153853126488.mp4', null, '2021-05-16 05:36:43', '1');
INSERT INTO `video_list` VALUES ('3', '很多考研的靠这个视频续命', '小凡', 'https://kqz-edu.oss-cn-beijing.aliyuncs.com/myapp/video/kaoyan/ky3.jpeg', 'https://p3.pstatp.com/large/a14a000405f16e51842f', '10', '19', '5', 'http://vfx.mtime.cn/Video/2019/03/19/mp4/190319222227698228.mp4', null, '2021-05-16 05:37:25', '1');
INSERT INTO `video_list` VALUES ('4', '星光不负赶路人”考研老师经典励志语录鸡汤合集', '考研名师', 'https://kqz-edu.oss-cn-beijing.aliyuncs.com/myapp/video/kaoyan/ky4.jpeg', 'https://sf3-ttcdn-tos.pstatp.com/img/pgc-image/f6b840d23f9e465bb5ac9e570b28321d~360x360.image', '22', '180', '963', 'http://vfx.mtime.cn/Video/2019/03/19/mp4/190319212559089721.mp4', null, '2021-05-16 05:37:33', '1');
INSERT INTO `video_list` VALUES ('5', '四六级考前急救】临时抱佛脚？揭秘出题人套路【考场必备】|英语', '阿远', 'https://kqz-edu.oss-cn-beijing.aliyuncs.com/myapp/video/siliuji/slj1.jpeg', 'https://p9.pstatp.com/large/6edc0000758b2daaa6cc', '36', '3', '56', 'http://vfx.mtime.cn/Video/2019/03/18/mp4/190318231014076505.mp4', null, '2021-05-16 05:49:06', '3');
INSERT INTO `video_list` VALUES ('6', '四六级没复习?考前急救技巧', '王刚', 'https://kqz-edu.oss-cn-beijing.aliyuncs.com/myapp/video/siliuji/slj2.jpeg', 'https://sf3-ttcdn-tos.pstatp.com/img/mosaic-legacy/da860012437af2fd24c2~360x360.image', '96', '700', '89', 'http://vfx.mtime.cn/Video/2019/03/18/mp4/190318214226685784.mp4', null, '2021-05-16 05:49:16', '3');
INSERT INTO `video_list` VALUES ('7', '【一天200】单词怎么背最快？手把手教你做', '山药', 'https://kqz-edu.oss-cn-beijing.aliyuncs.com/myapp/video/siliuji/slj4.jpeg', 'https://p1.pstatp.com/large/719f0015d12364d07c5b', '9', '56', '123', 'http://vfx.mtime.cn/Video/2019/03/19/mp4/190319104618910544.mp4', null, '2021-05-16 05:49:38', '3');
INSERT INTO `video_list` VALUES ('8', '【纯干货】10天过英语四级的复习方法', '先生', 'https://kqz-edu.oss-cn-beijing.aliyuncs.com/myapp/video/siliuji/slj6.jpeg', 'https://sf1-ttcdn-tos.pstatp.com/img/mosaic-legacy/dae9000ee0a875804aae~360x360.image', '98', '546', '23', 'http://vfx.mtime.cn/Video/2019/03/19/mp4/190319125415785691.mp4', null, '2021-05-16 05:49:49', '3');
INSERT INTO `video_list` VALUES ('9', '15天快速过四六级【350分→470分考前突击经验】', '韩小浪', 'https://kqz-edu.oss-cn-beijing.aliyuncs.com/myapp/video/siliuji/%E5%9B%9B%E7%AB%8B%E5%8D%B3.jpeg', 'https://sf6-ttcdn-tos.pstatp.com/img/mosaic-legacy/b77400114e944ff697e4~360x360.image', '156', '56', '856', 'http://vfx.mtime.cn/Video/2019/03/17/mp4/190317150237409904.mp4', null, '2021-05-16 05:49:52', '3');
INSERT INTO `video_list` VALUES ('10', '四六级段落匹配救命干货！！！', '康康', 'https://kqz-edu.oss-cn-beijing.aliyuncs.com/myapp/video/siliuji/%E5%9B%9B%E7%AB%8B%E5%8D%B3.jpeg', 'https://sf3-ttcdn-tos.pstatp.com/img/pgc-image/7cbcfbb82fa142058fd45549d3b63a5b~360x360.image', '85', '4566', '100', 'http://vfx.mtime.cn/Video/2019/03/14/mp4/190314223540373995.mp4', null, '2021-05-16 05:50:19', '3');
INSERT INTO `video_list` VALUES ('11', '【2020法考】罗翔讲刑法（合集）已完结 共96讲 厚', '次位面', 'https://kqz-edu.oss-cn-beijing.aliyuncs.com/myapp/video/fakao/fk5.jpeg', 'https://p3.pstatp.com/large/888f000186913353fe3f', '123', '500', '320', 'http://vfx.mtime.cn/Video/2019/03/14/mp4/190314223540373995.mp4', '2020-07-19 16:05:38', '2021-05-16 06:13:12', '8');
INSERT INTO `video_list` VALUES ('12', '2021年法考-瑞达法考-精讲阶段-三国法-杨帆（完结）', '方阵', 'https://kqz-edu.oss-cn-beijing.aliyuncs.com/myapp/video/fakao/fk2.jpeg', 'https://sf6-ttcdn-tos.pstatp.com/img/pgc-image/1f5b712339ab475aa6ba0280d36189ba~360x360.image', '12', '343', '78', 'http://vfx.mtime.cn/Video/2019/03/14/mp4/190314223540373995.mp4', '2020-07-19 16:05:38', '2021-05-16 06:11:23', '8');
INSERT INTO `video_list` VALUES ('13', '法考客观题235分的我，主观题竟然只考了89分?！！', '观察员东旭', 'https://kqz-edu.oss-cn-beijing.aliyuncs.com/myapp/video/fakao/fk3.jpeg', 'https://sf6-ttcdn-tos.pstatp.com/img/mosaic-legacy/4110014cf3649fd8d6b~360x360.image', '543', '423', '22', 'http://vfx.mtime.cn/Video/2019/03/14/mp4/190314223540373995.mp4', '2020-07-19 16:05:38', '2021-05-16 06:11:35', '8');
INSERT INTO `video_list` VALUES ('14', '2021法考行政法李佳内部系统强化精讲 2021李佳内部', 'YiTube', 'https://kqz-edu.oss-cn-beijing.aliyuncs.com/myapp/video/fakao/fk1.jpeg', 'https://sf3-ttcdn-tos.pstatp.com/img/mosaic-legacy/ff3700002d8bc2b3ab3e~360x360.image', '654', '234', '466', 'http://vfx.mtime.cn/Video/2019/03/14/mp4/190314223540373995.mp4', '2020-07-19 16:05:38', '2021-05-16 06:13:14', '8');
INSERT INTO `video_list` VALUES ('15', '必读计算机编程好书推荐！程序员小伙搬出了他的书架！', '灿星', 'https://kqz-edu.oss-cn-beijing.aliyuncs.com/myapp/video/it/it1.jpeg', 'https://sf1-ttcdn-tos.pstatp.com/img/user-avatar/d58021eb3b4d5a6066eaf84fb793b360~360x360.image', '12', '45', '6', 'http://vfx.mtime.cn/Video/2019/03/14/mp4/190314223540373995.mp4', '2020-07-19 16:05:38', '2021-05-16 05:48:09', '2');
INSERT INTO `video_list` VALUES ('16', '像极了计算机专业写代码的你。', '比三呆', 'https://kqz-edu.oss-cn-beijing.aliyuncs.com/myapp/video/it/it2.jpeg', 'https://p3.pstatp.com/large/dac80011227f8d67d02b', '34', '456', '123', 'http://vfx.mtime.cn/Video/2019/03/14/mp4/190314223540373995.mp4', '2020-07-19 16:05:38', '2021-05-16 05:48:22', '2');
INSERT INTO `video_list` VALUES ('17', '程序员的这些话是如何做到全国统一的？', '下饭中二', 'https://kqz-edu.oss-cn-beijing.aliyuncs.com/myapp/video/it/it3.jpg', 'https://p1.pstatp.com/large/8b5f000540ca210dc4a7', '76', '47', '768', 'http://vfx.mtime.cn/Video/2019/03/14/mp4/190314223540373995.mp4', '2020-07-19 16:05:38', '2021-05-16 05:48:28', '2');
INSERT INTO `video_list` VALUES ('18', '毕业两年都用来考公，一无所获。一无所长，感觉自己是个废材。胖了二十斤。', '深漂的猪', 'https://kqz-edu.oss-cn-beijing.aliyuncs.com/myapp/video/kaogong/kg1.jpeg', 'https://p3.pstatp.com/large/da57000d5b84204f3e8f', '43', '46', '78', 'http://vfx.mtime.cn/Video/2019/03/14/mp4/190314223540373995.mp4', '2020-07-19 22:39:15', '2021-05-16 05:50:40', '4');
INSERT INTO `video_list` VALUES ('19', '帮你省报班费！2021教资面试通关技巧！如何备考教资面试？', '麦小登', 'https://kqz-edu.oss-cn-beijing.aliyuncs.com/myapp/video/jiaozi/jz.jpeg', 'https://sf1-ttcdn-tos.pstatp.com/img/mosaic-legacy/dae9000a4fdeff22675f~360x360.image', '65', '66', '567', 'http://vfx.mtime.cn/Video/2019/03/14/mp4/190314223540373995.mp4', '2020-07-19 16:05:38', '2021-05-16 05:51:29', '5');
INSERT INTO `video_list` VALUES ('20', '教资面试保过专题之结构化超强模版！你不知道的那些考试套路', '旗开得胜号', 'https://kqz-edu.oss-cn-beijing.aliyuncs.com/myapp/video/jiaozi/jz2.jpeg', 'https://p1.pstatp.com/large/986a0004c8fa4adec094', '776', '67', '23', 'http://vfx.mtime.cn/Video/2019/03/14/mp4/190314223540373995.mp4', '2020-07-19 16:05:38', '2021-05-16 05:51:38', '5');
INSERT INTO `video_list` VALUES ('21', '21教资面试学科逐字稿｜各科都有，人手必背，建议打印，试讲直接套用模版', '麦小兜', 'https://kqz-edu.oss-cn-beijing.aliyuncs.com/myapp/video/jiaozi/jz3.jpeg', 'https://sf6-ttcdn-tos.pstatp.com/img/user-avatar/a0fb5b628254086d23af194c4eec2426~360x360.image', '43', '45', '1123', 'http://vfx.mtime.cn/Video/2019/03/14/mp4/190314223540373995.mp4', '2020-07-19 16:05:38', '2021-05-16 05:51:47', '5');
INSERT INTO `video_list` VALUES ('22', '2020教资面试】各科逐字稿模板（语文数学英语美术体育化学生物政治历史）', '我是郭杰瑞', 'https://kqz-edu.oss-cn-beijing.aliyuncs.com/myapp/video/jiaozi/jz4.jpeg', 'https://p1.pstatp.com/large/4e6900026fa8d9eaee0a', '43', '654', '21', 'http://vfx.mtime.cn/Video/2019/03/14/mp4/190314223540373995.mp4', '2020-07-19 16:05:38', '2021-05-16 05:52:08', '5');
INSERT INTO `video_list` VALUES ('23', '北京大学赵炜：2020高考数学怎么考到135+？', '三江锅', 'https://kqz-edu.oss-cn-beijing.aliyuncs.com/myapp/video/gaokao/gk1.jpeg', 'https://p3.pstatp.com/large/ef40008c39119bef556', '56', '3435', '74', 'http://vfx.mtime.cn/Video/2019/03/14/mp4/190314223540373995.mp4', '2020-07-19 16:05:38', '2021-05-16 05:52:22', '6');
INSERT INTO `video_list` VALUES ('24', '【高考】省重点一位教师讲述自己的高考经历 山东省实验中学', '陈翔', 'https://kqz-edu.oss-cn-beijing.aliyuncs.com/myapp/video/gaokao/gk4.jpeg', 'https://sf1-ttcdn-tos.pstatp.com/img/mosaic-legacy/dac80012b10b5678b21e~360x360.image', '45', '234', '567', 'http://vfx.mtime.cn/Video/2019/03/14/mp4/190314223540373995.mp4', '2020-07-19 16:05:38', '2021-05-16 05:52:26', '6');
INSERT INTO `video_list` VALUES ('25', '【从零开始】175个最实用英语会话句型 ｜ 麦克老师', '肉蛋', 'https://kqz-edu.oss-cn-beijing.aliyuncs.com/myapp/video/yingyu/yy1.jpeg', 'https://p3.pstatp.com/large/289a0019c8fc986e193f', '45', '234', '6', 'http://vfx.mtime.cn/Video/2019/03/14/mp4/190314223540373995.mp4', '2020-07-19 16:05:38', '2021-05-16 05:52:54', '7');
INSERT INTO `video_list` VALUES ('26', '【24+】对话学习英语（各种生活实用场景，语速适', 'papi家', 'https://kqz-edu.oss-cn-beijing.aliyuncs.com/myapp/video/yingyu/yy2.jpeg', 'https://p1.pstatp.com/large/47220003b76b9bfc799c', '211', '12', '345', 'http://vfx.mtime.cn/Video/2019/03/14/mp4/190314223540373995.mp4', '2020-07-19 16:05:38', '2021-05-16 05:53:00', '7');
