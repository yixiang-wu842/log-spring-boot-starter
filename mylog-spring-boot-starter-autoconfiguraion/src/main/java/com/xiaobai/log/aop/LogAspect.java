package com.xiaobai.log.aop;

import ch.qos.logback.core.db.dialect.HSQLDBDialect;
import com.alibaba.fastjson.JSON;
import com.xiaobai.log.DO.Log;
import com.xiaobai.log.db.factory.DB;
import com.xiaobai.log.db.factory.DataSourceFactory;
import com.xiaobai.log.db.wrapper.LogWrapper;
import com.xiaobai.log.util.IpUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author 萧白
 * @title: LogAspect
 * @projectName request-log-spring-boot-starter
 * @description: 日志切面
 * @date 2021/7/1613:12
 */
@Aspect
@Component
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(LogAspect.class);

    /**
     * 设置哪些注解需要被记录日志，一般就是请求过来时记录
     */
    private final Class[] needSaveLogAnntation = {Controller.class, RestController.class};

    @Autowired
    private LogWrapper logWrapper;

    /**
     * 存放请求开始时间
     */
    private static ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(* com.xiaobai..*.*(..))")
    public void pointcut() {
    }

    @Before(value = "pointcut()")
    public void before() {
        startTime.set(System.currentTimeMillis()/1000);
    }

    @AfterReturning(pointcut = "pointcut()", returning = "jsonResult")
    public void afterReturning(JoinPoint joinPoint, Object jsonResult) {
        handleLog(joinPoint, null, jsonResult);
    }

    @AfterThrowing(pointcut = "pointcut()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        handleLog(joinPoint, e, null);
    }

    /**
     * 处理日志
     *
     * @param joinPoint 切点参数
     * @param e         异常
     * @param result    返回结果
     */
    private void handleLog(JoinPoint joinPoint, Exception e, Object result) {
        if (HasControllerAnnotation(joinPoint)) {
            Log log = new Log();
            HttpServletRequest request = getRequest();
            log.setMethodName(request.getRequestURI());
            log.setRequestParams(JSON.toJSONString(joinPoint.getArgs()));
            log.setResponse(JSON.toJSONString(result));
            log.setResponseError(e != null ? e.getMessage() : "");
            log.setRequestIp(IpUtils.getIpAddr(request));
            long endTime = System.currentTimeMillis()/1000;
            Long starTime = startTime.get();
            log.setStartTime(starTime);
            log.setEndTime(endTime);
            log.setTimes(endTime - starTime);
            log.setCreateTime(endTime);
            logger.info("请求日志为：" + log.toString());
            //todo save DB 异步
            logWrapper.insert(log);
        }
    }

    private HttpServletRequest getRequest() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        return request;
    }

    /**
     * 判断方法是否含有controller注解
     *
     * @param joinPoint
     * @return
     */
    private boolean HasControllerAnnotation(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Class declaringType = methodSignature.getDeclaringType();
        for (int i = 0; i < needSaveLogAnntation.length; i++) {
            Annotation annotation = declaringType.getAnnotation(needSaveLogAnntation[i]);
            if(annotation!= null){
                return true;
            }
        }
        return false;
    }
}
