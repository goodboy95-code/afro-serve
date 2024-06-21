create table sys_role
(
    role_id     smallint unsigned auto_increment comment '角色ID'
        primary key,
    role_name   varchar(30)             null comment '角色名称',
    role_sort   int                     null comment '显示顺序',
    status      char                    null comment '角色状态（0 正常，1 停用）',
    del_flag    char        default '0' null comment '删除标志（0 代表存在，1 代表删除）',
    create_by   varchar(64) default ''  null comment '创建者',
    create_time datetime(3)             null comment '创建时间',
    update_by   varchar(64) default ''  null comment '更新者',
    update_time datetime(3)             null comment '更新时间',
    remark      varchar(500)            null comment '备注'
)
    comment '角色信息表';

INSERT INTO sys_role (role_id, role_name, role_sort, status, del_flag, create_by, create_time, update_by, update_time, remark) VALUES (1, '角色一', 100, '0', '0', 'Kong Jiehong', '2005-06-30 06:28:43.000', 'Kong Jiehong', '2023-12-22 21:15:38.000', 'hGm2Ubu9j');
INSERT INTO sys_role (role_id, role_name, role_sort, status, del_flag, create_by, create_time, update_by, update_time, remark) VALUES (2, '角色二', 200, '0', '0', 'Jiang Anqi', '2005-12-13 00:47:11.000', 'Jiang Anqi', '2011-09-03 03:47:33.000', 'sfeQG5bAqb');
INSERT INTO sys_role (role_id, role_name, role_sort, status, del_flag, create_by, create_time, update_by, update_time, remark) VALUES (3, '角色三', 300, '0', '0', 'He Ziyi', '2015-03-15 14:10:18.000', 'He Ziyi', '2004-12-03 19:36:23.000', 'gnT5Zw1gi6');
INSERT INTO sys_role (role_id, role_name, role_sort, status, del_flag, create_by, create_time, update_by, update_time, remark) VALUES (4, '角色四', 400, '0', '0', 'Jesse Edwards', '2001-09-08 12:06:20.000', 'Jesse Edwards', '2017-03-30 18:45:08.000', '2xgoGqrBSS');
