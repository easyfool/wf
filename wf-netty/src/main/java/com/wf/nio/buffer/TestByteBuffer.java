package com.wf.nio.buffer;

import io.netty.buffer.ByteBufUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestByteBuffer {
    public static void main(String[] args) throws IOException {
        FileChannel channel = null;
        try {
            channel = new FileInputStream("data.txt").getChannel();
            //分配10字节的缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate(10);
            while (true) {
                //从channel读，向buffer写
                int read = channel.read(byteBuffer);
                //读取完成
                if (read == -1) {
                    break;
                }
                //buffer切换为读模式
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()) {
                    System.out.println((char) byteBuffer.get());
                }
                //buffer切换为写模式，另外一个切换写模式的方式为buffer.compact()会将未读取的内容往前移动
                byteBuffer.clear();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (channel != null) {
                    channel.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
