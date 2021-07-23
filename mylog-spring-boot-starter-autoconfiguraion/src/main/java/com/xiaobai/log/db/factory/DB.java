package com.xiaobai.log.db.factory;

import java.sql.Connection;

/**
 * @author 萧白
 * @title: DataSource
 * @projectName log-spring-boot-starter
 * @description: db interface
 * @date 2021/7/2211:07
 */
public interface DB {

    /**
     * 执行sql
     * @param sql
     */
    void executeSql(String sql);
}
