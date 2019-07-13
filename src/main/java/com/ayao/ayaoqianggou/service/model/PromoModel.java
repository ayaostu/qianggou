package com.ayao.ayaoqianggou.service.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ：ayao
 * @date ：Created in 2019/7/9 13:03
 * @version:
 */
public class PromoModel {
  private Integer id;

  private String promoName;


  private BigDecimal promoItemPrice;

  private Integer itemId;

  private Date startTime;

  private Date endTime;
  //秒杀活动状态 0表示没有 ，1表示还未开始， 2表示正在进行， 3表示已经结束
  private Integer promoStatus;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getPromoName() {
    return promoName;
  }

  public void setPromoName(String promoName) {
    this.promoName = promoName;
  }


  public BigDecimal getPromoItemPrice() {
    return promoItemPrice;
  }

  public void setPromoItemPrice(BigDecimal promoItemPrice) {
    this.promoItemPrice = promoItemPrice;
  }

  public Integer getItemId() {
    return itemId;
  }

  public void setItemId(Integer itemId) {
    this.itemId = itemId;
  }

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  public Integer getPromoStatus() {
    return promoStatus;
  }

  public void setPromoStatus(Integer promoStatus) {
    this.promoStatus = promoStatus;
  }

  @Override
  public String toString() {
    return "PromoModel{" +
        "id=" + id +
        ", promoName='" + promoName + '\'' +
        ", promoItemPrice=" + promoItemPrice +
        ", itemId=" + itemId +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", promoStatus=" + promoStatus +
        '}';
  }
}
