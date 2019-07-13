package com.ayao.ayaoqianggou.controller;

import com.ayao.ayaoqianggou.controller.viewobject.UserVo;
import com.ayao.ayaoqianggou.error.BusinessException;
import com.ayao.ayaoqianggou.error.EmBusinessError;
import com.ayao.ayaoqianggou.response.CommonReturnType;
import com.ayao.ayaoqianggou.service.UserService;
import com.ayao.ayaoqianggou.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author ：ayao
 * @date ：Created in 2019/6/30 14:44
 * @version:
 */
@Controller
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class UserController extends BaseController{

  @Resource
  private HttpServletRequest httpServletRequest;

  @Resource
  private UserService userService;

  /**
   * 分页查询所有
   * @param pageNum
   * @param pageSize
   * @return
   */
  @ResponseBody
  @GetMapping("/list")
  public CommonReturnType findByPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                 @RequestParam(defaultValue = "4") Integer pageSize) {
    List<UserModel> userModelList = userService.queryAllByPage(pageNum, pageSize);
    List<UserVo> userVoList = userModelList.stream().map(userModel -> {
      UserVo userVo = convertFromUserModel(userModel);
      return userVo;
    }).collect(Collectors.toList());
    return CommonReturnType.create(userVoList);
  }
  //用户登录接口
  @PostMapping(value = "/login",consumes = {CONTENT_TYPE_FORMED})
  @ResponseBody
  public CommonReturnType login(@RequestParam("telphone") String telphone,
                                @RequestParam("password") String password) throws BusinessException {
    //入参校验
    if (org.apache.commons.lang3.StringUtils.isEmpty(telphone)||
    StringUtils.isEmpty(password)){
      throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
    }
    //登录服务，校验用户登录是否合法
    UserModel userModel = userService.login(telphone,md5(password));
    //将用户登录凭证加入到登录成功的session中
    httpServletRequest.getSession().setAttribute("IS_LOGIN",true);
    httpServletRequest.getSession().setAttribute("LOGIN_USER",userModel);
    return CommonReturnType.create(null);
  }

  @PostMapping(value = "/register",consumes = CONTENT_TYPE_FORMED)
  @ResponseBody
  public CommonReturnType register(@RequestParam("userName") String userName,
                                   @RequestParam("telphone") String telphone,
                                   @RequestParam("age") Integer age,
                                   @RequestParam("gender") Integer gender,
                                   @RequestParam("password") String password,
                                   @RequestParam("otpCode") String otpCode) throws BusinessException {
    //验证手机号和相对应的otpcode是否相符
    String inOtpCode = (String) this.httpServletRequest.getSession().getAttribute(telphone);
    if (!com.alibaba.druid.util.StringUtils.equals(otpCode,inOtpCode)){
      throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"短信验证不符");
    }
    //用户注册流程
    UserModel userModel = new UserModel();
    userModel.setUserName(userName);
    userModel.setPassword(md5(password));
    userModel.setAge(age);
    userModel.setGender(gender);
    userModel.setTelphone(telphone);
    userService.register(userModel);
    return CommonReturnType.create(null);
  }
  /**
   * 加盐md5加密密码
   * @param password
   * @return
   */
  private String md5(String password) {
    String salt = "sdfjoewi234@#$s';ldsfsg9";
    String base = password +"/"+ salt;
    return DigestUtils.md5DigestAsHex(base.getBytes());
  }

  @PostMapping("/getotp")
  @ResponseBody
  public CommonReturnType getOtp(@RequestParam("telphone") String telphone){
    //按照一定规则生成otp验证码
    Random random = new Random();
    int randomInt = random.nextInt(99999);
    randomInt +=10000;
    String otpCode = String.valueOf(randomInt);
    //将otp验证码同对应的用户手机关联，
   this.httpServletRequest.getSession().setAttribute(telphone,otpCode);

    //将otp验证码通过短信通道发送给用户，省略
    System.out.println("telphone"+telphone+"otpCode"+otpCode);
    return CommonReturnType.create(null);
  }
  @RequestMapping("/get")
  @ResponseBody
  public CommonReturnType getUser(@RequestParam("id") Integer id) throws BusinessException {
    UserModel userModel = userService.selectUserById(id);
    if (userModel == null) {
      throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
    }
    UserVo userVo = convertFromUserModel(userModel);
    return CommonReturnType.create(userVo);
  }
  private UserVo convertFromUserModel(UserModel userModel){
    if (userModel == null) {
      return null;
    }
    UserVo userVo = new UserVo();
    BeanUtils.copyProperties(userModel,userVo);
    return userVo;
  }

}
