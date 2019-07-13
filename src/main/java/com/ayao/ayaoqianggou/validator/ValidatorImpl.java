package com.ayao.ayaoqianggou.validator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author ：ayao
 * @date ：Created in 2019/7/9 10:45
 * @version:
 */@Component
public class ValidatorImpl implements InitializingBean {
  private Validator validator;

  @Override
  public void afterPropertiesSet() throws Exception {
    //将hibernate validator通过工厂的初始化方式使其实例化
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }
  //实现校验方法并返回校验结果
  public ValidationResult validationResult(Object bean){
    final  ValidationResult result = new ValidationResult();
    Set<ConstraintViolation<Object>> constraintViolationSet = validator.validate(bean);
    if (constraintViolationSet.size() > 0){
      //有错误
      result.setHasError(true);
      constraintViolationSet.forEach(objectConstraintViolation -> {
        String errMsg = objectConstraintViolation.getMessage();
        String propertyName = objectConstraintViolation.getPropertyPath().toString();
        result.getErrorMap().put(propertyName,errMsg);
      });
    }
    return result;
  }
}
