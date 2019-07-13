package com.ayao.ayaoqianggou.service;

import com.ayao.ayaoqianggou.error.BusinessException;
import com.ayao.ayaoqianggou.service.model.ItemModel;

import java.util.List;

public interface ItemService {
  /**
   * 创建商品
   */
  ItemModel createItem(ItemModel itemModel) throws BusinessException;
  /**
   * 商品列表浏览
   */
  List<ItemModel> listItemByPage(Integer pageNum, Integer pageSize);
  /**
   * 商品详情页浏览
   */
  ItemModel getItemById(Integer id);

  /**
   * 库存扣减
   * @param itemId
   * @param amount
   * @return
   */
  boolean decreaseStock(Integer itemId,Integer amount);

  /**
   * 商品销量增加
   * @param itemId
   * @param amount
   */
  void increaseSales( Integer itemId,Integer amount);
}
