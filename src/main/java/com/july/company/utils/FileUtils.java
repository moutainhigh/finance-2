package com.july.company.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 文件工具类
 * @author zengxueqi
 * @program finance
 * @since 2020-06-15 23:35
 **/
public class FileUtils {

    /**
     * 上传文件
     * @param file
     * @param filePath
     * @param fileName
     * @return java.lang.Boolean
     * @author zengxueqi
     * @since 2020/6/15
     */
    public static Boolean uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        FileOutputStream out = null;
        try {
            File targetFile = new File(filePath);
            //如果目录不存在，创建目录
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            out = new FileOutputStream(filePath + fileName);
            out.write(file);
            out.flush();
            //写入成功
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            //写入失败
            return false;
        } finally {
            out.close();
        }
    }

}
