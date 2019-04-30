package com.eric.server.pojo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description
 * @Author eric
 * @Version V1.0.0
 * @Date 2019/4/30
 */
public class ServerConfig {
    private static final Logger logger = LoggerFactory.getLogger(ServerConfig.class);

    private Integer port;
    private String channelType;
    private String protocolType;

    private static ServerConfig instance = null;

    private ServerConfig() {
    }

    public static ServerConfig getInstance() {
        if (instance == null) {
            instance = new ServerConfig();
            instance.init();
            instance.printServerInfo();
        }
        return instance;
    }

    private void init() {
        port = 8088;
        channelType = "NIO";
        protocolType = "TCP";
    }

    public void printServerInfo() {
        logger.info("**************Server INFO******************");
        logger.info("protocolType  : " + protocolType);
        logger.info("port          : " + port);
        logger.info("channelType   : " + channelType);
        logger.info("**************Server INFO******************");
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(String protocolType) {
        this.protocolType = protocolType;
    }
}
