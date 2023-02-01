package com.bigdata.backstage.common.api;


/**
 * ##################################
* Description:  封装API的错误码
* author: zhj
* date: 2023/1/26 13:17
 * ##################################
*/

public interface IErrorCode {
    long getCode();

    String getMessage();
}
