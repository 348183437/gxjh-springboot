package com.shcd.test;

import com.cn.shcd.gxjh.controller.LpAccountApplication;
import com.cn.shcd.gxjh.dao.UserMapper;
import com.cn.shcd.gxjh.entity.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName MapperTest
 * @Description TODO
 * @Author hh
 * @Data 2020/03/29 18:06
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = LpAccountApplication.class)
public class MapperTest {
    @Autowired(required = false)
    private UserMapper userMapper;

    @Test
    public void testSelectUser() {
        UserInfo userInfo = userMapper.selectById("5620B9489290481992A5FDD0BF2FDCF7");
        System.out.println(userInfo.toString() );
    }
}
