create table sys_operate_log
(
    operate_log_id   bigint unsigned auto_increment comment '日志主键'
        primary key,
    title            varchar(50)   default ''  null comment '系统模块',
    operate_type     char          default '0' null comment '操作类型（0其它 1新增 2修改 3删除）',
    method_name      varchar(100)  default ''  null comment '方法名称',
    request_method   varchar(10)   default ''  null comment '请求方式',
    operator_type    char          default '0' null comment '操作类别（0其它 1后台用户 2手机端用户）',
    operator         varchar(50)   default ''  null comment '操作人员',
    request_url      varchar(255)  default ''  null comment '请求URL',
    ip_address       varchar(128)  default ''  null comment '主机地址',
    operate_location varchar(255)  default ''  null comment '操作地点',
    request_param    varchar(2000) default ''  null comment '请求参数',
    json_result      varchar(2000) default ''  null comment '返回参数',
    status           char          default '0' null comment '操作状态（0正常 1异常）',
    error_msg        varchar(2000) default ''  null comment '错误消息',
    operate_time     datetime                  null comment '操作时间',
    cost_time        bigint                    null comment '花费时间'
)
    comment '操作日志记录表';
