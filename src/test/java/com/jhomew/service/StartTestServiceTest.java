package com.jhomew.service;

import ch.qos.logback.classic.Logger;
import com.jhomew.BaseTest;
import com.jhomew.entity.StartTest;
import com.jhomew.mapper.StartTestMapper;
import com.jhomew.mapper.StartTestMapperTest;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Hxin
 * @version 1.0
 * @since 2021/9/23 7:38 下午
 */
public class StartTestServiceTest extends BaseTest {
    private static Logger logger = (Logger) LoggerFactory.getLogger(StartTestMapperTest.class);
    @Autowired
    StartTestServiceImpl startTestService;
    @Test
    public void testStartTest(){
        StartTest startTest = startTestService.findStartTestByNum(1001);
        logger.debug("--------------------------");
        logger.debug(startTest.toString());
    }
}
