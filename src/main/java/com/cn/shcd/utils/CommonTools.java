package com.cn.shcd.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * 常用工具类
 */

public class CommonTools {
    /**
     *  json返回值格式
     * @param msg  提示信息
     * @param code 错位代码
     * @param date  返回数据
     *
     */
    public static String ReturnString(String msg, String code, JSONObject date){
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
