package com.shcd.gxjh.domain.role;

import com.shcd.Permission;
import com.shcd.gxjh.entity.UserInfo;
import com.google.common.collect.Lists;
import com.shcd.gxjh.entity.UserPermission;
import com.shcd.gxjh.service.UserInfoServer;
import org.springframework.beans.factory.annotation.Autowired;
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
    UserPermission userPermission = new UserPermission();
    @Autowired
    private UserInfoServer userInfoServer;
//
//    UserPermission userPermission = new UserPermission();


//        user_id=userInfo.getId();
//        code_salt=userInfo.getSalt();
//        user_role=userInfo.getSalt();
     //    permissionList=(Lists.newArrayList(AccountPermission.TEST_PERMISSION_1,AccountPermission.TEST_PERMISSION_2));
    public static ArrayList<Permission> roleList = Lists.newArrayList();
    public static Account TEST_ACCOUNT = new Account();

    public Account getAccount(String uid) {
        return TEST_ACCOUNT;
    }

    public Account createAccount(String uid) {
        int roleCode = userInfoServer.getRoleCode(uid);
        List<UserPermission> userPermision = userInfoServer.getUserPermission(roleCode);
        List<Permission> customPermissionList = new ArrayList<>(userPermision.size());
        for (UserPermission permission : userPermision) {
            CustomPermission customPermission = CustomPermission.create()
                    .id(permission.getModCode())
                    .desc(permission.getModName()).build();
            customPermissionList.add(customPermission);
        }
        Account account = new Account().setUid("2")
                .setCredentialsSalt("233")
                .setPermissionList(customPermissionList);
        return account;
    }
}