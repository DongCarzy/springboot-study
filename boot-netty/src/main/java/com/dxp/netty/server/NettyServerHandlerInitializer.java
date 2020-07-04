package com.dxp.netty.server;

import com.dxp.netty.handler.HeartbeatHandler;
import com.dxp.netty.handler.LoggingHandler;
import com.dxp.netty.protocol.protobuf.MessageBase;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * 初始化 pipeline
 *
 * @author carzy
 * @date 2020/6/29
 */
public class NettyServerHandlerInitializer extends ChannelInitializer<NioSocketChannel> {

    private final LoggingHandler loggingHandler = new LoggingHandler();

    @Override
    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
        final ChannelPipeline pipeline = nioSocketChannel.pipeline();

        pipeline.addLast(new IdleStateHandler(10, 0, 0))
                .addLast(new ProtobufVarint32FrameDecoder())
                .addLast(new ProtobufDecoder(MessageBase.Message.getDefaultInstance()))
                .addLast(new ProtobufVarint32LengthFieldPrepender())
                .addLast(new ProtobufEncoder())
                .addLast(loggingHandler)
                .addLast(new HeartbeatHandler())
                .addLast(new ServerDispatchHandler());
    }
}