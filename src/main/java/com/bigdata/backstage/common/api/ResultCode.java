package com.bigdata.backstage.common.api;


/**
 * ##################################
* Description: 枚举了一些常用API操作码
* author: zhj
* date: 2023/1/26 13:17
 * ##################################
*/

public enum ResultCode implements IErrorCode {
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),

    VALIDATE_RESULT_CODE(402, "资源已经存在"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限");
    private long code;
    private String message;

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
