package com.jcs.opendota.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jcs.base.response.Response;
import com.jcs.opendota.mapper.HeroMapper;
import com.jcs.opendota.model.Hero;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @auther xukh
 * @date 2019/8/1 16:04
 */
@Service
public class OpenDotaServiceImpl implements OpenDotaService {

    @Autowired
    private HeroMapper heroMapper;

    @Override
    public String get(){
        return "open-dota";
    }

    @Override
    public Response<Hero> getHeroInfoByUuid(String uuid) {

        Hero hero = heroMapper.selectByPrimaryKey(uuid);

        return Response.createSuccess(hero);
    }

    @Override
    public Response<String> selectUuid() {

        String s = heroMapper.selectuuids("1");

        return Response.createSuccess(s);
    }

    @Override
    public Response<Integer> selectCount() {

        int i = heroMapper.selectCount();

        return Response.createSuccess(i);
    }


}
