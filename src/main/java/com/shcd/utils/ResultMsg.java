package com.shcd.utils;


import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONObject;

import java.util.Date;

/**
 * 返回结果
 */
public class ResultMsg {

    //返回代码
    private String code;
    //返回状态
    private String result;
    //返回信息
    private String msg;
    //返回数据
    private Object data;


    public String getResultMsg(){
        return new JSONObject(this).toString();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
