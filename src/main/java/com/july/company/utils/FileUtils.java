package com.july.company.utils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;

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

    /**
     * 根据图片文件名获取Base64
     * @param fileName
     * @param uploadFolder
     * @param response
     * @return void
     * @author zengxueqi
     * @since 2020/6/16
     */
    public static String getBase64Images(String fileName, String uploadFolder, HttpServletResponse response) {
        if (fileName != null) {
            //设置文件路径
            String realPath = uploadFolder;
            File file = new File(realPath, fileName);
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            if (file.exists()) {
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(file);
                    //设置页面不缓存
                    response.setHeader("Pragma", "no-cache");
                    response.setHeader("Cache-Control", "no-cache");
                    response.setDateHeader("Expires", 0);
                    BufferedImage bufferedImage = ImageIO.read(fis);
                    //io流
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    //写入流中
                    ImageIO.write(bufferedImage, suffix, baos);
                    //转换成字节
                    byte[] bytes = baos.toByteArray();
                    Base64.Encoder encoder = Base64.getEncoder();
                    //转换成base64串
                    String png_base64 = encoder.encodeToString(bytes).trim();
                    //删除 \r\n
                    png_base64 = png_base64.replaceAll("\n", "").replaceAll("\r", "");
                    return "data:image/" + suffix + ";base64," + png_base64;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }

}
