package com.wf.validation.exception;

import com.wf.validation.enums.ErrorCode;

/**
 * @author wangfeng
 * @version 1.0
 * @mail wangfengbabe@163.com
 * @data 2024/9/15 20:28
 */
public class ApiException extends RuntimeException {
    private ErrorCode errorCode;
    private String message;

    public ApiException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.errorCode = errorCode;
    }

    public ApiException(ErrorCode errorCode,String message) {
        super(errorCode.getMsg());
        this.errorCode = errorCode;
        this.message = message;
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

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}
