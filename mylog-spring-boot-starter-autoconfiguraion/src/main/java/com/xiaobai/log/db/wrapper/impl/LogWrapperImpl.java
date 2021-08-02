package com.xiaobai.log.db.wrapper.impl;

import com.xiaobai.log.DO.Log;
import com.xiaobai.log.db.factory.DB;
import com.xiaobai.log.db.factory.DataSourceFactory;
import com.xiaobai.log.db.wrapper.LogWrapper;
import com.xiaobai.log.enums.SqlTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 萧白
 * @title: LogWrapperImpl
 * @projectName log-spring-boot-starter
 * @description:
 * @date 2021/8/211:04
 */
@Service
public class LogWrapperImpl implements LogWrapper {

    @Autowired
    private DataSourceFactory dataSourceFactory;

    @Override
    public boolean insert(Log log) {
        DB dataSource = dataSourceFactory.getDataSource();
        return (boolean)dataSource.executeSql(SqlTypeEnum.INSERT.name(),log);
    }

    @Override
    public boolean update(Log log) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
