package com.shcd.gxjh.service;

import com.shcd.gxjh.dao.UserMapper;
import com.shcd.gxjh.entity.UserInfo;
import com.shcd.gxjh.entity.UserPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    private UserMapper userMapper;

    public UserInfo login(String username, String password){
        return userMapper.login(username, password);
    }
    public UserInfo findUserInfoById(String id){
        return userMapper.selectById(id);
    }
    public List<UserPermission> getUserPermission(int roleCode){
        return userMapper.getModList(roleCode);
    }

}
