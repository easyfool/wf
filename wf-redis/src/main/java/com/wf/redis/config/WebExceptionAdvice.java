package com.wf.redis.config;

import com.wf.redis.domain.dto.CommonResponse;
import com.wf.redis.enums.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author wangfeng
 * @version 1.0
 * @mail wangfengbabe@163.com
 * @data 2024/10/7 18:41
 */
@Slf4j
@RestControllerAdvice
public class WebExceptionAdvice {
    @ExceptionHandler(RuntimeException.class)
    public CommonResponse handleException(Exception e) {
        log.error(e.getMessage(), e);
        return CommonResponse.fail(ErrorCode.BUSINESS_ERROR, e.getMessage());
    }
}
