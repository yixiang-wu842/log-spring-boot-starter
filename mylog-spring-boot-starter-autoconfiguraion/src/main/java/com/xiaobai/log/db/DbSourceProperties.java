package com.xiaobai.log.db;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 萧白
 * @title: DbSourceProperties
 * @projectName log-spring-boot-starter
 * @description:
 * @date 2021/7/2210:39
 */
@ConfigurationProperties(prefix = "spring.datasource")
@Component
public class DbSourceProperties {
    /**
     * com.mysql.jdbc.Driver
     */
    private String driverClassName;

    /**
     * jdbc:mysql://db-com:3306/jshp_distrib?useUnicode=true&characterEncoding=utf8&autoReconnect=true
     */
    private String url;
    private String username;
    private String password;
    /**
     * 自定义字段：MYSQL、DB2
     */
    private String dbType;

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
