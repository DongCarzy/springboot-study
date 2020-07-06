package com.dxp.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * 客户端启动类.
 * 测试类
 *
 * @author carzy
 * @date 2020/7/6
 */
public class ClientServer {

    private static final Logger log = LoggerFactory.getLogger(ClientServer.class);

    public static void main(String[] args) {
        NioEventLoopGroup work = new NioEventLoopGroup();
        start(work);
    }

    private static void start(NioEventLoopGroup work){
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(work);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.remoteAddress(new InetSocketAddress("127.0.0.1", 3333))
                //.localAddress(new InetSocketAddress(7777)) 绑定本地端口.
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ClientHandlerInitializer());
        /*
         * 绑定本地采用固定端口连接服务端的3333端口
         * bootstrap.connect(new InetSocketAddress("127.0.0.1", 3333), new InetSocketAddress(7777));
         */
        ChannelFuture future = bootstrap.connect();
        final Channel channel = future.channel();
        future.addListener(f -> {
            if (f.isSuccess()) {
                log.info("connection server suc.");
            } else {
                log.info("连接失败，进行断线重连");
                channel.close();
            }
        });

        channel.closeFuture().addListener(f -> {
            start(work);
        });
    }

}
