package com.wf.file.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wangfeng
 * @version 1.0
 * @mail wangfengbabe@163.com
 * @data 2024/9/16 19:55
 */
@RequestMapping("file2")
@Controller
public class FileController2 {

    @GetMapping("/fileupload-page")
    public String getUploadPage(){
        return "fileupload";
    }
}
