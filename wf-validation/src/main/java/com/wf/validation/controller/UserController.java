package com.wf.validation.controller;

import com.wf.validation.domain.CommonResponse;
import com.wf.validation.domain.LoginForm;
import com.wf.validation.domain.QueryCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangfeng
 * @version 1.0
 * @mail wangfengbabe@163.com
 * @data 2024/9/15 19:10
 */
@RestController
@RequestMapping("/user")
@Api(tags = "02 用户模块")
@Slf4j
public class UserController {

    @ApiOperation("根据条件查询数据")
    @PostMapping("/queryUser")
    public String queryUser(@RequestBody QueryCondition queryCondition) {
        return "this is user demo";
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public CommonResponse<String> login(@Validated @RequestBody LoginForm loginForm, BindingResult result) {
        return CommonResponse.success("this is user demo");
//        throw new MethodArgumentNotValidException();
    }
}
