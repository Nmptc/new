package com.javarush.task.task22.task2208;

import java.util.HashMap;
import java.util.Map;

/* 
Формируем WHERE
*/
public class Solution {
    public static void main(String[] args) {
    }
    public static String getQuery(Map<String, String> params) {
        StringBuilder builder = new StringBuilder();
        boolean needAnd = false;
        if(params!=null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (entry.getValue() != null) {
                    if (needAnd) builder.append(" and ");
                    else needAnd = true;

                    builder.append(entry.getKey());
                    builder.append(" = ");
                    builder.append("\'");
                    builder.append(entry.getValue());
                    builder.append("\'");
                }
            }
        }
        return builder.toString();
    }
}
