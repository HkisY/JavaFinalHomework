package com.jhomew.utils;

import com.jhomew.BaseTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

/**
 * @author Hxin
 * @version 1.0
 * @since 2021/10/15 9:57 下午
 */
public class RedisTest extends BaseTest {
    public static Logger logger = LoggerFactory.getLogger(jjwtTest.class);

    @Test
    public void redisTest(){
        //Connecting to Redis server on localhost
        Jedis jedis = new Jedis();
        System.out.println("Connection to server sucessfully");
        //check whether server is running or not
        System.out.println("Server is running: "+jedis.ping());
    }

    @Test
    public void redisStringTest(){
        //Connecting to Redis server on localhost
        Jedis jedis = new Jedis();
        System.out.println("Connection to server sucessfully");
        //set the data in redis string
        jedis.set("tutorial-name", "Redis tutorial");
        // Get the stored data and print it
        System.out.println("Stored string in redis:: "+ jedis.get("tutorial-name"));
    }

    @Test
    public void redisListTest(){
        //Connecting to Redis server on localhost
        Jedis jedis = new Jedis();
        System.out.println("Connection to server sucessfully");
        //store data in redis list
        jedis.lpush("tutorial-list", "Redis");
        jedis.lpush("tutorial-list", "Mongodb");
        jedis.lpush("tutorial-list", "Mysql");
        jedis.lpush("tutorial-list", "Hxin");

        // Get the stored data and print it
        //storage rule of lrange is a que,  parameter  start is the que head index ,stop is the que rear
        List<String> list = jedis.lrange("tutorial-list", 1 ,3);

        for(int i = 0; i<list.size(); i++) {
            System.out.println("Stored string in redis:: "+list.get(i));
        }
    }

    @Test
    public void redisKeyTest(){
        //Connecting to Redis server on localhost
        Jedis jedis = new Jedis();
        System.out.println("Connection to server sucessfully");
        //store data in redis list
        // Get the stored data and print it
        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            System.out.println(key);
        }
    }
}
