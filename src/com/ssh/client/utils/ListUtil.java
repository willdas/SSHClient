package com.ssh.client.utils;

import java.util.ArrayList;
import java.util.Collections;

public class ListUtil {

    public static <T> ArrayList<T> newArrayList(T... elements) {
        int capacity = computeArrayListCapacity(elements.length);
        ArrayList<T> list = new ArrayList(capacity);
        Collections.addAll(list, elements);
        return list;
    }

    static int computeArrayListCapacity(int arraySize) {
        return saturatedCast(5L + (long) arraySize + (long) (arraySize / 10));
    }

    private static int saturatedCast(long value) {
        if (value > 2147483647L) {
            return 2147483647;
        } else {
            return value < -2147483648L ? -2147483648 : (int) value;
        }
    }
}
