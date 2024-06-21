create table sys_department
(
    department_id   bigint unsigned auto_increment comment '部门ID'
        primary key,
    parent_id       bigint      default 0   null comment '父部门ID',
    ancestors       varchar(50) default ''  null comment '祖级列表',
    department_name varchar(30) default ''  null comment '部门名称',
    order_num       int         default 0   null comment '显示顺序',
    leader          varchar(20)             null comment '负责人',
    phone           varchar(11)             null comment '联系电话',
    email           varchar(50)             null comment '邮箱',
    status          char        default '0' null comment '部门状态（0 正常，1 停用）',
    del_flag        char        default '0' null comment '删除标志（0 代表存在，2 代表删除）',
    create_by       varchar(64) default ''  null comment '创建者',
    create_time     datetime(3)             null comment '创建时间',
    update_by       varchar(64) default ''  null comment '更新者',
    update_time     datetime(3)             null comment '更新时间',
    abbreviation    varchar(50)             not null comment '缩写'
)
    comment '部门表';

INSERT INTO sys_department (department_id, parent_id, ancestors, department_name, order_num, leader, phone, email, status, del_flag, create_by, create_time, update_by, update_time, abbreviation) VALUES (100, 0, '0', '爆炸小伙伴总公司', 1, '爆炸小伙伴', '15888888888', 'ry@qq.com', '0', '0', '小明', '2023-04-28 11:48:37.000', '小明', '2023-04-28 11:48:37.000', 'P');
INSERT INTO sys_department (department_id, parent_id, ancestors, department_name, order_num, leader, phone, email, status, del_flag, create_by, create_time, update_by, update_time, abbreviation) VALUES (101, 100, '0,100', '合肥分公司', 1, '爆炸小伙伴', '15888888888', 'ry@qq.com', '0', '0', '小明', '2023-04-28 11:48:37.000', '小明', '2023-04-28 11:48:37.000', 'HF');
INSERT INTO sys_department (department_id, parent_id, ancestors, department_name, order_num, leader, phone, email, status, del_flag, create_by, create_time, update_by, update_time, abbreviation) VALUES (102, 100, '0,100', '六安分公司', 2, '爆炸小伙伴', '15888888888', 'ry@qq.com', '0', '0', '小明', '2023-04-28 11:48:37.000', '小明', '2023-04-28 11:48:37.000', 'LA');
INSERT INTO sys_department (department_id, parent_id, ancestors, department_name, order_num, leader, phone, email, status, del_flag, create_by, create_time, update_by, update_time, abbreviation) VALUES (103, 100, '0,100', '黄山分公司', 3, '爆炸小伙伴', '15888888888', 'ry@qq.com', '0', '0', '小明', '2023-04-28 11:48:37.000', '小明', '2023-04-28 11:48:37.000', 'HS');
INSERT INTO sys_department (department_id, parent_id, ancestors, department_name, order_num, leader, phone, email, status, del_flag, create_by, create_time, update_by, update_time, abbreviation) VALUES (104, 100, '0,100', '安庆分公司', 4, '爆炸小伙伴', '15888888888', 'ry@qq.com', '0', '0', '小明', '2023-04-28 11:48:37.000', '小明', '2023-04-28 11:48:37.000', 'AQ');
INSERT INTO sys_department (department_id, parent_id, ancestors, department_name, order_num, leader, phone, email, status, del_flag, create_by, create_time, update_by, update_time, abbreviation) VALUES (105, 101, '0,100,101', '部门一', 1, '爆炸小伙伴', '15888888888', 'ry@qq.com', '0', '0', '小明', '2023-04-28 11:48:37.000', '小明', '2023-04-28 11:48:37.000', 'HF_DP1');
INSERT INTO sys_department (department_id, parent_id, ancestors, department_name, order_num, leader, phone, email, status, del_flag, create_by, create_time, update_by, update_time, abbreviation) VALUES (106, 101, '0,100,101', '部门二', 2, '爆炸小伙伴', '15888888888', 'ry@qq.com', '0', '0', '小明', '2023-04-28 11:48:37.000', '小明', '2023-04-28 11:48:37.000', 'HF_DP2');
INSERT INTO sys_department (department_id, parent_id, ancestors, department_name, order_num, leader, phone, email, status, del_flag, create_by, create_time, update_by, update_time, abbreviation) VALUES (107, 102, '0,100,102', '部门一', 1, '爆炸小伙伴', '15888888888', 'ry@qq.com', '0', '0', '小明', '2023-04-28 11:48:37.000', '小明', '2023-04-28 11:48:37.000', 'LA_DP1');
INSERT INTO sys_department (department_id, parent_id, ancestors, department_name, order_num, leader, phone, email, status, del_flag, create_by, create_time, update_by, update_time, abbreviation) VALUES (108, 102, '0,100,102', '部门二', 2, '爆炸小伙伴', '15888888888', 'ry@qq.com', '0', '0', '小明', '2023-04-28 11:48:37.000', '小明', '2023-04-28 11:48:37.000', 'LA_DP2');
INSERT INTO sys_department (department_id, parent_id, ancestors, department_name, order_num, leader, phone, email, status, del_flag, create_by, create_time, update_by, update_time, abbreviation) VALUES (109, 103, '0,100,103', '部门一', 1, '爆炸小伙伴', '15888888888', 'ry@qq.com', '0', '0', '小明', '2023-04-28 11:48:37.000', '小明', '2023-04-28 11:48:37.000', 'HS_DP1');
INSERT INTO sys_department (department_id, parent_id, ancestors, department_name, order_num, leader, phone, email, status, del_flag, create_by, create_time, update_by, update_time, abbreviation) VALUES (110, 103, '0,100,103', '部门二', 2, '爆炸小伙伴', '15888888888', 'ry@qq.com', '0', '0', '小明', '2023-04-28 11:48:37.000', '小明', '2023-04-28 11:48:37.000', 'HS_DP2');
INSERT INTO sys_department (department_id, parent_id, ancestors, department_name, order_num, leader, phone, email, status, del_flag, create_by, create_time, update_by, update_time, abbreviation) VALUES (111, 104, '0,100,104', '部门一', 1, '爆炸小伙伴', '15888888888', 'ry@qq.com', '0', '0', '小明', '2023-04-28 11:48:37.000', '小明', '2023-04-28 11:48:37.000', 'AQ_DP1');
INSERT INTO sys_department (department_id, parent_id, ancestors, department_name, order_num, leader, phone, email, status, del_flag, create_by, create_time, update_by, update_time, abbreviation) VALUES (112, 104, '0,100,104', '部门二', 2, '爆炸小伙伴', '15888888888', 'ry@qq.com', '0', '0', '小明', '2023-04-28 11:48:37.000', '小明', '2023-04-28 11:48:37.000', 'AQ_DP2');
