package com.ayao.ayaoqianggou.service.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author ：ayao
 * @date ：Created in 2019/6/30 14:30
 * @version:
 */
public class UserModel implements Serializable {
  private Integer id;

  @NotBlank(message = "姓名不能为空")
  private String userName;

  @NotBlank(message = "密码不能为空")
  private String password;

  @NotNull(message = "性别不能为空")
  private Integer gender;

  @NotNull(message = "年龄不能为空")
  @Min(value = 0,message = "年龄必须大于0")
  @Max(value = 150,message = "年龄必须小于150")
  private Integer age;

  @NotBlank(message = "手机号不能为空")
  private String telphone;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Integer getGender() {
    return gender;
  }

  public void setGender(Integer gender) {
    this.gender = gender;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getTelphone() {
    return telphone;
  }

  public void setTelphone(String telphone) {
    this.telphone = telphone;
  }

  @Override
  public String toString() {
    return "UserModel{" +
        "id=" + id +
        ", userName='" + userName + '\'' +
        ", password='" + password + '\'' +
        ", gender=" + gender +
        ", age=" + age +
        ", telphone='" + telphone + '\'' +
        '}';
  }
}
