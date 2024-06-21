create table sys_login_log
(
    login_log_id   bigint auto_increment comment '访问ID'
        primary key,
    user_name      varchar(50)  default ''  null comment '用户账号',
    ip_address     varchar(128) default ''  null comment '登录IP地址',
    login_location varchar(255) default ''  null comment '登录地点',
    browser        varchar(50)  default ''  null comment '浏览器类型',
    os             varchar(50)  default ''  null comment '操作系统',
    status         char         default '0' null comment '登录状态（0成功 1失败）',
    msg            varchar(255) default ''  null comment '提示消息',
    login_time     datetime                 null comment '访问时间'
)
    comment '系统访问记录';

create index idx_sys_loginlog_lt
    on sys_login_log (login_time);

create index idx_sys_loginlog_s
    on sys_login_log (status);

INSERT INTO sys_login_log (login_log_id, user_name, ip_address, login_location, browser, os, status, msg, login_time) VALUES (1, '胡诗涵', '36.33.37.99', '北京', 'Chrome', 'OSX', '1', '验证码不匹配', '2024-05-04 20:40:55');
INSERT INTO sys_login_log (login_log_id, user_name, ip_address, login_location, browser, os, status, msg, login_time) VALUES (2, '胡诗涵', '36.33.37.99', '北京', 'Chrome', 'OSX', '1', '验证码不匹配', '2024-05-04 20:41:05');
INSERT INTO sys_login_log (login_log_id, user_name, ip_address, login_location, browser, os, status, msg, login_time) VALUES (3, '胡诗涵', '36.33.37.99', '北京', 'Chrome', 'OSX', '0', '登录成功', '2024-05-04 20:41:12');
INSERT INTO sys_login_log (login_log_id, user_name, ip_address, login_location, browser, os, status, msg, login_time) VALUES (4, '胡诗涵', '36.33.37.26', '北京', 'Chrome', 'OSX', '0', '登录成功', '2024-05-18 17:13:24');
INSERT INTO sys_login_log (login_log_id, user_name, ip_address, login_location, browser, os, status, msg, login_time) VALUES (5, '胡诗涵', '36.33.37.26', '北京', 'Safari', 'iPhone', '0', '登录成功', '2024-05-18 17:20:38');
INSERT INTO sys_login_log (login_log_id, user_name, ip_address, login_location, browser, os, status, msg, login_time) VALUES (6, '胡诗涵', '127.0.0.1', '北京', 'Chrome', 'OSX', '1', '验证码不匹配', '2024-05-18 19:10:25');
INSERT INTO sys_login_log (login_log_id, user_name, ip_address, login_location, browser, os, status, msg, login_time) VALUES (7, '胡诗涵', '127.0.0.1', '北京', 'Chrome', 'OSX', '1', '验证码不匹配', '2024-05-18 19:10:35');
INSERT INTO sys_login_log (login_log_id, user_name, ip_address, login_location, browser, os, status, msg, login_time) VALUES (8, '胡诗涵', '127.0.0.1', '北京', 'Chrome', 'OSX', '0', '登录成功', '2024-05-18 19:10:45');
INSERT INTO sys_login_log (login_log_id, user_name, ip_address, login_location, browser, os, status, msg, login_time) VALUES (9, '胡诗涵', '127.0.0.1', '北京', 'Chrome', 'OSX', '0', '登录成功', '2024-05-18 19:11:35');
INSERT INTO sys_login_log (login_log_id, user_name, ip_address, login_location, browser, os, status, msg, login_time) VALUES (10, '胡诗涵', '127.0.0.1', '北京', 'Chrome', 'OSX', '0', '登录成功', '2024-05-18 19:21:36');
INSERT INTO sys_login_log (login_log_id, user_name, ip_address, login_location, browser, os, status, msg, login_time) VALUES (11, '胡诗涵', '127.0.0.1', '北京', 'Chrome', 'OSX', '0', '登录成功', '2024-05-18 19:23:02');
INSERT INTO sys_login_log (login_log_id, user_name, ip_address, login_location, browser, os, status, msg, login_time) VALUES (12, '胡诗涵', '58.243.42.93', '北京', 'MSEdge', 'OSX', '0', '登录成功', '2024-06-05 13:02:14');
INSERT INTO sys_login_log (login_log_id, user_name, ip_address, login_location, browser, os, status, msg, login_time) VALUES (13, '胡诗涵', '58.243.43.227', '北京', 'MSEdge', 'OSX', '0', '登录成功', '2024-06-12 17:25:57');
