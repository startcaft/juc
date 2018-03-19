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

 Date: 16/03/2018 16:25:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_dic_item
-- ----------------------------
INSERT INTO `sys_dic_item` VALUES (1, '证件类型', NULL, NULL, 99, 0);
INSERT INTO `sys_dic_item` VALUES (2, '民族', NULL, NULL, 98, 0);
INSERT INTO `sys_dic_item` VALUES (3, '身份证', NULL, NULL, 99, 1);
INSERT INTO `sys_dic_item` VALUES (4, '军官证', NULL, NULL, 98, 1);
INSERT INTO `sys_dic_item` VALUES (5, '护照', NULL, NULL, 97, 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES (1, '系统管理', NULL, NULL, '2018-03-09 10:42:21', 1, 100, NULL, 1, 0);
INSERT INTO `sys_resource` VALUES (2, '用户管理', NULL, NULL, '2018-03-09 10:42:28', 1, 100, NULL, 1, 1);
INSERT INTO `sys_resource` VALUES (3, '用户信息', NULL, NULL, '2018-03-15 15:25:43', 1, 100, '/admin/user/list', 99, 2);
INSERT INTO `sys_resource` VALUES (4, '字典管理', NULL, NULL, '2018-03-09 15:37:31', 1, 100, NULL, 99, 1);
INSERT INTO `sys_resource` VALUES (5, '资源管理', NULL, NULL, '2018-03-14 09:23:34', 1, 100, NULL, 99, 1);
INSERT INTO `sys_resource` VALUES (6, '资源信息', NULL, NULL, '2018-03-15 14:46:10', 1, 100, '/admin/resoruce/list', 99, 5);
INSERT INTO `sys_resource` VALUES (7, '添加资源', NULL, NULL, '2018-03-15 10:17:54', 2, 100, '', 99, 6);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(4) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名，唯一',
  `role_decs` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_ alias` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色别名，唯一，授权时使用别名授权',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `role_name`(`role_name`) USING BTREE,
  UNIQUE INDEX `role_ alias`(`role_ alias`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '系统管理员', NULL, 'admin');
INSERT INTO `sys_role` VALUES (2, '数据录入员', NULL, 'data');

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
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role_resource
-- ----------------------------
INSERT INTO `sys_role_resource` VALUES (1, 1, 3);
INSERT INTO `sys_role_resource` VALUES (2, 2, 3);
INSERT INTO `sys_role_resource` VALUES (3, 1, 6);

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '系统管理员', 'f6fdffe48c908deb0f4c3bd36c032e72', 2, 100, 1, '2018-03-13 15:43:05');

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
