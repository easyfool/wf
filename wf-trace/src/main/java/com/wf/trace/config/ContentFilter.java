package com.wf.trace.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;

/**
 * @author wangfeng
 * @version 1.0
 * @mail wangfengbabe@163.com
 * @data 2024/9/16 7:08
 */
@Slf4j
@Component
public class ContentFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        filterChain.doFilter(requestWrapper, responseWrapper);
        byte[] responseBody = responseWrapper.getContentAsByteArray();
        log.info("RESPONSE DATA: {}", new String(responseBody));
//        MessageDigest md5Digest = MessageDigest.getInstance("MD5");
//        byte[] md5Hash = md5Digest.digest(responseBody);
//        String md5HashString = DatatypeConverter.printHexBinary(md5Hash);
//        responseWrapper.setHeader("X-API-SIGN", md5HashString);// 必须调用，否则响应无法输出到客户端
        responseWrapper.copyBodyToResponse();
    }
}