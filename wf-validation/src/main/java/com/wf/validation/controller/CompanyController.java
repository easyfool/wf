package com.wf.validation.controller;

import com.wf.validation.domain.QueryCondition;
import io.swagger.annotations.Api;
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
@Api(tags = "03 公司模块")
@RestController
@RequestMapping("/company")
public class CompanyController {

    @PostMapping("/queryCompany")
    public String queryCompany(@RequestBody QueryCondition queryCondition) {
        return "this is company demo";
    }
}
