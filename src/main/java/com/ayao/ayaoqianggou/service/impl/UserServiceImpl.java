package com.ayao.ayaoqianggou.service.impl;

import com.ayao.ayaoqianggou.dao.UserDoMapper;
import com.ayao.ayaoqianggou.dataobject.UserDo;
import com.ayao.ayaoqianggou.error.BusinessException;
import com.ayao.ayaoqianggou.error.EmBusinessError;
import com.ayao.ayaoqianggou.page.PageInfo;
import com.ayao.ayaoqianggou.service.UserService;
import com.ayao.ayaoqianggou.service.model.UserModel;
import com.ayao.ayaoqianggou.validator.ValidationResult;
import com.ayao.ayaoqianggou.validator.ValidatorImpl;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ：ayao
 * @date ：Created in 2019/6/30 14:32
 * @version:
 */
@Service
@CacheConfig(cacheNames = "user")
public class UserServiceImpl implements UserService {

  @Resource
  private UserDoMapper userDoMapper;
  @Resource
  private ValidatorImpl validator;

  @Override
  @Cacheable
  public UserModel selectUserById(Integer id) {
    //获取数据库中的用户信息
    UserDo userDo = userDoMapper.selectByPrimaryKey(id);
    if (userDo == null) {
      return null;
    }
    return converFromUserDo(userDo);

  }

  @Override
  @Cacheable
  public List<UserModel> queryAllByPage(Integer pageNum, Integer pageSize) {
    PageHelper.startPage(pageNum,pageSize);
    //从数据库中获取信息
    List<UserDo> userDoList = userDoMapper.queryAllByPage();
    List<UserModel> userModelList = userDoList.stream().map(this::converFromUserDo).collect(Collectors.toList());
    //获取分页信息，使用PageInfo对象获取，我们使用PageInfo的目的便是把List强转成Page对象，从而得到分页结果
    PageInfo<UserModel> pageInfo = new PageInfo<>(pageNum,pageSize);
    pageInfo.setList(userModelList);
    return pageInfo.getList();
  }

  @Override
  @CachePut
  public int register(UserModel userModel) throws BusinessException {
    if (userModel == null) {
      throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
    }

//       if (StringUtils.isEmpty(userModel.getUserName())
//                || userModel.getGender() == null
//                || userModel.getAge() == null
//                || StringUtils.isEmpty(userModel.getTelphone())) {
//            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
//        }
    //使用validator校验
    ValidationResult result = validator.validationResult(userModel);
    if (result.isHasError()){
      throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,result.getErrorMsg());
    }

    //model-->do
    UserDo userDo = convertFromUserModel(userModel);
    return userDoMapper.insertSelective(userDo);
  }

  @Override
  @CachePut
  public UserModel login(String telphone, String password) throws BusinessException {
    //通过手机号获取用户信息
    UserDo userDo = userDoMapper.selectByTelphone(telphone);
    if (userDo == null) {
      throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
    }
    //do-->model
    UserModel userModel = converFromUserDo(userDo);
    //比对用户信息内的密码和传输进来的密码是否匹配
    if (!password.equals(userModel.getPassword())){
      throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
    }
    return userModel;
  }
  private UserModel converFromUserDo(UserDo userDo){
    if (userDo == null) {
      return null;
    }
    UserModel userModel = new UserModel();
    BeanUtils.copyProperties(userDo,userModel);
    return userModel;
  }

  private UserDo convertFromUserModel(UserModel userModel){
    if (userModel == null) {
      return null;
    }
    UserDo userDo = new UserDo();
    BeanUtils.copyProperties(userModel,userDo);
    return userDo;
  }
}
