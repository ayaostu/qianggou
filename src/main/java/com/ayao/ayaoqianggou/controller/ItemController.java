package com.ayao.ayaoqianggou.controller;

import com.ayao.ayaoqianggou.controller.viewobject.ItemVo;
import com.ayao.ayaoqianggou.error.BusinessException;
import com.ayao.ayaoqianggou.error.EmBusinessError;
import com.ayao.ayaoqianggou.response.CommonReturnType;
import com.ayao.ayaoqianggou.service.ItemService;
import com.ayao.ayaoqianggou.service.model.ItemModel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static com.ayao.ayaoqianggou.controller.BaseController.CONTENT_TYPE_FORMED;

/**
 * @author ：ayao
 * @date ：Created in 2019/7/6 14:54
 * @version:
 */
@Controller
@RequestMapping("/item")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class ItemController {
  @Resource
  private ItemService itemService;

  @ResponseBody
  @PostMapping(value = "/createitem", consumes = CONTENT_TYPE_FORMED)
  public CommonReturnType creatItem(@RequestParam(name = "title") String title,
                                    @RequestParam(name = "description") String description,
                                    @RequestParam(name = "price") BigDecimal price,
                                    @RequestParam(name = "stock") Integer stock,
                                    @RequestParam(name = "imgUrl") String imgUrl,
                                    @RequestParam(name = "sales") Integer sales) throws BusinessException {
    //封装service请求创建商品
    ItemModel itemModel = new ItemModel();
    itemModel.setTitle(title);
    itemModel.setDescription(description);
    itemModel.setPrice(price);
    itemModel.setStock(stock);
    itemModel.setImgUrl(imgUrl);
    itemModel.setSales(sales);


   ItemModel itemModelForReturn = itemService.createItem(itemModel);
   ItemVo itemVo = convertFromModel(itemModelForReturn);

    return CommonReturnType.create(itemVo);
  }
  private ItemVo convertFromModel(ItemModel itemModel){
    if (itemModel == null) {
      return null;
    }
    ItemVo itemVo = new ItemVo();
    BeanUtils.copyProperties(itemModel,itemVo);
    if (itemModel.getPromoModel() != null){
      //有正在进行或即将进行的抢购活动
      itemVo.setPromoStatus(itemModel.getPromoModel().getPromoStatus());
      itemVo.setPromoId(itemModel.getPromoModel().getId());
      itemVo.setPromoStartTime(itemModel.getPromoModel().getStartTime());
      itemVo.setPromoItemPrice(itemModel.getPromoModel().getPromoItemPrice());
    }else {
      itemVo.setPromoStatus(0);
      BeanUtils.copyProperties(itemModel,itemVo);
    }
    return itemVo;
  }


  /**
   * 获取商品详情
   * @param id
   * @return
   */
  @GetMapping("/getitem")
  @ResponseBody
  public CommonReturnType getItemById(@RequestParam("id") Integer id) throws BusinessException {
    ItemModel itemModel = itemService.getItemById(id);
    if (itemModel == null) {
      throw new BusinessException(EmBusinessError.ITEM_NOT_EXIST);
    }
    ItemVo itemVo = convertFromModel(itemModel);
    return CommonReturnType.create(itemVo);

  }

  /**
   * 获取商品列表
   * @param pageNum
   * @param pageSize
   * @return
   */
  @GetMapping("/listitem")
  @ResponseBody
  public CommonReturnType listItem(@RequestParam(defaultValue = "1") Integer pageNum,
                                  @RequestParam(defaultValue = "4") Integer pageSize){
    List<ItemModel> itemModelList = itemService.listItemByPage(pageNum,pageSize);
    List<ItemVo> itemVoList = itemModelList.stream().map(itemModel -> {
      ItemVo itemVo = convertFromModel(itemModel);
      return itemVo;
    }).collect(Collectors.toList());
    return CommonReturnType.create(itemVoList);
  }


}
