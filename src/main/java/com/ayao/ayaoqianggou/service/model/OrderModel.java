package com.ayao.ayaoqianggou.service.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ：ayao
 * @date ：Created in 2019/7/6 20:35
 * @version:
 */
public class OrderModel implements Serializable {
  private String id;

  private Integer userId;

  private Integer itemId;

  private BigDecimal itemPrice;

  private BigDecimal orderPrice;

  private Integer promoId;
  private Integer amount;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Integer getItemId() {
    return itemId;
  }

  public void setItemId(Integer itemId) {
    this.itemId = itemId;
  }

  public BigDecimal getItemPrice() {
    return itemPrice;
  }

  public void setItemPrice(BigDecimal itemPrice) {
    this.itemPrice = itemPrice;
  }

  public BigDecimal getOrderPrice() {
    return orderPrice;
  }

  public void setOrderPrice(BigDecimal orderPrice) {
    this.orderPrice = orderPrice;
  }

  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  public Integer getPromoId() {
    return promoId;
  }

  public void setPromoId(Integer promoId) {
    this.promoId = promoId;
  }

  @Override
  public String toString() {
    return "OrderModel{" +
        "id='" + id + '\'' +
        ", userId=" + userId +
        ", itemId=" + itemId +
        ", itemPrice=" + itemPrice +
        ", orderPrice=" + orderPrice +
        ", promoId=" + promoId +
        ", amount=" + amount +
        '}';
  }
}
