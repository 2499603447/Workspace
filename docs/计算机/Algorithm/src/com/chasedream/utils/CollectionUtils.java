/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.utils;

import java.util.*;
import java.util.stream.Stream;

/**
 * @author Zhang Dezhou
 * @Description Encapsulate collection operation not defined by official
 * @date 2020/3/30 23:27
 */
public class CollectionUtils {
    private CollectionUtils() {
    }

    /**
     * Before JDK 1.7 ,we can sorted according to value like this
     * @param map the map need to be sorted.
     * @param <K> key type
     * @param <V> value type
     * @return the map after sorted.
     */
    /*public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, Comparator.comparing(Map.Entry::getValue));

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }*/

    /**
     * After JDK 1.8(also include) ,we can sorted map according to value with Stream.
     *
     * @param map the map need to be sorted.
     * @param <K> key type
     * @param <V> value type
     * @return the map after sorted.
     */
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        Map<K, V> result = new LinkedHashMap<>();
        Stream<Map.Entry<K, V>> st = map.entrySet().stream();
        // from low to high.
        st.sorted(Map.Entry.comparingByValue()).forEach(e -> result.put(e.getKey(), e.getValue()));

        return result;
    }

    /**
     * After JDK 1.8(also include) ,we can sorted map according to key with Stream.
     *
     * @param map the map need to be sorted.
     * @param <K> key type
     * @param <V> value type
     * @return the map after sorted.
     */
    public static <K extends Comparable<? super K>, V> Map<K, V> sortByKey(Map<K, V> map) {
        Map<K, V> result = new LinkedHashMap<>();
        Stream<Map.Entry<K, V>> st = map.entrySet().stream();

        // st.sorted(Map.Entry.comparingByKey()).forEachOrdered(e -> result.put(e.getKey(), e.getValue()))
        // from high to low.
        // st.sorted(Map.Entry.<K, V>comparingByKey().reversed()).forEach(e -> result.put(e.getKey(), e.getValue()))
        // form low to high.
        st.sorted(Map.Entry.comparingByKey()).forEach(e -> result.put(e.getKey(), e.getValue()));

        return result;
    }

    /**
     * list to set
     *
     * @param list need to be converted list.
     * @param <E>  element type
     * @return converted set
     */
    public static <E> Set<E> toSet(List<E> list) {
        return new HashSet<>(list);
    }

    /**
     * set to list
     *
     * @param set need to be converted set
     * @param <E> element type
     * @return converted list
     */
    public static <E> List<E> toList(Set<E> set) {
        return new ArrayList<>(set);
    }
}
