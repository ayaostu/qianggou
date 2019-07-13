package com.ayao.ayaoqianggou.dao;

import com.ayao.ayaoqianggou.dataobject.ItemDo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemDoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ItemDo record);

    int insertSelective(ItemDo record);

    ItemDo selectByPrimaryKey(Integer id);
    ItemDo getItemById(Integer id);
    List<ItemDo> listItem();
    void increaseSales(@Param("id") Integer itemId, @Param("amount") Integer amount);


    int updateByPrimaryKeySelective(ItemDo record);

    int updateByPrimaryKey(ItemDo record);
}