package com.jcs.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jcs.provider.service.ProviderService;

/**
 * @auther xukh
 * @date 2019/7/27 16:03
 */
@Service
public class ProviderServiceImpl implements ProviderService {

    @Override
    public String get() {
        return "start";
    }
}
