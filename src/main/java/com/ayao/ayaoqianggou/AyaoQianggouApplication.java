package com.ayao.ayaoqianggou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ayao.ayaoqianggou.dao")
public class AyaoQianggouApplication {

  public static void main(String[] args) {
    SpringApplication.run(AyaoQianggouApplication.class, args);
  }

}
