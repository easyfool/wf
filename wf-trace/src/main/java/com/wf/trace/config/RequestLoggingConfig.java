package com.wf.trace.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

/**
 * @author wangfeng
 * @version 1.0
 * @mail wangfengbabe@163.com
 * @data 2024/9/16 7:02
 * Spring Boot 为日志有效载荷提供了内置解决方案。
 * AbstractRequestLoggingFilter 是一个提供日志记录基本功能的过滤器。
 * 子类应覆盖 beforeRequest() 和 afterRequest() 方法，
 * 以围绕请求执行实际日志记录。Spring Boot 框架提供了三个具体实现类，
 * 我们可以用它们来记录传入的请求：CommonsRequestLoggingFilter
 * Log4jNestedDiagnosticContextFilter (过时，弃用)
 * ServletContextRequestLoggingFilter
 */
@Configuration
public class RequestLoggingConfig {
    @Bean
    public CommonsRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        // 是否记录请求的查询参数信息
        filter.setIncludeQueryString(true);
        // 是否记录请求body内容
        filter.setIncludePayload(true);
        // 是否记录请求header信息
        filter.setIncludeHeaders(true);
        // 是否记录请求客户端信息
        filter.setIncludeClientInfo(true);
        // 设置日期记录的前缀
        filter.setAfterMessagePrefix("REQUEST DATA: ");
        return filter;
    }
}