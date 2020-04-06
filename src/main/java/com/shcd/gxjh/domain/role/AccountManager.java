package com.shcd.gxjh.domain.role;

import com.shcd.Permission;
import com.shcd.gxjh.entity.UserInfo;
import com.google.common.collect.Lists;
import com.shcd.gxjh.entity.UserPermission;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhh on 2020/2/23.
 * 用户管理 初始化用户信息
 */
@Component
public class AccountManager {
/**
 * @Author zhanghaohao
 * @Description //TODO 创建用户 并赋予用户权限
 * @Date 22:57 2020/3/25
 * @Param
 * @return
**/

//    UserInfo userInfo = new UserInfo();
//    Permission userPermission = new UserPermission();
//
//    UserPermission userPermission = new UserPermission();


//        user_id=userInfo.getId();
//        code_salt=userInfo.getSalt();
//        user_role=userInfo.getSalt();
     //    permissionList=(Lists.newArrayList(AccountPermission.TEST_PERMISSION_1,AccountPermission.TEST_PERMISSION_2));
//        ArrayList<Permission> RoleList = new ArrayList<Permission>();
//        permissionList = (Lists.newArrayList(RoleList));


    public static Account TEST_ACCOUNT = new Account()
            .setUid("2")
            .setCredentialsSalt("2333")
//            .setPermissionList(permissionList);
            //根据用户角色给权限
            .setPermissionList(Lists.newArrayList(AccountPermission.TEST_PERMISSION_1,AccountPermission.TEST_PERMISSION_2));

    public Account getAccount(String uid) {
        return TEST_ACCOUNT;
    }

    public Account createAccount() {
        return TEST_ACCOUNT;
    }
}