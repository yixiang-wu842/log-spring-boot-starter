create table log
(
  id             int auto_increment
    primary key,
  method_name    varchar(100) null
  comment '请求方法名',
  request_params text         null
  comment '请求参数',
  response       text         null
  comment '返回数据',
  response_error text         null
  comment '返回错误信息',
  start_time     int          null
  comment '请求开始时间',
  end_time       int          null
  comment '请求结束时间',
  times          int          null
  comment '请求总耗时',
  request_ip     varchar(100) null
  comment '请求IP',
  create_by      varchar(100) null,
  create_time    int          null
  comment '创建时间'
)
  comment '记录日志';

