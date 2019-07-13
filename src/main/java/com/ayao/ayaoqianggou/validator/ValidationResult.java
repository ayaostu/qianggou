package com.ayao.ayaoqianggou.validator;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：ayao
 * @date ：Created in 2019/7/9 10:38
 * @version:
 */
public class ValidationResult {
  //存放错误信息的map
  private Map<String,String> errMsgMap = new HashMap<>();

  //校验结果是否有错，默认没有
  private boolean hasError = false;

  //实现通用的 通过格式化字符串信息获取错误结果的msg方法
  public String getErrorMsg(){
    return StringUtils.join(errMsgMap.values().toArray(),",");
  }

  public Map<String, String> getErrorMap() {
    return errMsgMap;
  }

  public void setErrorMap(Map<String, String> errMsgMap) {
    this.errMsgMap = errMsgMap;
  }

  public boolean isHasError() {
    return hasError;
  }

  public void setHasError(boolean hasError) {
    this.hasError = hasError;
  }
}
