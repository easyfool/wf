package com.wf.validation.domain;

import com.wf.validation.annotation.EnumString;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author wangfeng
 * @version 1.0
 * @mail wangfengbabe@163.com
 * @data 2024/9/15 19:43
 */
@ApiModel("登录实体")
@Data
public class LoginForm {
    @ApiModelProperty(value = "用户名", required = true, example = "user_1")
    @NotBlank(message = "[username] required!")
    private String username;

    @ApiModelProperty(value = "密码", required = true, example = "123456")
    @NotBlank(message = "[password] required!")
    private String password;


    @ApiModelProperty(value = "性别")
    @EnumString(value = {"F","M"}, message="性别只允许为F或M")
    private String sex;
}
