package com.jcs.api.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.jcs.consumer.service.ConsumerService;
import com.jcs.opendota.service.OpenDotaService;
import com.jcs.provider.service.ProviderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther xukh
 * @date 2019/7/27 15:23
 */
@RestController
@RequestMapping("/start")
public class StartController {

    @Reference
    private ProviderService providerService;
    @Reference
    private OpenDotaService openDotaService;
    @Reference
    private ConsumerService consumerService;

    @RequestMapping("/provider")
    public String provider(){
        return providerService.get();
    }

    @RequestMapping("/openDota")
    public String openDota(){
        return openDotaService.get();
    }

    @RequestMapping("/consumer")
    public String consumer(){
        return consumerService.get();
    }
}
