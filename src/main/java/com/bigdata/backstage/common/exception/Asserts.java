package com.bigdata.backstage.common.exception;


import com.bigdata.backstage.common.api.IErrorCode;


/**
 * ##################################
* Description: 断言处理类，用于抛出各种API异常
* author: zhj
* date: 2023/1/26 13:19
 * ##################################
*/

public class Asserts {
    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }
}
