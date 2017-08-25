package com.javarush.task.task30.task3008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Администратор on 08.08.2017.
 */
public class ConsoleHelper {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message)
    {
        System.out.println(message);
    }

    public static String readString()
    {
        while (true) {
            try {
                return bufferedReader.readLine();
            } catch (IOException e) {
                writeMessage("Произошла ошибка при попытке ввода текста. Попробуйте еще раз.");
            }
        }
    }

    public static int readInt()
    {
        while (true) {
            try {
                return Integer.parseInt(readString());
            } catch (NumberFormatException ex) {
                writeMessage("Произошла ошибка при попытке ввода числа. Попробуйте еще раз.");
            }
        }
    }
}