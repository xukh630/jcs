package com.jcs.opendota.service;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * @auther xukh
 * @date 2019/8/1 16:04
 */
@Service
public class OpenDotaServiceImpl implements OpenDotaService {

    @Override
    public String get(){
        return "open-dota";
    }
}