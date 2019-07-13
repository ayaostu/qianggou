package com.ayao.ayaoqianggou.dao;

import com.ayao.ayaoqianggou.dataobject.UserDo;

import java.util.List;

public interface UserDoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserDo record);

    int insertSelective(UserDo record);

    UserDo selectByPrimaryKey(Integer id);
    UserDo selectByTelphone(String telphone);
    List<UserDo> queryAllByPage();

    int updateByPrimaryKeySelective(UserDo record);

    int updateByPrimaryKey(UserDo record);
}