package com.shcd.gxjh.domain.role;

/**
 * Created by zhh on 2020/2/17.
 */
public enum AccountPermission implements SysPermission {

    TEST_PERMISSION_1(SysPermission.TEST_PERMISSION_1, "测试权限1"),

    TEST_PERMISSION_2(SysPermission.TEST_PERMISSION_2, "测试权限2"),

    TEST_PERMISSION_3(SysPermission.TEST_PERMISSION_2, "测试权限3"),;
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
