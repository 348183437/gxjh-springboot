package com.cn.shcd.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * 常用工具类
 */

//@SuppressWarnings({"ALL", "AlibabaClassMustHaveAuthor"})
public class CommonTools {
/**
 * @Author zhanghaohao
 * @Description 通用返回数据格式
 * @Date 23:42 2020/3/24
 * @Param [msg 提示信息, code 错误代码, date 返回数据]
 * @return java.lang.String
**/
    public static String returnString(String msg, String code, JSONObject date){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg",msg);
        jsonObject.put("code",code);
        jsonObject.put("date",date.toString());
        jsonObject.put("count",date.size());
        return jsonObject.toString();
    }

    /**
     * Base64加密
     *
     * 用于验证密码
     */


}
