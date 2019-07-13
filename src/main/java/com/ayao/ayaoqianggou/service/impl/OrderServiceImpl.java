package com.ayao.ayaoqianggou.service.impl;

import com.ayao.ayaoqianggou.dao.OrderDoMapper;
import com.ayao.ayaoqianggou.dao.SequenceDoMapper;
import com.ayao.ayaoqianggou.dataobject.OrderDo;
import com.ayao.ayaoqianggou.dataobject.SequenceDo;
import com.ayao.ayaoqianggou.error.BusinessException;
import com.ayao.ayaoqianggou.error.EmBusinessError;
import com.ayao.ayaoqianggou.service.ItemService;
import com.ayao.ayaoqianggou.service.OrderService;
import com.ayao.ayaoqianggou.service.UserService;
import com.ayao.ayaoqianggou.service.model.ItemModel;
import com.ayao.ayaoqianggou.service.model.OrderModel;
import com.ayao.ayaoqianggou.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author ：ayao
 * @date ：Created in 2019/7/6 20:39
 * @version:
 */
@Service
@CacheConfig(cacheNames = "order")
public class OrderServiceImpl implements OrderService {
  @Resource
  private UserService userService;
  @Resource
  private ItemService itemService;
  @Resource
  private SequenceDoMapper sequenceDoMapper;
  @Resource
  private OrderDoMapper orderDoMapper;

  @Override
  @Transactional
  @CachePut
  public OrderModel createOrder(Integer itemId, Integer uerId, Integer promoId,Integer amount) throws BusinessException {
    //校验入参
    UserModel userModel = userService.selectUserById(uerId);
    if (userModel == null) {
      throw new BusinessException(EmBusinessError.USER_NOT_LOGIN);
    }
    ItemModel itemModel = itemService.getItemById(itemId);
    if (itemModel == null) {
      throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"商品信息不存在");
    }

    if (amount <=0 || amount > 99){
      throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"商品数量信息不正确");
    }
    //校验活动信息
    if (promoId != null){
      //检验对应活动是否存在这个商品
      if (promoId.intValue() != itemModel.getPromoModel().getId()){
        throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"活动信息不正确");
      }//2.校验活动是否正在进行中
      else if (itemModel.getPromoModel().getPromoStatus() != 2){
        throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"活动信息不正确");
      }
    }
    //落单减库存
    boolean result = itemService.decreaseStock(itemId,amount);
    if (!result){
      throw new BusinessException(EmBusinessError.STOCK_NOT_ENOUGH);
    }


    //订单入库
    OrderModel orderModel = new OrderModel();
    orderModel.setItemId(itemModel.getId());
    orderModel.setAmount(amount);
    orderModel.setUserId(userModel.getId());
    orderModel.setPromoId(promoId);
    if (promoId != null){
      orderModel.setItemPrice(itemModel.getPromoModel().getPromoItemPrice());
    }else {
      orderModel.setItemPrice(itemModel.getPrice());
    }
    orderModel.setOrderPrice(orderModel.getItemPrice().multiply(new BigDecimal(amount)));

    //生成订单号
    orderModel.setId(generateOrder());


    OrderDo orderDo = convertFromOrderModel(orderModel);
    //写入数据库
    orderDoMapper.insertSelective(orderDo);

    //加上商品的销量
    itemService.increaseSales(itemModel.getId(),amount);
    //返回前端
    return orderModel;
  }
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public String generateOrder(){
    //生成16为订单号
    StringBuilder sb = new StringBuilder(16);
    //前8位位时间信息，年月日
    LocalDateTime now = LocalDateTime.now();
    String nowDate = now.format(DateTimeFormatter.ISO_DATE).replace("-","");
    sb.append(nowDate);

    //中间6位为自增序列
    //获取当前sequence
    int sequence = 0;
    SequenceDo sequenceDo = sequenceDoMapper.getSequenceByName("order_info");
    sequence = sequenceDo.getCurrentValue();
    sequenceDo.setCurrentValue(sequenceDo.getCurrentValue() + sequenceDo.getStep());
    sequenceDoMapper.updateByPrimaryKeySelective(sequenceDo);
    String sequenceStr = String.valueOf(sequence);
    //若序列号不足六位补0
    for (int i = 0; i < 6 - sequenceStr.length(); i++) {
      sb.append(0);
    }
    sb.append(sequenceStr);

    //最后两位为分库分表位,这里锁定
    sb.append("00");
    return sb.toString();
  }
  private OrderDo convertFromOrderModel(OrderModel orderModel){
    OrderDo orderDo = new OrderDo();
    BeanUtils.copyProperties(orderModel,orderDo);
    orderDo.setItemPrice(orderModel.getItemPrice().doubleValue());
    orderDo.setOrderPrice(orderModel.getOrderPrice().doubleValue());
    return orderDo;
  }
}
