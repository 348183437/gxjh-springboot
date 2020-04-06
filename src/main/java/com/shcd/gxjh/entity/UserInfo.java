package com.shcd.gxjh.entity;



/**
 * @ClassName UserInfo
 * @Description TODO  用户表
 * @Author hh
 * @Data
 * @Version 1.0
 **/

public class UserInfo {
    private String id;
    private String username;
    private String password;
    private int type;
    private String passwordBody;
    private String salt;
    private int role;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPasswordBody() {
        return passwordBody;
    }

    public void setPasswordBody(String passwordBody) {
        this.passwordBody = passwordBody;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                ", passwordBody='" + passwordBody + '\'' +
                ", salt='" + salt + '\'' +
                ", role=" + role +
                '}';
    }
}
