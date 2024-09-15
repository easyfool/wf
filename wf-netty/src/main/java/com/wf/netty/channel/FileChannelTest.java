package com.wf.netty.channel;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelTest {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("D:\\workspace\\idea\\wf\\wf-netty\\data.txt")) {
            FileChannel channel = in.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(10);
            while (true) {
                int read = channel.read(buffer);
                if (read == -1) {
                    break;
                }
                buffer.flip();
                while (buffer.hasRemaining()) {
                    System.out.println((char) buffer.get());
                }
                buffer.compact();
            }

        } catch (IOException e) {
        }
    }
}
