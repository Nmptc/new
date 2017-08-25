package com.javarush.task.task40.task4007;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Работа с датами
*/

public class Solution {
    public static void main(String[] args) {
        printDate("21.4.2014 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        Pattern patternDate = Pattern.compile("\\d{1,2}.\\d{1,2}.\\d{4,4}");
        Pattern patternTime = Pattern.compile("\\d{1,2}:\\d{1,2}:\\d{1,2}");
        Matcher matcher = patternDate.matcher(date);
        boolean hasDate = matcher.find();
        matcher = patternTime.matcher(date);
        boolean hasTime = matcher.find();

        String dateFormat = "";
        if(hasDate)
        {
            dateFormat = "dd.MM.yyyy";
            if(hasTime)
                dateFormat = dateFormat + " ";
        }

        if(hasTime)
            dateFormat = dateFormat + "HH:mm:ss";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(simpleDateFormat.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }

        if(hasDate)
        {
            System.out.println("День: " + calendar.get(Calendar.DAY_OF_MONTH));
            int dayofweek = calendar.get(Calendar.DAY_OF_WEEK);
            dayofweek = dayofweek==1?7:(dayofweek-1);
            System.out.println("День недели: " + dayofweek);
            System.out.println("День месяца: " + calendar.get(Calendar.DAY_OF_MONTH));
            System.out.println("День года: " + calendar.get(Calendar.DAY_OF_YEAR));
            System.out.println("Неделя месяца: " + calendar.get(Calendar.WEEK_OF_MONTH));
            System.out.println("Неделя года: " + calendar.get(Calendar.WEEK_OF_YEAR));
            System.out.println("Месяц: " + (calendar.get(Calendar.MONTH)+1));
            System.out.println("Год: " + calendar.get(Calendar.YEAR));
        }
        if(hasTime)
        {
            if(calendar.get(Calendar.AM_PM)==0)
                System.out.println("AM или PM: AM");
            else
                System.out.println("AM или PM: PM");
            System.out.println("Часы: " + calendar.get(Calendar.HOUR));
            System.out.println("Часы дня: " + calendar.get(Calendar.HOUR_OF_DAY));
            System.out.println("Минуты: " + calendar.get(Calendar.MINUTE));
            System.out.println("Секунды: " + calendar.get(Calendar.SECOND));
        }
    }
}
