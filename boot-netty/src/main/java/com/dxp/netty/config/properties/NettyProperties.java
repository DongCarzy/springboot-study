package com.dxp.netty.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * netty相关的配置项
 *
 * @author carzy
 * @date 2020/6/29
 */
@Component
@ConfigurationProperties(prefix = "netty")
public class NettyProperties {

    /**
     * 服务端口
     */
    private int port = 3333;

    /**
     * boss 线程数, 默认值为0,采用netty默认的线程数
     *
     * @see io.netty.channel.MultithreadEventLoopGroup for DEFAULT_EVENT_LOOP_THREADS
     */
    private int boss = 0;

    /**
     * work线程数
     *
     * @see io.netty.channel.MultithreadEventLoopGroup for DEFAULT_EVENT_LOOP_THREADS
     */
    private int work = 0;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getBoss() {
        return boss;
    }

    public void setBoss(int boss) {
        this.boss = boss;
    }

    public int getWork() {
        return work;
    }

    public void setWork(int work) {
        this.work = work;
    }
}
