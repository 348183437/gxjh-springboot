package com.shcd.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * JWT工具类
 */
public class JwtUtils {

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的签发时间
     */
    public static Date getIssuedAt(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getIssuedAt();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public static Map<String, String> getClaimMap(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            Map<String, String> dataMap = new HashMap<>();
            for (Map.Entry<String, Claim> entry : jwt.getClaims().entrySet()) {
                dataMap.put(entry.getKey(), entry.getValue().asString());
            }
            return dataMap;
        } catch (JWTDecodeException e) {
            return Collections.emptyMap();
        }
    }

    /**
     * 校验Token
     *
     * @param token   jwtToken
     * @param secrecy 保密内容，用作签名，例如用户密码
     * @return
     */
    public static boolean verifyToken(String token, String secrecy) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secrecy);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            Date expiresAt = decodedJWT.getExpiresAt();
            if (Objects.nonNull(expiresAt)) {
                if (System.currentTimeMillis() > expiresAt.getTime()) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {

        }
        return false;
    }

    /**
     * 生成签名,expireTime后过期
     *
     * @param time 过期时间
     * @param salt 盐（加密时传入，防止解密）
     * @param time 超时时间
     * @param paramMap  参数map
     * @return 加密的token
     */
    public static String sign(String salt, long time, Map<String, String> paramMap) {
        try {
            Date issuedDate = new Date();
            Date expiresDate = new Date(System.currentTimeMillis() + time);
            Algorithm algorithm = Algorithm.HMAC256(salt);
            /**
             * withExpiresAt 设置过期时间 过期时间大于签发时间
             * withIssuedAt 设置签发时间
             */
            JWTCreator.Builder builder = JWT.create()
                    .withExpiresAt(expiresDate)
                    .withIssuedAt(issuedDate);
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                builder.withClaim(entry.getKey(), entry.getValue());
            }
            return builder.sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * token是否过期
     *
     * @return true：过期
     */
    public static boolean isTokenExpired(String token) {
        Date now = Calendar.getInstance().getTime();
        DecodedJWT jwt = JWT.decode(token);
        Date time = jwt.getExpiresAt();
        if (time == null) {
            return false;
        }
        return time.before(now);
    }

}
