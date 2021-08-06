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

    public MysqlDataSource(DbSourceProperties dbSourceProperties) {
       super(dbSourceProperties);
    }

    @Override
    protected String query() {
        return SqlConstant.MYSQL_LOG_QUERY();
    }

    @Override
    protected String insert(Log log) {
        return SqlConstant.MYSQL_LOG_INSERT(log);
    }
}
