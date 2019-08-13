package com.jcs.api.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jcs.api.req.HeroReq;

import com.jcs.base.response.Response;
import com.jcs.opendota.service.OpenDotaService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther xukh
 * @date 2019/8/13 20:23
 */
@RestController
@RequestMapping("/opendota")
public class OpenDotaController {

    @Reference
    private OpenDotaService openDotaService;

    @PostMapping("/getHeroByUuid")
    public Response getHeroByUuid(@Validated HeroReq req){
        String uuid = req.getUuid();

        return openDotaService.getHeroInfoByUuid(uuid);
    }


}
