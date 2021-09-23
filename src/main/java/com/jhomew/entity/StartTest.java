package com.jhomew.entity;

/**
 * @author Hxin
 * @version 1.0
 * @since 2021/9/23 7:24 下午
 */
public class StartTest {
    private Integer num;
    private String name;

    @Override
    public String toString() {
        return "StartTest{" +
                "num=" + num +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
