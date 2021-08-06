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

    private static final String TABLE_NAME = "log";

    private static final String TABLE_FIELD = "method_name," +
            "request_params," +
            "response," +
            "response_error," +
            "start_time," +
            "end_time," +
            "times," +
            "request_ip," +
            "create_by," +
            "create_time";

    /**
     * mysql insert log
     *
     * @param log
     * @return
     */
    public static String MYSQL_LOG_INSERT(Log log) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into "+TABLE_NAME+" ("+TABLE_FIELD+") values ('" +
                log.getMethodName() + "','" +
                log.getRequestParams() + "','" +
                log.getResponse() + "','" +
                log.getResponseError() + "'," +
                log.getStartTime() + "," +
                log.getEndTime() + "," +
                log.getTimes() + ",'" +
                log.getRequestIp() + "','" +
                log.getCreateBy() + "'," +
                log.getCreateTime() + ")");
        return sql.toString();
    }

    /**
     * mysql query log
     * @return
     */
    public static String MYSQL_LOG_QUERY (){
        StringBuilder sql = new StringBuilder();
        sql.append("select "+TABLE_FIELD+" from "+ TABLE_NAME);
        return sql.toString();
    }

    /**
     * mysql delete log
     * @param id
     * @return
     */
    public static String MYSQL_LOG_DELETE(int id){
        StringBuilder sql = new StringBuilder();
        sql.append("delete from "+ TABLE_NAME);
        return sql.toString();
    }
}

