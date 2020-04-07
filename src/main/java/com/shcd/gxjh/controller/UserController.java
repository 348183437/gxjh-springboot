package com.shcd.gxjh.controller;

import com.alibaba.fastjson.JSONObject;
import com.shcd.annotations.NeedLogin;
import com.shcd.gxjh.dao.UserMapper;
import com.shcd.gxjh.domain.AuthConfig;
import com.shcd.gxjh.domain.CommonWebAuthInterceptor;
import com.shcd.gxjh.domain.role.AccountMeta;
import com.shcd.gxjh.domain.role.SysPermission;
import com.shcd.gxjh.domain.utils.CodingUtils;
import com.shcd.gxjh.entity.UserInfo;
import com.shcd.gxjh.entity.UserPermission;
import com.shcd.gxjh.service.UserInfoServer;
import com.shcd.utils.JwtUtils;
import com.shcd.utils.CommonTools;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhh on 2020/2/23.
 */
@CrossOrigin
@RestController
@ApiOperation(value = "测试接口")
public class UserController {
//    @Autowired
//    private AccountManager accountManager;

    @Autowired
    private AuthConfig authConfig;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserInfoServer userInfoServer;
    @GetMapping("test")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "lp_token", value = "令牌", required = true)
    })
//    @NeedLogin
    @ApiOperation(value = "测试服务", notes = "测试服务")
    public String test(HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", null);
        return CommonTools.returnString("请求成功","200",jsonObject);
    }
    @GetMapping("login")
    @ApiOperation(value = "登录", notes = "登录")
    public String Login(UserInfo userInfo, HttpServletRequest request, HttpSession session) {
        String username = request.getHeader("username");
        String password = request.getHeader("password");
        //权限列表
        JSONObject jsonObject = new JSONObject();
        if(username==null||password==null){
            return CommonTools.returnString("参数错误","202",jsonObject);
        }
        userInfo = userMapper.login(username,password);
            if(userInfo != null){
                session.setAttribute("userinfo",userInfo);
                Map<String, String> paramMap = new HashMap<>();
                paramMap.put(AccountMeta.UID_PARAM_NAME,userInfo.getId());
                paramMap.put(AccountMeta.ACCOUNT_NAME_PARAM_NAME, userInfo.getUsername());
//                List<UserPermission> userPermissionList = userInfoServer.getUserPermission(userInfo.getRole().toString());
//            获取签名
                String tok = JwtUtils.sign(authConfig.getSecretKey(), authConfig.getTokenRefreshSpace(), paramMap);
//            AES加密
                String token = CodingUtils.encryptAES(tok, authConfig.getTokenSecretKey());
                jsonObject.put("token",token);
//                jsonObject.put("userPermission",userPermissionList);
                jsonObject.put("userinfo",userInfo);
                return CommonTools.returnString("登录成功","200",jsonObject);
        }else{
            return  CommonTools.returnString("密码错误","401",jsonObject);
        }
    }
    @NeedLogin(SysPermission.PERMISSION_1)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "lp_token", value = "令牌", required = true)
    })

    @GetMapping("testPermission")
    @ApiOperation(value = "测试权限服务", notes = "测试权限服务")
    public String testPermission() {
        return "success";
    }

    @NeedLogin({SysPermission.PERMISSION_1})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "lp_token", value = "令牌", required = true)
    })

    @GetMapping("testNoPermission")
    @ApiOperation(value = "测试无权限服务", notes = "测试无权限服务")
    public String testNoPermission() {
        return "success";
    }

    @NeedLogin
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "lp_token", value = "令牌", required = true)
    })
    @GetMapping("testLogOut")
    @ApiOperation(value = "测试退出", notes = "测试退出")
    public String testLogOut(HttpServletRequest request) {
        request.setAttribute(CommonWebAuthInterceptor.RENEWAL_TOKEN_REQUEST_ATT_NAME, false);
        return "success";
    }
}
