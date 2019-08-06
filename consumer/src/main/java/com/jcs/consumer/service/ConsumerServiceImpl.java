package com.jcs.consumer.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.jcs.provider.service.ProviderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @auther xukh
 * @date 2019/8/2 19:37
 */
@Service
public class ConsumerServiceImpl implements ConsumerService{

    private static final Logger log = LoggerFactory.getLogger(ConsumerServiceImpl.class);

    @Reference
    private ProviderService providerService;

    @Override
    public String get() {
        return "consumer";
    }

    @Override
    public String getProvider() {
        return providerService.get();
    }


}
