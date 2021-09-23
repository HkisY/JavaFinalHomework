package com.jhomew.service;

import com.jhomew.entity.StartTest;
import com.jhomew.mapper.StartTestMapper;
import com.jhomew.service.Interface.IStartTestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Hxin
 * @version 1.0
 * @since 2021/9/23 7:31 下午
 */
@Service
public class StartTestServiceImpl implements IStartTestService {
    @Resource
    private StartTestMapper startTestMapper;

    @Override
    public StartTest findStartTestByNum(Integer num) {
        return startTestMapper.selectStartTestByNum(num);
    }
}
