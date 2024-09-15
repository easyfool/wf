package com.wf.nio.buffer;

import java.nio.ByteBuffer;

import static com.wf.common.ByteBufferUtil.debugAll;

public class TestByteBufferReadWrite {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte) 0x61);
        debugAll(buffer);
        buffer.put(new byte[]{0x62, 0x63, 0x64});
        debugAll(buffer);
        buffer.flip();
        System.out.println(buffer.get());
        debugAll(buffer);
        buffer.compact();
        debugAll(buffer);
//        buffer.rewind();
        buffer.clear();
        debugAll(buffer);
        buffer.clear();
        debugAll(buffer);
        buffer.put((byte) 0x67);
        debugAll(buffer);

    }
}
