package com.dxp.netty.server;

import com.dxp.netty.protocol.protobuf.MessageBase;
import com.dxp.netty.service.RegisterService;
import com.dxp.netty.utils.SpringContextUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 获取到终端的消息,进行分发到不同的业务处理.
 *
 * @author carzy
 * @date 2020/7/4
 */
public class ServerDispatchHandler extends SimpleChannelInboundHandler<MessageBase.Message> {

    private final RegisterService registerService;
    private final Logger logger = LoggerFactory.getLogger(ServerDispatchHandler.class);

    private boolean registered = false;

    public ServerDispatchHandler() {
        this.registerService = SpringContextUtil.getBean(RegisterService.class);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageBase.Message message) throws Exception {
        final String id = ctx.channel().id().asShortText();
        final String requestId = message.getRequestId();
        MessageBase.Message.CommandType cmd = message.getCmd();
        logger.debug("{} - {} rec {} message", id, requestId, cmd.name());
        if (!registered && cmd != MessageBase.Message.CommandType.REGISTER_REQUEST) {
            logger.error("{} client not register, try to close channel.", id);
            ctx.close();
            return;
        }
        switch (cmd) {
            case HEARTBEAT_REQUEST:
                MessageBase.Message heartbeatResponse = MessageBase.Message.newBuilder()
                        .setCmd(MessageBase.Message.CommandType.HEARTBEAT_RESPONSE)
                        .setRequestId(message.getRequestId())
                        .build();
                ctx.writeAndFlush(heartbeatResponse);
                break;
            case REGISTER_RESPONSE:
                break;
            case REGISTER_REQUEST:
                registerService.register(message.getContent()).whenComplete((r, e) -> {
                    if (e != null) {
                        logger.error("{} - {} register err by server, try to close channel", id, requestId, e);
                        ctx.close();
                    } else {
                        if (r) {
                            MessageBase.Message registerRes = MessageBase.Message.newBuilder()
                                    .setCmd(MessageBase.Message.CommandType.REGISTER_RESPONSE)
                                    .setRequestId(message.getRequestId())
                                    .setContent("success")
                                    .build();
                            ctx.writeAndFlush(registerRes);
                            registered = true;
                        } else {
                            logger.warn("{} - {} register err by pwd, try to close channel", id, requestId);
                        }
                    }
                });
                break;
            default:
                //todo.. 未知消息处理
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("ServerDispatchHandler", cause);
        ctx.close().addListener(future -> {
            logger.info("{} close channel ", ctx.channel().id());
        });
    }
}
