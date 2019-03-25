package com.wind.springsession.dao;

import com.wind.springsession.model.UserThought;
import org.springframework.stereotype.Repository;

@Repository
public interface UserThoughtMapper {
    int deleteByPrimaryKey(Integer thoughtId);

    int insert(UserThought record);

    int insertSelective(UserThought record);

    UserThought selectByPrimaryKey(Integer thoughtId);

    int updateByPrimaryKeySelective(UserThought record);

    int updateByPrimaryKeyWithBLOBs(UserThought record);

    int updateByPrimaryKey(UserThought record);
}