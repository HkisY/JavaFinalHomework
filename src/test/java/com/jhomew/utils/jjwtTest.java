package com.jhomew.utils;

import com.jhomew.BaseTest;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author Hxin
 * @version 1.0
 * @since 2021/10/14 8:16 下午
 */
public class jjwtTest extends BaseTest {
    public static Logger logger = LoggerFactory.getLogger(jjwtTest.class);


    @Test
    public void testCreateJWT(){
        JwtBuilder jwtBuilder = Jwts.builder().setId("1001")
                .setSubject("老李")
                .setIssuedAt(new Date())//签发时间
                .signWith(SignatureAlgorithm.HS256,"hxinJava");//签名
        //.claim存放键值对
        String token = jwtBuilder.compact();
        logger.debug("---------------------"+token);
    }
}
