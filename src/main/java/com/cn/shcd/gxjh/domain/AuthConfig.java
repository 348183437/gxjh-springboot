package com.cn.shcd.gxjh.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zhh on 2020/2/21.
 *
 * 配置参数
 * secretKey   保密内容，密码
 * tokenSecretKey   加密 解密Token时用到
 * refreshSpace Token   有效时间
 */
@Configuration
public class AuthConfig {
    //保密内容，密码
    @Value("${server.auth.secretKey: gxjh1234}")
    private String secretKey;
    //加密 解密Token时用到
    @Value("${server.auth.tokenSecretKey: gxjh5678}")
    private String tokenSecretKey;

    @Value("${server.auth.jwt.refreshSpace: 300000}")
    //刷新间隔
    private long tokenRefreshSpace;

    public String getSecretKey() {
        return secretKey;
    }

    public AuthConfig setSecretKey(String secretKey) {
        this.secretKey = secretKey;
        return this;
    }

    public String getTokenSecretKey() {
        return tokenSecretKey;
    }

    public AuthConfig setTokenSecretKey(String tokenSecretKey) {
        this.tokenSecretKey = tokenSecretKey;
        return this;
    }

    public long getTokenRefreshSpace() {
        return tokenRefreshSpace;
    }

    public AuthConfig setTokenRefreshSpace(long tokenRefreshSpace) {
        this.tokenRefreshSpace = tokenRefreshSpace;
        return this;
    }
}
