package com.javarush.task.task22.task2203;

/* 
Между табуляциями
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException {
        if(string == null)
            throw new TooShortStringException();

        int firstt = string.indexOf('\t');
        if(firstt<0)
            throw new TooShortStringException();

        int secondt = string.indexOf('\t', firstt+1);
        if(secondt<0)
            throw new TooShortStringException();

        return string.substring(firstt + 1, secondt);
    }

    public static class TooShortStringException extends Exception {
    }

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("\tJavaRush - лучший сервис \tобучения Java\t."));
    }
}
