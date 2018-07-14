package com.example.exception;

import com.example.config.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p></p>
 * Created by zhezhiyong@163.com on 2018/7/2.
 */
@ControllerAdvice
public class ServiceExceptionHandler {

    private Logger log = LoggerFactory.getLogger(ServiceExceptionHandler.class);

    private static final String ERROR = "error";

    @ExceptionHandler(value = ServiceException.class)
    public Object serviceExceptionHandler(HttpServletRequest reqest, HttpServletResponse response, ServiceException e) throws Exception {
        return Result.fail(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(value = Exception.class)
    public Object errorHandler(HttpServletRequest reqest, HttpServletResponse response, Exception e) throws Exception {
//        log.error("捕获到异常: ", e);
        log.error(e.getMessage(), e);
        if (isAjax(reqest)) {
            return Result.fail(e.getMessage());
        } else {
            ModelAndView mav = new ModelAndView();
            mav.addObject("exception", e);
            mav.addObject("url", reqest.getRequestURL());
            mav.setViewName(ERROR);
            return mav;
        }
    }

    /**
     * 判断是否是ajax请求
     */
    private static boolean isAjax(HttpServletRequest httpRequest) {
        return (httpRequest.getHeader("X-Requested-With") != null && "XMLHttpRequest".equals(httpRequest.getHeader("X-Requested-With")));
    }
}
