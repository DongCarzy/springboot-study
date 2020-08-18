package com.dxp.shiro.constant

/**
 * 业务状态吗
 *
 * @author carzy
 * @date 2020/8/7
 */
enum class BusStatus(
        /**
         * 状态码
         */
        private val code: Int,
        /**
         * 中文表述信息
         */
        private val description: String) {
    /**
     * 转态码 + 状态信息表述
     */
    SUCCESS(1000, "成功"),
    UNAUTHORIZED(4001, "未认证"),
    AUTHORIZED_ERROR(4002, "用户名或密码错误"),
    FORBIDDEN(4003, "权限不足"),
    UNKNOWN(5000, "未知异常");

    fun code(): Int {
        return code
    }

    fun description(): String {
        return description
    }
}