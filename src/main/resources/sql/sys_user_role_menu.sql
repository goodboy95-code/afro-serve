create table sys_user_role_menu
(
    user_id bigint unsigned   not null comment '用户ID',
    role_id smallint unsigned not null comment '角色ID',
    menu_id smallint unsigned not null comment '菜单ID'
)
    comment '用户、角色和快捷导航（菜单）关联表';

INSERT INTO sys_user_role_menu (user_id, role_id, menu_id) VALUES (1, 4, 1);
INSERT INTO sys_user_role_menu (user_id, role_id, menu_id) VALUES (1, 4, 2);
