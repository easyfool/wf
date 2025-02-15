package com.wf;

import lombok.Data;
import org.apache.sshd.sftp.server.FileHandle;

/**
 * @author wangfeng
 * @version 1.0
 * @mail wangfengbabe@163.com
 * @data 2025/2/15 9:34
 * 文件上传、下载、读取等操作都会触发多个 sftp 事件，在多个事件中传递参数，可以使用以下方式
 * AttributeRepository.AttributeKey<FileHandleContext>
 * FileHandle.set(AttributeKey,FileHandleContext)
 * FileHandle.get(AttributeKey)
 */
@Data
public class FileHandleContext {
    private FileHandle fileHandle;
    private String other;
}
