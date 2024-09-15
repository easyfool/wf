package com.wf.trace.util;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;

/**
 * @author wangfeng
 * @version 1.0
 * @mail wangfengbabe@163.com
 * @data 2024/9/15 21:55
 */
public class TraceIdUtils {
    public static String getTraceId(String traceId) {
        if (StrUtil.isNotBlank(traceId)) {
            return traceId;
        }
        return UUID.fastUUID().toString();
    }

}
