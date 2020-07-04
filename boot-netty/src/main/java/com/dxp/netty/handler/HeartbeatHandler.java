package com.dxp.netty.handler;

import com.dxp.netty.protocol.protobuf.MessageBase;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * 服务端的心跳检测.
 * <p>
 * 客户端网络异常断开时,虽然是TCP连接,但是应用层也不会收到任何的响应,此时主动向终端发送一条心跳,若发送失败则关闭连接,否则不做任何事.
 *
 * @author carzy
 */
public class HeartbeatHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(HeartbeatHandler.class);

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            if (idleStateEvent.state() == IdleState.READER_IDLE) {
                logger.debug("{} read timeout 10s, try to send idea message", ctx.channel().id().asShortText());
                MessageBase.Message.Builder builder = MessageBase.Message.newBuilder();
                builder.setRequestId(UUID.randomUUID().toString());
                builder.setCmd(MessageBase.Message.CommandType.HEARTBEAT_RESPONSE);
                // 心跳包不需要发送任何的消息内容
                MessageBase.Message heartbeat = builder.build();
                //发送心跳消息，并在发送失败时关闭该连接
                ctx.writeAndFlush(heartbeat).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }
}
