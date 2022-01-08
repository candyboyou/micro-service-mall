package io.canboyou.mallportal.utils;

import org.springframework.util.DigestUtils;

/**
 * MD5加密工具类
 */
public class Md5Util {

    public static String md5(String plainText) {
        return DigestUtils.md5DigestAsHex(plainText.getBytes());
    }

}
