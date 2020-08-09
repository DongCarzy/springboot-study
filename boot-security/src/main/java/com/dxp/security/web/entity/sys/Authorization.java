package com.dxp.security.web.entity.sys;

/**
 * 权限
 *
 * @author dxp
 * 2020/8/9 4:23 下午
 */
public class Authorization {

    /**
     * 主键
     */
    private Long id;

    /**
     * 权限名称
     */
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
