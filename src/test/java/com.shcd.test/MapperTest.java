package com.shcd.test;

import com.shcd.GxjhApplication;
import com.shcd.gxjh.dao.UserMapper;
import com.shcd.gxjh.entity.UserInfo;
import com.shcd.gxjh.entity.UserPermission;

import com.shcd.gxjh.service.UserInfoServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/**
 * @ClassName MapperTest
 * @Description TODO
 * @Author hh
 * @Data 2020/03/29 18:06
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = GxjhApplication.class)
public class MapperTest {
    @Autowired(required = false)
    private UserMapper userMapper;
    @Autowired(required = false)
    private UserInfoServer userInfoServer;
    @Test
    public void testSelectUser() {
        UserInfo userInfo = userInfoServer.findUserInfoById("5620B9489290481992A5FDD0BF2FDCF7");
        System.out.println(userInfo.toString());
    }
    @Test
    public void testLogin(){
        UserInfo userInfo = userInfoServer.login("sjgl","gxjh123");
        System.out.printf(userInfo.toString());
    }
    @Test
    public void testGetPermissionList(){
       List<UserPermission> list = userMapper.getModList(3);
//        List<UserPermission> list = userInfoServer.getUserPermission(3);
       System.out.printf(list.toString());
    }
}
