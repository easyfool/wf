package com.wf.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * @author wangfeng
 * @version 1.0
 * @mail wangfengbabe@163.com
 * @data 2024/9/15 10:39
 */
@Data
public class PageDTO<T> {
    private long total;
    private long pageNo;
    private long pageSize;
    private List<T> records;
}
