package com.jcs.opendota.service;

import com.jcs.base.response.Response;
import com.jcs.opendota.model.Hero;


/**
 * @auther xukh
 * @date 2019/8/1 16:24
 */
public interface OpenDotaService {

    String get();

    Response<Hero> getHeroInfoByUuid(String uuid);

    Response<String> selectUuid();

    Response<Integer> selectCount();
}
