package com.cn.shcd.gxjh.domain.role;

import com.cn.shcd.AccountIdentifier;

/**
 * Created by zhh on 2020/2/19.
 */
public class AccountMeta implements AccountIdentifier {

    public static String UID_PARAM_NAME = "uid";
//    public static String PASSWORD_PARAM_NAME = "pwd";
    private long uid;
//    private String pwd;

    public static AccountMeta of(long uid) {
        AccountMeta accountMeta = new AccountMeta();
        accountMeta.uid = uid;
//        accountMeta.pwd = pwd;
        return accountMeta;
    }

    public Long getUid() {
        return uid;
    }
//    public String  getPwd() {
//        return pwd;
//    }

}
