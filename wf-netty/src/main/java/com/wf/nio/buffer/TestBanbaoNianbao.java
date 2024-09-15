package com.wf.nio.buffer;

import java.nio.ByteBuffer;

import static com.wf.common.ByteBufferUtil.debugAll;

/**
 * 半包粘包处理
 * 根据换行符解析
 */
public class TestBanbaoNianbao {
    public static void main(String[] args) {
        ByteBuffer source = ByteBuffer.allocate(32);
        source.put("hello,world\nI'm zhansan\nHo".getBytes());
        split(source);
        source.put("w are you?\n".getBytes());
        split(source);
    }

    private static void split(ByteBuffer source) {
        source.flip();
        for (int i = 0; i < source.limit(); i++) {
            //找到一条完整的消息
            if (source.get(i) == '\n') {
                int length = i + 1 - source.position();
                //将完整消息存入新的ByteBuffer
                ByteBuffer target = ByteBuffer.allocate(length);
                for (int i1 = 0; i1 < length; i1++) {
                    target.put(source.get());
                }
                debugAll(target);
            }
        }

        source.compact();
    }


}
