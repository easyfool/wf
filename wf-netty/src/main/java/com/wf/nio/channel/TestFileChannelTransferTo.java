package com.wf.nio.channel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * 使用channel完成文件拷贝
 * 只能用于文件大小小于2G的文件
 */
public class TestFileChannelTransferTo {
    public static void main(String[] args) throws IOException {
        FileChannel in = new FileInputStream("data.txt").getChannel();
        FileChannel out = new FileOutputStream("data_new.txt").getChannel();
        in.transferTo(0, in.size(), out);
        in.close();
        out.close();
    }
}
