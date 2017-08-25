package com.javarush.task.task22.task2202;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
    }

    public static String getPartOfString(String string) throws TooShortStringException {
        if(string == null)
            throw new TooShortStringException();

        String[] split = string.split(" ");
        if(split.length<5) throw new TooShortStringException();

        return split[1] + " " + split[2] + " " + split[3] + " " + split[4];
    }

    public static class TooShortStringException extends RuntimeException {
    }
}
