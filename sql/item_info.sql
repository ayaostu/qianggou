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

 Date: 30/10/2019 18:27:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for item_info
-- ----------------------------
DROP TABLE IF EXISTS `item_info`;
CREATE TABLE `item_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `price` double(10, 2) NOT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `img_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `sales` int(255) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of item_info
-- ----------------------------
INSERT INTO `item_info` VALUES (15, '华为mate', 888.00, '好用', 'https://p1.ssl.qhimgs1.com/sdr/400__/t0195b9c992178e4e6d.jpg', 8);
INSERT INTO `item_info` VALUES (16, '华为mate30', 8881.00, '好用HAI', 'https://p1.ssl.qhimgs1.com/sdr/400__/t0195b9c992178e4e6d.jpg', 11);
INSERT INTO `item_info` VALUES (18, 'iPhonexx', 99999.00, 'ssssss', 'http://ku.90sjimg.com/element_origin_min_pic/17/11/06/952060701240a3b02d8cacc70a9a65a6.jpg', 4);
INSERT INTO `item_info` VALUES (20, '360nn手机', 600.00, '安全实惠', 'https://p0.ssl.qhimgs1.com/sdr/400__/t019ac871fb9b6ba99c.jpg', 4);

SET FOREIGN_KEY_CHECKS = 1;
