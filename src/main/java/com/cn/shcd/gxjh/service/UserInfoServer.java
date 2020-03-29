package com.cn.shcd.gxjh.service;

import com.cn.shcd.gxjh.dao.UserMapper;
import com.cn.shcd.gxjh.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserInfoServer
 * @Description TODO
 * @Author hh
 * @Data
 * @Version 1.0
 **/
@Service
public class UserInfoServer {
    @Autowired
    UserMapper userMapper;
    public UserInfo login(UserInfo userInfo){
        userInfo=this.userMapper.login(userInfo.getUsername(), userInfo.getPassword());
        return userInfo;
    }

//    @Autowired
//    public UserPermission getModList(UserPermission userPermission){
//        userPermission = this.userMapper.getModList(userPermission.getRole_code());
//        return userPermission;
//    }

}
