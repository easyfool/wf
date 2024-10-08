package com.wf.util.compress;

/**
 * @author wangfeng
 * @version 1.0
 * @mail wangfengbabe@163.com
 * @data 2024/9/21 9:47
 * <p>
 * 2010-4-20
 */
/**
 * 2010-4-20
 */

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;

import java.io.*;
import java.util.Arrays;

/**
 * TAR工具
 *
 * @author
 * @since 1.0
 */
public abstract class TarUtils {

    private static final String BASE_DIR = "";

    // 符号"/"用来作为目录标识判断符
    private static final String PATH = "/";
    private static final int BUFFER = 1024;

    private static final String EXT = ".tar";

    /**
     * 归档
     *
     * @param srcPath
     * @param destPath
     * @throws Exception
     */
    public static void archive(String srcPath, String destPath)
            throws Exception {

        File srcFile = new File(srcPath);

        archive(srcFile, destPath);

    }

    /**
     * 归档
     *
     * @param srcFile
     *            源路径
     * @param destFile
     *            目标路径
     * @throws Exception
     */
    public static void archive(File srcFile, File destFile) throws Exception {

        TarArchiveOutputStream taos = new TarArchiveOutputStream(
                new FileOutputStream(destFile));

        archive(srcFile, taos, BASE_DIR);

        taos.flush();
        taos.close();
    }

    /**
     * 归档
     *
     * @param srcFile
     * @throws Exception
     */
    public static void archive(File srcFile) throws Exception {
        String name = srcFile.getName();
        //String basePath = srcFile.getParent();
        String destPath = name + EXT;
        archive(srcFile, destPath);
    }

    /**
     * 归档文件
     *
     * @param srcFile
     * @param destPath
     * @throws Exception
     */
    public static void archive(File srcFile, String destPath) throws Exception {
        archive(srcFile, new File(destPath));
    }

    /**
     * 归档
     *
     * @param srcPath
     * @throws Exception
     */
    public static void archive(String srcPath) throws Exception {
        File srcFile = new File(srcPath);

        archive(srcFile);
    }

    /**
     * 归档
     *
     * @param srcFile
     *            源路径
     * @param taos
     *            TarArchiveOutputStream
     * @param basePath
     *            归档包内相对路径
     * @throws Exception
     */
    private static void archive(File srcFile, TarArchiveOutputStream taos,
                                String basePath) throws Exception {
        if (srcFile.isDirectory()) {
            archiveDir(srcFile, taos, basePath);
        } else {
            archiveFile(srcFile, taos, basePath);
        }
    }

    /**
     * 目录归档
     *
     * @param dir
     * @param taos
     *            TarArchiveOutputStream
     * @param basePath
     * @throws Exception
     */
    private static void archiveDir(File dir, TarArchiveOutputStream taos,
                                   String basePath) throws Exception {

        File[] files = dir.listFiles();

        if (files.length < 1) {
            TarArchiveEntry entry = new TarArchiveEntry(basePath
                    + dir.getName() + PATH);

            taos.putArchiveEntry(entry);
            taos.closeArchiveEntry();
        }

        for (File file : files) {

            // 递归归档
            archive(file, taos, basePath + dir.getName() + PATH);

        }
    }

    /**
     * 数据归档
     *
     * @param taos
     *            待归档数据
     * @param dir
     *            归档数据的当前路径
     * @param file
     *            归档文件名
     * @param taos
     *            TarArchiveOutputStream
     * @throws Exception
     */
    private static void archiveFile(File file, TarArchiveOutputStream taos,
                                    String dir) throws Exception {

        /**
         * 归档内文件名定义
         *
         * <pre>
         * 如果有多级目录，那么这里就需要给出包含目录的文件名
         * 如果用WinRAR打开归档包，中文名将显示为乱码
         * </pre>
         */
        TarArchiveEntry entry = new TarArchiveEntry(dir + file.getName());

        entry.setSize(file.length());

        taos.putArchiveEntry(entry);

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
                file));
        int count;
        byte data[] = new byte[BUFFER];
        while ((count = bis.read(data, 0, BUFFER)) != -1) {
            taos.write(data, 0, count);
        }

        bis.close();

        taos.closeArchiveEntry();
    }

    /**
     * 解归档
     *
     * @param srcFile
     * @throws Exception
     */
    public static void dearchive(File srcFile) throws Exception {
        String basePath = srcFile.getParent();
        dearchive(srcFile, basePath);
    }

    /**
     * 解归档
     *
     * @param srcFile
     * @param destFile
     * @throws Exception
     */
    public static void dearchive(File srcFile, File destFile) throws Exception {

        TarArchiveInputStream tais = new TarArchiveInputStream(
                new FileInputStream(srcFile));
        dearchive(destFile, tais);

        tais.close();

    }

    /**
     * 解归档
     *
     * @param srcFile
     * @param destPath
     * @throws Exception
     */
    public static void dearchive(File srcFile, String destPath)
            throws Exception {
        dearchive(srcFile, new File(destPath));

    }

    /**
     * 文件 解归档
     *
     * @param destFile
     *            目标文件
     * @param tais
     *            ZipInputStream
     * @throws Exception
     */
    private static void dearchive(File destFile, TarArchiveInputStream tais)
            throws Exception {

        TarArchiveEntry entry = null;
        while ((entry = tais.getNextTarEntry()) != null) {

            // 文件
            String dir = destFile.getPath() + File.separator + entry.getName();

            File dirFile = new File(dir);

            // 文件检查
            fileProber(dirFile);

            if (entry.isDirectory()) {
                dirFile.mkdirs();
            } else {
                dearchiveFile(dirFile, tais);
            }

        }
    }

    /**
     * 文件 解归档
     *
     * @param srcPath
     *            源文件路径
     *
     * @throws Exception
     */
    public static void dearchive(String srcPath) throws Exception {
        File srcFile = new File(srcPath);

        dearchive(srcFile);
    }

    /**
     * 文件 解归档
     *
     * @param srcPath
     *            源文件路径
     * @param destPath
     *            目标文件路径
     * @throws Exception
     */
    public static void dearchive(String srcPath, String destPath)
            throws Exception {

        File srcFile = new File(srcPath);
        dearchive(srcFile, destPath);
    }

    /**
     * 文件解归档
     *
     * @param destFile
     *            目标文件
     * @param tais
     *            TarArchiveInputStream
     * @throws Exception
     */
    private static void dearchiveFile(File destFile, TarArchiveInputStream tais)
            throws Exception {

        BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream(destFile));

        int count;
        byte data[] = new byte[BUFFER];
        while ((count = tais.read(data, 0, BUFFER)) != -1) {
            bos.write(data, 0, count);
        }

        bos.close();
    }

    /**
     * 文件探针
     *
     * <pre>
     * 当父目录不存在时，创建目录！
     * </pre>
     *
     * @param dirFile
     */
    private static void fileProber(File dirFile) {

        File parentFile = dirFile.getParentFile();
        if (!parentFile.exists()) {

            // 递归寻找上级目录
            fileProber(parentFile);

            parentFile.mkdir();
        }

    }

    /**
     *
     * @param file 压缩的文件路径
     * @param srcFile 需要压缩的文件
     * @throws Exception
     */
    public static void tarFile(File file, File srcFile) throws Exception {
        TarArchiveOutputStream taos = new TarArchiveOutputStream(new FileOutputStream(srcFile));

        TarArchiveEntry tae = new TarArchiveEntry(file);
        tae.setSize(file.length());//大小
        tae.setName(new String(file.getName().getBytes("gbk"), "ISO-8859-1"));//不设置会默认全路径
        taos.putArchiveEntry(tae);

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        int count;
        byte data[] = new byte[1024];
        while ((count = bis.read(data, 0, 1024)) != -1) {
            taos.write(data, 0, count);
        }
        bis.close();

        taos.closeArchiveEntry();
    }

    public static void archiveTarFile(String destPath, File srcFile) throws Exception {
        TarArchiveInputStream tais = new TarArchiveInputStream(new FileInputStream(srcFile));
        TarArchiveEntry tae = null;
        while ((tae = tais.getNextTarEntry()) != null) {

            String dir = destPath + File.separator + tae.getName();//tar档中文件
            File dirFile = new File(dir);
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dirFile));

            int count;
            byte data[] = new byte[1024];
            while ((count = tais.read(data, 0, 1024)) != -1) {
                bos.write(data, 0, count);
            }

            bos.close();
        }
        tais.close();

    }

    /**
     * 解压tar.gz文件
     * tar文件只是把多个文件或文件夹打包合成一个文件，本身并没有进行压缩。gz是进行过压缩的文件。
     * @param dir
     * @param
     * @throws Exception
     */
    public static void deGzipArchive(String desDir, File srcGZFile)
            throws Exception {
        InputStream is = null;
        CompressorInputStream in = null;
        TarArchiveInputStream tin = null;
        try {
            is = new FileInputStream(srcGZFile);
            in = new GzipCompressorInputStream(is, true);
            tin = new TarArchiveInputStream(in);
            dearchive(new File(desDir), tin);
        } finally {
            if (in != null) {
                in.close();
            }
            if (tin != null) {
                tin.close();
            }
        }

    }


    /**
     * 生成.tar.Z文件
     */
    public static void compressTarZFile(File srcFile,File tarZFile) throws Exception {
        TarUtils.archive(srcFile,tarZFile);
        byte[] tarByte =ByteUtil.getBytes(tarZFile.getAbsolutePath());
        byte[] tarZByte = new byte[tarByte.length];
        int len = ZCompressUtil.Compress.spec_select_action(tarByte,tarByte.length,0,tarZByte);
        tarZByte = Arrays.copyOf(tarZByte,len);
        ByteUtil.saveFile(tarZByte,tarZFile);

    }


}
