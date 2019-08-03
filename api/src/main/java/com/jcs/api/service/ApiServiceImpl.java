package com.jcs.api.service;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * @auther xukh
 * @date 2019/8/3 14:29
 */
@Service
public class ApiServiceImpl implements ApiService{


    @Override
    public String get() {
        return "api success";
    }
}
