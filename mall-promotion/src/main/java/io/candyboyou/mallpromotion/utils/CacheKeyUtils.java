package io.candyboyou.mallpromotion.utils;

public class CacheKeyUtils {

    private static String REDIS_KEY_EX;
    private static String ENV;

    /**
     * 本服务key前缀拼接
     * @param key 数据key
     */
    public static String realKey(String key) {
        if (StringUtils.isEmpty(key)) {
            throw new NullPointerException("redis key cannot be null!");
        }
        String realEnv = "c".equals(ENV) ? "prod" : ENV;
        return REDIS_KEY_EX + "_" + realEnv + "_" + key;
    }

    /**
     * 自定义拼接redis服务前缀名
     * @param keyPrefix 自定义服务前缀
     * @param key 数据key
     */
    public static String realKey(String keyPrefix, String key) {
        if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(keyPrefix)) {
            String realEnv = "c".equals(ENV) ? "prod" : ENV;
            return keyPrefix + "_" + realEnv + "_" + key;
        }
        throw new NullPointerException("redis key or keyPrefix cannot be null!");
    }

    public static void setRedisKeyEx(String redisKeyEx) {
        CacheKeyUtils.REDIS_KEY_EX = redisKeyEx;
    }

    public static void setEnv(String env) {
        CacheKeyUtils.ENV = env;
    }
}

