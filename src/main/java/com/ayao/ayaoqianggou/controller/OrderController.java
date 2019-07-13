package com.ayao.ayaoqianggou.controller;

import com.ayao.ayaoqianggou.error.BusinessException;
import com.ayao.ayaoqianggou.error.EmBusinessError;
import com.ayao.ayaoqianggou.response.CommonReturnType;
import com.ayao.ayaoqianggou.service.OrderService;
import com.ayao.ayaoqianggou.service.model.OrderModel;
import com.ayao.ayaoqianggou.service.model.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
/**
 * @author ：ayao
 * @date ：Created in 2019/7/6 21:55
 * @version:
 */
@Controller
@RequestMapping("/order")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class OrderController extends BaseController{
  @Resource
  private HttpServletRequest httpServletRequest;




  @Resource
  private OrderService orderService;

  @PostMapping(value = "/createorder",consumes = CONTENT_TYPE_FORMED)
  @ResponseBody
  public CommonReturnType createOrder(@RequestParam("itemId") Integer itemId,
                                      @RequestParam(name = "amount") Integer amount,
                                      @RequestParam(name = "promoId",required = false) Integer promoId) throws BusinessException {
    //校验用户是否登录
    Boolean isLogin = (Boolean) httpServletRequest.getSession().getAttribute("IS_LOGIN");
    if (isLogin == null) {
      throw new BusinessException(EmBusinessError.USER_NOT_LOGIN);
    }
    //获取用户信息
    UserModel userModel = (UserModel) httpServletRequest.getSession().getAttribute("LOGIN_USER");
    OrderModel orderModel = orderService.createOrder(itemId, userModel.getId(),promoId, amount);
    return CommonReturnType.create(orderModel);
  }
}
