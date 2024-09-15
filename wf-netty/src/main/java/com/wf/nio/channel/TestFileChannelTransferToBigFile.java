package com.wf.nio.channel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * 用于拷贝大文件
 */
public class TestFileChannelTransferToBigFile {
    public static void main(String[] args) throws IOException {
        FileChannel in = new FileInputStream("data.txt").getChannel();
        FileChannel out = new FileOutputStream("data_new2.txt").getChannel();
        for (long left = in.size(); left > 0; ) {
            left = left - in.transferTo(in.size() - left, left, out);
        }
        in.close();
        out.close();
    }
}
