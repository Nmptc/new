package com.javarush.task.task40.task4008;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Работа с Java 8 DateTime API
*/

public class Solution {
    public static void main(String[] args) {
        printDate("21.4.2017 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        Pattern patternDate = Pattern.compile("\\d{1,2}.\\d{1,2}.\\d{4,4}");
        Pattern patternTime = Pattern.compile("\\d{1,2}:\\d{1,2}:\\d{1,2}");
        Matcher matcher = patternDate.matcher(date);
        LocalDate localDate;
        if(matcher.find()) {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d.M.yyyy");
            localDate = LocalDate.parse(date.substring(matcher.start(), matcher.end()), dateFormatter);
        }
        else
            localDate = null;
        matcher = patternTime.matcher(date);
        LocalTime localTime;
        if(matcher.find()) {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("H:m:s");
            localTime = LocalTime.parse(date.substring(matcher.start(), matcher.end()), dateFormatter);
        }
        else
            localTime = null;

        if(localDate != null)
        {
            System.out.println("День: " + localDate.getDayOfMonth());
            System.out.println("День недели: " + (localDate.getDayOfWeek().ordinal() + 1));
            System.out.println("День месяца: " + localDate.getDayOfMonth());
            System.out.println("День года: " + localDate.getDayOfYear());
            System.out.println("Неделя месяца: " + localDate.get(WeekFields.of(Locale.getDefault()).weekOfMonth()));
            System.out.println("Неделя года: " + localDate.get(WeekFields.of(Locale.getDefault()).weekOfYear()));
            System.out.println("Месяц: " + (localDate.getMonth().ordinal() + 1));
            System.out.println("Год: " + localDate.getYear());
        }
        if(localTime!=null)
        {
            int hour = localTime.getHour();
            if(hour<13)
                System.out.println("AM или PM: AM");
            else
                System.out.println("AM или PM: PM");
            System.out.println("Часы: " + (hour>12?hour-12:hour));
            System.out.println("Часы дня: " + hour);
            System.out.println("Минуты: " + localTime.getMinute());
            System.out.println("Секунды: " + localTime.getSecond());
        }
    }
}
