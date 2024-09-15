package com.wf.validation.domain;

import com.wf.validation.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangfeng
 * @version 1.0
 * @mail wangfengbabe@163.com
 * @data 2024/9/15 20:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> {
    private int code;
    private String message;
    private T data;

    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<>(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMsg(), data);
    }

    public static <T> CommonResponse<T> success() {
        return success(null);
    }

    public static <T> CommonResponse<T> fail(ErrorCode errorCode) {
        return new CommonResponse<>(errorCode.getCode(), errorCode.getMsg(), null);
    }

    public static <T> CommonResponse<T> fail(ErrorCode errorCode, T data) {
        return new CommonResponse<>(errorCode.getCode(), errorCode.getMsg(), data);
    }

    public static <T> CommonResponse<T> fail(ErrorCode errorCode, String message) {
        return new CommonResponse<>(errorCode.getCode(), message, null);
    }

    public static <T> CommonResponse<T> fail(ErrorCode errorCode, String message, T data) {
        return new CommonResponse<>(errorCode.getCode(), message, data);
    }

    public static <T> CommonResponse<T> fail(String message) {
        return new CommonResponse<>(ErrorCode.BUSINESS_ERROR.getCode(), message, null);
    }
}
