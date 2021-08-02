package com.xiaobai.log.db.factory.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.xiaobai.log.DO.Log;
import com.xiaobai.log.constant.SqlConstant;
import com.xiaobai.log.db.DbSourceProperties;
import com.xiaobai.log.db.factory.DB;
import com.xiaobai.log.enums.SqlTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 萧白
 * @title: MysqlDataSource
 * @projectName log-spring-boot-starter
 * @description: mysql
 * @date 2021/7/2211:17
 */
public class MysqlDataSource extends DB {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public MysqlDataSource(DbSourceProperties dbSourceProperties) {
        getConnection(dbSourceProperties);
    }

    @Override
    public Object getSqlResult(String type, Log log) throws SQLException {
        StringBuilder sql = new StringBuilder();
        SqlTypeEnum sqlTypeEnum = SqlTypeEnum.valueOf(type);
        switch (sqlTypeEnum) {
            case UPDATE:
                int i = connection.prepareStatement(sql.toString()).executeUpdate();
                return i == 1;
            case QUERY:
                ResultSet resultSet = connection.prepareStatement(sql.toString()).executeQuery();
                break;
            case INSERT:
                String log_insert = SqlConstant.MYSQL_LOG_INSERT(log);
                logger.debug("execute sql :"+log_insert);
                return connection.prepareStatement(log_insert).execute();
            case DELETE:
                connection.prepareStatement(sql.toString()).execute();
                break;
            default:
                return false;
        }
        return false;
    }
}
