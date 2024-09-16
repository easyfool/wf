package com.wf.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author wangfeng
 * @version 1.0
 * @mail wangfengbabe@163.com
 * @data 2024/9/16 7:27
 */
@SpringBootApplication
@ServletComponentScan(basePackages = "com.wf.file.servlet")
public class WfFileApp {
    public static void main(String[] args) {
        SpringApplication.run(WfFileApp.class);
    }
}
