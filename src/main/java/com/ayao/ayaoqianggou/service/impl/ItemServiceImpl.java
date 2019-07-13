package com.ayao.ayaoqianggou.service.impl;

import com.ayao.ayaoqianggou.dao.ItemDoMapper;
import com.ayao.ayaoqianggou.dao.ItemStockDoMapper;
import com.ayao.ayaoqianggou.dataobject.ItemDo;
import com.ayao.ayaoqianggou.dataobject.ItemStockDo;
import com.ayao.ayaoqianggou.error.BusinessException;
import com.ayao.ayaoqianggou.error.EmBusinessError;
import com.ayao.ayaoqianggou.page.PageInfo;
import com.ayao.ayaoqianggou.service.ItemService;
import com.ayao.ayaoqianggou.service.PromoService;
import com.ayao.ayaoqianggou.service.model.ItemModel;
import com.ayao.ayaoqianggou.service.model.PromoModel;
import com.ayao.ayaoqianggou.validator.ValidationResult;
import com.ayao.ayaoqianggou.validator.ValidatorImpl;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ：ayao
 * @date ：Created in 2019/7/6 14:07
 * @version:
 */
@Service
@CacheConfig(cacheNames = "item")
public class ItemServiceImpl implements ItemService {
  @Resource
  private ItemDoMapper itemDoMapper;
  @Resource
  private ItemStockDoMapper itemStockDoMapper;
  @Resource
  private ValidatorImpl validator;
  @Resource
  private PromoService promoService;

  @Override
  @CachePut
  @Transactional
  public ItemModel createItem(ItemModel itemModel) throws BusinessException {
    //入参校验
    ValidationResult result = validator.validationResult(itemModel);
    if (result.isHasError()){
      throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,result.getErrorMsg());
    }

    //model->dataObject
    ItemDo itemDo = converFromItemModel(itemModel);
    //写入数据库
    itemDoMapper.insert(itemDo);
    //获取itemStock

    //model->dataObject
    itemModel.setId(itemDo.getId());
    ItemStockDo itemStockDo = converFromItemStockModel(itemModel);

    //写入数据库
    itemStockDoMapper.insert(itemStockDo);
    //返回创建完成对象
    return getItemById(itemModel.getId());
  }

  private ItemDo converFromItemModel(ItemModel itemModel){
    if (itemModel == null) {
      return null;
    }
    ItemDo itemDo = new ItemDo();
    BeanUtils.copyProperties(itemModel,itemDo);
    itemDo.setPrice(itemModel.getPrice().doubleValue());
    return itemDo;
  }

  private ItemStockDo converFromItemStockModel(ItemModel itemModel){
    if (itemModel == null) {
      return null;
    }
    ItemStockDo itemStockDo = new ItemStockDo();
    itemStockDo.setItemId(itemModel.getId());
    itemStockDo.setStock(itemModel.getStock());
    return itemStockDo;
  }

  @Override
  @Cacheable
  public List<ItemModel> listItemByPage(Integer pageNum, Integer pageSize) {
    PageHelper.startPage(pageNum,pageSize);
    //从数据库中获取信息
    List<ItemDo> itemDoList = itemDoMapper.listItem();
    List<ItemModel> itemModelList = itemDoList.stream().map(itemDo -> {
      ItemStockDo itemStockDo = itemStockDoMapper.selectByItemId(itemDo.getId());
      ItemModel itemModel = converFromDataObject(itemDo,itemStockDo);
      return itemModel;
    }).collect(Collectors.toList());
    PageInfo<ItemModel> pageInfo = new PageInfo<>(pageNum,pageSize);
    pageInfo.setList(itemModelList);
    return pageInfo.getList();
  }

  @Override
  @Cacheable(value = "getitem")
  public ItemModel getItemById(Integer id) {
    ItemDo itemDo = itemDoMapper.getItemById(id);
    if (itemDo == null) {
      return null;
    }
    //获得库存数量
    ItemStockDo itemStockDo = itemStockDoMapper.selectByItemId(itemDo.getId());
    //dataObject->model
    ItemModel itemModel = converFromDataObject(itemDo,itemStockDo);

    //获得活动商品信息
    PromoModel promoModel = promoService.getPromoByItemId(itemModel.getId());
    if (promoModel == null) {
      return converFromDataObject(itemDo,itemStockDo);
    }
    if (promoModel.getPromoStatus() != 0 && promoModel.getPromoStatus() != 3){
      itemModel.setPromoModel(promoModel);
    }
    return itemModel;
  }
  private ItemModel converFromDataObject(ItemDo itemDo,ItemStockDo itemStockDo){
    ItemModel itemModel = new ItemModel();
    BeanUtils.copyProperties(itemDo,itemModel);
    itemModel.setPrice(new BigDecimal(itemDo.getPrice()));
    itemModel.setStock(itemStockDo.getStock());
    return itemModel;
  }

  @Override
  @Transactional
  @CachePut
  public boolean decreaseStock(Integer itemId, Integer amount) {
    int affectedRow = itemStockDoMapper.decreaseStock(itemId,amount);
    //更新成功
    return affectedRow > 0;
  }

  @Override
  @Transactional
  @CachePut
  public void increaseSales(Integer itemId, Integer amount) {
  itemDoMapper.increaseSales(itemId,amount);
  }
}
