package com.eric.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;


/**
 * @Description 服务端
 * @Author eric
 * @Version V1.0.0
 * @Date 2019/4/30
 */
public class SocketServer {
    private static final Logger logger = LoggerFactory.getLogger(SocketServer.class);
    private static final String IP = "127.0.0.1";
    private static final int PORT = 8088;

    /**
     * 分配用于处理业务的线程组数量
     */
    private static final int BIS_GROUP_SIZE = Runtime.getRuntime().availableProcessors() * 2;
    /**
     * 每个线程组中线程的数量
     */
    private static final int WORK_GROUP_SIZE = 4;

    private static EventLoopGroup bossGroup = new NioEventLoopGroup(BIS_GROUP_SIZE);
    private static EventLoopGroup workerGroup = new NioEventLoopGroup(WORK_GROUP_SIZE);

    public void run() throws Exception {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                // 以("\n")为结尾分割的 解码器
                pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
                pipeline.addLast("decoder", new StringDecoder());
                pipeline.addLast("encoder", new StringEncoder());
                pipeline.addLast(new SocketServerHandler());
            }
        });
        bootstrap.bind(IP, PORT).sync();
        logger.info("Socket服务器已启动完成");
    }

    protected static void shutdown() {
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }

    public static void main(String[] args) throws Exception {
        logger.info("开始启动Socket服务器...");
        new SocketServer().run();
    }
}

