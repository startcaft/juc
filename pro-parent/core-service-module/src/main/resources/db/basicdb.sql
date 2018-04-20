/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50559
 Source Host           : localhost:3306
 Source Schema         : basicdb

 Target Server Type    : MySQL
 Target Server Version : 50559
 File Encoding         : 65001

 Date: 19/04/2018 16:02:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_article
-- ----------------------------
DROP TABLE IF EXISTS `sys_article`;
CREATE TABLE `sys_article`  (
  `id` bigint(20) NOT NULL,
  `article_title` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章的标题',
  `article_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章的html内容',
  `dic_item_id` bigint(20) NOT NULL COMMENT '字典外键',
  `create_time` datetime NOT NULL COMMENT '时间',
  `article_desc` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章的描述，一般用作微信的图文消息的description属性',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_dic_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dic_item`;
CREATE TABLE `sys_dic_item`  (
  `id` bigint(4) NOT NULL AUTO_INCREMENT COMMENT '主键ID自增长',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类别名称，唯一约束',
  `code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类别code标识码',
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类别说明',
  `seq` int(4) NULL DEFAULT 99 COMMENT '排序，默认99',
  `pid` bigint(4) NOT NULL DEFAULT 0 COMMENT '父节点id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE COMMENT 'name字段唯一索引'
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_dic_item
-- ----------------------------
INSERT INTO `sys_dic_item` VALUES (1, '证件类型', NULL, NULL, 99, 0);
INSERT INTO `sys_dic_item` VALUES (2, '民族', NULL, NULL, 98, 0);
INSERT INTO `sys_dic_item` VALUES (3, '身份证', NULL, NULL, 99, 1);
INSERT INTO `sys_dic_item` VALUES (4, '军官证', NULL, NULL, 98, 1);
INSERT INTO `sys_dic_item` VALUES (5, '护照', NULL, NULL, 97, 1);
INSERT INTO `sys_dic_item` VALUES (6, '性别', 'gender', '描述居民性别', 3, 0);
INSERT INTO `sys_dic_item` VALUES (7, '男性', 'gender001', '', 1, 6);
INSERT INTO `sys_dic_item` VALUES (8, '女性', 'gender002', '', 2, 6);
INSERT INTO `sys_dic_item` VALUES (9, '文章分类', 'wz', '', 4, 0);
INSERT INTO `sys_dic_item` VALUES (10, '绝地求生刺激战场', 'wz001', '', 1, 9);
INSERT INTO `sys_dic_item` VALUES (11, '王者荣耀', 'wz002', '', 2, 9);
INSERT INTO `sys_dic_item` VALUES (12, '攻略', 'wz00101', '', 1, 10);
INSERT INTO `sys_dic_item` VALUES (13, '战术打法', 'wz00102', '', 2, 10);
INSERT INTO `sys_dic_item` VALUES (14, '枪械扫盲', 'wz00103', '', 3, 10);

-- ----------------------------
-- Table structure for sys_organization
-- ----------------------------
DROP TABLE IF EXISTS `sys_organization`;
CREATE TABLE `sys_organization`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `org_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `org_bak` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pid` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_organization
-- ----------------------------
INSERT INTO `sys_organization` VALUES (1, 'yyy总公司', 'yyy总公司', 0);
INSERT INTO `sys_organization` VALUES (2, '人事部门', NULL, 1);
INSERT INTO `sys_organization` VALUES (3, '社保', NULL, 2);
INSERT INTO `sys_organization` VALUES (4, 'HR', NULL, 2);
INSERT INTO `sys_organization` VALUES (5, '医保', NULL, 2);
INSERT INTO `sys_organization` VALUES (6, '财务部门', '', 1);

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource`  (
  `id` bigint(4) NOT NULL AUTO_INCREMENT COMMENT '主键ID自增长',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源名称，唯一约束',
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源描述',
  `icon` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源图标url',
  `create_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间，默认当前时间',
  `resource_type` int(4) NOT NULL COMMENT '资源类型',
  `states` int(4) NOT NULL COMMENT '资源状态',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '访问资源url',
  `seq` int(4) NULL DEFAULT 99 COMMENT '排序，默认99',
  `pid` bigint(4) NULL DEFAULT 0 COMMENT 'pid，默认0',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE COMMENT '资源名称唯一索引'
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES (1, '系统管理', NULL, NULL, '2018-03-09 10:42:21', 1, 100, NULL, 1, 0);
INSERT INTO `sys_resource` VALUES (2, '用户管理', NULL, NULL, '2018-03-09 10:42:28', 1, 100, NULL, 1, 1);
INSERT INTO `sys_resource` VALUES (3, '用户信息', NULL, NULL, '2018-03-21 10:10:35', 1, 100, '/admin/user/list', 1, 2);
INSERT INTO `sys_resource` VALUES (4, '字典管理', NULL, NULL, '2018-03-21 10:10:26', 1, 100, NULL, 3, 1);
INSERT INTO `sys_resource` VALUES (5, '资源管理', NULL, NULL, '2018-03-21 10:10:20', 1, 100, NULL, 2, 1);
INSERT INTO `sys_resource` VALUES (6, '资源信息', NULL, '', '2018-03-21 10:20:01', 1, 100, '/admin/resource/list', 1, 5);
INSERT INTO `sys_resource` VALUES (7, '添加资源', NULL, '', '2018-03-20 15:37:35', 2, 100, '/admin/resource/add', 1, 6);
INSERT INTO `sys_resource` VALUES (8, '资源修改', NULL, '', '2018-03-20 14:25:04', 2, 100, '/admin/resource/edit', 2, 6);
INSERT INTO `sys_resource` VALUES (9, '启动资源', NULL, '', '2018-03-20 11:25:43', 2, 100, '/admin/resource/enable', 3, 6);
INSERT INTO `sys_resource` VALUES (10, '锁定资源', NULL, '', '2018-03-20 13:40:59', 2, 100, '/admin/resource/lock', 4, 6);
INSERT INTO `sys_resource` VALUES (11, '删除资源', NULL, '', '2018-03-20 15:38:21', 2, 100, '/admin/resource/del', 5, 6);
INSERT INTO `sys_resource` VALUES (12, '角色管理', NULL, NULL, '2018-03-21 10:12:33', 1, 100, '', 4, 1);
INSERT INTO `sys_resource` VALUES (13, '角色信息', NULL, NULL, '2018-03-21 10:12:40', 1, 100, '/admin/role/list', 1, 12);
INSERT INTO `sys_resource` VALUES (14, '添加角色', NULL, NULL, '2018-03-21 15:18:01', 2, 100, '/admin/role/add', 1, 13);
INSERT INTO `sys_resource` VALUES (15, '角色修改', NULL, NULL, '2018-03-21 15:18:30', 2, 100, '/admin/role/edit', 2, 13);
INSERT INTO `sys_resource` VALUES (16, '角色授权', NULL, NULL, '2018-03-21 15:20:17', 2, 100, '/admin/role/grant', 3, 13);
INSERT INTO `sys_resource` VALUES (17, '数据字典信息', NULL, '', '2018-04-03 08:49:13', 1, 100, '/admin/dic/list', 1, 4);
INSERT INTO `sys_resource` VALUES (18, '添加字典', NULL, '', '2018-04-11 15:43:06', 2, 100, '/admin/dic/add', 1, 17);
INSERT INTO `sys_resource` VALUES (19, '修改字典', NULL, '', '2018-04-11 15:43:44', 2, 100, '/admin/dic/edit', 2, 17);
INSERT INTO `sys_resource` VALUES (20, '组织管理', NULL, '', '2018-04-16 11:19:18', 1, 100, '', 5, 1);
INSERT INTO `sys_resource` VALUES (21, '组织部门信息', NULL, '', '2018-04-16 11:19:39', 1, 100, '/admin/org/list', 1, 20);
INSERT INTO `sys_resource` VALUES (22, '添加组织部门信息', NULL, '', '2018-04-16 11:20:11', 2, 100, '/admin/org/add', 1, 21);
INSERT INTO `sys_resource` VALUES (23, '修改组织部门信息', NULL, '', '2018-04-16 11:20:31', 2, 100, '/admin/org/edit', 2, 21);
INSERT INTO `sys_resource` VALUES (24, '添加用户', NULL, '', '2018-04-17 15:21:42', 2, 100, '/admin/user/add', 1, 3);
INSERT INTO `sys_resource` VALUES (25, '修改用户', NULL, '', '2018-04-17 15:22:00', 2, 100, '/admin/user/edit', 2, 3);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(4) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名，唯一',
  `role_desc` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_alias` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色别名，唯一，授权时使用别名授权',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `role_name`(`role_name`) USING BTREE,
  UNIQUE INDEX `role_ alias`(`role_alias`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '系统管理员', NULL, 'admin');
INSERT INTO `sys_role` VALUES (2, '数据录入员', NULL, 'data');
INSERT INTO `sys_role` VALUES (3, 'test', 'test', 'test');
INSERT INTO `sys_role` VALUES (4, 'role', 'role', 'role');
INSERT INTO `sys_role` VALUES (5, 'test1', 'test1', 'test1');
INSERT INTO `sys_role` VALUES (6, 'test2', 'test2', 'test2');
INSERT INTO `sys_role` VALUES (7, '123', '1232131', '213213');
INSERT INTO `sys_role` VALUES (8, '3423', '4234234', '234234');
INSERT INTO `sys_role` VALUES (9, '哈哈1', '', 'hah1');
INSERT INTO `sys_role` VALUES (10, '哈哈', '哈哈', 'haha');

-- ----------------------------
-- Table structure for sys_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource`  (
  `id` bigint(4) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(4) NOT NULL,
  `resoruce_id` bigint(4) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE,
  INDEX `resoruce_id`(`resoruce_id`) USING BTREE,
  CONSTRAINT `sys_role_resource_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sys_role_resource_ibfk_2` FOREIGN KEY (`resoruce_id`) REFERENCES `sys_resource` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 108 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role_resource
-- ----------------------------
INSERT INTO `sys_role_resource` VALUES (35, 2, 3);
INSERT INTO `sys_role_resource` VALUES (36, 2, 6);
INSERT INTO `sys_role_resource` VALUES (37, 2, 7);
INSERT INTO `sys_role_resource` VALUES (38, 2, 8);
INSERT INTO `sys_role_resource` VALUES (39, 2, 9);
INSERT INTO `sys_role_resource` VALUES (40, 2, 10);
INSERT INTO `sys_role_resource` VALUES (41, 2, 11);
INSERT INTO `sys_role_resource` VALUES (42, 2, 13);
INSERT INTO `sys_role_resource` VALUES (43, 2, 16);
INSERT INTO `sys_role_resource` VALUES (44, 2, 14);
INSERT INTO `sys_role_resource` VALUES (45, 2, 15);
INSERT INTO `sys_role_resource` VALUES (89, 1, 3);
INSERT INTO `sys_role_resource` VALUES (90, 1, 24);
INSERT INTO `sys_role_resource` VALUES (91, 1, 25);
INSERT INTO `sys_role_resource` VALUES (92, 1, 21);
INSERT INTO `sys_role_resource` VALUES (93, 1, 22);
INSERT INTO `sys_role_resource` VALUES (94, 1, 23);
INSERT INTO `sys_role_resource` VALUES (95, 1, 17);
INSERT INTO `sys_role_resource` VALUES (96, 1, 18);
INSERT INTO `sys_role_resource` VALUES (97, 1, 19);
INSERT INTO `sys_role_resource` VALUES (98, 1, 6);
INSERT INTO `sys_role_resource` VALUES (99, 1, 7);
INSERT INTO `sys_role_resource` VALUES (100, 1, 8);
INSERT INTO `sys_role_resource` VALUES (101, 1, 9);
INSERT INTO `sys_role_resource` VALUES (102, 1, 10);
INSERT INTO `sys_role_resource` VALUES (103, 1, 11);
INSERT INTO `sys_role_resource` VALUES (104, 1, 13);
INSERT INTO `sys_role_resource` VALUES (105, 1, 16);
INSERT INTO `sys_role_resource` VALUES (106, 1, 14);
INSERT INTO `sys_role_resource` VALUES (107, 1, 15);

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user`  (
  `id` bigint(4) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(4) NOT NULL,
  `user_id` bigint(4) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `sys_role_user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sys_role_user_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES (1, 1, 1);
INSERT INTO `sys_role_user` VALUES (2, 2, 1);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(4) NOT NULL AUTO_INCREMENT COMMENT '主键ID自增长',
  `login_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登陆名称，具有唯一性',
  `real_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '真实姓名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码，加密值',
  `gender` int(4) NOT NULL COMMENT '性别',
  `states` int(4) NOT NULL COMMENT '状态',
  `organization_id` bigint(4) NOT NULL DEFAULT 0 COMMENT 'org外键',
  `create_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `login_name`(`login_name`) USING BTREE COMMENT '登录名唯一索引'
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '系统管理员', 'a66abb5684c45962d887564f08346e8d', 2, 100, 1, '2018-04-16 15:16:21');
INSERT INTO `sys_user` VALUES (2, 'test', 'test', '05a671c66aefea124cc08b76ea6d30bb', 2, 100, 1, '2018-04-18 10:47:54');
INSERT INTO `sys_user` VALUES (3, 'zhangsan', '', '9bad41710724cf6511abde2a52416881', 2, 100, 1, '2018-04-17 16:39:57');

-- ----------------------------
-- Function structure for getChildDics
-- ----------------------------
DROP FUNCTION IF EXISTS `getChildDics`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `getChildDics`(dicId INT) RETURNS varchar(4000) CHARSET utf8
BEGIN 
	DECLARE oTemp VARCHAR(4000); 
	DECLARE oTempChild VARCHAR(4000); 
	SET oTemp = ''; 
	SET oTempChild = CAST(dicId AS CHAR); 
	WHILE oTempChild IS NOT NULL DO 
	SET oTemp = CONCAT(oTemp,',',oTempChild); 
	SELECT GROUP_CONCAT(id) INTO oTempChild FROM sys_dic_item WHERE FIND_IN_SET(pid,oTempChild) > 0; 
	END WHILE; 
	RETURN oTemp; 
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
