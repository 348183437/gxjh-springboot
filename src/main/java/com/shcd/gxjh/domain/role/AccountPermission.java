package com.shcd.gxjh.domain.role;

/**
 * Created by zhh on 2020/2/17.
 */
public enum AccountPermission implements SysPermission {

    PERMISSION_1(SysPermission.PERMISSION_1, "模块1111"),

    PERMISSION_2(SysPermission.PERMISSION_1, "测试权限2"),

    PERMISSION_3(SysPermission.PERMISSION_1, "测试权限3"),

    PERMISSION_15(SysPermission.PERMISSION_15, "测试权限15"),;

    private int id;

    private String desc;

    AccountPermission(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }

    public static AccountPermission getByID(int id) {
        for (AccountPermission permission : values()) {
            if (permission.getId() == id) {
                return permission;
            }
        }
        return null;
    }
}
