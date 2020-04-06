package com.shcd.gxjh.domain.role;

import com.shcd.AccountIdentifier;
import com.shcd.Permission;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhh on 2020/2/21.
 */
public class Account implements AccountIdentifier {

    private String uid;

    private List<Permission> permissionList = new ArrayList<>();

    private String credentialsSalt;

    public String getCredentialsSalt() {
        return credentialsSalt;
    }

    protected Account setCredentialsSalt(String credentialsSalt) {
        this.credentialsSalt = credentialsSalt;
        return this;
    }

    public String getUid() {
        return uid;
    }

    protected Account setUid(String uid) {
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
