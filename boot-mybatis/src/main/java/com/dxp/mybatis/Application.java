package com.dxp.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author carzy.
 * @date 15:04 2019/2/11
 */
@MapperScans({
        @MapperScan("com.dxp.mybatis.mapper")
})
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
