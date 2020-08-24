package com.dxp.shiro.web.vo;

import com.dxp.security.constant.BusStatus;

import java.io.Serializable;

/**
 * jaxa返回的JSON
 *
 * @author carzy
 * @date 2020/8/6
 */
public class R<T> implements Serializable {

    private int code;

    private String msg;

    private T data;

    public static <T> R<T> suc(T data) {
        return of(data, BusStatus.SUCCESS);
    }

    public static R<String> err(BusStatus status){
        return err("", status);
    }

    public static R<String> err(String data, BusStatus status){
        return of(data, status);
    }

    public static <T> R<T> of(T data, BusStatus status) {
        R<T> r = new R<>();
        r.setCode(status.code());
        //todo.. 国际化. 英文时则用 status.name()
//        r.setMsg(status.name());
        r.setMsg(status.description());
        r.setData(data);
        return r;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
