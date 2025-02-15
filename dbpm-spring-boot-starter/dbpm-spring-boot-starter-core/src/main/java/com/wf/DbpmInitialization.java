package com.wf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.Resource;

/**
 * @author wangfeng
 * @version 1.0
 * @mail wangfengbabe@163.com
 * @data 2024/12/20 20:10
 */
public class DbpmInitialization implements ApplicationContextInitializer<ConfigurableApplicationContext> {
//    @Resource
//    private DbpmProperties dbpmProperties;
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
//        System.out.println(dbpmProperties);
        System.out.println(applicationContext.getEnvironment().getProperty("dbpm.appIp"));
        System.out.println(applicationContext.getEnvironment().getProperty("dbpm.masterServers.total"));
        System.out.println(applicationContext.getEnvironment().getProperty("dbpm.slaveServers.total"));
        System.out.println(applicationContext.getEnvironment().getProperty("dbpm.replacements.total  "));
    }
}
