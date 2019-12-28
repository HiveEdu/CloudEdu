/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50528
 Source Host           : localhost:3306
 Source Schema         : myedu

 Target Server Type    : MySQL
 Target Server Version : 50528
 File Encoding         : 65001

 Date: 28/12/2019 17:35:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table`  (
  `table_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '表描述',
  `class_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
  `package_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '生成功能作者',
  `options` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '代码生成业务表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of gen_table
-- ----------------------------
INSERT INTO `gen_table` VALUES (1, 'sys_grade', '年级基础表', 'SysGrade', 'crud', 'com.myedu.project.system', 'system', 'grade', '年级基础', 'myedu', NULL, 'admin', '2019-12-26 21:52:51', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (2, 'yun_stu_leave', '学生请假表', 'YunStuLeave', 'crud', 'com.myedu.project.system', 'system', 'leave', '学生请假', 'myedu', NULL, 'admin', '2019-12-26 22:21:31', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (3, 'sys_course', '课程基本数据', 'SysCourse', 'crud', 'com.myedu.project.dataBasic', 'dataBasic', 'course', '课程基本数据', '梁少鹏', '{}', 'admin', '2019-12-28 13:03:58', '', '2019-12-28 13:10:43', '课程基本数据管理');
INSERT INTO `gen_table` VALUES (4, 'yun_student', '学生表', 'YunStudent', 'crud', 'com.myedu.project.parents', 'parents', 'student', '学生数据', '梁少鹏', '{}', 'admin', '2019-12-28 15:39:51', '', '2019-12-28 15:42:04', '学生数据表');

-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column`  (
  `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '字典类型',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '代码生成业务表字段' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of gen_table_column
-- ----------------------------
INSERT INTO `gen_table_column` VALUES (1, '1', 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2019-12-26 21:52:51', '', NULL);
INSERT INTO `gen_table_column` VALUES (2, '1', 'name', '年级名称', 'varchar(100)', 'String', 'name', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2019-12-26 21:52:51', '', NULL);
INSERT INTO `gen_table_column` VALUES (3, '1', 'remark', '描述', 'text', 'String', 'remark', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'input', '', 3, 'admin', '2019-12-26 21:52:51', '', NULL);
INSERT INTO `gen_table_column` VALUES (4, '1', 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 4, 'admin', '2019-12-26 21:52:51', '', NULL);
INSERT INTO `gen_table_column` VALUES (5, '1', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 5, 'admin', '2019-12-26 21:52:51', '', NULL);
INSERT INTO `gen_table_column` VALUES (6, '1', 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 6, 'admin', '2019-12-26 21:52:51', '', NULL);
INSERT INTO `gen_table_column` VALUES (7, '1', 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 7, 'admin', '2019-12-26 21:52:51', '', NULL);
INSERT INTO `gen_table_column` VALUES (8, '2', 'id', NULL, 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2019-12-26 22:21:31', '', NULL);
INSERT INTO `gen_table_column` VALUES (9, '2', 'reason', '请假原因', 'text', 'String', 'reason', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2019-12-26 22:21:31', '', NULL);
INSERT INTO `gen_table_column` VALUES (10, '2', 'start_time', '开始时间', 'date', 'Date', 'startTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 3, 'admin', '2019-12-26 22:21:31', '', NULL);
INSERT INTO `gen_table_column` VALUES (11, '2', 'end_time', '结束时间', 'date', 'Date', 'endTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 4, 'admin', '2019-12-26 22:21:31', '', NULL);
INSERT INTO `gen_table_column` VALUES (12, '2', 'student_id', '关联学生ID', 'bigint(20)', 'Long', 'studentId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2019-12-26 22:21:31', '', NULL);
INSERT INTO `gen_table_column` VALUES (13, '2', 'remark', '备注', 'text', 'String', 'remark', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'input', '', 6, 'admin', '2019-12-26 22:21:31', '', NULL);
INSERT INTO `gen_table_column` VALUES (14, '2', 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 7, 'admin', '2019-12-26 22:21:31', '', NULL);
INSERT INTO `gen_table_column` VALUES (15, '2', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 8, 'admin', '2019-12-26 22:21:31', '', NULL);
INSERT INTO `gen_table_column` VALUES (16, '2', 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 9, 'admin', '2019-12-26 22:21:31', '', NULL);
INSERT INTO `gen_table_column` VALUES (17, '2', 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 10, 'admin', '2019-12-26 22:21:31', '', NULL);
INSERT INTO `gen_table_column` VALUES (18, '2', 'del_flag', '删除标志（0代表存在 1代表删除）', 'char(1)', 'String', 'delFlag', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 11, 'admin', '2019-12-26 22:21:31', '', NULL);
INSERT INTO `gen_table_column` VALUES (19, '3', 'id', NULL, 'bigint(20)', 'Long', 'id', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2019-12-28 13:03:58', '', '2019-12-28 13:10:43');
INSERT INTO `gen_table_column` VALUES (20, '3', 'name', '课程名称', 'varchar(50)', 'String', 'name', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2019-12-28 13:03:58', '', '2019-12-28 13:10:43');
INSERT INTO `gen_table_column` VALUES (21, '3', 'remark', '课程说明', 'varchar(500)', 'String', 'remark', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'textarea', '', 3, 'admin', '2019-12-28 13:03:58', '', '2019-12-28 13:10:43');
INSERT INTO `gen_table_column` VALUES (22, '3', 'type', '课程类型（1学校课程2兴趣班课程）', 'char(255)', 'String', 'type', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', 'course_type', 4, 'admin', '2019-12-28 13:03:58', '', '2019-12-28 13:10:43');
INSERT INTO `gen_table_column` VALUES (23, '3', 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 5, 'admin', '2019-12-28 13:03:58', '', '2019-12-28 13:10:43');
INSERT INTO `gen_table_column` VALUES (24, '3', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 6, 'admin', '2019-12-28 13:03:58', '', '2019-12-28 13:10:43');
INSERT INTO `gen_table_column` VALUES (25, '3', 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 7, 'admin', '2019-12-28 13:03:58', '', '2019-12-28 13:10:43');
INSERT INTO `gen_table_column` VALUES (26, '3', 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 8, 'admin', '2019-12-28 13:03:58', '', '2019-12-28 13:10:43');
INSERT INTO `gen_table_column` VALUES (27, '3', 'del_flag', '删除标志（0代表存在 1代表删除）', 'char(1)', 'String', 'delFlag', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 9, 'admin', '2019-12-28 13:03:58', '', '2019-12-28 13:10:43');
INSERT INTO `gen_table_column` VALUES (28, '4', 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2019-12-28 15:39:51', '', '2019-12-28 15:42:04');
INSERT INTO `gen_table_column` VALUES (29, '4', 'name', '学生名称', 'varchar(255)', 'String', 'name', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2019-12-28 15:39:51', '', '2019-12-28 15:42:04');
INSERT INTO `gen_table_column` VALUES (30, '4', 'gendel', '学生性别（1：男2：女）', 'char(1)', 'String', 'gendel', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', 'sys_user_sex', 3, 'admin', '2019-12-28 15:39:51', '', '2019-12-28 15:42:04');
INSERT INTO `gen_table_column` VALUES (31, '4', 'avatar', '学生头像地址', 'text', 'String', 'avatar', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2019-12-28 15:39:51', '', '2019-12-28 15:42:04');
INSERT INTO `gen_table_column` VALUES (32, '4', 'school', '学生就读所在学校', 'varchar(255)', 'String', 'school', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2019-12-28 15:39:51', '', '2019-12-28 15:42:04');
INSERT INTO `gen_table_column` VALUES (33, '4', 'grade_id', '关联年级Id', 'bigint(20)', 'Long', 'gradeId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2019-12-28 15:39:51', '', '2019-12-28 15:42:04');
INSERT INTO `gen_table_column` VALUES (34, '4', 'parent_id', '关联家长Id', 'bigint(20)', 'Long', 'parentId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2019-12-28 15:39:51', '', '2019-12-28 15:42:04');
INSERT INTO `gen_table_column` VALUES (35, '4', 'create_by_id', '创建人Id', 'bigint(20)', 'Long', 'createById', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2019-12-28 15:39:51', '', '2019-12-28 15:42:04');
INSERT INTO `gen_table_column` VALUES (36, '4', 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 9, 'admin', '2019-12-28 15:39:51', '', '2019-12-28 15:42:04');
INSERT INTO `gen_table_column` VALUES (37, '4', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 10, 'admin', '2019-12-28 15:39:51', '', '2019-12-28 15:42:04');
INSERT INTO `gen_table_column` VALUES (38, '4', 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 11, 'admin', '2019-12-28 15:39:51', '', '2019-12-28 15:42:04');
INSERT INTO `gen_table_column` VALUES (39, '4', 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 12, 'admin', '2019-12-28 15:39:51', '', '2019-12-28 15:42:04');
INSERT INTO `gen_table_column` VALUES (40, '4', 'del_flag', '删除标志（0代表存在 1代表删除）', 'char(1)', 'String', 'delFlag', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 13, 'admin', '2019-12-28 15:39:51', '', '2019-12-28 15:42:04');

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `config_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '参数配置表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
INSERT INTO `sys_config` VALUES (2, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '初始化密码 123456');
INSERT INTO `sys_config` VALUES (3, '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '深色主题theme-dark，浅色主题theme-light');

-- ----------------------------
-- Table structure for sys_course
-- ----------------------------
DROP TABLE IF EXISTS `sys_course`;
CREATE TABLE `sys_course`  (
  `id` bigint(20) NOT NULL DEFAULT 0 AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '课程名称',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '课程说明',
  `type` char(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '课程类型（0校内课程1校外课程）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '课程基本数据' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_course
-- ----------------------------
INSERT INTO `sys_course` VALUES (2, '语文', '语文课', '0', 'admin', '2019-12-28 14:30:12', 'admin', '2019-12-28 15:57:01', '0');
INSERT INTO `sys_course` VALUES (3, '数学', '数学课', '0', 'admin', '2019-12-28 14:30:21', 'admin', '2019-12-28 15:57:05', '0');
INSERT INTO `sys_course` VALUES (4, '钢琴课', '钢琴课', '1', 'admin', '2019-12-28 14:32:24', 'admin', '2019-12-28 15:24:19', '0');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父部门id',
  `ancestors` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '部门名称',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `leader` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '邮箱',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 110 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '部门表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (100, 0, '0', '若依科技', 0, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES (101, 100, '0,100', '深圳总公司', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES (102, 100, '0,100', '长沙分公司', 2, '若依', '15888888888', 'ry@qq.com', '0', '2', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES (103, 101, '0,100,101', '研发部门', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES (104, 101, '0,100,101', '市场部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '2', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES (105, 101, '0,100,101', '测试部门', 3, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES (106, 101, '0,100,101', '财务部门', 4, '若依', '15888888888', 'ry@qq.com', '0', '2', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES (107, 101, '0,100,101', '运维部门', 5, '若依', '15888888888', 'ry@qq.com', '0', '2', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES (108, 102, '0,100,102', '市场部门', 1, '若依', '15888888888', 'ry@qq.com', '0', '2', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES (109, 102, '0,100,102', '财务部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '2', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `dict_code` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(4) NULL DEFAULT 0 COMMENT '字典排序',
  `dict_label` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '字典数据表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '性别男');
INSERT INTO `sys_dict_data` VALUES (2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '性别女');
INSERT INTO `sys_dict_data` VALUES (4, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '显示菜单');
INSERT INTO `sys_dict_data` VALUES (5, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '隐藏菜单');
INSERT INTO `sys_dict_data` VALUES (6, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES (7, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '停用状态');
INSERT INTO `sys_dict_data` VALUES (8, 1, '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES (9, 2, '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '停用状态');
INSERT INTO `sys_dict_data` VALUES (10, 1, '默认', 'DEFAULT', 'sys_job_group', '', '', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '默认分组');
INSERT INTO `sys_dict_data` VALUES (11, 2, '系统', 'SYSTEM', 'sys_job_group', '', '', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统分组');
INSERT INTO `sys_dict_data` VALUES (12, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统默认是');
INSERT INTO `sys_dict_data` VALUES (13, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统默认否');
INSERT INTO `sys_dict_data` VALUES (14, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知');
INSERT INTO `sys_dict_data` VALUES (15, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '公告');
INSERT INTO `sys_dict_data` VALUES (16, 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES (17, 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '关闭状态');
INSERT INTO `sys_dict_data` VALUES (18, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '新增操作');
INSERT INTO `sys_dict_data` VALUES (19, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '修改操作');
INSERT INTO `sys_dict_data` VALUES (20, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '删除操作');
INSERT INTO `sys_dict_data` VALUES (21, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '授权操作');
INSERT INTO `sys_dict_data` VALUES (22, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '导出操作');
INSERT INTO `sys_dict_data` VALUES (23, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '导入操作');
INSERT INTO `sys_dict_data` VALUES (24, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '强退操作');
INSERT INTO `sys_dict_data` VALUES (25, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '生成操作');
INSERT INTO `sys_dict_data` VALUES (26, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '清空操作');
INSERT INTO `sys_dict_data` VALUES (27, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES (28, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '停用状态');
INSERT INTO `sys_dict_data` VALUES (29, 0, '校内课程', '0', 'course_type', NULL, NULL, 'N', '0', 'admin', '2019-12-28 13:06:57', 'admin', '2019-12-28 15:22:13', '校内课程');
INSERT INTO `sys_dict_data` VALUES (30, 1, '校外课程', '1', 'course_type', NULL, NULL, 'N', '0', 'admin', '2019-12-28 13:07:38', 'admin', '2019-12-28 15:22:04', '校外课程');
INSERT INTO `sys_dict_data` VALUES (31, 0, '牛奶', '0', 'allergic_type', NULL, NULL, 'N', '0', 'admin', '2019-12-28 17:04:10', '', NULL, '牛奶过敏');
INSERT INTO `sys_dict_data` VALUES (32, 1, '海鲜', '1', 'allergic_type', NULL, NULL, 'N', '0', 'admin', '2019-12-28 17:04:36', '', NULL, '海鲜过敏');
INSERT INTO `sys_dict_data` VALUES (33, 3, '鸡蛋', '3', 'allergic_type', NULL, NULL, 'N', '0', 'admin', '2019-12-28 17:04:55', '', NULL, '鸡蛋过敏');
INSERT INTO `sys_dict_data` VALUES (34, 4, '豆制品', '4', 'allergic_type', NULL, NULL, 'N', '0', 'admin', '2019-12-28 17:05:30', '', NULL, '豆制品过敏');
INSERT INTO `sys_dict_data` VALUES (35, 5, '花粉', '5', 'allergic_type', NULL, NULL, 'N', '0', 'admin', '2019-12-28 17:05:56', '', NULL, '花粉过敏');

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '字典类型',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`) USING BTREE,
  UNIQUE INDEX `dict_type`(`dict_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '字典类型表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1, '用户性别', 'sys_user_sex', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '用户性别列表');
INSERT INTO `sys_dict_type` VALUES (2, '菜单状态', 'sys_show_hide', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '菜单状态列表');
INSERT INTO `sys_dict_type` VALUES (3, '系统开关', 'sys_normal_disable', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统开关列表');
INSERT INTO `sys_dict_type` VALUES (4, '任务状态', 'sys_job_status', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '任务状态列表');
INSERT INTO `sys_dict_type` VALUES (5, '任务分组', 'sys_job_group', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '任务分组列表');
INSERT INTO `sys_dict_type` VALUES (6, '系统是否', 'sys_yes_no', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统是否列表');
INSERT INTO `sys_dict_type` VALUES (7, '通知类型', 'sys_notice_type', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知类型列表');
INSERT INTO `sys_dict_type` VALUES (8, '通知状态', 'sys_notice_status', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知状态列表');
INSERT INTO `sys_dict_type` VALUES (9, '操作类型', 'sys_oper_type', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '操作类型列表');
INSERT INTO `sys_dict_type` VALUES (10, '系统状态', 'sys_common_status', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '登录状态列表');
INSERT INTO `sys_dict_type` VALUES (11, '课程类型', 'course_type', '0', 'admin', '2019-12-28 13:06:23', '', NULL, '课程类型');
INSERT INTO `sys_dict_type` VALUES (12, '过敏类型', 'allergic_type', '0', 'admin', '2019-12-28 17:03:43', '', NULL, '过敏类型');

-- ----------------------------
-- Table structure for sys_grade
-- ----------------------------
DROP TABLE IF EXISTS `sys_grade`;
CREATE TABLE `sys_grade`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '年级名称',
  `remark` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '描述',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '年级基础表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_grade
-- ----------------------------
INSERT INTO `sys_grade` VALUES (2, '一年级', '一年级', '', '2019-12-26 22:14:50', '', NULL);
INSERT INTO `sys_grade` VALUES (4, '二年级', '二年级', 'admin', '2019-12-28 14:19:44', '', NULL);

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`  (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
  `invoke_target` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '调用目标字符串',
  `cron_expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `concurrent` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '0' COMMENT '状态（0正常 1暂停）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`job_id`, `job_name`, `job_group`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '定时任务调度表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job` VALUES (1, '系统默认（无参）', 'DEFAULT', 'ryTask.ryNoParams', '0/10 * * * * ?', '3', '1', '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_job` VALUES (2, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '0/15 * * * * ?', '3', '1', '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_job` VALUES (3, '系统默认（多参）', 'DEFAULT', 'ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)', '0/20 * * * * ?', '3', '1', '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log`  (
  `job_log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '任务组名',
  `invoke_target` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '调用目标字符串',
  `job_message` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '日志信息',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `exception_info` varchar(2000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '异常信息',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '定时任务调度日志表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor`  (
  `info_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '用户账号',
  `ipaddr` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '操作系统',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '提示消息',
  `login_time` datetime NULL DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 110 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '系统访问记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_logininfor
-- ----------------------------
INSERT INTO `sys_logininfor` VALUES (100, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-12-19 21:56:54');
INSERT INTO `sys_logininfor` VALUES (101, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-12-26 20:58:31');
INSERT INTO `sys_logininfor` VALUES (102, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-12-28 12:56:48');
INSERT INTO `sys_logininfor` VALUES (103, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-12-28 13:36:55');
INSERT INTO `sys_logininfor` VALUES (104, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2019-12-28 13:44:09');
INSERT INTO `sys_logininfor` VALUES (105, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-12-28 13:44:18');
INSERT INTO `sys_logininfor` VALUES (106, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-12-28 14:12:31');
INSERT INTO `sys_logininfor` VALUES (107, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-12-28 14:56:58');
INSERT INTO `sys_logininfor` VALUES (108, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2019-12-28 15:51:26');
INSERT INTO `sys_logininfor` VALUES (109, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-12-28 15:51:34');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `path` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '组件路径',
  `is_frame` int(1) NULL DEFAULT 1 COMMENT '是否为外链（0是 1否）',
  `menu_type` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `perms` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1078 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '菜单权限表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, 1, 'system', NULL, 1, 'M', '0', '', 'system', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统管理目录');
INSERT INTO `sys_menu` VALUES (2, '系统监控', 0, 2, 'monitor', NULL, 1, 'M', '0', '', 'monitor', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统监控目录');
INSERT INTO `sys_menu` VALUES (3, '系统工具', 0, 3, 'tool', NULL, 1, 'M', '0', '', 'tool', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统工具目录');
INSERT INTO `sys_menu` VALUES (4, '若依官网', 0, 100, 'http://ruoyi.vip', NULL, 0, 'M', '0', '', 'guide', 'admin', '2018-03-16 11:33:00', 'admin', '2019-12-26 21:01:34', '若依官网地址');
INSERT INTO `sys_menu` VALUES (100, '用户管理', 1, 1, 'user', 'system/user/index', 1, 'C', '0', 'system:user:list', 'user', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '用户管理菜单');
INSERT INTO `sys_menu` VALUES (101, '角色管理', 1, 2, 'role', 'system/role/index', 1, 'C', '0', 'system:role:list', 'peoples', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '角色管理菜单');
INSERT INTO `sys_menu` VALUES (102, '菜单管理', 1, 3, 'menu', 'system/menu/index', 1, 'C', '0', 'system:menu:list', 'tree-table', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '菜单管理菜单');
INSERT INTO `sys_menu` VALUES (103, '部门管理', 1, 4, 'dept', 'system/dept/index', 1, 'C', '0', 'system:dept:list', 'tree', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '部门管理菜单');
INSERT INTO `sys_menu` VALUES (104, '岗位管理', 1, 5, 'post', 'system/post/index', 1, 'C', '0', 'system:post:list', 'post', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '岗位管理菜单');
INSERT INTO `sys_menu` VALUES (105, '字典管理', 1, 6, 'dict', 'system/dict/index', 1, 'C', '0', 'system:dict:list', 'dict', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '字典管理菜单');
INSERT INTO `sys_menu` VALUES (106, '参数设置', 1, 7, 'config', 'system/config/index', 1, 'C', '0', 'system:config:list', 'edit', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '参数设置菜单');
INSERT INTO `sys_menu` VALUES (107, '通知公告', 1, 8, 'notice', 'system/notice/index', 1, 'C', '0', 'system:notice:list', 'message', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知公告菜单');
INSERT INTO `sys_menu` VALUES (108, '日志管理', 1, 9, 'log', 'system/log/index', 1, 'M', '0', '', 'log', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '日志管理菜单');
INSERT INTO `sys_menu` VALUES (109, '在线用户', 2, 1, 'online', 'monitor/online/index', 1, 'C', '0', 'monitor:online:list', 'online', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '在线用户菜单');
INSERT INTO `sys_menu` VALUES (110, '定时任务', 2, 2, 'job', 'monitor/job/index', 1, 'C', '0', 'monitor:job:list', 'job', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '定时任务菜单');
INSERT INTO `sys_menu` VALUES (111, '数据监控', 2, 3, 'druid', 'monitor/druid/index', 1, 'C', '0', 'monitor:druid:list', 'druid', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '数据监控菜单');
INSERT INTO `sys_menu` VALUES (112, '服务监控', 2, 4, 'server', 'monitor/server/index', 1, 'C', '0', 'monitor:server:list', 'server', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '服务监控菜单');
INSERT INTO `sys_menu` VALUES (113, '表单构建', 3, 1, 'build', 'tool/build/index', 1, 'C', '0', 'tool:build:list', 'build', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '表单构建菜单');
INSERT INTO `sys_menu` VALUES (114, '代码生成', 3, 2, 'gen', 'tool/gen/index', 1, 'C', '0', 'tool:gen:list', 'code', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '代码生成菜单');
INSERT INTO `sys_menu` VALUES (115, '系统接口', 3, 3, 'swagger', 'tool/swagger/index', 1, 'C', '0', 'tool:swagger:list', 'swagger', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统接口菜单');
INSERT INTO `sys_menu` VALUES (500, '操作日志', 108, 1, 'operlog', 'monitor/operlog/index', 1, 'C', '0', 'monitor:operlog:list', 'form', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '操作日志菜单');
INSERT INTO `sys_menu` VALUES (501, '登录日志', 108, 2, 'logininfor', 'monitor/logininfor/index', 1, 'C', '0', 'monitor:logininfor:list', 'logininfor', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '登录日志菜单');
INSERT INTO `sys_menu` VALUES (1001, '用户查询', 100, 1, '', '', 1, 'F', '0', 'system:user:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1002, '用户新增', 100, 2, '', '', 1, 'F', '0', 'system:user:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1003, '用户修改', 100, 3, '', '', 1, 'F', '0', 'system:user:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1004, '用户删除', 100, 4, '', '', 1, 'F', '0', 'system:user:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1005, '用户导出', 100, 5, '', '', 1, 'F', '0', 'system:user:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1006, '用户导入', 100, 6, '', '', 1, 'F', '0', 'system:user:import', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1007, '重置密码', 100, 7, '', '', 1, 'F', '0', 'system:user:resetPwd', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1008, '角色查询', 101, 1, '', '', 1, 'F', '0', 'system:role:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1009, '角色新增', 101, 2, '', '', 1, 'F', '0', 'system:role:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1010, '角色修改', 101, 3, '', '', 1, 'F', '0', 'system:role:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1011, '角色删除', 101, 4, '', '', 1, 'F', '0', 'system:role:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1012, '角色导出', 101, 5, '', '', 1, 'F', '0', 'system:role:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1013, '菜单查询', 102, 1, '', '', 1, 'F', '0', 'system:menu:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1014, '菜单新增', 102, 2, '', '', 1, 'F', '0', 'system:menu:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1015, '菜单修改', 102, 3, '', '', 1, 'F', '0', 'system:menu:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1016, '菜单删除', 102, 4, '', '', 1, 'F', '0', 'system:menu:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1017, '部门查询', 103, 1, '', '', 1, 'F', '0', 'system:dept:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1018, '部门新增', 103, 2, '', '', 1, 'F', '0', 'system:dept:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1019, '部门修改', 103, 3, '', '', 1, 'F', '0', 'system:dept:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1020, '部门删除', 103, 4, '', '', 1, 'F', '0', 'system:dept:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1021, '岗位查询', 104, 1, '', '', 1, 'F', '0', 'system:post:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1022, '岗位新增', 104, 2, '', '', 1, 'F', '0', 'system:post:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1023, '岗位修改', 104, 3, '', '', 1, 'F', '0', 'system:post:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1024, '岗位删除', 104, 4, '', '', 1, 'F', '0', 'system:post:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1025, '岗位导出', 104, 5, '', '', 1, 'F', '0', 'system:post:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1026, '字典查询', 105, 1, '#', '', 1, 'F', '0', 'system:dict:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1027, '字典新增', 105, 2, '#', '', 1, 'F', '0', 'system:dict:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1028, '字典修改', 105, 3, '#', '', 1, 'F', '0', 'system:dict:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1029, '字典删除', 105, 4, '#', '', 1, 'F', '0', 'system:dict:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1030, '字典导出', 105, 5, '#', '', 1, 'F', '0', 'system:dict:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1031, '参数查询', 106, 1, '#', '', 1, 'F', '0', 'system:config:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1032, '参数新增', 106, 2, '#', '', 1, 'F', '0', 'system:config:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1033, '参数修改', 106, 3, '#', '', 1, 'F', '0', 'system:config:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1034, '参数删除', 106, 4, '#', '', 1, 'F', '0', 'system:config:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1035, '参数导出', 106, 5, '#', '', 1, 'F', '0', 'system:config:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1036, '公告查询', 107, 1, '#', '', 1, 'F', '0', 'system:notice:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1037, '公告新增', 107, 2, '#', '', 1, 'F', '0', 'system:notice:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1038, '公告修改', 107, 3, '#', '', 1, 'F', '0', 'system:notice:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1039, '公告删除', 107, 4, '#', '', 1, 'F', '0', 'system:notice:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1040, '操作查询', 500, 1, '#', '', 1, 'F', '0', 'monitor:operlog:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1041, '操作删除', 500, 2, '#', '', 1, 'F', '0', 'monitor:operlog:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1042, '日志导出', 500, 4, '#', '', 1, 'F', '0', 'monitor:operlog:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1043, '登录查询', 501, 1, '#', '', 1, 'F', '0', 'monitor:logininfor:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1044, '登录删除', 501, 2, '#', '', 1, 'F', '0', 'monitor:logininfor:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1045, '日志导出', 501, 3, '#', '', 1, 'F', '0', 'monitor:logininfor:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1046, '在线查询', 109, 1, '#', '', 1, 'F', '0', 'monitor:online:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1047, '批量强退', 109, 2, '#', '', 1, 'F', '0', 'monitor:online:batchLogout', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1048, '单条强退', 109, 3, '#', '', 1, 'F', '0', 'monitor:online:forceLogout', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1049, '任务查询', 110, 1, '#', '', 1, 'F', '0', 'monitor:job:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1050, '任务新增', 110, 2, '#', '', 1, 'F', '0', 'monitor:job:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1051, '任务修改', 110, 3, '#', '', 1, 'F', '0', 'monitor:job:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1052, '任务删除', 110, 4, '#', '', 1, 'F', '0', 'monitor:job:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1053, '状态修改', 110, 5, '#', '', 1, 'F', '0', 'monitor:job:changeStatus', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1054, '任务导出', 110, 7, '#', '', 1, 'F', '0', 'monitor:job:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1055, '生成查询', 114, 1, '#', '', 1, 'F', '0', 'tool:gen:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1056, '生成修改', 114, 2, '#', '', 1, 'F', '0', 'tool:gen:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1057, '生成删除', 114, 3, '#', '', 1, 'F', '0', 'tool:gen:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1058, '导入代码', 114, 2, '#', '', 1, 'F', '0', 'tool:gen:import', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1059, '预览代码', 114, 4, '#', '', 1, 'F', '0', 'tool:gen:preview', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1060, '生成代码', 114, 5, '#', '', 1, 'F', '0', 'tool:gen:code', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1061, '基础数据', 0, 4, 'dataBase', NULL, 1, 'M', '0', '', 'build', 'admin', '2019-12-26 21:03:27', 'admin', '2019-12-26 21:22:48', '');
INSERT INTO `sys_menu` VALUES (1062, '年级管理', 1061, 1, 'grade', 'dataBasic/grade/index', 1, 'C', '0', 'dataBasic:grade:list', 'nested', 'admin', '2019-12-26 21:11:14', 'admin', '2019-12-26 21:34:51', '');
INSERT INTO `sys_menu` VALUES (1063, '年级查询', 1062, 1, '', NULL, 1, 'F', '0', 'dataBasic:grade:query', '#', 'admin', '2019-12-26 21:17:51', 'admin', '2019-12-26 21:35:17', '');
INSERT INTO `sys_menu` VALUES (1064, '年级新增', 1062, 2, '', NULL, 1, 'F', '0', 'dataBasic:grade:add', '#', 'admin', '2019-12-26 21:18:37', 'admin', '2019-12-26 21:35:26', '');
INSERT INTO `sys_menu` VALUES (1065, '年级修改', 1062, 3, '', NULL, 1, 'F', '0', 'dataBasic:grade:edit', '#', 'admin', '2019-12-26 21:19:08', 'admin', '2019-12-26 21:35:32', '');
INSERT INTO `sys_menu` VALUES (1066, '年级删除', 1062, 4, '', NULL, 1, 'F', '0', 'dataBasic:grade:remove', '#', 'admin', '2019-12-26 21:19:41', 'admin', '2019-12-26 21:35:50', '');
INSERT INTO `sys_menu` VALUES (1067, '课程管理', 1061, 2, 'course', 'dataBasic/course/index', 1, 'C', '0', 'dataBasic:course:list', 'excel', 'admin', '2019-12-28 13:38:43', 'admin', '2019-12-28 13:48:37', '');
INSERT INTO `sys_menu` VALUES (1068, '课程查询', 1067, 1, '', NULL, 1, 'F', '0', 'dataBasic:course:query', '#', 'admin', '2019-12-28 13:40:54', 'admin', '2019-12-28 13:59:02', '');
INSERT INTO `sys_menu` VALUES (1069, '课程新增', 1067, 2, '', NULL, 1, 'F', '0', 'dataBasic:course:add', '#', 'admin', '2019-12-28 13:41:25', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1070, '课程修改', 1067, 3, '', NULL, 1, 'F', '0', 'dataBasic:course:edit', '#', 'admin', '2019-12-28 13:41:58', 'admin', '2019-12-28 13:58:10', '');
INSERT INTO `sys_menu` VALUES (1071, '课程删除', 1067, 4, '', NULL, 1, 'F', '0', 'dataBasic:course:remove', '#', 'admin', '2019-12-28 13:42:24', 'admin', '2019-12-28 13:58:17', '');
INSERT INTO `sys_menu` VALUES (1072, '家长端管理', 0, 5, 'parents', NULL, 1, 'M', '0', '', 'people', 'admin', '2019-12-28 15:28:46', 'admin', '2019-12-28 15:54:07', '');
INSERT INTO `sys_menu` VALUES (1073, '学生管理', 1072, 1, 'student', 'parents/student/index', 1, 'C', '0', 'parents:student:list', 'peoples', 'admin', '2019-12-28 15:33:02', 'admin', '2019-12-28 15:55:39', '');
INSERT INTO `sys_menu` VALUES (1074, '学生查询', 1073, 1, '', NULL, 1, 'F', '0', 'parents:student:query', '#', 'admin', '2019-12-28 15:35:07', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1075, '学生新增', 1073, 2, '', NULL, 1, 'F', '0', 'parents:student:add', '#', 'admin', '2019-12-28 15:36:19', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1076, '学生修改', 1073, 3, '', NULL, 1, 'F', '0', 'parents:student:edit', '#', 'admin', '2019-12-28 15:36:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1077, '学生删除', 1073, 4, '', NULL, 1, 'F', '0', 'parents:student:remove', '#', 'admin', '2019-12-28 15:37:40', '', NULL, '');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `notice_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '公告标题',
  `notice_type` char(1) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '公告内容',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '通知公告表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES (1, '温馨提醒：2018-07-01 若依新版本发布啦', '2', '新版本内容', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '管理员');
INSERT INTO `sys_notice` VALUES (2, '维护通知：2018-07-01 若依系统凌晨维护', '1', '维护内容', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '管理员');

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`  (
  `oper_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) NULL DEFAULT 0 COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '请求方式',
  `operator_type` int(1) NULL DEFAULT 0 COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '返回参数',
  `status` int(1) NULL DEFAULT 0 COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`oper_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 106 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '操作日志记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------
INSERT INTO `sys_oper_log` VALUES (1, '菜单管理', 2, 'com.myedu.project.system.controller.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"guide\",\"orderNum\":\"100\",\"menuName\":\"若依官网\",\"params\":{},\"parentId\":0,\"path\":\"http://ruoyi.vip\",\"children\":[],\"createTime\":1521171180000,\"updateBy\":\"admin\",\"isFrame\":\"0\",\"menuId\":4,\"menuType\":\"M\",\"perms\":\"\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-26 21:01:34');
INSERT INTO `sys_oper_log` VALUES (2, '菜单管理', 1, 'com.myedu.project.system.controller.SysMenuController.add()', 'POST', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"dashboard\",\"orderNum\":\"4\",\"menuName\":\"基础数据\",\"params\":{},\"parentId\":0,\"path\":\"dataBase\",\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"M\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-26 21:03:27');
INSERT INTO `sys_oper_log` VALUES (3, '菜单管理', 1, 'com.myedu.project.system.controller.SysMenuController.add()', 'POST', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"nested\",\"orderNum\":\"1\",\"menuName\":\"年级管理\",\"params\":{},\"parentId\":1061,\"path\":\"grade\",\"component\":\"grade\",\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"C\",\"perms\":\"grade\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-26 21:11:14');
INSERT INTO `sys_oper_log` VALUES (4, '菜单管理', 2, 'com.myedu.project.system.controller.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"nested\",\"orderNum\":\"1\",\"menuName\":\"年级管理\",\"params\":{},\"parentId\":1061,\"path\":\"grade\",\"component\":\"system/grade/index\",\"children\":[],\"createTime\":1577365874000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1062,\"menuType\":\"C\",\"perms\":\"grade\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-26 21:12:00');
INSERT INTO `sys_oper_log` VALUES (5, '菜单管理', 2, 'com.myedu.project.system.controller.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"nested\",\"orderNum\":\"1\",\"menuName\":\"年级管理\",\"params\":{},\"parentId\":1061,\"path\":\"grade\",\"component\":\"system/grade/index\",\"children\":[],\"createTime\":1577365874000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1062,\"menuType\":\"C\",\"perms\":\"system:grade:list\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-26 21:12:21');
INSERT INTO `sys_oper_log` VALUES (6, '菜单管理', 1, 'com.myedu.project.system.controller.SysMenuController.add()', 'POST', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"orderNum\":\"1\",\"menuName\":\"年级查询\",\"params\":{},\"parentId\":1062,\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"F\",\"perms\":\"system:grade:query\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-26 21:17:51');
INSERT INTO `sys_oper_log` VALUES (7, '菜单管理', 1, 'com.myedu.project.system.controller.SysMenuController.add()', 'POST', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"orderNum\":\"2\",\"menuName\":\"年级新增\",\"params\":{},\"parentId\":1062,\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"F\",\"perms\":\"system:grade:add\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-26 21:18:37');
INSERT INTO `sys_oper_log` VALUES (8, '菜单管理', 1, 'com.myedu.project.system.controller.SysMenuController.add()', 'POST', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"orderNum\":\"3\",\"menuName\":\"年级修改\",\"params\":{},\"parentId\":1062,\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"F\",\"perms\":\"system:grade:edit\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-26 21:19:08');
INSERT INTO `sys_oper_log` VALUES (9, '菜单管理', 1, 'com.myedu.project.system.controller.SysMenuController.add()', 'POST', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"orderNum\":\"4\",\"menuName\":\"年级删除\",\"params\":{},\"parentId\":1062,\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"F\",\"perms\":\"system:grade:remove\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-26 21:19:41');
INSERT INTO `sys_oper_log` VALUES (10, '角色管理', 2, 'com.myedu.project.system.controller.SysRoleController.dataScope()', 'PUT', 1, 'admin', NULL, '/system/role/dataScope', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":1,\"admin\":true,\"remark\":\"管理员\",\"dataScope\":\"1\",\"delFlag\":\"0\",\"params\":{},\"roleSort\":\"1\",\"createTime\":1521171180000,\"roleKey\":\"admin\",\"roleName\":\"管理员\",\"deptIds\":[],\"status\":\"0\"}', 'null', 1, '不允许操作超级管理员角色', '2019-12-26 21:21:22');
INSERT INTO `sys_oper_log` VALUES (11, '菜单管理', 2, 'com.myedu.project.system.controller.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"build\",\"orderNum\":\"4\",\"menuName\":\"基础数据\",\"params\":{},\"parentId\":0,\"path\":\"dataBase\",\"children\":[],\"createTime\":1577365407000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1061,\"menuType\":\"M\",\"perms\":\"\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-26 21:22:48');
INSERT INTO `sys_oper_log` VALUES (12, '菜单管理', 2, 'com.myedu.project.system.controller.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"nested\",\"orderNum\":\"1\",\"menuName\":\"年级管理\",\"params\":{},\"parentId\":1061,\"path\":\"grade\",\"component\":\"dataBasic/grade/index\",\"children\":[],\"createTime\":1577365874000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1062,\"menuType\":\"C\",\"perms\":\"dataBasic:grade:list\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-26 21:34:51');
INSERT INTO `sys_oper_log` VALUES (13, '菜单管理', 2, 'com.myedu.project.system.controller.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"1\",\"menuName\":\"年级查询\",\"params\":{},\"parentId\":1062,\"path\":\"\",\"children\":[],\"createTime\":1577366271000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1063,\"menuType\":\"F\",\"perms\":\"dataBasic:grade:query\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-26 21:35:17');
INSERT INTO `sys_oper_log` VALUES (14, '菜单管理', 2, 'com.myedu.project.system.controller.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"2\",\"menuName\":\"年级新增\",\"params\":{},\"parentId\":1062,\"path\":\"\",\"children\":[],\"createTime\":1577366317000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1064,\"menuType\":\"F\",\"perms\":\"dataBasic:grade:add\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-26 21:35:26');
INSERT INTO `sys_oper_log` VALUES (15, '菜单管理', 2, 'com.myedu.project.system.controller.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"3\",\"menuName\":\"年级修改\",\"params\":{},\"parentId\":1062,\"path\":\"\",\"children\":[],\"createTime\":1577366348000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1065,\"menuType\":\"F\",\"perms\":\"dataBasic:grade:edit\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-26 21:35:32');
INSERT INTO `sys_oper_log` VALUES (16, '菜单管理', 2, 'com.myedu.project.system.controller.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"4\",\"menuName\":\"年级删除\",\"params\":{},\"parentId\":1062,\"path\":\"\",\"children\":[],\"createTime\":1577366381000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1066,\"menuType\":\"F\",\"perms\":\"dataBasic:grade:remove\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-26 21:35:50');
INSERT INTO `sys_oper_log` VALUES (17, '岗位管理', 2, 'com.myedu.project.system.controller.SysPostController.edit()', 'PUT', 1, 'admin', NULL, '/system/post', '127.0.0.1', '内网IP', '{\"postSort\":\"1\",\"flag\":false,\"remark\":\"\",\"postId\":1,\"params\":{},\"createBy\":\"admin\",\"createTime\":1521171180000,\"updateBy\":\"admin\",\"postName\":\"董事长\",\"postCode\":\"ceo\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-26 21:38:20');
INSERT INTO `sys_oper_log` VALUES (18, '代码生成', 6, 'com.myedu.project.tool.gen.controller.GenController.importTableSave()', 'POST', 1, 'admin', NULL, '/tool/gen/importTable', '127.0.0.1', '内网IP', 'sys_grade', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-26 21:52:51');
INSERT INTO `sys_oper_log` VALUES (19, '代码生成', 8, 'com.myedu.project.tool.gen.controller.GenController.batchGenCode()', 'GET', 1, 'admin', NULL, '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{}', 'null', 0, NULL, '2019-12-26 21:53:17');
INSERT INTO `sys_oper_log` VALUES (20, '年级基础', 1, 'com.myedu.project.dataBasic.controller.SysGradeController.add()', 'POST', 1, 'admin', NULL, '/system/grade', '127.0.0.1', '内网IP', '{\"createTime\":1577369302036,\"name\":\"一年级\",\"id\":1,\"params\":{}}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-26 22:08:22');
INSERT INTO `sys_oper_log` VALUES (21, '年级基础', 2, 'com.myedu.project.dataBasic.controller.SysGradeController.edit()', 'PUT', 1, 'admin', NULL, '/system/grade', '127.0.0.1', '内网IP', '{\"createBy\":\"\",\"createTime\":1577369302000,\"updateBy\":\"\",\"name\":\"二年级\",\"remark\":\"啊啊\",\"updateTime\":1577369676352,\"id\":1,\"params\":{}}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-26 22:14:36');
INSERT INTO `sys_oper_log` VALUES (22, '年级基础', 3, 'com.myedu.project.dataBasic.controller.SysGradeController.remove()', 'DELETE', 1, 'admin', NULL, '/system/grade/1', '127.0.0.1', '内网IP', '{ids=1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-26 22:14:39');
INSERT INTO `sys_oper_log` VALUES (23, '年级基础', 1, 'com.myedu.project.dataBasic.controller.SysGradeController.add()', 'POST', 1, 'admin', NULL, '/system/grade', '127.0.0.1', '内网IP', '{\"createTime\":1577369690393,\"name\":\"一年级\",\"remark\":\"一年级\",\"id\":2,\"params\":{}}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-26 22:14:50');
INSERT INTO `sys_oper_log` VALUES (24, '代码生成', 8, 'com.myedu.project.tool.gen.controller.GenController.batchGenCode()', 'GET', 1, 'admin', NULL, '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{}', 'null', 0, NULL, '2019-12-26 22:19:03');
INSERT INTO `sys_oper_log` VALUES (25, '代码生成', 6, 'com.myedu.project.tool.gen.controller.GenController.importTableSave()', 'POST', 1, 'admin', NULL, '/tool/gen/importTable', '127.0.0.1', '内网IP', 'yun_stu_leave', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-26 22:21:31');
INSERT INTO `sys_oper_log` VALUES (26, '年级基础', 5, 'com.myedu.project.dataBasic.controller.SysGradeController.export()', 'GET', 1, 'admin', NULL, '/system/grade/export', '127.0.0.1', '内网IP', '{}', '{\"msg\":\"8428ac49-7b17-4547-822b-0ece4f8765d5_grade.xlsx\",\"code\":200}', 0, NULL, '2019-12-26 22:24:53');
INSERT INTO `sys_oper_log` VALUES (27, '代码生成', 6, 'com.myedu.project.tool.gen.controller.GenController.importTableSave()', 'POST', 1, 'admin', NULL, '/tool/gen/importTable', '127.0.0.1', '内网IP', 'sys_course', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 13:03:58');
INSERT INTO `sys_oper_log` VALUES (28, '字典类型', 1, 'com.myedu.project.system.controller.SysDictTypeController.add()', 'POST', 1, 'admin', NULL, '/system/dict/type', '127.0.0.1', '内网IP', '{\"remark\":\"课程类型\",\"params\":{},\"dictType\":\"course_type\",\"createBy\":\"admin\",\"dictName\":\"课程类型\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 13:06:23');
INSERT INTO `sys_oper_log` VALUES (29, '字典数据', 1, 'com.myedu.project.system.controller.SysDictDataController.add()', 'POST', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"1\",\"dictSort\":0,\"remark\":\"学校课程\",\"params\":{},\"dictType\":\"course_type\",\"dictLabel\":\"学校课程\",\"createBy\":\"admin\",\"default\":false,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 13:06:57');
INSERT INTO `sys_oper_log` VALUES (30, '字典数据', 1, 'com.myedu.project.system.controller.SysDictDataController.add()', 'POST', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"2\",\"dictSort\":1,\"remark\":\"课外课程\",\"params\":{},\"dictType\":\"course_type\",\"dictLabel\":\"课外课程\",\"createBy\":\"admin\",\"default\":false,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 13:07:38');
INSERT INTO `sys_oper_log` VALUES (31, '字典数据', 2, 'com.myedu.project.system.controller.SysDictDataController.edit()', 'PUT', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"1\",\"dictSort\":0,\"remark\":\"学校课程\",\"params\":{},\"dictType\":\"course_type\",\"dictLabel\":\"课内课程\",\"createBy\":\"admin\",\"default\":false,\"isDefault\":\"N\",\"createTime\":1577509617000,\"dictCode\":29,\"updateBy\":\"admin\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 13:07:46');
INSERT INTO `sys_oper_log` VALUES (32, '代码生成', 2, 'com.myedu.project.tool.gen.controller.GenController.editSave()', 'PUT', 1, 'admin', NULL, '/tool/gen', '127.0.0.1', '内网IP', '{\"functionAuthor\":\"myedu\",\"columns\":[{\"usableColumn\":false,\"columnId\":19,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"id\",\"htmlType\":\"input\",\"edit\":false,\"query\":false,\"sort\":1,\"list\":false,\"params\":{},\"javaType\":\"Long\",\"queryType\":\"EQ\",\"columnType\":\"bigint(20)\",\"createBy\":\"admin\",\"isPk\":\"1\",\"createTime\":1577509438000,\"tableId\":3,\"pk\":true,\"columnName\":\"id\"},{\"usableColumn\":false,\"columnId\":20,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"name\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"课程名称\",\"isQuery\":\"1\",\"sort\":2,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"LIKE\",\"columnType\":\"varchar(50)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1577509438000,\"isEdit\":\"1\",\"tableId\":3,\"pk\":false,\"columnName\":\"name\"},{\"usableColumn\":false,\"columnId\":21,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":false,\"superColumn\":true,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"remark\",\"htmlType\":\"textarea\",\"edit\":true,\"query\":false,\"columnComment\":\"课程说明\",\"sort\":3,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(500)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1577509438000,\"isEdit\":\"1\",\"tableId\":3,\"pk\":false,\"columnName\":\"remark\"},{\"usableColumn\":false,\"columnId\":22,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"course_type\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"type\",\"htmlType\":\"select\",\"edit\":true,\"query\":true,\"columnComment\":\"课程类型（1学校课程2兴趣班课程）\",\"isQuery\":\"1\",\"sort\":4,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"char(255)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1577509438000,\"isEdit\":\"1\",\"tableId\":3,\"pk\":false,\"columnName\":\"type\"},{\"usableColumn\":false,\"columnId\":23,\"isIncrement\":\"0', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 13:09:18');
INSERT INTO `sys_oper_log` VALUES (33, '代码生成', 2, 'com.myedu.project.tool.gen.controller.GenController.editSave()', 'PUT', 1, 'admin', NULL, '/tool/gen', '127.0.0.1', '内网IP', '{\"functionAuthor\":\"梁少鹏\",\"columns\":[{\"usableColumn\":false,\"columnId\":19,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"id\",\"htmlType\":\"input\",\"edit\":false,\"query\":false,\"updateTime\":1577509758000,\"sort\":1,\"list\":false,\"params\":{},\"javaType\":\"Long\",\"queryType\":\"EQ\",\"columnType\":\"bigint(20)\",\"createBy\":\"admin\",\"isPk\":\"1\",\"createTime\":1577509438000,\"tableId\":3,\"pk\":true,\"columnName\":\"id\"},{\"usableColumn\":false,\"columnId\":20,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"name\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"课程名称\",\"isQuery\":\"1\",\"updateTime\":1577509758000,\"sort\":2,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"LIKE\",\"columnType\":\"varchar(50)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1577509438000,\"isEdit\":\"1\",\"tableId\":3,\"pk\":false,\"columnName\":\"name\"},{\"usableColumn\":false,\"columnId\":21,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":false,\"superColumn\":true,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"remark\",\"htmlType\":\"textarea\",\"edit\":true,\"query\":false,\"columnComment\":\"课程说明\",\"updateTime\":1577509758000,\"sort\":3,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(500)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1577509438000,\"isEdit\":\"1\",\"tableId\":3,\"pk\":false,\"columnName\":\"remark\"},{\"usableColumn\":false,\"columnId\":22,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"course_type\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"type\",\"htmlType\":\"select\",\"edit\":true,\"query\":true,\"columnComment\":\"课程类型（1学校课程2兴趣班课程）\",\"isQuery\":\"1\",\"updateTime\":1577509758000,\"sort\":4,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"char(255)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1577509438000,\"is', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 13:09:47');
INSERT INTO `sys_oper_log` VALUES (34, '代码生成', 2, 'com.myedu.project.tool.gen.controller.GenController.editSave()', 'PUT', 1, 'admin', NULL, '/tool/gen', '127.0.0.1', '内网IP', '{\"functionAuthor\":\"梁少鹏\",\"columns\":[{\"usableColumn\":false,\"columnId\":19,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"id\",\"htmlType\":\"input\",\"edit\":false,\"query\":false,\"updateTime\":1577509787000,\"sort\":1,\"list\":false,\"params\":{},\"javaType\":\"Long\",\"queryType\":\"EQ\",\"columnType\":\"bigint(20)\",\"createBy\":\"admin\",\"isPk\":\"1\",\"createTime\":1577509438000,\"tableId\":3,\"pk\":true,\"columnName\":\"id\"},{\"usableColumn\":false,\"columnId\":20,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"name\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"课程名称\",\"isQuery\":\"1\",\"updateTime\":1577509787000,\"sort\":2,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"LIKE\",\"columnType\":\"varchar(50)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1577509438000,\"isEdit\":\"1\",\"tableId\":3,\"pk\":false,\"columnName\":\"name\"},{\"usableColumn\":false,\"columnId\":21,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":false,\"superColumn\":true,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"remark\",\"htmlType\":\"textarea\",\"edit\":true,\"query\":false,\"columnComment\":\"课程说明\",\"updateTime\":1577509787000,\"sort\":3,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(500)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1577509438000,\"isEdit\":\"1\",\"tableId\":3,\"pk\":false,\"columnName\":\"remark\"},{\"usableColumn\":false,\"columnId\":22,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"course_type\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"type\",\"htmlType\":\"select\",\"edit\":true,\"query\":true,\"columnComment\":\"课程类型（1学校课程2兴趣班课程）\",\"isQuery\":\"1\",\"updateTime\":1577509787000,\"sort\":4,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"char(255)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1577509438000,\"is', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 13:10:43');
INSERT INTO `sys_oper_log` VALUES (35, '代码生成', 8, 'com.myedu.project.tool.gen.controller.GenController.batchGenCode()', 'GET', 1, 'admin', NULL, '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{}', 'null', 0, NULL, '2019-12-28 13:10:58');
INSERT INTO `sys_oper_log` VALUES (36, '菜单管理', 1, 'com.myedu.project.system.controller.SysMenuController.add()', 'POST', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"excel\",\"orderNum\":\"1\",\"menuName\":\"课程管理\",\"params\":{},\"parentId\":1061,\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"C\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 13:38:43');
INSERT INTO `sys_oper_log` VALUES (37, '菜单管理', 2, 'com.myedu.project.system.controller.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"excel\",\"orderNum\":\"2\",\"menuName\":\"课程管理\",\"params\":{},\"parentId\":1061,\"path\":\"course\",\"component\":\"dataBasic/course/index\",\"children\":[],\"createTime\":1577511523000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1067,\"menuType\":\"C\",\"perms\":\"course\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 13:39:11');
INSERT INTO `sys_oper_log` VALUES (38, '菜单管理', 2, 'com.myedu.project.system.controller.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"excel\",\"orderNum\":\"2\",\"menuName\":\"课程管理\",\"params\":{},\"parentId\":1061,\"path\":\"course\",\"component\":\"dataBasic/course/index\",\"children\":[],\"createTime\":1577511523000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1067,\"menuType\":\"C\",\"perms\":\"dataBasic:course:list\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 13:39:35');
INSERT INTO `sys_oper_log` VALUES (39, '菜单管理', 1, 'com.myedu.project.system.controller.SysMenuController.add()', 'POST', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"orderNum\":\"1\",\"menuName\":\"课程查询\",\"params\":{},\"parentId\":1067,\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"F\",\"perms\":\"dataBasic:course:query\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 13:40:54');
INSERT INTO `sys_oper_log` VALUES (40, '菜单管理', 1, 'com.myedu.project.system.controller.SysMenuController.add()', 'POST', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"orderNum\":\"2\",\"menuName\":\"课程新增\",\"params\":{},\"parentId\":1067,\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"F\",\"perms\":\"dataBasic:course:add\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 13:41:25');
INSERT INTO `sys_oper_log` VALUES (41, '菜单管理', 1, 'com.myedu.project.system.controller.SysMenuController.add()', 'POST', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"orderNum\":\"3\",\"menuName\":\"课程修改\",\"params\":{},\"parentId\":1067,\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"F\",\"perms\":\"dataBasic:course:edit\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 13:41:58');
INSERT INTO `sys_oper_log` VALUES (42, '菜单管理', 1, 'com.myedu.project.system.controller.SysMenuController.add()', 'POST', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"orderNum\":\"4\",\"menuName\":\"课程删除\",\"params\":{},\"parentId\":1067,\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"F\",\"perms\":\"dataBasic:course:remove\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 13:42:24');
INSERT INTO `sys_oper_log` VALUES (43, '年级基础', 1, 'com.myedu.project.dataBasic.controller.SysGradeController.add()', 'POST', 1, 'admin', NULL, '/system/grade', '127.0.0.1', '内网IP', '{\"createBy\":\"admin\",\"createTime\":1577511955262,\"name\":\"二年级\",\"remark\":\"二年级\",\"id\":3,\"params\":{}}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 13:45:55');
INSERT INTO `sys_oper_log` VALUES (44, '角色管理', 2, 'com.myedu.project.system.controller.SysRoleController.dataScope()', 'PUT', 1, 'admin', NULL, '/system/role/dataScope', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":1,\"admin\":true,\"remark\":\"管理员\",\"dataScope\":\"1\",\"delFlag\":\"0\",\"params\":{},\"roleSort\":\"1\",\"createTime\":1521171180000,\"roleKey\":\"admin\",\"roleName\":\"管理员\",\"deptIds\":[],\"status\":\"0\"}', 'null', 1, '不允许操作超级管理员角色', '2019-12-28 13:47:07');
INSERT INTO `sys_oper_log` VALUES (45, '菜单管理', 2, 'com.myedu.project.system.controller.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"excel\",\"orderNum\":\"2\",\"menuName\":\"课程管理\",\"params\":{},\"parentId\":1061,\"path\":\"course\",\"component\":\"dataBasic/course/index\",\"children\":[],\"createTime\":1577511523000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1067,\"menuType\":\"C\",\"perms\":\"dataBasic:course:list\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 13:48:37');
INSERT INTO `sys_oper_log` VALUES (46, '课程基本数据', 1, 'com.myedu.project.dataBasic.controller.SysCourseController.add()', 'POST', 1, 'admin', NULL, '/dataBasic/course', '127.0.0.1', '内网IP', '{\"params\":{},\"type\":\"1\",\"createBy\":\"admin\",\"createTime\":1577512239797,\"name\":\"11\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 13:50:39');
INSERT INTO `sys_oper_log` VALUES (47, '年级基础', 3, 'com.myedu.project.dataBasic.controller.SysGradeController.remove()', 'DELETE', 1, 'admin', NULL, '/system/grade/3', '127.0.0.1', '内网IP', '{ids=3}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 13:54:28');
INSERT INTO `sys_oper_log` VALUES (48, '菜单管理', 2, 'com.myedu.project.system.controller.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"3\",\"menuName\":\"课程修改\",\"params\":{},\"parentId\":1067,\"path\":\"\",\"children\":[],\"createTime\":1577511718000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1070,\"menuType\":\"F\",\"perms\":\"dataBasic:course:edit\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 13:58:10');
INSERT INTO `sys_oper_log` VALUES (49, '菜单管理', 2, 'com.myedu.project.system.controller.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"4\",\"menuName\":\"课程删除\",\"params\":{},\"parentId\":1067,\"path\":\"\",\"children\":[],\"createTime\":1577511744000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1071,\"menuType\":\"F\",\"perms\":\"dataBasic:course:remove\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 13:58:17');
INSERT INTO `sys_oper_log` VALUES (50, '菜单管理', 2, 'com.myedu.project.system.controller.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"1\",\"menuName\":\"课程查询\",\"params\":{},\"parentId\":1067,\"path\":\"\",\"children\":[],\"createTime\":1577511654000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1068,\"menuType\":\"F\",\"perms\":\"dataBasic:course:query\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 13:59:02');
INSERT INTO `sys_oper_log` VALUES (51, '课程基本数据', 5, 'com.myedu.project.dataBasic.controller.SysCourseController.export()', 'GET', 1, 'admin', NULL, '/dataBasic/course/export', '127.0.0.1', '内网IP', '{}', '{\"msg\":\"12156f38-3647-4661-ad6b-16201af26e7b_course.xlsx\",\"code\":200}', 0, NULL, '2019-12-28 14:05:12');
INSERT INTO `sys_oper_log` VALUES (52, '课程基本数据', 2, 'com.myedu.project.dataBasic.controller.SysCourseController.edit()', 'PUT', 1, 'admin', NULL, '/dataBasic/course', '127.0.0.1', '内网IP', '{\"remark\":\"语文课程\",\"updateTime\":1577513827837,\"delFlag\":\"0\",\"params\":{},\"type\":\"1\",\"createBy\":\"admin\",\"createTime\":1577512239000,\"updateBy\":\"admin\",\"name\":\"语文\",\"id\":0}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 14:17:07');
INSERT INTO `sys_oper_log` VALUES (53, '年级基础', 1, 'com.myedu.project.dataBasic.controller.SysGradeController.add()', 'POST', 1, 'admin', NULL, '/dataBasic/grade', '127.0.0.1', '内网IP', '{\"createBy\":\"admin\",\"createTime\":1577513984470,\"name\":\"二年级\",\"remark\":\"二年级\",\"id\":4,\"params\":{}}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 14:19:44');
INSERT INTO `sys_oper_log` VALUES (54, '课程基本数据', 1, 'com.myedu.project.dataBasic.controller.SysCourseController.add()', 'POST', 1, 'admin', NULL, '/dataBasic/course', '127.0.0.1', '内网IP', '{\"remark\":\"数学\",\"params\":{},\"type\":\"1\",\"createBy\":\"admin\",\"createTime\":1577514413707,\"name\":\"数学\"}', 'null', 1, '\r\n### Error updating database.  Cause: java.sql.SQLIntegrityConstraintViolationException: Duplicate entry \'0\' for key \'PRIMARY\'\r\n### The error may involve com.myedu.project.dataBasic.mapper.SysCourseMapper.insertSysCourse-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into sys_course          ( name,             remark,             type,             create_by,             create_time )           values ( ?,             ?,             ?,             ?,             ? )\r\n### Cause: java.sql.SQLIntegrityConstraintViolationException: Duplicate entry \'0\' for key \'PRIMARY\'\n; Duplicate entry \'0\' for key \'PRIMARY\'; nested exception is java.sql.SQLIntegrityConstraintViolationException: Duplicate entry \'0\' for key \'PRIMARY\'', '2019-12-28 14:26:53');
INSERT INTO `sys_oper_log` VALUES (55, '课程基本数据', 1, 'com.myedu.project.dataBasic.controller.SysCourseController.add()', 'POST', 1, 'admin', NULL, '/dataBasic/course', '127.0.0.1', '内网IP', '{\"remark\":\"数学\",\"params\":{},\"type\":\"1\",\"createBy\":\"admin\",\"createTime\":1577514423272,\"name\":\"数学\"}', 'null', 1, '\r\n### Error updating database.  Cause: java.sql.SQLIntegrityConstraintViolationException: Duplicate entry \'0\' for key \'PRIMARY\'\r\n### The error may involve com.myedu.project.dataBasic.mapper.SysCourseMapper.insertSysCourse-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into sys_course          ( name,             remark,             type,             create_by,             create_time )           values ( ?,             ?,             ?,             ?,             ? )\r\n### Cause: java.sql.SQLIntegrityConstraintViolationException: Duplicate entry \'0\' for key \'PRIMARY\'\n; Duplicate entry \'0\' for key \'PRIMARY\'; nested exception is java.sql.SQLIntegrityConstraintViolationException: Duplicate entry \'0\' for key \'PRIMARY\'', '2019-12-28 14:27:03');
INSERT INTO `sys_oper_log` VALUES (56, '课程基本数据', 1, 'com.myedu.project.dataBasic.controller.SysCourseController.add()', 'POST', 1, 'admin', NULL, '/dataBasic/course', '127.0.0.1', '内网IP', '{\"remark\":\"数学\",\"params\":{},\"type\":\"1\",\"createBy\":\"admin\",\"createTime\":1577514446406,\"name\":\"数学\"}', 'null', 1, '\r\n### Error updating database.  Cause: java.sql.SQLIntegrityConstraintViolationException: Duplicate entry \'0\' for key \'PRIMARY\'\r\n### The error may involve com.myedu.project.dataBasic.mapper.SysCourseMapper.insertSysCourse-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into sys_course          ( name,             remark,             type,             create_by,             create_time )           values ( ?,             ?,             ?,             ?,             ? )\r\n### Cause: java.sql.SQLIntegrityConstraintViolationException: Duplicate entry \'0\' for key \'PRIMARY\'\n; Duplicate entry \'0\' for key \'PRIMARY\'; nested exception is java.sql.SQLIntegrityConstraintViolationException: Duplicate entry \'0\' for key \'PRIMARY\'', '2019-12-28 14:27:26');
INSERT INTO `sys_oper_log` VALUES (57, '课程基本数据', 1, 'com.myedu.project.dataBasic.controller.SysCourseController.add()', 'POST', 1, 'admin', NULL, '/dataBasic/course', '127.0.0.1', '内网IP', '{\"remark\":\"数学\",\"params\":{},\"type\":\"1\",\"createBy\":\"admin\",\"createTime\":1577514456713,\"name\":\"数学\"}', 'null', 1, '\r\n### Error updating database.  Cause: java.sql.SQLIntegrityConstraintViolationException: Duplicate entry \'0\' for key \'PRIMARY\'\r\n### The error may involve com.myedu.project.dataBasic.mapper.SysCourseMapper.insertSysCourse-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into sys_course          ( name,             remark,             type,             create_by,             create_time )           values ( ?,             ?,             ?,             ?,             ? )\r\n### Cause: java.sql.SQLIntegrityConstraintViolationException: Duplicate entry \'0\' for key \'PRIMARY\'\n; Duplicate entry \'0\' for key \'PRIMARY\'; nested exception is java.sql.SQLIntegrityConstraintViolationException: Duplicate entry \'0\' for key \'PRIMARY\'', '2019-12-28 14:27:36');
INSERT INTO `sys_oper_log` VALUES (58, '课程基本数据', 1, 'com.myedu.project.dataBasic.controller.SysCourseController.add()', 'POST', 1, 'admin', NULL, '/dataBasic/course', '127.0.0.1', '内网IP', '{\"remark\":\"数学课\",\"params\":{},\"type\":\"1\",\"createBy\":\"admin\",\"createTime\":1577514504637,\"name\":\"数学\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 14:28:24');
INSERT INTO `sys_oper_log` VALUES (59, '课程基本数据', 1, 'com.myedu.project.dataBasic.controller.SysCourseController.add()', 'POST', 1, 'admin', NULL, '/dataBasic/course', '127.0.0.1', '内网IP', '{\"remark\":\"语文课\",\"params\":{},\"type\":\"1\",\"createBy\":\"admin\",\"createTime\":1577514519170,\"name\":\"语文课\"}', 'null', 1, '\r\n### Error updating database.  Cause: java.sql.SQLIntegrityConstraintViolationException: Duplicate entry \'0\' for key \'PRIMARY\'\r\n### The error may involve com.myedu.project.dataBasic.mapper.SysCourseMapper.insertSysCourse-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into sys_course          ( name,             remark,             type,             create_by,             create_time )           values ( ?,             ?,             ?,             ?,             ? )\r\n### Cause: java.sql.SQLIntegrityConstraintViolationException: Duplicate entry \'0\' for key \'PRIMARY\'\n; Duplicate entry \'0\' for key \'PRIMARY\'; nested exception is java.sql.SQLIntegrityConstraintViolationException: Duplicate entry \'0\' for key \'PRIMARY\'', '2019-12-28 14:28:39');
INSERT INTO `sys_oper_log` VALUES (60, '课程基本数据', 1, 'com.myedu.project.dataBasic.controller.SysCourseController.add()', 'POST', 1, 'admin', NULL, '/dataBasic/course', '127.0.0.1', '内网IP', '{\"remark\":\"语文课\",\"params\":{},\"type\":\"1\",\"createBy\":\"admin\",\"createTime\":1577514612966,\"name\":\"语文\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 14:30:12');
INSERT INTO `sys_oper_log` VALUES (61, '课程基本数据', 1, 'com.myedu.project.dataBasic.controller.SysCourseController.add()', 'POST', 1, 'admin', NULL, '/dataBasic/course', '127.0.0.1', '内网IP', '{\"remark\":\"数学课\",\"params\":{},\"type\":\"1\",\"createBy\":\"admin\",\"createTime\":1577514621921,\"name\":\"数学\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 14:30:21');
INSERT INTO `sys_oper_log` VALUES (62, '课程基本数据', 1, 'com.myedu.project.dataBasic.controller.SysCourseController.add()', 'POST', 1, 'admin', NULL, '/dataBasic/course', '127.0.0.1', '内网IP', '{\"remark\":\"钢琴课\",\"params\":{},\"type\":\"2\",\"createBy\":\"admin\",\"createTime\":1577514744935,\"name\":\"钢琴课\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 14:32:24');
INSERT INTO `sys_oper_log` VALUES (63, '角色管理', 1, 'com.myedu.project.system.controller.SysRoleController.add()', 'POST', 1, 'admin', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":3,\"admin\":false,\"remark\":\"家长角色\",\"params\":{},\"roleSort\":\"3\",\"createBy\":\"admin\",\"roleKey\":\"parents\",\"roleName\":\"家长\",\"deptIds\":[],\"menuIds\":[1046,3,113,114,1055,1056,1058,1057,1059,1060,115,2,109],\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 15:01:01');
INSERT INTO `sys_oper_log` VALUES (64, '角色管理', 1, 'com.myedu.project.system.controller.SysRoleController.add()', 'POST', 1, 'admin', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":4,\"admin\":false,\"remark\":\"门店管理员\",\"params\":{},\"roleSort\":\"4\",\"createBy\":\"admin\",\"roleKey\":\"store_manager\",\"roleName\":\"门店管理员\",\"deptIds\":[],\"menuIds\":[109,1046,1047,1048,2],\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 15:02:27');
INSERT INTO `sys_oper_log` VALUES (65, '角色管理', 1, 'com.myedu.project.system.controller.SysRoleController.add()', 'POST', 1, 'admin', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":5,\"admin\":false,\"remark\":\"门店老师\",\"params\":{},\"roleSort\":\"5\",\"createBy\":\"admin\",\"roleKey\":\"store_teacher\",\"roleName\":\"门店老师\",\"deptIds\":[],\"menuIds\":[109,1046,1047,1048,2],\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 15:04:22');
INSERT INTO `sys_oper_log` VALUES (66, '部门管理', 3, 'com.myedu.project.system.controller.SysDeptController.remove()', 'DELETE', 1, 'admin', NULL, '/system/dept/109', '127.0.0.1', '内网IP', '{deptId=109}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 15:08:22');
INSERT INTO `sys_oper_log` VALUES (67, '部门管理', 3, 'com.myedu.project.system.controller.SysDeptController.remove()', 'DELETE', 1, 'admin', NULL, '/system/dept/108', '127.0.0.1', '内网IP', '{deptId=108}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 15:08:27');
INSERT INTO `sys_oper_log` VALUES (68, '部门管理', 3, 'com.myedu.project.system.controller.SysDeptController.remove()', 'DELETE', 1, 'admin', NULL, '/system/dept/102', '127.0.0.1', '内网IP', '{deptId=102}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 15:08:29');
INSERT INTO `sys_oper_log` VALUES (69, '部门管理', 3, 'com.myedu.project.system.controller.SysDeptController.remove()', 'DELETE', 1, 'admin', NULL, '/system/dept/107', '127.0.0.1', '内网IP', '{deptId=107}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 15:08:32');
INSERT INTO `sys_oper_log` VALUES (70, '部门管理', 3, 'com.myedu.project.system.controller.SysDeptController.remove()', 'DELETE', 1, 'admin', NULL, '/system/dept/106', '127.0.0.1', '内网IP', '{deptId=106}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 15:08:33');
INSERT INTO `sys_oper_log` VALUES (71, '部门管理', 3, 'com.myedu.project.system.controller.SysDeptController.remove()', 'DELETE', 1, 'admin', NULL, '/system/dept/105', '127.0.0.1', '内网IP', '{deptId=105}', '{\"msg\":\"部门存在用户,不允许删除\",\"code\":500}', 0, NULL, '2019-12-28 15:08:35');
INSERT INTO `sys_oper_log` VALUES (72, '部门管理', 3, 'com.myedu.project.system.controller.SysDeptController.remove()', 'DELETE', 1, 'admin', NULL, '/system/dept/104', '127.0.0.1', '内网IP', '{deptId=104}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 15:08:43');
INSERT INTO `sys_oper_log` VALUES (73, '部门管理', 3, 'com.myedu.project.system.controller.SysDeptController.remove()', 'DELETE', 1, 'admin', NULL, '/system/dept/103', '127.0.0.1', '内网IP', '{deptId=103}', '{\"msg\":\"部门存在用户,不允许删除\",\"code\":500}', 0, NULL, '2019-12-28 15:08:45');
INSERT INTO `sys_oper_log` VALUES (74, '字典类型', 3, 'com.myedu.project.system.controller.SysDictDataController.remove()', 'DELETE', 1, 'admin', NULL, '/system/dict/data/3', '127.0.0.1', '内网IP', '{dictCodes=3}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 15:19:29');
INSERT INTO `sys_oper_log` VALUES (75, '字典数据', 2, 'com.myedu.project.system.controller.SysDictDataController.edit()', 'PUT', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"0\",\"dictSort\":0,\"remark\":\"学校课程\",\"params\":{},\"dictType\":\"course_type\",\"dictLabel\":\"课内课程\",\"createBy\":\"admin\",\"default\":false,\"isDefault\":\"N\",\"createTime\":1577509617000,\"dictCode\":29,\"updateBy\":\"admin\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 15:20:11');
INSERT INTO `sys_oper_log` VALUES (76, '字典数据', 2, 'com.myedu.project.system.controller.SysDictDataController.edit()', 'PUT', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"1\",\"dictSort\":1,\"remark\":\"课外课程\",\"params\":{},\"dictType\":\"course_type\",\"dictLabel\":\"课外课程\",\"createBy\":\"admin\",\"default\":false,\"isDefault\":\"N\",\"createTime\":1577509658000,\"dictCode\":30,\"updateBy\":\"admin\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 15:20:15');
INSERT INTO `sys_oper_log` VALUES (77, '课程基本数据', 2, 'com.myedu.project.dataBasic.controller.SysCourseController.edit()', 'PUT', 1, 'admin', NULL, '/dataBasic/course', '127.0.0.1', '内网IP', '{\"remark\":\"钢琴课\",\"updateTime\":1577517671129,\"delFlag\":\"0\",\"params\":{},\"type\":\"1\",\"createBy\":\"admin\",\"createTime\":1577514744000,\"updateBy\":\"admin\",\"name\":\"钢琴课\",\"id\":4}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 15:21:11');
INSERT INTO `sys_oper_log` VALUES (78, '字典数据', 2, 'com.myedu.project.system.controller.SysDictDataController.edit()', 'PUT', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"0\",\"dictSort\":0,\"remark\":\"学校课程\",\"params\":{},\"dictType\":\"course_type\",\"dictLabel\":\"校内课程\",\"createBy\":\"admin\",\"default\":false,\"isDefault\":\"N\",\"createTime\":1577509617000,\"dictCode\":29,\"updateBy\":\"admin\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 15:21:46');
INSERT INTO `sys_oper_log` VALUES (79, '字典数据', 2, 'com.myedu.project.system.controller.SysDictDataController.edit()', 'PUT', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"1\",\"dictSort\":1,\"remark\":\"课外课程\",\"params\":{},\"dictType\":\"course_type\",\"dictLabel\":\"校外课程\",\"createBy\":\"admin\",\"default\":false,\"isDefault\":\"N\",\"createTime\":1577509658000,\"dictCode\":30,\"updateBy\":\"admin\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 15:21:56');
INSERT INTO `sys_oper_log` VALUES (80, '字典数据', 2, 'com.myedu.project.system.controller.SysDictDataController.edit()', 'PUT', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"1\",\"dictSort\":1,\"remark\":\"校外课程\",\"params\":{},\"dictType\":\"course_type\",\"dictLabel\":\"校外课程\",\"createBy\":\"admin\",\"default\":false,\"isDefault\":\"N\",\"createTime\":1577509658000,\"dictCode\":30,\"updateBy\":\"admin\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 15:22:04');
INSERT INTO `sys_oper_log` VALUES (81, '字典数据', 2, 'com.myedu.project.system.controller.SysDictDataController.edit()', 'PUT', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"0\",\"dictSort\":0,\"remark\":\"校内课程\",\"params\":{},\"dictType\":\"course_type\",\"dictLabel\":\"校内课程\",\"createBy\":\"admin\",\"default\":false,\"isDefault\":\"N\",\"createTime\":1577509617000,\"dictCode\":29,\"updateBy\":\"admin\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 15:22:13');
INSERT INTO `sys_oper_log` VALUES (82, '课程基本数据', 2, 'com.myedu.project.dataBasic.controller.SysCourseController.edit()', 'PUT', 1, 'admin', NULL, '/dataBasic/course', '127.0.0.1', '内网IP', '{\"remark\":\"钢琴课\",\"updateTime\":1577517859285,\"delFlag\":\"0\",\"params\":{},\"type\":\"1\",\"createBy\":\"admin\",\"createTime\":1577514744000,\"updateBy\":\"admin\",\"name\":\"钢琴课\",\"id\":4}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 15:24:19');
INSERT INTO `sys_oper_log` VALUES (83, '菜单管理', 1, 'com.myedu.project.system.controller.SysMenuController.add()', 'POST', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"people\",\"orderNum\":\"5\",\"menuName\":\"家长宝贝\",\"params\":{},\"parentId\":0,\"path\":\"parents_baby\",\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"M\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 15:28:46');
INSERT INTO `sys_oper_log` VALUES (84, '菜单管理', 2, 'com.myedu.project.system.controller.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"people\",\"orderNum\":\"5\",\"menuName\":\"家长端管理\",\"params\":{},\"parentId\":0,\"path\":\"parents_baby\",\"children\":[],\"createTime\":1577518126000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1072,\"menuType\":\"M\",\"perms\":\"\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 15:30:22');
INSERT INTO `sys_oper_log` VALUES (85, '菜单管理', 1, 'com.myedu.project.system.controller.SysMenuController.add()', 'POST', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"peoples\",\"orderNum\":\"1\",\"menuName\":\"学生管理\",\"params\":{},\"parentId\":1072,\"path\":\"student\",\"component\":\"parents/student/index\",\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"C\",\"perms\":\"parents:student:list\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 15:33:02');
INSERT INTO `sys_oper_log` VALUES (86, '菜单管理', 1, 'com.myedu.project.system.controller.SysMenuController.add()', 'POST', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"orderNum\":\"1\",\"menuName\":\"学生查询\",\"params\":{},\"parentId\":1073,\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"F\",\"perms\":\"parents:student:query\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 15:35:07');
INSERT INTO `sys_oper_log` VALUES (87, '菜单管理', 1, 'com.myedu.project.system.controller.SysMenuController.add()', 'POST', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"orderNum\":\"2\",\"menuName\":\"学生新增\",\"params\":{},\"parentId\":1073,\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"F\",\"perms\":\"parents:student:add\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 15:36:19');
INSERT INTO `sys_oper_log` VALUES (88, '菜单管理', 1, 'com.myedu.project.system.controller.SysMenuController.add()', 'POST', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"orderNum\":\"3\",\"menuName\":\"学生修改\",\"params\":{},\"parentId\":1073,\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"F\",\"perms\":\"parents:student:edit\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 15:36:57');
INSERT INTO `sys_oper_log` VALUES (89, '菜单管理', 1, 'com.myedu.project.system.controller.SysMenuController.add()', 'POST', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"orderNum\":\"4\",\"menuName\":\"学生删除\",\"params\":{},\"parentId\":1073,\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"F\",\"perms\":\"parents:student:remove\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 15:37:40');
INSERT INTO `sys_oper_log` VALUES (90, '代码生成', 6, 'com.myedu.project.tool.gen.controller.GenController.importTableSave()', 'POST', 1, 'admin', NULL, '/tool/gen/importTable', '127.0.0.1', '内网IP', 'yun_student', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 15:39:51');
INSERT INTO `sys_oper_log` VALUES (91, '代码生成', 2, 'com.myedu.project.tool.gen.controller.GenController.editSave()', 'PUT', 1, 'admin', NULL, '/tool/gen', '127.0.0.1', '内网IP', '{\"functionAuthor\":\"梁少鹏\",\"columns\":[{\"usableColumn\":false,\"columnId\":28,\"isIncrement\":\"1\",\"increment\":true,\"insert\":true,\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"id\",\"htmlType\":\"input\",\"edit\":false,\"query\":false,\"columnComment\":\"主键\",\"sort\":1,\"list\":false,\"params\":{},\"javaType\":\"Long\",\"queryType\":\"EQ\",\"columnType\":\"bigint(20)\",\"createBy\":\"admin\",\"isPk\":\"1\",\"createTime\":1577518791000,\"tableId\":4,\"pk\":true,\"columnName\":\"id\"},{\"usableColumn\":false,\"columnId\":29,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"name\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"学生名称\",\"isQuery\":\"1\",\"sort\":2,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"LIKE\",\"columnType\":\"varchar(255)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1577518791000,\"isEdit\":\"1\",\"tableId\":4,\"pk\":false,\"columnName\":\"name\"},{\"usableColumn\":false,\"columnId\":30,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"sys_user_sex\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"gendel\",\"htmlType\":\"select\",\"edit\":true,\"query\":true,\"columnComment\":\"学生性别（1：男2：女）\",\"isQuery\":\"1\",\"sort\":3,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"char(1)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1577518791000,\"isEdit\":\"1\",\"tableId\":4,\"pk\":false,\"columnName\":\"gendel\"},{\"usableColumn\":false,\"columnId\":31,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"avatar\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"学生头像地址\",\"isQuery\":\"1\",\"sort\":4,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"text\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1577518791000,\"isEdit\":\"1\",\"tableId\":4,\"pk\":false,\"columnName\":\"avatar\"},{\"usableColumn\":false,\"columnI', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 15:42:04');
INSERT INTO `sys_oper_log` VALUES (92, '代码生成', 8, 'com.myedu.project.tool.gen.controller.GenController.batchGenCode()', 'GET', 1, 'admin', NULL, '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{}', 'null', 0, NULL, '2019-12-28 15:42:07');
INSERT INTO `sys_oper_log` VALUES (93, '菜单管理', 2, 'com.myedu.project.system.controller.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"peoples\",\"orderNum\":\"1\",\"menuName\":\"学生管理\",\"params\":{},\"parentId\":1072,\"path\":\"parents\",\"component\":\"parents/student/index\",\"children\":[],\"createTime\":1577518382000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1073,\"menuType\":\"C\",\"perms\":\"parents:student:list\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 15:50:39');
INSERT INTO `sys_oper_log` VALUES (94, '菜单管理', 2, 'com.myedu.project.system.controller.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"peoples\",\"orderNum\":\"1\",\"menuName\":\"学生管理\",\"params\":{},\"parentId\":1072,\"path\":\"student\",\"component\":\"parents/student/index\",\"children\":[],\"createTime\":1577518382000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1073,\"menuType\":\"C\",\"perms\":\"parents:student:list\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 15:51:13');
INSERT INTO `sys_oper_log` VALUES (95, '菜单管理', 2, 'com.myedu.project.system.controller.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"people\",\"orderNum\":\"5\",\"menuName\":\"家长端管理\",\"params\":{},\"parentId\":0,\"path\":\"parents\",\"children\":[],\"createTime\":1577518126000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1072,\"menuType\":\"M\",\"perms\":\"\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 15:54:07');
INSERT INTO `sys_oper_log` VALUES (96, '菜单管理', 2, 'com.myedu.project.system.controller.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"peoples\",\"orderNum\":\"1\",\"menuName\":\"学生管理\",\"params\":{},\"parentId\":1072,\"path\":\"student\",\"component\":\"parents/student/index\",\"children\":[],\"createTime\":1577518382000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1073,\"menuType\":\"C\",\"perms\":\"parents:student:list\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 15:55:39');
INSERT INTO `sys_oper_log` VALUES (97, '课程基本数据', 2, 'com.myedu.project.dataBasic.controller.SysCourseController.edit()', 'PUT', 1, 'admin', NULL, '/dataBasic/course', '127.0.0.1', '内网IP', '{\"remark\":\"语文课\",\"updateTime\":1577519821828,\"delFlag\":\"0\",\"params\":{},\"type\":\"0\",\"createBy\":\"admin\",\"createTime\":1577514612000,\"updateBy\":\"admin\",\"name\":\"语文\",\"id\":2}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 15:57:01');
INSERT INTO `sys_oper_log` VALUES (98, '课程基本数据', 2, 'com.myedu.project.dataBasic.controller.SysCourseController.edit()', 'PUT', 1, 'admin', NULL, '/dataBasic/course', '127.0.0.1', '内网IP', '{\"remark\":\"数学课\",\"updateTime\":1577519825933,\"delFlag\":\"0\",\"params\":{},\"type\":\"0\",\"createBy\":\"admin\",\"createTime\":1577514621000,\"updateBy\":\"admin\",\"name\":\"数学\",\"id\":3}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 15:57:05');
INSERT INTO `sys_oper_log` VALUES (99, '字典类型', 1, 'com.myedu.project.system.controller.SysDictTypeController.add()', 'POST', 1, 'admin', NULL, '/system/dict/type', '127.0.0.1', '内网IP', '{\"remark\":\"过敏类型\",\"params\":{},\"dictType\":\"allergic_type\",\"createBy\":\"admin\",\"dictName\":\"过敏类型\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 17:03:43');
INSERT INTO `sys_oper_log` VALUES (100, '字典数据', 1, 'com.myedu.project.system.controller.SysDictDataController.add()', 'POST', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"0\",\"dictSort\":0,\"remark\":\"牛奶过敏\",\"params\":{},\"dictType\":\"allergic_type\",\"dictLabel\":\"牛奶\",\"createBy\":\"admin\",\"default\":false,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 17:04:10');
INSERT INTO `sys_oper_log` VALUES (101, '字典数据', 1, 'com.myedu.project.system.controller.SysDictDataController.add()', 'POST', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"1\",\"dictSort\":1,\"remark\":\"海鲜过敏\",\"params\":{},\"dictType\":\"allergic_type\",\"dictLabel\":\"海鲜\",\"createBy\":\"admin\",\"default\":false,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 17:04:36');
INSERT INTO `sys_oper_log` VALUES (102, '字典数据', 1, 'com.myedu.project.system.controller.SysDictDataController.add()', 'POST', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"3\",\"dictSort\":3,\"remark\":\"鸡蛋过敏\",\"params\":{},\"dictType\":\"allergic_type\",\"dictLabel\":\"鸡蛋\",\"createBy\":\"admin\",\"default\":false,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 17:04:55');
INSERT INTO `sys_oper_log` VALUES (103, '字典数据', 1, 'com.myedu.project.system.controller.SysDictDataController.add()', 'POST', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"4\",\"dictSort\":4,\"remark\":\"豆制品过敏\",\"params\":{},\"dictType\":\"allergic_type\",\"dictLabel\":\"豆制品\",\"createBy\":\"admin\",\"default\":false,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 17:05:30');
INSERT INTO `sys_oper_log` VALUES (104, '字典数据', 1, 'com.myedu.project.system.controller.SysDictDataController.add()', 'POST', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"5\",\"dictSort\":5,\"remark\":\"花粉过敏\",\"params\":{},\"dictType\":\"allergic_type\",\"dictLabel\":\"花粉\",\"createBy\":\"admin\",\"default\":false,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 17:05:56');
INSERT INTO `sys_oper_log` VALUES (105, '学生数据', 1, 'com.myedu.project.parents.controller.YunStudentController.add()', 'POST', 1, 'admin', NULL, '/parents/student', '127.0.0.1', '内网IP', '{\"gradeId\":12,\"params\":{},\"createBy\":\"admin\",\"createTime\":1577524313881,\"school\":\"sda\",\"name\":\"小乌马河\",\"createById\":1,\"id\":1,\"gendel\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2019-12-28 17:11:53');

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`  (
  `post_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '岗位名称',
  `post_sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '岗位信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES (1, 'ceo', '董事长', 1, '0', 'admin', '2018-03-16 11:33:00', 'admin', '2019-12-26 21:38:20', '');
INSERT INTO `sys_post` VALUES (2, 'se', '项目经理', 2, '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_post` VALUES (3, 'hr', '人力资源', 3, '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_post` VALUES (4, 'user', '普通员工', 4, '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', 'admin', 1, '1', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '管理员');
INSERT INTO `sys_role` VALUES (2, '普通角色', 'common', 2, '2', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '普通角色');
INSERT INTO `sys_role` VALUES (3, '家长', 'parents', 3, '1', '0', '0', 'admin', '2019-12-28 15:01:01', '', NULL, '家长角色');
INSERT INTO `sys_role` VALUES (4, '门店管理员', 'store_manager', 4, '1', '0', '0', 'admin', '2019-12-28 15:02:27', '', NULL, '门店管理员');
INSERT INTO `sys_role` VALUES (5, '门店老师', 'store_teacher', 5, '1', '0', '0', 'admin', '2019-12-28 15:04:22', '', NULL, '门店老师');

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`, `dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色和部门关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES (2, 100);
INSERT INTO `sys_role_dept` VALUES (2, 101);
INSERT INTO `sys_role_dept` VALUES (2, 105);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色和菜单关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (2, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2);
INSERT INTO `sys_role_menu` VALUES (2, 3);
INSERT INTO `sys_role_menu` VALUES (2, 4);
INSERT INTO `sys_role_menu` VALUES (2, 100);
INSERT INTO `sys_role_menu` VALUES (2, 101);
INSERT INTO `sys_role_menu` VALUES (2, 102);
INSERT INTO `sys_role_menu` VALUES (2, 103);
INSERT INTO `sys_role_menu` VALUES (2, 104);
INSERT INTO `sys_role_menu` VALUES (2, 105);
INSERT INTO `sys_role_menu` VALUES (2, 106);
INSERT INTO `sys_role_menu` VALUES (2, 107);
INSERT INTO `sys_role_menu` VALUES (2, 108);
INSERT INTO `sys_role_menu` VALUES (2, 109);
INSERT INTO `sys_role_menu` VALUES (2, 110);
INSERT INTO `sys_role_menu` VALUES (2, 111);
INSERT INTO `sys_role_menu` VALUES (2, 112);
INSERT INTO `sys_role_menu` VALUES (2, 113);
INSERT INTO `sys_role_menu` VALUES (2, 114);
INSERT INTO `sys_role_menu` VALUES (2, 115);
INSERT INTO `sys_role_menu` VALUES (2, 500);
INSERT INTO `sys_role_menu` VALUES (2, 501);
INSERT INTO `sys_role_menu` VALUES (2, 1000);
INSERT INTO `sys_role_menu` VALUES (2, 1001);
INSERT INTO `sys_role_menu` VALUES (2, 1002);
INSERT INTO `sys_role_menu` VALUES (2, 1003);
INSERT INTO `sys_role_menu` VALUES (2, 1004);
INSERT INTO `sys_role_menu` VALUES (2, 1005);
INSERT INTO `sys_role_menu` VALUES (2, 1006);
INSERT INTO `sys_role_menu` VALUES (2, 1007);
INSERT INTO `sys_role_menu` VALUES (2, 1008);
INSERT INTO `sys_role_menu` VALUES (2, 1009);
INSERT INTO `sys_role_menu` VALUES (2, 1010);
INSERT INTO `sys_role_menu` VALUES (2, 1011);
INSERT INTO `sys_role_menu` VALUES (2, 1012);
INSERT INTO `sys_role_menu` VALUES (2, 1013);
INSERT INTO `sys_role_menu` VALUES (2, 1014);
INSERT INTO `sys_role_menu` VALUES (2, 1015);
INSERT INTO `sys_role_menu` VALUES (2, 1016);
INSERT INTO `sys_role_menu` VALUES (2, 1017);
INSERT INTO `sys_role_menu` VALUES (2, 1018);
INSERT INTO `sys_role_menu` VALUES (2, 1019);
INSERT INTO `sys_role_menu` VALUES (2, 1020);
INSERT INTO `sys_role_menu` VALUES (2, 1021);
INSERT INTO `sys_role_menu` VALUES (2, 1022);
INSERT INTO `sys_role_menu` VALUES (2, 1023);
INSERT INTO `sys_role_menu` VALUES (2, 1024);
INSERT INTO `sys_role_menu` VALUES (2, 1025);
INSERT INTO `sys_role_menu` VALUES (2, 1026);
INSERT INTO `sys_role_menu` VALUES (2, 1027);
INSERT INTO `sys_role_menu` VALUES (2, 1028);
INSERT INTO `sys_role_menu` VALUES (2, 1029);
INSERT INTO `sys_role_menu` VALUES (2, 1030);
INSERT INTO `sys_role_menu` VALUES (2, 1031);
INSERT INTO `sys_role_menu` VALUES (2, 1032);
INSERT INTO `sys_role_menu` VALUES (2, 1033);
INSERT INTO `sys_role_menu` VALUES (2, 1034);
INSERT INTO `sys_role_menu` VALUES (2, 1035);
INSERT INTO `sys_role_menu` VALUES (2, 1036);
INSERT INTO `sys_role_menu` VALUES (2, 1037);
INSERT INTO `sys_role_menu` VALUES (2, 1038);
INSERT INTO `sys_role_menu` VALUES (2, 1039);
INSERT INTO `sys_role_menu` VALUES (2, 1040);
INSERT INTO `sys_role_menu` VALUES (2, 1041);
INSERT INTO `sys_role_menu` VALUES (2, 1042);
INSERT INTO `sys_role_menu` VALUES (2, 1043);
INSERT INTO `sys_role_menu` VALUES (2, 1044);
INSERT INTO `sys_role_menu` VALUES (2, 1045);
INSERT INTO `sys_role_menu` VALUES (2, 1046);
INSERT INTO `sys_role_menu` VALUES (2, 1047);
INSERT INTO `sys_role_menu` VALUES (2, 1048);
INSERT INTO `sys_role_menu` VALUES (2, 1049);
INSERT INTO `sys_role_menu` VALUES (2, 1050);
INSERT INTO `sys_role_menu` VALUES (2, 1051);
INSERT INTO `sys_role_menu` VALUES (2, 1052);
INSERT INTO `sys_role_menu` VALUES (2, 1053);
INSERT INTO `sys_role_menu` VALUES (2, 1054);
INSERT INTO `sys_role_menu` VALUES (2, 1055);
INSERT INTO `sys_role_menu` VALUES (2, 1056);
INSERT INTO `sys_role_menu` VALUES (2, 1057);
INSERT INTO `sys_role_menu` VALUES (2, 1058);
INSERT INTO `sys_role_menu` VALUES (2, 1059);
INSERT INTO `sys_role_menu` VALUES (2, 1060);
INSERT INTO `sys_role_menu` VALUES (3, 2);
INSERT INTO `sys_role_menu` VALUES (3, 3);
INSERT INTO `sys_role_menu` VALUES (3, 109);
INSERT INTO `sys_role_menu` VALUES (3, 113);
INSERT INTO `sys_role_menu` VALUES (3, 114);
INSERT INTO `sys_role_menu` VALUES (3, 115);
INSERT INTO `sys_role_menu` VALUES (3, 1046);
INSERT INTO `sys_role_menu` VALUES (3, 1055);
INSERT INTO `sys_role_menu` VALUES (3, 1056);
INSERT INTO `sys_role_menu` VALUES (3, 1057);
INSERT INTO `sys_role_menu` VALUES (3, 1058);
INSERT INTO `sys_role_menu` VALUES (3, 1059);
INSERT INTO `sys_role_menu` VALUES (3, 1060);
INSERT INTO `sys_role_menu` VALUES (4, 2);
INSERT INTO `sys_role_menu` VALUES (4, 109);
INSERT INTO `sys_role_menu` VALUES (4, 1046);
INSERT INTO `sys_role_menu` VALUES (4, 1047);
INSERT INTO `sys_role_menu` VALUES (4, 1048);
INSERT INTO `sys_role_menu` VALUES (5, 2);
INSERT INTO `sys_role_menu` VALUES (5, 109);
INSERT INTO `sys_role_menu` VALUES (5, 1046);
INSERT INTO `sys_role_menu` VALUES (5, 1047);
INSERT INTO `sys_role_menu` VALUES (5, 1048);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门ID',
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户账号',
  `nick_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户昵称',
  `user_type` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '00' COMMENT '用户类型（00系统管理员用户，01机构负责人，02老师，03家长）',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '手机号码',
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '密码',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '最后登陆IP',
  `login_date` datetime NULL DEFAULT NULL COMMENT '最后登陆时间',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  `address` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '住址',
  `open_id` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `id_card` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证',
  `education` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '学历',
  `certificate` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '证书图片地址',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 103, 'admin', '若依', '00', 'ry@163.com', '15888888888', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '管理员', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (2, 105, 'ry', '若依', '00', 'ry@qq.com', '15666666666', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '测试员', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`, `post_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户与岗位关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post` VALUES (1, 1);
INSERT INTO `sys_user_post` VALUES (2, 2);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户和角色关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);

-- ----------------------------
-- Table structure for yun_store
-- ----------------------------
DROP TABLE IF EXISTS `yun_store`;
CREATE TABLE `yun_store`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '门店名称',
  `logo` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '门店logo',
  `manager` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '门店负责人',
  `manager_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '负责人电话',
  `photos` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '门店照片墙',
  `video` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '门店宣传视频地址',
  `license` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '门店营业执照',
  `address` varchar(300) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '门店详细地址',
  `address_map` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '门店地图坐标经纬度详情',
  `type` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '门店类型（1托管班2培训机构3早教机构4家教5幼儿园）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '门店表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for yun_stu_hw
-- ----------------------------
DROP TABLE IF EXISTS `yun_stu_hw`;
CREATE TABLE `yun_stu_hw`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `height` decimal(20, 2) NULL DEFAULT NULL COMMENT '身高（cm）',
  `weight` decimal(20, 2) NULL DEFAULT NULL COMMENT '体重（kg）',
  `student_id` bigint(20) NULL DEFAULT NULL COMMENT '关联学生id',
  `remark` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '备注',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '学生身高体重记录表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for yun_stu_leave
-- ----------------------------
DROP TABLE IF EXISTS `yun_stu_leave`;
CREATE TABLE `yun_stu_leave`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `reason` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '请假原因',
  `start_time` date NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` date NULL DEFAULT NULL COMMENT '结束时间',
  `student_id` bigint(20) NULL DEFAULT NULL COMMENT '关联学生ID',
  `remark` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '备注',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '学生请假表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for yun_stu_mistake
-- ----------------------------
DROP TABLE IF EXISTS `yun_stu_mistake`;
CREATE TABLE `yun_stu_mistake`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `source` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '错题来源',
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '错题类型',
  `reason` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '错题原因',
  `images` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '错题图片地址',
  `solutions` varchar(300) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '解决思路',
  `student_id` bigint(20) NULL DEFAULT NULL COMMENT '关联学生id',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '学生错题记录表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for yun_student
-- ----------------------------
DROP TABLE IF EXISTS `yun_student`;
CREATE TABLE `yun_student`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '学生名称',
  `gendel` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '1' COMMENT '学生性别（1：男2：女）',
  `avatar` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '学生头像地址',
  `school` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '学生就读所在学校',
  `grade_id` bigint(20) NULL DEFAULT NULL COMMENT '关联年级Id',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '关联家长Id',
  `create_by_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人Id',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '学生表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of yun_student
-- ----------------------------
INSERT INTO `yun_student` VALUES (1, '小乌马河', '0', NULL, 'sda', 12, NULL, 1, 'admin', '2019-12-28 17:11:53', '', NULL, '0');

SET FOREIGN_KEY_CHECKS = 1;
