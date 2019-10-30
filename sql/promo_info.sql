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

 Date: 30/10/2019 18:28:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for promo_info
-- ----------------------------
DROP TABLE IF EXISTS `promo_info`;
CREATE TABLE `promo_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `promo_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `promo_item_price` decimal(10, 2) NOT NULL,
  `item_id` int(11) NOT NULL,
  `start_time` datetime(0) NOT NULL,
  `end_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of promo_info
-- ----------------------------
INSERT INTO `promo_info` VALUES (1, '华为mate30', 666.00, 16, '2019-07-09 14:11:07', '2019-07-11 14:07:23');
INSERT INTO `promo_info` VALUES (2, '360nn手机', 555.00, 20, '2019-07-10 22:27:03', '2019-07-11 18:17:12');

SET FOREIGN_KEY_CHECKS = 1;
