package io.canboyou.mallportal.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class Util {

    private static String FULL_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String ES_FORMAT_PATTERN = "yyyy/MM/dd HH:mm:ss";
    private static String DAY_FORMAT = "yyyy-MM-dd";
    private static String timeZone = "Asia/Shanghai";
    private static String utcTimeZone = "UTC";

    /**
     * return uuid
     */
    public static String getUUid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
    }

    /**
     * return FULL_FORMAT time by given timestamp
     */
    public static String getTime(Long timestamp) {
        DateFormat dateFormat = new SimpleDateFormat(FULL_FORMAT);
        dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
        Date date = new Date(timestamp);
        return dateFormat.format(date);
    }

    public static long getTimestamp(String formatTime) {
        if (StringUtils.isBlank(formatTime)) {
            return 0L;
        }
        try {
            DateFormat df = new SimpleDateFormat(FULL_FORMAT);
            df.setTimeZone(TimeZone.getTimeZone(timeZone));
            return df.parse(formatTime).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    /**
     * getString current sql timestamp
     */
    public static Timestamp getCurrentSqlTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * getString current timestamp
     */
    public static long getCurrentTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * getString getBooks values from given map collection(key/function)
     */
    public static <K, V> List<V> getValuesFromMap(Map<K, V> map) {
        ArrayList<V> list = new ArrayList<>();
        map.forEach((key, value) -> list.add(value));
        return list;
    }

    public static String getCurrentFormatTime() {
        SimpleDateFormat sdf = new SimpleDateFormat(FULL_FORMAT);
        sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
        return sdf.format(new Date());
    }

    public static String getEsTime() {
        SimpleDateFormat sdf = new SimpleDateFormat(ES_FORMAT_PATTERN);
        sdf.setTimeZone(TimeZone.getTimeZone(utcTimeZone));
        return sdf.format(new Date());
    }

    public static String getCurrentDayFormatTime() {
        LocalDate now = LocalDate.now();
        return now + " 00:00:00";
    }

    public static Long getCurrentDayTimestamp() {
        String s = getCurrentDayFormatTime();
        SimpleDateFormat sdf = new SimpleDateFormat(FULL_FORMAT);
        try {
            return sdf.parse(s).getTime();
        } catch (ParseException e) {
            return null;
        }
    }

    public static Integer getCurrentDaySeconds() {
        Long timestamp = getCurrentDayTimestamp();
        if (timestamp == null) {
            return null;
        }
        return Math.toIntExact(timestamp / 1000);
    }

    /**
     * getString getBooks keys from given map collection(key/function)
     */
    public static <U, V> List<U> getKeysFromMap(Map<U, V> map) {
        ArrayList<U> list = new ArrayList<>();
        map.forEach((key, value) -> list.add(key));
        return list;
    }

    /**
     * question!
     */
    public static <T> Map<String, T> getReferFromList(String key, List<T> list) {
        HashMap<String, T> map = new HashMap<>();
        list.forEach(item -> {
            Class<?> clazz = item.getClass();
            try {
                Field field = clazz.getDeclaredField(key);
                field.setAccessible(true);
                String k = (String) field.get(item);
                map.put(k, item);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return map;
    }

    /**
     * delete key/function from given map
     */
    @Deprecated
    public static Map removeKeyValueFromMap(Map map, String key) {
        return null;
    }

    /**
     * return a random number of int, >= begin and < end
     *
     * @param begin start number
     * @param end end number
     */
    public static int getRandomInt(int begin, int end) {
        Random random = new Random();
        if (end < begin) {
            throw new IllegalArgumentException("end must grate than begin!");
        }
        return random.nextInt(end - begin + 1) + begin;
    }

    public static void getRandomSleep(int begin, int end) {
        try {
            Thread.sleep(getRandomInt(begin, end) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void getRandomSleep(int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException ignored) {
        }
    }

    public static void getSleep(long millisecond) {
        try {
            Thread.sleep(millisecond);
        } catch (InterruptedException ignored) {
        }
    }

    /**
     * 获取两个集合的差集
     * */
    public static <T> void diffList(List<T> origin, List<T> newList) {
        origin.removeIf(newList::contains);
    }

    public static String getJsonContent(String urlStr) {
        try {
            // 获取HttpURLConnection连接对象
            URL url = new URL(urlStr);
            HttpURLConnection httpConn = (HttpURLConnection) url
                    .openConnection();
            // 设置连接属性
            httpConn.setConnectTimeout(3000);
            httpConn.setDoInput(true);
            httpConn.setRequestMethod("GET");
            // 获取相应码
            int respCode = httpConn.getResponseCode();
            if (respCode == 200) {
                return convertStream2Json(httpConn.getInputStream());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String convertStream2Json(InputStream inputStream) {
        String jsonStr = "";
        // ByteArrayOutputStream相当于内存输出流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        // 将输入流转移到内存输出流中
        try {
            while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
                out.write(buffer, 0, len);
            }
            // 将内存流转换为字符串
            jsonStr = out.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonStr;
    }

    public static String listJoin(List<String> list, char ch) {
        return StringUtils.join(list.toArray(), ch);
    }

    /**
     * 从已有的集合中随机获取指定个数的元素
     * @param list 元素容器
     * @param takeCount 获取数量
     */
    public static <T> List<T> getRandomList(List<T> list, int takeCount) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        if (list.size() <= takeCount) {
            return list;
        }
        List<Integer> randomIndexes = new ArrayList<>(takeCount);
        for (int i = 0; i < takeCount;) {
            int randomInt = getRandomInt(0, takeCount - 1);
            if (randomIndexes.contains(randomInt)) {
                continue;
            }
            randomIndexes.add(randomInt);
            i++;
        }
        List<T> result = new ArrayList<>(takeCount);
        for (Integer randomIndex : randomIndexes) {
            T t = list.get(randomIndex);
            result.add(t);
        }
        return result;
    }

    /**
     * 计算比列，保留小数点后两位，且返回int类型
     * @param numerator 分子
     * @param denominator 分母
     */
    public static Integer calcRate(int numerator, int denominator) {
        float result = (float) numerator / (float) denominator;
        String format = new DecimalFormat("0.00").format(result);
        float value = Float.parseFloat(format);
        return Math.round(value * 100);
    }

    /**
     * 对指定字符串进行md5加密
     */
    public static String md5Encrypt(String content) {
        return Md5Util.md5(content);
    }

}
