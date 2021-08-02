package com.xiaobai.log.db.factory;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.xiaobai.log.DO.Log;
import com.xiaobai.log.db.DbSourceProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author 萧白
 * @title: DataSource
 * @projectName log-spring-boot-starter
 * @description: db interface
 * @date 2021/7/2211:07
 */
public abstract class DB {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    protected Connection connection = null;

    protected Connection getConnection(DbSourceProperties dbSourceProperties) {
        try {
            DataSource dataSource = DruidDataSourceFactory.createDataSource(BeanUtil.beanToMap(dbSourceProperties));
            connection = dataSource.getConnection();
            logger.info("=============================MSQL init success==================");
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object executeSql(String type, Log log) {
        try {
            return getSqlResult(type, log);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                logger.info("========================connection closed=============================");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 拼接sql,执行sql。
     * 留给其他实现类去实现，重写不同的sql
     * @param type
     * @param log
     */
    abstract public Object getSqlResult(String type , Log log) throws SQLException;
}
