package com.javarush.task.task22.task2210;

import java.util.StringTokenizer;

/* 
StringTokenizer
*/
public class Solution {
    public static void main(String[] args) {
    }

    public static String [] getTokens(String query, String delimiter) {
        StringTokenizer tokenizer = new StringTokenizer(query, delimiter);
        String[] res = new String[tokenizer.countTokens()];
        int i = 0;
        while (tokenizer.hasMoreTokens())        
            res[i++] = tokenizer.nextToken();
        return res;
    }
}
