package com.wf.validation.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;

/**
 * @author wangfeng
 * @version 1.0
 * @mail wangfengbabe@163.com
 * @data 2024/9/15 18:32
 */
@Configuration
public class HibernateValidatorConfig {
    @Bean
    public Validator validator() {
//        HibernateValidator hibernateValidator = new HibernateValidator();
//        return hibernateValidator;
        return Validation.byProvider(HibernateValidator.class)
                .configure()
                //开启快速校验（不校验所有参数，只要出现校验失败的情况直接返回，不再进行后续校验）
                .failFast(true)
                .buildValidatorFactory()
                .getValidator();
    }
}
