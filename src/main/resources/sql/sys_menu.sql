create table sys_menu
(
    menu_id     smallint unsigned auto_increment comment '菜单ID'
        primary key,
    menu_name   varchar(50)                   null comment '菜单名称',
    parent_id   smallint unsigned default '0' null comment '父菜单ID',
    order_num   int unsigned      default '0' null comment '显示顺序',
    path        varchar(200)      default ''  null comment '路由地址',
    component   varchar(255)                  null comment '组件路径',
    query       varchar(255)                  null comment '路由参数',
    is_frame    char              default '1' null comment '是否为外链（0 是，1 否）',
    is_cache    char              default '0' null comment '是否缓存（0 缓存，1 不缓存）',
    menu_type   char              default ''  null comment '菜单类型（D 目录，M 菜单， B 按钮）',
    visible     char              default '0' null comment '菜单显示状态（0 显示，1 隐藏）',
    status      char              default '0' null comment '菜单使用状态（0 正常，1 停用）',
    perms       varchar(100)                  null comment '权限标识',
    icon        varchar(100)      default '#' null comment '菜单图标',
    create_by   varchar(64)       default ''  null comment '创建者',
    create_time datetime(3)                   null comment '创建时间',
    update_by   varchar(64)       default ''  null comment '更新者',
    update_time datetime(3)                   null comment '更新时间',
    remark      varchar(500)      default ''  null comment '备注'
)
    comment '菜单权限表';

INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1, '系统管理', 0, 1, 'system', '', '', '1', '0', 'D', '0', '0', '', 'icon-system', 'Kimura Nanami', '2015-09-26 15:32:37.000', 'Kimura Nanami', '2012-01-08 15:33:14.000', '系统管理');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2, '用户管理', 1, 1, 'user', 'system/User', '', '1', '0', 'M', '0', '0', 'system:user:list', 'icon-people', 'Zhou Yunxi', '2006-11-28 06:27:20.000', 'Zhou Yunxi', '2014-12-04 01:33:58.000', '用户管理');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (3, '角色管理', 1, 2, 'role', 'system/Role', '', '1', '0', 'M', '0', '0', 'system:role:list', 'icon-avatar', 'Chan Hiu Tung', '2002-11-06 00:54:52.000', 'Chan Hiu Tung', '2004-06-24 20:21:27.000', '角色管理');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (4, '菜单管理', 1, 3, 'menu', 'system/Menu', '', '1', '0', 'M', '0', '0', 'system:menu:list', 'icon-application-menu', 'Tian Shihan', '2017-01-10 06:21:31.000', 'Tian Shihan', '2021-01-25 03:34:06.000', '菜单管理');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (5, '部门管理', 1, 4, 'department', 'system/Department', '', '1', '0', 'M', '0', '0', 'system:department:list', 'icon-peoples', 'Sara Chen', '2002-01-05 21:07:57.000', 'Sara Chen', '2005-08-04 00:36:40.000', '部门管理');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (6, '字典管理', 1, 5, 'dictionary', 'system/Dctionary', '', '1', '0', 'M', '1', '0', 'system:dictionary:list', 'icon-book', 'Amber Wallace', '2010-10-15 15:02:21.000', 'Amber Wallace', '2006-03-17 07:36:19.000', '字典管理');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (7, '系统监控', 1, 6, 'monitor', '', '', '1', '0', 'D', '1', '0', '', 'icon-monitor-camera', 'Virginia Walker', '2007-01-23 01:40:19.000', 'Virginia Walker', '2022-08-05 08:47:21.000', '系统监控');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (8, '参数设置', 1, 7, 'config', 'system/Config', '', '1', '0', 'M', '1', '0', 'system:config:list', 'icon-file-settings-one', 'Fu Yuning', '2010-08-08 16:54:47.000', 'Fu Yuning', '2021-07-07 23:45:22.000', '参数设置');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (9, '通知公告', 1, 8, 'notice', 'system/Notice', '', '1', '0', 'M', '0', '0', 'system:notice:list', 'icon-message-one', 'Kwan Tsz Hin', '2000-07-04 12:05:25.000', 'Kwan Tsz Hin', '2022-04-29 15:03:39.000', '通知公告');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (10, '日志管理', 1, 9, 'log', '', '', '1', '0', 'D', '0', '0', '', 'icon-notes', 'Yue Kwok Kuen', '2011-08-11 11:35:30.000', 'Yue Kwok Kuen', '2017-02-24 08:50:12.000', '日志管理');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (11, '操作日志', 10, 1, 'operateLog', 'system/OperateLog', '', '1', '0', 'M', '0', '0', 'system:operateLog:list', 'icon-upload-logs', 'Cindy Black', '2002-09-26 07:56:15.000', 'Cindy Black', '2022-09-26 03:06:46.000', '操作日志');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (12, '登录日志', 10, 2, 'loginLog', 'system/LoginLog', '', '1', '0', 'M', '0', '0', 'system:loginLog:list', 'icon-log', 'Arimura Aoshi', '2016-09-24 13:27:42.000', 'Arimura Aoshi', '2016-11-24 06:22:09.000', '登录日志');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (100, '用户查询', 2, 1, '', null, null, '1', '0', 'B', '0', '0', 'system:user:query', '#', 'Arimura Aoshi', '2015-09-26 15:32:37.000', 'Arimura Aoshi', '2015-09-26 15:32:37.000', '查询用户');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (101, '用户新增', 2, 2, '', null, null, '1', '0', 'B', '0', '0', 'system:user:add', '#', 'Arimura Aoshi', '2015-09-26 15:32:37.000', 'Arimura Aoshi', '2015-09-26 15:32:37.000', '添加用户');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (102, '用户修改', 2, 3, '', null, null, '1', '0', 'B', '0', '0', 'system:user:edit', '#', 'Arimura Aoshi', '2015-09-26 15:32:37.000', 'Arimura Aoshi', '2015-09-26 15:32:37.000', '修改用户');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (103, '用户删除', 2, 4, '', null, null, '1', '0', 'B', '0', '0', 'system:user:remove', '#', 'Arimura Aoshi', '2015-09-26 15:32:37.000', 'Arimura Aoshi', '2015-09-26 15:32:37.000', '删除用户');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (104, '用户导出', 2, 5, '', null, null, '1', '0', 'B', '0', '0', 'system:user:export', '#', 'Arimura Aoshi', '2015-09-26 15:32:37.000', 'Arimura Aoshi', '2015-09-26 15:32:37.000', '导出用户');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (105, '用户导入', 2, 6, '', null, null, '1', '0', 'B', '0', '0', 'system:user:import', '#', 'Arimura Aoshi', '2015-09-26 15:32:37.000', 'Arimura Aoshi', '2015-09-26 15:32:37.000', '导入用户');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (106, '重置密码', 2, 7, '', null, null, '1', '0', 'B', '0', '0', 'system:user:resetPwd', '#', 'Arimura Aoshi', '2015-09-26 15:32:37.000', 'Arimura Aoshi', '2015-09-26 15:32:37.000', '重置密码');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (110, '角色查询', 3, 1, '', null, null, '1', '0', 'B', '0', '0', 'system:role:query', '#', 'Arimura Aoshi', '2015-09-26 15:32:37.000', 'Arimura Aoshi', '2015-09-26 15:32:37.000', '查询角色');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (111, '角色新增', 3, 2, '', null, null, '1', '0', 'B', '0', '0', 'system:role:add', '#', 'Arimura Aoshi', '2015-09-26 15:32:37.000', 'Arimura Aoshi', '2015-09-26 15:32:37.000', '添加角色');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (112, '角色修改', 3, 3, '', null, null, '1', '0', 'B', '0', '0', 'system:role:edit', '#', 'Arimura Aoshi', '2015-09-26 15:32:37.000', 'Arimura Aoshi', '2015-09-26 15:32:37.000', '修改角色');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (113, '角色删除', 3, 4, '', null, null, '1', '0', 'B', '0', '0', 'system:role:remove', '#', 'Arimura Aoshi', '2015-09-26 15:32:37.000', 'Arimura Aoshi', '2015-09-26 15:32:37.000', '删除角色');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (114, '角色导出', 3, 5, '', null, null, '1', '0', 'B', '0', '0', 'system:role:export', '#', 'Arimura Aoshi', '2015-09-26 15:32:37.000', 'Arimura Aoshi', '2015-09-26 15:32:37.000', '导出角色');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (120, '菜单查询', 4, 1, '', null, null, '1', '0', 'B', '0', '0', 'system:menu:query', '#', 'Arimura Aoshi', '2015-09-26 15:32:37.000', 'Arimura Aoshi', '2015-09-26 15:32:37.000', '查询菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (121, '菜单新增', 4, 2, '', null, null, '1', '0', 'B', '0', '0', 'system:menu:add', '#', 'Arimura Aoshi', '2015-09-26 15:32:37.000', 'Arimura Aoshi', '2015-09-26 15:32:37.000', '添加菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (122, '菜单修改', 4, 3, '', null, null, '1', '0', 'B', '0', '0', 'system:menu:edit', '#', 'Arimura Aoshi', '2015-09-26 15:32:37.000', 'Arimura Aoshi', '2015-09-26 15:32:37.000', '修改菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (123, '菜单删除', 4, 4, '', null, null, '1', '0', 'B', '0', '0', 'system:menu:remove', '#', 'Arimura Aoshi', '2015-09-26 15:32:37.000', 'Arimura Aoshi', '2015-09-26 15:32:37.000', '删除菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (131, '部门查询', 5, 1, '', null, null, '1', '0', 'B', '0', '0', 'system:department:query', '#', 'Arimura Aoshi', '2015-09-26 15:32:37.000', 'Arimura Aoshi', '2015-09-26 15:32:37.000', '查询部门');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (132, '部门新增', 5, 2, '', null, null, '1', '0', 'B', '0', '0', 'system:department:add', '#', 'Arimura Aoshi', '2015-09-26 15:32:37.000', 'Arimura Aoshi', '2015-09-26 15:32:37.000', '添加部门');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (133, '部门修改', 5, 3, '', null, null, '1', '0', 'B', '0', '0', 'system:department:edit', '#', 'Arimura Aoshi', '2015-09-26 15:32:37.000', 'Arimura Aoshi', '2015-09-26 15:32:37.000', '修改部门');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (134, '部门删除', 5, 4, '', null, null, '1', '0', 'B', '0', '0', 'system:department:remove', '#', 'Arimura Aoshi', '2015-09-26 15:32:37.000', 'Arimura Aoshi', '2015-09-26 15:32:37.000', '删除部门');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (139, 'demo', 2, 1, 'demo', 'demo', null, '0', null, 'B', '0', '0', 'system', 'demo', null, null, null, null, 'test');