package com.wf.file.servlet;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author wangfeng
 * @version 1.0
 * @mail wangfengbabe@163.com
 * @data 2024/9/16 16:11
 */
@Slf4j
@WebServlet(urlPatterns = "/file2/uploadServlet")
public class FileUploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //判断请求是否是多段的，只有多段的才是有文件请求的
        if (ServletFileUpload.isMultipartContent(req)) {
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            //解析上传的数据，得到每一个表单项
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            try {
                List<FileItem> fileItems = servletFileUpload.parseRequest(req);
                //循环每一个表单项，判断是普通表单项还是文件上传表单项
                for (FileItem fileItem : fileItems) {
                    if (fileItem.isFormField()) {
                        //普通表单项
                        log.info("表单项名称：" + fileItem.getFieldName());
                        log.info("表单项value：" + fileItem.getString(StandardCharsets.UTF_8.name()));
                    } else {
                        //文件表单项
                        log.info("表单项名称：" + fileItem.getFieldName());
                        log.info("表单项文件名：" + fileItem.getName());
                        fileItem.write(new File("E:\\test\\upload" + File.separator + fileItem.getName()));

                    }
                }
            } catch (FileUploadException | UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }





}
