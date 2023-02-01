package com.bigdata.backstage.common.exception;


import com.bigdata.backstage.common.api.IErrorCode;

/**
 * ##################################
* Description: 自定义API异常
* author: zhj
* date: 2023/1/26 13:19
 * ##################################
*/

public class ApiException extends RuntimeException {
    private IErrorCode errorCode;

    public ApiException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }
}
