package com.ayao.ayaoqianggou.dao;

import com.ayao.ayaoqianggou.dataobject.PromoDo;

public interface PromoDoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PromoDo record);

    int insertSelective(PromoDo record);

    PromoDo selectByPrimaryKey(Integer id);
    PromoDo getPromoByItemId(Integer itemId);


    int updateByPrimaryKeySelective(PromoDo record);

    int updateByPrimaryKey(PromoDo record);
}