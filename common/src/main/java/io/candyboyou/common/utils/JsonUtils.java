package io.candyboyou.common.utils;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.Set;

public class JsonUtils {

    private final static Gson gson = new Gson();

    public static String toJson(Object o) {
        return gson.toJson(o);
    }

    public static String toJson(Object o, Set<String> ignoreFields) {

        Gson gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                // 返回true，说明要skip
                return ignoreFields.contains(f.getName());
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        }).create();

        return gson.toJson(o);
    }

    public static <T> T parseJson(String jsonString, Type type) {

        return gson.fromJson(jsonString, type);
    }

//    public static void main(String[] args) {
//        Set<String> ignoreFields = new HashSet<>();
//        ignoreFields.add("name");
//
//        PropertyFilter propertyFilter = new PropertyFilter() {
//            @Override
//            // 如果是ignore的，返回false
//            public boolean apply(Object object, String name, Object value) {
//                if (ignoreFields.contains(name)) {
//                    return false;
//                }
//                return true;
//            }
//        };
//
//        UserInfo userInfo = new UserInfo();
//        userInfo.setCompanyName("setCompanyName");
//        userInfo.setName("hhh");
//        String s = toJson(userInfo, ignoreFields);
//        String s = JSON.toJSONString(userInfo, propertyFilter);
//        System.out.println(s);
//    }
}
