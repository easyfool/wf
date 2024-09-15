package com.wf.validation.aop;

import com.wf.validation.enums.ErrorCode;
import com.wf.validation.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import javax.validation.ValidationException;

/**
 * @author wangfeng
 * @version 1.0
 * @mail wangfengbabe@163.com
 * @data 2024/9/15 20:10
 */
@Slf4j
@Aspect
@Component
public class BindingResultAspect {

    @Pointcut(value = "execution(* com.wf.validation.controller.*.*(..))")
    public void bindingResult() {

    }

    @Around(value = "bindingResult()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof BindingResult) {
                BindingResult bindingResult = (BindingResult) arg;
                if (bindingResult.hasErrors()) {
                    throw new ApiException(ErrorCode.PARAMETER_ERROR, bindingResult.getAllErrors().get(0).getDefaultMessage());
                }
            }
        }
        return joinPoint.proceed();
    }
}
