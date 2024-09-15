package com.wf.trace.controller;

import cn.hutool.core.bean.BeanUtil;
import com.wf.trace.dto.MyRequest;
import com.wf.trace.dto.MyResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangfeng
 * @version 1.0
 * @mail wangfengbabe@163.com
 * @data 2024/9/16 7:12
 */
@Api("全链路日志测试")
@Slf4j
@RestController
public class HelloController {

    @ApiOperation("hello")
    @PostMapping("/hello")
    public MyResponse hello(@RequestBody MyRequest myRequest) {
        return BeanUtil.copyProperties(myRequest, MyResponse.class);

    }
}
