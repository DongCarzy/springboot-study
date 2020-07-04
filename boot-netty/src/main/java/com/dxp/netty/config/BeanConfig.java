package com.dxp.netty.config;

import com.dxp.netty.config.properties.NettyProperties;
import io.netty.channel.nio.NioEventLoopGroup;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Bean配置
 *
 * @author carzy
 * @date 2020/6/29
 */
@Configuration
public class BeanConfig {

    private final NettyProperties nettyProperties;

    public BeanConfig(NettyProperties nettyProperties) {
        this.nettyProperties = nettyProperties;
    }

    @Bean("boss")
    public NioEventLoopGroup boos(){
        return new NioEventLoopGroup(nettyProperties.getBoss());
    }

    @Bean("work")
    public NioEventLoopGroup work(){
        return new NioEventLoopGroup(nettyProperties.getWork());
    }

    @Bean
    @Primary
    public AsyncTaskExecutor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(8);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("dxp-core-");
        // 丢弃任务并抛出RejectedExecutionException异常
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        return executor;
    }
}
