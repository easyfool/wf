package com.wf.validation.controller;

import com.wf.validation.domain.CommonResponse;
import com.wf.validation.domain.ValidErrorInfo;
import com.wf.validation.enums.ErrorCode;
import com.wf.validation.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangfeng
 * @version 1.0
 * @mail wangfengbabe@163.com
 * @data 2024/9/15 20:18
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ApiException.class)
    @ResponseBody
    public CommonResponse<String> apiException(ApiException e) {
        return CommonResponse.fail(e.getErrorCode(), e.getMessage());
    }

    /**
     * 参数校验异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)  // 指定要处理的异常
    public CommonResponse<List<ValidErrorInfo>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        // 封装异常结果
        List<ValidErrorInfo> errs = new ArrayList<>();
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        fieldErrors.forEach(err -> {
            ValidErrorInfo validErrorInfo = new ValidErrorInfo();
            validErrorInfo.setField(err.getField());
            validErrorInfo.setMessage(err.getDefaultMessage());
            errs.add(validErrorInfo);
        });
        return CommonResponse.fail(ErrorCode.PARAMETER_ERROR, errs);
    }


}
