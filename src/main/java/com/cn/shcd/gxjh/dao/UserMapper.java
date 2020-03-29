package com.cn.shcd.gxjh.dao;

import com.cn.shcd.gxjh.entity.UserInfo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "userMapper")
public interface UserMapper{

    UserInfo login(String username, String password);

    UserInfo selectById(String id);

    UserInfo selectByName(String username);

    int insertUser(UserInfo userInfo);

    int updatePassword(String id,String password);

//    UserPermission getModList(int role_code);
}