package com.dxp.shiro.common;

/**
 * 通用常量类, 单个业务的常量请单开一个类, 方便常量的分类管理
 *
 * @author carzy
 */
public class Constants {

    private Constants() {
    }

    /**
     * session中存放用户信息的key值
     */
    public static final String SESSION_USER_INFO = "userInfo";
    public static final String SESSION_USER_ROLE = "userRole";
    public static final String SESSION_SIGN_OUT = "signOut";
    public static final String SESSION_USER_PERMISSION = "userPermission";

    /**
     * 默认密码
     */
    public static final String DEFAULT_PASSWORD = "12345678";
    public static final int MD5_HASH_ITERATIONS = 3;

    public static final String REDIS_USER_LOGIN = "user:login:";
}
