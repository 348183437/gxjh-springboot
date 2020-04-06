package com.shcd.gxjh.domain.role;

import com.shcd.AccountIdentifier;

/**
 * Created by zhh on 2020/2/19.
 * TOO// 用户单元
 */


public class AccountMeta implements AccountIdentifier {

    public static String UID_PARAM_NAME = "uid";

    public static String ACCOUNT_NAME_PARAM_NAME = "accountName";

    private String uid;

    private String accountName;

    public static AccountMeta of(String uid, String accountName) {
        AccountMeta accountMeta = new AccountMeta();
        accountMeta.uid = uid;
        accountMeta.accountName = accountName;
        return accountMeta;
    }

    public String getUid() {
        return uid;
    }

    public String getAccountName() {
        return accountName;
    }

}
