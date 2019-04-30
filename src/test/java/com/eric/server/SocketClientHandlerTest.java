package com.eric.server;

import com.eric.client.SocketClientTest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @Description
 * @Author eric
 * @Version V1.0.0
 * @Date 2019/4/30
 */
public class SocketClientHandlerTest extends SimpleChannelInboundHandler<String> {
    private static final Logger logger = LoggerFactory.getLogger(SocketClientTest.class);

    @Override
    public void exceptionCaught(ChannelHandlerContext arg0, Throwable arg1) {
        logger.info("异常发生", arg1);
    }

    @Override
    public void channelRead(ChannelHandlerContext arg0, Object msg) throws Exception {
        super.channelRead(arg0, msg);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext arg0, String data) {
        logger.info("数据内容：data=" + data);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("客户端连接建立");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("客户端连接断开");
        super.channelInactive(ctx);
    }
}

