/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : librarydata

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 03/12/2020 21:43:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bookdata
-- ----------------------------
DROP TABLE IF EXISTS `bookdata`;
CREATE TABLE `bookdata`  (
  `BookNumber` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `BookName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `BorrowingSituation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `PublicationTime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bookdata
-- ----------------------------
INSERT INTO `bookdata` VALUES ('1917043236', 'C#程序设计', '已借出', '2000年12月3日');
INSERT INTO `bookdata` VALUES ('40350417', '123', '未借出', '123');
INSERT INTO `bookdata` VALUES ('1726535429', 'java程序设计', '已借出', '2000年12月3日');

-- ----------------------------
-- Table structure for borrowingdata
-- ----------------------------
DROP TABLE IF EXISTS `borrowingdata`;
CREATE TABLE `borrowingdata`  (
  `UserName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `BookNumber` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `BorrowingDate` date NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of borrowingdata
-- ----------------------------
INSERT INTO `borrowingdata` VALUES ('admin', '1917043236', '2020-12-03');
INSERT INTO `borrowingdata` VALUES ('leking', '1726535429', '2020-12-03');

-- ----------------------------
-- Table structure for userdata
-- ----------------------------
DROP TABLE IF EXISTS `userdata`;
CREATE TABLE `userdata`  (
  `UserNumber` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `UserName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Jurisdiction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `BorrowingCardPeriod` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userdata
-- ----------------------------
INSERT INTO `userdata` VALUES ('59747745', 'leking', '1', '用户', '正常');
INSERT INTO `userdata` VALUES ('2126760529', 'xerxer', '1', '用户', '正常');
INSERT INTO `userdata` VALUES ('0', 'admin', 'admin', '管理员', '正常');

SET FOREIGN_KEY_CHECKS = 1;
