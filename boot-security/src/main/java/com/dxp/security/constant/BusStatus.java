package com.dxp.security.constant;

/**
 * 业务状态吗
 *
 * @author carzy
 * @date 2020/8/7
 */
public enum BusStatus {

    /**
     * 转态码 + 状态信息表述
     */
    SUCCESS(1000, "成功"),
    UNAUTHORIZED(4001, "未认证"),
    AUTHORIZED_ERROR(4002, "用户名或密码错误"),
    FORBIDDEN(4003, "权限不足"),
    UNKNOWN(5000, "未知异常"),
    ;

    /**
     * 状态码
     */
    private int code;

    /**
     * 中文表述信息
     */
    private String description;

    public int code() {
        return code;
    }

    public String description() {
        return description;
    }

    BusStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
