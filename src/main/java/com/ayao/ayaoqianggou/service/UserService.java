package com.ayao.ayaoqianggou.service;

import com.ayao.ayaoqianggou.error.BusinessException;
import com.ayao.ayaoqianggou.service.model.UserModel;

import java.util.List;

public interface UserService {
  /**
   * 根据用用户ID查询用户信息
   * @param id
   * @return
   */
  UserModel selectUserById(Integer id);
  List<UserModel> queryAllByPage(Integer pageNum, Integer pageSize);
  int register(UserModel userModel) throws BusinessException;

  UserModel login( String telphone,String password) throws BusinessException;
}
