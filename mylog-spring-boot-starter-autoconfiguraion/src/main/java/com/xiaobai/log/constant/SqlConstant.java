package com.xiaobai.log.constant;

import com.xiaobai.log.DO.Log;

/**
 * @author 萧白
 * @title: SqlConstant
 * @projectName log-spring-boot-starter
 * @description: sql 解释器
 * @date 2021/8/214:38
 */
public class SqlConstant {

    /**
     * mysql insert log
     * @param log
     * @return
     */
    public static String MYSQL_LOG_INSERT(Log log){
        StringBuilder sql = new StringBuilder();
        sql.append("insert into log (method_name," +
                "request_params," +
                "response," +
                "response_error," +
                "start_time," +
                "end_time," +
                "times," +
                "request_ip," +
                "create_by," +
                "create_time) values ('"+
                log.getMethodName()+"','"+
                log.getRequestParams()+"','"+
                log.getResponse()+"','"+
                log.getResponseError()+"',"+
                log.getStartTime()+","+
                log.getEndTime()+","+
                log.getTimes()+",'"+
                log.getRequestIp()+"','"+
                log.getCreateBy()+"',"+
                log.getCreateTime()+")");
        return sql.toString();
    }
}
