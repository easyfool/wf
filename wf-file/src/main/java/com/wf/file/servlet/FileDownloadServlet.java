package com.wf.file.servlet;

import cn.hutool.core.codec.Base64Encoder;
import cn.hutool.core.io.IoUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author wangfeng
 * @version 1.0
 * @mail wangfengbabe@163.com
 * @data 2024/9/16 20:22
 */
@Slf4j
@WebServlet(urlPatterns = "/file2/downloadServlet")
public class FileDownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 获取要下载的文件名
        String fileName = "中国.txt";
        String downloadPath = "E:\\test\\upload\\中国.txt";

        //         4. 在回传前，通过响应头告诉客户端返回的数据类型
        ServletContext servletContext = getServletContext();
        String mimeType = servletContext.getMimeType(downloadPath);
//        resp.setContentType("application/octet-stream");
        resp.setContentType(mimeType);
//         5. 使用响应头告诉客户端收到的数据是用来下载.如果不告诉，则显示在浏览器里面
        //如果文件名称中含有中文，需要特殊处理，否则是乱码
        if (req.getHeader("User-Agent").contains("Firefox")) {
            //火狐浏览器使用base64编码和解码
            resp.setHeader("Content-Disposition", "attachment;filename=?UTF-8?B?" + Base64Encoder.encode(fileName.getBytes(StandardCharsets.UTF_8)));
        } else {
            //IE+chrome使用urlencoder 和urldecoder来编码和解码
            resp.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8.name()));
        }

//        2.读取要下载的文件内容
        //         3. 把下载的文件内容 回传给客户端
        InputStream inputStream = new FileInputStream(downloadPath);
        OutputStream outputStream = resp.getOutputStream();
        IoUtil.copy(inputStream, outputStream);
//        outputStream.flush();


    }
}
