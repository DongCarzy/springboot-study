package com.dxp.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dxp.mybatisplus.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户操作持久层操作类
 *
 * @author carzy
 * @date 2020/6/28
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
