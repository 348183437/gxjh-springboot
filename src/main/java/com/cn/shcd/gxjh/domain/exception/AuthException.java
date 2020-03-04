package com.cn.shcd.gxjh.domain.exception;

/**
 * 自定义权限异常
 * Created by zhh on 2020/2/23.
 */
public class AuthException extends RuntimeException {

    public AuthException() {
        super();
    }

    public AuthException(String msg) {
        super(msg);
    }

    public AuthException(Throwable throwable) {
        super(throwable);
    }

}
