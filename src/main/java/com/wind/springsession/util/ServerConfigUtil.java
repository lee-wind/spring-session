package com.wind.springsession.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ServerConfigUtil {

    private static final Logger logger = LoggerFactory.getLogger(ServerConfigUtil.class);

    public static String protocol;

    public static String protocolSeparator;

    public static String ip;

    public static String domain;

    public static boolean allowCORS;

    public static String fileServerDir;

    public static String userAvatarDir;

    public static long userAvatarMaxSize;

    @Value("${server.protocol}")
    public void setProtocol(String protocol) {
        ServerConfigUtil.protocol = protocol;
    }

    @Value("${server.protocolSeparator}")
    public void setProtocolSeparator(String protocolSeparator) {
        ServerConfigUtil.protocolSeparator = protocolSeparator;
    }

    @Value("${server.ip}")
    public void setIp(String ip) {
        ServerConfigUtil.ip = ip;
    }

    @Value("${server.domain}")
    public void setDomain(String domain) {
        ServerConfigUtil.domain = domain;
    }


    @Value("${server.allowCORS}")
    public void setAllowCORS(boolean allowCORS) {
        ServerConfigUtil.allowCORS = allowCORS;
    }


    @Value("${server.fileServer.dir}")
    public void setFileServerDir(String fileServerDir) {
        ServerConfigUtil.fileServerDir = fileServerDir;
    }

    @Value("${server.fileServer.user.avatar.dir}")
    public void setUserAvatarDir(String userAvatarDir) {
        ServerConfigUtil.userAvatarDir = userAvatarDir;
    }

    @Value("${server.fileServer.user.avatar.maxSize}")
    public void setUserAvatarMaxSize(long userAvatarMaxSize) {
        ServerConfigUtil.userAvatarMaxSize = userAvatarMaxSize;
    }

    @PostConstruct
    public void init(){

        logger.info("protocol: {}", protocol);
        logger.info("protocolSeparator: {}", protocolSeparator);
        logger.info("ip: {}", ip);
        logger.info("domain: {}", domain);
        logger.info("allowCROS: {}", allowCORS);
        logger.info("fileServerDir: {}", fileServerDir);
        logger.info("userAvatarDir: {}", userAvatarDir);
        logger.info("userAvatarMaxSize: {}", userAvatarMaxSize);
    }
}
