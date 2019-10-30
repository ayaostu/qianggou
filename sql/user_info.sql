/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : qianggou

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 30/10/2019 18:29:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `gender` int(11) NOT NULL,
  `age` int(11) NOT NULL,
  `telphone` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `telphone`(`telphone`) USING BTREE COMMENT '电话号唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (5, 'ayao', '92eb4fb4f54afc5db3ffe8715cff6d94', 1, 19, '123456');
INSERT INTO `user_info` VALUES (6, '手电筒', 'b3d72f7f331415d0e73f92ea81b1d69c', 2, 12, '1234567');
INSERT INTO `user_info` VALUES (7, '阿尔泰', '92eb4fb4f54afc5db3ffe8715cff6d94', 1, 33, '123456789');
INSERT INTO `user_info` VALUES (8, '未发生', '92eb4fb4f54afc5db3ffe8715cff6d94', 1, 14, '1234');
INSERT INTO `user_info` VALUES (9, '阿尕色', '92eb4fb4f54afc5db3ffe8715cff6d94', 1, 17, '12345');
INSERT INTO `user_info` VALUES (10, 'aseg', 'b3d72f7f331415d0e73f92ea81b1d69c', 2, 22, '123123');
INSERT INTO `user_info` VALUES (11, '菲菲', 'b3d72f7f331415d0e73f92ea81b1d69c', 1, 0, '1101312664');

SET FOREIGN_KEY_CHECKS = 1;
