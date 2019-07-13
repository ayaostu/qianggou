package com.ayao.ayaoqianggou.dao;

import com.ayao.ayaoqianggou.dataobject.ItemStockDo;

public interface ItemStockDoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ItemStockDo record);

    int insertSelective(ItemStockDo record);

    ItemStockDo selectByPrimaryKey(Integer id);
    ItemStockDo selectByItemId(Integer itemId);
    int decreaseStock(Integer itemId, Integer amount);


    int updateByPrimaryKeySelective(ItemStockDo record);

    int updateByPrimaryKey(ItemStockDo record);
}