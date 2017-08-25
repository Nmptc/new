package com.javarush.task.task26.task2603;

import java.util.Comparator;

/*
Убежденному убеждать других не трудно
*/
public class Solution {

    public static void main(String[] args) {

    }

    public static class CustomizedComparator<T> implements Comparator<T>
    {
        private Comparator<T>[] comparators;

        public CustomizedComparator(Comparator<T>... comparators) {
            this.comparators = comparators;
        }

        @Override
        public int compare(Object o1, Object o2) {
            for(Comparator comparator:comparators)
            {
                int dif = comparator.compare(o1, o2);
                if(dif!=0) return dif;
            }
            return 0;
        }
    }
}
