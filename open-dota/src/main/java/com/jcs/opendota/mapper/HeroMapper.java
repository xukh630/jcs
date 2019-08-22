package com.jcs.opendota.mapper;

import com.jcs.opendota.model.Hero;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HeroMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(Hero record);

    int insertSelective(Hero record);

    Hero selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(Hero record);

    int updateByPrimaryKey(Hero record);

    String selectuuids(String uuid);

    int selectCount();
}