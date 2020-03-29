package com.cn.shcd.gxjh.domain;

import com.alibaba.fastjson.JSONObject;
import com.cn.shcd.annotations.NeedLogin;
import com.cn.shcd.gxjh.dao.UserMapper;
import com.cn.shcd.gxjh.domain.role.SysPermission;
import com.cn.shcd.gxjh.domain.utils.CodingUtils;
import com.cn.shcd.gxjh.entity.UserInfo;
import com.cn.shcd.utils.JwtUtils;
import com.cn.shcd.utils.CommonTools;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhh on 2020/2/23.
 */
@RestController
@ApiOperation(value = "测试接口")
public class TestController {

//    @Autowired
//    private AccountManager accountManager;

    @Autowired
    private AuthConfig authConfig;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("test")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "lp_token", value = "令牌", required = true)
    })
//    @NeedLogin
    @ApiOperation(value = "测试服务", notes = "测试服务")
    public String test(HttpServletResponse response) {
        CommonTools commonTools = new CommonTools();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", null);
        return CommonTools.returnString("请求成功","200",jsonObject);
    }
    @GetMapping("login")
    @ApiOperation(value = "登录", notes = "登录")
    public String Login(UserInfo userInfo, HttpServletRequest request, HttpSession session) {

        String username = request.getHeader("username");
        String password = request.getHeader("password");
        userInfo.setUsername(username);
        userInfo.setPassword(password);

        userInfo = this.userMapper.login(username,password);
        if(userInfo != null){
            session.setAttribute("userinfo",userInfo);

            Map<String, String> paramMap = new HashMap<>();
//      放入验证的信息
//            paramMap.put(AccountMeta.UID_PARAM_NAME, account.getUid().toString());
            paramMap.put(userInfo.getUsername(),userInfo.getId());
//      生成token秘钥
            String tok = JwtUtils.sign(authConfig.getSecretKey(), authConfig.getTokenRefreshSpace(), paramMap);
            String token = CodingUtils.encryptAES(tok, authConfig.getTokenSecretKey());
            return token;
        }else{
            return null;
        }


    }
    @NeedLogin(SysPermission.TEST_PERMISSION_1)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "lp_token", value = "令牌", required = true)
    })

    @GetMapping("testPermission")
    @ApiOperation(value = "测试权限服务", notes = "测试权限服务")
    public String testPermission() {
        return "success";
    }

    @NeedLogin({SysPermission.TEST_PERMISSION_1, SysPermission.TEST_PERMISSION_2})
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
