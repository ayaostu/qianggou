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

 Date: 30/10/2019 18:28:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `user_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `item_price` double(10, 2) NOT NULL,
  `order_price` double(10, 2) NOT NULL,
  `amount` int(255) NOT NULL,
  `promo_id` int(11) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO `order_info` VALUES ('2019070700000100', 10, 16, 8881.00, 8881.00, 1, 0);
INSERT INTO `order_info` VALUES ('2019070700000200', 10, 16, 8881.00, 8881.00, 1, 0);
INSERT INTO `order_info` VALUES ('2019070700000300', 10, 15, 888.00, 888.00, 1, 0);
INSERT INTO `order_info` VALUES ('2019070700000400', 5, 15, 888.00, 888.00, 1, 0);
INSERT INTO `order_info` VALUES ('2019070700000500', 5, 16, 8881.00, 8881.00, 1, 0);
INSERT INTO `order_info` VALUES ('2019070700000600', 5, 16, 8881.00, 8881.00, 1, 0);
INSERT INTO `order_info` VALUES ('2019070900000800', 5, 16, 666.00, 666.00, 1, 1);
INSERT INTO `order_info` VALUES ('2019070900000900', 5, 16, 666.00, 666.00, 1, 1);
INSERT INTO `order_info` VALUES ('2019070900001000', 10, 16, 666.00, 666.00, 1, 1);
INSERT INTO `order_info` VALUES ('2019070900001100', 10, 18, 99999.00, 99999.00, 1, NULL);
INSERT INTO `order_info` VALUES ('2019070900001200', 10, 18, 99999.00, 99999.00, 1, NULL);
INSERT INTO `order_info` VALUES ('2019070900001300', 10, 16, 666.00, 666.00, 1, 1);
INSERT INTO `order_info` VALUES ('2019070900001700', 10, 20, 600.00, 600.00, 1, NULL);
INSERT INTO `order_info` VALUES ('2019070900001800', 10, 18, 99999.00, 99999.00, 1, NULL);
INSERT INTO `order_info` VALUES ('2019070900001900', 5, 20, 600.00, 600.00, 1, NULL);
INSERT INTO `order_info` VALUES ('2019070900002000', 5, 20, 600.00, 600.00, 1, NULL);
INSERT INTO `order_info` VALUES ('2019070900002100', 5, 18, 99999.00, 99999.00, 1, NULL);
INSERT INTO `order_info` VALUES ('2019070900002200', 5, 15, 888.00, 888.00, 1, NULL);
INSERT INTO `order_info` VALUES ('2019070900002300', 5, 15, 888.00, 888.00, 1, NULL);
INSERT INTO `order_info` VALUES ('2019070900002400', 5, 15, 888.00, 888.00, 1, NULL);
INSERT INTO `order_info` VALUES ('2019071000002500', 11, 20, 555.00, 555.00, 1, 2);
INSERT INTO `order_info` VALUES ('2019071000002600', 11, 15, 888.00, 888.00, 1, NULL);

SET FOREIGN_KEY_CHECKS = 1;
