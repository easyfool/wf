package com.wf.validation.domain;

import lombok.Data;

/**
 * @author wangfeng
 * @version 1.0
 * @mail wangfengbabe@163.com
 * @data 2024/9/15 20:51
 */
@Data
public class ValidErrorInfo {
    private String field;
    private String message;
}
