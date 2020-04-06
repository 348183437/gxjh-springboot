package com.shcd.gxjh.entity;

import java.util.ArrayList;

/**
 * @ClassName UserPermission
 * @Description TODO 根据用户角色查询模块权限list
 * @Author hh
 * @Data 2020/3/28 16:49
 * @Version 1.0
 **/
public class UserPermission  {
    private int modCode;
    private String modName;
    private int modLevel;
    private int roleName;
    private int roleCode;

    public int getModCode() {
        return modCode;
    }

    public void setModCode(int modCode) {
        this.modCode = modCode;
    }

    public String getModName() {
        return modName;
    }

    public void setModName(String modName) {
        this.modName = modName;
    }

    public int getModLevel() {
        return modLevel;
    }

    public void setModLevel(int modLevel) {
        this.modLevel = modLevel;
    }

    public int getRoleName() {
        return roleName;
    }

    public void setRoleName(int roleName) {
        this.roleName = roleName;
    }

    public int getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(int roleCode) {
        this.roleCode = roleCode;
    }

    @Override
    public String toString() {
        return "UserPermission{" +
                "modCode=" + modCode +
                ", modName='" + modName + '\'' +
                ", modLevel=" + modLevel +
                ", roleName=" + roleName +
                ", roleCode=" + roleCode +
                '}';
    }
}
