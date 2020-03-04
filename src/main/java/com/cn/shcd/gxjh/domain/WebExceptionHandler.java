package com.cn.shcd.gxjh.domain;

import com.cn.shcd.gxjh.domain.exception.AuthException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

/**
 * 全局异常拦截
 */
@ControllerAdvice
public class WebExceptionHandler {

//    private static final Logger LOG = LoggerFactory.getLogger(WebExceptionHandler.class);

    /**
     * 权限异常
     */
    @ExceptionHandler(value = AuthException.class)
    @ResponseBody
    public String onException(HttpServletRequest req, Exception e) {
//        logException(req, e);
        return e.getMessage();
    }

//    private void logException(HttpServletRequest req, Exception e) {
//        if (LOG.isErrorEnabled()) {
//            LOG.error("请求异常：[uri={},method={},e={}]", req.getRequestURI(), req.getMethod(), e.getClass());
//            LOG.error(e.getMessage());
//            LOG.error("StackTrace: {}", e);
//        }
//    }

}
