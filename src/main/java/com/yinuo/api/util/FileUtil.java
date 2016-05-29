package com.yinuo.api.util;

import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.zip.*;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-27
 */
public class FileUtil {
    public static final int BUFFER_SIZE = 2048 * 1024;

    public static byte[] compressToGzipBytes(File srcFile) throws IOException {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(srcFile);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            out = new GzipCompressorOutputStream(new BufferedOutputStream(baos, BUFFER_SIZE));
            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            out.close();
            return baos.toByteArray();
        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
        }
    }

    public static byte[] compressToZipBytes(File srcFile, String entryFileName) throws IOException {
        InputStream in = null;
        ZipOutputStream zos = null;
        try {
            in = new FileInputStream(srcFile);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            CheckedOutputStream csum = new CheckedOutputStream(baos, new Adler32());
            zos = new ZipOutputStream(csum);
            byte[] buffer = new byte[1024];
            zos.putNextEntry(new ZipEntry(entryFileName));
            int len;
            while ((len = in.read(buffer)) > 0) {
                zos.write(buffer, 0, len);
            }
            zos.closeEntry();
            zos.close();
            return baos.toByteArray();
        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(zos);
        }
    }

    public static byte[] compressToZipBytes(InputStream in, String entryFileName) throws IOException {
        ZipOutputStream zos = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            CheckedOutputStream csum = new CheckedOutputStream(baos, new Adler32());
            zos = new ZipOutputStream(csum);
            byte[] buffer = new byte[1024];
            zos.putNextEntry(new ZipEntry(entryFileName));
            int len;
            while ((len = in.read(buffer)) > 0) {
                zos.write(buffer, 0, len);
            }
            zos.closeEntry();
            zos.close();
            return baos.toByteArray();
        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(zos);
        }
    }

    public static void doDecompressToDir(File srcFile, File destDir) throws IOException {
        File destFile = new File(destDir, FilenameUtils.getBaseName(srcFile.toString()));
        ZipInputStream zis = null;
        OutputStream os = null;
        byte[] buffer = new byte[BUFFER_SIZE];

        try {
            zis = new ZipInputStream(new FileInputStream(srcFile));
            os = new BufferedOutputStream(new FileOutputStream(destFile), BUFFER_SIZE);
            ZipEntry ze = zis.getNextEntry();
            while (ze != null) {
                String fileName = ze.getName();
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    os.write(buffer, 0, len);
                }
                os.close();
                ze = zis.getNextEntry();
            }
            zis.closeEntry();
            IOUtils.copy(zis, os);
        } finally {
            IOUtils.closeQuietly(zis);
            IOUtils.closeQuietly(os);
        }
    }

    public static void saveToFile(InputStream in, File destFile) throws IOException {
        OutputStream os = null;
        byte[] buffer = new byte[BUFFER_SIZE];
        try {
            os = new BufferedOutputStream(new FileOutputStream(destFile), BUFFER_SIZE);
            IOUtils.copy(in, os);
        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(os);
        }
    }

    public static void doDecompressToFile(InputStream in, File destFile) throws IOException {
        ZipInputStream zis = null;
        OutputStream os = null;
        byte[] buffer = new byte[BUFFER_SIZE];

        try {
            zis = new ZipInputStream(in);
            os = new BufferedOutputStream(new FileOutputStream(destFile), BUFFER_SIZE);
            ZipEntry ze = zis.getNextEntry();
            while (ze != null) {
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    os.write(buffer, 0, len);
                }
                os.close();
                ze = zis.getNextEntry();
            }
            zis.closeEntry();
            IOUtils.copy(in, os);
//            if (destFile.exists() && destFile.isFile()) {
//                System.out.println("destFile length = " + destFile.length());
//                throw new IOException();
//            }
        } finally {
            IOUtils.closeQuietly(zis);
            IOUtils.closeQuietly(os);
        }
    }

    public static byte[] doDecompressToByte(InputStream in) throws IOException {
        ZipInputStream zis = null;
        byte[] buffer = new byte[BUFFER_SIZE];
        ByteArrayOutputStream os = null;
        try {
            os = new ByteArrayOutputStream(BUFFER_SIZE);
            zis = new ZipInputStream(in);
            ZipEntry ze = zis.getNextEntry();
            while (ze != null) {
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    os.write(buffer, 0, len);
                }
                os.close();
                ze = zis.getNextEntry();
            }
            zis.closeEntry();
            return os.toByteArray();
        } finally {
            IOUtils.closeQuietly(os);
            IOUtils.closeQuietly(zis);
        }
    }

    /**
     * 删除单个文件
     *
     * @param sPath 被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    public static InputStream getInputSteam(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        return new FileInputStream(file);
    }
}
