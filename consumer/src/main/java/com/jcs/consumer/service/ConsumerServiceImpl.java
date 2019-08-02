package com.jcs.consumer.service;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * @auther xukh
 * @date 2019/8/2 19:37
 */
@Service
public class ConsumerServiceImpl implements ConsumerService{

    @Override
    public String get() {
        return "consumer";
    }
}
