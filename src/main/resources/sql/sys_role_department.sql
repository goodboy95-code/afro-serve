create table sys_role_department
(
    role_id       smallint unsigned not null comment '角色ID',
    department_id smallint unsigned not null comment '部门ID'
)
    comment '角色和部门关联表';

INSERT INTO sys_role_department (role_id, department_id) VALUES (1, 105);
INSERT INTO sys_role_department (role_id, department_id) VALUES (2, 105);
INSERT INTO sys_role_department (role_id, department_id) VALUES (2, 106);
INSERT INTO sys_role_department (role_id, department_id) VALUES (2, 108);
INSERT INTO sys_role_department (role_id, department_id) VALUES (3, 109);
INSERT INTO sys_role_department (role_id, department_id) VALUES (4, 110);
INSERT INTO sys_role_department (role_id, department_id) VALUES (4, 111);
INSERT INTO sys_role_department (role_id, department_id) VALUES (3, 112);
