package com.wind.springsession.dao;

import com.wind.springsession.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    int insertUniqueUser(User user);

    List<User> getUserList(@Param("limit") int limit, @Param("offset") int offset);

    int importUserList(List<User> list);

    User selectByUsername(String username);

    User getUserInfoByUserId(int userId);

    List<User> getAllUsers();
}