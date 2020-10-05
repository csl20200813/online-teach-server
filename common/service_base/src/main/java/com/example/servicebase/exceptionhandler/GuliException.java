package com.example.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: csl
 * @description: 自定义异常类
 * @date: 2020-09-29 22:52
 */
@Data
@AllArgsConstructor   //生成有参构造方法
@NoArgsConstructor  //生成无参构造
public class GuliException extends RuntimeException {


    private Integer code; //状态码
    private String msg;//异常信息




}