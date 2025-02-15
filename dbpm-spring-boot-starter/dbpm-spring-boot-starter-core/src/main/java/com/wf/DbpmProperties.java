package com.wf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author wangfeng
 * @version 1.0
 * @mail wangfengbabe@163.com
 * @data 2024/12/20 19:44
 */
@Data
//@ConfigurationProperties(prefix="dbpm")
public class DbpmProperties {
    private String appIp;
}
