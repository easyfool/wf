package com.wf.mp.domain.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wangfeng
 * @version 1.0
 * @mail wangfengbabe@163.com
 * @data 2024/9/21 19:31
 */
@ApiModel("UserQuery")
@Data
public class UserQuery {
    @ApiModelProperty("username")
    private String username;
    @ApiModelProperty("minBalance")
    private Integer minBalance;
    @ApiModelProperty("maxBalance")
    private Integer maxBalance;
    @ApiModelProperty("status")
    private Integer status;
}
