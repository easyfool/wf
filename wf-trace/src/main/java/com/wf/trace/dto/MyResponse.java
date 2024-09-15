package com.wf.trace.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wangfeng
 * @version 1.0
 * @mail wangfengbabe@163.com
 * @data 2024/9/16 7:13
 */
@ApiModel
@Data
public class MyResponse {
    @ApiModelProperty
    private String userName;
    @ApiModelProperty
    private String password;
}