package io.canboyou.mallportal.utils;

import java.io.*;
import java.util.Properties;

/**
 * @author david
 * @date 07/08/18 18:25
 */
public class FileUtil {

    private static String HOST_URL = "lang://image.hasyou.cn/";

    private static String FLY_PATH = "/usr/local/file";

    private static String TEST_PATH = "/tmp";

    private static String PATH = FLY_PATH;

    /**
     * 获取制定bucket下的所有文件
     */
    public static void listFiles() {

    }

    /**
     * 获取当前项目根目录
     */
    public static String getUserDir() {
        return System.getProperty("user.dir");
    }

    /**
     * 获取该用户home目录
     */
    public static String getUserHome() {
        return System.getProperty("user.home");
    }

    /**
     * 根据指定key获取系统参数
     */
    public static String getSysValueByKey(String key) {
        return System.getProperty(key);
    }

    /**
     * 保存properties配置信息
     */
    public static void storeConfigFile(Properties p, String filePath, String fileName) throws IOException {
        String fullPath = filePath + "/" + fileName;
        File file = new File(fullPath);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream stream = new FileOutputStream(file);
        p.store(stream, fullPath);
    }

    /**
     * 写json文件
     */
    public static void writeJson(String path, String fileName, String json) {
        try {
            createDirsIfDoesNotExist(path);
            FileWriter fw = new FileWriter(path + fileName);
            PrintWriter pw = new PrintWriter(fw);
            pw.write(json);
            pw.println();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取json文件
     */
    public static String readJson(String path) {
        File file = new File(path);
        BufferedReader reader = null;
        StringBuilder laststr = new StringBuilder();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                laststr.append(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return laststr.toString();
    }

    /**
     * 读取json文件     * @param file 文件对象     * @return
     */
    public static String readJson(File file) {
        BufferedReader reader = null;
        StringBuilder laststr = new StringBuilder();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                laststr.append(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return laststr.toString();
    }

    /**
     * 创建多级文件夹
     *
     * @param dirs 多级文件夹路径
     */
    public static String createDirsIfDoesNotExist(String... dirs) {
        StringBuilder path = new StringBuilder();
        for (String dir : dirs) {
            if (StringUtils.isBlank(dir)) {
                continue;
            }
            path.append("/").append(dir);
        }
        File file = new File(path.toString());
        if (!file.exists()) {
            file.mkdirs();
        }
        return path.toString();
    }

    /**
     * 创建单级文件夹
     *
     * @param dirName 单机文件夹名
     */
    public static void createDir(String dirName) {
        File file = new File(dirName);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    /**
     * 根据文件路径或者url地址获取文件名称
     */
    public static String getFileName(String src) {
        if (StringUtils.isBlank(src)) {
            return null;
        }
        int extIndex = src.lastIndexOf("/");
        if (extIndex > 0 && extIndex + 1 < src.length()) {
            return src.substring(extIndex + 1).toLowerCase();
        }
        return null;
    }

    /**
     * 获取文件名的后缀名
     *
     * @param fileName 文件名
     * @return 后缀名
     */
    public static String getExtension(String fileName) {
        if (StringUtils.isBlank(fileName)) {
            return null;
        }
        int extIndex = fileName.lastIndexOf(".");
        if (extIndex > 0 && extIndex + 1 < fileName.length()) {
            return fileName.substring(extIndex + 1).toLowerCase();
        }
        return null;
    }

    /**
     * file对象转换字节数组
     */
    public static byte[] getBytes(File file) {
        byte[] buffer = null;
        try {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    /**
     * 文件类型枚举
     */
    public enum FileType {



    }

    public static void main(String[] args) {
        String fileName = getFileName("https://img2.doubanio.com/view/photo/s_ratio_poster/public/p480747492.jpg");
        System.out.println(fileName);
    }

}
