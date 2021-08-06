package com.xiaobai.log.db.factory;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.xiaobai.log.DO.Log;
import com.xiaobai.log.constant.SqlConstant;
import com.xiaobai.log.db.DbSourceProperties;
import com.xiaobai.log.enums.SqlTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public DB(DbSourceProperties dbSourceProperties){
        getConnection(dbSourceProperties);
    }

    protected Connection getConnection(DbSourceProperties dbSourceProperties) {
        try {
            DataSource dataSource = DruidDataSourceFactory.createDataSource(BeanUtil.beanToMap(dbSourceProperties));
            connection = dataSource.getConnection();
            logger.info("=============================druid connection init success==================");
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
                logger.info("========================druid connection closed=============================");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    private Object getSqlResult(String type, Log log) throws SQLException {
        SqlTypeEnum sqlTypeEnum = SqlTypeEnum.valueOf(type);
        switch (sqlTypeEnum) {
            case UPDATE:
                int i = connection.prepareStatement(null).executeUpdate();
                return i == 1;
            case QUERY:
                ResultSet resultSet = connection.prepareStatement(query()).executeQuery();
                return convertList(resultSet);
            case INSERT:
                String log_insert = insert(log);
                logger.debug("execute sql :" + log_insert);
                return connection.prepareStatement(log_insert).execute();
            case DELETE:
                return connection.prepareStatement(SqlConstant.MYSQL_LOG_DELETE(log.getId())).execute();
            default:
                break;
        }
        return false;
    }

    private List<Log> convertList(ResultSet resultSet) throws SQLException {
        List<Log> list = new ArrayList<>();
        while (resultSet.next()){
            Log log = new Log();
            log.setMethodName(resultSet.getString("method_name"));
            log.setRequestParams(resultSet.getString("request_params"));
            log.setResponse(resultSet.getString("response"));
            log.setResponseError(resultSet.getString("response_error"));
            log.setStartTime(resultSet.getLong("start_time"));
            log.setEndTime(resultSet.getLong("end_time"));
            log.setTimes(resultSet.getLong("times"));
            log.setRequestIp(resultSet.getString("request_ip"));
            log.setCreateBy(resultSet.getString("create_by"));
            log.setCreateTime(resultSet.getLong("create_time"));
            list.add(log);
        }
        return list;
    }


    /**
     * 拼接sql,执行sql
     * query
     * 留给其他实现类去实现，重写不同的sql
     * @throws SQLException
     * @return Object
     */
    abstract protected String query();

    /**
     * insert sql
     * @param log
     * @return
     */
    abstract protected String insert(Log log);
}
