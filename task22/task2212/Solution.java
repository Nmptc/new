package com.javarush.task.task22.task2212;

import javax.xml.stream.events.Characters;
import java.util.HashMap;
import java.util.Map;

/*
Проверка номера телефона
*/
public class Solution {
    public static boolean checkTelNumber(String telNumber) {
        if(telNumber==null||telNumber.isEmpty()) return false;
        int pluspos = telNumber.indexOf('+');
        if(pluspos>0) return false;
        char[] chars = telNumber.toCharArray();
        int minuscount = 0;
        int charsinbracket = 0;
        int totaldigits = 0;
        boolean hasOpenBracket = false, hasCloseBracket = false;
        for(int i = 0; i < chars.length;++i)
        {
            switch (chars[i])
            {
                case '-':
                {
                    if(hasOpenBracket&&!hasCloseBracket) return false;
                    ++minuscount;
                    if(minuscount>2) return false;

                    if((i<chars.length-1)&&chars[i+1]=='-')
                        return false;
                    break;
                }
                case '(':
                {
                    if(minuscount>0) return false;
                    if(hasOpenBracket) return false;
                    hasOpenBracket = true;
                    break;
                }
                case ')':
                {
                    if(hasCloseBracket||!hasOpenBracket) return false;
                    hasCloseBracket = true;
                    break;
                }
                case '+':
                {
                    if(i>0) return false;
                    break;
                }
                default:
                {
                    if(Character.isDigit(chars[i]))
                    {
                        if(hasOpenBracket&&!hasCloseBracket)
                        {
                            ++charsinbracket;
                            if(charsinbracket>3)
                                return false;
                        }
                        ++totaldigits;
                    }
                    else
                    {
                        return false;
                    }
                }
            }
        }

        if(hasOpenBracket!=hasCloseBracket) return false;
        if(charsinbracket!=3&&charsinbracket!=0) return false;
        if(pluspos==0) {
            if (totaldigits != 12) return false;
        }
        else
            if(totaldigits!=10) return false;

        return true;
    }

    public static void main(String[] args) {

    }
}
