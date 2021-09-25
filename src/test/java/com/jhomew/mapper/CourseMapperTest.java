package com.jhomew.mapper;

import ch.qos.logback.classic.Logger;
import com.jhomew.BaseTest;
import com.jhomew.entity.Course;
import com.jhomew.entity.StartTest;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Hxin
 * @version 1.0
 * @since 2021/9/25 5:20 下午
 */
public class CourseMapperTest extends BaseTest {
    private static Logger logger = (Logger) LoggerFactory.getLogger(CourseMapperTest.class);

    @Autowired
    CourseMapper courseMapper;
    @Test
    public void testStartTest(){
        Course course = courseMapper.selectById(1);
        logger.debug("--------------------------");
        logger.debug(course.toString());
    }
}
