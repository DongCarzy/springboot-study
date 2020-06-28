package com.dxp.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 系统级别的sql语句.
 * <p>
 * 初始化,查询binlog,死锁,连接数等
 *
 * @author carzy
 * @date 2020/6/28
 */
@Mapper
public interface SystemMapper {

    void loadDb(@Param("sql") String sql);

}
