package com.wf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author wangfeng
 * @version 1.0
 * @mail wangfengbabe@163.com
 * @data 2024/12/21 8:17
 */
public class DbpmEnvironmentPostProcessor implements EnvironmentPostProcessor {
    private final String PROPERTY_SOURCE_NAME = "DBPM_PROPERTY";
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        System.out.println(environment.getProperty("dbpm.servers"));
        System.out.println(environment.getProperty("dbpm.items"));
    }
}
