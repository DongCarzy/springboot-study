package com.dxp.shiro

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 * 启动类
 *
 * @author carzy
 * @date 2020/8/18
 */
@SpringBootApplication
open class Application

fun main(args: Array<String>) {

    SpringApplication.run(Application::class.java, *args)

}