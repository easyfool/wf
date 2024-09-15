package com.wf.validation.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wangfeng
 * @version 1.0
 * @mail wangfengbabe@163.com
 * @data 2024/9/15 19:08
 */

@ApiModel("通用查询条件实体")
@Data
public class QueryCondition {

    @ApiModelProperty(value = "页码", required = true, example = "1")
    private Integer pageSize;

    @ApiModelProperty(value = "每页显示数量", required = true, example = "20")
    private Integer pageNum;

    @ApiModelProperty(value = "主键ID", required = false, example = "1", dataType = "Long")
    private Long id;

    @ApiModelProperty(value = "名称", required = false, example = "张三")
    private String name;
}
