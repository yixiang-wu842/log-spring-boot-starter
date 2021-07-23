package com.xiaobai.log.db.factory.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.xiaobai.log.db.DbSourceProperties;
import com.xiaobai.log.db.factory.DB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author 萧白
 * @title: MysqlDataSource
 * @projectName log-spring-boot-starter
 * @description: mysql
 * @date 2021/7/2211:17
 */
public class MysqlDataSource implements DB {

    private  Logger logger = LoggerFactory.getLogger(this.getClass());

    private Connection connection = null;

    public MysqlDataSource(DbSourceProperties dbSourceProperties){
        getConnection(dbSourceProperties);
    }

    private Connection getConnection(DbSourceProperties dbSourceProperties) {
        try {
            DataSource dataSource = DruidDataSourceFactory.createDataSource(BeanUtil.beanToMap(dbSourceProperties));
            connection = dataSource.getConnection();
            logger.info("MSQL init success...");
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void executeSql(String sql) {
        try {
            connection.prepareStatement(sql).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
