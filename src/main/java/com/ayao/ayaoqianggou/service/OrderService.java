package com.ayao.ayaoqianggou.service;

import com.ayao.ayaoqianggou.error.BusinessException;
import com.ayao.ayaoqianggou.service.model.OrderModel;

public interface OrderService {
  /**
   * 创建订单
   * @param uerId
   * @param itemId
   * @param amount
   * @return
   */
  OrderModel createOrder(Integer itemId,Integer uerId, Integer promoId, Integer amount) throws BusinessException;
}
