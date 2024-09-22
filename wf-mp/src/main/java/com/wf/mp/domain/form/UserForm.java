package com.wf.mp.domain.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author wangfeng
 * @version 1.0
 * @mail wangfengbabe@163.com
 * @data 2024/9/21 18:41
 */
@ApiModel("UserForm")
@Data
public class UserForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "姓名")
    private String username;

    @ApiModelProperty(value = "年龄")
    private Integer password;

    @ApiModelProperty(value = "邮箱")
    private String phone;

    @ApiModelProperty(value = "用户信息")
    private String info;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "余额")
    private Integer balance;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
}
