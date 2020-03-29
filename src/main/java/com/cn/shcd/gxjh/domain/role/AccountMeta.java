package com.cn.shcd.gxjh.domain.role;

import com.cn.shcd.AccountIdentifier;
import com.cn.shcd.gxjh.entity.UserInfo;

/**
 * Created by zhh on 2020/2/19.
 * TOO// 用户单元
 */


public class AccountMeta implements AccountIdentifier {

//    public static String UID_PARAM_NAME;

    private Long uid;

    public static AccountMeta of(Long uid) {
        AccountMeta accountMeta = new AccountMeta();
        accountMeta.uid = uid;
        return accountMeta;
    }

    public Long getUid() {
        return uid;
    }

}
