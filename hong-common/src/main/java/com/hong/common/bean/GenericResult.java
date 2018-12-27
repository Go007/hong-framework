package com.hong.common.bean;

import java.io.Serializable;

/**
 * <br>泛型通用返回结果类</br>
 */
public class GenericResult<T> implements Serializable {
    private static final long serialVersionUID = -2804195259517755050L;

    public static final int SUCCESS_CODE = 0;
    public static final String ERROR_MSG = "fail";

    private int code; // 状态码

    private String message; // 说明信息

    private String result; // result结果，快速判断

    private T data; // 结果数据

    public GenericResult() {
        this(SUCCESS_CODE, "成功","success",null);
    }

    public GenericResult(int code, String msg) {
        this(code, msg, ERROR_MSG, null);
    }

    public GenericResult(T data) {
        this(SUCCESS_CODE, "成功", "success", data);
    }

    public GenericResult(int code, String message, String result, T data) {
        this.code = code;
        this.message = message;
        this.result = result;
        this.data=data;
    }

    public String getCode() {
        return String.valueOf(code);
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result [code=" + code + ", message=" + message + ", result=" + result + ", data=" + data + "]";
    }

}
