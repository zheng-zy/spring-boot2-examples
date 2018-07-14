package com.example.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 * <p>日志异常处理</p>
 * Created by zhezhiyong@163.com on 2017/4/26.
 */
@Aspect
@Component
@Order(10) // 值越小优先级越高
public class WebLogAspect {

    private static final Logger log = LoggerFactory.getLogger(WebLogAspect.class);

    @Value("${spring.application.name}")
    private String appName;

    @Pointcut("execution(public * com.example.controller..*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        LogResult logResult = new LogResult();
        logResult.setProject(appName);

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();

        // 接收到请求，记录请求url
        String url = request.getRequestURL().toString();
        Map<String, String> header = new HashMap<>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            header.put(key, value);
        }
        String qString = request.getQueryString();
        logResult.setMoreInfo("qString", qString);
        logResult.setHeader(header);
        logResult.setRequest_url(url);
        String methodName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logResult.setProtocol(methodName);
        logResult.setParameter(args);
        LogUtil.setLogResult(logResult);
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        LogResult result = LogUtil.getLogResult();
        result.setResponse(ret);
        try {
            JSONObject object = JSON.parseObject((String) ret);
            System.out.println("object = " + object.toJSONString());
//            int error = object.getIntValue("errorcode");
//            result.setStatus(error);
        } catch (Exception ignored) {
        }
        log.info(result.toString());
        LogUtil.removeLogResult();
    }

    @AfterThrowing(pointcut = "webLog()", throwing = "e")
    public void afterThrowing(Throwable e) {
        // 处理完请求，返回内容
        LogResult result = LogUtil.getLogResult();
        result.setResponse(String.format("exception: %s : %s", e.getClass().getName(), e.getMessage()));
        result.setStatus(-1);
        log.error(result.toString());
        LogUtil.removeLogResult();
    }
}
