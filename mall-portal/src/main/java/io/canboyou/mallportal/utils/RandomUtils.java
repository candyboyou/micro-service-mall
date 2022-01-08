package io.canboyou.mallportal.utils;

import java.util.Random;

public class RandomUtils {

    public RandomUtils() {
        throw new UnsupportedOperationException();
    }

    /**
     * 获取消息验证码 6位
     */
    public static String getCaptchaRandom() {
        int randomInt = getRandomInt(100_000, 999_999);
        return String.valueOf(randomInt);
    }

    /**
     * 获取指定区间的随机数，区间：[begin, end]
     */
    public static int getRandomInt(int begin, int end) {
        Random random = new Random();
        if (end < begin) {
            throw new IllegalArgumentException("end must grate than begin!");
        }
        return random.nextInt(end - begin + 1) + begin;
    }

}
