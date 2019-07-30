package com.jcs.provider.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther xukh
 * @date 2019/7/27 15:23
 */
@RestController
@RequestMapping("/start")
public class StartController {

    @RequestMapping("/start")
    public String start(){
        return "start";
    }
}
