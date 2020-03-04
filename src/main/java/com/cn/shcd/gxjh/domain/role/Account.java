package com.cn.shcd.gxjh.domain.role;

import com.cn.shcd.AccountIdentifier;
import com.cn.shcd.Permission;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhh on 2020/2/21.
 */
public class Account implements AccountIdentifier {

    private long uid;

    private List<Permission> permissionList = new ArrayList<>();

    private String credentialsSalt;

    public String getCredentialsSalt() {
        return credentialsSalt;
    }

    protected Account setCredentialsSalt(String credentialsSalt) {
        this.credentialsSalt = credentialsSalt;
        return this;
    }

    public Long getUid() {
        return uid;
    }

    protected Account setUid(long uid) {
        this.uid = uid;
        return this;
    }

    public List<Permission> getPermissions() {
        return permissionList;
    }

    protected List<Permission> getPermissionList() {
        return permissionList;
    }

    protected Account setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
        return this;
    }
}
