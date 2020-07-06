package com.dxp.netty.client;

import com.dxp.netty.protocol.protobuf.MessageBase;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * 客户端处理类
 *
 * @author carzy
 * @date 2020/7/6
 */
public class ClientHandler extends SimpleChannelInboundHandler<MessageBase.Message> {

    private static final Logger log = LoggerFactory.getLogger(ClientHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        final MessageBase.Message.Builder builder = MessageBase.Message.newBuilder();
        builder.setCmd(MessageBase.Message.CommandType.REGISTER_REQUEST);
        builder.setRequestId(UUID.randomUUID().toString());
        builder.setContent("{\"username\":\"test\",\"password\":\"123456\"}");
        ctx.writeAndFlush(builder.build());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageBase.Message msg) throws Exception {
        log.info("客户端收到消息：{}", msg.toString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("捕获的异常：{}", cause.getMessage());
        ctx.channel().close();
    }
}
