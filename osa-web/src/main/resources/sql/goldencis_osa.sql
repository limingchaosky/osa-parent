/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : goldencis_osa

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-10-08 18:50:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_navigation`
-- ----------------------------
DROP TABLE IF EXISTS `t_navigation`;
CREATE TABLE `t_navigation` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键（自增长）',
  `compositor` int(10) unsigned NOT NULL COMMENT '排序',
  `name` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '页签显示名称',
  `url` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '页签跳转链接',
  `parent_id` int(10) DEFAULT NULL COMMENT '父级页签Id',
  `icon` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '页签图标',
  `level` int(10) unsigned NOT NULL COMMENT '页签级别',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='页签-导航信息表';

-- ----------------------------
-- Records of t_navigation
-- ----------------------------
INSERT INTO `t_navigation` VALUES ('1', '1', '首页', '/homepage/index?navId=1', null, 'nav_home', '1');
INSERT INTO `t_navigation` VALUES ('2', '2', '用户', '', null, 'nav_user', '1');
INSERT INTO `t_navigation` VALUES ('3', '3', '准入', '/access/index?navId=1', null, 'nav_check', '1');
INSERT INTO `t_navigation` VALUES ('4', '4', '策略', '', null, 'nav_policy', '1');
INSERT INTO `t_navigation` VALUES ('5', '5', '审批', '', null, 'nav_approve', '1');
INSERT INTO `t_navigation` VALUES ('6', '6', '日志', '', null, 'nav_report', '1');
INSERT INTO `t_navigation` VALUES ('7', '7', '系统', '', null, 'nav_system', '1');
INSERT INTO `t_navigation` VALUES ('8', '4', '关于', '/about/index?navId=1', '7', '', '2');
INSERT INTO `t_navigation` VALUES ('9', '1', '用户管理', '/clientUser/index', '2', null, '2');
INSERT INTO `t_navigation` VALUES ('10', '2', '部门管理', '/department/index', '2', null, '2');
INSERT INTO `t_navigation` VALUES ('11', '1', '视频平台', '/systemClient/index', '7', '', '2');
INSERT INTO `t_navigation` VALUES ('13', '2', '系统日志', '/systemLog/index', '6', null, '2');
INSERT INTO `t_navigation` VALUES ('14', '3', '设置', '/systemSetting/index', '7', null, '2');

-- ----------------------------
-- Table structure for `t_operation`
-- ----------------------------
DROP TABLE IF EXISTS `t_operation`;
CREATE TABLE `t_operation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键（自增长）',
  `compositor` int(11) NOT NULL COMMENT '排序',
  `code` varchar(50) DEFAULT NULL COMMENT '功能操作编码',
  `url_partten` varchar(200) NOT NULL COMMENT '功能操作url样式',
  `description` varchar(200) DEFAULT NULL COMMENT '功能描述',
  `parent_id` int(11) DEFAULT NULL COMMENT '父级功能Id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='功能操作表';

-- ----------------------------
-- Records of t_operation
-- ----------------------------

-- ----------------------------
-- Table structure for `t_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键（自增长）',
  `resource_type` int(11) NOT NULL COMMENT '访问资源类型',
  `resource_id` int(11) NOT NULL COMMENT '访问资源id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='访问资源权限表';

-- ----------------------------
-- Records of t_permission
-- ----------------------------

-- ----------------------------
-- Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `guid` varchar(36) NOT NULL COMMENT '主键.唯一标示',
  `name` varchar(50) NOT NULL COMMENT '角色名称',
  `description` varchar(200) DEFAULT NULL COMMENT '角色描述',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of t_role
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `guid` varchar(36) NOT NULL COMMENT '主键.唯一标示',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `department` int(11) DEFAULT NULL COMMENT '所属部门',
  `name` varchar(50) NOT NULL COMMENT '姓名',
  `visible` int(11) DEFAULT '0' COMMENT '是否可见',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(15) NOT NULL COMMENT '电话号码',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `status` int(11) unsigned DEFAULT '1' COMMENT '用户状态可用：11为可用，10为不可用(锁定)',
  `skin` varchar(15) DEFAULT 'blue',
  `error_login_count` int(3) DEFAULT '0' COMMENT '错误登录次数',
  `error_login_last_time` timestamp NULL DEFAULT NULL COMMENT '最近错误登录时间',
  `create_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表-定义用户基本信息';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'SYSTEM', '$2a$10$.Rn3tfOA5JhZZHdWoeBu3.ZGsVZm.y0liqBA3oHQHxD7Wev3T5P9G', '0', '超级管理员', '0', '', '', '', '11', 'black', '0', '2018-09-26 08:30:01', '2018-09-26 08:30:01');
INSERT INTO `t_user` VALUES ('2', 'ADMIN', 'F1FEA6DCDBE21260484F946A1BDFCB4D46C8253B320910E8AC2206EA302A7751', '0', '管理员', '0', '', '', '', '11', 'blue', '0', '2018-09-25 14:30:21', '2018-09-25 14:30:21');
INSERT INTO `t_user` VALUES ('3', 'OPERATOR', 'F1FEA6DCDBE21260484F946A1BDFCB4D46C8253B320910E8AC2206EA302A7751', '0', '操作员', '0', '', '', '', '11', 'blue', '0', '2018-09-25 14:30:22', '2018-09-25 14:30:22');
INSERT INTO `t_user` VALUES ('4', 'AUDITOR', 'F1FEA6DCDBE21260484F946A1BDFCB4D46C8253B320910E8AC2206EA302A7751', '0', '审计员', '0', '', '', '', '11', 'blue', '0', '2018-09-25 14:30:23', '2018-09-25 14:30:23');

-- ----------------------------
-- Table structure for `t_usergroup`
-- ----------------------------
DROP TABLE IF EXISTS `t_usergroup`;
CREATE TABLE `t_usergroup` (
  `guid` varchar(36) NOT NULL COMMENT '主键.唯一标示',
  `name` varchar(50) NOT NULL COMMENT '用户组名称',
  `comment` varchar(200) DEFAULT NULL COMMENT '用户组备注',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建者',
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户组表';

-- ----------------------------
-- Records of t_usergroup
-- ----------------------------

-- ----------------------------
-- Table structure for `t_usergroup_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_usergroup_role`;
CREATE TABLE `t_usergroup_role` (
  `usergroup_guid` varchar(36) NOT NULL COMMENT '用户组guid',
  `role_guid` varchar(36) NOT NULL COMMENT '角色guid',
  PRIMARY KEY (`usergroup_guid`,`role_guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户组角色关联表';

-- ----------------------------
-- Records of t_usergroup_role
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `user_guid` varchar(36) NOT NULL COMMENT '用户guid',
  `role_guid` varchar(36) NOT NULL COMMENT '角色guid',
  PRIMARY KEY (`user_guid`,`role_guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of t_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user_usergroup`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_usergroup`;
CREATE TABLE `t_user_usergroup` (
  `user_guid` varchar(36) NOT NULL COMMENT '用户guid',
  `usergroup_guid` varchar(36) NOT NULL COMMENT '用户组guid',
  PRIMARY KEY (`user_guid`,`usergroup_guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户和用户组关联表';

-- ----------------------------
-- Records of t_user_usergroup
-- ----------------------------
