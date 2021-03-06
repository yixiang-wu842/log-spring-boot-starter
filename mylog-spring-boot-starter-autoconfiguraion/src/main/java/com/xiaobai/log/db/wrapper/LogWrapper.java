package com.xiaobai.log.db.wrapper;

import com.xiaobai.log.DO.Log;

import java.util.List;

/**
 * @author 萧白
 * @title: LogWrapper
 * @projectName log-spring-boot-starter
 * @description:
 * @date 2021/8/211:02
 */
public interface LogWrapper {

    /**
     * 查询
     * @param log
     * @return
     */
    List<Log> query(Log log);

    /**
     * 添加
     * @return
     */
    boolean insert(Log log);

    /**
     * 更新
     * @return
     */
    boolean update(Log log);

    /**
     * 删除
     */
    boolean delete(int id);
}
