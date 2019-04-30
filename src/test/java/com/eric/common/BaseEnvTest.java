package com.eric.common;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description 基本环境测试
 * @Author eric
 * @Version V1.0.0
 * @Date 2019/4/30
 */
public class BaseEnvTest {

    Logger logger = LoggerFactory.getLogger(BaseEnvTest.class);

    @Test
    public void testLogger() {
        logger.info("测试打印日志");

    }


}
