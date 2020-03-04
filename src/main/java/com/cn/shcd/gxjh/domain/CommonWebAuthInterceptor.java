package com.cn.shcd.gxjh.domain;

import com.cn.shcd.AccountIdentifier;
import com.cn.shcd.Permission;
import com.cn.shcd.annotations.NeedLogin;
import com.cn.shcd.gxjh.domain.exception.AuthException;
import com.cn.shcd.gxjh.domain.role.AccountManager;
import com.cn.shcd.gxjh.domain.role.AccountMeta;
import com.cn.shcd.gxjh.domain.role.AccountPermission;
import com.cn.shcd.interceptor.WebAuthInterceptor;
import com.cn.shcd.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.method.HandlerMethod;
import com.cn.shcd.gxjh.domain.utils.CodingUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * web拦截器的基础实现
 * Created by zhh on 2020/2/21.
 */
@Component
public class CommonWebAuthInterceptor extends WebAuthInterceptor {

    @Autowired
    private AccountManager accountManager;

    public static final String RENEWAL_TOKEN_REQUEST_ATT_NAME = "renewal_token";

    public static final String ACCESS_TOKEN_REQUEST_ATT_NAME = "access_token";

    public static final String ACCESS_TOKEN_HEADER_NAME = "lp_token";

    @Autowired
    private AuthConfig authConfig;

    /**
     * 校验token并获取用户信息
     *
     * @param token
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    protected Optional<AccountIdentifier> verifyAndGetIdentifier(String token, HttpServletRequest request, HttpServletResponse response, HandlerMethod handler) {
        if (JwtUtils.verifyToken(token, authConfig.getSecretKey())) {
            Long uid = Long.valueOf(JwtUtils.getClaimMap(token).get(AccountMeta.UID_PARAM_NAME));
            return Optional.ofNullable(AccountMeta.of(uid));
        }
        return Optional.empty();
    }
    //解密Token
    @Override
    protected String decodeToken(String token) {
        return CodingUtils.decryptAES(token, authConfig.getTokenSecretKey());
    }

    @Override
    protected String getToken(HttpServletRequest request) {
        return request.getHeader(ACCESS_TOKEN_HEADER_NAME);
    }

    /**
     * 未登录流程
     *
     * @param token
     * @param request
     * @param response
     * @param handler
     */
    @Override
    protected void unAuthorizedProcess(String token, HttpServletRequest request, HttpServletResponse response, HandlerMethod handler) {
        throw new AuthException("未登录");
    }

    /**
     * 获取所需权限
     *
     * @param handler
     * @return
     */
    @Override
    protected List<Permission> getNeedPermissions(AccountIdentifier accountIdentifier, HandlerMethod handler) {
        NeedLogin needLogin = handler.getMethodAnnotation(NeedLogin.class);
        Assert.notNull(needLogin, () -> "");
        List<Permission> permissionList = new ArrayList<>();
        // 获取注解上所有权限
        for (int id : needLogin.value()) {
            // 将权限转换为枚举
            AccountPermission permission = AccountPermission.getByID(id);
            Assert.notNull(permission, "权限转换错误, 权限ID :" + id + "，方法 : " + handler.getMethod().getName());
            permissionList.add(permission);
        }
        return permissionList;
    }

    /**
     * 校验权限
     *
     * @param accountIdentifier
     * @param needPermissions
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean verifyPermissions(AccountIdentifier accountIdentifier, List<Permission> needPermissions, HttpServletRequest request, HttpServletResponse response) {
        // 从账户对象上获取权限列表
        AccountMeta accountMeta = (AccountMeta) accountIdentifier;
        List<Permission> hadPermissionList = accountManager.getAccount(accountMeta.getUid()).getPermissions();
        for (Permission permission : needPermissions) {
            // 判断权限列表是否包含所需权限
            if (!hadPermissionList.contains(permission)) {
                return false;
            }
        }
        // 成功
        return true;
    }

    /**
     * 拒绝流程
     *
     * @param token
     * @param request
     * @param response
     * @param handler
     */
    @Override
    protected void forbiddenProcess(String token, HttpServletRequest request, HttpServletResponse response, HandlerMethod handler) {
        throw new AuthException("没有权限，拒绝登录");
    }

    @Override
    protected void postGetAccount(String token, HttpServletRequest request, HttpServletResponse response, HandlerMethod handler) {
        super.postGetAccount(token, request, response, handler);
        request.setAttribute(RENEWAL_TOKEN_REQUEST_ATT_NAME, true);
        request.setAttribute(ACCESS_TOKEN_REQUEST_ATT_NAME, token);
    }

}
