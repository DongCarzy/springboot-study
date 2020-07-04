package com.dxp.netty.handler;

import com.dxp.netty.protocol.protobuf.MessageBase;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.logging.ByteBufFormat;
import io.netty.handler.logging.LogLevel;

/**
 * 记录日志
 *
 * @author carzy
 * @date 2020/7/4
 */
@ChannelHandler.Sharable
public class LoggingHandler extends io.netty.handler.logging.LoggingHandler {

    public LoggingHandler() {
        this(LogLevel.DEBUG);
    }

    public LoggingHandler(LogLevel level) {
        super(level, ByteBufFormat.HEX_DUMP);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (this.logger.isEnabled(this.internalLevel)) {
            if (msg instanceof MessageBase.Message) {
                this.logger.log(this.internalLevel, this.format(ctx, "READ", filter((MessageBase.Message) msg)));
            } else {
                this.logger.log(this.internalLevel, this.format(ctx, "READ", msg));
            }
        }
        ctx.fireChannelRead(msg);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (this.logger.isEnabled(this.internalLevel)) {
            if (msg instanceof MessageBase.Message) {
                this.logger.log(this.internalLevel, this.format(ctx, "WRITE", filter((MessageBase.Message) msg)));
            } else {
                this.logger.log(this.internalLevel, this.format(ctx, "WRITE", msg));
            }
        }

        ctx.write(msg, promise);
    }

    private String filter(MessageBase.Message message) {
        MessageBase.Message.Builder builder = MessageBase.Message.newBuilder(message);
        MessageBase.Message.CommandType cmd = builder.getCmd();
        final String requestId = builder.getRequestId();
        //todo..  对一些body特别长的业务,不打印具体内容. 这里假设 NORMAL 业务的body请求体特别长.
        if (MessageBase.Message.CommandType.NORMAL == cmd && !this.logger.isTraceEnabled()) {
            return String.format("requestId:%s, cmd:%s", requestId, cmd.name());
        } else {
            return builder.toString();
        }
    }
}
