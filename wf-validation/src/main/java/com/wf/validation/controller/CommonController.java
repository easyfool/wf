package com.wf.validation.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.wf.validation.domain.QueryCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangfeng
 * @version 1.0
 * @mail wangfengbabe@163.com
 * @data 2024/9/15 19:09
 */
@RequestMapping("/common")
@RestController
@Api(tags = "01 通用模块")
public class CommonController {

    @ApiOperation("根据条件查询数据")
    @ApiOperationSupport(order = 2)
    @PostMapping("/queryByCondition")
    public String queryByCondition(@RequestBody QueryCondition queryCondition) {
        return "this is common demo";
    }


    @ApiOperation("保存")
    @ApiOperationSupport(order = 3)
    @PostMapping("/add")
    public String add(@RequestBody QueryCondition queryCondition) {
        return "this is add";
    }


    @ApiOperation("更新")
    @ApiOperationSupport(order = 1)
    @PostMapping("/update")
    public String update(@RequestBody QueryCondition queryCondition) {
        return "this is update";
    }


    @ApiOperation("根据主键ID查询")
    @ApiOperationSupport(order = 4)
    @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "Long", paramType = "path")
    @PostMapping("/queryById/{id}")
    public String queryById(@PathVariable Long id) {
        return "this is queryById";
    }
}





