package com.cn.shcd.gxjh.domain.role;

import com.cn.shcd.gxjh.entity.UserInfo;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

/**
 * Created by zhh on 2020/2/23.
 * 用户管理 初始化用户信息
 */
@Component
public class AccountManager {
/**
 * @Author zhanghaohao
 * @Description //TODO 创建用户 并赋予用户权限
 * @Date 22:57 2020/3/25
 * @Param
 * @return
**/
    UserInfo userInfo = new UserInfo();
    private static String user_id;
    private static String code_salt;//盐
    private static String user_role;//用戶角色
    {user_id=userInfo.getId();code_salt=userInfo.getSalt();user_role=user_role;}
    public static Account TEST_ACCOUNT = new Account()
            .setUid(user_id)
            .setCredentialsSalt(code_salt)
            //根据用户角色给权限
            .setPermissionList(Lists.newArrayList(AccountPermission.TEST_PERMISSION_1));

    public Account getAccount(long uid) {
        return TEST_ACCOUNT;
    }

    public Account createAccount() {
        return TEST_ACCOUNT;
    }
}