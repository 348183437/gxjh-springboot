package com.cn.shcd.gxjh.entity;

import org.springframework.context.annotation.Bean;

/**
 * @ClassName UserPermission
 * @Description TODO 根据用户角色查询模块权限list
 * @Author hh
 * @Data 2020/3/28 16:49
 * @Version 1.0
 **/
public class UserPermission {
    private int mod_code;
    private String mod_name;
    private int mod_level;
    private int role_name;
    private int role_code;

    public int getMod_code() {
        return mod_code;
    }

    public String getMod_name() {
        return mod_name;
    }

    public int getMod_level() {
        return mod_level;
    }

    public int getRole_name() {
        return role_name;
    }
    public int getRole_code() {
        return role_code;
    }

    public void setMod_code(int mod_code) {
        this.mod_code = mod_code;
    }

    public void setMod_name(String mod_name) {
        this.mod_name = mod_name;
    }

    public void setMod_level(int mod_level) {
        this.mod_level = mod_level;
    }

    public void setRole_name(int role_name) {
        this.role_name = role_name;
    }

    public void setRole_code(int role_code) {
        this.role_code = role_code;
    }
}
