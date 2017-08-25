package com.javarush.task.task26.task2601;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {

    }

    public static Integer[] sort(Integer[] array) {
        Arrays.sort(array);
        final int median;
        if (array.length % 2 == 0)
            median = (int)((double)array[array.length/2-1]+(double)array[array.length/2])/2;
        else
            median = array[array.length/2];
        Arrays.sort(array, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int d1 = Math.abs(o1 - median);
                int d2 = Math.abs(o2 - median);
                if(d1==d2)
                    return o1 - o2;
                else
                    return d1 - d2;
            }
        });
        return array;
    }
}
