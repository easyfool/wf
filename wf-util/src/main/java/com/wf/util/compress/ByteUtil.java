package com.wf.util.compress;

/**
 * @author wangfeng
 * @version 1.0
 * @mail wangfengbabe@163.com
 * @data 2024/9/21 9:46
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * byte数组工具类实现byte[]与文件之间的相互转换
 *
 * @Date 2018-03-26
 */
public class ByteUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ByteUtil.class);

    /**
     * 获得指定文件的byte数组
     */
    public static byte[] getBytes(String filePath) throws Exception{
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            LOGGER.error("文件未找到",e);
            throw e;
        } catch (IOException e) {
            LOGGER.error("io异常",e);
            throw e;
        }
        return buffer;
    }

    /**
     * 根据byte数组，生成文件
     */
    public static void saveFile(byte[] bfile, String filePath,String fileName) throws Exception{
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            //判断文件目录是否存在
            if(!dir.exists()&&dir.isDirectory()){
                dir.mkdirs();
            }
            file = new File(filePath+fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            LOGGER.error("io异常",e);
            throw e;
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    LOGGER.error("io异常",e1);
                    throw e1;
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    LOGGER.error("io异常",e1);
                    throw e1;
                }
            }
        }
    }

    /**
     * 根据byte数组，生成文件
     */
    public static void saveFile(byte[] bfile, File file) throws Exception{
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        try {
            //判断文件是否存在
            if(!file.exists()){
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            LOGGER.error("io异常",e);
            throw e;
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    LOGGER.error("io异常",e1);
                    throw e1;
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    LOGGER.error("io异常",e1);
                    throw e1;
                }
            }
        }
    }
}