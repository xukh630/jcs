package com.jcs.api.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.jcs.provider.service.StartService;
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
    private StartService startService;

    @RequestMapping("/start")
    public String start(){
        return startService.get();
    }
}
