package com.cn.shcd.gxjh.domain.role;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

/**
 * Created by zhh on 2020/2/23.
 * 用户管理
 */
@Component
public class AccountManager {

    public static Account TEST_ACCOUNT = new Account()
            .setUid(1)
            .setCredentialsSalt("lp")
            .setPermissionList(Lists.newArrayList(AccountPermission.TEST_PERMISSION_1));
    public Account getAccount(long uid) {
        return TEST_ACCOUNT;
    }

    public Account createAccount() {
        return TEST_ACCOUNT;
    }
}
