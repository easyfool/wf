package com.wf.cloud.common.domain;

import com.wf.cloud.common.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> {

    private Integer code;
    private String msg;
    private T data;

    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<T>(ErrorCode.SUCCESS.getCode(), "success", data);
    }

    public static <T> CommonResponse<T> success() {
        return new CommonResponse<T>(ErrorCode.SUCCESS.getCode(), "success", null);
    }

    public static <T> CommonResponse<T> fail(ErrorCode errorCode, String msg) {
        return new CommonResponse<T>(errorCode.getCode(), msg, null);
    }

    public static <T> CommonResponse<T> fail(ErrorCode errorCode, T data) {
        return new CommonResponse<T>(errorCode.getCode(), errorCode.getMsg(), data);
    }


}
