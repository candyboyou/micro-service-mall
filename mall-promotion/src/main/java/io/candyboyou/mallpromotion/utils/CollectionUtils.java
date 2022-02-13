package io.candyboyou.mallpromotion.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionUtils {

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return collection != null && !collection.isEmpty();
    }

    public static boolean isEmpty(Iterable<?> iterable) {
        return !isNotEmpty(iterable);
    }

    public static boolean isNotEmpty(Iterable<?> iterable) {
        return iterable != null && iterable.iterator().hasNext();
    }

    /**
     * 合并多个list
     */
    @SafeVarargs
    public static <T> List<T> merge(List<T> ...list) {
        if (list == null || list.length == 0) {
            return Collections.emptyList();
        }
        return Arrays.stream(list).filter(CollectionUtils::isNotEmpty)
                .flatMap(Collection::stream).collect(Collectors.toList());
    }

    /**
     * 合并多个list
     */
    public static <T> List<T> merge(List<List<T>> list) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        return list.stream().filter(CollectionUtils::isNotEmpty)
                .flatMap(Collection::stream).collect(Collectors.toList());
    }

}
