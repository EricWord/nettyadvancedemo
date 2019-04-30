package com.eric.base.factory;


import com.eric.base.constant.ConstantValue;
import com.eric.base.exception.ServerErrException;
import com.eric.server.channel.tcp.str.TcpServerStringInitializer;
import com.eric.server.pojo.ServerConfig;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @Description
 * @Author eric
 * @Version V1.0.0
 * @Date 2019/4/30
 */
public class ServerChannelFactory {
    private static final Logger logger = LoggerFactory.getLogger(ServerChannelFactory.class);

    public static Channel createAcceptorChannel() throws ServerErrException {
        Integer port = ServerConfig.getInstance().getPort();
        final ServerBootstrap serverBootstrap = ServerBootstrapFactory.createServerBootstrap();
        serverBootstrap.childHandler(getChildHandler());
        //        serverBootstrap.childHandler()
        logger.info("创建Server...");
        try {
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            channelFuture.awaitUninterruptibly();
            if (channelFuture.isSuccess()) {
                return channelFuture.channel();
            } else {
                String errMsg = "Failed to open socket! Cannot bind to port: " + port + "!";
                logger.error(errMsg);
                throw new ServerErrException(errMsg);
            }
            //      java.net.BindException: Address already in use: bind
            //接下来就是创建一个
        } catch (Exception e) {
            logger.debug(port + "is bind");
            throw new ServerErrException(e);
        }
    }

    private static ChannelInitializer<SocketChannel> getChildHandler() throws ServerErrException {

        String protocolType = ServerConfig.getInstance().getProtocolType();
        if (ConstantValue.PROTOCOL_TYPE_HTTP.equals(protocolType) || ConstantValue.PROTOCOL_TYPE_HTTPS
                .equals(protocolType)) {
        } else if (ConstantValue.PROTOCOL_TYPE_TCP.equals(protocolType)) {
            return new TcpServerStringInitializer();
        } else if (ConstantValue.PROTOCOL_TYPE_WEBSOCKET.equals(protocolType)) {
        } else if (ConstantValue.PROTOCOL_TYPE_CUSTOM.equals(protocolType)) {
        } else {
        }
        String errMsg = "undefined protocol:" + protocolType + "!";
        throw new ServerErrException(errMsg);
    }
}
