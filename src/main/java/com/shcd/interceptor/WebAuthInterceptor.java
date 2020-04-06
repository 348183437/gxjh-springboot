package com.shcd.interceptor;

import com.shcd.AccountIdentifier;
import com.shcd.Permission;
import com.shcd.annotations.NeedLogin;
import com.google.common.base.Joiner;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

/**
 * web拦截器
 */
public abstract class WebAuthInterceptor implements HandlerInterceptor {

    protected static Logger logger = LoggerFactory.getLogger(WebAuthInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        Optional<HandlerMethod> methodOP = getHandlerMethod(handler);
        // 如果不是访问接口，直接成功
        if (!methodOP.isPresent()) {
            return true;
        }
        Optional<AccountIdentifier> IdentifierOp;

        String token = this.getToken(request);
        // 如果有token，则验证有效性
        if (StringUtils.isNotBlank(token)) { //判断某字符串是否不为空且长度不为0且不由空白符(whitespace)构成
            token = decodeToken(token);
            // 校验和获取用户
            IdentifierOp = verifyAndGetIdentifier(token, request, response, methodOP.get());
            if (IdentifierOp.isPresent()) { //判断对象是否存在
                postGetAccount(token, request, response, methodOP.get());
            }
            // 获取旧的用户
        } else {
            IdentifierOp = Optional.empty();
            // 构建新的用户
        }

        // 需要授权
        if (isNeedToAuth(methodOP.get())) {
            if (!IdentifierOp.isPresent()) {
                if (logger.isErrorEnabled()) {
                    logger.error("接口权限验证失败, 未登录!");
                }
                // 未登录异常
                unAuthorizedProcess(token, request, response, methodOP.get());
            }
            List<Permission> permissions = getNeedPermissions(IdentifierOp.get(), methodOP.get());
            if (!verifyPermissions(IdentifierOp.get(), permissions, request, response)) {
                if (logger.isErrorEnabled()) {
                    logger.error("接口权限验证失败, 需要权限: {}", Joiner.on(",").join(permissions));
                }
                // 权限不足
                forbiddenProcess(token, request, response, methodOP.get());
            }

            if (logger.isDebugEnabled()) {
                logger.debug("接口权限验证成功:[{}].", request.getServletPath());
            }

        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("接口不需要授权:[{}].", request.getServletPath());
            }
        }

        return true;
    }

    protected abstract String getToken(HttpServletRequest request);

    /**
     * 解密token
     *
     * @param token
     * @return
     */
    protected String decodeToken(String token) {
        return token;
    }

    /**
     * 校验token并获取用户信息
     *
     * @param token
     * @param request
     * @param response
     * @param handler
     * @return
     */
    protected abstract Optional<AccountIdentifier> verifyAndGetIdentifier(String token, HttpServletRequest request, HttpServletResponse response, HandlerMethod handler);

    /**
     * 未登录流程
     *
     * @param token
     * @param request
     * @param response
     * @param handler
     */
    protected abstract void unAuthorizedProcess(String token, HttpServletRequest request, HttpServletResponse response, HandlerMethod handler);

    /**
     * 获取所需权限
     *
     * @param handler
     * @return
     */
    protected abstract List<Permission> getNeedPermissions(AccountIdentifier identifier, HandlerMethod handler);

    /**
     * 校验权限
     *
     * @param permissions
     * @param request
     * @param response
     * @return
     */
    protected abstract boolean verifyPermissions(AccountIdentifier identifier, List<Permission> permissions, HttpServletRequest request, HttpServletResponse response);

    /**
     * 拒绝流程
     *
     * @param token
     * @param request
     * @param response
     * @param handler
     */
    protected abstract void forbiddenProcess(String token, HttpServletRequest request, HttpServletResponse response, HandlerMethod handler);


    /**
     * 获取账户后
     *
     * @param token
     * @param request
     * @param response
     * @param handler
     */
    protected void postGetAccount(String token, HttpServletRequest request, HttpServletResponse response, HandlerMethod handler) {

    }

    /**
     * 判断是否是Controller方法
     *
     * @param handler
     * @return
     */
    private Optional<HandlerMethod> getHandlerMethod(Object handler) {
        if (handler instanceof HandlerMethod) {
            return Optional.of((HandlerMethod) handler);
        }
        return Optional.empty();
    }

    /**
     * 是否需要授权
     *
     * @param handler Controller接口方法
     * @return 是否需要授权
     */
    protected boolean isNeedToAuth(HandlerMethod handler) {
        return handler.hasMethodAnnotation(NeedLogin.class);
    }

}
