package com.eric.base.factory;

import com.eric.base.constant.ConstantValue;
import com.eric.base.exception.ServerErrException;
import com.eric.server.pojo.ServerConfig;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.oio.OioServerSocketChannel;

/**
 * @Description
 * @Author eric
 * @Version V1.0.0
 * @Date 2019/4/30
 */
public class ServerBootstrapFactory {
    private ServerBootstrapFactory() {
    }

    public static ServerBootstrap createServerBootstrap() throws ServerErrException {

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        switch (ServerConfig.getInstance().getChannelType()) {
            case ConstantValue.CHANNEL_TYPE_NIO:
                EventLoopGroup bossGroup = new NioEventLoopGroup();
                EventLoopGroup workerGroup = new NioEventLoopGroup();
                serverBootstrap.group(bossGroup, workerGroup);
                serverBootstrap.channel(NioServerSocketChannel.class);

                return serverBootstrap;
            case ConstantValue.CHANNEL_TYPE_OIO:
                serverBootstrap.group(new OioEventLoopGroup());
                serverBootstrap.channel(OioServerSocketChannel.class);

                return serverBootstrap;
            default:
                throw new ServerErrException(
                        "Failed to create ServerBootstrap,  " +ServerConfig.getInstance().getChannelType() + " not supported!");
        }
    }
}

