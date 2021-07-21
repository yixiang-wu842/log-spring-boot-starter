package com.xiaobai.log.DO;

import java.io.Serializable;

/**
 * @author 萧白
 * @title: Log
 * @projectName request-log-spring-boot-starter
 * @description:
 * @date 2021/7/1615:26
 */
public class Log implements Serializable {
    /**
     * 请求参数
     */
    private String methodName;

    /**
     * 请求参数
     */
    private String requestParams;

    /**
     * 返回数据
     */
    private String response;

    private String responseError;

    /**
     * 开始时间
     */
    private Long startTime;

    /**
     * 结束时间
     */
    private Long endTime;

    /**
     * 耗时
     * @return
     */
    private long times;

    /**
     * 请求IP
     */
    private String requestIp;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(String requestParams) {
        this.requestParams = requestParams;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public long getTimes() {
        return times;
    }

    public void setTimes(long times) {
        this.times =  times;
    }

    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }

    public String getResponseError() {
        return responseError;
    }

    public void setResponseError(String responseError) {
        this.responseError = responseError;
    }

    @Override
    public String toString() {
        return "Log{" +
                "methodName='" + methodName + '\'' +
                ", requestParams='" + requestParams + '\'' +
                ", response='" + response + '\'' +
                ", responseError='" + responseError + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", times=" + times +
                ", requestIp='" + requestIp + '\'' +
                '}';
    }
}
