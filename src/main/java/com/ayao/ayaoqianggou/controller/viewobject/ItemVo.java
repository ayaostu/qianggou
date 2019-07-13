package com.ayao.ayaoqianggou.controller.viewobject;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class ItemVo {

  //商品的描述
  private String description;
  private Integer id;
  //商品描述图片的url
  private String imgUrl;
  //商品价格
  private BigDecimal price;
  //抢购活动id
  private Integer promoId;
  //抢购活动价格
  private BigDecimal promoItemPrice;
  //抢购活动开始时间
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  private Date promoStartTime;
  //记录商品是否在抢购活动中，以及对应的状态0：表示没有抢购活动，1表示抢购活动待开始，2表示抢购活动正在开始
  private Integer promoStatus;
  //商品的销量
  private Integer sales;
  //商品库存
  private Integer stock;
  //商品名称
  private String title;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getImgUrl() {
    return imgUrl;
  }

  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Integer getPromoId() {
    return promoId;
  }

  public void setPromoId(Integer promoId) {
    this.promoId = promoId;
  }

  public BigDecimal getPromoItemPrice() {
    return promoItemPrice;
  }

  public void setPromoItemPrice(BigDecimal promoItemPrice) {
    this.promoItemPrice = promoItemPrice;
  }

  public Date getPromoStartTime() {
    return promoStartTime;
  }

  public void setPromoStartTime(Date promoStartTime) {
    this.promoStartTime = promoStartTime;
  }

  public Integer getPromoStatus() {
    return promoStatus;
  }

  public void setPromoStatus(Integer promoStatus) {
    this.promoStatus = promoStatus;
  }

  public Integer getSales() {
    return sales;
  }

  public void setSales(Integer sales) {
    this.sales = sales;
  }

  public Integer getStock() {
    return stock;
  }

  public void setStock(Integer stock) {
    this.stock = stock;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


}
