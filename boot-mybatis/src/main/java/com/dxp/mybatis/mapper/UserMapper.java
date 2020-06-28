package com.dxp.mybatis.mapper;

import com.dxp.mybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户操作持久层操作类
 *
 * @author carzy
 * @date 2020/6/28
 */
@Mapper
public interface UserMapper {

    /**
     * 新增用户
     *
     * @param user 用户信息
     * @return 影响行数
     */
    Integer save(@Param("user") User user);

    /**
     * 通过ID查询唯一用户信息.
     *
     * @param id 主键
     * @return 返回用户信息
     */
    User getById(@Param("id") Long id);

    /**
     * 通过ID修改用户信息.
     *
     * @param user 用户信息
     */
    void updateById(@Param("user") User user);

    /**
     * 查询所有用户信息.
     *
     * @return 用户集合
     */
    List<User> all();

    /**
     * 通过Id删除用户信息.
     * @param id 用户ID
     */
    void deleteById(@Param("id") Long id);
}
