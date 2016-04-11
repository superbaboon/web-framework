package com.superbaboon.web.framework.core.response;

/**
 * Created by junjiewu on 16/4/11.
 */
public class JsonDataWrapper {

    private int code;

    private Object msg;

    public JsonDataWrapper(int code, Object data) {
        this.code = code;
        this.msg = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

}
