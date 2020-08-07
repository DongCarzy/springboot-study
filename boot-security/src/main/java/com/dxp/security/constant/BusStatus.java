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
    UNKNOWN(1000, "未知异常"),
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
