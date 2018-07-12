package com.example.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>日志实体类</p>
 * Created by zhezhiyong@163.com on 2017/4/26.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LogResult {

    @JSONField(format = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date request_time = new Date();
    private String project;
    private String model;
    private String protocol;
    private String request_url;
    private Map<String, String> more_info = new HashMap<>();
    private Map<String, String> header;
    private String device;
    private String parameter;
    private Object response;
    private float response_time;
    private String label = "km";
    private int status;
    private String client_ip;

    public LogResult(String sign, String more_info) {
        this.more_info.put(sign, more_info);
    }

    public LogResult(String label, String sign, String more_info) {
        this.more_info.put(sign, more_info);
        this.label = label;
    }

    public void setMoreInfo(String sign, String more_info) {
        this.more_info.put(sign, more_info);
    }

    public void setParameter(String parameter) {
        //  todo: sendAdSms重复
        if (StringUtils.isEmpty(this.parameter)) {
            this.parameter = parameter;
        } else {
            this.parameter += parameter;
        }
    }

    private void calculate() {
        Date end = new Date();
        if (null == request_time) return;
        this.response_time = (end.getTime() - request_time.getTime()) / 1000f;
    }

    @Override
    public String toString() {
        calculate();
        return JSON.toJSONString(this);
    }
}
