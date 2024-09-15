package com.wf.domain.query;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * @author wangfeng
 * @version 1.0
 * @mail wangfengbabe@163.com
 * @data 2024/9/15 10:38
 */
@Data
public class PageQuery {
    private Long pageNo = 1L;
    private Long pageSize = 10L;
    private String orderBy;
    private Boolean asc;


}
