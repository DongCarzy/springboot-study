package com.dxp.mybatis.config;


import com.dxp.mybatis.entity.User;
import com.dxp.mybatis.mapper.SystemMapper;
import com.dxp.mybatis.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.stream.Stream;

/**
 * 初始化数据库,为测试做准备
 *
 * @author carzy
 * @date 2020/6/28
 */
@Component
public class InitDb implements ApplicationListener<ContextRefreshedEvent> {

    private final Logger logger = LoggerFactory.getLogger(InitDb.class);
    private final SystemMapper systemMapper;
    private final UserMapper userMapper;

    public InitDb(SystemMapper systemMapper, UserMapper userMapper) {
        this.systemMapper = systemMapper;
        this.userMapper = userMapper;
    }

    @Override
    public void onApplicationEvent(@NonNull ContextRefreshedEvent contextRefreshedEvent) {
        try {
            loadData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 采用的是内存数据库 h2.  这里初始化数据
     */
    private void loadData() throws IOException {
        logger.info("init db start");
        String rootDir = System.getProperty("user.dir");
        String sqlPath = rootDir + "/boot-mybatis/sql-script/script.sql";
        File file = new File(sqlPath);
        String sql = String.join("\r\n", Files.readAllLines(file.toPath(), StandardCharsets.UTF_8));
        systemMapper.loadDb(sql);
        Stream.of(
                new User(1L, "user1", "1234", 20),
                new User(2L, "user2", "1234", 21),
                new User(3L, "user3", "1234", 21),
                new User(4L, "user4", "1234", 20),
                new User(5L, "user5", "1234", 21)
        ).forEach(user -> userMapper.save(user));
        logger.info("init db end");
    }
}
