package com.jcs.opendota.mapper;


import com.jcs.opendota.model.Hero;

public interface HeroMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(Hero record);

    int insertSelective(Hero record);

    Hero selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(Hero record);

    int updateByPrimaryKey(Hero record);
}