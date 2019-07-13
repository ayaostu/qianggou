package com.ayao.ayaoqianggou.service.impl;

import com.ayao.ayaoqianggou.dao.PromoDoMapper;
import com.ayao.ayaoqianggou.dataobject.PromoDo;
import com.ayao.ayaoqianggou.service.PromoService;
import com.ayao.ayaoqianggou.service.model.PromoModel;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author ：ayao
 * @date ：Created in 2019/7/9 13:06
 * @version:
 */
@Service
@CacheConfig(cacheNames = "order")
public class PromoServiceImpl implements PromoService {
  @Resource
  private PromoDoMapper promoDoMapper;

  @Override
  @Cacheable
  public PromoModel getPromoByItemId(Integer itemId) {
    //从数据库中获取对应商品的抢购活动信息
    PromoDo promoDo = promoDoMapper.getPromoByItemId(itemId);

    //dataObject -> model
    PromoModel promoModel = convertFromPromoDo(promoDo);

    //判断是否有抢购活动
    //没有，设置状态为0
    if (promoModel == null) {
      return null;
    }//当前时间是否是抢购活动即将开始或正在进行
    else if (promoModel.getStartTime().after(new Date())){
      //未开始
      promoModel.setPromoStatus(1);
    }else if (promoModel.getEndTime().before(new Date())){
      //已结束
      promoModel.setPromoStatus(3);
    }else {
      //进行中
      promoModel.setPromoStatus(2);
    }

    return promoModel;
  }


  private PromoModel convertFromPromoDo(PromoDo promoDo){
    if (promoDo == null) {
      return null;
    }
    PromoModel promoModel = new PromoModel();
    BeanUtils.copyProperties(promoDo,promoModel);
    return promoModel;
  }
}
