package com.xiaobai.log.db.factory;

import com.xiaobai.log.db.DbSourceProperties;
import com.xiaobai.log.db.factory.impl.MysqlDataSource;
import com.xiaobai.log.enums.DbtypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @author 萧白
 * @title: DataSourceFactory
 * @projectName log-spring-boot-starter
 * @description: 获取数据库实例
 * @date 2021/7/2213:36
 */
@Component
public class DataSourceFactory {

    @Autowired
    private DbSourceProperties properties;

    /**
     * 获取DB 实例
     *
     * @return
     */
    public DB getDataSource() {
        String dbType = properties.getDbType();
        DB db = null;
        if (DbtypeEnum.MYSQL.name().equals(dbType)) {
           return new MysqlDataSource(properties);
        } else if (DbtypeEnum.ORACLE.name().equals(dbType)) {
            //sql 语句一致，所以不用单独写一份
            return new MysqlDataSource(properties);
        }
        return null;
    }
}
