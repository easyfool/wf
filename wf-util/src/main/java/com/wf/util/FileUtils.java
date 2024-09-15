package com.wf.util;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

/**
 * @author wangfeng
 * @version 1.0
 * @mail wangfengbabe@163.com
 * @data 2024/9/15 9:12
 */
public class FileUtils {
    public static FileTime getLastModifiedTime(String file) {
        try {
            return Files.getLastModifiedTime(Paths.get(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static FileTime getLastAccessTime(String file) {
        try {
            BasicFileAttributeView fileAttributeView = Files.getFileAttributeView(Paths.get(file), BasicFileAttributeView.class);
            BasicFileAttributes basicFileAttributes = fileAttributeView.readAttributes();
            return basicFileAttributes.lastAccessTime();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static FileTime getCreationTime(String file) {
        try {
            BasicFileAttributeView fileAttributeView = Files.getFileAttributeView(Paths.get(file), BasicFileAttributeView.class);
            BasicFileAttributes basicFileAttributes = fileAttributeView.readAttributes();
            return basicFileAttributes.creationTime();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateFileTime(String file, FileTime lastModifiedTime, FileTime lastAccessTime, FileTime createTime) {
        BasicFileAttributeView fileAttributeView = Files.getFileAttributeView(Paths.get(file), BasicFileAttributeView.class);
        try {
            fileAttributeView.setTimes(lastModifiedTime, lastAccessTime, createTime);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateFileLastAccessTime(String file, FileTime lastAccessTime) {
        BasicFileAttributeView fileAttributeView = Files.getFileAttributeView(Paths.get(file), BasicFileAttributeView.class);
        try {
            fileAttributeView.setTimes(null, lastAccessTime, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void updateFileLastModifiedTime(String file, FileTime lastModifiedTime) {
        BasicFileAttributeView fileAttributeView = Files.getFileAttributeView(Paths.get(file), BasicFileAttributeView.class);
        try {
            fileAttributeView.setTimes(lastModifiedTime, null, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void copyFile(String from, String to) throws IOException {
        try (
                FileChannel in = new FileInputStream(from).getChannel();
                FileChannel out = new FileOutputStream(to, true).getChannel();
        ) {
            long totalSize = in.size();
            long left = totalSize;
            while (left > 0) {
                left -= in.transferTo(totalSize - left, left, out);
            }
        }
    }

}
