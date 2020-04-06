package com.shcd.gxjh.dao;

import com.shcd.gxjh.entity.UserInfo;
import com.shcd.gxjh.entity.UserPermission;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "userMapper")
public interface UserMapper{

    UserInfo login(String username, String password);

    UserInfo selectById(String id);

    UserInfo selectByName(String username);

    int insertUser(UserInfo userInfo);

    int updatePassword(String id,String password);

    List<UserPermission> getModList(int roleCode);

    int getRoleCode(String uid);
}