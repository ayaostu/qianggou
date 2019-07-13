package com.ayao.ayaoqianggou.service;

import com.ayao.ayaoqianggou.service.model.PromoModel;

public interface PromoService {
  //根据itemId获取即将进行的或正在进行的抢购活动
  PromoModel getPromoByItemId(Integer itemId);
}
