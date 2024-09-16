package com.wf.file.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author wangfeng
 * @version 1.0
 * @mail wangfengbabe@163.com
 * @data 2024/9/16 9:22
 */
@Slf4j
@Api("文件上传下载预览")
@Controller
@RequestMapping("file")
public class FileController {

    @Value("${file.upload.dir}")
    private String uploadDir;

    @ApiOperation("文件上传")
    @PostMapping("/upload")
    @ResponseBody
    public String fileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "error";
        }
        String fileName = file.getOriginalFilename();
        String suffix = FileNameUtil.getSuffix(fileName);
        String fileNameOnly = StrUtil.emptyIfNull(fileName.replace(suffix, ""));

        log.info("File upload fileName:{}, suffix:{}", fileName, suffix);

        Path path = Paths.get(uploadDir);
        if (Files.notExists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        File target = new File(uploadDir, fileNameOnly + System.currentTimeMillis() + "." + suffix);
        try {
            file.transferTo(target);
            return "success";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @ApiOperation("文件下载")
    @GetMapping("/download")
    public String fileDownload(HttpServletResponse response){

        File file = new File("E:/keepme/github-recovery-codes.txt");
        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentLength((int)file.length());
        response.addHeader("Content-Disposition", "attachment;filename=" + System.currentTimeMillis() + FileNameUtil.getSuffix(file));
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            outputStream.write(FileUtil.readBytes(file));
            return "success";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
