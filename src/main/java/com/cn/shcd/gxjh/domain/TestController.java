package com.cn.shcd.gxjh.domain;

import com.cn.shcd.annotations.NeedLogin;
import com.cn.shcd.gxjh.domain.role.Account;
import com.cn.shcd.gxjh.domain.role.AccountManager;
import com.cn.shcd.gxjh.domain.role.AccountMeta;
import com.cn.shcd.gxjh.domain.role.SysPermission;
import com.cn.shcd.gxjh.domain.utils.CodingUtils;
import com.cn.shcd.utils.JwtUtils;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhh on 2020/2/23.
 */

@RestController
@ApiOperation(value = "测试接口")
public class TestController {
    @Autowired
    private AccountManager accountManager;

    @Autowired
    private AuthConfig authConfig;

    @GetMapping("test")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "lp_token", value = "令牌", required = true)
    })
    @NeedLogin
    @ApiOperation(value = "测试服务", notes = "测试服务")
    public String test(HttpServletResponse response) {
        return "success";
    }
    @GetMapping("testAuth")
    @ApiOperation(value = "测试登录服务", notes = "测试登录服务")
    public String testAuth(HttpServletRequest request) {

//        JSONObject date = new JSONObject();
//        date.put("name","张豪豪");
//        String count = "date";
//        if(!date.isEmpty()){
//            return ReturnFormat.ReturnString("返回成功","200",date);
//        }
//        String name = response.getHeader("user_name");
        String name = request.getHeader("user_name");
        String pwd  = request.getHeader("pwd");

//        System.out.println(name+","+pwd);
//        登陆的验证用户名密码
        Account account = accountManager.createAccount();
        Map<String, String> paramMap = new HashMap<>();
//      放入验证的信息
        paramMap.put(AccountMeta.UID_PARAM_NAME, account.getUid().toString());
//        paramMap.put(AccountMeta.PASSWORD_PARAM_NAME, account.getUid().toString());
//      生成token秘钥
        String tok = JwtUtils.sign(authConfig.getSecretKey(), authConfig.getTokenRefreshSpace(), paramMap);
        String token = CodingUtils.encryptAES(tok, authConfig.getTokenSecretKey());
        return token;
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
//    @NeedLogin({SysPermission.TEST_PERMISSION_1})
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
